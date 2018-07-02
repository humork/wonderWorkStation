package com.cc.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDao<T extends Serializable,PK extends Serializable> {
	//查询单个实体
	public T get(PK id);
	//查询单个实体通过hql
	public T get(String hql,Map<String,Object> params);
	//查询总数
	public Long count(String hql,Map<String,Object> params);
	//查询全部[不分页]
	public List<T> getAll(String hql,Map<String,Object> params);
	//查询全部[分页]
	public List<T> getAllByPage(String hql,Map<String,Object> params,Integer DataBegin,Integer pageSize);
		
	//新增
	public Integer add(T t);
	//修改
	public Integer upd(T t);
	//删除
	public Integer del(T t);
	
	//通用执行Hql
	public Integer execute(String hql,Map<String,Object> params);
}
