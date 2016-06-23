package com.hongzhi.zswh.app_v4.wechatpay.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v4.wechatpay.entity.WeChatNotifyEntity;
import com.hongzhi.zswh.app_v4.wechatpay.service.WeChatPayService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.date.DateFormat;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 18, 2016    1:26:11 PM
 */
@Controller
@RequestMapping("/v4/wechat")
public class WeChatPayController {
    
    @Autowired
    private DictionaryUtil dic;
    
    @Autowired
    private SessionUtil sess;
    
    @Autowired
    private WeChatPayService weChat;
    
    @ResponseBody
    @RequestMapping("/notify")
    public String  notify(HttpServletRequest request){
        
        return weChat.reciveNotify(request);
    }
    
    @ResponseBody
    @RequestMapping("/clientNotify")
    public String  notifyFromClient(HttpSession session, String session_id ,String order_code, String pay_status){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,session_id , "/v4/wechat/clientNotify");
            language = properties.getLanguage();
            return ObjectUtil.jsonOutDT( weChat.clentNotify(order_code,Integer.parseInt(pay_status)) ,DateFormat.getFormat(language)+" HH:mm:dd");
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
        
    }
    
    @ResponseBody
    @RequestMapping("/test")
    public String test(String code,String status){
        return weChat.test(code,status);
    }
    
    @ResponseBody
    @RequestMapping("/newWeChatPayOrder")
    public String newWeChatPay(String order_code, String device){
        try {
            return  ObjectUtil.jsonOut( weChat.createOrder(order_code,device) );
        } catch (HongZhiException e) {
            return  ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), "zh") );
        }
    }

    
    ////////////////////////refund 
    @ResponseBody
    @RequestMapping("/refund")
    public String  refund(HttpSession session, String session_id ,String order_code, Integer  amount ,String device,String refund_no){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sess.sessionEffective(session,session_id , "/v4/wechat/clientNotify");
            language = properties.getLanguage();
            return ObjectUtil.jsonOut( weChat.refund(order_code,amount,device,refund_no) );
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
        
    }
    
    
}
