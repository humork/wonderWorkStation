package com.cc.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.IFeesManagerDao;
import com.cc.po.DataGrid;
import com.cc.po.FeesManager;
import com.cc.service.IFeesManagerService;
import com.cc.util.Page;
/**
 * @Author ChenXiang
 * @Date 2018/7/1,15:05
 */
@Service("FeesManagerService")
public class FeesManagerServiceimpl implements IFeesManagerService {
	
	@Autowired
	private IFeesManagerDao ife;
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DataGrid<FeesManager> findFeesDG(FeesManager fees) {
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		//页面数据容器
		DataGrid<FeesManager> dg =new DataGrid<FeesManager>();
		
		//分页工具
		Page page=new Page(fees.getPage(),fees.getRows());
		
		String hql ="from FeesManager f where 1=1 ";
		
		//这里只展示缴过费用的车辆
		if(fees.getCar() !=null && fees.getCar().getCarNo() !=null){
			hql+="and f.car.carNo like :carno ";
			params.put("carno", "%"+fees.getCar().getCarNo()+"%");
		}	
		List<FeesManager> data=ife.getAllByPage(hql, params, page.getBegin(), page.getPageSize());
		
		if(data !=null){
			Long c=findFeesCount(fees);			
			dg.setRows(data);
			dg.setTotal(c);
		}
		return dg;
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long findFeesCount(FeesManager fees) {
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();		
		
		String hql ="select count(f.id) from FeesManager f where 1=1 ";
		
		if(fees.getCar() !=null && fees.getCar().getCarNo() !=null){
			hql+="and f.car.carNo like :carno ";
			params.put("carno", "%"+fees.getCar().getCarNo()+"%");
		}
		
		return ife.count(hql, params);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public FeesManager findFeesById(int id) {
		return ife.get(id);
	}

	@Override
	@Transactional
	public boolean addFees(FeesManager fees) {
		boolean res=false;
		
		if(ife.add(fees)>0){
			res=true;
		}
		return res;
	}

	@Override
	@Transactional
	public boolean updFees(FeesManager fees) {
		boolean res=false;
		
		if(ife.upd(fees)>0){
			res=true;
		}
		return res;
	}

	@Override
	@Transactional
	public boolean delFees(int id) {
		boolean res=false;
		Map<String,Object> params =new HashMap<String,Object>();
		
		String hql="delete FeesManager f where f.id=:id";
		params.put("id", id);
		
		if(ife.execute(hql, params)>0){
			res=true;
		}
		return res;
	}
	
}
