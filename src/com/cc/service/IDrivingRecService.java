package com.cc.service;

import com.cc.po.DataGrid;
import com.cc.po.DrivingRec;
import com.cc.po.Users;
/**
 * @Author ChenXiang
 * @Date 2018/7/1,15:02
 */
public interface IDrivingRecService {
	/**
	 * 查询全部
	 * @param dr
	 * @param u
	 * @return
	 */
	public DataGrid<DrivingRec> findDRDG(DrivingRec dr,Users u);
	
	/**
	 * 查询总数
	 * @param dr
	 * @param u
	 * @return
	 */
	public Long findcount(DrivingRec dr,Users u);
	
	/**
	 * 新增
	 * @param dr
	 * @return
	 */
	public boolean add(DrivingRec dr);
	
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public DrivingRec findid(int id);
	
	/**
	 * 修改出车记录
	 * @param dr
	 * @return
	 */
	public boolean upd(DrivingRec dr);
	
	/**
	 * 回车
	 * @param dr
	 * @return
	 */
	public boolean backDR(DrivingRec dr);
	
	/**
	 * 删除出车记录
	 * @param dr
	 * @return
	 */
	public boolean del(DrivingRec dr);
}
