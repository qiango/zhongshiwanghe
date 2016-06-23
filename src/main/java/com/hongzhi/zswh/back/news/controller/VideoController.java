package com.hongzhi.zswh.back.news.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.news.service.VideoService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 17, 2016    3:52:52 PM
 */
@Controller
@RequestMapping("/video")
public class VideoController {
    
    @Autowired 
    private VideoService videoService;
    @Autowired
    private SessionUtil sess;
    @Autowired
    private DictionaryUtil dic;

    
    @ResponseBody
    @RequestMapping("/toupload")
    public String  toupload(HttpSession session){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,null, "backend news list ");
            language = properties.getLanguage();
            return ObjectUtil.jsonOut( videoService.toUpload() );
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }  
    }
    
    

}
