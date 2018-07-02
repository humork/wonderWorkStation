package com.cc.service;

import com.cc.po.DataGrid;
import com.cc.po.ViewExpiration;

public interface IViewExpirationService {
	
	/**
	 * 查询全部
	 */
	public DataGrid<ViewExpiration> findAll(ViewExpiration viewexpiration);
	
	/**
	 * 查询总数
	 * @param viewexpiration
	 * @return
	 */
	public Long findCount(ViewExpiration viewexpiration);
}
