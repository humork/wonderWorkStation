<!--
    User:wm
    Date:2018/07/01
    Add modify passwd
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
    <style type="text/css">
    	td{border: 1px solid #818181;}
    </style>
	<script type="text/javascript">
	$(function(){
		//密码框失去焦点
		$('#opass').blur(function(){			
			var b=$('#opass').validatebox('isValid');			
			if(!b){return;}
			
			var opass=$(this).val();			
			var params={"opass":opass};
			
			//取得数据
			$.ajax({
				url:'<%=basePath%>users/ckPass.do',
				data:params,
				dataType:'json',
				type:'POST',
				//回调函数
				success:function(data){
					if(!data){						
						$.messager.alert('提示','原始密码不正确','warning');
						$('#opass').focus();
						$('#opass').val('');
					}																		
				}
			});	
		});
		//定义自定义的验证规则。继承了equals的验证规则
		$.extend($.fn.validatebox.defaults.rules, {    
		    equals: {
		    	//验证方式    
		        validator: function(value,param){    
		            return value == $(param[0]).val();    
		        },    
		        message:'新密码和确认新密码不相同'   
		    }    
		});
		
		//确认修改
		$('#qbutton').on('click',function(){
			$('#f1').form('submit',{
				url:'<%=basePath%>users/updPass.do',
				//在表单提交成功以后触发。
				success:function(data){
					//将data转换为Json格式
					var obj=$.parseJSON(data);
					if(obj.success){
						$.messager.alert('提示','修改成功，将跳至登陆界面','warning');
						//alert('修改成功，将跳至登陆界面');
						window.parent.location.href='<%=basePath%>users/exit.do';	
					}else{
						$.messager.alert('提示','修改失败','warning');
					}
				}
			});
		});
		
	});	
	
	</script>

  </head>
  
  <body>
  	<form id="f1" method="post"> 	
       <table cellpadding="0" cellspacing="1">
    	<tr>
 			<td align="right">原始密码:</td>
 			<td>
 				<input id="opass" type="password" class="easyui-validatebox" required="required" />
 			</td>
 		</tr>
 		<tr>
 			<td align="right">新&nbsp;密&nbsp;码:</td>	   				
 			<td>
 				<input id="pwd" name="lpass" type="password" class="easyui-validatebox" required="required"/>
 			</td>
 		</tr>
 		<tr>
 			<td align="right">确认新密码:</td>	   				
 			<td>
 				<input id="rpwd" validType="equals['#pwd']"  type="password" class="easyui-validatebox" required="required"/>
 			</td>
 		</tr>
 		<tr>
 			<td colspan="2" align="center">
 				<input type="button" id="qbutton" value="确认"/>
 				<input type="reset" value="清空"/>
 			</td>
 		</tr>
   	</table>
   </form>
  </body>
</html>
