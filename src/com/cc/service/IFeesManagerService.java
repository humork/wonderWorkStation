package com.cc.service;

import com.cc.po.DataGrid;
import com.cc.po.FeesManager;
/**
 * @Author ChenXiang
 * @Date 2018/7/1,14:59
 */
public interface IFeesManagerService {
	/***
	 * 查询规费信息
	 * @param fees 实体
	 * @return 数据容器
	 */
	public DataGrid<FeesManager> findFeesDG(FeesManager fees);
	/***
	 * 查询总数
	 * @param fees 实体
	 * @return 总数
	 */
	public Long findFeesCount(FeesManager fees);
	/***
	 * 按照ID查询资料
	 * @param id 主键ID
	 * @return 实体
	 */
	public FeesManager findFeesById(int id);
	/***
	 * 新增规费信息
	 * @param fees 实体
	 * @return 成功与否
	 */
	public boolean addFees(FeesManager fees);
	/***
	 * 修改规费信息
	 * @param fees 实体
	 * @return 成功与否
	 */
	public boolean updFees(FeesManager fees);
	/***
	 * 删除规费信息
	 * @param fees 实体
	 * @return 成功与否
	 */
	public boolean delFees(int id);
}
