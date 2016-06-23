package com.hongzhi.zswh.back.basic.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.basic.entity.ParamObj;
import com.hongzhi.zswh.back.basic.service.RoleService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 24, 2016    2:09:06 PM
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private DictionaryUtil dicUtil;
	@Autowired
	private RoleService roleService;
	@Autowired
	private SessionUtil sessionUtil;
	

	@ResponseBody
	@RequestMapping("/list")
	public String roleList(HttpSession session,String role_name){
//		User user=(User) session.getAttribute("user");
		SessionProperty sp = null;
		try {
//			dicUtil.verifyData(user, "1000");
			sp = sessionUtil.getProperty(session, "role/list");
		return 	roleService.list(sp.getLanguage(),role_name);
		} catch (HongZhiException e) {
			return dicUtil.appOut(e.getCode(), null, null);
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/insertSave")
	public String save(ParamObj paramObj,HttpSession session){
//		User user=(User) session.getAttribute("user");
		String  param_name = "role_name,remark";
		String   errorcode = "1009,0";
		Map<String,String> params=new HashMap<>();
		try {
				params=dicUtil.checkParams(param_name,  errorcode,paramObj,session,"role/newSave/"+ObjectUtil.getProperty(paramObj.getRole_name(), "null"));
				return roleService.newRoleSave(params);
			} catch (HongZhiException e) {
				params.put("code", e.getCode());
			}
		return dicUtil.appOut( params.get("code"), params.get("language") , null);
	}
	
	
	@ResponseBody
	@RequestMapping("/modifyLoad")
	public String modifyLoad(ParamObj paramObj,HttpSession session){
//		User user=(User) session.getAttribute("user");
		String  param_name = "role_id";
		String   errorcode = "1009";
		Map<String,String> params=new HashMap<>();
		try {
				params=dicUtil.checkParams(param_name, errorcode,paramObj,session,"role/modifyLoad/"+ObjectUtil.getProperty(paramObj.getRole_id(), "null"));
				return roleService.modifyLoad(params);
			} catch (HongZhiException e) {
				params.put("code", e.getCode());
			}
		return dicUtil.appOut( params.get("code"), params.get("language") , null);
	}
	
	
	@ResponseBody
	@RequestMapping("/modifySave")
	public String modifysave(ParamObj paramObj,HttpSession session){
//		User user=(User) session.getAttribute("user");
		String  param_name = "role_id,role_name,remark";
		String   errorcode = "1037,1009,0";
		Map<String,String> params=new HashMap<>();
		try {
				params=dicUtil.checkParams(param_name, errorcode,paramObj,session,"role/modifySave/"+ObjectUtil.getProperty(paramObj.getRole_id(), "null"));
				return roleService.modifyRoleSave(params);
			} catch (HongZhiException e) {
				params.put("code", e.getCode());
			}
		return dicUtil.appOut( params.get("code"), params.get("language") , null);
	}
	
	
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(ParamObj paramObj,HttpSession session){
//		User user=(User) session.getAttribute("user");
		String  param_name = "role_id";
		String   errorcode = "1037";
		Map<String,String> params=new HashMap<>();
		try {
				params=dicUtil.checkParams(param_name, errorcode,paramObj,session,"role/delete/"+ObjectUtil.getProperty(paramObj.getRole_id(), "null"));
				return roleService.delete(params);
			} catch (HongZhiException e) {
				return dicUtil.appOut(e.getCode(), params.get("language") , null);
			}
	}
	
	
	@ResponseBody
	@RequestMapping("/gear")
	public String gear( String role_id,HttpSession session) { 
		try {
			SessionProperty sp = sessionUtil.getProperty(session, "role/gear/"+ObjectUtil.getProperty(role_id, "null"));
			dicUtil.verifyData(role_id, "1037");
			return roleService.roleMenu(sp,role_id);
		} catch (HongZhiException e) {
			return dicUtil.appOut(e.getCode(), null , null);
		}
	}
	
	@ResponseBody
	@RequestMapping("/gearSave")
	public String gearSave(String role_id,String menus_ids,HttpSession session) {
		try {
			SessionProperty sp = sessionUtil.getProperty(session, "role/gearSave/"+ObjectUtil.getProperty(role_id, "null"));
			dicUtil.verifyData(role_id, "1037");
			dicUtil.verifyData(menus_ids, "1040");
			return roleService.roleMenuSave(sp,role_id,menus_ids);
		} catch (HongZhiException e) {
			return dicUtil.appOut(e.getCode(), null , null);
		}
	}
	
	
	

}
