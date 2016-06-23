package com.hongzhi.zswh.app_v4.mall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v4.mall.entity.V4OrderEntity;
import com.hongzhi.zswh.app_v4.mall.entity.V4ReturnHistory;
import com.hongzhi.zswh.app_v4.mall.service.V4OrderService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.date.DateFormat;
import com.hongzhi.zswh.util.exception.HongZhiException;

@Controller
@RequestMapping("/v4/order")
public class V4OrderController {

	@Autowired
	private V4OrderService orderService ;
	@Autowired
	private DictionaryUtil dic;
	@Autowired
	private SessionUtil sess;
	
	@ResponseBody
	@RequestMapping("/createOrder")
	public String placeOrder(HttpSession session,String session_id, V4OrderEntity orderEntity){ //燕京啤酒节门票下单 
		
		SessionProperty properties ;
		String language = "zh";
		try {
			properties = sess.sessionEffective(session,session_id , "/v4/order/createOrder");
			language = properties.getLanguage();
			orderEntity.setUser_id(Integer.parseInt(properties.getUser_id()));
			return ObjectUtil.jsonOut( orderService.saveOrder(orderEntity) );
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
	        properties = sess.sessionEffective(session,session_id , "/v4/order/myOrder");
	        language = properties.getLanguage();
	        return ObjectUtil.jsonOut( orderService.myOrder(properties) );
	    } catch (HongZhiException e) {
	        return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
	    }
	}
	
	/**
	 * @author zhurenkui 
	 * @param session
	 * @param session_id
	 * @param orderEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cancelOrder")
	public String cancelOrder(HttpSession session,String session_id, V4OrderEntity orderEntity){
		SessionProperty properties ;
		String language = "zh";
		try {
			properties = sess.sessionEffective(session,session_id , " order_V4  cancel order ");
			language = properties.getLanguage();
			return ObjectUtil.jsonOut( orderService.updateOrderStatusById(orderEntity) );
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
    /**
     * @author zhurenkui
     * @param session
     * @param session_id
     * @param orderEntity
     * @return
     */
	@ResponseBody
	@RequestMapping("/ticketCheck")
	public String ticketCheck(HttpSession session,String session_id, V4OrderEntity orderEntity){
		SessionProperty properties ;
		String language = "zh";
		try {
			properties = sess.sessionEffective(session,session_id , " order_V4  ticket check ");
			language = properties.getLanguage();
			return ObjectUtil.jsonOut(orderService.ticketCheck(orderEntity));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	@ResponseBody
	@RequestMapping("/checkUserClubStatus")
	public String checkUserClubStatus(HttpSession session,String session_id,String goods_id){
		SessionProperty properties ;
		String language = "zh";
		try {
			properties = sess.sessionEffective(session,session_id , " club_V4  userClubStatus check ");
			language = properties.getLanguage();
			return ObjectUtil.jsonOut(orderService.checkUserClubStatus(properties.getUser_id(),goods_id));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}

	@ResponseBody
	@RequestMapping("/voteTickets")
	public String voteTickets(HttpSession session,String session_id,String order_code ){
		SessionProperty properties;
		String language = "zh";
		try {
			properties = sess.sessionEffective(session, session_id, " voteTickets_V4  voteTickets check ");
			language = properties.getLanguage();
			return ObjectUtil.jsonOut(orderService.voteTickets(order_code));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language));
		}
	}
	
    /**
     * @author zhurenkui
     * @param session
     * @param session_id
     * @param orderEntity
     * @return
     */
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
	@RequestMapping("/returnTicket")
	public String returnTicket(HttpSession session,String session_id, V4ReturnHistory returnHistory){
		
		SessionProperty properties;
		String language = "zh";
		try {
			properties = sess.sessionEffective(session, session_id, " order_V4  returnHistory detail");
			language = properties.getLanguage();
			returnHistory.setUser_id(Integer.parseInt(properties.getUser_id()));
			return ObjectUtil.jsonOut(orderService.saveReturnHistory(returnHistory));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language));
		}
	}
}
