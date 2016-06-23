package com.hongzhi.zswh.back.basic.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.basic.entity.UserInfo;
import com.hongzhi.zswh.back.basic.service.UserService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : enjun.zhu 
 * Creat time : Mar 24, 2016    17:31 PM
 */

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private SessionUtil sessionUtil;
	
	@ResponseBody
	@RequestMapping("/list")
	public String userList( String user_name,  String page_number,String page_size, HttpSession session ) {
//		User user= (User) session.getAttribute("user");
		try {
//			dictionaryUtil.verifyData(user, "1000");
			SessionProperty sp = sessionUtil.getProperty(session, "/user/list") ;
			return userService.userList(sp,user_name,page_number,page_size);
		} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode(), "", null);
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/insertLoad") 
	public String toInsert (HttpSession session) {
//		User user= (User) session.getAttribute("user");
			try {
			SessionProperty sp = sessionUtil.getProperty(session, "/user/insertLoad") ;
//				dictionaryUtil.verifyData(user, "1000");
				return  userService.insertLoad(sp);
			} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode(), "", null);
			}
	}
	
	@ResponseBody
	@RequestMapping("/insertSave")
	public String insertSave (HttpSession session, UserInfo userInfo, String user_roles) {
//		User user= (User) session.getAttribute("user");
			try {
				SessionProperty sp = sessionUtil.getProperty(session, "user/insertSave/"+ObjectUtil.getProperty(userInfo.getPhone(), "null"));

				dictionaryUtil.verifyData(userInfo.getPhone(), "1006");
				dictionaryUtil.verifyData(userInfo.getUser_password(), "1007");
				dictionaryUtil.verifyData(userInfo.getPhone(), "1006");
				return userService.insertSave(userInfo , user_roles, sp );
			} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode(), "", null);
			}
	}
	
	@ResponseBody
	@RequestMapping("/modifyLoad")
	public String modifyLoad(HttpSession session, String user_id) {
//		User user= (User) session.getAttribute("user");
			try {
//				dictionaryUtil.verifyData(user, "1000");
				SessionProperty sp = sessionUtil.getProperty(session, "user/modifyLoad/"+ObjectUtil.getProperty(user_id, "null"));
				dictionaryUtil.verifyData(user_id, "1001");
				return userService.modifyLoad(user_id,sp);
			} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode(), "", null);
			}
	}
	
	@ResponseBody
	@RequestMapping("/modifySave")
	public String modifySave (HttpSession session, UserInfo userInfo ,String user_roles) {
//		User user= (User) session.getAttribute("user");
			try {
				SessionProperty sp = sessionUtil.getProperty(session, "user/modifySave/"+ObjectUtil.getProperty(userInfo.getUser_id(), "null"));
				dictionaryUtil.verifyData(userInfo, "1001");
				return userService.modifySave(userInfo,sp,user_roles);
			} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode(), "", null);
			}
	}

	@ResponseBody
	@RequestMapping("/delete")
	public String delete(HttpSession session, String user_id ) {
//		User user= (User) session.getAttribute("user");
			try {
				SessionProperty sp = sessionUtil.getProperty(session, "user/modifySave/"+ObjectUtil.getProperty(user_id, "null"));
				dictionaryUtil.verifyData(user_id, "1001");
				return userService.userDelete(user_id,sp);
			} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode(), "", null);
			}
	}
		
}
