package com.cc.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.ICarDao;
import com.cc.dao.IRepairRecDao;
import com.cc.po.DataGrid;
import com.cc.po.RepairRec;
import com.cc.service.IRepairRecService;
import com.cc.util.Page;

//维修
@Service("RepairRecService")
public class RepairRecServiceimpl implements IRepairRecService{
	@Autowired
	private IRepairRecDao ire;
	@Autowired
	private ICarDao ic;
	
	//查询所有
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DataGrid<RepairRec> findRepRecDG(RepairRec rr) {
		DataGrid<RepairRec> data=new DataGrid<RepairRec>();
		List<RepairRec> list=new ArrayList<RepairRec>();
		String hql="from RepairRec r where 1=1 ";
		
		Map<String,Object> params=new HashMap<String,Object>();
		
		Page page=new Page(rr.getPage(),rr.getRows());
		
		//只展示正在维修的车辆
		if(rr.getCar() !=null && rr.getCar().getCarNo() !=null){
			hql+="and r.car.carNo like :carNo ";
			params.put("carNo", "%"+rr.getCar().getCarNo()+"%");
		}else{
			hql+="and r.getTime is null ";
		}

		list=ire.getAllByPage(hql, params, page.getBegin(), page.getPageSize());
		
		if(list!=null&&list.size()>0){
			data.setRows(list);
			Long l=findRepRecCount(rr);
			data.setTotal(l);
		}
		return data;
	}
	
	//查询总数
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long findRepRecCount(RepairRec rr) {
		String hql="select count(r) from RepairRec r where 1=1 ";
		
		Map<String,Object> params=new HashMap<String,Object>();
		
		//只展示正在维修的车辆
		if(rr.getCar() !=null && rr.getCar().getCarNo() !=null){
			hql+="and r.car.carNo like :carNo ";
			params.put("carNo", "%"+rr.getCar().getCarNo()+"%");
		}else{
			hql+="and r.getTime is null ";
		}
		return ire.count(hql, params);
	}
	
	//根据id查询信息
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public RepairRec findRepRecById(int id) {
		return ire.get(id);
	}
	
	//新增维修记录
	@Override
	@Transactional
	public boolean addRepRec(RepairRec rr) {
		Map<String,Object> params=new HashMap<String,Object>();
		if(ire.add(rr)>0){
			//修改车辆状态为维修状态
			String hql="update Car c set c.carState=3 where c.id=:id ";
			params.put("id",rr.getCar().getId());
			if(ic.execute(hql, params)>0){
				return true;
			}
		}
		return false;
	}
	
	//取车
	@Transactional
	@Override
	public boolean backRepRec(RepairRec rr) {
		boolean res=false;
		Map<String,Object> params1 =new HashMap<String,Object>();
		Map<String,Object> params2 =new HashMap<String,Object>();
		
		String hql1="update RepairRec rr " +
				"set rr.repType.id=:repType, " +
				"rr.getTime=:getTime, " +
				"rr.repCost=:repCost, " +
				"rr.repOption=:repOption, " +
				"rr.useStuff=:useStuff, " +
				"rr.getRemarks=:getRemarks " +
				"where rr.id=:id ";
		
		params1.put("repType", rr.getRepType().getId());
		params1.put("getTime", rr.getGetTime());
		params1.put("repCost", rr.getRepCost());
		params1.put("repOption", rr.getRepOption());
		params1.put("useStuff", rr.getUseStuff());
		params1.put("getRemarks", rr.getGetRemarks());
		params1.put("id", rr.getId());
		
		//修改成功
		if(ire.execute(hql1, params1)>0){
			//修改车辆为可用状态
			String hql2="update Car c set c.carState=1 where c.id=:carid";
			params2.put("carid", rr.getCar().getId());
			if(ic.execute(hql2, params2)>0){
				res=true;
			}
		}
		return res;
	}
	
	//修改
	@Transactional
	@Override
	public boolean updSendRepRec(RepairRec rr) {
		boolean res=false;
		Map<String,Object> params =new HashMap<String,Object>();
		
		String hql="update RepairRec rr " +
				"set rr.repDepot.id=:repDepot, " +
				"rr.sendTime=:sendTime, " +
				"rr.estTime=:estTime, " +
				"rr.sendReason=:sendReason, " +
				"rr.sendRemarks=:sendRemarks, " +
				"rr.operator.id=:operator " +
				"where rr.id=:id ";
		
		params.put("repDepot", rr.getRepDepot().getId());
		params.put("sendTime", rr.getSendTime());
		params.put("estTime", rr.getEstTime());
		params.put("sendReason", rr.getSendReason());
		params.put("sendRemarks", rr.getSendRemarks());
		params.put("operator", rr.getOperator().getId());
		params.put("id", rr.getId());
		
		if(ire.execute(hql, params)>0){
			res=true;
		}
		return res;
	}
	
	//删除
	@Transactional
	@Override
	public boolean delRepRec(int id) {
		boolean res=false;
		int i=0;
		RepairRec rr=ire.get(id);
		
		if(rr !=null){			
			if(ire.del(rr)>0){
				//如果删除的是未取车信息，则修改车辆信息为可用
				if(rr.getGetTime() ==null){
					//条件容器
					Map<String,Object> params =new HashMap<String,Object>();
					//修改车辆为可用状态
					String hql="update Car c set c.carState=1 where c.id=:carid";
					params.put("carid", rr.getCar().getId());
					
					i=ic.execute(hql, params);
				}				
				res=true;
			}
		}
		return res;
	}
	
}
