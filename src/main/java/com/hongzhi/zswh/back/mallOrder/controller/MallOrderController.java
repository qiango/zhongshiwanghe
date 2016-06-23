package com.hongzhi.zswh.back.mallOrder.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v4.mall.entity.V4OrderEntity;
import com.hongzhi.zswh.back.mallOrder.entity.ParamComp;
import com.hongzhi.zswh.back.mallOrder.service.MallOrderService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.date.DateFormat;
import com.hongzhi.zswh.util.exception.HongZhiException;

@Controller
@RequestMapping("/mallOrder")
public class MallOrderController {
	
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private MallOrderService mallOrderService;
	@Autowired
	private SessionUtil sessionUtil;
	
	
	
	//列表和查找
	@ResponseBody
	@RequestMapping("/list")
	public  String listMallOrder(HttpSession session,String session_id, ParamComp paramComp){
	    SessionProperty properties ;
        String language = "zh";
        try {
            properties = sessionUtil.sessionEffective(session,session_id ,"mallOrder detail");
            language = properties.getLanguage();
            return  ObjectUtil.jsonOut(mallOrderService.listByPage(paramComp));
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dictionaryUtil.getCodeValue(e.getCode(), language ) );
        }
	}
	
	//根据订单号查询单个订单的详情
	@ResponseBody
	@RequestMapping("/detail")
	public  String selectOrderDetailByCode(HttpSession session,String session_id, ParamComp paramComp ){
		SessionProperty properties ;
        String language = "zh";
        try {
            properties = sessionUtil.sessionEffective(session,session_id ,"mallOrder detail");
            language = properties.getLanguage();
    		return  ObjectUtil.jsonOut(mallOrderService.selectOrderDetailByCode(paramComp));
        } catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dictionaryUtil.getCodeValue(e.getCode(), language ) );
        }
	}
	
	
	//订单总额和总数
	@ResponseBody
	@RequestMapping("/sumprice")
	public  String orderSumPriceAndCount(HttpSession session,String session_id,String goods_id){
		SessionProperty properties ;
        String language = "zh";
        try {
            properties = sessionUtil.sessionEffective(session,session_id ,"mallOrder total");
            language = properties.getLanguage();
    		return  ObjectUtil.jsonOut(mallOrderService.orderSumPriceAndCount(goods_id));
        } catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dictionaryUtil.getCodeValue(e.getCode(), language ) );
        }
	}
}
