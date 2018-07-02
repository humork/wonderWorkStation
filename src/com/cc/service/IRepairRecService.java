package com.cc.service;

import com.cc.po.DataGrid;
import com.cc.po.RepairRec;

public interface IRepairRecService {
	
	/***
	 * 查询维修信息
	 * @param rr 实体
	 * @return 数据容器
	 */
	public DataGrid<RepairRec> findRepRecDG(RepairRec rr);
	/***
	 * 查询总数
	 * @param rr 实体
	 * @return 总数
	 */
	public Long findRepRecCount(RepairRec rr);
	/***
	 * 按照ID查询维修信息
	 * @param id 主键
	 * @return 实体
	 */
	public RepairRec findRepRecById(int id);
	/***
	 * 登记送修信息
	 * @param rr
	 * @return 成功与否
	 */
	public boolean addRepRec(RepairRec rr);
	/***
	 * 登记取车信息
	 * @param rr 实体
	 * @return 成功与否
	 */
	public boolean backRepRec(RepairRec rr);
	/***
	 * 修改送修信息
	 * @param rr 实体
	 * @return 成功与否
	 */
	public boolean updSendRepRec(RepairRec rr);
	/***
	 * 删除维修信息
	 * @param id 主键
	 * @return 成功与否
	 */
	public boolean delRepRec(int id);
}
