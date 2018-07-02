package com.cc.service;

import java.util.List;

import com.cc.po.DataGrid;
import com.cc.po.Driver;

public interface IDriverService {
	/**
	 * 根据传进来的数字，查询对应的驾驶员。
	 * @param isdisable
	 * @return
	 */
	public List<Driver> findDriverList(int isdisable);
	
	/**
	 * 查询总数
	 * @param driver
	 * @return
	 */
	public DataGrid<Driver> findDriverDG(Driver driver);
	
	/**
	 * 查询总数
	 * @param driver
	 * @return
	 */
	public Long findDriverCount(Driver driver);
	
	/**
	 * 新增驾驶员信息
	 * @param driver
	 * @return
	 */
	public boolean add(Driver driver);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Driver findid(int id);
	
	/**
	 * 修改驾驶员信息
	 * @param driver
	 * @return
	 */
	public boolean upd(Driver driver);
	
	/**
	 * 删除
	 * @param driver
	 * @return
	 */
	public boolean del(Driver driver);
}
