package com.cc.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.IInsuranceRecDao;
import com.cc.po.DataGrid;
import com.cc.po.InsuranceRec;
import com.cc.service.IInsuranceRecService;
import com.cc.util.Page;

//保险信息
@Service("InsuranceRecService")
public class InsuranceRecServiceImpl implements IInsuranceRecService {
	@Autowired
	private IInsuranceRecDao iinsurancerecd;
	
	//查询所有
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DataGrid<InsuranceRec> findIRDG(InsuranceRec ir) {
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		//页面数据容器
		DataGrid<InsuranceRec> dg =new DataGrid<InsuranceRec>();
		//分页工具
		Page page=new Page(ir.getPage(),ir.getRows());
		
		String hql="from InsuranceRec ir where 1=1 ";
		if(ir.getCar() !=null && ir.getCar().getCarNo() !=null){
			hql+="and ir.car.carNo like :carNo ";
			params.put("carNo", "%"+ir.getCar().getCarNo()+"%");	
		}
		
		hql+="order by ir.expireDate desc ";
		
		List<InsuranceRec> data=iinsurancerecd.getAllByPage(hql, params, page.getBegin(), page.getPageSize());
		
		if(data !=null){
			Long c=findIRCount(ir);
			dg.setRows(data);
			dg.setTotal(c);
		}
		
		return dg;
	}
	
	//查询总数
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long findIRCount(InsuranceRec ir) {
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		
		String hql="select count(ir.id) from InsuranceRec ir where 1=1 ";
		
		if(ir.getCar() !=null && ir.getCar().getCarNo() !=null){
			hql+="and ir.car.carNo like :carNo ";
			params.put("carNo", "%"+ir.getCar().getCarNo()+"%");	
		}
		
		return iinsurancerecd.count(hql, params);
	}
	
	//根据id查询
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public InsuranceRec findIRById(int id) {
		return iinsurancerecd.get(id);
	}
	
	//新增
	@Transactional
	@Override
	public boolean addIR(InsuranceRec ir) {
		boolean res=false;
		
		if(iinsurancerecd.add(ir)>0){
			res=true;
		}
		System.out.println("111111111111111111"+res);
		return res;
	}
	
	//修改
	@Transactional
	@Override
	public boolean updIR(InsuranceRec ir) {
		boolean res=false;
		
		if(iinsurancerecd.upd(ir)>0){
			res=true;
		}
		
		return res;
	}
	
	//删除
	@Override
	@Transactional
	public boolean delIR(int id) {
		boolean res=false;
		
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		String hql="delete InsuranceRec ir where ir.id=:id ";
		params.put("id", id);
		
		if(iinsurancerecd.execute(hql, params)>0){
			res=true;
		}
		return res;
	}
	
}
