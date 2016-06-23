package com.hongzhi.zswh.app_v2.profile.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v2.profile.entity.DeviceInformation;
import com.hongzhi.zswh.app_v2.profile.service.AppDeviceService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 6, 2016    4:06:22 PM
 */
@Controller
@RequestMapping("/appdeviceV2")
public class AppDevice {
	
	@Autowired
	private AppDeviceService appDeviceService;
	@Autowired
	private SessionUtil sessionUtil;
	@Autowired
	private DictionaryUtil dictionaryUtil;

	@ResponseBody
	@RequestMapping("/saveInfo") 
	public  String  saveDeviceInfo(DeviceInformation devInfo , HttpSession session ){
		try {
			SessionProperty sp = sessionUtil.getProperty(session, "appdeviceV2/saveInfo");
			return appDeviceService.saveDevInfo(devInfo,sp);
		} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode(), "", "");
		}
	}

}
