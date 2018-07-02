package com.cc.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.IViewExpirationDao;
import com.cc.po.DataGrid;
import com.cc.po.ViewExpiration;
import com.cc.service.IViewExpirationService;
import com.cc.util.Page;

@Service("/ViewExpirationService")
public class ViewExpirationServiceImpl implements IViewExpirationService{
	
	@Autowired
	private IViewExpirationDao ive;
	//查询全部
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	@Override
	public DataGrid<ViewExpiration> findAll(ViewExpiration viewexpiration) {
		DataGrid<ViewExpiration> data=new DataGrid<ViewExpiration>();
		List<ViewExpiration> list=new ArrayList<ViewExpiration>();
		Map<String,Object> params=new HashMap<String,Object>();
		String hql="from ViewExpiration v where 1=1 ";
		Page page=new Page(viewexpiration.getPage(),viewexpiration.getRows());
		
		list=ive.getAllByPage(hql, params, page.getBegin(), page.getPageSize());
		if(list!=null && list.size()>0){
			Long l=findCount(viewexpiration);
			data.setRows(list);
			data.setTotal(l);
		}	
		return data;
	}
	
	//查询总数
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long findCount(ViewExpiration viewexpiration) {
		String hql="select count(v) from ViewExpiration v where 1=1 ";
		return ive.count(hql, null);
	}

}
