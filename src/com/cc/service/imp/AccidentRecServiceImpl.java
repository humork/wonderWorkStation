package com.cc.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.IAccidentRecDao;
import com.cc.po.AccidentRec;
import com.cc.po.DataGrid;
import com.cc.service.IAccidentRecService;
import com.cc.util.Page;

@Service("AccidentRecService")
public class AccidentRecServiceImpl implements IAccidentRecService {
	@Autowired
	private IAccidentRecDao iaccidentrecd;
	
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DataGrid<AccidentRec> findARDG(AccidentRec ar) {
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		//页面数据容器
		DataGrid<AccidentRec> dg =new DataGrid<AccidentRec>();
		//分页工具
		Page page=new Page(ar.getPage(),ar.getRows());
		
		String hql="from AccidentRec ar where 1=1 ";
		
		if(ar.getCar() !=null && ar.getCar().getCarNo() !=null){
			hql+="and ar.car.carNo like :carNo ";
			params.put("carNo", "%"+ar.getCar().getCarNo()+"%");	
		}
		
		List<AccidentRec> data=iaccidentrecd.getAllByPage(hql, params, page.getBegin(), page.getPageSize());
		
		if(data !=null){
			Long c=findARCount(ar);
			dg.setRows(data);
			dg.setTotal(c);
		}
		
		return dg;
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long findARCount(AccidentRec ar) {
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		
		String hql="select count(ar.id) from AccidentRec ar where 1=1 ";
		
		if(ar.getCar() !=null && ar.getCar().getCarNo() !=null){
			hql+="and ar.car.carNo like :carNo ";
			params.put("carNo", "%"+ar.getCar().getCarNo()+"%");	
		}
		return iaccidentrecd.count(hql, params);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public AccidentRec findARById(int id) {
		return iaccidentrecd.get(id);
	}

	@Override
	@Transactional
	public boolean addAR(AccidentRec ar) {
		boolean res=false;
		
		if(iaccidentrecd.add(ar)>0){
			res=true;
		}
		
		return res;
	}

	@Override
	@Transactional
	public boolean updAR(AccidentRec ar) {
		boolean res=false;
		
		if(iaccidentrecd.upd(ar)>0){
			res=true;
		}
		
		return res;
	}

	@Override
	@Transactional
	public boolean delAR(int id) {
		boolean res=false;
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		
		String hql="delete AccidentRec ar where ar.id=:id";
		
		params.put("id", id);
		
		if(iaccidentrecd.execute(hql, params)>0){
			res=true;
		}
		
		return res;
	}
	
}
