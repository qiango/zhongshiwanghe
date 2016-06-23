package com.hongzhi.zswh.back.advertisement.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.advertisement.entity.AdParam;
import com.hongzhi.zswh.back.advertisement.service.AdService;
import com.hongzhi.zswh.back.mallOrder.entity.ParamComp;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.date.DateFormat;
import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 31, 2016    3:48:50 PM
 */
@Controller
@RequestMapping("/ad")
public class AdController {
    
    @Autowired
    private AdService ad;
    @Autowired
    private DictionaryUtil dic;
    @Autowired
    private SessionUtil sess;
    
    @ResponseBody
    @RequestMapping("/list")
    public  String list(HttpSession session,AdParam adParam){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,null, "backend ad list");
            language = properties.getLanguage();
            return ObjectUtil.jsonOut( ad.list(adParam,properties) );
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }
    
    
      
    @ResponseBody
    @RequestMapping("/newAd")
    public  String newad(HttpSession session,AdParam adParam){
    //http://localhost:8080/springmvc_mybatis_demo/ad/newAd.htmls?ad_name=test&ad_type=1&ad_start_time=2016-05-31&ad_end_time=2016-06-04&image_url=fhdajfj&property_code=hello&property_value=hellovlaue
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,null, "backend ad newAd");
            language = properties.getLanguage();
            return ObjectUtil.jsonOut( ad.newAds(adParam,properties) );
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }
   
    
    @ResponseBody
    @RequestMapping("/modifyLoad")
    public  String modifyLoad(HttpSession session,String ad_id){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,null, "backend ad modifyload");
            language = properties.getLanguage();
            ExcepUtil.verify(ad_id, "");
            return ObjectUtil.jsonOutDT( ad.modifyLoad(Integer.parseInt(ad_id)),DateFormat.getFormat(language));
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }
    
    @ResponseBody
    @RequestMapping("/modifySave")
    public  String modifySave(HttpSession session,AdParam adParam){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,null, "backend ad modifysave");
            language = properties.getLanguage();
            return ObjectUtil.jsonOut( ad.modifySave(adParam) );
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }
    @ResponseBody
    @RequestMapping("/delete")
    public String updateAdvertisementById(HttpSession session,String ad_id){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,null, "backend news newLoad");
            language = properties.getLanguage();
            return ObjectUtil.jsonOut( ad.updateAdvertisementById(ad_id) );
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }
	@ResponseBody
	@RequestMapping("/detail")
	public  String selectAdDetailById(HttpSession session,String session_id, String ad_id ){
		SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,session_id ,"mallOrder detail");
            language = properties.getLanguage();
    		return  ObjectUtil.jsonOutDT(ad.selectAdDetailById(ad_id), DateFormat.getFormat(language));
        } catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
	}
    /**
     * 
     * @param session
     * @param adParam
     * @return
     */
    @ResponseBody
    @RequestMapping("/insertload")
    public  String insertLoad(HttpSession session){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,null, "backend ad list");
            language = properties.getLanguage();
            return ObjectUtil.jsonOut( ad.insertLoad() );
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }
    
}
