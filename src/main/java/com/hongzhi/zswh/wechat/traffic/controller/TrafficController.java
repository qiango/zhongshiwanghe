/**  
 * @Title: TrafficController.java
 * @Package com.hongzhi.zswh.wechat.traffic.controller
 * @Description: TODO
 * @author Saxon Zhong
 * @date 2016年5月31日
 */
package com.hongzhi.zswh.wechat.traffic.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.wechat.traffic.service.HttpURLService;
import com.hongzhi.zswh.wechat.traffic.service.TrafficService;

/**
 * ClassName: TrafficController
 * @Description: TODO
 * @author Saxon Zhong
 * @date 2016年5月31日
 */
@Controller
@RequestMapping("/a")
public class TrafficController {
	
	@Autowired
	private TrafficService trafficService;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	@ResponseBody
	@RequestMapping("/b")
	public String toApplyForTraffic(@Param(value = "phone") String phone) {
		try {
			
			SimpleDateFormat hours = new SimpleDateFormat("HH");
			int nowHours = Integer.parseInt(hours.format(new Date())) + 8;
			if (17 > nowHours || 21 <= nowHours) {
				throw new HongZhiException("1076");
			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			String nowDate = df.format(new Date());
			String order_id = nowDate + UUID.randomUUID();
			System.out.println(order_id);
			
			String status = trafficService.toApplyForTraffic(phone, order_id);
			System.out.println(status);
			
			return ObjectUtil.jsonOut(status);
		
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dictionaryUtil.getCodeValue(e.getCode(), "zh") );
		}
		
		
	}
	
	@ResponseBody
	@RequestMapping("/red_packets")
	public String red_packets() {
		return ObjectUtil.jsonOut("");
	}
	
	@ResponseBody
	@RequestMapping("/bb")
	public String applyForFeedback(@Param(value = "code") String code, @Param(value = "orderId") String orderId) {
		try {
			trafficService.updateTrafficByOrder(orderId, code);
			return ObjectUtil.jsonOut("");
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dictionaryUtil.getCodeValue(e.getCode(), "zh"));
		}
	}
	
	
}
