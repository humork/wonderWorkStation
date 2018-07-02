package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.InsuranceRec;
import com.cc.po.TempJSON;
import com.cc.service.ICarService;
import com.cc.service.ICurrentUnitService;
import com.cc.service.IDictionaryService;
import com.cc.service.IDriverService;
import com.cc.service.IInsuranceRecService;
import com.cc.util.JsonUtil;
import com.cc.util.SysStr;


//保险
@Controller
@RequestMapping("/InsuranceRecController")
public class InsuranceRecController {
	@Autowired
	private IInsuranceRecService iinsurancerecs;
	@Autowired
	private IDriverService idris;
	@Autowired
	private ICurrentUnitService icus;
	@Autowired
	private ICarService icars;
	@Autowired
	private IDictionaryService idics;
	


	
	//填充车辆下拉列表,查询所有车辆
	@RequestMapping(value="/findCarList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarList(){
		return JsonUtil.writeJson(icars.findListCar(-1, 1), new String[]{"dic","carBrand","carModel","dept","sup"});		
	}
	
	//查询驾驶员信息,查询所有驾驶员
	@RequestMapping(value="/findDriverList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDriverList(){
		return JsonUtil.writeJson(idris.findDriverList(-1),new String[]{"dic","dept","driverType"});
	}
	
	//查询保险公司
	@RequestMapping(value="/findInsUnitList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findInsUnitList(){
		return JsonUtil.writeJson(icus.findCUListByType("保险公司"),new String[]{"unitType"});
	}
	
	//查询保险种类
	@RequestMapping(value="/findInsTypeList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findInsTypeList(){
		return JsonUtil.writeJson(idics.findDicListByName("保险种类"), new String[]{"dic"});
	}
	
	//查询数据,查询全部
	@RequestMapping(value="/findIRDG",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findIRDG(InsuranceRec ir){
		return JsonUtil.writeJson(iinsurancerecs.findIRDG(ir),new String[]{"dic"});
	}
	
	//按照ID查询数据
	@RequestMapping(value="/findIRById",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findIRById(InsuranceRec ir){
		return JsonUtil.writeJson(iinsurancerecs.findIRById(ir.getId()));
	}
	
	//新增
	@RequestMapping(value="/add",produces="text/html;charset=utf-8")
	@ResponseBody
	public String add(InsuranceRec ir){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.addfail);
				
		if(iinsurancerecs.addIR(ir)){
			ts.setMsg(SysStr.addsuccess);
			ts.setSuccess(true);
		}
		
		return JsonUtil.writeJson(ts);
	}
	
	//修改
	@RequestMapping(value="/upd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String upd(InsuranceRec ir){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.editfail);
				
		if(iinsurancerecs.updIR(ir)){
			ts.setMsg(SysStr.editsuccess);
			ts.setSuccess(true);
		}
		
		return JsonUtil.writeJson(ts);
	}
	
	//删除
	@RequestMapping(value="/del",produces="text/html;charset=utf-8")
	@ResponseBody
	public String del(InsuranceRec ir){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.delfail);
				
		if(iinsurancerecs.delIR(ir.getId())){
			ts.setMsg(SysStr.delsuccess);
			ts.setSuccess(true);
		}
		
		return JsonUtil.writeJson(ts);
	}
}
