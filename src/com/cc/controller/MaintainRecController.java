package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.MaintainRec;
import com.cc.po.TempJSON;
import com.cc.service.ICarService;
import com.cc.service.ICurrentUnitService;
import com.cc.service.IDictionaryService;
import com.cc.service.IDriverService;
import com.cc.service.IMaintainRecService;
import com.cc.util.JsonUtil;
import com.cc.util.SysStr;


@Controller
@RequestMapping("/MaintainRecController")
public class MaintainRecController {
	
	@Autowired
	private IDictionaryService idics;
	@Autowired
	private IMaintainRecService imaintainrecs;
	@Autowired
	private IDriverService idris;
	@Autowired
	private ICurrentUnitService icus;
	@Autowired
	private ICarService icars;
	
	//填充车辆下拉列表.查询所有启用车辆
	@RequestMapping(value="/findCarList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarList(){
		return JsonUtil.writeJson(icars.findListCar(-1, 1), new String[]{"dic","carBrand","carModel","dept","sup"});		
	}
		
	//查询车辆信息
	@RequestMapping(value="/findCarById",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarById(MaintainRec mr){
		return JsonUtil.writeJson(icars.findCarById(mr.getCar().getId()),new String[]{"dic","carBrand","carModel","dept","sup"});
	}
		
	//填充驾驶员信息,所有驾驶员
	@RequestMapping(value="/findDriverList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDriverList(){
		return JsonUtil.writeJson(idris.findDriverList(-1),new String[]{"dic","dept","driverType"});
	}
		
	//填充保养单位
	@RequestMapping(value="/findMainUnitList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findMainUnitList(){
		return JsonUtil.writeJson(icus.findCUListByType(new String[]{"4S商家"}),new String[]{"unitType"});
	}
		
	//填充保养类别下拉列表
	@RequestMapping(value="/findMainTypeList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findMainTypeList(){
		return JsonUtil.writeJson(idics.findDicListByName("保养类别"), new String[]{"dic"});
	}
		
	//展示数据,查询所有
	@RequestMapping(value="/findMRDG",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findMRDG(MaintainRec mr){
		return JsonUtil.writeJson(imaintainrecs.findMRDG(mr),new String[]{"dic"});
	}
		
	//按照ID查询数据
	@RequestMapping(value="/findMRById",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findMRById(MaintainRec mr){
		return JsonUtil.writeJson(imaintainrecs.findMRById(mr.getId()));
	}
		
	//新增
	@RequestMapping(value="/add",produces="text/html;charset=utf-8")
	@ResponseBody
	public String add(MaintainRec mr){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.addfail);
				
		if(imaintainrecs.addMR(mr)){
			ts.setMsg(SysStr.addsuccess);
			ts.setSuccess(true);
		}
		
		return JsonUtil.writeJson(ts);
	}
		
	//修改
	@RequestMapping(value="/upd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String upd(MaintainRec mr){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.editfail);
				
		if(imaintainrecs.updMR(mr)){
			ts.setMsg(SysStr.editsuccess);
			ts.setSuccess(true);
		}	
		return JsonUtil.writeJson(ts);
	}
		
	//删除
	@RequestMapping(value="/del",produces="text/html;charset=utf-8")
	@ResponseBody
	public String del(MaintainRec mr){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.delfail);
				
		if(imaintainrecs.delMR(mr.getId())){
			ts.setMsg(SysStr.delsuccess);
			ts.setSuccess(true);
		}	
		return JsonUtil.writeJson(ts);
	}
		
	//查询上一次保养信息[暂未使用]
	/*public void findLastMR(){
		this.writeJson(imaintainrecs.findMRByLastDate(mr),new String[]{"dic"});
	}*/
	
}
