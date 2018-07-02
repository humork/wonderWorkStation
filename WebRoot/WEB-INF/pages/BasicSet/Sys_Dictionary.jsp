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
			//大分类列表选择事件,注意：此事件要先于select方法执行.
			$('#dic_select1').combobox({
				onSelect:function(record){
					if(record.id !=-1){
						//清空已选小分类
						$('#dic_select3').combobox('clear');						
			        	//清空小分类列表
						$('#dic_select3').combobox('loadData',[]);
						//填充中分类
						initComboboxByPid($('#dic_select2'),record.id);					
						//填充DG
						initDG($('#dic_dg'),record.id);
					}
				},
				onLoadSuccess:function(){//onloadSuccess:在加载数据成功的时候触发
					var data =$(this).combobox('getData');
					if(data.length>0){
						$(this).combobox('select',data[0].id);
					}
				}
			});
			
			
			//中分类列表选择事件设置开始
			$('#dic_select2').combobox({
				onSelect:function(record){//onSelect:在用户选择列表项的时候触发。
					//填充小分类
					initComboboxByPid($('#dic_select3'),record.id);					
					//填充数据表格
					initDG($('#dic_dg'),record.id);
				},
				onLoadSuccess:function(){
					var data =$(this).combobox('getData');//getdata:返回加载数据。
					if(data.length>0){
						//$(this).combobox('select',data[0].id);
					}
				}
			});
			
			//小分类列表选择事件设置开始
			$('#dic_select3').combobox({
				onSelect:function(record){
					//填充DG
					initDG($('#dic_dg'),record.id);
				},
				onLoadSuccess:function(){
					var data =$(this).combobox('getData');
					if(data.length>0){
						//$(this).combobox('select',data[0].id);
					}
				}
			});
			
			//初始化数据表格开始  
	    	$('#dic_dg').datagrid({
	            fitColumns:true,
	            pagination:true,
	            rownumbers:true,
	            toolbar: '#dic_tb',
	            columns:[[
					{field:'id',title:'编号',checkbox:true},
	                {field:'dic',title:'父类名称',formatter:function(v,r,i){
							if(v==null){
								return '一级节点';
							}else{
								return v.text;
							}
					}},
	                {field:'text',title:'文本'},
	                {field:'isdisable',title:'是否启用',formatter:function(v,r,i){
							if(v==1){
								return '启用';
							}else{
								return '禁用';
							}
					}}
	            ]]
	        }); 
	        
	       
	        /*填充dg开始***********************************************************/
	        function initDG($dataGridObj,id){
	        	//清空一级数据
        		$dataGridObj.datagrid('loadData',{total:0,rows:[]});
        		
				//取得一级dg设置对象
				var opt=$dataGridObj.datagrid('options');
				opt.url='DictionaryController/findDicDG.do';	
																				
				//带条件查询dg的数据
				$dataGridObj.datagrid('load',{pid:id});
	        }
	            
	        /*PID填充下拉列表开始***********************************************************/
	        function initComboboxByPid($comboboxObj,pid){
	        	//清空已选
				$comboboxObj.combobox('clear');
				
	        	//清空列表
				$comboboxObj.combobox('loadData',[]);
				
				//查询数据
				$comboboxObj.combobox({    
				    url:'DictionaryController/findDicAll.do?pid='+pid,    
				    valueField:'id',    
				    textField:'text'   
				});
	        }
	        /*PID填充下拉列表结束***********************************************************/
	        
	        /*LEV填充下拉列表开始***********************************************************/
	        function initComboboxByLev($comboboxObj,lev,ckId){
	        	//清空已选
				$comboboxObj.combobox('clear');
				
	        	//清空列表
				$comboboxObj.combobox('loadData',[]);
				
				//绑定数据
        		$comboboxObj.combobox({
        			url:'DictionaryController/findDicByLev.do?lev='+lev,    
				    valueField:'id',    
				    textField:'text',
				    onLoadSuccess:function(){
						var data =$(this).combobox('getData');
						if(data.length>0){
							//如果传入默认选中的ID不为-1则选中已选中的，否则选中第一个
							if(ckId ==-1){
								$(this).combobox('select',data[0].id);
							}else{
								$(this).combobox('select',ckId);
							}
						}
					} 
        		});
	        }
	        /*LEV填充下拉列表结束***********************************************************/
	        
	        /*新增窗口打开开始***********************************************************/
	        $('#tb_add').on('click',function(){
	        	$('#dic_addDialog').dialog('open');
	        });
	        /*新增窗口打开结束***********************************************************/
	       	        
	        /*新增窗口级别点击设置，开始***********************************************************/
	        $('#dic_addForm :radio[name=lev]').on('click',function(){
	        	//获取级别的值
	        	var lev=this.value;
	        	
	        	//清空旧数据，设置最上层选项
	        	$('#dic_select4').combobox('loadData',[]);
	        	$('#dic_select4').combobox('setValue','无');
	        	
	        	//如果选择1级，禁用下拉列表，因为一级代表根节点
	        	if(lev!=1){
	        		//取得所选级别的上级内容
	        		lev-=1;
	        		//绑定数据并选中第一个     		
	        		initComboboxByLev($('#dic_select4'),lev,-1);
	        	}
	        });
	        /*新增窗口级别点击设置，结束***********************************************************/
	        
	        /*修改窗口打开开始***********************************************************/
	        $('#tb_edit').on('click',function(){
	        	//未选中提示
    			var rows=$('#dic_dg').datagrid('getChecked');
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
				
				//取待修改的数据
				$.ajax({
					url:'DictionaryController/findUpdDic.do',
					data:params,
					dataType:'json',
					type:'POST',
					success:function(data){
						//绑定待修改数据
						updDataBind(data);
					}
				});	
	        });
	        /*修改窗口打开结束***********************************************************/
	        
	        //修改窗口级别点击设置
	        $('#dic_updForm :radio[name=lev]').on('click',function(){
	        	//获取级别的值
	        	var lev=this.value;
	        	
	        	//清空旧数据，设置最上层选项
	        	$('#dic_select5').combobox('loadData',[]);
	        	$('#dic_select5').combobox('setValue','无');
	        	
	        	//如果选择1级，禁用下拉列表，因为一级代表根节点
	        	if(lev !=1){
	        		//取得所选级别的上级内容
	        		lev-=1;	
	        		//绑定数据        		
	        		initComboboxByLev($('#dic_select5'),lev,-1);
	        	}
	        });
	       
	        /*待修改内容绑定，开始***********************************************************/
	        function updDataBind(data){
	        	//绑定主键
	        	$('#updId').val(data.id);
	        	
	        	//绑定级别
	        	$('#dic_updForm :radio[name=lev]').each(function(){
	        		if($(this).val()==data.lev){
	        			$(this).prop('checked',true);
	        			
	        			//当前级别的上一级
	        			var lev=data.lev-1;
	        			
	        			//绑定对应上级菜单数据 ,并选中      		
	        			initComboboxByLev($('#dic_select5'),lev,data.dic.id);	        			
	        		}
	        	});
	        	
	        	//绑定是否启用
	        	$('#dic_updForm :radio[name=isdisable]').each(function(){
	        		if($(this).val()==data.isdisable){
	        			$(this).prop('checked',true);
	        		}
	        	});	        	
	        	//绑定文本
	        	$('#dic_updForm :input[name=text]').val(data.text);
	        	//打开修改窗口
	        	$('#dic_updDialog').dialog('open');
	        }
	        /*待修改内容绑定，结束***********************************************************/
		});	
		
	</script>
  </head>
  
  <body>
    <!-- 数据展示窗体创建位置 --> 
  	<table id="dic_dg"></table>
  	
  	<!-- 数据工具栏创建位置 -->   
	<div id="dic_tb">
		<a class="easyui-linkbutton" id="tb_add" data-options="iconCls:'icon-add',plain:true" style="float:left;">新增</a>
		<div class="datagrid-btn-separator"></div>		
		<a class="easyui-linkbutton" id="tb_edit" data-options="iconCls:'icon-edit',plain:true" style="float:left;">编辑</a>
		<div class="datagrid-btn-separator"></div>
		
		<!-- 下拉框1,初始显示大分类 -->
		&nbsp;&nbsp;&nbsp;大分类：<input id="dic_select1" 
			class="easyui-combobox"
			name="pid"   
    		data-options="valueField:'id',textField:'text',
    		url:'DictionaryController/findDicAll.do'" />
    	
    	<!-- 下拉框2,初始显示中分类 -->
    	&nbsp;&nbsp;&nbsp;中分类：<input id="dic_select2" name="pid"/>
    	
    	<!-- 下拉框3,初始显示小分类 -->
    	&nbsp;&nbsp;&nbsp;小分类：<input id="dic_select3" name="pid"/>
    	
	</div>
	
	<!-- 新增窗体创建位置 -->
	<div id="dic_addDialog" class="easyui-dialog" title="新增字典信息" 
		style="width:300px;height:250px;align:center;"   
        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler: function(){
				$('#dic_addForm').form('submit',{
					url:'DictionaryController/addDic.do',
					success:function(data){
						var obj=$.parseJSON(data);
						if(obj.success){
							//成功后清空表单数据
							$('#dic_addForm').form('clear');
							//关闭表单
							$('#dic_addDialog').dialog('close');
						}
						$.messager.show({
							title:'提示',
							msg:obj.msg
						});
					}
				});
			}
		},{
			text:'取消',
			iconCls: 'icon-cancel',
			handler: function(){
				$('#dic_addDialog').dialog('close');
			}
		}]">
		<form id="dic_addForm" method="post">
			<table>
				<tr>
					<td style="text-align: rigth;">选择级别：</td>
					<td>
						<input type="radio" name="lev" value="1" checked="checked"/>1级
						<input type="radio" name="lev" value="2"/>2级
						<input type="radio" name="lev" value="3"/>3级
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">上级菜单：</td>
					<td>
						<select id="dic_select4" name="dic.id" class="easyui-combobox" style="width:143px;">
							<option value="-1">无</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">是否启用：</td>
					<td>
						<input type="radio" name="isdisable" value="1" checked="checked"/>启用
						<input type="radio" name="isdisable" value="0"/>禁用
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">字典文本：</td>
					<td>
						<input type="text" name="text" maxlength="10"/>
					</td>
				</tr>
			</table>
			
		</form>
	</div> 
	
	<!-- 修改窗体创建位置 -->
	<div id="dic_updDialog" class="easyui-dialog" title="修改字典信息" 
		style="width:300px;height:250px;align:center;"   
        data-options="iconCls:'icon-edit',resizable:false,modal:true,closed:true,
        buttons:[{
        	text:'保存',
			iconCls: 'icon-ok',
			handler: function(){
				$('#dic_updForm').form('submit',{
					url:'DictionaryController/updDic.do',
					success:function(data){
						var obj=$.parseJSON(data);
						if(obj.success){
							//去除选择
							$('#dic_dg').datagrid('unselectAll');
							//成功后清空表单数据
							$('#dic_updForm').form('clear');
							//刷新dg
							$('#dic_dg').datagrid('reload');
							//关闭表单
							$('#dic_updDialog').dialog('close');
						}
						$.messager.show({
							title:'提示',
							msg:obj.msg
						});
					}
				});
			}
		},{
			text:'取消',
			iconCls: 'icon-cancel',
			handler: function(){
				//去除选择
				$('#dic_dg').datagrid('unselectAll');
				$('#dic_updDialog').dialog('close');
			}
		}]">
		<form id="dic_updForm" method="post">
			<table>
				<tr>
					<td><input type="hidden" id="updId" name="id"/></td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: rigth;">选择级别：</td>
					<td>
						<input type="radio" name="lev" value="1" checked="checked"/>1级
						<input type="radio" name="lev" value="2"/>2级
						<input type="radio" name="lev" value="3"/>3级
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">上级菜单：</td>
					<td>
						<select id="dic_select5" name="dic.id" class="easyui-combobox" style="width:143px;">
							<option value="-1">无</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">是否启用：</td>
					<td>
						<input type="radio" name="isdisable" value="1" checked="checked"/>启用
						<input type="radio" name="isdisable" value="0"/>禁用
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">字典文本：</td>
					<td>
						<input type="text" name="text" maxlength="10"/>
					</td>
				</tr>
			</table>
			
		</form>
	</div> 	
  </body>
</html>
