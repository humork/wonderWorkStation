package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.Dictionary;
import com.cc.po.TempJSON;
import com.cc.service.IDictionaryService;
import com.cc.util.JsonUtil;
import com.cc.util.SysStr;

@Controller
@RequestMapping("/DictionaryController")
public class DictionaryController {
	
	@Autowired
	private IDictionaryService idic;
	
	//查询对应节点的子节点列表
	@RequestMapping(value="/findDicDG",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDicDG(Dictionary dic){
		return JsonUtil.writeJson(idic.findDicDGByPid(dic));
	}
		
	//根据pid查询所有pid=id的对象，返回给web端。
	@RequestMapping(value="/findDicAll",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDicAll(Dictionary dic){
		return JsonUtil.writeJson(idic.findDicListByPid(dic.getPid()));
	}
		
	//下拉列表初始化,按级别查询
	@RequestMapping(value="/findDicByLev",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDicByLev(Dictionary dic){
		return JsonUtil.writeJson(idic.findDicListByLev(dic.getLev(),dic.getIsdisable()));
	}
		
	//新增
	@RequestMapping(value="/addDic",produces="text/html;charset=utf-8")
	@ResponseBody
	public String addDic(Dictionary dic){
		boolean res=idic.addDictionary(dic);		
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.addfail);
		
		if(res){
			ts.setMsg(SysStr.addsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
		
	//取得待修改数据
	@RequestMapping(value="/findUpdDic",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findUpdDic(Dictionary dic){
		return JsonUtil.writeJson(idic.findDictionaryById(dic.getId()));
	}
		
	//修改
	@RequestMapping(value="/updDic",produces="text/html;charset=utf-8")
	@ResponseBody
	public String updDic(Dictionary dic){
		boolean res=idic.updDictionary(dic);
		
		TempJSON ts =new TempJSON();
		ts.setMsg(SysStr.editfail);
		
		if(res){
			ts.setMsg(SysStr.editsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
}
