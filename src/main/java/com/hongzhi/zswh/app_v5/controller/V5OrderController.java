package com.hongzhi.zswh.app_v5.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v4.mall.entity.V4OrderEntity;
import com.hongzhi.zswh.app_v5.entity.V5OrderEntity;
import com.hongzhi.zswh.app_v5.service.V5OrderService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.date.DateFormat;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 12, 2016    4:03:13 PM
 */
@Controller
@RequestMapping("/v5/order")
public class V5OrderController {
	@Autowired
	private V5OrderService orderService ;
	@Autowired
	private DictionaryUtil dic;
	@Autowired
	private SessionUtil sess;
	
	@ResponseBody
	@RequestMapping("/createOrder")
	public String createOrder(HttpSession session,String session_id, V5OrderEntity orderEntity){ //票下单 
		
		SessionProperty properties ;
		String language = "zh";
		try {
			properties = sess.sessionEffective(session,session_id , "/v5/order/createOrder");
			language = properties.getLanguage();
			orderEntity.setUser_id(Integer.parseInt(properties.getUser_id()));
			return ObjectUtil.jsonOut( orderService.saveOrder(orderEntity) );
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
		
	}

	
	
	@ResponseBody
	@RequestMapping("/orderDetail")
	public String orderDetail(HttpSession session,String session_id, V4OrderEntity orderEntity){
		SessionProperty properties ;
		String language = "zh";
		try {
			properties = sess.sessionEffective(session,session_id , " order_V4  order detail");
			language = properties.getLanguage();
			return ObjectUtil.jsonOutDT(orderService.selectOrderDetailByCode(orderEntity,properties), DateFormat.getFormat(language)+" HH:mm:ss");
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
		
	}

	
	@ResponseBody
	@RequestMapping("/myOrder")
	public String myOrder(HttpSession session , String session_id  ){
	    SessionProperty properties ;
	    String language = "zh";
	    try {
	        properties = sess.sessionEffective(session,session_id , "/v5/order/myOrder");
	        language = properties.getLanguage();
	        return ObjectUtil.jsonOut( orderService.myOrder(properties) );
	    } catch (HongZhiException e) {
	        return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
	    }
	}
	

}
