package com.hongzhi.zswh.app_v3.login.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v3.login.entity.LoginParam;
import com.hongzhi.zswh.app_v3.login.service.V3LoginService;
import com.hongzhi.zswh.util.basic.ObjectUtil;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 26, 2016    2:00:38 PM
 */
@Controller
@RequestMapping("/appLogin_V3")
public class V3LoginController {
	
	@Autowired
	private V3LoginService  login;
	
	
	
	@ResponseBody
	@RequestMapping("/login")
	public  String  login(LoginParam loginParam,HttpSession session){
		return  login.login(loginParam,session) ;
	}
	
	@ResponseBody
	@RequestMapping( "/register")
	public String register(LoginParam loginParam,HttpSession session){
		return  login.register(loginParam,session) ;
	}
	

}
