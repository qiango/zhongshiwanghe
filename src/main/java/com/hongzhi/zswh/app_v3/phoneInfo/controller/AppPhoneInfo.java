package com.hongzhi.zswh.app_v3.phoneInfo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v2.profile.entity.DeviceInformation;
import com.hongzhi.zswh.app_v2.profile.service.AppDeviceService;
import com.hongzhi.zswh.app_v3.phoneInfo.entity.DeviceInfo;
import com.hongzhi.zswh.app_v3.phoneInfo.service.V3DeviceInfoService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**
 * @author Saxon
 * create date: 2016-04-26 15:53
 */
@Controller
@RequestMapping("/appPhoneInfo_V3")
public class AppPhoneInfo {

	@Autowired
	private DictionaryUtil dic;
	@Autowired
	private SessionUtil sess;
	@Autowired
	private V3DeviceInfoService dev;

	@ResponseBody
	@RequestMapping("/getPhoneInfo")
	public String getPhoneInfo(HttpSession session, DeviceInfo deviceInfo ) {
		SessionProperty properties ;
		String language = "zh";
		try {
			properties = sess.sessionEffective(session, deviceInfo.getSession_id()," subscribe Sprots camp  ");
			language = properties.getLanguage();
			return ObjectUtil.jsonOut(  dev.saveInfo(properties,deviceInfo)  );
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}

}
