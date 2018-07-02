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
		$('#mr_dg').datagrid({
            url:'MaintainRecController/findMRDG.do',
            pagination:true,
            rownumbers:true,
            toolbar: '#mr_tb',
            columns:[[
				{field:'id',title:'编号',checkbox:true},
                {field:'car',title:'车牌号码',formatter:function(v,r,i){
                	return v.carNo;
                }},
                {field:'mainType',title:'保养类别',formatter:function(v,r,i){
                	return v.text;
                }},
                {field:'mainDate',title:'保养时间',formatter: function(v,r,i){
					return formatDate(v);
				}},
				{field:'mainAmount',title:'保养金额'},
				{field:'mainUnit',title:'保养单位',formatter:function(v,r,i){
                	return v.unitName;
                }}, 
				{field:'mainMil',title:'本次保养里程（公里）'},
				{field:'nextDate',title:'下次保养时间',formatter: function(v,r,i){
					return formatDate(v);
				}},
				{field:'nextMil',title:'下次保养里程（公里）'},  
				{field:'operator',title:'操作人员',formatter:function(v,r,i){
					return v.name;						
				}}
            ]]
       });
    }
    //按钮的单击事件
    function aninit(){
    	//新增按钮事件设置
		$('#tb_add').on('click',function(){
			//填充车辆下拉列表
			var url='MaintainRecController/findCarList.do';
			var carKV={k:'id',v:'carNo'};
			initCombobox($('#mr_addForm #car'),url,carKV,-1);
			
			//填充保养类别下拉列表
			var url='MaintainRecController/findMainTypeList.do';
			initCombobox($('#mr_addForm #mainType'),url,null,-1);
			
			//填充保养单位下拉列表
			var url='MaintainRecController/findMainUnitList.do';
			var rdKV={k:'id',v:'unitName'};
			initCombobox($('#mr_addForm #mainUnit'),url,rdKV,-1);
			
			//保养时间默认当前时间
			$('#mr_addForm #mainDate').datebox('setValue',getNowDate());			
			
			//本次保养里程默认0
			$('#mr_addForm #mainMil').numberbox('setValue',0);
			
			//填充【驾驶员】下拉列表
			var url='MaintainRecController/findDriverList.do';
			var opeKV={k:'id',v:'name'};
			initCombobox($('#mr_addForm #operator'),url,opeKV,-1);
			
			$('#mr_addDialog').dialog('open');					
		});			
		
		//新增界面选择保养时间计算下次保养时间
    	$('#mr_addForm #mainDate').datebox({
    		//onSelect:事件.用户选择了一个日期的时候触发
    		onSelect:function(date){
    			var v1=date;
        		var v2=$('#mr_addForm #nextDate');
        		var v3=$('#mr_addForm #mainMil').numberbox('getValue');
        		var v4=$('#mr_addForm #nextMil');
        		var v5=$('#mr_addForm #car').combobox('getValue');
        		
        		autoNextMaintain(v1,v2,v3,v4,v5);
    		}
    	});
    	
    	//新增界面输入本次保养里程计算下次保养里程
    	$('#mr_addForm #mainMil').on('blur',function(){
    		var v1=$('#mr_addForm #mainDate').datebox('getValue');
    		var v2=$('#mr_addForm #nextDate');
    		var v3=$('#mr_addForm #mainMil').numberbox('getValue');
    		var v4=$('#mr_addForm #nextMil');
    		var v5=$('#mr_addForm #car').combobox('getValue');
    		
    		autoNextMaintain(v1,v2,v3,v4,v5);
    	});
    
    	
    	//新增界面选择车辆自动更新该车辆对应的下次里程下次保养时间
    	$('#mr_addForm #car').combobox({
			onSelect:function(record){
				var v1=$('#mr_addForm #mainDate').datebox('getValue');
	    		var v2=$('#mr_addForm #nextDate');
	    		var v3=$('#mr_addForm #mainMil').numberbox('getValue');
	    		var v4=$('#mr_addForm #nextMil');
	    		var v5=record.id;
	    		
	    		autoNextMaintain(v1,v2,v3,v4,v5);
			}
		});
    	
    	//修改按钮事件设置
		$('#tb_edit').on('click',function(){
			//未选中提示
   			var rows=$('#mr_dg').datagrid('getChecked');
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
				url:'MaintainRecController/findMRById.do',
				data:params,
				dataType:'json',
				type:'POST',
				success:function(data){
					//绑定数据
					updDataBind(data);	
				}
			});
		});		
		
		//修改界面选择保养时间计算下次保养时间
    	$('#mr_updForm #mainDate').datebox({
    		onSelect:function(date){
    			var v1=date;
        		var v2=$('#mr_updForm #nextDate');
        		var v3=$('#mr_updForm #mainMil').numberbox('getValue');
        		var v4=$('#mr_updForm #nextMil');
        		var v5=$('#mr_updForm #car').combobox('getValue');
        		
        		autoNextMaintain(v1,v2,v3,v4,v5);
    		}
    	});
    	
    	//修改界面输入本次保养里程计算下次保养里程
    	$('#mr_updForm #mainMil').on('blur',function(){
    		var v1=$('#mr_updForm #mainDate').datebox('getValue');//本次保养时间
    		var v2=$('#mr_updForm #nextDate');//下次保养时间
    		var v3=$('#mr_updForm #mainMil').numberbox('getValue');//本次保养里程
    		var v4=$('#mr_updForm #nextMil');//下次保养里程
    		var v5=$('#mr_updForm #car').combobox('getValue');//车牌号码
    		
    		autoNextMaintain(v1,v2,v3,v4,v5);
    	});
    	
    	
    	//删除按钮事件设置
		$('#tb_remove').on('click',function(){
			//未选中提示
   			var rows=$('#mr_dg').datagrid('getChecked');
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
						url:'MaintainRecController/del.do',
						data:params,
						dataType:'json',
						type:'POST',
						success:function(data){
							if(data.success){
								//去除选择
								$('#mr_dg').datagrid('unselectAll');
								//刷新数据表格
								$('#mr_dg').datagrid('reload');
							}
							$.messager.show({
								title:'提示消息',
								msg:data.msg
							});								
						}
					});
				}else{
					$('#mr_dg').datagrid('unselectAll');
				}
			});
			
		});
    	
		//搜索按钮事件设置
		$('#sel').searchbox({
			searcher:function(v,n){
				var params={};
				
				params['car.carNo']=v;
				
				$('#mr_dg').datagrid('load',params);
			},
			menu:'#mm',
   			prompt:'此处输入车牌号码，支持模糊查询' 
		});
    	
	 }
    
    //自动设置下次保养时间和里程
    function autoNextMaintain(dDateValue,$nextDate,nMilValue,$nextMil,nCarId){
    	var params={};
	    params['car.id']=nCarId;
	    
    	//取得数据
		$.ajax({
			url:'MaintainRecController/findCarById.do',//查询车辆信息
			data:params,
			dataType:'json',
			type:'POST',
			success:function(data){
				
				//自动计算下次保养里程         保养里程:maintainMil(汽车表中的保养里程)
				$nextMil.numberbox('setValue',(parseInt(data.maintainMil)+parseInt(nMilValue)));
				
				//自动计算下次保养时间		maintainPeriod:保养周期(汽车表中的保养周期)	
				var nextDate=addMonth(dDateValue,data.maintainPeriod);
				$nextDate.datebox('setValue',nextDate);
			}
		});
    }   
    //绑定待修改内容
	function updDataBind(data){
		//填充车辆下拉列表
		var url='MaintainRecController/findCarList.do';
		var carKV={k:'id',v:'carNo'};
		initCombobox($('#mr_updForm #car'),url,carKV,data.car.id);
		
		//填充保养类别下拉列表
		var url='MaintainRecController/findMainTypeList.do';
		initCombobox($('#mr_updForm #mainType'),url,null,data.mainType.id);
		
		//填充保养单位下拉列表
		var url='MaintainRecController/findMainUnitList.do';
		var rdKV={k:'id',v:'unitName'};
		initCombobox($('#mr_updForm #mainUnit'),url,rdKV,data.mainUnit.id);	
		
		//填充经办人【驾驶员】下拉列表
		var url='MaintainRecController/findDriverList.do';
		var opeKV={k:'id',v:'name'};
		initCombobox($('#mr_updForm #operator'),url,opeKV,data.operator.id);		
		
		//待修改ID
		$('#mr_updForm [name=id]').val(data.id);
		//保养时间默认当前时间
		$('#mr_updForm #mainDate').datebox('setValue',formatDate(data.mainDate));
		//本次保养里程
		$('#mr_updForm #mainMil').numberbox('setValue',data.mainMil);
		//本次保养金额
		$('#mr_updForm #mainAmount').numberbox('setValue',data.mainAmount);
		//本次保养内容
		$('#mr_updForm #mainContent').val(data.mainContent);
		//下次保养里程
		$('#mr_updForm #nextMil').val(data.nextMil);
		//下次保养时间
		$('#mr_updForm #nextDate').datebox('setValue',formatDate(data.nextDate));
		//备注
		$('#mr_updForm #remarks').val(data.remarks);
		
		//打开修改窗体
		$('#mr_updDialog').dialog('open');	
		
    }   
    
    </script>
	

  </head>
  
  <body>
    <!-- 数据展示窗体创建位置 --> 
  	<table id="mr_dg"></table>
  	
  	<!-- 数据工具栏创建位置 -->   
	<div id="mr_tb">
		<a class="easyui-linkbutton" id="tb_add" data-options="iconCls:'icon-add',plain:true" style="float:left;">保养信息登记</a>
		<div class="datagrid-btn-separator"></div>		
		<a class="easyui-linkbutton" id="tb_edit" data-options="iconCls:'icon-edit',plain:true" style="float:left;">保养信息编辑</a>
		<div class="datagrid-btn-separator"></div>
		<a class="easyui-linkbutton" id="tb_remove" data-options="iconCls:'icon-remove',plain:true" style="float:left;">保养信息删除</a>
		<div class="datagrid-btn-separator"></div>
		
    	<!-- 搜索框div创建位置 --> 
    	&nbsp;
		<input style="width:350px" id="sel"></input> 
		<div id="mm" style="width:120px">
			<div data-options="name:'name'">车牌号码</div>
		</div>
	</div>
	
	<!-- 保养窗体新增 -->
	<div id="mr_addDialog" class="easyui-dialog" title="保养信息登记" 
		style="width:400px;height:450px;align:center;"   
        data-options="iconCls:'icon-add',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler: function(){
				$('#mr_addForm').form('submit',{
					url:'MaintainRecController/add.do',
					success:function(data){
						var obj=$.parseJSON(data);
						if(obj.success){
							//新增成功刷新
							$('#mr_dg').datagrid('load');
						
							//成功后清空表单数据
							$('#mr_addForm').form('clear');
							//关闭表单
							$('#mr_addDialog').dialog('close');
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
				$('#mr_addForm').form('clear');
				$('#mr_addDialog').dialog('close');
			}
		}]">
		<form id="mr_addForm" method="post">
			<table>
				<tr>
					<td style="text-align:rigth;">车牌号码：</td>
					<td>
						<input type="text" id="car" name="car.id" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">保养类别：</td>
					<td>
						<input id="mainType" name="mainType.id"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">保养时间：</td>
					<td>
						<input type="text" id="mainDate" name="mainDate" class="easyui-datebox" required="required"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">保养金额：</td>
					<td>
						<input type="text" id="mainAmount" name="mainAmount" data-options="min:0,precision:2" required="required" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">保养单位：</td>
					<td>
						<input type="text" id="mainUnit" name="mainUnit.id" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">本次保养里程（公里）：</td>
					<td>
						<input type="text" id="mainMil" name="mainMil" data-options="min:0" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">保养内容：</td>
					<td>
						<input type="text" name="mainContent" maxlength="100"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">下次保养时间：</td>
					<td>
						<input type="text" id="nextDate" readonly="readonly" name="nextDate" class="easyui-datebox" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">下次保养里程（公里）：</td>
					<td>
						<input type="text" id="nextMil" readonly="readonly" name="nextMil" data-options="min:0" class="easyui-numberbox"/>
					</td>
				</tr>				
				<tr>
					<td style="text-align: rigth;">经&nbsp;办&nbsp;人：</td>
					<td>
						<input id="operator" name="operator.id"/>
					</td>
				</tr>	
				<tr>
					<td style="text-align: rigth;">保养备注：</td>
					<td>
						<input type="text" name="remarks" maxlength="100"/>
					</td>
				</tr>
							
			</table>
			
		</form>
	</div>
	
	<!-- 保养窗体修改位置 -->
	<div id="mr_updDialog" class="easyui-dialog" title="保养信息修改" 
		style="width:400px;height:450px;align:center;"   
        data-options="iconCls:'icon-add',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler: function(){
				$('#mr_updForm').form('submit',{
					url:'MaintainRecController/upd.do',
					success:function(data){
						var obj=$.parseJSON(data);
						if(obj.success){
							//新增成功刷新DG
							$('#mr_dg').datagrid('load');
						
							//成功后清空表单数据
							$('#mr_updForm').form('clear');
							//取消选择
							$('#mr_dg').datagrid('unselectAll');
							//关闭表单
							$('#mr_updDialog').dialog('close');
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
				$('#mr_updForm').form('clear');
				//取消选择
				$('#mr_dg').datagrid('unselectAll');
				$('#mr_updDialog').dialog('close');
			}
		}]">
		<form id="mr_updForm" method="post">
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
					<td style="text-align: rigth;">保养类别：</td>
					<td>
						<input id="mainType" name="mainType.id"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">保养时间：</td>
					<td>
						<input type="text" id="mainDate" name="mainDate" class="easyui-datebox" required="required"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">保养金额：</td>
					<td>
						<input type="text" id="mainAmount" name="mainAmount" data-options="min:0,precision:2" required="required" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">保养单位：</td>
					<td>
						<input type="text" id="mainUnit" name="mainUnit.id" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">本次保养里程（公里）：</td>
					<td>
						<input type="text" id="mainMil" name="mainMil" data-options="min:0" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">保养内容：</td>
					<td>
						<input type="text" id="mainContent" name="mainContent" maxlength="100"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">下次保养时间：</td>
					<td>
						<input type="text" id="nextDate" readonly="readonly" name="nextDate" class="easyui-datebox" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">下次保养里程（公里）：</td>
					<td>
						<input type="text" id="nextMil" readonly="readonly" name="nextMil" data-options="min:0" class="easyui-numberbox"/>
					</td>
				</tr>				
				<tr>
					<td style="text-align: rigth;">经&nbsp;办&nbsp;人：</td>
					<td>
						<input id="operator" name="operator.id"/>
					</td>
				</tr>	
				<tr>
					<td style="text-align: rigth;">保养备注：</td>
					<td>
						<input type="text" id="remarks" name="remarks" maxlength="100"/>
					</td>
				</tr>
							
			</table>
			
		</form>
	</div>
	
  </body>
</html>
