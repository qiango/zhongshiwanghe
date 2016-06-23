package com.hongzhi.zswh.app.me.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app.me.service.AppWorldCityService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;



@Controller
@RequestMapping("/appWorldCity")
public class AppWorldCity {

	@Autowired
	private DictionaryUtil dictionaryUtil;

	@Autowired
	private AppWorldCityService appWordlCityService;
	
	@ResponseBody
	@RequestMapping("/loadWorldCityAll")
	public String loadWorldCity(String session_id,String user_id,String language_abbreviation,String platform_id){
		String  param_name="session_id,user_id,language_abbreviation,platform_id";
		String[] param_ary={session_id,user_id,language_abbreviation,platform_id};
		String  errorcode="0,0,0,1024";
		Map<String,String> params=new HashMap<>();
		try {
			params = dictionaryUtil.checkParams(param_name, param_ary, errorcode);
			return appWordlCityService.loadWorldCity(params);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	
	}
	
}
