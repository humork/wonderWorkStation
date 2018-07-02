package com.cc.service;

import com.cc.po.DataGrid;
import com.cc.po.PeccancyRec;

public interface IPeccancyRecService {
	
	/***
	 * 查询违章信息
	 * @param pr 实体
	 * @return 数据容器
	 */
	public DataGrid<PeccancyRec> findPRDG(PeccancyRec pr);
	/***
	 * 查询总数
	 * @param pr 实体
	 * @return 总数
	 */
	public Long findPRCount(PeccancyRec pr);
	/***
	 * 按照主键查询数据
	 * @param id 主键ID
	 * @return 实体
	 */
	public PeccancyRec findPRById(int id);
	/***
	 * 新增违章信息
	 * @param pr 实体
	 * @return 成功与否
	 */
	public boolean addPR(PeccancyRec pr);
	/***
	 * 修改违章信息
	 * @param pr 实体
	 * @return 成功与否
	 */
	public boolean updPR(PeccancyRec pr);
	/***
	 * 删除违章信息
	 * @param pr 实体
	 * @return 成功与否
	 */
	public boolean delPR(int id);
}
