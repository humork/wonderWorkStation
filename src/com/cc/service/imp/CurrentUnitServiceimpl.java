package com.cc.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.ICurrentUnitDao;
import com.cc.po.CurrentUnit;
import com.cc.po.DataGrid;
import com.cc.service.ICurrentUnitService;
import com.cc.util.Page;



@Service("CurrentService")
public class CurrentUnitServiceimpl implements ICurrentUnitService{
	
	@Autowired
	private ICurrentUnitDao icu;
	
	//根据名称查询所有可用的
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	@Override
	public List<CurrentUnit> findCUListByType(String name) {
		Map<String,Object> params=new HashMap<String,Object>();
		String hql="from CurrentUnit c where c.unitType.text=:name and c.isdisable=1 ";
		params.put("name",name);
		return icu.getAll(hql, params);
	}
	
	//根据名称数组查询所有可用的
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	@Override
	public List<CurrentUnit> findCUListByType(String[] name){
		String str="";
		//遍历数组
		if(name !=null && name.length>0){
			for(int i=0;i<name.length;i++){
				if(i==0){
					str="'"+name[i]+"'";
				}else{
					str+=",'"+name[i]+"'";
				}
			}
		}
		String hql="from CurrentUnit cu where cu.unitType.text in ("+str+") and cu.isdisable=1 ";
		return icu.getAll(hql, null);	
	}
	
	//查询所有
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DataGrid<CurrentUnit> findAll(CurrentUnit cur) {
		DataGrid<CurrentUnit> data=new DataGrid<CurrentUnit>();
		List<CurrentUnit> list=new ArrayList<CurrentUnit>();
		Map<String,Object> params=new HashMap<String,Object>();
		String hql="from CurrentUnit c where 1=1 ";
		
		if(cur.getUnitName()!=null){
			hql+="and c.unitName=:unitname ";
			params.put("unitname",cur.getUnitName());
		}
		Page page=new Page(cur.getPage(),cur.getRows());
		
		list=icu.getAllByPage(hql, params, page.getBegin(), page.getPageSize());
		if(list!=null&&list.size()>0){
			data.setRows(list);
			Long l=findCount(cur);
			data.setTotal(l);
		}
		return data;
	}
	
	//查询总数
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long findCount(CurrentUnit cur) {
		Map<String,Object> params=new HashMap<String,Object>();
		String hql="select count(c) from CurrentUnit c where 1=1 ";
		if(cur.getUnitName()!=null){
			hql+="and c.unitName=:unitname ";
			params.put("unitname",cur.getUnitName());
		}
		return icu.count(hql, params);
	}
	
	//新增
	@Override
	@Transactional
	public boolean add(CurrentUnit cur) {
		return icu.add(cur)>0?true:false;
	}
	
	//修改
	@Override
	@Transactional
	public boolean upd(CurrentUnit cur) {
		return icu.upd(cur)>0?true:false;
	}
	
	//删除
	@Override
	@Transactional
	public boolean del(CurrentUnit cur) {
		String hql="delete CurrentUnit c where c.id=:id ";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("id", cur.getId());
		icu.del(cur);
		return icu.del(cur)>0?true:false;
	}
	
	//根据id查询
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public CurrentUnit findid(int id) {
		return icu.get(id);
	}
	

}
