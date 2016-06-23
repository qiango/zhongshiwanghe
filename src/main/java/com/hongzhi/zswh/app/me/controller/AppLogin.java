package com.hongzhi.zswh.app.me.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app.me.service.AppLogInService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 25, 2016    2:17:46 PM
 */
@Controller
@RequestMapping("/appLogin")
public class AppLogin {
	
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private AppLogInService loginBiz;

	
	@ResponseBody
	@RequestMapping("/login")
	public  String  login(String user_name,String language_abbreviation,String user_password,String phone,String platform_id,HttpSession session){
		String  param_name="user_name,language_abbreviation,platform_id,phone,user_password";
		String [] param_list={user_name,language_abbreviation,platform_id,phone,user_password};
		String  errorcode="0,0,1024,1006,1007";
		Map<String,String> params=new HashMap<>();
		try {
			params = dictionaryUtil.checkParams(param_name, param_list, errorcode);
			return loginBiz.selectUserInfoByPhone(params, session);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	}
	
	@ResponseBody
	@RequestMapping(value = "/register")
	public String register(String language_abbreviation,String user_password ,String phone,String platform_id ,HttpSession session){
		String   param_name="language_abbreviation,platform_id,phone,user_password";
		String [] params_list={language_abbreviation,platform_id,phone,user_password};
		String  errorcode="0,1024,1006,1007";
		Map<String,String> params=new HashMap<>();
		try {
			 params=dictionaryUtil.checkParams(param_name, params_list, errorcode);
			 return loginBiz.register(params,session);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	}
	

		
}
