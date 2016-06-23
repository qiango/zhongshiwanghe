/**  
 * @Title: TrafficService.java
 * @Package com.hongzhi.zswh.wechat.traffic.service
 * @Description: TODO
 * @author Saxon Zhong
 * @date 2016年5月31日
 */
package com.hongzhi.zswh.wechat.traffic.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.util.MD5Util;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.wechat.traffic.dao.TrafficDao;
import com.hongzhi.zswh.wechat.traffic.entity.Traffic;

/**
 * ClassName: TrafficService
 * 
 * @Description: TODO
 * @author Saxon Zhong
 * @date 2016年5月31日
 */
@Service
public class TrafficService {
	
	private String url = "http://${host}:${port}/smpExternal/${u}/smpWNRecharge/${md5}?phoneNumber=${phoneNumber}&levelId=${levelId}&notifyUrl=${notifyUrl}&orderId=${orderId}";
	private String host = "liuliang.easy8.com.cn";
	private String port = "8088";
	private String cmu = "10342";
	private String cmkey = "fgmb142f";
	private String cuu = "10340";
	private String cukey = "w3faxqd3";
	private String ctu = "10343";
	private String ctkey = "xdnls434";
	
	
	@Autowired
	private TrafficDao trafficDao;
	
	/**
	 * @Description: TODO
	 * @param @param phone
	 * @param @return
	 * @return String
	 * @throws IOException
	 * @throws
	 * @author Saxon
	 * @date 2016年5月31日
	 */
	@SuppressWarnings("restriction")
	public synchronized String toApplyForTraffic(String phone, String order_id) throws HongZhiException {
		String level_id = matchesPhoneNumber(phone);
		System.out.println(level_id);
		
		Traffic traffic = new Traffic();
		traffic.setLevel_id(level_id);
		traffic.setOrder_id(order_id);
		traffic.setPhone(phone);
		
		int count_number = trafficDao.findTrafficByPhone(phone);
		System.out.println("count_number:"+count_number);
		
		if(0 < count_number) {
			throw new HongZhiException("1074");
		} else {
			trafficDao.addTraffic(traffic);
		}
		
		String use_url = url;
		
		String u = "";
		String key = "";
		if ("20150".equals(level_id)) {
			u = cmu;
			key = cmkey;
		} else if ("G00050".equals(level_id)) {
			u = cuu;
			key = cukey;
		} else {
			u = ctu;
			key = ctkey;
		}
		
		String notifyUrl = "http://api.chengjubei.com/a/bb.htmls";
		
		notifyUrl = new sun.misc.BASE64Encoder().encode(notifyUrl.getBytes());
		
		use_url = use_url.replace("${phoneNumber}", phone).replace("${levelId}", level_id).replace("${notifyUrl}", notifyUrl).replace("${orderId}", order_id);
		
		System.out.println(phone + level_id + notifyUrl + order_id + u + key);
		String md5 = MD5Util.MD5Encode(phone + level_id + notifyUrl + order_id + u + key);
		System.out.println(md5);
		
		use_url = use_url.replace("${host}", host).replace("${port}", port).replace("${u}", u).replace("${md5}", md5);
		System.out.println(use_url);
		
		HttpURLService httpURLService = new HttpURLService();
		String status = httpURLService.sendHttp(use_url);
		
		toApplyForStatus(order_id, status);
		
		return status;
	}

	public static String matchesPhoneNumber(String phone_number) {
		String cm = "^((13[4-9])|(147)|(15[0-2,7-9])|(18[2-4,7-8]))\\d{8}$";
		String cm2 = "^((139[0-1]))\\d{7}$";
		String cu = "^((13[0-2])|(145)|(15[5-6])|(18[5-6])|(176))\\d{8}$";
		String cu2 = "^((1709)|(1860))\\d{7}$";
		String ct = "^((133)|(153)|(18[0-1,9])|(177))\\d{8}$";
		String ct2 = "^((1700)|(1330)|(1890))\\d{7}$";
		String flag = "20150";
		if (phone_number.matches(cm)) {
			flag = "20150";
		} else if (phone_number.matches(cm2)) {
			flag = "20150";
		} else if (phone_number.matches(cu)) {
			flag = "G00050";
		} else if (phone_number.matches(cu2)) {
			flag = "G00050";
		} else if (phone_number.matches(ct)) {
			flag = "30050";
		} else if (phone_number.matches(ct2)) {
			flag = "30050";
		} else {
			flag = "20150";
		}
		return flag;
	}

	/**
	 * @Description: TODO
	 * @param @param phone
	 * @param @param status
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author Saxon
	 * @date 2016年6月6日
	 */
	public void toApplyForStatus(String order_id, String status) throws HongZhiException {
		int affected_row = trafficDao.updateTraffic(order_id, status);
		if (1 != affected_row) {
			throw new HongZhiException("1033");
		}
	}

	/**
	 * @Description: TODO
	 * @param @param orderId
	 * @param @param code   
	 * @return void  
	 * @throws
	 * @author Saxon
	 * @date 2016年6月12日
	 */
	public void updateTrafficByOrder(String order_id, String code) throws HongZhiException {
		int affected_row = trafficDao.updateTrafficByOrder(order_id, code);
		if (1 != affected_row) {
			throw new HongZhiException("1033");
		}
	}

	
}
