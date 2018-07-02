<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    	/* 图片展示样式 */
    	#carstate{width: 99%;}
		#carstate div{width:250px;text-align:center;}
		#carstate div img{width:150px;height:150px;cursor: pointer;}
		#carstate div img:hover{opacity:0.7;filter(alpha=80);}
    </style>
 	<script type="text/javascript">
 		$(function(){
 			$.ajax({
 				url:'CarController/findCarList.do',
 				dataType:'json',
				type:'POST',
				success:function(data){				
					//产生状态表格
					createTab(data);		
				}
 			});	
 		});	
    	//生成动态表格
    	function createTab(data){
    		var strTab='';    		
    		if(data !=null){ 
    			var index=0;//下标   			
    			var cell=4;//列数
    			var oCar;	
    			var carPic;
    			var carStateText='未知';
    			var count=data.length;//总数量		
				var lastTdLen=count%cell;//最后一行的列的数量			
				var rowLen=parseInt(lastTdLen==0?count/cell:count/cell+1);//总行数
    			    		
    			strTab+='<table>';
    			
    			//循环行		
				for(var i=0;i<rowLen;i++){
					strTab+='<tr>';
					//循环列
					for(var j=0;j<cell;j++){
						strTab+='<td>';
						//下标从0开始
						if(index<=count-1){
							oCar=data[index];//第几个
							strTab+='<div>';
							
							//隐藏的图片信息ID
							strTab+='<input type="hidden" id="carId" value="'+oCar.id+'"/>';						
							
							//拼装图片容器,车辆状态的文字和图片均来自sysUtil.js中的方法
							strTab+='<img name="csimg" src="<%=basePath%>Inc/img/'+carStatePic(oCar.carState)+'" />';
							strTab+='<br/><span>'+oCar.carNo+'【'+findLocDicText('state',oCar.carState)+'】'+'</span>';
							
							strTab+='</div>';
							index++;
						}
						
						strTab+='</td>';
					}
					
					strTab+='</tr>';
				}
    			
    			strTab+='</table>';
    		}
    		
    		$('#carstate').html(strTab);
    	}
 	
 	
 	</script>
 	
  </head>
  
  <body>
   	<div id="carstate"></div>
  </body>
</html>
