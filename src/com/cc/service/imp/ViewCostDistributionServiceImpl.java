package com.cc.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.IViewCostDistributionDao;
import com.cc.po.ViewCostDistribution;
import com.cc.service.IViewCostDistributionService;

@Service("ViewCostDistributionService")
public class ViewCostDistributionServiceImpl implements IViewCostDistributionService{
	
	@Autowired
	private IViewCostDistributionDao view;
	
	//根据cid查询对象
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	@Override
	public List<ViewCostDistribution> findVCDList(ViewCostDistribution vcd) {
	
		Map<String,Object> params=new HashMap<String,Object>();
		
		String hql="from ViewCostDistribution v where v.cid=:cid ";
		params.put("cid",vcd.getCid());
		
		List<ViewCostDistribution> list=view.getAll(hql, params);
		if(list!=null){
			return list;
		}
		return  new ArrayList<ViewCostDistribution>();
	}

}
