package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.RepairRec;
import com.cc.po.TempJSON;
import com.cc.service.ICarService;
import com.cc.service.ICurrentUnitService;
import com.cc.service.IDictionaryService;
import com.cc.service.IDriverService;
import com.cc.service.IRepairRecService;
import com.cc.util.JsonUtil;
import com.cc.util.SysStr;

@Controller
@RequestMapping("/RepairRecController")
public class RepairRecController {
	@Autowired
	private IRepairRecService ir;
	@Autowired
	private IDriverService id;
	@Autowired
	private ICarService ic;
	@Autowired
	private ICurrentUnitService icu;
	@Autowired
	private IRepairRecService ire;
	@Autowired
	private IDictionaryService ids;
	
	//展示所有
	@RequestMapping(value="/findRRDG",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findRRDG(RepairRec rr){
		return JsonUtil.writeJson(ir.findRepRecDG(rr),null);
	}
	
	//填充车辆下拉列表,新增时查询所有可以使用的
	@RequestMapping(value="/findCarListForAdd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarListForAdd(){
		return JsonUtil.writeJson(ic.findListCar(1,1), new String[]{"dic"});
	}
	
	//填充车辆下拉列表,修改时查询所有的
	@RequestMapping(value="/findCarListForupd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarListForupd(){
		return JsonUtil.writeJson(ic.findListCar(-1,1), new String[]{"dic"});
	}
	
	//填充维修厂商
	@RequestMapping(value="/findRepDepotList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findRepDepotList(){
		return JsonUtil.writeJson(icu.findCUListByType(new String[]{"4S商家",null}),new String[]{"unitType"});
	}
	
	//填充驾驶员.新增时查询可以使用的
	@RequestMapping(value="/findDriverListForAdd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDriverListForAdd(){
		return JsonUtil.writeJson(id.findDriverList(1),new String[]{"dic"});
	}
	
	//填充驾驶员信息包括已出车的.修改时用
	@RequestMapping(value="/findDriverListForUpd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDriverListForUpd(){
		return JsonUtil.writeJson(id.findDriverList(-1),new String[]{"dic"});
	}
			
	//新增维修记录
	@RequestMapping(value="/add",produces="text/html;charset=utf-8")
	@ResponseBody
	public String add(RepairRec rr){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.addfail);
		
		if(ire.addRepRec(rr)){
			ts.setMsg(SysStr.addsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//根据id查询记录
	@RequestMapping(value="/findRRById",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findRRById(RepairRec rr){
		return JsonUtil.writeJson(ire.findRepRecById(rr.getId()), new String[]{"dic","carBrand","carModel","dept","sup","driverType","unitType"});
	}
	
	//修改维修记录
	@RequestMapping(value="/updSendRR",produces="text/html;charset=utf-8")
	@ResponseBody
	public String updSendRR(RepairRec rr){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.editfail);
		if(ire.updSendRepRec(rr)){
			ts.setMsg(SysStr.editsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//删除记录
	@RequestMapping(value="/delRR",produces="text/html;charset=utf-8")
	@ResponseBody
	public String delRR(RepairRec rr){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.delfail);
		if(ire.delRepRec(rr.getId())){
			ts.setMsg(SysStr.delsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//查询维修类别
	@RequestMapping(value="/findRepTypeList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findRepTypeList(){
		return JsonUtil.writeJson(ids.findDicListByName("维修类别"), new String[]{"dic"});
	}
	
	//取车
	@RequestMapping(value="/backRR",produces="text/html;charset=utf-8")
	@ResponseBody
	public String backRR(RepairRec rr){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.getfail);
		
		if(ire.backRepRec(rr)){
			ts.setMsg(SysStr.getsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
}
