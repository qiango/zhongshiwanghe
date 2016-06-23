package com.hongzhi.zswh.app_v3.verstion.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v3.verstion.service.VersionService;
import com.hongzhi.zswh.util.basic.ObjectUtil;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 26, 2016    10:43:15 AM
 */
@Controller
@RequestMapping("/appVersion_V3")
public class VersionController {
	
	
	@Autowired
	private VersionService  version;
	
	@ResponseBody
	@RequestMapping("updateInfo")
	public String  forceUpgrade ( ){
		return ObjectUtil.jsonOut(version.upgradeInfo());
	}

}
