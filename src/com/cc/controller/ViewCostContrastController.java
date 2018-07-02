package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.ViewCostContrast;
import com.cc.service.IViewCostContrastService;
import com.cc.util.JsonUtil;

@Controller
@RequestMapping("/ViewCostContrastControllerk")
public class ViewCostContrastController {
	
	@Autowired
	private IViewCostContrastService iv;
	
	
	@RequestMapping(value="/findAll",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findAll(ViewCostContrast view){
		return JsonUtil.writeJson(iv.findVCCList(view));
	}
}
