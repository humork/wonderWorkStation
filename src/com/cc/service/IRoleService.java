package com.cc.service;

import java.util.List;
import java.util.Set;

import com.cc.po.DataGrid;
import com.cc.po.Role;

public interface IRoleService {
	/**
	 * 查询总数
	 * @param role
	 * @return
	 */
	public Long selcount(Role role);
	/**
	 * 查询全部
	 * @param role
	 * @return
	 */
	public DataGrid<Role> selall(Role role);
	/**
	 * 根据角色id删除对应菜单
	 * @param roleid
	 * @return
	 */
	public boolean delRoleMenu(int roleid);
	/**
	 * 新增角色对应的菜单
	 * @param roleid
	 * @param mids
	 * @return
	 */
	public boolean addRoleMenu(int roleid,String mids);
	/**
	 * 新增角色
	 * @param roleid
	 * @return
	 */
	public boolean add(Role role);
	/**
	 * 根据id查询角色
	 * @param roleid
	 * @return
	 */
	public Role findroleid(int roleid);
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	public boolean updRole(Role role);
	/**
	 * 查询所有角色集合
	 * @return
	 */
	public List<Role> selRole();
	/**
	 * 查询已标记选中的角色集合
	 * @param uid
	 * @return
	 */
	public List<Role> findGrantRoleList(int uid);
	/**
	 * 根据用户id查询用户对应的角色集合
	 * @param uid
	 * @return
	 */
	public Set<Role>  findRoleList(int uid);
}
