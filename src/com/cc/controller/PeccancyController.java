package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.PeccancyRec;
import com.cc.po.TempJSON;
import com.cc.service.ICarService;
import com.cc.service.IDictionaryService;
import com.cc.service.IDriverService;
import com.cc.service.IPeccancyRecService;
import com.cc.util.JsonUtil;
import com.cc.util.SysStr;


@Controller
@RequestMapping("/PeccancyController")
public class PeccancyController {
	@Autowired
	private ICarService icars;
	@Autowired
	private IDictionaryService idics;
	@Autowired
	private IDriverService idris;
	@Autowired
	private IPeccancyRecService ipeccancyrecs;

	//填充车辆下拉列表，所有启用车辆
	@RequestMapping(value="/findCarList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarList(){
		return JsonUtil.writeJson(icars.findListCar(-1, 1), new String[]{"dic","carBrand","carModel","dept","sup"});		
	}
	
	//填充驾驶员下拉列表,所有驾驶员
	@RequestMapping(value="/findDriverList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDriverList(){
		return JsonUtil.writeJson(idris.findDriverList(-1),new String[]{"dic","dept","driverType"});
	}
	
	//填充违章项目下拉列表
	@RequestMapping(value="/findOptionList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findOptionList(){
		System.out.println(idics.findDicListByName("违章项目").get(0));
		return JsonUtil.writeJson(idics.findDicListByName("违章项目"), new String[]{"dic"});
	}
	
	//查询数据,查询所有
	@RequestMapping(value="/findPRDG",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findPRDG(PeccancyRec pr){
		return JsonUtil.writeJson(ipeccancyrecs.findPRDG(pr),new String[]{"dic"});
	}
	
	//按照ID查询数据
	@RequestMapping(value="/findPRById",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findPRById(PeccancyRec pr){
		return JsonUtil.writeJson(ipeccancyrecs.findPRById(pr.getId()));
	}
	
	//新增
	@RequestMapping(value="/add",produces="text/html;charset=utf-8")
	@ResponseBody
	public String add(PeccancyRec pr){
		TempJSON tj =new TempJSON();
		tj.setMsg(SysStr.addfail);
				
		if(ipeccancyrecs.addPR(pr)){
			tj.setMsg(SysStr.addsuccess);
			tj.setSuccess(true);
		}
		
		return JsonUtil.writeJson(tj);
	}
	
	//修改
	@RequestMapping(value="/upd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String upd(PeccancyRec pr){
		TempJSON tj =new TempJSON();
		tj.setMsg(SysStr.editfail);
				
		if(ipeccancyrecs.updPR(pr)){
			tj.setMsg(SysStr.editsuccess);
			tj.setSuccess(true);
		}
		
		return JsonUtil.writeJson(tj);
	}
	
	//删除
	@RequestMapping(value="/del",produces="text/html;charset=utf-8")
	@ResponseBody
	public String del(PeccancyRec pr){
		TempJSON tj =new TempJSON();
		tj.setMsg(SysStr.delfail);
				
		if(ipeccancyrecs.delPR(pr.getId())){
			tj.setMsg(SysStr.delsuccess);
			tj.setSuccess(true);
		}
		
		return JsonUtil.writeJson(tj);
	}
	
}
