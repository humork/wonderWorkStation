<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="Inc.jsp" %>
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
	//tabs的对象
	var t;
	$(function(){	
		t=$('#layout_center_tabs');
		//更换皮肤.comboboc:下拉列表框
		$('#em_sel').combobox({
			//在用户选择下拉列表项时触发
			onSelect:function(record){
				//record:选择的option的对象
				//获得#em_sel对象中的value.去sysUtil.js中的changeTheme方法.
				changeTheme(record.value);
			}
		}); 
       /* $('#em_sel').combobox({
			onSelect:function(recode){
				changeTheme(recode.value);
			}
		}); */
	//获得树结构菜单
	  $('#tt').tree({
			url:'<%=basePath%>EasyUITree/loadTree.do',
			//平滑json需要的3个参数
			parentField:"pid",//父节点
			textFiled:"text",//文本
			idFiled:"id",//id
			animate:true,//定义节点在展开或折叠的时候是否显示动画效果
			//在用户点击一个节点的时候触发。node中是后台返回来的json数据!
			onClick:function(node){
				//state：节点状态,'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
				if(node.state=='open'){
					//collapse:折叠一个节点.
					$('#tt').tree('collapse',node.target);
				}else if(node.state=='closed'){
					//expand:展开一个节点。node.target目标Dom对象
					$('#tt').tree('expand',node.target);
				}else{ 
					addTab(node);
				}		
			}	
		}); 
	});
	
 function addTab(node){
		//选项卡是否打开
		if(t.tabs('exists',node.text)){
			//选中该打开的选项卡
			t.tabs('select',node.text);
		}else{
			//如果url为null，弹出一个警告
			if(node.attributes.url==null){
				$.messager.alert('警告','节点错误，请联系管理员!',error);
				return;
			}
			
			//打开进度条
			$.messager.progress({
				text:'亲：您的网速有点慢哦!!!',
				interval:100
			}); 
			//在主界面添加一个iframe
			t.tabs('add',{
				title:node.text,
				//closable:显示一个关闭按钮
				closable:true,
				content:'<iframe src="<%=basePath%>forwards/findUrl.do?url=pages/'+node.attributes.url+'" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
				//在每个添加的iframe中添加一个小刷新按钮
				tools:[{
					iconCls:'icon-mini-refresh',
					handler:function(){
						//刷新
						refreshTab(node.text);
					}
				}] 
			});
			//关闭进度条
			$.messager.progress('close');
		}
	} 
	//刷新tabs
	function refreshTab(title){
		//开进度条
		$.messager.progress({
			text:'亲：您的网速有点慢哦!!!',
			interval:100
		});
		//刷新tab选项卡
		var tabobj=t.tabs('getTab',title);
		t.tabs('update',{
			tab:tabobj,
			options:tabobj.panel('options')
		});
		//关进度条
		$.messager.progress('close');
	}
	</script>
  </head>
  
    <body class="easyui-layout">  		
	<div region="north" split="true" border="false" style="overflow: hidden; height: 90px;
	         background-color: #52a0fd;
	        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体 ">
	        
	        <span style="float:right;
	        	padding-top:60px; 
	        	padding-right:10px;
	        	font-size: 16px;" 
	        	class="head">
	        	欢迎
	        <sapn style="color:red;"> 【${sessionScope.login.rname }】</sapn>
	        	 登录本系统
	        	<a href="users/exit.do" id="loginOut" style="color:yellow;text-decoration: none;">
	        		【安全退出】
	        	</a>
	        	<!-- 通过class="easyui-combobox"设置成下拉菜单 -->
		        <select id="em_sel" class="easyui-combobox" style="width:100px;"> 
				    <option value="default" selected="selected">默认皮肤</option>   
				    <option value="black">古天乐黑</option> 
				    <option value="dark-hive">包青天黑</option>  
				    <option value="bootstrap">浅灰色</option>
				    <option value="gray">深灰色</option>
				    <option value="cupertino">青蓝色</option>
				    <option value="sunny">阳光色</option>   
				</select>		        	
	        </span>
	        
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer"></div>
    </div>   
    <!-- split:默认为false,为true时用户可以通过分割栏改变面板大小。 -->
    <div data-options="region:'west',title:'菜单栏',split:true " style="width:180px;">
    	<!-- 树菜单 -->
    	<ul id="tt"></ul>
    </div>   
    <div data-options="region:'center',title:'主界面'" style="padding:5px;background:#eee;">
    	<div id="layout_center_tabs" class="easyui-tabs" data-options="fit:true,border:false" style="overflow: hidden;">
			<div title="首页">
			</div>			
		</div>
    </div>   
</body> 
  
</html>
