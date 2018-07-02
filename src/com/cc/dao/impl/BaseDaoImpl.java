package com.cc.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.dao.IBaseDao;

/*
 * creater:roy
 * date:2014-12-5
 * content:baseDao
 */

public class BaseDaoImpl<T extends Serializable,PK extends Serializable> extends HibernateDaoSupport implements IBaseDao<T, PK>{
	//T的类型
	private Class<T> entityClass;
	
	public BaseDaoImpl() {
		this.entityClass=null;
		//利用反射的方式取得T的类型
		Class c =getClass();
		Type t=c.getGenericSuperclass();
		if(t instanceof ParameterizedType){
			Type[] p=((ParameterizedType)t).getActualTypeArguments();
			this.entityClass=(Class<T>)p[0];
		}
	}
	
	//自动注入，默认为bytype
	@Autowired
	public void setmysession(SessionFactory sf){
		super.setSessionFactory(sf);
	}
		
	//session工厂
	private Session getBaseSession(){
		Session s=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		s.flush();
		
		//授权过程中因为有循环新增，但是事务是在循环结束后提交，
		//所以不能提前清空session
		//s.clear();
		
		return s;
	}
	
	//新增
	public Integer add(T t) {
		int res=-1;
		try {
			res=(Integer)getBaseSession().save(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	//总数
	public Long count(String hql, Map<String, Object> params) {
		Long res=-1l;
		Query q=getBaseSession().createQuery(hql);
		
		if(params !=null && params.size()>0){
			for(String p:params.keySet()){
				q.setParameter(p, params.get(p));
			}
		}
		
		res=(Long) q.uniqueResult();
		
		return res;
	}

	//删除
	public Integer del(T t) {
		int res=-1;
		try {
			getBaseSession().delete(t);
			res=1;
		} catch (Exception e) {
		}
		
		return res;
	}

	//通用执行hql(update xxx set name=xxx where age>33 and name like)
	public Integer execute(String hql, Map<String, Object> params) {
		int res=-1;
		
		Query q=getBaseSession().createQuery(hql);
		if(params !=null && params.size()>0){
			for(String p:params.keySet()){
				q.setParameter(p, params.get(p));
			}
		}
		
		try {
			res=q.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return res;
	}

	//查询单个
	public T get(PK id) {
		T t=(T)getBaseSession().get(entityClass, id);
		if(t!=null){
			return t;
		}
		return null;
	}	
	
	//查询单个实体通过hql
	@Override
	public T get(String hql, Map<String, Object> params) {
		Query q=getBaseSession().createQuery(hql);
		
		if(params !=null && params.size()>0){
			for(String p:params.keySet()){
				q.setParameter(p, params.get(p));
			}
		}
		
		return (T) q.uniqueResult();
	}

	//查询全部[不分页]
	public List<T> getAll(String hql, Map<String, Object> params) {
		Query q=getBaseSession().createQuery(hql);
		
		if(params !=null && params.size()>0){
			for(String p:params.keySet()){
				q.setParameter(p, params.get(p));
			}
		}
		
		List<T> l =q.list();
		
		if(l!=null && l.size()>0){
			return l;
		}
		return null;
	}

	//查询全部[分页]
	public List<T> getAllByPage(String hql, Map<String, Object> params,
			Integer DataBegin, Integer pageSize) {
		Query q=getBaseSession().createQuery(hql);
		
		if(params !=null && params.size()>0){
			for(String p:params.keySet()){
				q.setParameter(p, params.get(p));
			}
		}
		
		List<T> l =q.setFirstResult(DataBegin).setMaxResults(pageSize).list();
		if(l!=null && l.size()>0){
			return l;
		}
		return null;
	}

	//修改
	public Integer upd(T t) {
		int res=-1;
		try {
			getBaseSession().update(t);
			res=1;
		} catch (Exception e) {
		}
		
		return res;
	}

}
