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
    		//填充车辆下拉列表
			var url='ViewCostDistributionController/findCarList.do';
			var carKV={k:'id',v:'carNo'};
			initCombobox($('#car'),url,carKV,-1);
    	
    		$('#car').combobox({
	    		onSelect:function(record){//在用户选择的时候触发
	    			var params={};
		    		params['cid']=record.id;
		    		
		    		$.ajax({
		    			url:'ViewCostDistributionController/findAll.do',
		    			data:params, 
		    			dataType:'json',
						type:'POST',
						success:function(data){
							var t=[];
							//var data=[['a',22],['b',34],['c',33]];
							//遍历
							$.each(data,function(k,v){
								//往数组中添加数据，是一个二维数组
								t.push([v.text,v.carCost]);
							});
							createReport(t,'pie');
						}
		    		});
	    		}
	    	});
    	
    		
    	});
    	
    	
	    //生成饼图
	   	var chart1;
	   	function createReport(data,cType){
	   		chart1=new Highcharts.Chart({
			 	chart:{
			 		renderTo:'container1',//图表渲染容器的 HTML 元素的 id 或对象引用
			 	},
			 	credits:{enabled:false},//在图表的右下角是否显示版权信息
               	tooltip:{  
                   formatter: function () {  
                       //当鼠标悬置数据点时的格式化提示  
                       return '<b>'+this.point.name+'</b>'+
                       	   ':'+
                       	   Highcharts.numberFormat(this.y,2)+
                       	   '元';
                       	   /*斜体
                       	   '<i>'+this.point.name+'</i>'+
                       	   ':'+
                       	   Highcharts.numberFormat(this.y,2)+
                       	   '元'
                       	   */  
                   }  
               	},
               	plotOptions:{//数据列配置
               		pie:{//pie:饼图
	               		allowPointSelect:true,//是否允许选中点
	                	cursor:'pointer',//光标形状
	                	dataLabels:{//显示百分比的数据标签
	                		enabled:true,
	                		color:'#F00000',
	                		formatter:function(){
	                			return '<b>'+this.point.name+'</b>'+
	                        	   ':'+
	                        	   Highcharts.numberFormat(this.percentage,2)+
	                        	   '%'; 
	                		}
	                	}
               	}
               },
               title: { text: '车辆费用分布' }, //图表主标题  
               subtitle: { text: '单个车辆' }, //图表副标题  
               series:[{//通用数据列
               	type:cType,
               	data:data
               }]
			 }); 
	   	}
    </script>
	
  </head>
  
  <body>
  	<input type="text" id="car" />
    <form id="form1">  
		<div>  
			<div id="container1" style="width: 1000px; height: 680px;  margin: 0 auto">  
	        </div>  
		</div>  
    </form>
  </body>
</html>
