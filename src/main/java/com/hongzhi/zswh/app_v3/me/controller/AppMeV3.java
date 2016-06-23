package com.hongzhi.zswh.app_v3.me.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v3.me.service.AppMeV3Servce;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 21, 2016    3:56:49 PM
 */
@Controller
@RequestMapping("/appMe_V3")
public class AppMeV3 {


	@Autowired
	private AppMeV3Servce meBiz;
	@Autowired
	private DictionaryUtil dic;
	@Autowired
	private SessionUtil sess;
	
	@ResponseBody
	@RequestMapping("/loadMe")
	public String loadMe(HttpSession session , String session_id ){
		SessionProperty properties ;
		String language = "zh"; 
		try {
			properties = sess.sessionEffective(session,session_id, " appMe v3 loadMe ");
			language = properties.getLanguage();
			return ObjectUtil.jsonOut(  meBiz.loadMe(properties)  );
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	@ResponseBody
	@RequestMapping("/following")
	public String myFollowing(HttpSession session , String session_id ){
		SessionProperty properties ;
		String language = "zh"; 
		try {
			properties = sess.sessionEffective(session,session_id, " appMe v3 following");
			language = properties.getLanguage();
			return ObjectUtil.jsonOut(  meBiz.following(properties)  );
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/followers")
	public String myFollowers(HttpSession session , String session_id ){
		SessionProperty properties ;
		String language = "zh"; 
		try {
			properties = sess.sessionEffective(session,session_id, " appMe v3 followers");
			language = properties.getLanguage();
			return ObjectUtil.jsonOut(  meBiz.followers(properties)  );
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/follow")
	public String myFollow(HttpSession session , String session_id ,String  follow_user_id ){
		SessionProperty properties ;
		String language = "zh"; 
		try {
			properties = sess.sessionEffective(session,session_id, " appMe v3 follow");
			language = properties.getLanguage();
			ExcepUtil.verify(follow_user_id, "1054");
			return ObjectUtil.jsonOut(  meBiz.follow(properties,follow_user_id)  );
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	@ResponseBody
	@RequestMapping("/unfollow")
	public String unFollow(HttpSession session , String session_id ,String  unfollow_user_id ){
		SessionProperty properties ;
		String language = "zh"; 
		try {
			properties = sess.sessionEffective(session, session_id, " appMe v3 unfollow");
			language = properties.getLanguage();
			ExcepUtil.verify(unfollow_user_id, "1055");
			return ObjectUtil.jsonOut(meBiz.unfollow(properties, unfollow_user_id));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	
	
//	@ResponseBody
//	@RequestMapping("/updatePassword")
//	public String updatePassword(String user_id,String platform_id,String phone,String password,String new_password_one,String new_password_two){
//		String  param_name="user_id,platform_id,phone,password,new_password_one,new_password_two";
//		String[] param_ary={user_id,platform_id,phone,password,new_password_one,new_password_two};
//		String  errorcode="1000,1024,1006,1007,1007,1007";
//		Map<String,String> params=new HashMap<>();
//		try {
//			params = dictionaryUtil.checkParams(param_name, param_ary, errorcode);
//			return meBiz.updatePassword(params);
//		} catch (HongZhiException e) {
//			params.put("code", e.getCode());
//		}
//		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
//	}
//	
//	@ResponseBody
//	@RequestMapping("/loadMeCompetitions")
//	public String loadMeCompetions(String user_id,String language_abbreviation,String platform_id){
//		String  param_name="user_id,language_abbreviation,platform_id";
//		String[] param_ary={user_id,language_abbreviation,platform_id};
//		String  errorcode="1000,0,1024";
//		Map<String, String> params = new HashMap<>();
//		try {
//			params = dictionaryUtil.checkParams(param_name, param_ary,errorcode);
//			return meBiz.loadMeCompetitons(params);
//		} catch (HongZhiException e) {params.put("code", e.getCode());
//		}
//		return dictionaryUtil.appOut(params.get("code"),params.get("language_abbreviation"), null);
//	}
//	
//	@ResponseBody
//	@RequestMapping("/loadMeSportsCamps")
//	public String loadMeSportsCamps(String user_id,String language_abbreviation,String platform_id,String page_number,String page_size){
//		String  param_name="user_id,language_abbreviation,platform_id,page_number,page_size";
//		String[] param_ary={user_id,language_abbreviation,platform_id,page_number,page_size};
//		String  errorcode="1000,0,1024,0,0";
//		Map<String, String> params = new HashMap<>();
//		try {
//			params = dictionaryUtil.checkParams(param_name, param_ary,errorcode);
//			return meBiz.loadMeSportsCamps(params);
//		} catch (HongZhiException e) {
//			params.put("code", e.getCode());
//		}
//		return dictionaryUtil.appOut(params.get("code"),params.get("language_abbreviation"), null);
//	}
//	
//	@ResponseBody
//	@RequestMapping("/loadMeDetail")
//	public String loadMeDetail(String user_id,String language_abbreviation,String platform_id,String session_id){
//		String  param_name="user_id,language_abbreviation,platform_id,session_id";
//		String[] param_ary={user_id,language_abbreviation,platform_id,session_id};
//		String  errorcode="1000,0,1024,0";
//		Map<String, String> params = new HashMap<>();
//		try {
//			params = dictionaryUtil.checkParams(param_name, param_ary,errorcode);
//			return meBiz.loadMeDetail(params);
//		} catch (HongZhiException e) {
//			params.put("code", e.getCode());
//		}
//		return dictionaryUtil.appOut(params.get("code"),params.get("language_abbreviation"), null);
//	}
//	
//	@ResponseBody
//	@RequestMapping("/saveMeDetail")
//	public String saveMeDetail(String user_id, String language_abbreviation, String platform_id, String session_id, String birthdate,String gender,String user_mail,String user_name, String nickname) {
//		String param_name = " user_id, language_abbreviation, platform_id, session_id, user_name, nickname,gender, birthdate, user_mail ";
//		String[] param_ss = { user_id, language_abbreviation, platform_id, session_id, user_name, nickname,gender, birthdate, user_mail  };
//		String errorcode = "1000,0,1024,0,0,0,0,0,0";
//		Map<String, String> params = new HashMap<>();
//		try {
//			params = dictionaryUtil.checkParams(param_name, param_ss, errorcode);
//			return meBiz.saveMeDetail(params);
//		} catch (HongZhiException e) {
//			params.put("code", e.getCode());
//		}
//		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), null);
//	}
//	
//	@ResponseBody
//	@RequestMapping("/loadMeAddress")
//	public String loadMeAddress(String user_id,String language_abbreviation,String platform_id,String Session_id){
//		String param_name = "user_id,language_abbreviation,platform_id,Session_id";
//		String[] param_ss = {user_id,language_abbreviation,platform_id,Session_id};
//		String errorcode = "1000,0,1024,0";
//		Map<String, String> params = new HashMap<>();
//		try {
//			params = dictionaryUtil.checkParams(param_name, param_ss, errorcode);
//			return meBiz.loadMeAddress(params);
//		} catch (HongZhiException e) {
//			params.put("code", e.getCode());
//		}
//		return dictionaryUtil.appOut(params.get("code"),params.get("language_abbreviation"), null);
//	}
//		
//	@ResponseBody
//	@RequestMapping("/insertAddress")
//	public String insertAddress(String language_abbreviation,String Session_id,String user_id,String city_code,String detail_address,String receiver_name,String receiver_phone,String is_default){
//		String param_name = "user_id,language_abbreviation,Session_id,city_code,detail_address,receiver_name,receiver_phone,is_default";
//		String[] param_ss = {user_id,language_abbreviation,Session_id,city_code,detail_address,receiver_name,receiver_phone,is_default};
//		String errorcode = "1000,0,0,0,0,0,0,0,0";
//		Map<String,String> params=new HashMap<>();
//		try {
//			params = dictionaryUtil.checkParams(param_name, param_ss, errorcode);
//			return meBiz.insertAddress(params);
//		} catch (HongZhiException e) {
//			params.put("code", e.getCode());
//		}
//		return dictionaryUtil.appOut(params.get("code"),params.get("language_abbreviation"), null);
//	}
//	
//	
//	
//	
//	@ResponseBody
//	@RequestMapping("/deleteMeAddress")
//	public String deleteMeAddress(String language_abbreviation,String Session_id,String shipping_id){
//		String param_name="language_abbreviation,Session_id,shipping_id";
//		String[] param_ss = {language_abbreviation,Session_id,shipping_id};
//		String errorcode = "0,0,0";
//		Map<String,String> params=new HashMap<>();
//		try {
//			params = dictionaryUtil.checkParams(param_name, param_ss, errorcode);
//			return meBiz.deleteAdress(params);
//		} catch (HongZhiException e) {
//			params.put("code", e.getCode());
//		}
//		return dictionaryUtil.appOut(params.get("code"),params.get("language_abbreviation"), null);
//	}
//	
//	@ResponseBody
//	@RequestMapping("/loadUpdateAddress")
//	public String loadUpdateAddress(String language_abbreviation,String Session_id, String shipping_id, String platform_id) {
//		String param_name = "language_abbreviation,Session_id,shipping_id,platform_id";
//		String[] param_ss = { language_abbreviation, Session_id, shipping_id, platform_id};
//		String errorcode = "0,0,0,1024";
//		Map<String, String> params = new HashMap<>();
//		try {
//			params = dictionaryUtil.checkParams(param_name, param_ss, errorcode);
//			return meBiz.loadAddress(params);
//		} catch (HongZhiException e) {
//			params.put("code", e.getCode());
//		}
//		return dictionaryUtil.appOut(params.get("code"),params.get("language_abbreviation"), null);
//	}
//	
//	@ResponseBody
//	@RequestMapping("/updateAddress")
//	public String UpdateAddress(String language_abbreviation,String Session_id,String shipping_id,String user_id,String city_code,String detail_address,String receiver_name,String receiver_phone,String is_default){
//		String param_name = "user_id,language_abbreviation,shipping_id,Session_id,city_code,detail_address,receiver_name,receiver_phone,is_default";
//		String[] param_ss = {user_id,language_abbreviation,shipping_id,Session_id,city_code,detail_address,receiver_name,receiver_phone,is_default};
//		String errorcode = "1000,0,0,0,0,0,0,0,0";
//		Map<String,String> params=new HashMap<>();
//		try {
//			params = dictionaryUtil.checkParams(param_name, param_ss, errorcode);
//			return meBiz.saveAddress(params);
//		} catch (HongZhiException e) {
//			params.put("code", e.getCode());
//		}
//		return dictionaryUtil.appOut(params.get("code"),params.get("language_abbreviation"), null);
//	}

}

