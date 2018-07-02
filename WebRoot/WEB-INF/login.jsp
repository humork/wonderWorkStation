<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="Inc.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//haha
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    <style type="text/css">
	    body{
	    	background-image: url(Inc/img/1581.jpg);}
    	#loginDivDialog{
    		position: absolute; left:970px;top:340px;}
    	.myinput{
	    	background-color:transparent;
	    	border:0px;
	    	width:335px;
	    	font-size:30px;
	    	height:61px;
	    	padding-left: 60px;}
    	
    </style> 
	<script type="text/javascript">		
		$(function(){
			//回车登陆(键盘敲击事件)
			$('body').keydown(function(){
				//事件的对象，保留着事件发生过程中的一些信息(当敲击回车键时)
				if(event.keyCode=='13'){
					$('#dologin').click();
				}
			});
			$('#dologin').on('click',function(){
				//return的目的就是为了阻止form的提交
				return login();
			});
		});
	
		function login(){
			$('#login_form').form('submit',{
				url:'users/login.do',
				//在表单提交成功后触发
				success:function(data){
					var obj=$.parseJSON(data);
					if(obj){
						window.location.href='<%=basePath%>forwards/findUrl.do?url=index.jsp';
					}else{
						$('#lname').val('');
						$('#lpass').val('');
						$.messager.alert('提示','用户名或密码错误','warning');						
					}
				},
				//在提交之前走这里.
				onSubmit:function(){
					var lname=$.trim($('#lname').val());
					var lpass=$.trim($('#lpass').val());
					
					if(lname==''){
						$.messager.alert('提示','用户名不能为空','warning');
						return false;
					}
					if(lpass==''){
						$.messager.alert('提示','密码不能为空','warning');
						return false;
					}	
					return true;
				}
			});	
			//必须返回false.这里返回false的话，点击图片按钮。不会提交！
			return false;
		}
	</script>

  </head>
  
  <body>
    <div id="loginDivDialog">
	    <form id="login_form" method="post">    	
	    	<table align="center">
	    		<tr>
	    			<td>
	    				<input type="text" value="admin" id="lname" name="lname" class="myinput" style="background: url('Inc/img/login_lname.png') no-repeat;"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td></td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<input type="password" value="admin" id="lpass" name="lpass" class="myinput" style="background: url('Inc/img/login_lpass.png') no-repeat;"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td></td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<input  type="image" id="dologin" src="Inc/img/login_denglu.png" />
	    			</td>
	    		</tr>
	    		
	    	</table>
	    </form>
    </div>
  </body>
</html>
