package com.cc.service;

import com.cc.po.DataGrid;
import com.cc.po.Users;

public interface IUsersService {
	
	/**
	 * 登陆
	 * @param lname 用户名
	 * @param lpass 密码
	 * @return
	 */
	public Users login(String lname,String lpass);
	/**
	 * 查询总数
	 * @param u
	 * @return
	 */
	public Long findUsersCount(Users u);
	
	/**
	 * 查询所有
	 * @param u
	 * @return
	 */
	public DataGrid<Users> findUsersDataGrid(Users u);
	
	/**
	 * 删除用户对应的角色
	 * @param uid
	 * @return
	 */
	public boolean delUsersRole(int uid);
	/**
	 * 新增用户对应的角色
	 * @param users
	 * @return
	 */
	public boolean addUsersRole(int uid, String rids);
	/***
	 * 新增用户
	 * @param users 实体
	 * @return 成功与否
	 */
	public boolean addUsers(Users users);
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public Users findUsersByid(int id);
	/***
	 * 修改用户
	 * @param users 实体
	 * @return 成功与否
	 */
	public boolean updUsers(Users users);
	/***
	 * 删除用户【改为禁用】
	 * @return 成功与否
	 */
	public boolean delUsers(Users users);
	/***
	 * 重置密码
	 * @param id
	 * @return 成功与否
	 */
	public boolean resetPass(Users users);
	/***
	 * 查询用户名是否被注册过
	 * @param lname 用户名
	 * @return 该用户名数量
	 */
	public int findLoginName(String lname);
}
