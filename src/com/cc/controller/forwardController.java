package com.cc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/forwards")
//专门用来在内部转发的一个类
public class forwardController {
	
	
	@RequestMapping("/findUrl")
	public String url(String url){	
		String a=url.substring(0,url.lastIndexOf("."));
		return a;
	}
	
}
