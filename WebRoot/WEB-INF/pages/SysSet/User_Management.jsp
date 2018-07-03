<!--
	User:wm
	Date:2018/06/29
	Add user management
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
    	//用户数据展示  
    	$(function(){
    		init();
    		aninit();
    		selreg();
    		namejc();
    	});
    	//用户数据展示
    	function init(){
    			$('#users_dg').datagrid({
    			url:'<%=basePath%>users/findUsersDG.do ',
    			fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
           	    pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏
            	rownumbers:true,//如果为true，则显示一个行号列。
                toolbar:'#users_tb', 
           		columns:[[
           		{title:'编号',checkbox:true},
           		{field:'lname',title:'用户名'},
           		{field:'rname',title:'真实姓名'},
           		{field:'age',title:'年龄'},
  				//value:数据库返回来的列的值.row:行记录数据.index:行索引.
           		{field:'gender',title:'性别',formatter:function(value,row,index){
           				return value=='M'?'男':'女';
           		}},
           		{field:'address',title:'地址'},
           		{field:'phone',title:'电话'},
           		{field:'dept',title:'部门',formatter: function(value,row,index){
           				return value.text;
           		}},
           		{field:'post',title:'职务',formatter: function(value,row,index){
           				return value.text;
           		}},
           		{field:'birthday',title:'生日',formatter: function(value,row,index){
           				return formatDate(value);
           		}},
           		{field:'card',title:'身份证号码'},
           		{field:'entrytime',title:'入职时间',formatter: function(value,row,index){
           				return formatDate(value);
           		}},
           		{field:'isdisable',title:'是否启用',formatter: function(value,row,index){
           				return value==1?'启用':'禁用';
           		}},
           		{field:'null',title:'操作',formatter: function(value,row,index){
           				var str='';
           				str='<a href="javascript:resetPass('+row.id+');">重置密码</a>';
           				return str;
           		}}
           		]]
    		}); 
    	}
    	//重置密码
    	function resetPass(id){
    		var params={"id":id};
    		$.ajax({
    			url:'users/resetPass.do',
    			data:params,
    			dataType:'json',
    			type:'POST',
    			success:function(data){
    				$.messager.show({
						title:'提示消息',
						msg:data.msg
					});
    			}
    		});
    	}
    	//搜索按钮事件设置
    	function selreg(){ 		
	    	$('#sel').searchbox({
				searcher:function(v,n){
					var params={'rname':v};
					$('#users_dg').datagrid('load',params);
				},
				menu:'#mm',
	   			prompt:'此处输入用户真实姓名，支持模糊查询' 
			});	
    	}
    	//新增前检查用户名是否已注册过
		function namejc(){
			//失去焦点
			$('#users_addForm #lname').blur(function(){
				var lname=$.trim($(this).val());
				var params={'lname':lname};
				//去后台验证用户名是否已被注册
				$.ajax({
					url:'users/findlname.do',
					data:params,
					dataType:'json',
					type:'POST',
					success:function(data){
						if(data>0){
							$.messager.alert('提示','该用户名已注册','warning');
							$('#users_addForm #lname').val('');
						}		
					}
				});
			});
		
			$.extend($.fn.validatebox.defaults.rules, {    
			   	   phoneNum: { //验证手机号   
				        validator: function(value, param){ 
				         	return /^1[3-8]+\d{9}$/.test(value);
				        },    
				        message: '请输入正确的手机号码.'   
					}       	   
			});
			
			$.extend($.fn.validatebox.defaults.rules, {    
			   	   carNO: { //验证身份证号码   
				        validator: function(value, param){ 
				         	return /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9X]$/.test(value);
				        },    
				        message: '请输入正确的身份证号码.'   
					}       	   
			});
			  			
}
		//按钮单击事件设置
		function aninit(){
			//新增按钮单击事件
			$('#tb_add').on('click',function(){
				//填充部门下拉列表
				var url='users/findDeptList.do';	
				initCombobox($('#users_addForm #dept'),url,null,-1);
				
				//填充职务下拉列表
				var url='users/findPostList.do';
				initCombobox($('#users_addForm #post'),url,null,-1);
				
				//入职时间默认当前时间 setValue:设置日期输入框的值。 
				$('#users_addForm #entrytime').datebox('setValue',getNowDate());
				
				//打开新增窗口
				$('#users_addDialog').dialog('open');
			});
			
			//修改按钮单击事件
			$('#tb_edit').on('click',function(){
				var rows=$('#users_dg').datagrid('getChecked');
				if(rows.length==0){
					$.messager.show({
						title : '提示',
						msg : '请勾选要修改的用户！'
					});
					return;
				}
				if(rows.length>1){
					$.messager.show({
						title : '提示',
						msg : '修改时只能选择一条用户记录！'
					});
					return;
				}	
				updbind(rows[0].id);
			});
			
			//删除按钮单击事件
			$('#tb_remove').on('click',function(){
				var rows=$('#users_dg').datagrid('getChecked');
				if(rows.length==0){
					$.messager.show({
						title : '提示',
						msg : '请勾选要删除的用户！'
					});
					return;
				}
				if(rows.length>1){
					$.messager.show({
						title : '提示',
						msg : '一次只能删除一条用户资料'
					});
					return;
				}	
				delbind(rows[0].id);
			});
			
			//授权按钮的单击事件
			$('#tb_grant').on('click',function(){
				var rows=$('#users_dg').datagrid('getChecked');
				if(rows.length==0){
					$.messager.show({
						title : '提示',
						msg : '请勾选要授予角色的用户！'
					});
					return;
				}
				if(rows.length>1){
					$.messager.show({
						title : '提示',
						msg : '一次只能授予一条'
					});
					return;
				}	
				grantbind(rows[0].id);
			});
		}
		
		//授予角色按钮的数据绑定
		function grantbind(inid){
			$.ajax({
				url:'users/findUsersRoleList.do',
				data:{
					id:inid
				},
				dataType:'json',
				type:'POST',
				success:function(data){
					var vHtml="";
					//遍历返回来的所有角色
					$.each(data,function(k,v){
						vHtml+="&nbsp;&nbsp;";
						if(v.ck){
							vHtml+="<input type='checkbox' name='roleIds' value='"+v.id+"' checked='checked'/>"+v.name;
						}else{
							vHtml+="<input type='checkbox' name='roleIds' value='"+v.id+"'/>"+v.name;
						}
					});
					//将拼接好的html放到指定div
					$('#users_assignForm #assignNames').html(vHtml);
					//将用户id加到指定位置
					$('#users_assignForm #id').val(inid);
        			//打开指派角色窗口
        			$('#users_assignDialog').dialog('open');
				}
			});	
		}
		
		//删除的数据绑定
		function delbind(inid){
			$.messager.confirm('确认','您确认想要删除该用户吗？',function(r){
					if(r){
						$.ajax({
							url:'users/del.do',
							data:{
								id:inid
							},
							dataType:'json',
							type:'POST',
							success:function(data){
								if(data.success){
									//刷新数据表格
									$('#users_dg').datagrid('reload');
								}	
								$.messager.show({
									title:'提示消息',
									msg:data.msg
								});								
						 	}												
						});		
				 	}
			});	
		}
		
		//修改按钮的数据绑定
		function updbind(inid){
			$.ajax({
				url:'users/findUsersid.do',
				data:{
					id:inid
				},
				dataType:'json',
				type:'POST',
				success:function(data){
					//填充部门下拉列表
					var url='users/findDeptList.do';	
					initCombobox($('#users_updForm #dept'),url,null,data.dept.id);
				
					//填充职务下拉列表
					var url='users/findPostList.do';
					initCombobox($('#users_updForm #post'),url,null,data.post.id);
					
					//入职时间
					$('#users_updForm #entrytime').datebox('setValue',formatDate(data.entrytime));
					
					//生日
					$('#users_updForm #birthday').datebox('setValue',formatDate(data.birthday));
				
					//绑定是否启用
		        	$('#users_updForm :radio[name=isdisable]').each(function(){
		        		if($(this).val()==data.isdisable){
		        			$(this).prop('checked',true);
		        		}
		        	});	
				
					$('#users_updForm #id').val(data.id);
					$('#users_updForm #lname').val(data.lname);
					$('#users_updForm #rname').val(data.rname);
					$('#users_updForm #age').numberbox('setValue',data.age);
					$('#users_updForm [name=gender][value='+data.gender+']').prop('checked',true);
					$('#users_updForm #address').val(data.address);
					$('#users_updForm #phone').val(data.phone);
					$('#users_updForm #card').val(data.card);
					
					//打开修改窗体
					$('#users_updDialog').dialog('open');
				}
			});
		}   
</script>
	

  </head>
  
  <body>
  	<!-- 数据表格展示区域 -->
  	<table id="users_dg"></table>
  	<!-- 工具栏以及搜索框div创建位置 -->   
	<div id="users_tb">
		<a class="easyui-linkbutton" id="tb_add" data-options="iconCls:'icon-add',plain:true" style="float:left;">新增</a>
		<div class="datagrid-btn-separator"></div>
		<a class="easyui-linkbutton" id="tb_edit" data-options="iconCls:'icon-edit',plain:true" style="float:left;">编辑</a>
		<div class="datagrid-btn-separator"></div>
		<a class="easyui-linkbutton" id="tb_remove" data-options="iconCls:'icon-remove',plain:true" style="float:left;">删除</a>
		<div class="datagrid-btn-separator"></div>		
		<a class="easyui-linkbutton" id="tb_grant" data-options="iconCls:'icon-edit',plain:true" style="float:left;">指派角色</a>
		<div class="datagrid-btn-separator"></div>
		<!-- 搜索框div创建位置 --> 
		<input id="sel"></input> 
		<div id="mm" style="width:120px">
			<div data-options="name:'item1',iconCls:'icon-ok'">用户真实姓名</div>
		</div>
	</div>
	<!-- 新增窗口 -->
	 <!--closed:true默认窗体是关闭的  -->
    <div id="users_addDialog" class="easyui-dialog" title="新增用户信息" 
		style="width:360px;height:450px;align:center;top:1px;"   
        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler: function(){
        		$('#users_addForm').form('submit',{
        			url:'users/addusers.do',
        			success:function(data){
						var obj=$.parseJSON(data);
        				if(obj.success){
								//清空表单
								$('#users_addForm').form('clear');
								//修改成功后刷新datagrid
								$('#users_dg').datagrid('reload');
								//关闭窗体
								$('#users_addDialog').dialog('close');
							}
        					$.messager.show({
								title:'提示',
								msg:obj.msg
							});
        			},
        			onSubmit:function(){
        					//validate方法：做表单字段验证，当所有字段都有效的时候返回true。
							var isValid = $(this).form('validate');
							if (!isValid){
								$.messager.alert('提示','表单未填写完整','warning');
							}
							return isValid;
						}
        		});	
        	}
        	
        	},{
        		text:'取消',
        		iconCls: 'icon-cancel',
        		handler:function(){
        			$('#users_addForm').form('clear');
        			$('#users_addDialog').dialog('close');
        		}
        	}]">
        <form id="users_addForm" method="post">
	   		<table>
	    		<tr>
	    			<td align="right">用&nbsp;户&nbsp;名：</td>
	    			<td>
	    				<!-- class="easyui-validatebox" 验证框 -->
	    				<input type="text" style="width:200px;" id="lname" name="lname"  class="easyui-validatebox" data-options="required:true" />
					</td>
	    		</tr>
	    		<tr>
	    			<td align="right">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
	    			<td>
	    				<input type="password" style="width:200px;" value="123456" name="lpass"/>
	    			</td>
	    		</tr>	    		
	    		<tr>
	    			<td align="right">真实姓名：</td>
	    			<td>
	    				<input type="text" style="width:200px;" name="rname"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td align="right">年&nbsp;&nbsp;&nbsp;&nbsp;龄：</td>
	    			<td>
	    				<input type="text" style="width:200px;" name="age" class="easyui-numberbox" data-options="min:0,max:150"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td align="right">性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
	    			<td>
	    				<input type="radio" value="M" name="gender" checked="checked"/>男
	    				<input type="radio" value="F" name="gender"/>女
	    			</td>
	    		</tr>	    		
	    		<tr>
	    			<td align="right">电&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
	    			<td>
	    				<input id="phone" name="phone" maxlength="11" class="easyui-validatebox" data-options="validType:'phoneNum'">  
	    			</td>
	    		</tr>	    		
	    		<tr>
	    			<td align="right">部&nbsp;&nbsp;&nbsp;&nbsp;门：</td>
	    			<td>
	    				<input id="dept" name="dept.id" style="width:200px;"/>
	    			</td>
	    		</tr>	    		
	    		<tr>
	    			<td align="right">职&nbsp;&nbsp;&nbsp;&nbsp;位：</td>
	    			<td>
	    				<input id="post" name="post.id" style="width:200px;"/>
	    			</td>
	    		</tr>	    		
	    		<tr>
					<td align="right">生&nbsp;&nbsp;&nbsp;&nbsp;日：</td>
					<td>
						<input type="text" name="birthday" style="width:200px;" class="easyui-datebox"  required="required"/>
					</td>
				</tr>				
				<tr>
					<td align="right">身&nbsp;份&nbsp;证：</td>
					<td>
						<input id="card" name="card" maxlength="18" class="easyui-validatebox" data-options="validType:'carNO'"/>  
						<!-- <input id="card" name="card" maxlength="18" class="easyui-validatebox" data-options="validType:'carNO'"/> -->
					</td>
				</tr>
				<tr>
					<td align="right">入职时间：</td>
					<td>
						<input type="text" id="entrytime" name="entrytime" style="width:200px;" class="easyui-datebox" required="required"/>
					</td>
				</tr>
	    		<tr>
	    			<td align="right">住&nbsp;&nbsp;&nbsp;&nbsp;址：</td>
	    			<td>
	    				<input type="text" style="width:200px;" name="address" style="width:200px;"/>
	    			</td>
	    		</tr>
	    		<tr>
					<td style="text-align: rigth;">是否启用：</td>
					<td>
						<input type="radio" name="isdisable" value="1" checked="checked"/>启用
						<input type="radio" name="isdisable" value="0"/>禁用
					</td>
				</tr>
	    	</table>
   		</form>
    </div>
 	<!-- 修改窗口 -->
 	<div id="users_updDialog" class="easyui-dialog" title="修改用户信息" 
		style="width:360px;height:440px;align:center;top:1px;"   
        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler: function(){
				$('#users_updForm').form('submit',{
        			url:'users/upd.do',
        			
        			success:function(data){
        				var obj=$.parseJSON(data);
        				
        				if(obj.success){
        					//清空form表单
        					$('#users_updForm').form('clear');
        					//刷新数据表格
        					$('#users_dg').datagrid('reload');   
        					//关闭修改窗口
        					$('#users_updDialog').dialog('close');
        					//取消数据表格的所有勾选
							$('#users_dg').datagrid('unselectAll');     				
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
			handler:function(){
				//关闭修改窗口
        		$('#users_updDialog').dialog('close');
				//取消数据表格的所有勾选
				$('#users_dg').datagrid('unselectAll'); 
			
			}
		}]">
        <form id="users_updForm" method="post"> 
	   		<table>
	   			<tr>
	   				<td></td>
	   				<td>
	   					<input type="hidden" id="id" name="id"/>
	   				</td>
	   			</tr>
	   			<tr>
	    			<td align="right">用&nbsp;户&nbsp;名：</td>
	    			<td>
	    				<input type="text" style="width:200px;" id="lname" name="lname" readonly="readonly" />
					</td>
	    		</tr>	    		
	    		<tr>
	    			<td align="right">真实姓名：</td>
	    			<td>
	    				<input type="text" style="width:200px;" id="rname" name="rname"/>
	    			</td>
	    		</tr>	    		
	    		<tr>
	    			<td align="right">年&nbsp;&nbsp;&nbsp;&nbsp;龄：</td>
	    			<td>
	    				<input type="text" style="width:200px;" id="age" name="age" class="easyui-numberbox" data-options="min:0,max:150"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td align="right">性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
	    			<td>
	    				<input type="radio" value="M" name="gender" checked="checked"/>男
	    				<input type="radio" value="F" name="gender"/>女
	    			</td>
	    		</tr>
	    		<tr>
	    			<td align="right">电&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
	    			<td>
	    				<input id="phone" name="phone" maxlength="11" class="easyui-validatebox" data-options="validType:'phoneNum'">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td align="right">部&nbsp;&nbsp;&nbsp;&nbsp;门：</td>
	    			<td>
	    				<input id="dept" id="dept" name="dept.id" style="width:200px;"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td align="right">职&nbsp;&nbsp;&nbsp;&nbsp;位：</td>
	    			<td>
	    				<input id="post" id="post" name="post.id" style="width:200px;"/>
	    			</td>
	    		</tr>
	    		<tr>
					<td align="right">生&nbsp;&nbsp;&nbsp;&nbsp;日：</td>
					<td>
						<input type="text" id="birthday" name="birthday" style="width:200px;" class="easyui-datebox" required="required"/>
					</td>
				</tr>
				<tr>
					<td align="right">身&nbsp;份&nbsp;证：</td>
					<td>
						<input id="card" name="card" maxlength="18" class="easyui-validatebox" data-options="validType:'carNO'">  
					</td>
				</tr>
				<tr>
					<td align="right">入职时间：</td>
					<td>
						<input type="text" id="entrytime" name="entrytime" style="width:200px;" class="easyui-datebox" required="required"/>
					</td>
				</tr>
	    		<tr>
	    			<td align="right">住&nbsp;&nbsp;&nbsp;&nbsp;址：</td>
	    			<td>
	    				<input type="text" style="width:200px;" id="address" name="address" style="width:200px;"/>
	    			</td>
	    		</tr>
	    		<tr>
					<td style="text-align: rigth;">是否启用：</td>
					<td>
						<input type="radio" name="isdisable" value="1" checked="checked"/>启用
						<input type="radio" name="isdisable" value="0"/>禁用
					</td>
				</tr>
	   		</table>
   		</form>
        </div> 
 <!-- 指派角色窗口 -->
  <div id="users_assignDialog" class="easyui-dialog" title="指派角色" 
		style="width:400px;height:300px;align:center;"   
        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler: function(){
				$('#users_assignForm').form('submit',{
					url:'users/addUsersRole.do',
					onSubmit:function(){
						var arrayRIDS=[];
						//得到所有选中的复选框的对象数组
						var cks=$('#users_assignForm :checkbox[name=roleIds]:checked');
						//遍历
						$.each(cks,function(i,v){
							arrayRIDS.push(v.value);
						});
						//拆分成字符串
						var strRIDS=arrayRIDS.join(',');
						
						//将拼接好的值放入隐藏域中
						$('#rids').val(strRIDS);
						return true;
					},
					success:function(data){
						//form方式返回的data格式是标准格式，需要处理
						var obj=$.parseJSON(data);
						
						if(obj.success){
							$('#users_assignDialog').dialog('close');
							$('#users_dg').datagrid('unselectAll');
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
				$('#users_assignDialog').dialog('close');
				$('#users_dg').datagrid('unselectAll');
			}
		}]">  
		<form id="users_assignForm" method="post">
			<input type="hidden" id="id" name="id"/>
			<input type="hidden" id="rids" name="rids"/>
			<div id="assignNames"></div>	   		
   		</form>
	</div>		
  </body>
</html>
