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
    		$.ajax({
    			url:'ViewCostContrastControllerk/findAll.do', 
    			dataType:'json',
				type:'POST',
				success:function(data){
					var chartType='column';
					//数据绑定
					createReport1(data[0],chartType);
				}
    		});
    	});
	 	
	 	//生成柱状图
    	var chart1;
    	function createReport1(data,cType){
    	    	
    		chart1=new Highcharts.Chart({
			 	chart:{
			 		renderTo:'container1',
			 		defaultSeriesType: cType, //图表类型 line, spline, area, areaspline, column, bar, pie , column，scatter
			 		inverted: false //左右显示，默认上下正向。假如设置为true，则横纵坐标调换位置
			 	},
			 	//x轴，默认情况下，显示在图表的底部
			 	xAxis:{  
                    categories:data.x, //X轴的坐标值  
                    title:{ text: '车牌号码'}  //Y轴坐标标题  labels:纵柱标尺  
                }, 
                //y轴,默认情况下，显示在图表的左边
                yAxis:{  
                    title:{ text: '总消费金额(元)' },  //Y轴坐标标题  labels:纵柱标尺  
                    min: 0  
                }, 
                tooltip:{  
                    formatter: function () {  
                        //当鼠标悬置数据点时的格式化提示  
                        return '总金额:'+'<span color="red">'+ Highcharts.numberFormat(this.y, 2)+'<span/>' + '元<br/>当前车牌:' + this.x;  
                    }  
                },
                credits:{  //显示版权
                    /* enabled: true,//是否启用
                    text:'哈哈哈哈哈哈'  */
                    enabled:false 
                }, 
                plotOptions:{ 
                   column:{  //柱状图样式
                        pointPadding: 0.25,  //图表柱形的  
                        borderWidth: 0      //图表柱形的粗细  
                    } 
                },
                title: { text: '车辆费用对比' }, //图表主标题  
                subtitle: { text: '所有车辆' }, //图表副标题  
                series:[{
                	name:data.name,//数据列名称
                	data:data.y //显示在图表中的数据列
                }]
                /* series: [{ 
                	name: '美国人数', 
                	data:data
                },{ 
                	name: '中国人数', 
                	data:[50, 20, 30, 78, 32, 30, 50]
                },{ 
                	name: '英国人数', 
                	data:[25, 30, 50, 98, 52, 40, 10]
                }] */                  
			 }); 
    	}
	 </script>
  </head>
  <body>
    <form id="form1">  
		<div>  
			<div id="container1" style="width: 1000px; height: 700px; margin: 0 auto">  
	        </div>  
		</div>  
    </form>
  </body>
</html>
