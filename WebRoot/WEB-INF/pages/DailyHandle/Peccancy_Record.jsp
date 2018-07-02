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
		/*数据表初始设置开始*****************************/  
		$('#pr_dg').datagrid({
            url:'PeccancyController/findPRDG.do',
            pagination:true,
            rownumbers:true,
            toolbar: '#pr_tb',
            columns:[[
				{field:'id',title:'编号',checkbox:true},
                {field:'car',title:'车牌号码',formatter:function(v,r,i){
                	return v.carNo;
                }},
                {field:'option',title:'违章项目',formatter:function(v,r,i){
                	return v.text;
                }},
                {field:'pecDate',title:'违章时间',formatter: function(v,r,i){
					return formatTime(v);
				}},
				{field:'fine',title:'违章罚款'},
				{field:'points',title:'违章扣分'},				 
				{field:'driver',title:'驾驶员',formatter:function(v,r,i){
					return v.name;						
				}},
				{field:'place',title:'违章地点'},
				{field:'remarks',title:'备注'}
            ]]
        });
		/*数据表初始设置结束*****************************/
		
		/*新增按钮事件设置,开始*****************************/
		$('#tb_add').on('click',function(){
			//填充车辆下拉列表
			var url='PeccancyController/findCarList.do';
			var carKV={k:'id',v:'carNo'};
			initCombobox($('#pr_addForm #car'),url,carKV,-1);
			
			//填充违章类别下拉列表
			var url='PeccancyController/findOptionList.do';
			initCombobox($('#pr_addForm #option'),url,null,-1);
			
			//违章时间默认当前时间
			$('#pr_addForm #pecDate').datetimebox('setValue',getNowTime());			
			
			//违章罚款默认0
			$('#pr_addForm #fine').numberbox('setValue',0);
			
			//违章扣分默认0
			$('#pr_addForm #points').numberbox('setValue',0);
			
			//填充驾驶员下拉列表
			var url='PeccancyController/findDriverList.do';
			var opeKV={k:'id',v:'name'};
			initCombobox($('#pr_addForm #driver'),url,opeKV,-1);
			
			$('#pr_addDialog').dialog('open');					
		});			
		/*新增按钮事件设置,结束*****************************/
		
		/*修改按钮事件设置,开始*****************************/
		$('#tb_edit').on('click',function(){
			//未选中提示
   			var rows=$('#pr_dg').datagrid('getChecked');
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
				url:'PeccancyController/findPRById.do',
				data:params,
				dataType:'json',
				type:'POST',
				success:function(data){
					//绑定数据
					updDataBind(data);
					
					$('#pr_updDialog').dialog('open');		
				}
			});
		});		
		/*修改按钮事件设置,结束*****************************/
		
		/*删除按钮事件设置,开始*****************************/
		$('#tb_remove').on('click',function(){
			//未选中提示
   			var rows=$('#pr_dg').datagrid('getChecked');
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
						url:'PeccancyController/del.do',
						data:params,
						dataType:'json',
						type:'POST',
						success:function(data){
							if(data.success){
								//去除选择
								$('#pr_dg').datagrid('unselectAll');
								//刷新dg
								$('#pr_dg').datagrid('reload');
							}
							
							$.messager.show({
								title:'提示消息',
								msg:data.msg
							});								
						}
					});
				}else{
					$('#pr_dg').datagrid('unselectAll');
				}
			});
			
		});
		/*删除按钮事件设置,结束*****************************/
		
		
		/*搜索按钮事件设置,开始*****************************/
		$('#sel').searchbox({
			searcher:function(v,n){
				var params={};
				
				params['car.carNo']=v;
				
				$('#pr_dg').datagrid('load',params);
			},
			menu:'#mm',
	  		prompt:'此处输入车牌号码，支持模糊查询' 
		});
		/*搜索按钮事件设置,结束*****************************/
		
	});
	
	/*绑定待修改内容开始*****************************/ 
	function updDataBind(data){
		//填充车辆下拉列表
		var url='PeccancyController/findCarList.do';
		var carKV={k:'id',v:'carNo'};
		initCombobox($('#pr_updForm #car'),url,carKV,data.car.id);
		
		//填充违章类别下拉列表
		var url='PeccancyController/findOptionList.do';
		initCombobox($('#pr_updForm #option'),url,null,data.option.id);
		
		//违章时间
		$('#pr_updForm #pecDate').datetimebox('setValue',formatTime(data.pecDate));			
		
		//违章罚款
		$('#pr_updForm #fine').numberbox('setValue',data.fine);
		
		//违章扣分
		$('#pr_updForm #points').numberbox('setValue',data.points);
		
		//填充驾驶员下拉列表
		var url='PeccancyController/findDriverList.do';
		var opeKV={k:'id',v:'name'};
		initCombobox($('#pr_updForm #driver'),url,opeKV,data.driver.id);
		
		//绑定ID
		$('#pr_updForm [name=id]').val(data.id);
		
		//绑定违章地点
		$('#pr_updForm #place').val(data.place);
		
		//绑定违章备注
		$('#pr_updForm #remarks').val(data.remarks);
		
		
	}
	/*绑定待修改内容结束*****************************/
	</script>

  </head>
  
  <body>
    <!-- 数据展示窗体创建位置 --> 
  	<table id="pr_dg"></table>
  	
  	<!-- 数据工具栏创建位置 -->   
	<div id="pr_tb">
		<a class="easyui-linkbutton" id="tb_add" data-options="iconCls:'icon-add',plain:true" style="float:left;">违章信息登记</a>
		<div class="datagrid-btn-separator"></div>		
		<a class="easyui-linkbutton" id="tb_edit" data-options="iconCls:'icon-edit',plain:true" style="float:left;">违章信息编辑</a>
		<div class="datagrid-btn-separator"></div>
		<a class="easyui-linkbutton" id="tb_remove" data-options="iconCls:'icon-remove',plain:true" style="float:left;">违章信息删除</a>
		<div class="datagrid-btn-separator"></div>
		
    	<!-- 搜索框div创建位置 --> 
    	&nbsp;
		<input style="width:350px" id="sel"></input> 
		<div id="mm" style="width:120px">
			<div data-options="name:'name'">车牌号码</div>
		</div>
	</div>
	
	<!-- 违章信息新增位置 -->
	<div id="pr_addDialog" class="easyui-dialog" title="违章信息登记" 
		style="width:400px;height:450px;align:center;"   
        data-options="iconCls:'icon-add',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler: function(){
				$('#pr_addForm').form('submit',{
					url:'PeccancyController/add.do',
					success:function(data){
						var obj=$.parseJSON(data);
						if(obj.success){
							//新增成功刷新DG
							$('#pr_dg').datagrid('load');
						
							//成功后清空表单数据
							$('#pr_addForm').form('clear');
							//关闭表单
							$('#pr_addDialog').dialog('close');
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
				$('#pr_addForm').form('clear');
				$('#pr_addDialog').dialog('close');
			}
		}]">
		<form id="pr_addForm" method="post">
			<table>
				<tr>
					<td style="text-align:rigth;">车牌号码：</td>
					<td>
						<input type="text" id="car" name="car.id" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">违章项目：</td>
					<td>
						<input id="option" name="option.id"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">违章时间：</td>
					<td>
						<input type="text" id="pecDate" name="pecDate" class="easyui-datetimebox" required="required"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">违章罚款：</td>
					<td>
						<input type="text" id="fine" name="fine" data-options="min:0,precision:2" required="required" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">违章扣分：</td>
					<td>
						<input type="text" id="points" name="points" data-options="min:0" required="required" class="easyui-numberbox"/>
					</td>
				</tr>								
				<tr>
					<td style="text-align: rigth;">驾&nbsp;驶&nbsp;员：</td>
					<td>
						<input id="driver" name="driver.id"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">违章地点：</td>
					<td>
						<input type="text" name="place" maxlength="100"/>
					</td>
				</tr>	
				<tr>
					<td style="text-align: rigth;">违章备注：</td>
					<td>
						<input type="text" name="remarks" maxlength="100"/>
					</td>
				</tr>
							
			</table>
			
		</form>
	</div>
	
	<!-- 违章信息修改位置 -->
	<div id="pr_updDialog" class="easyui-dialog" title="违章信息修改" 
		style="width:400px;height:450px;align:center;"   
        data-options="iconCls:'icon-add',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler: function(){
				$('#pr_updForm').form('submit',{
					url:'PeccancyController/upd.do',
					success:function(data){
						var obj=$.parseJSON(data);
						if(obj.success){
							//新增成功刷新DG
							$('#pr_dg').datagrid('load');
						
							//成功后清空表单数据
							$('#pr_updForm').form('clear');
							//关闭表单
							$('#pr_updDialog').dialog('close');
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
				$('#pr_updForm').form('clear');
				$('#pr_updDialog').dialog('close');
			}
		}]">
		<form id="pr_updForm" method="post">
			<table>
				<tr>
					<td><input type="hidden" name="id"/></td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align:rigth;">车牌号码：</td>
					<td>
						<input type="text" id="car" name="car.id" disabled="disabled"  />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">违章项目：</td>
					<td>
						<input id="option" name="option.id"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">违章时间：</td>
					<td>
						<input type="text" id="pecDate" name="pecDate" class="easyui-datetimebox" required="required"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">违章罚款：</td>
					<td>
						<input type="text" id="fine" name="fine" data-options="min:0,precision:2" required="required" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">违章扣分：</td>
					<td>
						<input type="text" id="points" name="points" data-options="min:0" required="required" class="easyui-numberbox"/>
					</td>
				</tr>								
				<tr>
					<td style="text-align: rigth;">驾&nbsp;驶&nbsp;员：</td>
					<td>
						<input id="driver" name="driver.id"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">违章地点：</td>
					<td>
						<input type="text" id="place" name="place" maxlength="100"/>
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
