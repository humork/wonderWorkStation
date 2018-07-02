/* ***********************
 * 创建人员:Roy【郝敏一】           
 * 创建时间:2016-8-19 
 * 程序功能:自定义扩展js     
 ********************** */

/*
 * 格式化时间，返回yyyy-MM-dd格式
 */
function formatDate(sDate){
	var oDate=new Date(sDate);
	var str=oDate.getFullYear()+'-'+(oDate.getMonth()+1)+'-'+oDate.getDate();
	return str;
}

/*
 * 格式化时间，返回yyyy-MM-dd hh:mm:ss格式
 */
function formatTime(sDate){
	var oDate=new Date(sDate);	
	
	var str=oDate.getFullYear()+'-'+(oDate.getMonth()+1)+'-'+oDate.getDate()+' '+oDate.getHours()+':'+oDate.getMinutes()+':'+oDate.getSeconds();
	return str;
}

/*
 * 得到当前日期yyyy-MM-dd格式
 */
function getNowDate(){
	var oDate=new Date();
	var str=oDate.getFullYear()+'-'+(oDate.getMonth()+1)+'-'+oDate.getDate();
	return str;
}

/*
 * 得到当前日期yyyy-MM-dd hh:mm:ss格式
 */
function getNowTime(){
	var oDate=new Date();
	var str=oDate.getFullYear()+'-'+(oDate.getMonth()+1)+'-'+oDate.getDate()+' '+oDate.getHours()+':'+oDate.getMinutes()+':'+oDate.getSeconds();
	return str;
}

/*
 * 对某个日期进行加月份操作并返回新的日期
 */
function addMonth(date,month){
	var oDate=new Date(date);
	oDate.setMonth(oDate.getMonth()+month);
	var str=oDate.getFullYear()+'-'+(oDate.getMonth()+1)+'-'+oDate.getDate();
	return str;
}

/*
 * 对某个日期进行加年操作并返回新的日期
 */
function addYear(date,year){
	var oDate=new Date(date);
	oDate.setFullYear(oDate.getFullYear()+year);
	var str=oDate.getFullYear()+'-'+(oDate.getMonth()+1)+'-'+oDate.getDate();
	return str;
}

/*
 * 判断时间，结束时间大于开始时间返回true
 */
function compareTime(beginTime,endTime){
	var dateBegin=new Date(beginTime);
	var dateEnd=new Date(endTime);
	return dateEnd>dateBegin;
}

/*
 * 将null值转换为提示字符
 */
function exp(v){
	if(v==null){
		return '未填写';
	}
	return v;	
}

/*
 * 图片格式验证
 */
function ckImgType(strFileName){
	var res=false;
	if(strFileName !=null && strFileName !=''){
		var reg=/\.(gif|jpg|jpeg|png)$/;
		res=reg.test(strFileName.substring(strFileName.lastIndexOf('.')).toLowerCase());
	}
	return res;
}


/*
 * 远程数据填充下拉列表
 * 参数1：jquery对象，表示待填充列表对象
 * 参数2：数据来源地址
 * 参数3：默认选中ID，-1表示选中第一个
 */
function initCombobox($comboboxObj,sUrl,oKV,ckId){
	if(oKV ==null){
		oKV={k:'id',v:'text'};
	}
	
	//绑定数据
	$comboboxObj.combobox({
		url:sUrl,    
	    valueField:oKV.k,    
	    textField:oKV.v,
	    onLoadSuccess:function(){//onloadSuccess:在加载数据完成后触发
			var data =$(this).combobox('getData');//getData:返回加载数据;
			if(data.length>0){
				//如果传入默认选中的ID不为-1则选中已选中的，否则选中第一个
				if(ckId ==-1){
					$(this).combobox('select',data[0][oKV.k]);
				}else{
					$(this).combobox('select',ckId);
				}
			}
		} 
	});
}

/*
 * 本地数据填充下拉列表
 * 参数1：jquery对象，表示待填充列表对象
 * 参数2：本地数据
 * 参数3：默认选中ID，-1表示选中第一个
 */
function initComboboxLocal($comboboxObj,objData,ckId){
	//绑定数据
	$comboboxObj.combobox({
		data:objData,    
	    valueField:'id',    
	    textField:'text',
	    onLoadSuccess:function(){//在加载数据成功后触发.
			var data =$(this).combobox('getData');//返回加载数据
			if(data.length>0){
				//如果传入默认选中的ID不为-1则选中已选中的，否则选中第一个
				if(ckId ==-1){
					$(this).combobox('select',data[0].id);
				}else{
					$(this).combobox('select',ckId);//默认选中
				}
			}
		} 
	});
}

/*
 * 参数：state,返回车辆状态
 * 参数：color，返回颜色 
 */
function initLocDic(strKey){
	var arrData;
	
	switch(strKey){
	case 'state':
		arrData=[{'id':1,'text':'可用'},{'id':2,'text':'出车'},{'id':3,'text':'维修'},
		      {'id':4,'text':'其它'}];
		break;
	case 'color':
		arrData=[{'id':1,'text':'黑色'},{'id':2,'text':'蓝色'},{'id':3,'text':'红色'},
	          {'id':4,'text':'珍珠白'},{'id':5,'text':'闷骚红'},{'id':6,'text':'军绿色'},
	          {'id':7,'text':'白色'}];
		break;
	}
	
	
	return arrData;
}

/*
 * 参数1：车辆状态
 * 返回：车辆状态对应的图片
 */
function carStatePic(carState){
	var carPic;
	if(carState==1){
		carPic='carstate_1.png';
	}else if(carState==2){
		carPic='carstate_2.png';
	}else if(carState==3){
		carPic='carstate_3.png';
	}else if(carState==4){
		carPic='carstate_4.png';
	}
	return carPic;
}

/*
 * 参数1：查询的本地数据的类型
 * 参数2：要查询的ID
 */
function findLocDicText(strKey,intId){
	var res='';
	//得到对应类型的数据
	var objData=initLocDic(strKey);
	
	if(objData !=null){
		$.each(objData,function(i,v){
			if(intId==v.id){
				res=v.text;				
			}
		});
	}
	
	return res;
}

/*
 * 更换主题 
 */
changeTheme = function(type){
	//获得inc中的id为easyuiTheme的对象
	var $easyuiTheme = $('#easyuiTheme');
	//获得该对象的href的值
	var url = $easyuiTheme.prop('href');
	//console.info(url);
	
	var href = url.substring(0, url.indexOf('easyui'))+ 'easyui/themes/' + type + '/easyui.css';
	//console.info(href);
	//为该对象的href赋予新的值
	$easyuiTheme.prop('href',href);
	
	/* 如果使用了iframe  则要添加下面这段代码、否则的话iframe中的内容不会出现样式的改变*/
	var $iframe = $('iframe');
	if($iframe.length > 0){
		for ( var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			$(ifr).contents().find('#easyuiTheme').attr('href', href);
			
		}
	}
	$.cookie('easyuiThemeName', type, {
		expires : 7//设置cookie失效时间为7天
	});
};