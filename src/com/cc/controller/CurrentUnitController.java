  package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.CurrentUnit;
import com.cc.po.TempJSON;
import com.cc.service.ICurrentUnitService;
import com.cc.service.IDictionaryService;
import com.cc.util.JsonUtil;
import com.cc.util.SysStr;

@Controller
@RequestMapping("/CurrentUnitController")
public class CurrentUnitController {
	
	@Autowired
	private ICurrentUnitService icu;
	@Autowired
	private IDictionaryService idc;
	
	//查询所有
	@RequestMapping(value="/findAll",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findAll(CurrentUnit cur){
		return JsonUtil.writeJson(icu.findAll(cur),new String[]{"div"});
	}
	
	//填充单位类型下拉列表
	@RequestMapping(value="/finddept",produces="text/html;charset=utf-8")
	@ResponseBody
	public String finddept(){
		return JsonUtil.writeJson(idc.findDicListByName("单位类型"),new String[]{"dic"});
	}
	
	//新增
	@RequestMapping(value="/add",produces="text/html;charset=utf-8")
	@ResponseBody
	public String add(CurrentUnit cur){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.addfail);
		if(icu.add(cur)){
			ts.setMsg(SysStr.addsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//修改
	@RequestMapping(value="/upd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String upd(CurrentUnit cur){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.editfail);
		if(icu.upd(cur)){
			ts.setMsg(SysStr.editsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//删除
	@RequestMapping(value="/del",produces="text/html;charset=utf-8")
	@ResponseBody
	public String del(CurrentUnit cur){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.delfail);
		if(icu.del(cur)){
			ts.setMsg(SysStr.delsuccess);
			ts.setSuccess(true);
		}
		return  JsonUtil.writeJson(ts);
	}
	
	//根据id查询
	@RequestMapping(value="/findid",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findid(CurrentUnit cur){
		return JsonUtil.writeJson(icu.findid(cur.getId()));
	}
}
