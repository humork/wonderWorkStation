package com.cc.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.cc.po.Menu;
import com.cc.po.PageMenu;
import com.cc.po.Users;
/**
 * @Author ChenXiang
 * @Date 2018/7/1,15:08
 */
public interface IEasyUITreeService {
	/**
	 * 获取对应节点的树节点
	 * @param p_u 用户实体
	 * @return	树集合
	 */
	public List<PageMenu> findTreeList(Users p_u);
	
	/**
	 * 获取全部树节点
	 * @return 所有树节点
	 */
	public List<PageMenu> findAllTreeList();
	/**
	 * 得到角色Id对应的数据实体集合
	 * @param Roleid 角色id
	 * @return	角色对应数据集合
	 */
	public Set<Menu> findRoleMenuList(int Roleid);
	/**
	 * 得到角色ID对应的树菜单页面集合
	 * @param Roleid 角色id
	 * @return 角色对应树菜单集合
	 */
	public List<PageMenu> findRoleTreeList(Integer Roleid);
	
	/**
	 * 将数据库中查出来的数据集合转换为树菜单需要的数据集合。
	 * @param lMenu
	 * @param lPageMenu
	 */
	public  void  converMenu(Collection<Menu> lMenu,List<PageMenu> lPageMenu);
}
