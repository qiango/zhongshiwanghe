package com.hongzhi.zswh.app_v5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v5.service.V5GoodsService;
import com.hongzhi.zswh.util.basic.ObjectUtil;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 12, 2016    4:04:52 PM
 */
@Controller
@RequestMapping("/v5/goods")
public class V5GoodsController {
    
    
    @Autowired
    private V5GoodsService goodsService;
    
    @ResponseBody
    @RequestMapping("/list")
    public String goodsList(){
        return ObjectUtil.jsonOut(goodsService.goodsList());
    }
    
    @ResponseBody
    @RequestMapping("/info")
    public String goodsinfo(Integer goods_id){
        return ObjectUtil.jsonOut(goodsService.goodsInfo(goods_id));
    }
    
    

}
