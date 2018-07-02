package com.cc.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.IDictionaryDao;
import com.cc.po.DataGrid;
import com.cc.po.Dictionary;
import com.cc.service.IDictionaryService;
import com.cc.util.Page;
@Service("DicSers")
public class DictionaryServiceimpl implements IDictionaryService{
	@Autowired
	private IDictionaryDao idi;
	
	//根据传进来的名称查询字典表中对应的对象
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Dictionary> findDicListByName(String name) {
		Map<String,Object> params=new HashMap<String,Object>();
		//根据text查询主键id.在根据pid=id得到所有pid和该id相同的对象
		String hql="from Dictionary d1 where d1.dic.id="+
		"(select d2.id from Dictionary d2 where d2.text=:text) " +
		"and d1.isdisable=1 ";
		
		params.put("text", name);
		
		return idi.getAll(hql, params);	
	}
	
	//根据传进来的pid查询对象
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Dictionary> findDicListByPid(int pid) {
		Map<String,Object> params=new HashMap<String,Object>();
		String hql="from Dictionary d where 1=1 ";
		if(pid==-1){
			hql+="and d.dic is null ";
		}else{
			hql+="and d.dic.id=:id ";
			params.put("id", pid);
		}
		return idi.getAll(hql, params);
	}

	
	//查询总数
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long findDicCount(Dictionary dic){
		Map<String,Object> params=new HashMap<String,Object>();
		String hql="select count(d) from Dictionary d where 1=1 ";
		
		if(dic.getPid()==-1){
			hql+="and d.dic is null";
		}else{
			hql+="and d.dic.id =:id";
			params.put("id", dic.getPid());
		}
		return idi.count(hql, params);
	}
	
	//根据id查询
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Dictionary findDictionaryById(int id) {	
		return idi.get(id);
	}
	
	//新增
	@Override
	@Transactional
	public boolean addDictionary(Dictionary dic) {
		if(dic.getDic().getId()==-1){
			dic.setDic(null);
		}
		return idi.add(dic)>0?true:false;
	}
	
	//查询所有
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DataGrid<Dictionary> findDicDGByPid(Dictionary dic) {
		Map<String,Object> params=new HashMap<String,Object>();
		String hql="from Dictionary d where 1=1 ";
		DataGrid<Dictionary> data=new DataGrid<Dictionary>();
		List<Dictionary> list=new ArrayList<Dictionary>();
		
		if(dic.getPid()==-1){
			hql+="and d.dic is null";
		}else{
			hql+="and d.dic.id =:id";
			params.put("id", dic.getPid());
		}
		
		Page page=new Page(dic.getPage(),dic.getRows());
		list=idi.getAllByPage(hql, params,page.getBegin(),page.getPageSize());
		if(list!=null && list.size()>0){
			Long l=findDicCount(dic);
			data.setRows(list);
			data.setTotal(l);
		}
		return data;
	}
	
	//根据pid查询id集合
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Dictionary> findDicListByPid(int pid, int isDisable) {
		Map<String,Object> params =new HashMap<String,Object>();		
		
		String hql="from Dictionary d where d.isdisable=:isdisable ";
		
		params.put("isdisable", isDisable);
		
		if(pid ==-1){
			hql+="and d.dic is null";
		}else{
			hql+="and d.dic.id =:id";
			params.put("id", pid);
		}

		return idi.getAll(hql, params);
	}
	
	//按照级别以及启用与否查询
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Dictionary> findDicListByLev(int lev, int isDisable) {
		
		Map<String,Object> params =new HashMap<String,Object>();		
		
		String hql="from Dictionary d where d.lev=:lev and d.isdisable=:isdisable ";
		
		params.put("lev", lev);
		params.put("isdisable", isDisable);

		return idi.getAll(hql, params);
	}
	
	//修改字典表
	@Override
	@Transactional
	public boolean updDictionary(Dictionary dic) {
		boolean res =false;
		
		if(idi.upd(dic)>0){
			//修改该节点对应的子节点的状态，不考虑成功与否，有的节点没有子节点
			updDicChildren(dic.getId(),dic.getIsdisable());
			res=true;
		}
		return res;	
	}
	
	//修改某节点的所有子节点
	@Override
	@Transactional
	public boolean updDicChildren(int id, int isdisable) {
		boolean res=false;
		//条件容器
		Map<String,Object> params =new HashMap<String,Object>();
		
		try {
			String hql="update Dictionary d set d.isdisable=:isdisable where d.dic.id=:id";
			params.put("isdisable", isdisable);
			params.put("id", id);
			
			if(idi.execute(hql, params)>0){
				res=true;
			}
			
		} catch (Exception e) {
			res=false;
		}	
		return res;
	}

	
}
