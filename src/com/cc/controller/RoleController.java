package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.DataGrid;
import com.cc.po.Role;
import com.cc.po.TempJSON;
import com.cc.service.IEasyUITreeService;
import com.cc.service.IRoleService;
import com.cc.util.JsonUtil;
import com.cc.util.SysStr;

@Controller
@RequestMapping("/RoleController")
public class RoleController {
		@Autowired
		private IRoleService irole;
		@Autowired
		private IEasyUITreeService iea;
		
		//角色页面展示
		@RequestMapping(value="/findRoleDG",produces="text/html;charset=utf-8")
		@ResponseBody
		public String findRoleDG(Role role){
			DataGrid<Role> data=irole.selall(role);
			return JsonUtil.writeJson(data);
		}
		
		//新增角色
		@RequestMapping(value="/addRole",produces="text/html;charset=utf-8")
		@ResponseBody
		public String addRole(Role role){
			
			TempJSON ts=new TempJSON();
			ts.setMsg(SysStr.addfail);
			
			if(irole.add(role)){
				ts.setMsg(SysStr.addsuccess);
				ts.setSuccess(true);
			}
			return JsonUtil.writeJson(ts);
		}
		//修改角色
		@RequestMapping(value="/updRole",produces="text/html;charset=utf-8")
		@ResponseBody
		public String updRole(Role role){
			TempJSON ts=new TempJSON();
			ts.setMsg(SysStr.editfail);
			if(irole.updRole(role)){
				ts.setMsg(SysStr.editsuccess);
				ts.setSuccess(true);
			}
			return JsonUtil.writeJson(ts);
		}
		
		//根据id查询Role
		@RequestMapping(value="/findRoleById",produces="text/html;charset=utf-8")
		@ResponseBody
		public String findRoleById(Role role){
			if(irole.findroleid(role.getId())!=null){
				return JsonUtil.writeJson(irole.findroleid(role.getId()));
			}
			return null;
		}
		
		//加载角色对应的树菜单
		@RequestMapping(value="/findRoleTree",produces="text/html;charset=utf-8")
		@ResponseBody
		public String findRoleTree(Role role){
			return JsonUtil.writeJson(iea.findRoleTreeList(role.getId()));
		}
		
		//给角色授予新的菜单
		@RequestMapping(value="/grant",produces="text/html;charset=utf-8")
		@ResponseBody
		public String grant(Role role,String mids){
			TempJSON ts=new TempJSON();
			ts.setMsg(SysStr.grantfail);
			if(irole.addRoleMenu(role.getId(), mids)){
				ts.setMsg(SysStr.grantsuccess);
				ts.setSuccess(true);
			}
			return JsonUtil.writeJson(ts);
		}
}
