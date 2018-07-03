package com.cc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.po.PageMenu;
import com.cc.po.Users;
import com.cc.service.IEasyUITreeService;
import com.cc.util.JsonUtil;
/**
 * @Author ChenXiang
 * @Date 2018/6/29,10:01
 */
@Controller
@RequestMapping("/EasyUITree")
public class EasyUITreeController {
	@Autowired
	private IEasyUITreeService ie;
	@Autowired
	private HttpSession session;
	
	//加载树菜单
	@RequestMapping(value="/loadTree",produces="text/html;charset=utf-8")
	@ResponseBody
	public String loadTree(){
		String json=null;
		//获得session中登陆过的用户
		Users u=(Users) this.session.getAttribute("login");
		
		if(u!=null){
			List<PageMenu> lpagemenu=ie.findTreeList(u);
			
			json=JsonUtil.writeJson(lpagemenu);
		}
		
		//返回一个json。这个json中包含了PageMenu对象。
		//PageMenu对象是我们人为的创建的一个符合平滑json格式的对象。
		return json;
	}
}
