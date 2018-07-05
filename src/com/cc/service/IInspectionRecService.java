package com.cc.service;

import com.cc.po.DataGrid;
import com.cc.po.InspectionRec;

//年检信息的增删改查
public interface IInspectionRecService {
	/***
	 * 查询年检信息
	 * @param ir 实体
	 * @return 数据容器
	 */
	public DataGrid<InspectionRec> findIRDG(InspectionRec ir);
	/***
	 * 查询总数
	 * @param ir 实体
	 * @return 总数
	 */
	public Long findIRCount(InspectionRec ir);
	/***
	 * 按照主键查询数据
	 * @param id 主键ID
	 * @return 实体
	 */
	public InspectionRec findIRById(int id);
	/***
	 * 新增年检信息
	 * @param ir 实体
	 * @return 成功与否
	 */
	public boolean addIR(InspectionRec ir);
	/***
	 * 修改年检信息
	 * @param ir 实体
	 * @return 成功与否
	 */
	public boolean updIR(InspectionRec ir);
	/***
	 * 删除年检信息
	 * @param id 主键ID
	 * @return 成功与否
	 */
	public boolean delIR(int id);	
}
