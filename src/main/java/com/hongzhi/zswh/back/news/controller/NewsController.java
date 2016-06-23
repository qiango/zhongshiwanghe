package com.hongzhi.zswh.back.news.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.news.entity.NewsParam;
import com.hongzhi.zswh.back.news.service.NewsService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 31, 2016    2:04:46 PM
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    
    @Autowired
    private SessionUtil sess;
    @Autowired
    private DictionaryUtil dic;
    @Autowired
    private NewsService service;
	@Autowired
	private DictionaryUtil dictionaryUtil;
    
    @ResponseBody
    @RequestMapping("/list")
    public String list(HttpSession session, NewsParam  newsParam){
//         分页  
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,null, "backend news list ");
            language = properties.getLanguage();
            return ObjectUtil.jsonOut( service.list(newsParam) );
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    	
//		String  param_name = "news_title,page_number,page_size";
//		String   errorcode = "0,0,0";
//		Map<String,String> params=new HashMap<>();
//		try {
//			params = dictionaryUtil.checkParams(param_name, errorcode, newsParam, session , "news/list");
//			return service.list(params);
//		} catch (HongZhiException e) {
//			return dictionaryUtil.appOut( e.getCode() , params.get("language_abbreviation") , null);
//		}
    }
    
    @ResponseBody
    @RequestMapping("/newLoad")
    public String newLoad(HttpSession session){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,null, "backend news newLoad");
            language = properties.getLanguage();
            return ObjectUtil.jsonOut( service.newLoad(properties) );
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }
    
    @ResponseBody
    @RequestMapping("/newSave")
    public String newSave(HttpSession session,NewsParam newsParam){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,null, "backend news newLoad");
            language = properties.getLanguage();
            newsParam.setCreate_user(properties.getUser_id()) ;
            return ObjectUtil.jsonOut( service.newSave(newsParam) );
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }
    /**
     * @author zhurenkui
     * @param session
     * @param news_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String newSave(HttpSession session,String news_id){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,null, "backend news newLoad");
            language = properties.getLanguage();
            return ObjectUtil.jsonOut( service.logicDelete(news_id) );
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }
    
    /**
     * @author zhurenkui
     * @param session
     * @param news_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/modifyLoad")
    public String modifyLoad(HttpSession session,String news_id){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,null, "backend news newLoad");
            language = properties.getLanguage();
            return ObjectUtil.jsonOut( service.modifyLoad(news_id) );
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }
    
    
    /**
     * @author zhurenkui
     * @param session
     * @param newsParam
     * @return
     */
    @ResponseBody
    @RequestMapping("/modifySave")
    public String modifySave(HttpSession session,NewsParam newsParam){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,null, "backend news newLoad");
            language = properties.getLanguage();
            return ObjectUtil.jsonOut( service.modifySave(newsParam) );
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }
    
    
    
    
    
}
