package com.cc.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.ICarDao;
import com.cc.dao.IDriverDao;
import com.cc.dao.IDrivingRecDao;
import com.cc.po.DataGrid;
import com.cc.po.DrivingRec;
import com.cc.po.Users;
import com.cc.service.IDrivingRecService;
import com.cc.util.Page;
import com.cc.util.SysStr;

@Service("driservice")
public class DrivingRecServiceimpl implements IDrivingRecService{
	
	@Autowired
	private IDrivingRecDao Rec;
	@Autowired
	private ICarDao ic;
	@Autowired
	private IDriverDao id;
	
	//查询所有
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DataGrid<DrivingRec> findDRDG(DrivingRec dr,Users u){
		
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		//页面数据容器
		DataGrid<DrivingRec> dg=new DataGrid<DrivingRec>();
		//分页工具
		Page page=new Page(dr.getPage(),dr.getRows());
				
		String hql="from DrivingRec dr where 1=1 ";
		
		//管理员看所有人的记录否则只能看自己的
		if(!u.getLname().equals(SysStr.admin)){
			hql+="and dr.creater.id =:usersid ";
			params.put("usersid",u.getId());
		}
		
		//这里只展示已出车的
		if(dr.getCar() !=null && dr.getCar().getCarNo() !=null){
			hql+="and dr.car.carNo like :carNo ";
			params.put("carNo", "%"+dr.getCar().getCarNo()+"%");
		}else{
			hql+="and dr.backtime is null ";
		}

		List<DrivingRec> data=Rec.getAllByPage(hql, params, page.getBegin(), page.getPageSize());
		
		if(data!=null){			
			Long c=findcount(dr,u);
			dg.setTotal(c);
			dg.setRows(data);
		}
		return dg;
	}
	
	//查询总数
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long findcount(DrivingRec dr,Users u){
		String hql="select count(dd) from DrivingRec dd where 1=1 ";
		Map<String,Object> params=new HashMap<String,Object>();
		
		//除了管理员，其他人只能看自己的
		if(!u.getLname().equals(SysStr.admin)){
			hql+="and dd.creater.id =:useid";
			params.put("useid",u.getId());
		}
		
		//如果出车的返回时间为null.那么它就是出车状态。
		/*if(dr.getBacktime()==null){
			hql+="and dd.backtime is null";
		}*/
		
		//这里的意思是只展示出车状态的车辆
		if(dr.getCar() !=null && dr.getCar().getCarNo() !=null){
			hql+="and dd.car.carNo =:carNo ";
			params.put("carNo", dr.getCar().getCarNo());
		}
		return Rec.count(hql, params);
	}
	
	//新增
	@Override
	@Transactional
	public boolean add(DrivingRec dr) {
		boolean res=false;
		Map<String,Object> params1 =new HashMap<String,Object>();
		Map<String,Object> params2 =new HashMap<String,Object>();
		
		//新增成功后
		if(Rec.add(dr)>0){
			//修改车辆为出车状态
			String hql1="update Car c set c.carState=2 where c.id=:carid";
			params1.put("carid", dr.getCar().getId());
			
			//修改驾驶员为禁用状态
			String hql2="update Driver d set d.isdisable=0 where d.id=:driverid";
			params2.put("driverid", dr.getDriver().getId());
			
			if((ic.execute(hql1, params1)+id.execute(hql2, params2))>1){
				res=true;
			}
		}
		return res;
	}
	
	//根据id查询
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DrivingRec findid(int id) {
		return Rec.get(id);
	}
	
	//修改出车状态
	@Override
	@Transactional
	public boolean upd(DrivingRec dr){
		boolean res=false;
		Map<String,Object> params =new HashMap<String,Object>();
		
		String hql="update DrivingRec dr " +
				"set dr.begin_time=:begin_time, " +				
				"dr.pre_backtime=:pre_backtime, " +
				"dr.dept.id=:dept, " +
				"dr.personnel=:personnel, " +
				"dr.reason=:reason, " +
				"dr.destination=:destination, " +
				"dr.start_mil=:start_mil " +
				"where dr.id=:id ";
		
		params.put("begin_time", dr.getBegin_time());		
		params.put("pre_backtime", dr.getPre_backtime());
		params.put("dept", dr.getDept().getId());
		params.put("personnel", dr.getPersonnel());
		params.put("reason", dr.getReason());
		params.put("destination", dr.getDestination());
		params.put("start_mil", dr.getStart_mil());
		params.put("id", dr.getId());
		
		if(Rec.execute(hql, params)>0){
			res=true;
		}		
		return res;
	}
	
	//回车
	@Override
	@Transactional
	public boolean backDR(DrivingRec dr) {
		Map<String,Object> params1 =new HashMap<String,Object>();
		Map<String,Object> params2 =new HashMap<String,Object>();
		Map<String,Object> params3 =new HashMap<String,Object>();
		String hql1="update DrivingRec dr " +
				"set dr.backtime=:backtime, " +
				"dr.return_mil=:return_mil, " +
				"dr.this_mil=:this_mil, " +
				"dr.park_place=:park_place, " +
				"dr.remarks=:remarks " +	
				"where dr.id=:id ";

		params1.put("backtime", dr.getBacktime());
		params1.put("return_mil", dr.getReturn_mil());
		//计算里程
		System.out.println("3333");
		
		//计算本次里程，前段控制开始里程小于返程里程
		params1.put("this_mil", (dr.getReturn_mil()-dr.getStart_mil()));
		System.out.println("4444");
		params1.put("park_place", dr.getPark_place());
		params1.put("remarks", dr.getRemarks());
		params1.put("id", dr.getId());	
		
		System.out.println("开始");
		//车辆信息修改为可用状态
		String hql2="update Car c set c.carState=1 where c.id=:carid";
		params2.put("carid", dr.getCar().getId());
		System.out.println("结束");
		
		//修改驾驶员为可用状态
		String hql3="update Driver d set d.isdisable=1 where d.id=:driverid";
		params3.put("driverid", dr.getDriver().getId());
		
		int i=Rec.execute(hql1, params1);
		int j=ic.execute(hql2, params2);
		int k=id.execute(hql3, params3);
	
		if((i+j+k)>2){
		
			return true;
		}
		return false;
	}
	
	//删除
	@Override
	@Transactional
	public boolean del(DrivingRec dd) {
		boolean res=false;
		Map<String,Object> params1 =new HashMap<String,Object>();
		Map<String,Object> params2 =new HashMap<String,Object>();
		
		DrivingRec dr=Rec.get(dd.getId());
		System.out.println("2");
		if(dr !=null){
			//如果删除的是未回车状态的出车信息，需要同时更新车辆信息和驾驶员信息
			if(dr.getBacktime() == null){
				//车辆信息修改为可用状态
				String hql1="update Car c set c.carState=1 where c.id=:carid";
				params1.put("carid", dr.getCar().getId());
				int i=ic.execute(hql1, params1);
				
				//驾驶员信息改为可用状态
				String hql2="update Driver d set d.isdisable=1 where d.id=:did";
				params2.put("did", dr.getDriver().getId());
				int j=id.execute(hql2, params2);
			}
			if(Rec.del(dr)>0){
				res=true;
			}
		}
		System.out.println(true);
		return res;
	}
}
