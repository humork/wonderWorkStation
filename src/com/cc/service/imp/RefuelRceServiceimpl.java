package com.cc.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.IRefuelRecDao;
import com.cc.po.DataGrid;
import com.cc.po.RefuelRec;
import com.cc.service.IRefuelRecService;
import com.cc.util.Page;

//规费
@Service("RefuelService")
public class RefuelRceServiceimpl implements IRefuelRecService{
	
	@Autowired
	private IRefuelRecDao ire;
	
	//查询所有
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DataGrid<RefuelRec> findRefuelRecDG(RefuelRec rr) {
		DataGrid<RefuelRec> data=new DataGrid<RefuelRec>();
		Map<String,Object> params=new HashMap<String,Object>();
		
		String hql="from RefuelRec r where 1=1 ";
		
		//这里只展示已经加过油的
		if(rr.getCar() !=null && rr.getCar().getCarNo() !=null){
			hql+="and r.car.carNo like :carNo ";
			params.put("carNo", "%"+rr.getCar().getCarNo()+"%");			
		}
		hql+="order by r.oilDate desc ";
		
		Page page=new Page(rr.getPage(),rr.getRows());
		
		List<RefuelRec> list=ire.getAllByPage(hql, params, page.getBegin(), page.getPageSize());
		if(list!=null){
			data.setRows(list);
			Long l=findRefuelRecCount(rr);
			data.setTotal(l);
		}
		return data;
	}
	
	//查询总数
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long findRefuelRecCount(RefuelRec rr) {
		Map<String,Object> params=new HashMap<String,Object>();
		String hql="select count(r.id) from RefuelRec r where 1=1 ";
		
		if(rr.getCar() !=null && rr.getCar().getCarNo() !=null){
			hql+="and r.car.carNo like :carNo ";
			params.put("carNo", "%"+rr.getCar().getCarNo()+"%");			
		}
		return ire.count(hql, params);
	}
	
	//根据id查询对象
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	@Override
	public RefuelRec findRefuelRecById(int id) {
		return ire.get(id);
	}
	
	//新增
	@Transactional
	@Override
	public boolean addRefuelRec(RefuelRec rr) {
		return ire.add(rr)>0?true:false;
	}
	
	//修改
	@Override
	@Transactional
	public boolean updRefuelRec(RefuelRec rr) {
		return ire.upd(rr)>0?true:false;
	}
	
	//删除
	@Override
	@Transactional
	public boolean delRefuelRec(int id) {
		boolean res=false;
		Map<String,Object> params=new HashMap<String,Object>();
		String hql="delete RefuelRec r where r.id=:id ";
		params.put("id",id);
		if(ire.execute(hql, params)>0){
			res=true;
		}
		return res;
	}
	
	//查询上一次加油记录
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public RefuelRec findRefuelRecByLastDate(RefuelRec rr) {
		RefuelRec res=null;
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();		
		//排序
		String hql="from RefuelRec rr where rr.car.id=:carid order by rr.oilDate desc ";
		params.put("carid", rr.getCar().getId());
		//根据条件查询这辆车的所有加油记录
		List<RefuelRec> data=ire.getAll(hql, params);		
				
		if(data !=null && data.size()>0){
			//获得上一次加油记录
			res =data.get(0);					
		}else{
			res=new RefuelRec();
		}
		return res;
	}

}
