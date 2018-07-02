package com.cc.service.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
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
import com.cc.po.Menu;
import com.cc.po.PageMenu;
import com.cc.po.Role;
import com.cc.po.Users;
import com.cc.service.IEasyUITreeService;
import com.cc.util.SysStr;
@Service("easyservice")
public class EasyUITreeServiceimp implements IEasyUITreeService {
	@Autowired
	private IMenuDao im;
	@Autowired
	private IRoleDao ir;
	@Autowired
	private IUsersDao iu;
	
	//获取部分树节点。
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<PageMenu> findTreeList(Users p_u) {
		
		List<PageMenu> lpagemenu=new ArrayList<PageMenu>();
		Set<Menu> smenu=new HashSet<Menu>();
		if(p_u!=null){
			//如果登陆用户是admin的话
			if(p_u.getLname().equals(SysStr.admin)){
				//去查询出所有的树节点
				lpagemenu=findAllTreeList();
			}else{	
				//再次去查询一次该用户的资料
				p_u=iu.get(p_u.getId());
				//通过用户遍历角色
				for(Role r:p_u.getRoles()){
					//通过角色遍历菜单
					for(Menu m:r.getMenus()){
						smenu.add(m);
					}
				}
				//将Menu对象转为PageMenu对象
				converMenu(smenu,lpagemenu);
			}
		}
		return lpagemenu;
	}
	//查询所有树节点
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<PageMenu> findAllTreeList() {
		List<PageMenu> lpagemenu=null;
		List<Menu> menu=new ArrayList<Menu>();
		String hql="from Menu m ";
		menu=im.getAll(hql,null);
		if(menu!=null && menu.size()>0){
			lpagemenu=new ArrayList<PageMenu>();
			converMenu(menu,lpagemenu);
		}
		return lpagemenu;
	}
	
	//根据角色id查询菜单
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Set<Menu> findRoleMenuList(int Roleid) {
		Role role=ir.get(Roleid);
		Set<Menu> setmenu=role.getMenus();
		return setmenu;
	}
	
	//根据角色id查询树节点
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<PageMenu> findRoleTreeList(Integer Roleid) {
		//得到id对应的菜单节点
		Set<Menu> menu=findRoleMenuList(Roleid);
		//得到所有节点
		List<PageMenu> listmenu=findAllTreeList();
		if(menu!=null&&listmenu!=null){
			for(PageMenu p:listmenu){
				for(Menu m:menu){
					//当id对应的菜单节点存在，并且树节点中的url不为空.将其选中(将最低级节点选中)
					if(p.getId().equals(m.getId())&& p.getAttributes().get("url")!=null){
						
						p.setChecked(true);
					}
				}
			}
		}
		
		return listmenu;
	}
	//查询回来的数据实体转换为树节点需要的实体数据
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void converMenu(Collection<Menu> lMenu, List<PageMenu> lPageMenu) {
		PageMenu pm=null;
		if(lMenu!=null && lPageMenu!=null){
			for(Menu l:lMenu){
				pm=new PageMenu();
				Map<String,String> params=new HashMap<String,String>();
				params.put("url",l.getUrl());
				
				pm.setId(l.getId());
				pm.setPid(l.getPid());
				pm.setText(l.getText());
				pm.setAttributes(params);
				
				lPageMenu.add(pm);
			}
		}
	}
	

}
