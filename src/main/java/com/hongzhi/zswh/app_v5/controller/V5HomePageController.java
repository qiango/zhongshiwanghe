package com.hongzhi.zswh.app_v5.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v5.service.V5HomeService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.date.DateFormat;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 1, 2016    5:35:27 PM
 */
@Controller
public class V5HomePageController {
        
    
    @Autowired
    private V5HomeService homeService;
    @Autowired
    private SessionUtil sess;
    
    @ResponseBody
    @RequestMapping("/v5/home")
    public String homePage(HttpSession session, String session_id ){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,session_id , "/v4/order/createOrder");
            language = properties.getLanguage();
            return ObjectUtil.jsonOutDT( homeService.homePage(properties) ,DateFormat.getFormatWithTime(language));
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutDT( homeService.homePage(null) ,DateFormat.getFormatWithTime(language));
        }
    }

}
