package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.ViewCostDistribution;
import com.cc.service.ICarService;
import com.cc.service.IViewCostDistributionService;
import com.cc.util.JsonUtil;

@Controller
@RequestMapping("ViewCostDistributionController")
public class ViewCostDistributionController {
	
	@Autowired
	private ICarService icars;
	@Autowired
	private IViewCostDistributionService iv;
	
	//填充车辆下拉列表
	@RequestMapping(value="/findCarList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findCarList(){
		return JsonUtil.writeJson(icars.findListCar(-1, 1), new String[]{"dic","carBrand","carModel","dept","sup"});		
	}
	
	//查询数据
	@RequestMapping(value="/findAll",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findPieList(ViewCostDistribution vcd){
		return JsonUtil.writeJson(iv.findVCDList(vcd));
	}

}
