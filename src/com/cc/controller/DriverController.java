package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.Driver;
import com.cc.po.TempJSON;
import com.cc.service.IDictionaryService;
import com.cc.service.IDriverService;
import com.cc.util.JsonUtil;
import com.cc.util.SysStr;

@Controller
@RequestMapping("/DriverController")
public class DriverController {
	
	@Autowired
	private IDriverService idr;
	@Autowired
	private IDictionaryService ids;
	
	//查询所有
	@RequestMapping(value="/findAll",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findAll(Driver driver){
		return JsonUtil.writeJson(idr.findDriverDG(driver),new String[]{"div"});	
	}
	
	//填充部门
	@RequestMapping(value="/finddept",produces="text/html;charset=utf-8")
	@ResponseBody
	public String finddept(){
		return JsonUtil.writeJson(ids.findDicListByName("部门"),new String[]{"div"});
	}
	
	//填充驾照类型下拉列表
	@RequestMapping(value="/findjzlx",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findjzlx(){
		return JsonUtil.writeJson(ids.findDicListByName("驾照类型"),new String[]{"dic"});
	}
	
	//新增
	@RequestMapping(value="/add",produces="text/html;charset=utf-8")
	@ResponseBody
	public String add(Driver driver){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.addfail);
		
		if(idr.add(driver)){
			ts.setMsg(SysStr.addsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//根据id查询
	@RequestMapping(value="/findid",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findid(Driver driver){
		return JsonUtil.writeJson(idr.findid(driver.getId()),new String[]{"dic"});
	}
	
	//修改
	@RequestMapping(value="/upd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String upd(Driver driver){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.editfail);
		
		if(idr.upd(driver)){
			ts.setMsg(SysStr.editsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//删除
	@RequestMapping(value="/del",produces="text/html;charset=utf-8")
	@ResponseBody
	public String del(Driver driver){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.delfail);
		
		if(idr.del(driver)){
			ts.setMsg(SysStr.delsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
}
