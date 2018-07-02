<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入jquery的文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/Inc/js/easyui/jquery.min.js"></script>
<!-- 引入jquery.cookie文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/Inc/js/jquery.cookie.js"></script>


<%
		//从cookies中读取主题名称
		String easyuiThemeName = "default";
		//获得Cookie数组
		Cookie cookies[] = request.getCookies();
		if(cookies != null && cookies.length > 0){
			for(int i=0; i<cookies.length; i++){
				//当Cookie中，有名字为easyuiThemeName时
				if(cookies[i].getName().equals("easyuiThemeName")){
					//去出Cookie中的名字为easyuiThemeName的值
					easyuiThemeName = cookies[i].getValue();
					break;
				}
			}
		}
%>


<!-- 引入easyui的样式表 -->
<link id="easyuiTheme" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/Inc/js/easyui/themes/<%=easyuiThemeName%>/easyui.css" ></link>
<!-- 引入easyui的图标导航 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/Inc/js/easyui/themes/icon.css" ></link>
<!-- 引入easyui的文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/Inc/js/easyui/jquery.easyui.min.js"></script>
<!-- 引入easyui的中文语言包，如果不导入，自动引入所有的语言包 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/Inc/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<!-- easyui tree的扩展 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/Inc/js/extEasyUI.js"></script>

<!-- Highcharts图表专用 -->
<script src="${pageContext.request.contextPath}/Inc/js/Highcharts/highcharts.js" type="text/javascript"></script>  
<!-- Highcharts 3D -->
<script src="${pageContext.request.contextPath}/Inc/js/Highcharts/highcharts-3d.js" type="text/javascript"></script>  
<!-- 添加主题样式js文件 -->
<script src="${pageContext.request.contextPath}/Inc/js/Highcharts/themes/grid.js" type="text/javascript"></script>  
<!--添加导出模式 -->  
<script src="${pageContext.request.contextPath}/Inc/js/Highcharts/modules/exporting.js" type="text/javascript"></script>
<!-- 自定义的JS扩展 -->
<script src="${pageContext.request.contextPath}/Inc/js/sysUtil.js" type="text/javascript"></script>