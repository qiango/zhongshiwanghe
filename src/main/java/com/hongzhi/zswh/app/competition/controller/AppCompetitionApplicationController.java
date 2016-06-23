package com.hongzhi.zswh.app.competition.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app.competition.service.AppCompetitionApplicationService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;




@Controller
@RequestMapping("/appCompetitionApplication")
public class AppCompetitionApplicationController {

	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private AppCompetitionApplicationService appCompetitionApplicationService;
	
	
	
	@ResponseBody
	@RequestMapping("/loadByCompetitionId")
	public String loadByCompetitionId(String user_id,String language_abbreviation,String platform_id,String competition_id){
		String  param_name="user_id,language_abbreviation,platform_id,competition_id";
		String[] param_ary={user_id,language_abbreviation,platform_id,competition_id};
		String  errorcode="1000,0,1024,1023";
		Map<String,String> params=new HashMap<>();
			try {
				params=dictionaryUtil.checkParams(param_name, param_ary, errorcode);
				return  appCompetitionApplicationService.loadByCompetitionId(params);
			} catch (HongZhiException e) {
				params.put("code", e.getCode());
			}
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	}
	
	
	@ResponseBody
	@RequestMapping("/saveCompetitionApplicationData")
	public String saveCompetitionApplicationData(String user_id,String language_abbreviation,String platform_id,String competition_id,String controls_ids,String competition_application_ids,String user_values,String title_names){
		String  param_name="user_id,language_abbreviation,platform_id,competition_id,controls_ids,competition_application_ids,user_values,title_names";
		String[] param_ary={user_id,language_abbreviation,platform_id,competition_id,controls_ids,competition_application_ids,user_values,title_names};
			
		String  errorcode="1000,0,1024,1023,1026,1022,0,0";
		Map<String,String> params=new HashMap<>();
			try {
				params=dictionaryUtil.checkParams(param_name, param_ary, errorcode);
				return  appCompetitionApplicationService.saveCompetitionApplicationData(params);
			} catch (HongZhiException e) {
				params.put("code", e.getCode());
			}
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
		
	}

	
	
	
	
	
	
	
	
}
