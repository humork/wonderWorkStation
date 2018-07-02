package com.cc.service;

import com.cc.po.DataGrid;
import com.cc.po.RefuelRec;

public interface IRefuelRecService {
	
	/***
	 * 查询加油信息
	 * @param rr 实体
	 * @return 数据容器
	 */
	public DataGrid<RefuelRec> findRefuelRecDG(RefuelRec rr);
	/***
	 * 查询总数
	 * @param rr 实体
	 * @return 总数
	 */
	public Long findRefuelRecCount(RefuelRec rr);
	/***
	 * 按照ID查询信息
	 * @param rr 实体
	 * @return 实体
	 */
	public RefuelRec findRefuelRecById(int id);
	/***
	 * 新增加油信息
	 * @param rr 实体 
	 * @return 成功与否
	 */
	public boolean addRefuelRec(RefuelRec rr);
	/***
	 * 修改加油信息
	 * @param rr 实体
	 * @return 成功与否
	 */
	public boolean updRefuelRec(RefuelRec rr);
	/***
	 * 删除加油信息
	 * @param rr 实体
	 * @return 成功与否
	 */
	public boolean delRefuelRec(int id);
	/***
	 * 查询上一次加油记录
	 * @param rr
	 * @return
	 */
	public RefuelRec findRefuelRecByLastDate(RefuelRec rr);
	
}
