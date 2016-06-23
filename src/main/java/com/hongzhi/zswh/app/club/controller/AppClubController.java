package com.hongzhi.zswh.app.club.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hongzhi.zswh.app.club.service.AppClubService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 29, 2016    11:30:01 AM
 */
@Controller
@RequestMapping("/appClub")
public class AppClubController {
	
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private AppClubService appClubService;
	
	@ResponseBody
	@RequestMapping("/loadClub")
	public String loadClub (String user_id,String language_abbreviation,String platform_id) {
		String  param_name="user_id,language_abbreviation,platform_id";
		String[] param_ary={user_id,language_abbreviation,platform_id};
		String  errorcode="1000,0,1024";
		
		Map<String,String> params=new HashMap<>();
		try {
			params=dictionaryUtil.checkParams(param_name, param_ary, errorcode);
			return appClubService.listClub(params);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
		
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	
	}
	
	
	@ResponseBody
	@RequestMapping("/loadSportsCamp")
	public String loadSportsCamp (String user_id,String language_abbreviation,String platform_id,String club_id,String session_id) {
		String  param_name="user_id,language_abbreviation,platform_id,club_id,session_id";
		String[] param_ary={user_id,language_abbreviation,platform_id,club_id,session_id};
		String  errorcode="1000,0,0,0,0";
		Map<String,String> params=new HashMap<>();
			try {
				params=dictionaryUtil.checkParams(param_name, param_ary, errorcode);
				return appClubService.listSportCamp(params);
			} catch (HongZhiException e) {
				params.put("code", e.getCode());
			}
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	
	}
	@ResponseBody
	@RequestMapping("/jumpSelectClub")
	public String jumpSelectClub(String user_id,String language_abbreviation,String platform_id) {
		String  param_name="user_id,language_abbreviation,platform_id";
		String[] param_ary={user_id,language_abbreviation,platform_id};
		String  errorcode="1000,0,0";
		Map<String, String> params = new HashMap<String, String>();
		try {
			params=dictionaryUtil.checkParams(param_name, param_ary, errorcode);
			return appClubService.jumpSelectClub(params);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
	return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	}
	
	
	@ResponseBody
	@RequestMapping("/saveUserSportCampAndClub")
	public String saveUserSportCampAndClub(String user_id,String language_abbreviation,String platform_id,String sports_camp_id,String club_id){
		String  param_name="user_id,language_abbreviation,platform_id,sports_camp_id,club_id";
		String[] param_ary={user_id,language_abbreviation,platform_id,sports_camp_id,club_id};
		String  errorcode="1000,0,1024,1029,1021";
		Map<String, String> params = new HashMap<String, String>();
		try {
			params=dictionaryUtil.checkParams(param_name, param_ary, errorcode);
			return appClubService.saveUserSportCampAndClub(params);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
	return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	}
	
	@ResponseBody
	@RequestMapping("/loadClubManage")
	public String loadClubManage(String user_id,String language_abbreviation,String platform_id,String session_id){
		String  param_name="user_id,language_abbreviation,platform_id,session_id";
		String[] param_ary={user_id,language_abbreviation,platform_id,session_id};
		String  errorcode="1000,0,1024,0";
		Map<String, String> params = new HashMap<String, String>();
		try {
			params=dictionaryUtil.checkParams(param_name, param_ary, errorcode);
			return appClubService.loadClubManage(params);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
	return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
		
	}

}
