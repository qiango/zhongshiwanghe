package com.hongzhi.zswh.app_v3.advertising.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v3.advertising.service.AppAdvertisingService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**
 * ClassName: AppAdvertising
 * @Description: TODO
 * @author Saxon Zhong
 * @date 2016年4月26日
 */
@Controller
@RequestMapping("/appAdvertising_V3")
public class AppAdvertising {

	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	@Autowired
	private AppAdvertisingService appAdvertisingService;
	
	@ResponseBody
	@RequestMapping("/loadAdvertising")
	public String loadAdvertising(String advertising_id, String platform_id) {
		String param_name = "advertising_id,platform_id";
		String[] param_ary = { advertising_id, platform_id };
		String  errorcode="0,1024";
		
		Map<String,String> params=new HashMap<>();
		try {
			params = dictionaryUtil.checkParams(param_name, param_ary, errorcode);
			Map<String, Object> map = appAdvertisingService.queryAdvertisingById(advertising_id);
			return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , map);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	}

}
