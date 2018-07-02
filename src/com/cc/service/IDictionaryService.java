package com.cc.service;

import java.util.List;

import com.cc.po.DataGrid;
import com.cc.po.Dictionary;


public interface IDictionaryService {
	
	
	/***
	 * 按照id查询单一字典实体
	 * @param id 字典主键
	 * @return 字典实体
	 */
	public Dictionary findDictionaryById(int id);
	/***
	 * 新增
	 * @param dic 待新增实体
	 * @return true或false
	 */
	public boolean addDictionary(Dictionary dic);
	/**
	 * 按照父节点ID查询对应子节点分页信息
	 * @param dic 带有分页所需数据的字典实体
	 * @return 字典集合
	 */
	public DataGrid<Dictionary> findDicDGByPid(Dictionary dic);
	/**
	 * 按照父节点ID查询对应全部子节点集合
	 * @param pid -1查询所有一级节点,其它查询对应子节点
	 * @return 字典集合
	 */
	public List<Dictionary> findDicListByPid(int pid);	
	/***
	 * 按照父节点ID查询对应状态子节点集合
	 * @param pid -1查询所有一级节点,其它查询对应子节点
	 * @param isDisable 1启用的子节点，0禁用的子节点
	 * @return 字典集合
	 */
	public List<Dictionary> findDicListByPid(int pid,int isDisable);
	/***
	 * 按照级别以及启用与否查询
	 * @param lev 级别 1,2,3
	 * @param isDisable 1启用 0禁用
	 * @return 字典集合
	 */
	public List<Dictionary> findDicListByLev(int lev,int isDisable);
	
	/***
	 * 查询总数
	 * @param dic 实体
	 * @return
	 */
	public Long findDicCount(Dictionary dic);
	/***
	 * 修改字典信息
	 * @param dic 待修改数据
	 * @return 成功与否
	 */
	public boolean updDictionary(Dictionary dic);
	/***
	 * 修改某节点的所有子节点
	 * @param id节点ID
	 * @param isdisable是否禁用
	 * @return 成功与否
	 */
	public boolean updDicChildren(int id,int isdisable);
	/***
	 * 按照名称查询对应子节点集合
	 * @param name 名字
	 * @return 集合
	 */
	public List<Dictionary> findDicListByName(String name);
	
}
