package com.cc.service;

import com.cc.po.AccidentRec;
import com.cc.po.DataGrid;


public interface IAccidentRecService {
	/***
	 * 查询事故信息
	 * @param ar 实体
	 * @return 数据容器
	 */
	public DataGrid<AccidentRec> findARDG(AccidentRec ar);
	/***
	 * 查询总数
	 * @param ar 实体
	 * @return 总数
	 */
	public Long findARCount(AccidentRec ar);
	/***
	 * 按照主键查询数据
	 * @param id 主键ID
	 * @return 实体
	 */
	public AccidentRec findARById(int id);
	/***
	 * 新增事故信息
	 * @param ar 实体
	 * @return 成功与否
	 */
	public boolean addAR(AccidentRec ar);
	/***
	 * 修改事故信息
	 * @param ar 实体
	 * @return 成功与否
	 */
	public boolean updAR(AccidentRec ar);
	/***
	 * 删除事故信息
	 * @param ar 实体
	 * @return 成功与否
	 */
	public boolean delAR(int id);
}
