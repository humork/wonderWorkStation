package com.cc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.TempJSON;
import com.cc.po.Users;
import com.cc.service.IDictionaryService;
import com.cc.service.IRoleService;
import com.cc.service.IUsersService;
import com.cc.util.JsonUtil;
import com.cc.util.MD5Util;
import com.cc.util.SysStr;

@Controller
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private IUsersService use;
	@Autowired
	private IDictionaryService idic;
	@Autowired
	private IRoleService iro;
	
	//登陆
	@RequestMapping("/login")
	@ResponseBody
	public String login(String lname,String lpass,HttpSession session){
		boolean res=false;
		//String lp=MD5Util.md5Encode(lpass);
		Users u=use.login(lname, lpass);
		if(u!=null){
			session.setAttribute("login", u);
			res=true;																											
		}
		String json=JsonUtil.writeJson(res);	
		return json;
	}
	
	//安全退出
	@RequestMapping("/exit")
	public String exit(HttpSession session){
		session.removeAttribute("login");
		return "login";
	}
	
	//验证输入的原始密码是否正确
	@RequestMapping("/ckPass")
	@ResponseBody
	public String ckPass(HttpSession session,Users uu){
		//取得登陆时的密码
		Users u=(Users) session.getAttribute("login");
		//将输入过来的原始密码转换为MD5加密后的格式
		String pass=MD5Util.md5Encode(uu.getOpass());
		//用登陆密码和输入进来的密码进行比较。这里比较的都是Md5加密后的格式
		boolean res=pass.equals(u.getLpass());
		String str=JsonUtil.writeJson(res);
		return str;
	}
	
	//修改密码
	@RequestMapping("/updPass")
	@ResponseBody
	public String updPass(String lpass,HttpSession session){
		TempJSON tj=new TempJSON();
		tj.setMsg(SysStr.editfail);
		//获得登陆用户的对象
		Users uu=(Users) session.getAttribute("login");
		//将用户的密码更改掉
		uu.setLpass(MD5Util.md5Encode(lpass));
		boolean res=use.updUsers(uu);
		if(res){
			tj.setSuccess(true);
			tj.setMsg(SysStr.editsuccess);
		}
		return JsonUtil.writeJson(tj);
	}
	
	//展示所有用户
	@RequestMapping(value="/findUsersDG",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findUsersDG(Users u){
		return JsonUtil.writeJson(use.findUsersDataGrid(u),new String[]{"dic"});
	}
	
	//重置密码
	@RequestMapping(value="/resetPass",produces="text/html;charset=utf-8")
	@ResponseBody
	public String resetPass(Users u){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.editfail);
		
		u.setLpass(MD5Util.md5Encode("123456"));
		if(use.resetPass(u)){
			ts.setSuccess(true);
			ts.setMsg(SysStr.editsuccess);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//用户新增
	@RequestMapping(value="/addusers",produces="text/html;charset=utf-8")
	@ResponseBody
	public String addusers(Users users){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.addfail);
		//将传过来的密码进行MD5加密
		users.setLpass(MD5Util.md5Encode(users.getLpass()));
		
		if(use.addUsers(users)){
			ts.setSuccess(true);
			ts.setMsg(SysStr.addsuccess);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//查询所有职员
	@RequestMapping(value="/findDeptList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findDeptList(){
		String json=
				JsonUtil.writeJson(idic.findDicListByName("部门"), new String[]{"dic"});
		return json;
	}
	//查询所有职务
	@RequestMapping(value="/findPostList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findPostList(){
		return JsonUtil.writeJson(idic.findDicListByName("职务"), new String[]{"dic"});
	}
	
	//修改用户资料
	@RequestMapping(value="/upd",produces="text/html;charset=utf-8")
	@ResponseBody
	public String upd(Users u){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.editfail);
		
		if(use.updUsers(u)){
			ts.setMsg(SysStr.editsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
	//根据id查询用户资料
	@RequestMapping(value="/findUsersid",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findUsersid(Users u){
		return JsonUtil.writeJson(use.findUsersByid(u.getId()));	
	}
	
	//删除用户
	@RequestMapping(value="/del",produces="text/html;charset=utf-8")
	@ResponseBody
	public String del(Users u){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.delfail);
		//如果删除成功
		if(use.delUsers(u)){
			ts.setMsg(SysStr.delsuccess);
			ts.setSuccess(true);	
		}
		return JsonUtil.writeJson(ts);
	}
	
	//用户名验证
	@RequestMapping(value="/findlname",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findlname(Users u){
		int i=use.findLoginName(u.getLname());
		return JsonUtil.writeJson(i);
	}
	
	//根据用户id,获取指派角色所需要的数据
	@RequestMapping(value="/findUsersRoleList",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findUsersRoleList(Users u){
		return JsonUtil.writeJson(iro.findGrantRoleList(u.getId()));
	}
	
	//指派角色
	@RequestMapping(value="/addUsersRole",produces="text/html;charset=utf-8")
	@ResponseBody
	public String addUsersRole(Users u){
		TempJSON ts=new TempJSON();
		ts.setMsg(SysStr.addfail);
		if(use.addUsersRole(u.getId(), u.getRids())){
			ts.setMsg(SysStr.addsuccess);
			ts.setSuccess(true);
		}
		return JsonUtil.writeJson(ts);
	}
	
}
