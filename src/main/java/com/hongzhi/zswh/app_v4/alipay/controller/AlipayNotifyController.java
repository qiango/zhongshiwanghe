package com.hongzhi.zswh.app_v4.alipay.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v4.alipay.entity.AlipayNotifyParam;
import com.hongzhi.zswh.app_v4.alipay.service.AlipayService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 17, 2016    10:52:43 AM
 */
@Controller
@RequestMapping("/v4/alipay")
public class AlipayNotifyController {
    
    @Autowired
    private AlipayService alipay;
    @Autowired
    private DictionaryUtil dic;
    
    @ResponseBody
    @RequestMapping("/notify")
    public String alipayNoti(AlipayNotifyParam notifyParam){
        return  alipay.reciveNotify(notifyParam);
    }
    
    @ResponseBody
    @RequestMapping("/newAlipayOrder")
    public String newAliparyOrder(String order_code){
        try {
            return  ObjectUtil.jsonOut( alipay.newOrder(order_code) );
        } catch (HongZhiException e) {
            return  ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), "zh") );
        }
    }
    
    @ResponseBody
    @RequestMapping("/refund")
    public String  refund(HttpSession session, String session_id ,String order_code, Double  amount ,String device,String refund_no){
        
        return  ObjectUtil.jsonOut(alipay.refund( order_code,   amount , device, refund_no));
    }
        

}
