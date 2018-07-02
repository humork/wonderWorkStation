<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../Inc.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    <script type="text/javascript">
    $(function(){
    	init();
    	aninit();
    });
		function init(){
			//数据展示  
			$('#ar_dg').datagrid({
	            url:'AccidentRecController/findARDG.do',
	            pagination:true,
	            rownumbers:true,
	            toolbar: '#ar_tb',
	            columns:[[
					{field:'id',title:'编号',checkbox:true},
	                {field:'car',title:'车牌号码',formatter:function(v,r,i){
	                	return v.carNo;
	                }},
	                {field:'driver',title:'驾驶员',formatter:function(v,r,i){
						return v.name;						
					}},
					{field:'accDate',title:'事故时间',formatter: function(v,r,i){
						return formatTime(v);
					}},
					{field:'accPlace',title:'事故地点'},
					{field:'accExplain',title:'事故说明'},
					{field:'weAmount',title:'我方承担金额'},
					{field:'otherAmount',title:'对方承担金额'},
					{field:'insAmount',title:'保险承担金额'},
					{field:'null',title:'操作',formatter: function(v,r,i){
						var str='<a href="javascript:showDetailed('+r.id+');">查看详细</a>';					
						return str; 					
					}}
	            ]]
			});
		}
		function aninit(){
				
		//新增按钮单击事件
		$('#tb_add').on('click',function(){
			//填充车辆下拉列表
			var url='AccidentRecController/findCarList.do';
			var carKV={k:'id',v:'carNo'};
			initCombobox($('#ar_addForm #car'),url,carKV,-1);
			
			//填充驾驶员下拉列表
			var url='AccidentRecController/findDriverList.do';
			var opeKV={k:'id',v:'name'};
			initCombobox($('#ar_addForm #driver'),url,opeKV,-1);
			
			//违章时间默认当前时间
			$('#ar_addForm #accDate').datetimebox('setValue',getNowTime());
			//我方承担金额默认0
			$('#ar_addForm #weAmount').numberbox('setValue',0);
			//对方承担金额默认0
			$('#ar_addForm #otherAmount').numberbox('setValue',0);
			//保险承担金额默认0
			$('#ar_addForm #insAmount').numberbox('setValue',0);
			//打开新增窗体
			$('#ar_addDialog').dialog('open');					
		});			
		
		//修改按钮单击事件
		$('#tb_edit').on('click',function(){
			//未选中提示
   			var rows=$('#ar_dg').datagrid('getChecked');
   			if(rows.length==0){
				$.messager.show({
					title : '提示',
					msg : '请勾选要修改的记录！'
				});
				return;
			}
			//选中多笔提示
			if(rows.length>1){
				$.messager.show({
					title : '提示',
					msg : '修改时只能选择一条记录！'
				});
				return;
			}
			
			//主键ID
			var id=rows[0].id;
			var params={"id":id};
			
			//取得数据
			$.ajax({
				url:'AccidentRecController/findARById.do',
				data:params,
				dataType:'json',
				type:'POST',
				success:function(data){
					//绑定数据
					updDataBind(data);			
				}
			});
		});		
		
		
		//删除按钮单击事件
		$('#tb_remove').on('click',function(){
			//未选中提示
   			var rows=$('#ar_dg').datagrid('getChecked');
   			if(rows.length==0){
				$.messager.show({
					title : '提示',
					msg : '请勾选要删除的记录！'
				});
				return;
			}
			//选中多笔提示
			if(rows.length>1){
				$.messager.show({
					title : '提示',
					msg : '删除时只能选择一条记录！'
				});
				return;
			}
			
			//主键ID
			var id=rows[0].id;
			var params={"id":id};
			
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
				if(r){
					//取得数据
					$.ajax({
						url:'AccidentRecController/del.do',
						data:params,
						dataType:'json',
						type:'POST',
						success:function(data){
							if(data.success){
								//去除选择
								$('#ar_dg').datagrid('unselectAll');
								//刷新dg
								$('#ar_dg').datagrid('reload');
							}
							
							$.messager.show({
								title:'提示消息',
								msg:data.msg
							});								
						}
					});
				}else{
					$('#ar_dg').datagrid('unselectAll');
				}
			});
			
		});
		
		//搜索按钮单击事件
		$('#sel').searchbox({
			searcher:function(v,n){
				var params={};
				
				params['car.carNo']=v;
				
				$('#ar_dg').datagrid('load',params);
			},
			menu:'#mm',
	  		prompt:'此处输入车牌号码，支持模糊查询' 
		});
		
	}
		
   
    
    /*绑定待修改内容开始*****************************/ 
	function updDataBind(data){
		//填充车辆下拉列表
		var url='AccidentRecController/findCarList.do';
		var carKV={k:'id',v:'carNo'};
		initCombobox($('#ar_updForm #car'),url,carKV,data.car.id);
		
		//填充驾驶员下拉列表
		var url='AccidentRecController/findDriverList.do';
		var opeKV={k:'id',v:'name'};
		initCombobox($('#ar_updForm #driver'),url,opeKV,data.driver.id);
		
		//绑定ID
		$('#ar_updForm [name=id]').val(data.id);
		//违章时间默认当前时间
		$('#ar_updForm #accDate').datetimebox('setValue',formatTime(data.accDate));
		//事故地点
		$('#ar_updForm #accPlace').val(data.accPlace);
		//事故说明
		$('#ar_updForm #accExplain').val(data.accExplain);
		//我方情况
		$('#ar_updForm #weSituation').val(data.weSituation);
		//对方情况
		$('#ar_updForm #otherSituation').val(data.otherSituation);
		//处理结果
		$('#ar_updForm #result').val(data.result);
		//我方承担金额默认0
		$('#ar_updForm #weAmount').numberbox('setValue',data.weAmount);
		//对方承担金额默认0
		$('#ar_updForm #otherAmount').numberbox('setValue',data.otherAmount);
		//保险承担金额默认0
		$('#ar_updForm #insAmount').numberbox('setValue',data.insAmount);
		//事故备注
		$('#ar_updForm #remarks').val(data.remarks);
		
		//打开修改窗体
		$('#ar_updDialog').dialog('open');
	}
	/*绑定待修改内容结束*****************************/
    
    /*展示详细内容，此方法必须声明在初始方法外,开始*****************************/ 
	function showDetailed(id){
		//参数容器
		var params={};
		params['id']=id;
		
		//取得数据
		$.ajax({
			url:'AccidentRecController/findARById.do',
			data:params,
			dataType:'json',
			type:'POST',
			success:function(data){
				//绑定数据开始				
				$('#ar_showDialog #car').html(data.car.carNo);
				$('#ar_showDialog #driver').html(data.driver.name);
				$('#ar_showDialog #accDate').html(formatTime(data.accDate));
				$('#ar_showDialog #accPlace').html(data.accPlace);
				$('#ar_showDialog #accExplain').html(data.accExplain);
				$('#ar_showDialog #weSituation').html(data.weSituation);
				$('#ar_showDialog #otherSituation').html(data.otherSituation);
				$('#ar_showDialog #result').html(data.result);
				$('#ar_showDialog #weAmount').html(data.weAmount);
				$('#ar_showDialog #otherAmount').html(data.otherAmount);
				$('#ar_showDialog #insAmount').html(data.insAmount);
				$('#ar_showDialog #remarks').html(data.remarks);				
				
				//绑定数据结束				
				$('#ar_showDialog').dialog('open');
			}
		});
		
	}	
	/*展示详细内容，此方法必须声明在初始方法外,结束*****************************/
    
    </script>
	

  </head>
  
  <body>
    <!-- 数据展示窗体创建位置 --> 
  	<table id="ar_dg"></table>
  	
  	<!-- 数据工具栏创建位置 -->   
	<div id="ar_tb">
		<a class="easyui-linkbutton" id="tb_add" data-options="iconCls:'icon-add',plain:true" style="float:left;">事故信息登记</a>
		<div class="datagrid-btn-separator"></div>		
		<a class="easyui-linkbutton" id="tb_edit" data-options="iconCls:'icon-edit',plain:true" style="float:left;">事故信息编辑</a>
		<div class="datagrid-btn-separator"></div>
		<a class="easyui-linkbutton" id="tb_remove" data-options="iconCls:'icon-remove',plain:true" style="float:left;">事故信息删除</a>
		<div class="datagrid-btn-separator"></div>
		
    	<!-- 搜索框div创建位置 --> 
    	&nbsp;
		<input style="width:350px" id="sel"></input> 
		<div id="mm" style="width:120px">
			<div data-options="name:'name'">车牌号码</div>
		</div>
	</div>
	
	<!-- 详细窗体创建位置 -->
	<div id="ar_showDialog" class="easyui-dialog" title="事故详细信息" 
		style="width:400px;height:500px;align:center;top:1px;"   
        data-options="iconCls:'icon-tip',resizable:false,modal:true,closed:true,
        buttons:[{
			text:'关闭',
			iconCls: 'icon-cancel',
			handler: function(){
				//去除选择
				$('#ar_dg').datagrid('unselectAll');
				$('#ar_showDialog').dialog('close');
			}
		}]">
		<table>
			<tr>
				<td style="text-align:rigth;">车牌号码：</td>
				<td>
					<span id="car"></span>
				</td>
			</tr>
			<tr>
				<td style="text-align: rigth;">驾&nbsp;驶&nbsp;员：</td>
				<td>
					<span id="driver"></span>
				</td>
			</tr>
			<tr>
				<td style="text-align: rigth;">事故时间：</td>
				<td>
					<span id="accDate"></span>
				</td>
			</tr>
			<tr>
				<td style="text-align: rigth;">事故地点：</td>
				<td>
					<span id="accPlace"></span>
				</td>
			</tr>
			<tr>
				<td style="text-align: rigth;">事故说明：</td>
				<td>
					<span id="accExplain"></span>
				</td>
			</tr>
			<tr>
				<td style="text-align: rigth;">我方情况：</td>
				<td>
					<span id="weSituation"></span>
				</td>
			</tr>
			<tr>
				<td style="text-align: rigth;">对方情况：</td>
				<td>
					<span id="otherSituation"></span>
				</td>
			</tr>
			<tr>
				<td style="text-align: rigth;">处理结果：</td>
				<td>
					<span id="result"></span>
				</td>
			</tr>
			<tr>
				<td style="text-align: rigth;">我方承担金额：</td>
				<td>
					<span id="weAmount"></span>
				</td>
			</tr>
			<tr>
				<td style="text-align: rigth;">对方承担金额：</td>
				<td>
					<span id="otherAmount"></span>
				</td>
			</tr>
			<tr>
				<td style="text-align: rigth;">保险承担金额：</td>
				<td>
					<span id="insAmount"></span>
				</td>
			</tr>
			<tr>
				<td style="text-align: rigth;">违章备注：</td>
				<td>
					<span id="remarks"></span>
				</td>
			</tr>
						
		</table>
	</div>
	
	<!-- 事故信息新增位置 -->
	<div id="ar_addDialog" class="easyui-dialog" title="事故信息登记" 
		style="width:400px;height:450px;align:center;"   
        data-options="iconCls:'icon-add',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler: function(){
				$('#ar_addForm').form('submit',{
					url:'AccidentRecController/add.do',
					success:function(data){
						var obj=$.parseJSON(data);
						if(obj.success){
							//新增成功刷新DG
							$('#ar_dg').datagrid('load');
						
							//成功后清空表单数据
							$('#ar_addForm').form('clear');
							//关闭表单
							$('#ar_addDialog').dialog('close');
						}
						$.messager.show({
							title:'提示',
							msg:obj.msg
						});
					}
				});
			}
		},{
			text:'取消',
			iconCls: 'icon-cancel',
			handler: function(){
				$('#ar_addForm').form('clear');
				$('#ar_addDialog').dialog('close');
			}
		}]">
		<form id="ar_addForm" method="post">
			<table>
				<tr>
					<td style="text-align:rigth;">车牌号码：</td>
					<td>
						<input type="text" id="car" name="car.id" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">驾&nbsp;驶&nbsp;员：</td>
					<td>
						<input id="driver" name="driver.id"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">事故时间：</td>
					<td>
						<input type="text" id="accDate" name="accDate" class="easyui-datetimebox" required="required"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">事故地点：</td>
					<td>
						<input id="accPlace" name="accPlace" maxlength="100" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">事故说明：</td>
					<td>
						<input id="accExplain" name="accExplain" maxlength="200" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">我方情况：</td>
					<td>
						<input id="weSituation" name="weSituation" maxlength="200" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">对方情况：</td>
					<td>
						<input id="otherSituation" name="otherSituation" maxlength="200" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">处理结果：</td>
					<td>
						<input id="result" name="result" maxlength="200" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">我方承担金额：</td>
					<td>
						<input type="text" id="weAmount" name="weAmount" data-options="min:0,precision:2" required="required" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">对方承担金额：</td>
					<td>
						<input type="text" id="otherAmount" name="otherAmount" data-options="min:0,precision:2" required="required" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">保险承担金额：</td>
					<td>
						<input type="text" id="insAmount" name="insAmount" data-options="min:0,precision:2" required="required" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">违章备注：</td>
					<td>
						<input type="text" id="remarks" name="remarks" maxlength="100"/>
					</td>
				</tr>
							
			</table>
			
		</form>
	</div>
	
	<!-- 事故信息修改位置 -->
	<div id="ar_updDialog" class="easyui-dialog" title="事故信息修改" 
		style="width:400px;height:450px;align:center;"   
        data-options="iconCls:'icon-add',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler: function(){
				$('#ar_updForm').form('submit',{
					url:'AccidentRecController/upd.do',
					success:function(data){
						var obj=$.parseJSON(data);
						if(obj.success){
							//新增成功刷新DG
							$('#ar_dg').datagrid('load');
						
							//成功后清空表单数据
							$('#ar_updForm').form('clear');
							//关闭表单
							$('#ar_updDialog').dialog('close');
						}
						$.messager.show({
							title:'提示',
							msg:obj.msg
						});
					}
				});
			}
		},{
			text:'取消',
			iconCls: 'icon-cancel',
			handler: function(){
				$('#ar_updForm').form('clear');
				$('#ar_updDialog').dialog('close');
			}
		}]">
		<form id="ar_updForm" method="post">
			<table>
				<tr>
					<td><input type="hidden" name="id"/></td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align:rigth;">车牌号码：</td>
					<td>
						<input type="text" id="car" name="car.id" disabled="disabled" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">驾&nbsp;驶&nbsp;员：</td>
					<td>
						<input id="driver" name="driver.id"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">事故时间：</td>
					<td>
						<input type="text" id="accDate" name="accDate" class="easyui-datetimebox" required="required"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">事故地点：</td>
					<td>
						<input id="accPlace" name="accPlace" maxlength="100" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">事故说明：</td>
					<td>
						<input id="accExplain" name="accExplain" maxlength="200" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">我方情况：</td>
					<td>
						<input id="weSituation" name="weSituation" maxlength="200" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">对方情况：</td>
					<td>
						<input id="otherSituation" name="otherSituation" maxlength="200" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">处理结果：</td>
					<td>
						<input id="result" name="result" maxlength="200" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">我方承担金额：</td>
					<td>
						<input type="text" id="weAmount" name="weAmount" data-options="min:0,precision:2" required="required" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">对方承担金额：</td>
					<td>
						<input type="text" id="otherAmount" name="otherAmount" data-options="min:0,precision:2" required="required" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">保险承担金额：</td>
					<td>
						<input type="text" id="insAmount" name="insAmount" data-options="min:0,precision:2" required="required" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">违章备注：</td>
					<td>
						<input type="text" id="remarks" name="remarks" maxlength="100"/>
					</td>
				</tr>
							
			</table>
			
		</form>
	</div>
	
  </body>
</html>
