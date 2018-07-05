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
    	$('#fees_dg').datagrid({
            url:'FeesManagerController/findFeesDG.do',
            pagination:true,
            rownumbers:true,            
            toolbar: '#fees_tb',            
            columns:[[
				{field:'id',title:'编号',checkbox:true},
                {field:'car',title:'车牌号码',formatter:function(v,r,i){
                	return v.carNo;
                }},
                {field:'feesName',title:'规费名称'},
                {field:'feesDate',title:'缴费时间',formatter: function(v,r,i){
					return formatTime(v);
				}},
                {field:'feesAmount',title:'规费金额'},	
                {field:'feesUnit',title:'收费单位',formatter:function(v,r,i){
                	return v.unitName;
                }},
                {field:'operator',title:'经办人',formatter:function(v,r,i){
					return v.name;
				}},
				{field:'remarks',title:'备注'}
            ]]            
        });
    	/*数据表初始设置结束*****************************/
    	
    	//新增按钮单击事件
		$('#tb_add').on('click',function(){
			//填充车辆下拉列表
			var url='FeesManagerController/findCarList.do';
			var carKV={k:'id',v:'carNo'};
			initCombobox($('#fees_addForm #car'),url,carKV,-1);
			
			//填充收费单位下拉列表
			var url='FeesManagerController/findFeesUnitList.do';
			var rdKV={k:'id',v:'unitName'};
			initCombobox($('#fees_addForm #feesUnit'),url,rdKV,-1);
			
			//送修时间默认当前时间
			$('#fees_addForm #feesDate').datetimebox('setValue',getNowTime());
						
			//填充经办人【驾驶员】下拉列表
			var url='FeesManagerController/findDriverList.do';
			var opeKV={k:'id',v:'name'};
			initCombobox($('#fees_addForm #operator'),url,opeKV,-1);
			
			//打开新增窗体
			$('#fees_addDialog').dialog('open');					
		});			
		
		//修改按钮单击事件
		$('#tb_edit').on('click',function(){
			//未选中提示
   			var rows=$('#fees_dg').datagrid('getChecked');
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
				url:'FeesManagerController/findFeesById.do',
				data:params,
				dataType:'json',
				type:'POST',
				success:function(data){
					//绑定数据
					updDataBind(data);
					
					$('#fees_updDialog').dialog('open');		
				}
			});
		});		
		
		//删除按钮单击事件
		$('#tb_remove').on('click',function(){
			//未选中提示
   			var rows=$('#fees_dg').datagrid('getChecked');
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
						url:'FeesManagerController/del.do',
						data:params,
						dataType:'json',
						type:'POST',
						success:function(data){
							if(data.success){
								//清空数据表单的选择
								$('#fees_dg').datagrid('unselectAll');
								//刷新数据表单
								$('#fees_dg').datagrid('reload');
							}
							
							$.messager.show({
								title:'提示消息',
								msg:data.msg
							});								
						}
					});
				}else{
					//清空数据表单的选择
					$('#fees_dg').datagrid('unselectAll');
				}
			});
			
		});
		
		
		
		//搜索按钮单击事件
		$('#sel').searchbox({
			searcher:function(v,n){
				var params={};
				
				params['car.carNo']=v;
				
				$('#fees_dg').datagrid('load',params);
			},
			menu:'#mm',
	  		prompt:'此处输入车牌号码，支持模糊查询' 
		});
	
    	
    });
    
    //绑定送修待修改数据
	function updDataBind(data){
		
		//填充车辆下拉列表
		var url='FeesManagerController/findCarList.do';
		var carKV={k:'id',v:'carNo'};
		initCombobox($('#fees_updForm #car'),url,carKV,data.car.id);
		
		//填充收费单位下拉列表
		var url='FeesManagerController/findFeesUnitList.do';
		var rdKV={k:'id',v:'unitName'};
		initCombobox($('#fees_updForm #feesUnit'),url,rdKV,data.feesUnit.id);
		
		//送修时间
		$('#fees_updForm #feesDate').datetimebox('setValue',formatTime(data.feesDate));
					
		//填充经办人【驾驶员】下拉列表
		var url='FeesManagerController/findDriverList.do';
		var opeKV={k:'id',v:'name'};
		initCombobox($('#fees_updForm #operator'),url,opeKV,data.operator.id);		
				
		$('#fees_updForm #id').val(data.id);
		$('#fees_updForm #feesName').val(data.feesName);
		$('#fees_updForm #feesAmount').numberbox('setValue',data.feesAmount);
		$('#fees_updForm [name=remarks]').val(data.remarks);
		
	}
    
    </script>
	

  </head>
  
  <body>
    <!-- 数据展示窗体创建位置 --> 
  	<table id="fees_dg"></table>
  	
  	<!-- 数据工具栏创建位置 -->   
	<div id="fees_tb">
		<a class="easyui-linkbutton" id="tb_add" data-options="iconCls:'icon-add',plain:true" style="float:left;">规费信息登记</a>
		<div class="datagrid-btn-separator"></div>		
		<a class="easyui-linkbutton" id="tb_edit" data-options="iconCls:'icon-edit',plain:true" style="float:left;">规费信息编辑</a>
		<div class="datagrid-btn-separator"></div>
		<a class="easyui-linkbutton" id="tb_remove" data-options="iconCls:'icon-remove',plain:true" style="float:left;">规费信息删除</a>
		<div class="datagrid-btn-separator"></div>
		
    	<!-- 搜索框div创建位置 --> 
    	&nbsp;
		<input style="width:350px" id="sel"></input> 
		<div id="mm" style="width:120px">
			<div data-options="name:'name'">车牌号码</div>
		</div>
	</div>
	
	<!-- 新增规费窗体创建位置 -->
	<div id="fees_addDialog" class="easyui-dialog" title="新增规费信息" 
		style="width:400px;height:450px;align:center;"   
        data-options="iconCls:'icon-add',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler: function(){
				$('#fees_addForm').form('submit',{
					url:'FeesManagerController/add.do',
					success:function(data){
						var obj=$.parseJSON(data);
						if(obj.success){
							//新增成功刷新DG
							$('#fees_dg').datagrid('load');
						
							//成功后清空表单数据
							$('#fees_addForm').form('clear');
							//关闭表单
							$('#fees_addDialog').dialog('close');
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
				$('#fees_addForm').form('clear');
				$('#fees_addDialog').dialog('close');
			}
		}]">
		<form id="fees_addForm" method="post">
			<table>
				<tr>
					<td style="text-align:rigth;">车牌号码：</td>
					<td>
						<input type="text" id="containercar" name="car.id" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">规费名称：</td>
					<td>
						<input type="text" name="feesName" maxlength="25" required="required"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">缴费时间：</td>
					<td>
						<input type="text" id="feeDate" name="feesDate" class="easyui-datetimebox" required="required"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">缴费金额：</td>
					<td>
						<input type="text" id="feeAmount" name="feesAmount" data-options="min:0,precision:2" required="required" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">收费单位：</td>
					<td>
						<input type="text" id="feeUnit" name="feesUnit.id" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">经&nbsp;办&nbsp;人：</td>
					<td>
						<input id="operators" name="operator.id"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">备&nbsp;&nbsp;注：</td>
					<td>
						<input type="text" name="remarks" maxlength="100"/>
					</td>
				</tr>				
			</table>
			
		</form>
	</div> 
	
	<!-- 修改规费窗体创建位置 -->
	<div id="fees_updDialog" class="easyui-dialog" title="修改规费信息" 
		style="width:400px;height:450px;align:center;"   
        data-options="iconCls:'icon-edit',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler: function(){
				$('#fees_updForm').form('submit',{
					url:'FeesManagerController/upd.do',
					success:function(data){
						var obj=$.parseJSON(data);
						if(obj.success){
							//修改成功刷新DG
							$('#fees_dg').datagrid('load');
						
							//成功后清空表单数据
							$('#fees_updForm').form('clear');
							//关闭表单
							$('#fees_updDialog').dialog('close');
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
				$('#fees_updForm').form('clear');
				$('#fees_updDialog').dialog('close');
			}
		}]">
		<form id="fees_updForm" method="post">
			<table>
				<tr>
					<td style="text-align:rigth;">车牌号码：</td>
					<td>
						<input type="hidden" id="id" name="id"/>
						<input type="text" id="car" name="car.id" disabled="disabled"  />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">规费名称：</td>
					<td>
						<input type="text" id="feesName" name="feesName" maxlength="25" required="required"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">缴费时间：</td>
					<td>
						<input type="text" id="feesDate" name="feesDate" class="easyui-datetimebox" required="required"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">缴费金额：</td>
					<td>
						<input type="text" id="feesAmount" name="feesAmount" data-options="min:0,precision:2" required="required" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">收费单位：</td>
					<td>
						<input type="text" id="feesUnit" name="feesUnit.id" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">经&nbsp;办&nbsp;人：</td>
					<td>
						<input id="operator" name="operator.id"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">备&nbsp;&nbsp;注：</td>
					<td>
						<input type="text" name="remarks" maxlength="100"/>
					</td>
				</tr>				
			</table>
			
		</form>
	</div>
  </body>
</html>
