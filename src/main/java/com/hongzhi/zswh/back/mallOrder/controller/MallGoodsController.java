package com.hongzhi.zswh.back.mallOrder.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.mallOrder.service.MallGoodsService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.date.DateFormat;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 7, 2016    4:14:54 PM
 */
@Controller
@RequestMapping("/mallGoods")
public class MallGoodsController {
    
    @Autowired
    private DictionaryUtil dictionaryUtil;
    @Autowired
    private  MallGoodsService goodsService;
    @Autowired
    private SessionUtil sessionUtil;

    @ResponseBody
    @RequestMapping("/list")
    public String list(HttpSession session,String page_number,String goods_status,String search_string){
        SessionProperty properties ;
        String language = "zh";
        try {
            properties = sessionUtil.sessionEffective(session,null,"mall goods list");
            language = properties.getLanguage();
            return  ObjectUtil.jsonOutDT(goodsService.list(properties,page_number,goods_status,search_string),DateFormat.getFormatWithTime(language));
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dictionaryUtil.getCodeValue(e.getCode(), language ) );
        } 
    }
    
}
