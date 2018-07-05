package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.InspectionRec;
import com.cc.po.TempJSON;
import com.cc.service.ICarService;
import com.cc.service.ICurrentUnitService;
import com.cc.service.IDriverService;
import com.cc.service.IInspectionRecService;
import com.cc.util.JsonUtil;
import com.cc.util.SysStr;


//车辆信息控制器
@Controller
@RequestMapping("InspectionController")
public class InspectionController {
	@Autowired
	private IInspectionRecService iinspectionrecs;
	@Autowired
	private IDriverService idris;
	@Autowired
	private ICurrentUnitService icus;
	@Autowired
	private ICarService icars;

	
	
	//查询车辆下拉列表
	@RequestMapping(value="/findCarList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarList(){
		return JsonUtil.writeJson(icars.findListCar(-1, 1), new String[]{"dic","carBrand","carModel","dept","sup"});		
	}
	
	//查询驾驶员信息
	@RequestMapping(value="/findDriverList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDriverList(){
		return JsonUtil.writeJson(idris.findDriverList(-1),new String[]{"dic","dept","driverType"});
	}
	
	//查询车管所
	@RequestMapping(value="/findVAOList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findVAOList(){
		return JsonUtil.writeJson(icus.findCUListByType("车管所"),new String[]{"unitType"});
	}
	
	//查询数据
	@RequestMapping(value="/findIRDG",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findIRDG(InspectionRec ir){
		return JsonUtil.writeJson(iinspectionrecs.findIRDG(ir),new String[]{"dic"});
	}
	
	//按照ID查询数据
	@RequestMapping(value="/findIRById",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findIRById(InspectionRec ir){
		return JsonUtil.writeJson(iinspectionrecs.findIRById(ir.getId()));
	}
	
	//新增
	@RequestMapping(value="/add",produces="text/html;charset=utf-8")
	@ResponseBody
	public String add(InspectionRec ir){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.addfail);
				
		if(iinspectionrecs.addIR(ir)){
			ts.setMsg(SysStr.addsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//修改
	@RequestMapping(value="/upd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String upd(InspectionRec ir){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.editfail);
				
		if(iinspectionrecs.updIR(ir)){
			ts.setMsg(SysStr.editsuccess);
			ts.setSuccess(true);
		}
		
		return JsonUtil.writeJson(ts);
	}
	
	//删除
	@RequestMapping(value="/del",produces="text/html;charset=utf-8")
	@ResponseBody
	public String del(InspectionRec ir){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.delfail);
				
		if(iinspectionrecs.delIR(ir.getId())){
			ts.setMsg(SysStr.delsuccess);
			ts.setSuccess(true);
		}
		
		return JsonUtil.writeJson(ts);
	}
}
