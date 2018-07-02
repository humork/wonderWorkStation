package com.cc.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.IPeccancyRecDao;
import com.cc.po.DataGrid;
import com.cc.po.PeccancyRec;
import com.cc.service.IPeccancyRecService;
import com.cc.util.Page;

//违章
@Service("PeccancyRecService")
public class PeccancyRecServiceImpl implements IPeccancyRecService{
	
	@Autowired
	private IPeccancyRecDao ipeccancyrecd;
	
	//查询所有
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DataGrid<PeccancyRec> findPRDG(PeccancyRec pr) {
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		//页面数据容器
		DataGrid<PeccancyRec> dg =new DataGrid<PeccancyRec>();
		//分页工具
		Page page=new Page(pr.getPage(),pr.getRows());
		
		String hql="from PeccancyRec pr where 1=1 ";
		
		if(pr.getCar() !=null && pr.getCar().getCarNo() !=null){
			hql+="and pr.car.carNo like :carNo ";
			params.put("carNo", "%"+pr.getCar().getCarNo()+"%");	
		}
		
		List<PeccancyRec> data=ipeccancyrecd.getAllByPage(hql, params, page.getBegin(), page.getPageSize());
		
		if(data !=null){
			Long c=findPRCount(pr);
			dg.setRows(data);
			dg.setTotal(c);
		}
		
		return dg;
	}
	
	//查询总数
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long findPRCount(PeccancyRec pr) {
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		
		String hql="select count(pr.id) from PeccancyRec pr where 1=1 ";
		
		if(pr.getCar() !=null && pr.getCar().getCarNo() !=null){
			hql+="and pr.car.carNo like :carNo ";
			params.put("carNo", "%"+pr.getCar().getCarNo()+"%");	
		}
		return ipeccancyrecd.count(hql, params);
	}
	
	//根据id查询
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public PeccancyRec findPRById(int id) {
		return ipeccancyrecd.get(id);
	}
	
	//新增
	@Override
	@Transactional
	public boolean addPR(PeccancyRec pr) {
		boolean res=false;
		
		if(ipeccancyrecd.add(pr)>0){
			res=true;
		}
		return res;
	}
	
	//修改
	@Override
	@Transactional
	public boolean updPR(PeccancyRec pr) {
		boolean res=false;
		
		if(ipeccancyrecd.upd(pr)>0){
			res=true;
		}
		
		return res;
	}
	
	//删除
	@Override
	@Transactional
	public boolean delPR(int id) {
		boolean res=false;
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		
		String hql="delete PeccancyRec pr where pr.id=:id";
		
		params.put("id", id);
		
		if(ipeccancyrecd.execute(hql, params)>0){
			res=true;
		}
		
		return res;
	}
	
}
