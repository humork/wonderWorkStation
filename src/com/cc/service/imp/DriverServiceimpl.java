package com.cc.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.IDriverDao;
import com.cc.po.DataGrid;
import com.cc.po.Driver;
import com.cc.service.IDriverService;
import com.cc.util.Page;

//驾驶员
@Service("DriverService")
public class DriverServiceimpl implements IDriverService {
	@Autowired
	private IDriverDao ids;
	
	//根据传入的值，查询对应状态的驾驶员
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Driver> findDriverList(int isdisable) {
		String hql="from Driver d where 1=1 ";
		Map<String,Object> params=new HashMap<String,Object>();
		if(isdisable!=-1){
			hql+="and d.isdisable =:isdisable ";
			params.put("isdisable", isdisable);
		}
		return ids.getAll(hql, params);
	}
	
	//查询全部驾驶员
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DataGrid<Driver> findDriverDG(Driver driver) {
		DataGrid<Driver>  data=new DataGrid<Driver>();
		List<Driver> list=new ArrayList<Driver>();
		
		String hql="from Driver d where 1=1 ";
		Map<String,Object> params=new HashMap<String,Object>();
		
		if(driver.getName()!=null){
			hql+="and d.name like :name ";
			params.put("name","%"+driver.getName()+"%");
		}
		
		if(driver.getDriverNo()!=null){
			hql+="and d.driverNo like :driverno ";
			params.put("driverno", "%"+driver.getDriverNo()+"%");
		}
		Page page=new Page(driver.getPage(),driver.getRows());
		System.out.println("每页资料数"+page.getPageSize());
		list=ids.getAllByPage(hql, params, page.getBegin(), page.getPageSize());
		System.out.println("list"+list);
		if(list!=null && list.size()>0){
			data.setRows(list);
			Long l=findDriverCount(driver);
			data.setTotal(l);
		}
		return data;
	}
	
	//查询驾驶员总数
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long findDriverCount(Driver driver) {
		String hql="select count(d) from Driver d where 1=1 ";
		
		Map<String,Object> params=new HashMap<String,Object>();
		
		if(driver.getName()!=null){
			hql+="and d.name like :name ";
			params.put("name","%"+driver.getName()+"%");
		}
		
		if(driver.getDriverNo()!=null){
			hql+="and d.driverNo like :driverno ";
			params.put("driverno", "%"+driver.getDriverNo()+"%");
		}
		
		return ids.count(hql, params);
	}
	
	//新增
	@Override
	@Transactional
	public boolean add(Driver driver) {
		return ids.add(driver)>0?true:false;
	}
	
	//根据id查询
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Driver findid(int id) {
		return ids.get(id);
	}
	
	//修改
	@Override
	@Transactional
	public boolean upd(Driver driver) {
		return ids.upd(driver)>0?true:false;
	}
	
	//删除
	@Override
	@Transactional
	public boolean del(Driver driver) {
		Map<String,Object> params=new HashMap<String,Object>();
		String hql="delete Driver d where d.id=:id ";
		params.put("id",driver.getId());		
		return ids.execute(hql, params)>0?true:false;
	}
	
}
