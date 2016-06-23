package com.hongzhi.zswh.app_v2.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v2.profile.service.AppVersion;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 6, 2016    4:44:41 PM
 */
@Controller
@RequestMapping("/appVersion")
public class AppVersionController {
	
	@Autowired
	private AppVersion appVersion;
	
	
	@ResponseBody
	@RequestMapping("/updateInfo")
	public String  updateInfo(){
		return appVersion.getVersionInfo();
	}

}
