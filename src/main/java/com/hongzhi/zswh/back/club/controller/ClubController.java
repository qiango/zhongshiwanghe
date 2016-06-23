package com.hongzhi.zswh.back.club.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.club.entity.ParamObj;
import com.hongzhi.zswh.back.club.service.ClubService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 22, 2016    3:35:25 PM
 */
@Controller
@RequestMapping("/club")
public class ClubController {
	
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private ClubService backClubService  ;
	@Autowired
	private SessionUtil sessionUtil;
	
	@ResponseBody
	@RequestMapping("/list")
	public  String listClub(ParamObj paramObj,HttpSession session){
		String  param_name = "club_name,page_number,page_size";
		String   errorcode = "0,0,0";
		Map<String,String> params=new HashMap<>();
		try {
				params=dictionaryUtil.checkParams(param_name, errorcode , paramObj , session , "club/list");
				return backClubService.listClubByPage(params);
			} catch (HongZhiException e) {
				return dictionaryUtil.appOut( e.getCode(), params.get("language_abbreviation") , null);
			}
	}


	@ResponseBody
	@RequestMapping("/insertLoad")
	public  String insertLoad(ParamObj paramObj , HttpSession session){
		String  param_name = "language_abbreviation";
		String   errorcode = "0";
		Map<String,String> params=new HashMap<>();
		try {
				params=dictionaryUtil.checkParams(param_name, errorcode, paramObj, session, "club/insertLoad");
				return backClubService.insertLoad(params);
			} catch (HongZhiException e) {
				return dictionaryUtil.appOut( e.getCode(), params.get("language_abbreviation") , null);
			}
	}
	
	@ResponseBody
	@RequestMapping("/insertSave")
	public  String insertSave(HttpSession session,ParamObj paramObj){
		String  param_name = "id,language_id,club_name,club_description,club_create_date,club_qq,club_applicant_name,club_name_short";
		String   errorcode = "1031,1025,0,0,0,0,0,0";
//		User user = (User) session.getAttribute("user");
		Map<String,String> params=new HashMap<>();
		try {
				params=dictionaryUtil.checkParams(param_name, errorcode, paramObj, session ,"club/insertSave/"+ObjectUtil.getProperty(paramObj.getClub_name(), "null"));
				return backClubService.insertSave(params);
			} catch (HongZhiException e) {
				return dictionaryUtil.appOut( e.getCode(), params.get("language_abbreviation") , null);
			}
	}
	
	@ResponseBody
	@RequestMapping("/modifyLoad")
	public  String modifyLoad(ParamObj paramObj , HttpSession session){
		String  param_name = "club_id";
		String   errorcode = "1021";
		Map<String,String> params=new HashMap<>();
		try {
				params=dictionaryUtil.checkParams(param_name, errorcode, paramObj, session ,"club/modifyLoad/"+ObjectUtil.getProperty(paramObj.getClub_id(), "null"));
				return backClubService.modifyLoad(params);
			} catch (HongZhiException e) {
				return dictionaryUtil.appOut( e.getCode() , params.get("language_abbreviation") , null);
			}
	}
	
	@ResponseBody
	@RequestMapping("/modifySave")
	public  String modifySave(ParamObj paramObj,HttpSession session){
		String  param_name = "club_status,club_applicant_name,club_id,id,language_id,club_name,club_description,club_create_date,club_qq,club_name_short";
		String   errorcode = "0,0,1021,1031,1025,0,0,0,0,0";
		Map<String,String> params=new HashMap<>();
		try {
				params=dictionaryUtil.checkParams(param_name, errorcode, paramObj, session , "club/modfiySave/"+ObjectUtil.getProperty(paramObj.getClub_id(), "null"));
				return backClubService.modifySave(params);
			} catch (HongZhiException e) {
				return dictionaryUtil.appOut( e.getCode() , params.get("language_abbreviation") , null);
			}
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public  String delete(ParamObj paramObj , HttpSession session){
		String  param_name = "club_id";
		String   errorcode = "1021";
		Map<String,String> params=new HashMap<>();
		try {
				params=dictionaryUtil.checkParams(param_name, errorcode,paramObj, session ,"club/delete/"+ObjectUtil.getProperty(paramObj.getClub_id(), "null"));
				return backClubService.delete(params);
			} catch (HongZhiException e) {
				return dictionaryUtil.appOut( e.getCode() , params.get("language_abbreviation") , null);
			}
	}
	
	
	@ResponseBody
	@RequestMapping("/sportscamp")
	public  String sportscamp(HttpSession session,String club_id) {
		try {
			SessionProperty  sp  = sessionUtil.getProperty(session, "club/sportscamp");
			dictionaryUtil.verifyData(club_id, "1021");
			return  backClubService.listSportCamp(sp,club_id);
		} catch (HongZhiException e) {
			return 	dictionaryUtil.appOut(e.getCode(), "", "");
		}
	}
	
	@ResponseBody
	@RequestMapping("/sportscampSave")
	public  String sportscamp(HttpSession session,String club_id,String sports_camp_id) {
		try {
			SessionProperty  sp  = sessionUtil.getProperty(session, "club/sportscamp");
			dictionaryUtil.verifyData(club_id, "1021");
			dictionaryUtil.verifyData(sports_camp_id, "1029");
			return  backClubService.saveSportCamp(sp,club_id,sports_camp_id);
		} catch (HongZhiException e) {
			return 	dictionaryUtil.appOut(e.getCode(), "", "");
		}
	}
	
}
