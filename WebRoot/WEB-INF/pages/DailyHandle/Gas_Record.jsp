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
   <script type="text/javascript">
   		$(function(){
   			init();
   			aninit();
   			reafg();
   		});
   		
   		function init(){
   			$('#rr_dg').datagrid({
	            url:'RefuelRce/findRRDG.do',
	            fitColumns:true,
	            pagination:true,
	            rownumbers:true,
	            toolbar: '#rr_tb',
	            columns:[[
					{field:'id',title:'编号',checkbox:true},
	                {field:'car',title:'车牌号码',formatter:function(v,r,i){
	                	return v.carNo;
	                }},
	                {field:'oilSta',title:'油气站',formatter:function(v,r,i){
	                	return v.unitName;
	                }},
	                {field:'oilDate',title:'加油时间',formatter: function(v,r,i){
						return formatTime(v);
					}},
	                {field:'oilLabel',title:'油料标号',formatter:function(v,r,i){
	                	return v.text;
	                }},
	                {field:'unitPrice',title:'单价（每升）'},
	                {field:'this_volu',title:'本次加油量（升）'},
	                {field:'amount',title:'本次加油总价'},
	                {field:'this_mil',title:'本次加油里程（公里）'},
	                {field:'last_mil',title:'上次加油里程（公里）'},
	                {field:'last_volu',title:'上次加油量（升）'},
	                {field:'xxxx',title:'上次油耗（升/每百公里）',formatter:function(v,r,i){
	                	//油耗=(加油量/行驶里程)*100
	                	var nOV=r.last_volu/(r.this_mil-r.last_mil)*100;
	                	//四舍五入
	                	return nOV.toFixed(2);
	                }},
					{field:'operator',title:'加油人员',formatter:function(v,r,i){
						return v.name;						
					}},
					{field:'remarks',title:'备注'}
	            ]]
	        });
   		}
   		//按钮单击事件
   		function aninit(){
   		
	   		//新增加油费用自动计算事件设置
			$('#rr_addForm #unitPrice,#rr_addForm #this_volu').on('blur',function(){
				autoOilAmount($('#rr_addForm #unitPrice'),$('#rr_addForm #this_volu'),$('#rr_addForm #amount'));
			});	

   			//新增按钮单击事件
   			$('#tb_add').on('click',function(){
   				//填充车辆下拉列表
				var url='RefuelRce/findCarList.do';
				var carKV={k:'id',v:'carNo'};
				initCombobox($('#rr_addForm #car'),url,carKV,-1);
				
				//填充油气站
				var url='RefuelRce/findOilStaList.do ';
				var kv={k:'id',v:'unitName'};
				initCombobox($('#rr_addForm #oilSta'),url,kv,-1);
				
				//填充油料标号下拉列表
				var url='RefuelRce/findOilLabelList.do ';
				initCombobox($('#rr_addForm #oilLabel'),url,null,-1);
			
				//加油时间默认当前时间
				$('#rr_addForm #oilDate').datetimebox('setValue',getNowTime());
				
				//填充加油人员（驾驶员）下拉列表
				var url='RefuelRce/findDriverList.do';
				var driverKV={k:'id',v:'name'};
				initCombobox($('#rr_addForm #operator'),url,driverKV,-1);
				//打开新增窗体
				$('#rr_addDialog').dialog('open');		
   			});
   			
   			//选择车牌查询该车牌上次加油记录
	    	$('#rr_addForm #car').combobox({
	    		onSelect:function(record){
	    			var params={};
		    		params['car.id']=record.id;
		    		//取得数据
					$.ajax({
						url:'RefuelRce/findLastRR.do',
						data:params,
						dataType:'json',
						type:'POST',
						success:function(data){						
							//上次加油里程为查询到的本车牌最近一次加油记录的【本次加油里程】
							$('#rr_addForm #last_mil').numberbox('setValue',data.this_mil);
							//上次加油量为查询到的本车牌最近一次加油记录的【本次加油量】
							$('#rr_addForm #last_volu').numberbox('setValue',data.this_volu);	
							//上次单价
							$('#rr_addForm #unitPrice').numberbox('setValue',data.unitPrice);
							//上次加油时间
							$('#rr_addForm #old_oilDate').val(data.oilDate);
							
							//上次油料标号下拉列表
							var url='RefuelRce/findOilLabelList.do';
							initCombobox($('#rr_addForm #oilLabel'),url,null,data.oilLabel.id);												
						}
					});
	    		}
	    	});
	    	
	    	//修改按钮的单击事件
	    	$('#tb_edit').on('click',function(){
    		//未选中提示
	   			var rows=$('#rr_dg').datagrid('getChecked');
	   			if(rows.length==0){
					$.messager.show({
						title : '提示',
						msg : '请勾选要修改的记录！'
					});
					return;
				}
				//选中多笔提示
				if(rows.length>1){
					$.messager.show({
						title : '提示',
						msg : '修改时只能选择一条记录！'
					});
					return;
				}			
				//主键ID
				var id=rows[0].id;
				var params={"id":id};
				
				//取得数据
				$.ajax({
					url:'RefuelRce/findRRById.do',
					data:params,
					dataType:'json',
					type:'POST',
					success:function(data){
						//绑定数据
						updDataBind(data);			
					}
				});
    		});
    		
    		//修改加油费用自动计算事件设置
			$('#rr_updForm #unitPrice,#rr_updForm #this_volu').on('blur',function(){
				autoOilAmount($('#rr_updForm #unitPrice'),$('#rr_updForm #this_volu'),$('#rr_updForm #amount'));
			});
				
		//删除按钮单击事件
		$('#tb_remove').on('click',function(){
			//未选中提示
   			var rows=$('#rr_dg').datagrid('getChecked');
   			if(rows.length==0){
				$.messager.show({
					title : '提示',
					msg : '请勾选要删除的记录！'
				});
				return;
			}
			//选中多笔提示
			if(rows.length>1){
				$.messager.show({
					title : '提示',
					msg : '删除时只能选择一条记录！'
				});
				return;
			}
			//主键ID
			var id=rows[0].id;
			var params={"id":id};
			
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
				if(r){
					//取得数据
					$.ajax({
						url:'RefuelRce/del.do',
						data:params,
						dataType:'json',
						type:'POST',
						success:function(data){
							if(data.success){
								//取消数据表格选择
								$('#rr_dg').datagrid('unselectAll');
								//刷新数据表格
								$('#rr_dg').datagrid('reload');
							}
							$.messager.show({
								title:'提示消息',
								msg:data.msg
							});								
						}
					});
				}else{
					$('#rr_dg').datagrid('unselectAll');
				}
			});
			
		});			
   	}
   	//搜索框
   	function reafg(){
   		$('#sel').searchbox({
			searcher:function(v,n){
				var params={};
				
				params['car.carNo']=v;
				
				$('#rr_dg').datagrid('load',params);
			},
			menu:'#mm',
   			prompt:'此处输入车牌号码，支持模糊查询' 
		});
   	}
   	
   	
   		//自动计算加油费用
   		function autoOilAmount(objUP,objTV,objAM){
	    	/*参数1：单价输入框对象 
	    	      参数2：加油量输入框对象
	    	      参数3：加油费用接收框对象    
	        */
	    	var v1=objUP.numberbox('getValue');
	    	var v2=objTV.numberbox('getValue');
	    	objAM.numberbox('setValue',(v1*v2));
   		}
   		
   	//绑定修改数据
   function updDataBind(data){
		//填充车辆下拉列表
		var url='RefuelRce/findCarList.do';
		var carKV={k:'id',v:'carNo'};
		initCombobox($('#rr_updForm #car'),url,carKV,data.car.id);
		
		//填充油气站
		var url='RefuelRce/findOilStaList.do';
		var kv={k:'id',v:'unitName'};
		initCombobox($('#rr_updForm #oilSta'),url,kv,data.oilSta.id);
		
		//填充油料标号下拉列表
		var url='RefuelRce/findOilLabelList.do';
		initCombobox($('#rr_updForm #oilLabel'),url,null,data.oilLabel.id);
			
		//加油时间
		$('#rr_updForm #oilDate').datetimebox('setValue',formatTime(data.oilDate));
		
		//填充加油人员（驾驶员）下拉列表
		var url='RefuelRce/findDriverList.do';
		var driverKV={k:'id',v:'name'};
		initCombobox($('#rr_updForm #operator'),url,driverKV,data.operator.id);
		
		$('#rr_updForm #id').val(data.id);
		$('#rr_updForm #oilDate').datebox('setValue',formatTime(data.oilDate));
		$('#rr_updForm #unitPrice').numberbox('setValue',data.unitPrice);
		$('#rr_updForm #this_volu').numberbox('setValue',data.this_volu);
		$('#rr_updForm #amount').numberbox('setValue',data.amount);
		$('#rr_updForm #this_mil').numberbox('setValue',data.this_mil);
		$('#rr_updForm #last_mil').numberbox('setValue',data.last_mil);
		$('#rr_updForm #last_volu').numberbox('setValue',data.last_volu);
		$('#rr_updForm #remarks').val(data.remarks);	
		
		
		//打开修改窗体
		$('#rr_updDialog').dialog('open');		
	} 
  </script>
  </head>
  
  <body>
   	<!-- 数据展示窗体创建位置 --> 
  	<table id="rr_dg"></table>
  	  	<!-- 数据工具栏创建位置 -->   
	<div id="rr_tb">
		<a class="easyui-linkbutton" id="tb_add" data-options="iconCls:'icon-add',plain:true" style="float:left;">加油信息登记</a>
		<div class="datagrid-btn-separator"></div>		
		<a class="easyui-linkbutton" id="tb_edit" data-options="iconCls:'icon-edit',plain:true" style="float:left;">加油信息编辑</a>
		<div class="datagrid-btn-separator"></div>
		<a class="easyui-linkbutton" id="tb_remove" data-options="iconCls:'icon-remove',plain:true" style="float:left;">加油信息删除</a>
		<div class="datagrid-btn-separator"></div>
    	<!-- 搜索框div创建位置 --> 
    	&nbsp;
		<input style="width:350px" id="sel"></input> 
		<div id="mm" style="width:120px">
			<div data-options="name:'name'">车牌号码</div>
		</div>
	</div>
	<!-- 新增窗体创建位置 -->
	<div id="rr_addDialog" class="easyui-dialog" title="加油信息登记" 
		style="width:400px;height:450px;align:center;"   
        data-options="iconCls:'icon-add',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler: function(){
				$('#rr_addForm').form('submit',{
					url:'RefuelRce/add.do',
					success:function(data){
						var obj=$.parseJSON(data);
						if(obj.success){
							//新增成功刷新数据表格
							$('#rr_dg').datagrid('load');
						
							//成功后清空表单数据
							$('#rr_addForm').form('clear');
							//关闭表单
							$('#rr_addDialog').dialog('close');
						}
						$.messager.show({
							title:'提示',
							msg:obj.msg
						});
					},
					onSubmit:function(){
						var bTime=$('#rr_addForm #old_oilDate').val();
						var eTime=$('#rr_addForm #oilDate').datetimebox('getValue');
						
						if(bTime !='' && !compareTime(bTime,eTime)){
							$.messager.alert('提示','加油时间必须大于上次加油时间【'+bTime+'】','warning');
							return false;
						}
						
						//本次里程
						var this_mil=parseInt($('#rr_addForm #this_mil').numberbox('getValue'));
						//上次里程
						var last_mil=parseInt($('#rr_addForm #last_mil').numberbox('getValue'));
												
						if(this_mil<=last_mil){
							$.messager.alert('提示','本次里程必须大于上次里程','warning');
							return false;
						}
						return true;
					}
				});
			}
		},{
			text:'取消',
			iconCls: 'icon-cancel',
			handler: function(){
				//关闭新增窗体
				$('#rr_addDialog').dialog('close');
			}
		}]">
		<form id="rr_addForm" method="post">
			<table>
				<tr>
					<td style="text-align:rigth;">车牌号码：</td>
					<td>
						<input type="text" id="car" name="car.id" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">加&nbsp;油&nbsp;站：</td>
					<td>
						<input type="text" id="oilSta" name="oilSta.id" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">加油时间：</td>
					<td>
						<input type="text" id="oilDate" name="oilDate" class="easyui-datetimebox" required="required"/>
						<input type="hidden" id="old_oilDate"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">油料标号：</td>
					<td>
						<input type="text" name="oilLabel.id" id="oilLabel"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">油料单价（每升）：</td>
					<td>
						<input type="text" id="unitPrice" name="unitPrice" data-options="min:0,precision:2" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">本次加油（升）：</td>
					<td>
						<input type="text" id="this_volu" name="this_volu" data-options="min:0,precision:2" class="easyui-numberbox"/>
					</td>
				</tr>				
				<tr>
					<td style="text-align: rigth;">加油总价：</td>
					<td>
						<input type="text" readonly="readonly" id="amount" name="amount" data-options="min:0,precision:2" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">本次里程（公里）：</td>
					<td>
						<input type="text" id="this_mil" name="this_mil" data-options="min:0" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">上次里程（公里）：</td>
					<td>
						<input type="text" readonly="readonly" id="last_mil" name="last_mil" data-options="min:0" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">上次加油（升）：</td>
					<td>
						<input type="text" readonly="readonly" id="last_volu" name="last_volu" data-options="min:0,precision:2" class="easyui-numberbox"/>
					</td>
				</tr>				
				<tr>
					<td style="text-align: rigth;">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					<td>
						<input type="text" name="remarks" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">加油人员：</td>
					<td>
						<input id="operator" name="operator.id"/>
					</td>
				</tr>				
				
			</table>
			
		</form>
	</div>
	<!-- 修改窗体创建位置 -->
	<div id="rr_updDialog" class="easyui-dialog" title="加油信息修改" 
		style="width:400px;height:450px;align:center;"   
        data-options="iconCls:'icon-add',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler: function(){
				$('#rr_updForm').form('submit',{
					url:'RefuelRce/upd.do',
					success:function(data){
						var obj=$.parseJSON(data);
						if(obj.success){
							//修改成功刷新DG
							$('#rr_dg').datagrid('load');
						
							//成功后清空表单数据
							$('#rr_updForm').form('clear');
							//关闭表单
							$('#rr_updDialog').dialog('close');
						}
						$.messager.show({
							title:'提示',
							msg:obj.msg
						});
					},
					onSubmit:function(){						
						
						var this_mil=parseInt($('#rr_updForm #this_mil').numberbox('getValue'));
						var last_mil=parseInt($('#rr_updForm #last_mil').numberbox('getValue'));
												
						if(this_mil<=last_mil){
							$.messager.alert('提示','本次里程必须大于上次里程','warning');
							return false;
						}
						
						return true;
					}
				});
			}
		},{
			text:'取消',
			iconCls: 'icon-cancel',
			handler: function(){
				$('#rr_dg').datagrid('unselectAll');
				$('#rr_updDialog').dialog('close');
			}
		}]">
		<form id="rr_updForm" method="post">
			<table>
				<tr>
					<td style="text-align:rigth;">车牌号码：</td>
					<td>
						<input type="hidden" id="id" name="id" />
						<input type="text" id="car" name="car.id" disabled="disabled" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">加&nbsp;油&nbsp;站：</td>
					<td>
						<input type="text" id="oilSta" name="oilSta.id" />
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">加油时间：</td>
					<td>
						<input type="text" id="oilDate" name="oilDate" class="easyui-datetimebox" disabled="disabled" />
						<input type="hidden" id="old_oilDate"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">油料标号：</td>
					<td>
						<input type="text" name="oilLabel.id" id="oilLabel"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">油料单价（每升）：</td>
					<td>
						<input type="text" id="unitPrice" name="unitPrice" data-options="min:0,precision:2" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">本次加油（升）：</td>
					<td>
						<input type="text" id="this_volu" name="this_volu" data-options="min:0,precision:2" class="easyui-numberbox"/>
					</td>
				</tr>				
				<tr>
					<td style="text-align: rigth;">加油总价：</td>
					<td>
						<input type="text" readonly="readonly" id="amount" name="amount" data-options="min:0,precision:2" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">本次里程（公里）：</td>
					<td>
						<input type="text" id="this_mil" name="this_mil" data-options="min:0" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">上次里程（公里）：</td>
					<td>
						<input type="text" readonly="readonly" id="last_mil" name="last_mil" data-options="min:0" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">上次加油（升）：</td>
					<td>
						<input type="text" readonly="readonly" id="last_volu" name="last_volu" data-options="min:0,precision:2" class="easyui-numberbox"/>
					</td>
				</tr>				
				<tr>
					<td style="text-align: rigth;">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					<td>
						<input type="text" id="remarks" name="remarks" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">加油人员：</td>
					<td>
						<input id="operator" name="operator.id"/>
					</td>
				</tr>				
				
			</table>
			
		</form>
	</div>
	
  </body>
</html>
