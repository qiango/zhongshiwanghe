package com.hongzhi.zswh.app_v2.profile.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v2.profile.service.AppUserProfileService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 6, 2016    1:10:19 PM
 */
@Controller
@RequestMapping("/appProfileV2")
public class AppProfile {
	
	@Autowired
	private AppUserProfileService appUserProfileService;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	
	@ResponseBody
	@RequestMapping("/saveProfilePic")
	public String  springUpload(HttpServletRequest request,String user_id) throws IllegalStateException, IOException {
		try {
			dictionaryUtil.verifyData(user_id, "1000");
			return appUserProfileService.profilePicUpload(request, user_id);
		} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode(), "" ,"");
		}

    }
	
	
	
	

}
