package com.cc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.DataGrid;
import com.cc.po.DrivingRec;
import com.cc.po.TempJSON;
import com.cc.po.Users;
import com.cc.service.ICarService;
import com.cc.service.IDictionaryService;
import com.cc.service.IDriverService;
import com.cc.service.IDrivingRecService;
import com.cc.util.JsonUtil;
import com.cc.util.SysStr;

//出车记录，返回数据web
@Controller
@RequestMapping("/driving")
public class DrivingRecController {
	
	@Autowired
	private IDrivingRecService idvr;
	@Autowired
	private IDictionaryService idic;
	@Autowired
	private ICarService is;
	@Autowired
	private IDriverService id;
	
	
	//查询所有，数据展示用
	@RequestMapping(value="/findDRDG",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDRDG(HttpSession session,DrivingRec dr){
		Users u=(Users) session.getAttribute("login");
		DataGrid<DrivingRec> dd=idvr.findDRDG(dr, u);
		//System.out.println(JsonUtil.writeJson(dd,new String[]{"dic","carBrand","carModel","sup","driverType"}));
		return JsonUtil.writeJson(dd,new String[]{"dic","carBrand","carModel","sup","driverType"});
	}
	
	//填充部门下拉列表，返回给web。
	@RequestMapping(value="/findDeptList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDeptList(){
		return JsonUtil.writeJson(idic.findDicListByName("部门"),new String[]{"dic"});
	}
	
	//填充车辆下拉列表.只显示未出车的.新增时用.
	@RequestMapping(value="/findCarListForAdd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarListForAdd(){
		return JsonUtil.writeJson(is.findListCar(1, 1),new String[]{"dic"});
	}
	
	//填充车辆下拉列表.显示所有启用的.修改时用.
	@RequestMapping(value="/findCarListForupd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarListForupd(){
		return JsonUtil.writeJson(is.findListCar(-1, 1),new String[]{"dic"});
	}
	
	//填充驾驶员下拉列表.新增时用
	@RequestMapping(value="/findDriverListForAdd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDriverListForAdd(){
		return JsonUtil.writeJson(id.findDriverList(1),new String[]{"dic"});
	}
	
	//填充驾驶员下拉列表，修改时用.查询所有驾驶员!
	@RequestMapping(value="/findDriverListForupd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDriverListForupd(){
		return JsonUtil.writeJson(id.findDriverList(-1),new String[]{"dic"});
	}
	
	//新增出车记录
	@RequestMapping(value="/add",produces="text/html;charset=utf-8")
	@ResponseBody
	public String add(DrivingRec dr,HttpSession session){
		//将登记出车记录的人添加上
		dr.setCreater((Users)session.getAttribute("login"));
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.addfail);
		
		if(idvr.add(dr)){
			ts.setMsg(SysStr.addsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//根据id查询实体对象
	@RequestMapping(value="/findDRById",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDRById(DrivingRec dr){
		return JsonUtil.writeJson(idvr.findid(dr.getId()));
	}
	
	//修改出车记录
	@RequestMapping(value="/upd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String upd(DrivingRec dr){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.editfail);
		if(idvr.upd(dr)){
			ts.setMsg(SysStr.editsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//回车
	@RequestMapping(value="/back",produces="text/html;charset=utf-8")
	@ResponseBody
	public String back(DrivingRec dr){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.backfail);
		if(idvr.backDR(dr)){
			ts.setMsg(SysStr.backsuccess);
			
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//删除出车记录
	@RequestMapping(value="/del",produces="text/html;charset=utf-8")
	@ResponseBody
	public String del(DrivingRec dr){
		System.out.println("1");
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.delfail);
		if(idvr.del(dr)){
			ts.setMsg(SysStr.delsuccess);
			ts.setSuccess(true);
		}
		System.out.println(JsonUtil.writeJson(ts));
		return JsonUtil.writeJson(ts);
	}
}
