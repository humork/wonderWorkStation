package com.cc.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.IMenuDao;
import com.cc.dao.IRoleDao;
import com.cc.dao.IUsersDao;
import com.cc.po.DataGrid;
import com.cc.po.Menu;
import com.cc.po.Role;
import com.cc.po.Users;
import com.cc.service.IRoleService;
import com.cc.util.Page;
@Service("RoleService")
public class RoleServiceImpl implements IRoleService{
	@Autowired
	private IRoleDao ir;
	@Autowired
	private IUsersDao iu;
	@Autowired
	private IMenuDao im;
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long selcount(Role role) {
		String hql="select count(r.id) from Role r where 1=1 ";
		Map<String,Object> params=new HashMap<String,Object>();
		if(role.getName()!=null){
			hql+="and r.name like :name ";
			params.put("name","%"+role.getName()+"%");
		}
		Long l=ir.count(hql, params);
		return l;
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DataGrid<Role> selall(Role role) {
		DataGrid<Role> data=new DataGrid<Role>();
		String hql="from Role r where 1=1 ";
		Map<String,Object> params=new HashMap<String,Object>();
		if(role.getName()!=null){
			hql+="and r.name like:name ";
			params.put("name","%"+role.getName()+"%");
		}
		hql+="order by r.id";
		Page page=new Page(role.getPage(),role.getRows());
		List<Role> listrole=ir.getAllByPage(hql, params, page.getBegin(),page.getPageSize());
		if(listrole!=null && listrole.size()>0){
			data.setRows(listrole);
			long l=selcount(role);
			data.setTotal(l);
		}
		return data;
	}
	
	//删除
	@Transactional
	@Override
	public boolean delRoleMenu(int roleid) {
		boolean res=true;
		try {
			Role role=ir.get(roleid);
			if(role!=null){
				role.getMenus().clear();
			}
		} catch (Exception e) {
			res=false;
			e.printStackTrace();
		}
		return res;
	}
	
	//新增角色对应的菜单
	@Transactional
	@Override
	public boolean addRoleMenu(int roleid, String mids) {
		boolean res=true;
		Menu m=null;
		try {
			System.out.println("2");
			//删除旧的角色对应的菜单
			if(delRoleMenu(roleid)){
				//获得角色实体
				Role role=ir.get(roleid);
				System.out.println("3");
				if(role !=null && !mids.equals("")){
					//将传回来的String类型转换为数组.
					String[] mid=mids.split(",");
					for(String s:mid){
						//获得每一个Menuid对应的实体
						m=im.get(s);
						System.out.println(m.getId());
						role.getMenus().add(m);
					}
				}
			}
		} catch (Exception e) {
			res=false;
			e.printStackTrace();
		}
		return res;
	}
	
	//新增角色
	@Transactional
	@Override
	public boolean add(Role role) {
		return ir.add(role)>0?true:false;
	}
	
	//根据id查询角色
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Role findroleid(int roleid) {
		return ir.get(roleid);
	}
	
	//角色修改
	@Transactional
	@Override
	public boolean updRole(Role role) {
		System.out.println(ir.upd(role));
		return ir.upd(role)>0?true:false;
	}
	
	//查询所有角色
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Role> selRole() {
		String hql="from Role  ";
		return ir.getAll(hql, null);
	}
	
	//为用户对应的角色打勾
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Role> findGrantRoleList(int uid) {
		 List<Role> listrole=selRole();
		 Set<Role> setrole=findRoleList(uid);
		 for(Role r:listrole){
			 for(Role o:setrole){
				 if(r.getId()==o.getId()){
					 r.setCk(true);
				 }
			 }
		 } 
		return listrole;
	}
	
	//查询用户对应的角色集合
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Set<Role> findRoleList(int uid) {
		Set<Role> setrole=null;
		Users u=iu.get(uid);
		if(u!=null){
			setrole=u.getRoles();
		}
		return setrole;
	}
	
	

}
