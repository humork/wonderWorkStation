package com.cc.service;

import java.util.List;

import com.cc.po.ViewCostDistribution;

public interface IViewCostDistributionService {
	
	/***
	 * 查询单个车辆费用分布
	 * @param vcd 实体
	 * @return 数据容器
	 */
	public List<ViewCostDistribution> findVCDList(ViewCostDistribution vcd);
}
