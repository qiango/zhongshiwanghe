package com.hongzhi.zswh.app_v5.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v5.service.V5NewsService;
import com.hongzhi.zswh.app_v5.service.V5iQiYiService;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.date.DateFormat;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 31, 2016    2:40:30 PM
 */
@Controller
@RequestMapping("/v5/news/")
public class V5NewsController {
    
    @Autowired
    public V5NewsService v5NewsService;
    @Autowired
    public V5iQiYiService iQiYiService;
    @Autowired
    private SessionUtil sess;
    
    @ResponseBody
    @RequestMapping("/latest")
    public String latestNews(HttpSession session , String session_id ,Integer page_number){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,session_id , "/v5/news/latest");
            language = properties.getLanguage();
            return ObjectUtil.jsonOutDT(v5NewsService.newsList(properties,page_number,"latest"),DateFormat.getFormatWithTime("zh"));
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutDT(v5NewsService.newsList(null,page_number,"latest"),DateFormat.getFormatWithTime("zh"));
        }
    }

    @ResponseBody
    @RequestMapping("/moment")
    public String video(HttpSession session , String session_id ,Integer page_number){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,session_id , "/v5/news/moment");
            language = properties.getLanguage();
            return ObjectUtil.jsonOutDT(v5NewsService.newsList(properties,page_number,"video"),DateFormat.getFormatWithTime("zh"));
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutDT(v5NewsService.newsList(null,page_number,"video"),DateFormat.getFormatWithTime("zh"));
        }
    }
    
    @ResponseBody
    @RequestMapping("/momentRecommend")
    public String momentReconnand(HttpSession session , String session_id ,Integer page_number,Integer news_id){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,session_id , "/v5/news/moment");
            language = properties.getLanguage();
            return ObjectUtil.jsonOutDT(v5NewsService.newsListRecommend(properties,page_number,news_id),DateFormat.getFormatWithTime("zh"));
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutDT(v5NewsService.newsListRecommend(null,page_number,news_id),DateFormat.getFormatWithTime("zh"));
        }
    }
    
    @RequestMapping("/detail")
    public  String  toIndex(Model model ,String news_id) {
        return v5NewsService.newsDetail(news_id,model);
    }

    @ResponseBody
    @RequestMapping("/video")
    public  String  video(String news_id) {
        return ObjectUtil.jsonOutDT( iQiYiService.getVideo(news_id) ,DateFormat.getFormatWithTime("zh"));
    }
    
    
}
