package com.hongzhi.zswh.back.basic.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.basic.service.LoginService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 22, 2016    1:27:47 PM
 */
@Controller
public class LoginController {
	
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private LoginService backLoginService;
	
	
	@ResponseBody
	@RequestMapping("/login")
	public String login(String language_abbreviation,String user_login_name,String user_password,HttpSession session){
		String  param_name = "language_abbreviation,user_login_name,user_password";
		String[] param_ary = {language_abbreviation,user_login_name,user_password};
		String   errorcode = "0,1005,1007";
		Map<String,String> params=new HashMap<>();
		try {
				params=dictionaryUtil.checkParams(param_name, param_ary, errorcode);
				return backLoginService.login(params,session);
			} catch (HongZhiException e) {
				params.put("code", e.getCode());
			}
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	}
	
	@ResponseBody
	@RequestMapping("/init")
	public String init(HttpSession session){
//		String  param_name = "language_abbreviation";
//		String[] param_ary = {language_abbreviation};
//		String   errorcode = "0";
		Map<String,String> params=new HashMap<>();
		try {
//				params=dictionaryUtil.checkParams(param_name, param_ary, errorcode);
				dictionaryUtil.verifyData(session.getAttribute("user"), "1000" );
				return backLoginService.init(session);
			} catch (HongZhiException e) {
				params.put("code", e.getCode());
			}
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	}
	
	@ResponseBody
	@RequestMapping("/logout")
	public  String logOut(HttpSession session){
		session.removeAttribute("user");
		session.invalidate();
		return "<script type=\"text/javascript\">  location.href= url  </script>";
	}


}
