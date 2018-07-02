package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.RefuelRec;
import com.cc.po.TempJSON;
import com.cc.service.ICarService;
import com.cc.service.ICurrentUnitService;
import com.cc.service.IDictionaryService;
import com.cc.service.IDriverService;
import com.cc.service.IRefuelRecService;
import com.cc.util.JsonUtil;
import com.cc.util.SysStr;

@Controller
@RequestMapping("RefuelRce")
public class RefuelRceController {
	
	@Autowired
	private IRefuelRecService ir;
	@Autowired
	private ICarService ic;
	@Autowired
	private ICurrentUnitService icu;
	@Autowired
	private IDriverService id;
	@Autowired
	private IDictionaryService idc;
	
	//数据展示,查询所有.
	@RequestMapping(value="/findRRDG",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findRRDG(RefuelRec rr){
		return JsonUtil.writeJson(ir.findRefuelRecDG(rr),null);
	}
	
	//查询车辆下拉列表新增时查询所有可用的
	@RequestMapping(value="/findCarList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarList(){
		return JsonUtil.writeJson(ic.findListCar(1, 1), new String[]{"dic"});		
	}
	
	//查询油气站
	@RequestMapping(value="/findOilStaList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findOilStaList(){
		 return JsonUtil.writeJson(icu.findCUListByType("油气站"),new String[]{"unitType"});
	}
	
	//查询驾驶员
	@RequestMapping(value="/findDriverList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDriverList(){
		return JsonUtil.writeJson(id.findDriverList(-1),new String[]{"dic"});
	}
	
	//查询字典表中的油料标号
	@RequestMapping(value="/findOilLabelList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findOilLabelList(){
		return JsonUtil.writeJson(idc.findDicListByName("油料标号"), new String[]{"dic"});
	}
	
	//新增加油信息
	@RequestMapping(value="/add",produces="text/html;charset=utf-8")
	@ResponseBody
	public String add(RefuelRec rr){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.addfail);
				
		if(ir.addRefuelRec(rr)){
			ts.setMsg(SysStr.addsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//通过ID查询信息
	@RequestMapping(value="/findRRById",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findRRById(RefuelRec rr){
     	return JsonUtil.writeJson(ir.findRefuelRecById(rr.getId()));
	}
	
	//修改加油信息
	@RequestMapping(value="/upd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String upd(RefuelRec rr){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.editfail);	
		if(ir.updRefuelRec(rr)){
			ts.setMsg(SysStr.editsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//删除加油信息
	@RequestMapping(value="/del",produces="text/html;charset=utf-8")
	@ResponseBody
	public String del(RefuelRec rr){
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.delfail);	
		if(ir.delRefuelRec(rr.getId())){
			ts.setMsg(SysStr.delsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//查询上一次加油信息
	@RequestMapping(value="/findLastRR",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findLastRR(RefuelRec rr){
		 return JsonUtil.writeJson(ir.findRefuelRecByLastDate(rr),new String[]{"car","oilSta"});
	}
	
	
}
