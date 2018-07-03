<!--
  User:wm
  Date:2018/07/02
  Function:Maintenance expiration reminder, insurance expiration reminder, annual inspection expiration reminder
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
    	/*数据表初始设置开始*****************************/  
		$('#ve_dg').datagrid({
            url:'ViewExpirationController/findAll.do',
            fitColumns:true,
            pagination:true,
            rownumbers:true,
            columns:[[
                {field:'carNo',title:'车牌号码'},
                {field:'exDate',title:'到期时间',formatter: function(v,r,i){
					return formatDate(v);
				}},
				{field:'text',title:'提醒类别'}
            ]]
        });
		/*数据表初始设置结束*****************************/
    });
    </script>
  </head>
  <body>
    <!-- 数据展示窗体创建位置 --> 
  	<table id="ve_dg"></table>
  </body>
</html>
