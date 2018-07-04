package com.cc.service;

import com.cc.po.DataGrid;
import com.cc.po.MaintainRec;

//保养记录的增删改查
public interface IMaintainRecService {
	/***
	 * 查询保养信息
	 * @param mr 实体
	 * @return 数据容器
	 */
	public DataGrid<MaintainRec> findMRDG(MaintainRec mr);
	/***
	 * 查询总数
	 * @param mr 实体
	 * @return 总数
	 */
	public Long findMRCount(MaintainRec mr);
	/***
	 * 按照主键查询数据
	 * @param id 主键ID
	 * @return 实体
	 */
	public MaintainRec findMRById(int id);
	/***
	 * 新增保养信息
	 * @param mr 实体
	 * @return 成功与否
	 */
	public boolean addMR(MaintainRec mr);
	/***
	 * 修改保养信息
	 * @param mr 实体
	 * @return 成功与否
	 */
	public boolean updMR(MaintainRec mr);
	/***
	 * 删除保养信息
	 * @param id 主键ID
	 * @return 成功与否
	 */
	public boolean delMR(int id);
	/***
	 * 查询最后一次保养信息
	 * @param mr
	 * @return
	 */
	public MaintainRec findMRByLastDate(MaintainRec mr);
}
