package com.cc.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.IRoleDao;
import com.cc.dao.IUsersDao;
import com.cc.po.DataGrid;
import com.cc.po.Role;
import com.cc.po.Users;
import com.cc.service.IUsersService;
import com.cc.util.Page;
import com.cc.util.SysStr;

@Service("use")
public class UsersServiceimpl implements IUsersService{
	
	@Autowired
	private IUsersDao usedao;
	@Autowired
	private IRoleDao irdao;
	
	//登陆
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	@Override	
	public Users login(String lname, String lpass) {
		Map<String,Object> params=new HashMap<String,Object>();
		String hql="from Users U where U.lname=:lname and U.lpass=:lpass and U.isdisable != 0";
		params.put("lname",lname);
		params.put("lpass",lpass);
		Users u=usedao.get(hql, params);
		if(u!=null){
			return u;
		}
		return null;
	}
	
	//查询总数
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long findUsersCount(Users u) {
		Long l=0l;
		Map<String,Object> params=new HashMap<String,Object>();
		String hql="select count(u.id) from Users u where 1=1 and u.lname!='"+SysStr.admin+"'and u.isdisable != 0 ";
		if(u.getRname()!=null){
			hql+="and u.rname like :rname ";
			params.put("rname","%"+u.getRname()+"%");
		}
		l=usedao.count(hql, params);
		return l;
	}
	
	//查询全部带分页
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public DataGrid<Users> findUsersDataGrid(Users u) {
		DataGrid<Users> data=new DataGrid<Users>();
		Map<String,Object> params=new HashMap<String,Object>();
		//超级管理员不需要查看自己的
		String hql="from Users u where 1=1 and u.lname !='"+SysStr.admin+"'and u.isdisable != 0 ";
		if(u.getRname()!=null){
			hql+="and u.rname like:rname ";
			params.put("rname", "%"+u.getRname()+"%");
		}
		hql+="order by u.id";
		Page page=new Page(u.getPage(),u.getRows());
		
		List<Users> list=usedao.getAllByPage(hql, params, page.getBegin(), page.getPageSize());
		
		if(list!=null){
			Long l=findUsersCount(u);
			data.setRows(list);
			data.setTotal(l);
		}
		return data;
	}
	
	//删除用户旧的对应关系
	@Override
	@Transactional
	public boolean delUsersRole(int uid) {
		boolean res=true;
		try {
			Users u=usedao.get(uid);
			if(u!=null){
				//清空用户对应的角色
				u.getRoles().clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	//新增用户对应的角色
	@Override
	@Transactional
	public boolean addUsersRole(int uid, String rids) {
		boolean res=true;
		try {
			if(delUsersRole(uid)){
				Users u=usedao.get(uid);
				if(u!=null&&rids!=null&&!rids.trim().equals("")){
					//这里面存放的是前端传过来的选中的角色id。
					String[] a=rids.split(",");
					for(String k:a){
						int intpar=Integer.parseInt(k);
						Role r=irdao.get(intpar);
						u.getRoles().add(r);
					}
				}
			}
		} catch (Exception e) {
			res=false;
			e.printStackTrace();
		}
		return res;
	}
	
	//用户新增
	@Override
	@Transactional
	public boolean addUsers(Users users) {
		return usedao.add(users)>0?true:false;
	} 
	
	//跟据id查询用户
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Users findUsersByid(int id) {
		return usedao.get(id);
	}
	
	//修改用户资料
	@Override
	@Transactional
	public boolean updUsers(Users users) {
		boolean res=false;		
		Map<String,Object> params = new HashMap<String, Object>();
		String hql="update Users u set u.rname=:rname," +
				"u.age=:age," +
				"u.gender=:gender," +
				"u.address=:address," +
				"u.phone=:phone," +
				"u.dept.id=:dept," +
				"u.post.id=:post," +
				"u.birthday=:birthday," +
				"u.card=:card," +
				"u.entrytime=:entrytime," +
				"u.isdisable=:isdisable " +
				"where u.id=:id";
		params.put("rname",users.getRname());
		params.put("age",users.getAge());
		params.put("gender",users.getGender());
		params.put("address",users.getAddress());
		params.put("phone",users.getPhone());
		params.put("dept",users.getDept().getId());
		params.put("post",users.getPost().getId());
		params.put("birthday",users.getBirthday());
		params.put("card",users.getCard());
		params.put("entrytime",users.getEntrytime());
		params.put("isdisable",users.getIsdisable());
		params.put("id",users.getId());
		
		if(usedao.execute(hql, params)>0){
			res=true;
		}
		return res;
	}
	
	//删除
	@Override
	@Transactional
	public boolean delUsers(Users users) {
		Map<String,Object> params=new HashMap<String,Object>();
		String hql="update Users u set u.isdisable=0 where id=:id ";
		params.put("id",users.getId());
		
		return usedao.execute(hql, params)>0?true:false;
	}
	//重置密码
	@Override
	@Transactional
	public boolean resetPass(Users users) {
		String hql="update Users u set u.lpass=:lpass where u.id=:id ";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("lpass",users.getLpass());
		params.put("id", users.getId());
		if(usedao.execute(hql, params)>0){
			return true;
		}
		return false;
	}
	
	//查询用户名是否被注册过
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public int findLoginName(String lname) {
		String hql="from Users u where u.lname=:lname ";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("lname",lname);
		Users u=usedao.get(hql, params);
		if(u!=null){
			return 1;
		}
		return 0;
	}

}
