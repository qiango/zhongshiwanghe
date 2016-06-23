package com.hongzhi.zswh.app_v4.mall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v4.mall.service.V4GoodsService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.date.DateFormat;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 16, 2016    11:00:52 AM
 */
@Controller
@RequestMapping("/v4/goods")
public class V4GoodsController {
    
    @Autowired
    private DictionaryUtil dic;
    @Autowired
    private SessionUtil sessionUtil;
    @Autowired
    private V4GoodsService goodsService;
    
    @ResponseBody
    @RequestMapping("/info")
    public String goodsList(HttpSession session,String session_id , Integer goods_id){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sessionUtil.sessionEffective(session,session_id ,"v4 goods list");
            language = properties.getLanguage();
            return ObjectUtil.jsonOutDT(  goodsService.listGoods(goods_id)   , DateFormat.getFormat(language));
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutDT(  goodsService.listGoods(goods_id)   , DateFormat.getFormat(language));
//            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
        
    }
    
    @ResponseBody
    @RequestMapping("/list")
    public String allGoodsList(){
        return  ObjectUtil.jsonOut(  goodsService.allGoodsList());
    }
    
    
    @ResponseBody
    @RequestMapping("/presetRefundReason")
    public String presetRefundReason(){
        return  ObjectUtil.jsonOut(goodsService.presetRefundReasonList());
    }
    
    @ResponseBody
    @RequestMapping("/canRefundCount")
    public String canRefundCount(String order_id){
        return ObjectUtil.jsonOut(  goodsService.canRefundCount(order_id) );
    }


}
