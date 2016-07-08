package com.hongzhi.zswh.app_v6.controller;

import com.hongzhi.zswh.app_v6.service.V6HomeService;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.date.DateFormat;
import com.hongzhi.zswh.util.exception.HongZhiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 1, 2016    5:35:27 PM
 */
@Controller
public class V6HomePageController {
        
    
    @Autowired
    private V6HomeService homeService;
    @Autowired
    private SessionUtil sess;
    
    @ResponseBody
    @RequestMapping("/v6/home")
    public String homePage(HttpSession session, String session_id ){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,session_id , "/v6/home");
            language = properties.getLanguage();
            return ObjectUtil.jsonOutDT( homeService.homePage(properties) ,DateFormat.getFormatWithTime(language));
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutDT( homeService.homePage(null) ,DateFormat.getFormatWithTime(language));
        }
    }

}
