package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.ViewExpiration;
import com.cc.service.IViewExpirationService;
import com.cc.util.JsonUtil;

@Controller
@RequestMapping("/ViewExpirationController")
public class ViewExpirationController {
	
	@Autowired
	private IViewExpirationService iv;
	
	//查询全部
	@RequestMapping(value="/findAll",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findAll(ViewExpiration view){
		return JsonUtil.writeJson(iv.findAll(view));
	}
}
