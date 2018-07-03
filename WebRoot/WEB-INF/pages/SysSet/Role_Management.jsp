<!--
	User:wm
	Date:2018/06/30
	Add role management
-->
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
			selReg();
		});
		function init(){
			//创建数据表格.toolbar:顶部工具栏
			$('#t1').datagrid({
				url:'<%=basePath%>RoleController/findRoleDG.do',
				pagination:true,
				toolbar:'#t',
				columns:[[
					{field:'id',checkbox:true},
					{field:'name',title:'角色名称'}
				]]
			});
		 	//为新增按钮添加单击事件
			$('#tb_add').on('click',function(){
				//打开新增窗口
				$('#role_addDialog').dialog('open');
			});	 
			//为编辑按钮添加单击事件
			$('#tb_edit').on('click',function(){
				//获得数据表格中选中的数量。
				var rows=$('#t1').datagrid('getChecked');
				if(rows.length==0){
					$.messager.alert('警告','请选择一个修改的对象','warning');
					return;
				}
				if(rows.length>1){
					//取消勾选当前页所有选择
					$('#t1').datagrid('uncheckAll');
					$.messager.alert('警告','一次只能选择一个对象','warning');
					return;
				}
				//打开修改窗口
				//$('#role_updDialog').dialog('open');
				
				//从后台取值并绑定.
				bindUpdData(rows[0].id);
			});
			//为授权按钮添加单击事件
			$('#tb_grant').on('click',function(){
				var rows=$('#t1').datagrid('getChecked');
				
				if(rows.length==0){
					$.messager.alert('警告','请选择一个需要授权的对象','warning');
					return;
				}
				
				if(rows.length>1){
					//取消勾选当前页所有选择
					$('#t1').datagrid('uncheckAll');
					$.messager.alert('警告','一次只能选择一个','warning');
					return;
				}
				//生成授权树菜单
				createGrantTree(rows[0].id);
			});	
		}
		
	function createGrantTree(roleId){		
    	$('#tt').tree({
			url:'RoleController/findRoleTree.do?id='+roleId,
			parentField:"pid",
			textFiled:"text",
			idFiled:"id",
			checkbox:true
		});
		//打开授权窗口
		$('#grantDialog').dialog('open');
    }
		
		function bindUpdData(intid){
			//用ajax技术去后台查询要修改的值	
			$.ajax({
				url:'RoleController/findRoleById.do ',
				data:{
					id:intid
				},
				dataType:'json',
				type:'POST',
				success:function(data){
					if(data !=null){
						//将查询回来的数据绑定在form表单中
						$('#role_updform #id').val(data.id);
						$('#role_updform #name').val(data.name);
						//打开修改窗体
						$('#role_updDialog').dialog('open');
					}
				}
			});
		}
		function selReg(){
		/*搜索按钮事件设置,开始*****************************/
		$('#sel').searchbox({
			searcher:function(v,n){				
				var params={'name':v};
				
				$('#t1').datagrid('load',params);
			},
			menu:'#mm',
   			prompt:'此处输入用户真实姓名，支持模糊查询' 
		});
		/*搜索按钮事件设置,结束*****************************/
	}
	</script>
  </head>
  	
  <body>
  	<!-- 数据表格创建位置 -->
	<table id="t1"></table>
  	<div id="t">
	  	<a class="easyui-linkbutton" id="tb_add" data-options="iconCls:'icon-add',plain:true" style="float:left;">新增</a>
	   	<!-- 按钮之间的横杠杠 -->
	   	<div class="datagrid-btn-separator"></div>	
	   	<a class="easyui-linkbutton" id="tb_edit" data-options="iconCls:'icon-edit',plain:true" style="float:left;">编辑</a>
	   	<div class="datagrid-btn-separator"></div>
	   	<a class="easyui-linkbutton" id="tb_grant" data-options="iconCls:'icon-edit',plain:true" style="float:left;">授权</a>
	   	<div class="datagrid-btn-separator"></div>
	   	
	   	<input style="width:250px" id="sel"></input> 
		<div id="mm" style="width:120px">
			<div data-options="name:'name'">角色名称</div>
		</div>
	</div>
<!-- 新增窗体创建位置 -->
 <div id="role_addDialog" class="easyui-dialog" title="新增角色" 
		style="width:270px;height:120px;align:center;"   
        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
        	iconCls: 'icon-ok',
        	handler: function(){
        		$('#role_addform').form('submit',{
        			url:'RoleController/addRole.do',
        			//新增成功后走这里
        			success:function(data){
        				var obj=$.parseJSON(data);
        				if(obj.success){
        					//清空表单
        					$('#role_addform').form('clear');
        					//load：加载和显示第一页的所有行
        					$('#t1').datagrid('load');
        					//关闭新增窗口
        					$('#role_addDialog').dialog('close');	
        				}
        				//屏幕右下角弹出一个提示
        				$.messager.show({
								title:'提示',
								msg:obj.msg
						});	
        			},
        			onSubmit:function(){
        				//获得填写的name对象的值。如果为空，则弹出一个提示!
        				if($('#role_addform :input[name=name]').val()==''){
        					$.messager.show({
									title:'提示',
									msg:'角色名称必须填写'
							});
							return false;
        				}
        				return true;
        			}
        	});
        }
     },{
        	text:'取消',
        	iconCls: 'icon-cancel',//格式
        	handler:function(){
        		//清空form表单
        		$('#role_addform').form('clear');
        		//关闭新增窗口
        		$('#role_addDialog').dialog('close');
	      }
        }]">
        <form id="role_addform" method="post"> 
	   		<table>
	   			<tr>
	   				<th>角色名称:</th>
	   				<td>
	   					<input name="name"/>
	   				</td>
	   			</tr>
	   		</table>
   		</form> 
	</div> 
	<!-- 编辑窗体位置 -->
	<div id="role_updDialog" class="easyui-dialog" title="修改角色" 
		style="width:270px;height:120px;align:center;"   
        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true,
		buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler:function(){
				$('#role_updform').form('submit',{
					url:'RoleController/updRole.do',
					success:function(data){
						var obj =$.parseJSON(data);
						if(obj.success){
							//清空form表单
							$('#role_updform').form('clear');
							//关闭编辑窗体
							$('#role_updDialog').dialog('close');
							//数据表格所有行均取消选择
							$('#t1').datagrid('unselectAll');
							//重新加载当前页面
							$('#t1').datagrid('reload');
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
		   		//关闭编辑窗口
		   		$('#role_updDialog').dialog('close');
		   		//所有选择均取消
				$('#t1').datagrid('unselectAll'); 	
		   }		
		}]">
		<form id="role_updform" method="post"> 
	   		<table>
	   			<tr>
	   				<th>角色名称:</th>
	   				<td>
	   					<input type="hidden" id="id" name="id"/>
	   				</td>
	   				<td>
	   					<input id="name" name="name"/>
	   				</td>
	   			</tr>
	   		</table>
   		</form>
	</div>  
	<!-- 授权窗体位置 -->
<div id="grantDialog" class="easyui-dialog" title="角色授权" 
		style="width:500px;height:500px;align:center;"   
        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
        		handler: function(){
					var ids=[];
					//获取选中的角色数量.
					var rows=$('#t1').datagrid('getChecked');
					//获得选中的角色id
					var rid=rows[0].id;
					
					//获得所有选中及半选中状态的节点id
					var nodes=$('#tt').tree('getChecked',['checked','indeterminate']);	      		
        			//遍历
        			$.each(nodes,function(i,v){
						ids.push(v.id);
					});
        			//按照每一个(,)拆分成字符串.(L1,l101,L10101)
        			var mIds=ids.join(',');
        			$.ajax({
        				url:'RoleController/grant.do',
        				data:{
							id:rid,
							mids:mIds
						},
						dataType:'json',
						type:'POST',
        				success:function(data){
							if(data.success){
								//关闭窗口
								$('#grantDialog').dialog('close');
								//数据表格取消全选
								$('#t1').datagrid('unselectAll');
							}
							$.messager.show({
								title:'提示',
								msg:data.msg
							});
						}
        			});
        	}					
        },{
        	text:'取消',
			iconCls: 'icon-cancel',
			handler: function(){
				$('#grantDialog').dialog('close');
				$('#t1').datagrid('unselectAll');
        	}
        }]">
	<!-- 角色授权树位置 -->
		<ul id="tt"></ul>
	</div> 	 
 </body>
</html>
