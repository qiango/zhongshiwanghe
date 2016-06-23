package com.hongzhi.zswh.back.competition.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.basic.entity.User;
import com.hongzhi.zswh.back.competition.entity.ParamComp;
import com.hongzhi.zswh.back.competition.service.CompetitionService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 23, 2016    2:42:51 PM
 */
@Controller
@RequestMapping("/competition")
public class CompetitionController {
	
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private CompetitionService competitionService;
	@Autowired
	private SessionUtil sessionUtil;

	//列表和查找
	@ResponseBody
	@RequestMapping("/list")
	public  String listCompetition(ParamComp paramComp,HttpSession session){
		String  param_name = "competion_name,page_number,page_size";
		String   errorcode = "0,0,0";
		Map<String,String> params=new HashMap<>();
		try {
				params=dictionaryUtil.checkParams(param_name, errorcode, paramComp, session , "competition/list");
				return  competitionService.listByPage(params);
			} catch (HongZhiException e) {
				return dictionaryUtil.appOut(e.getCode(), params.get("language_abbreviation") , null);
			}
	}
	
	//插入读取
	@ResponseBody
	@RequestMapping("/insertLoad")
	public  String insertLoad(HttpSession session){
		try {
			SessionProperty sp = sessionUtil.getProperty(session, "competition/insertLoad");
				return competitionService.insertLoad(sp);
			} catch (HongZhiException e) {
				return dictionaryUtil.appOut( e.getCode(), null , null);
			}
	}
	
	//插入保存
	@ResponseBody
	@RequestMapping("/insertSave")
	public  String insertSave(ParamComp paramComp,HttpSession session){
//		User user=(User) session.getAttribute("user");
//		String user_id=user.getUser_id().toString();
		String  param_name = "submit_file,user_id,platform_id,competition_name,competition_level,registration_start_date,registration_end_date,competition_start_date,competition_end_date,compertition_live_status";
		String   errorcode = "0,0,0,1022,0,0,0,0,0,0";
		Map<String,String> params=new HashMap<>();
		try {
				params=dictionaryUtil.checkParams(param_name, errorcode, paramComp, session , "competition/insertSave/"+ObjectUtil.getProperty(paramComp.getCompetion_name(), "null"));
				params.put("competition_publicity_pictures", params.get("submit_file"));
				return competitionService.insertSave(params);
			} catch (HongZhiException e) {
				return dictionaryUtil.appOut( e.getCode() , params.get("language_abbreviation") , null);
			}
	}
	
	//删除
	@ResponseBody
	@RequestMapping("/delete")
	public  String delete(ParamComp paramComp,HttpSession session){
		String  param_name = "competition_id";
		String   errorcode = "1023";
		Map<String,String> params=new HashMap<>();
		try {
				params=dictionaryUtil.checkParams(param_name, errorcode, paramComp , session ,"competition/delete/"+ObjectUtil.getProperty(paramComp.getCompetition_id(), "null"));
				return competitionService.delete(params);
			} catch (HongZhiException e) {
				return dictionaryUtil.appOut( e.getCode() , params.get("language_abbreviation") , null);
			}
	}
	
	//修改加载
	@ResponseBody
	@RequestMapping("/modifyLoad")
	public  String modifyLoad(ParamComp paramComp , HttpSession session){
		String  param_name = "competition_id";
		String   errorcode = "1023";
		Map<String,String> params=new HashMap<>();
		try {
				params=dictionaryUtil.checkParams(param_name, errorcode, paramComp, session , "compeition/modifyLoad/"+ObjectUtil.getProperty(paramComp.getCompetition_id(), "null"));
				return competitionService.modifyLoad(params);
			} catch (HongZhiException e) {
				return dictionaryUtil.appOut( e.getCode() , params.get("language_abbreviation") , null);
			}
	}
	
	//修改保存
	@ResponseBody
	@RequestMapping("/modifySave")
	public  String modifySave(ParamComp paramComp,HttpSession session){
//		User user = (User) session.getAttribute("user");competition_publicity_pictures
//		String user_id=user.getUser_id().toString();submit_file
		String  param_name = "id,competition_id,user_id,competition_description,submit_file,platform_id,competition_name,competition_level,registration_start_date,registration_end_date,competition_start_date,competition_end_date,compertition_live_status";
		String   errorcode = "0,1023,0,0,0,0,1022,0,0,0,0,0,0";
		Map<String,String> params=new HashMap<>();
		try {
				params=dictionaryUtil.checkParams(param_name, errorcode, paramComp, session,"compeition/modifySave/"+ObjectUtil.getProperty(paramComp.getCompetition_id(), "null"));
				params.put("competition_publicity_pictures", params.get("submit_file"));
				return competitionService.modifySave(params);
			} catch (HongZhiException e) {
				return dictionaryUtil.appOut( e.getCode() , params.get("language_abbreviation") , null);
			}
	}
	
	@ResponseBody
	@RequestMapping("/changeStatus")
	public String changeStatus(ParamComp paramComp,HttpSession session){
		String  param_name = "competition_id,new_status";
		String   errorcode = "0,0";
		Map<String,String> params=new HashMap<>();
		try {
				params=dictionaryUtil.checkParams(param_name, errorcode, paramComp, session , "compeition/changeStatus/"+ObjectUtil.getProperty(paramComp.getCompetition_id(), "null"));
				return competitionService.changeStatus(params);
			} catch (HongZhiException e) {
				return dictionaryUtil.appOut( e.getCode() , params.get("language_abbreviation") , null);
			}
	}
	
	
	
	
}
	


