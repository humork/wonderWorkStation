package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.FeesManager;
import com.cc.po.TempJSON;
import com.cc.service.ICarService;
import com.cc.service.ICurrentUnitService;
import com.cc.service.IDriverService;
import com.cc.service.IFeesManagerService;
import com.cc.util.JsonUtil;
import com.cc.util.SysStr;

@Controller
@RequestMapping("/FeesManagerController")
public class FeesManagerController {
	@Autowired
	private IFeesManagerService ifs;
	@Autowired
	private ICarService ic;
	@Autowired
	private ICurrentUnitService icu;
	@Autowired
	private IDriverService id;
	
	//新增规费记录
	@RequestMapping(value="/add",produces="text/html;charset=utf-8")
	@ResponseBody
	public String add(FeesManager fs){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.addfail);
		
		if(ifs.addFees(fs)){
			ts.setMsg(SysStr.addsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	//修改规费记录
	@RequestMapping(value="/upd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String upd(FeesManager fs){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.uploadfail);
		
		if(ifs.updFees(fs)){
			ts.setSuccess(true);
			ts.setMsg(SysStr.uploadsuccess);
		}
		
		return JsonUtil.writeJson(ts);
	}	
	
	//删除规费记录
	@RequestMapping(value="/del",produces="text/html;charset=utf-8")
	@ResponseBody
	public String del(FeesManager fs){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.delfail);
		
		if(ifs.delFees(fs.getId())){
			ts.setSuccess(true);
			ts.setMsg(SysStr.delsuccess);
		}
		
		return JsonUtil.writeJson(ts);
	}
	//查询车辆下拉列表,查询所有启用的车辆
	@RequestMapping(value="/findCarList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarList(){
		return JsonUtil.writeJson(ic.findListCar(-1, 1), new String[]{"dic","carBrand","carModel","dept","sup"});		
	}
	
	//查询驾驶员信息,查询所有驾驶员
	@RequestMapping(value="/findDriverList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDriverList(){
		return JsonUtil.writeJson(id.findDriverList(-1),new String[]{"dic","dept","driverType"});
	}
	
	//查询收费单位
	@RequestMapping(value="/findFeesUnitList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findFeesUnitList(){
		return JsonUtil.writeJson(icu.findCUListByType("其它"),new String[]{"unitType"});
	}
	
	//查询规费信息.查询所有
	@RequestMapping(value="/findFeesDG",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findFeesDG(FeesManager fs){
		return JsonUtil.writeJson(ifs.findFeesDG(fs), new String[]{"dic","carBrand","carModel","dept","sup","unitType","driverType"});
	}
	
	//按照ID查询资料
	@RequestMapping(value="/findFeesById",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findFeesById(FeesManager fs){
		return JsonUtil.writeJson(ifs.findFeesById(fs.getId()), new String[]{"dic","carBrand","carModel","dept","sup","unitType","driverType"});
	}
	
}
