package com.hongzhi.zswh.app.competition.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app.competition.service.AppCompetitionService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;



@Controller
@RequestMapping("/appCompetition")
public class AppCompetitionController {
	
	@Autowired
	private AppCompetitionService appCompetitionService;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	

	@ResponseBody
	@RequestMapping("/loadCompetition")
	public String loadCompetition(String user_id,String language_abbreviation,String platform_id,String page_number,String page_size){
		String  param_name="user_id,language_abbreviation,platform_id,page_number,page_size";
		String[] param_ary={user_id,language_abbreviation,platform_id,page_number,page_size};
		String  errorcode="0,0,1024,0,0";
		
		Map<String,String> params=new HashMap<>();
		try {
			params=dictionaryUtil.checkParams(param_name, param_ary, errorcode);
			return  appCompetitionService.loadCompetition(params);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
		
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	}
	
	@ResponseBody
	@RequestMapping("/loadCompetitionDetail")
	public String loadCompetitionDetail(String user_id,String language_abbreviation,String platform_id,String competition_id){
		String  param_name="user_id,language_abbreviation,platform_id,competition_id";
		String[] param_ary={user_id,language_abbreviation,platform_id,competition_id};
		String  errorcode="0,0,1024,1023";
		
		Map<String,String> params=new HashMap<>();
		try {
			params=dictionaryUtil.checkParams(param_name, param_ary, errorcode);
	        return  appCompetitionService.getCompetitionById(params);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
		
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	}
	
	@ResponseBody
	@RequestMapping("/isJoinCompetition")
	public String isJoinCompetition(String user_id,String language_abbreviation,String platform_id,String competition_id){
		String  param_name="user_id,language_abbreviation,platform_id,competition_id";
		String[] param_ary={user_id,language_abbreviation,platform_id,competition_id};
		String  errorcode="1000,0,1024,1023";
		
		Map<String,String> params=new HashMap<>();
		try {
			params=dictionaryUtil.checkParams(param_name, param_ary, errorcode);
			return  appCompetitionService.getJoinCompetition(params);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
		
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	}
}
