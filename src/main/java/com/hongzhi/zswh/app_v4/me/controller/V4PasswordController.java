package com.hongzhi.zswh.app_v4.me.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v4.me.entity.V4PasswordParams;
import com.hongzhi.zswh.app_v4.me.service.V4PasswordService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 12, 2016    4:10:42 PM
 */
@Controller
@RequestMapping("/v4/password")
public class V4PasswordController {
	
	@Autowired
	private V4PasswordService  passwordService;
	@Autowired
	private DictionaryUtil dic;
	
	@ResponseBody
	@RequestMapping("/reset")
	public String passwordReset(V4PasswordParams  params){
		try {
			return  ObjectUtil.jsonOut( passwordService.reset(params) );
		} catch (HongZhiException e) {
			return  ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), "zh") );
		}
	}

}
