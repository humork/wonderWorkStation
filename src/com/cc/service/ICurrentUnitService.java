package com.cc.service;

import java.util.List;

import com.cc.po.CurrentUnit;
import com.cc.po.DataGrid;

public interface ICurrentUnitService {
	
	/**
	 * 根据传进来的名称查询所有可用的
	 * @param name
	 * @return
	 */
	public List<CurrentUnit> findCUListByType(String name);
	
	/**
	 * 根据传进来的名称数组，查询所有可用的
	 * @param name
	 * @return
	 */
	public List<CurrentUnit> findCUListByType(String[] name);
	
	/**
	 * 查询所有
	 * @param cur
	 * @return
	 */
	public DataGrid<CurrentUnit> findAll(CurrentUnit cur);
	
	/**
	 * 查询总数
	 * @param cur
	 * @return
	 */
	public Long findCount(CurrentUnit cur);
	
	/**
	 * 新增
	 * @param cur
	 * @return
	 */
	public boolean add(CurrentUnit cur);
	
	/**
	 * 修改
	 * @param cur
	 * @return
	 */
	public boolean upd(CurrentUnit cur);
	
	/**
	 * 删除
	 * @param cur
	 * @return
	 */
	public boolean del(CurrentUnit cur);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public CurrentUnit findid(int id);
}
