package com.hongzhi.zswh.app_v6.controller;

import com.hongzhi.zswh.app_v6.service.V6NewsService;
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
 * Creat time : May 31, 2016    2:40:30 PM
 */
@Controller
@RequestMapping("/v6/news/")
public class V6NewsController {
    
    @Autowired
    public V6NewsService v6NewsService;
    @Autowired
    private SessionUtil sess;


    @ResponseBody
    @RequestMapping("/moment")
    public String video(HttpSession session , String session_id ,Integer page_number){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,session_id , "/v6/news/moment");
            language = properties.getLanguage();
            return ObjectUtil.jsonOutDT(v6NewsService.newsList(properties,page_number,"video"),DateFormat.getFormatWithTime("zh"));
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutDT(v6NewsService.newsList(null,page_number,"video"),DateFormat.getFormatWithTime("zh"));
        }
    }
    
    @ResponseBody
    @RequestMapping("/moment_recommend")
    public String momentReconnand(HttpSession session , String session_id ,Integer page_number,Integer news_id){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,session_id , "/v6/news/momentRecommend");
            language = properties.getLanguage();
            return ObjectUtil.jsonOutDT(v6NewsService.newsListRecommend(properties,page_number,news_id),DateFormat.getFormatWithTime("zh"));
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutDT(v6NewsService.newsListRecommend(null,page_number,news_id),DateFormat.getFormatWithTime("zh"));
        }
    }
}
