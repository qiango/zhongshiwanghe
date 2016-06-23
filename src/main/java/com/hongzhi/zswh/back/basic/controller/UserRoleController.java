package com.hongzhi.zswh.back.basic.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.basic.entity.ParamObj;
import com.hongzhi.zswh.back.basic.service.UserRoleService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 24, 2016    10:59:33 AM
 */
@Controller
@RequestMapping("/userrole")
public class UserRoleController {
	
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private UserRoleService roleService;
	
		
	@ResponseBody
	@RequestMapping("/roleLoad") 
	public String listRolesOfUser(ParamObj paramObj,HttpSession session){
		String  param_name = "user_id";
//		User user=(User) session.getAttribute("user");
//		String[] param_ary = {user_id,(String) ObjectUtil.getProperty(user, user.getLanguage(), "zh")};
		String   errorcode = "1036";
		Map<String,String> params=new HashMap<>();
		try {
				params=dictionaryUtil.checkParams(param_name, errorcode , paramObj , session , "userrole/roleLoad"+ObjectUtil.getProperty(paramObj.getUser_id(), "null") );
				return roleService.roleOfUser(params);
			} catch (HongZhiException e) {
				params.put("code", e.getCode());
			}
		return dictionaryUtil.appOut( params.get("code"), params.get("language") , null);

		
	}
	
	
	@ResponseBody
	@RequestMapping("/roleSave") 
	public String roleSave(ParamObj paramObj, HttpSession session){
//		User user=(User) session.getAttribute("user");
		String  param_name = "user_id,role_ids";
		String   errorcode = "1036,1009";
		Map<String,String> params=new HashMap<>();
		try {
				params=dictionaryUtil.checkParams(param_name, errorcode, paramObj , session , "userrole/roleSave/"+ObjectUtil.getProperty(paramObj.getUser_id(), "null"));
				return roleService.updateUserRoles(params);
			} catch (HongZhiException e) {
				params.put("code", e.getCode());
			}
		return dictionaryUtil.appOut( params.get("code"), params.get("language") , null);
	}
	


}
