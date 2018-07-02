package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.AccidentRec;
import com.cc.po.TempJSON;
import com.cc.service.IAccidentRecService;
import com.cc.service.ICarService;
import com.cc.service.IDriverService;
import com.cc.util.JsonUtil;
import com.cc.util.SysStr;

@Controller
@RequestMapping("/AccidentRecController")
public class AccidentRecController {
	@Autowired
	private ICarService icars;
	@Autowired
	private IDriverService idris;
	@Autowired
	private IAccidentRecService iaccidentrecs;	
	
	//填充车辆下拉列表 查询所有启用的车辆
	@RequestMapping(value="/findCarList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarList(){
		return JsonUtil.writeJson(icars.findListCar(-1, 1), new String[]{"dic","carBrand","carModel","dept","sup"});		
	}
	
	//填充驾驶员信息 查询所有驾驶员
	@RequestMapping(value="/findDriverList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDriverList(){
		return JsonUtil.writeJson(idris.findDriverList(-1),new String[]{"dic","dept","driverType"});
	}
	
	//查询数据,查询所有
	@RequestMapping(value="/findARDG",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findARDG(AccidentRec ar){
		return JsonUtil.writeJson(iaccidentrecs.findARDG(ar),new String[]{"dic"});
	}
		
	//按照ID查询数据
	@RequestMapping(value="/findARById",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findARById(AccidentRec ar){
		return JsonUtil.writeJson(iaccidentrecs.findARById(ar.getId()));
	}
	
	//新增
	@RequestMapping(value="/add",produces="text/html;charset=utf-8")
	@ResponseBody
	public String add(AccidentRec ar){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.addfail);
				
		if(iaccidentrecs.addAR(ar)){
			ts.setMsg(SysStr.addsuccess);
			ts.setSuccess(true);
		}
		
		return JsonUtil.writeJson(ts);
	}
		
	//修改
	@RequestMapping(value="/upd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String upd(AccidentRec ar){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.editfail);
				
		if(iaccidentrecs.updAR(ar)){
			ts.setMsg(SysStr.editsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
		
	//删除
	@RequestMapping(value="/del",produces="text/html;charset=utf-8")
	@ResponseBody
	public String del(AccidentRec ar){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.delfail);
				
		if(iaccidentrecs.delAR(ar.getId())){
			ts.setMsg(SysStr.delsuccess);
			ts.setSuccess(true);
		}
			
		return JsonUtil.writeJson(ts);
	}
}
