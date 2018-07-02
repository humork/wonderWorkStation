package com.cc.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.IMaintainRecDao;
import com.cc.po.DataGrid;
import com.cc.po.MaintainRec;
import com.cc.service.IMaintainRecService;
import com.cc.util.Page;

//保养记录
@Service("MaintainRecService")
public class MaintainRecServiceimpl implements IMaintainRecService{
	@Autowired
	private IMaintainRecDao imaintainrecd;
	
	//查询所有
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DataGrid<MaintainRec> findMRDG(MaintainRec mr) {
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		//页面数据容器
		DataGrid<MaintainRec> dg=new DataGrid<MaintainRec>();
		//分页工具
		Page page=new Page(mr.getPage(),mr.getRows());
		
		String hql="from MaintainRec mr where 1=1 ";
		//这里只展示保养过的信息
		if(mr.getCar() !=null && mr.getCar().getCarNo() !=null){
			hql+="and mr.car.carNo like :carNo ";
			params.put("carNo", "%"+mr.getCar().getCarNo()+"%");	
		}
		
		List<MaintainRec> data=imaintainrecd.getAllByPage(hql, params, page.getBegin(), page.getPageSize());
		
		if(data !=null){
			Long c=findMRCount(mr);
			dg.setRows(data);
			dg.setTotal(c);
		}
		
		return dg;
	}
	//查询总数
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long findMRCount(MaintainRec mr) {
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		
		String hql="select count(mr.id) from MaintainRec mr where 1=1 ";
		
		if(mr.getCar() !=null && mr.getCar().getCarNo() !=null){
			hql+="and mr.car.carNo like :carNo ";
			params.put("carNo", "%"+mr.getCar().getCarNo()+"%");	
		}
		
		return imaintainrecd.count(hql, params);
	}
	
	//根据id查询
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public MaintainRec findMRById(int id) {
		return imaintainrecd.get(id);
	}
	
	//新增
	@Override
	@Transactional
	public boolean addMR(MaintainRec mr) {
		boolean res=false;
		
		if(imaintainrecd.add(mr)>0){
			res=true;
		}
		
		return res;
	}
	
	//修改
	@Override
	@Transactional
	public boolean updMR(MaintainRec mr) {
		boolean res=false;
		
		if(imaintainrecd.upd(mr)>0){
			res=true;
		}
		
		return res;
	}
	
	//删除
	@Override
	@Transactional
	public boolean delMR(int id) {
		boolean res=false;
		
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		String hql="delete MaintainRec mr where mr.id=:id ";
		params.put("id", id);
		
		if(imaintainrecd.execute(hql, params)>0){
			res=true;
		}
		
		return res;
	}
	
	//查询汽车上次保养的时间
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public MaintainRec findMRByLastDate(MaintainRec mr) {
		MaintainRec maintainRec=null;
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();		
		
		String hql="from MaintainRec mr where mr.car.id=:carid order by mr.mainDate desc ";
		
		params.put("carid", mr.getCar().getId());
		
		List<MaintainRec> data=imaintainrecd.getAll(hql, params);		
				
		if(data !=null && data.size()>0){
			maintainRec =data.get(0);					
		}else{
			maintainRec=new MaintainRec();
		}
		
		return maintainRec;
	}
}
