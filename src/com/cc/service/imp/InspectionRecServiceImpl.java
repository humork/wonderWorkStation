package com.cc.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.IInspectionRecDao;
import com.cc.po.DataGrid;
import com.cc.po.InspectionRec;
import com.cc.service.IInspectionRecService;
import com.cc.util.Page;

//年检
@Service("InspectionRecService")
public class InspectionRecServiceImpl implements IInspectionRecService{
	
	@Autowired
	private IInspectionRecDao iinspectionrecd;

	//查询所有
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DataGrid<InspectionRec> findIRDG(InspectionRec ir) {
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		//页面数据容器
		DataGrid<InspectionRec> dg =new DataGrid<InspectionRec>();
		//分页工具
		Page page=new Page(ir.getPage(),ir.getRows());
		
		String hql="from InspectionRec ir where 1=1 ";
		if(ir.getCar() !=null && ir.getCar().getCarNo() !=null){
			hql+="and ir.car.carNo like :carNo ";
			params.put("carNo", "%"+ir.getCar().getCarNo()+"%");	
		}
		
		hql+="order by ir.expireDate desc ";
		
		List<InspectionRec> data=iinspectionrecd.getAllByPage(hql, params, page.getBegin(), page.getPageSize());
		
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
	public Long findIRCount(InspectionRec ir) {
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		
		String hql="select count(ir.id) from InspectionRec ir where 1=1 ";
		
		if(ir.getCar() !=null && ir.getCar().getCarNo() !=null){
			hql+="and ir.car.carNo like :carNo ";
			params.put("carNo", "%"+ir.getCar().getCarNo()+"%");	
		}
		
		return iinspectionrecd.count(hql, params);
	}
	
	//根据id查询
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public InspectionRec findIRById(int id) {
		return iinspectionrecd.get(id);
	}
	
	//新增
	@Override
	@Transactional
	public boolean addIR(InspectionRec ir) {
		boolean res=false;
		
		if(iinspectionrecd.add(ir)>0){
			res=true;
		}
		
		return res;
	}
	
	//修改
	@Override
	@Transactional
	public boolean updIR(InspectionRec ir) {
		boolean res=false;
		
		if(iinspectionrecd.upd(ir)>0){
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
		String hql="delete InspectionRec ir where ir.id=:id ";
		params.put("id", id);
		
		if(iinspectionrecd.execute(hql, params)>0){
			res=true;
		}
		
		return res;
	}
	
}
