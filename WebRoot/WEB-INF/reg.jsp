<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="Inc.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title></title>
   <script type="text/javascript">
   	$(function(){
   		$('#regDialog').dialog({
   			draggable:false,
   			closable:false,//关闭右上角关闭按钮显示
			modal:true,//设置为模式窗体背景不可操作
			buttons:[
			{
				text:'确认注册',
				handler:function(){
					$('#regForm').form('submit',{
						url:'<%=basePath%>users_add.action',
						success:function(data){
							//form方式返回的data格式是标准格式，需要处理
							var obj=$.parseJSON(data);
							if(obj.success){
								window.location.href='<%=basePath%>forwards/url.do?url=login.jsp';
							}
							$.messager.show({
									title:'提示',
									msg:obj.msg
							});
						}
					});
				}
			},{
				text:'返回登录',
				handler:function(){
					window.location.href='<%=basePath%>forwards/url.do?url=login.jsp';
				}
			},{
				text:'重置',
				handler:function(){
					$('#regForm input').val('');
				}
			}]
   		});
   	});   	
   	
   	
   </script>
  </head>
    
  <body style="width:99%;height:97%;">
    <div id="regDialog" style="width:500px;height:700px;" title="用户注册">
    	<form id="regForm" method="post">
	    	<table width="90%">
	    		<tr>
	    			<td colspan="2" style="height:10px;"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">用&nbsp;户&nbsp;名：</td>
	    			<td>
	    				<input type="text" style="width:200px;" id="lname" name="lname"/>
	    				<span style="color:red">*</span>
					</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" style="height:10px;"></td>
	    		</tr> 
	    		<tr>
	    			<td align="right">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
	    			<td>
	    				<input type="password" style="width:200px;" name="lpass"/>
	    				<span style="color:red">*</span>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" style="height:10px;"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">确认密码：</td>
	    			<td>
	    				<input type="password" style="width:200px;" name="rpass"/>
	    				<span style="color:red">*</span>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" style="height:10px;"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">真实姓名：</td>
	    			<td>
	    				<input type="text" style="width:200px;" name="rname"/>
	    				<span style="color:red">*</span>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" style="height:10px;"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">年&nbsp;&nbsp;&nbsp;&nbsp;龄：</td>
	    			<td>
	    				<input type="text" style="width:200px;" name="age" class="easyui-numberbox" data-options="min:0,max:150"/>
	    				<span style="color:red">*</span>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" style="height:10px;"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
	    			<td>
	    				<input type="radio" value="M" name="gender" checked="checked"/>男
	    				<input type="radio" value="F" name="gender"/>女
	    			</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" style="height:10px;"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">电&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
	    			<td>
	    				<input type="text" style="width:200px;" name="phone"/>
	    				<span style="color:red">*</span>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" style="height:10px;"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">部&nbsp;&nbsp;&nbsp;&nbsp;门：</td>
	    			<td>
	    				<input id="dept" name="dept.id" style="width:200px;"/>
	    				<span style="color:red">*</span>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" style="height:10px;"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">职&nbsp;&nbsp;&nbsp;&nbsp;位：</td>
	    			<td>
	    				<input id="post" name="post.id" style="width:200px;"/>
	    				<span style="color:red">*</span>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" style="height:10px;"></td>
	    		</tr>
	    		<tr>
					<td align="right">生&nbsp;&nbsp;&nbsp;&nbsp;日：</td>
					<td>
						<input type="text" name="birthday" style="width:200px;" class="easyui-datebox" required="required"/>
					</td>
				</tr>
				<tr>
	    			<td colspan="2" style="height:10px;"></td>
	    		</tr>
				<tr>
					<td align="right">身&nbsp;份&nbsp;证：</td>
					<td>
						<input type="text" name="card" maxlength="18" style="width:200px;"/>
					</td>
				</tr>
				<tr>
	    			<td colspan="2" style="height:10px;"></td>
	    		</tr>
				<tr>
					<td align="right">入职时间：</td>
					<td>
						<input type="text" id="entrytime" name="entrytime" style="width:200px;" class="easyui-datebox" required="required"/>
					</td>
				</tr>
				<tr>
	    			<td colspan="2" style="height:10px;"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">住&nbsp;&nbsp;&nbsp;&nbsp;址：</td>
	    			<td>
	    				<input type="text" style="width:200px;" name="address" style="width:200px;"/>
	    				<span style="color:red">*</span>
	    			</td>
	    		</tr>
	    	</table>
    	</form>
    </div>
  </body>
</html>
