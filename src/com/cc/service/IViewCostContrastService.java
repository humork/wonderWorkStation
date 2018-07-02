package com.cc.service;

import java.util.List;

import com.cc.po.ReportColumn;
import com.cc.po.ViewCostContrast;



public interface IViewCostContrastService {
	
	/***
	 * 查询车辆费用对比
	 * @param vcc 实体
	 * @return 柱状图格式数据容器
	 */
	public List<ReportColumn<Double>> findVCCList(ViewCostContrast vcc);
}
