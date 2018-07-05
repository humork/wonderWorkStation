package com.cc.service;

import com.cc.po.DataGrid;
import com.cc.po.InsuranceRec;

//保险信息
public interface IInsuranceRecService {
	/***
	 * 查询保险信息
	 * @param ir 实体
	 * @return 数据容器
	 */
	public DataGrid<InsuranceRec> findIRDG(InsuranceRec ir);
	/***
	 * 查询总数
	 * @param ir 实体
	 * @return 总数
	 */
	public Long findIRCount(InsuranceRec ir);
	/***
	 * 按照主键查询数据
	 * @param id 主键ID
	 * @return 实体
	 */
	public InsuranceRec findIRById(int id);
	/***
	 * 新增保险信息
	 * @param ir 实体
	 * @return 成功与否
	 */
	public boolean addIR(InsuranceRec ir);
	/***
	 * 修改保险信息
	 * @param ir 实体
	 * @return 成功与否
	 */
	public boolean updIR(InsuranceRec ir);
	/***
	 * 删除保险信息
	 * @param id 主键ID
	 * @return 成功与否
	 */
	public boolean delIR(int id);
}
