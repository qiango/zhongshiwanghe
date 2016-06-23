package com.hongzhi.zswh.app_v4.mall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.hongzhi.zswh.app_v4.mall.service.V4SettingService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.date.DateFormat;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 23, 2016    1:48:27 PM
 */
@Controller
@RequestMapping("/v4/settings")
public class V4SettingController {
    
	@Autowired
	private V4SettingService v4SettingService ;
	@Autowired
	private DictionaryUtil dic;
	@Autowired
	private SessionUtil sess;
    @ResponseBody
    @RequestMapping("/specialAuthority")
    public String  specialAuthority(HttpSession session ,String session_id ){
        // 判断用户有无 角色备注为"verify_ticket" 的角色, 若有返回1, 若无返回0
	    SessionProperty properties ;
	    String language = "zh";
	    try {
	        properties = sess.sessionEffective(session,session_id , "/v4/settings/specialAuthority");
	        //properties.getUser_id();
	        language = properties.getLanguage();
	        return ObjectUtil.jsonOut(v4SettingService.isTicketCheckerById(properties.getUser_id()));
	    } catch (HongZhiException e) {
	        return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
	    }  
    }

}
