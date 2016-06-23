package com.hongzhi.zswh.app_v5.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hongzhi.zswh.app_v4.mall.entity.V4OrderDetailEntity;
import com.hongzhi.zswh.app_v4.mall.entity.V4OrderEntity;

import com.hongzhi.zswh.app_v5.dao.V5OrderDao;
import com.hongzhi.zswh.app_v5.entity.V5OrderDetailEntity;
import com.hongzhi.zswh.app_v5.entity.V5OrderEntity;
import com.hongzhi.zswh.util.basic.ObjectUtil;

import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;

import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.calculate.StringMath;

import com.hongzhi.zswh.util.exception.HongZhiException;

@Service
public class V5OrderService {
	@Autowired
	private V5OrderDao orderDao;
	public  synchronized Object saveOrder(V5OrderEntity orderEntity) throws HongZhiException {
		
		orderEntity.VGoods_id();
		//orderEntity.VCoupon_id();
		orderEntity.setTotal_counts(orderEntity.VTotal_counts());
		orderEntity.setPrice(orderEntity.VPrice().toString());
		
		String orderCode = getStringOrderNo(4);
		orderEntity.setCode(orderCode);
		orderEntity.setStatus(1);
		orderDao.saveOrder(orderEntity);

		if (!ObjectUtil.isEmpty(orderEntity.getId())) {
			orderEntity.setProperties_id(1);
			orderEntity.setGoods_price(Double.parseDouble(orderEntity.getPrice()) / orderEntity.getTotal_counts());//单价
			orderDao.saveOrderGoods(orderEntity);
			
			if (!ObjectUtil.isEmpty(orderEntity.getCoupon_id())) {
				List<String> list_coupon_id = Arrays.asList(orderEntity.getCoupon_id().split(","));
				orderEntity.setOrder_id(orderEntity.getId());
				orderDao.saveOrderCouponUse(orderEntity.getOrder_id(), list_coupon_id);
			}

			Map<String, String> map = new HashMap<>();
			map.put("order_code", orderCode);
			return map;
		} else {
			throw new HongZhiException("1011");
		}
		
	}

	
	public String getStringOrderNo(int length) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("CJB");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
		buffer.append(sdf.format(d));

		Random random = new Random();
		
		String randomString= "";

		// 参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {
			// TODO charOrNumber 
			String charOrNumber = random.nextInt(2) % 2 == 0 ? "char" : "number";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNumber)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				randomString += (char) (random.nextInt(26) + temp);
			} else if ("number".equalsIgnoreCase(charOrNumber)) {
				randomString += String.valueOf(random.nextInt(10));
			}
		}
		String orderCode = buffer.append(randomString).toString();
		if (checkOrderCode(orderCode)) {
			return orderCode;
		} else {
			return getStringOrderNo(length);
		}
	}
	
	public boolean checkOrderCode(String orderCode) {
		List<V5OrderEntity> order_list = orderDao.checkOrderCode(orderCode);
		if (order_list.size() == 0) {
			return true;
		}
		return false;
	}

	
	
	@SuppressWarnings("unchecked")
    public Object myOrder(SessionProperty properties) {
        List<Map<String,Object>>  order_list = orderDao.myOrderList(properties.getUser_id(),properties.getLanguage());
        List<Map<String,Object>>  order_goods_list = orderDao.myOrderGoodsList(properties.getUser_id(),properties.getLanguage());
        
        List<Integer> order_id_list = new ArrayList<>();
		for (int i = 0; i < order_list.size(); i++) {
			List<Map<String, Object>> goods_list = new ArrayList<>();
			order_list.get(i).put("goods_list", goods_list);
			order_id_list.add((Integer) order_list.get(i).get("id"));
		}
		for (int i = 0; i < order_goods_list.size(); i++) {
		    if(!ObjectUtil.isEmpty(order_goods_list.get(i).get("begin_time")) && !ObjectUtil.isEmpty(order_goods_list.get(i).get("due_time"))){
		           Long begin_time = Long.parseLong(order_goods_list.get(i).get("begin_time").toString());
		           Long end_time = Long.parseLong(order_goods_list.get(i).get("due_time").toString());
		           Long current_time = System.currentTimeMillis();
		           if(current_time < begin_time){
		               order_goods_list.get(i).put("validity_time", -1);
		           }else if(begin_time <= current_time && current_time <= end_time){
		               order_goods_list.get(i).put("validity_time", 0);
		           }else if (current_time>end_time){
		               order_goods_list.get(i).put("validity_time", 1);
		           }else{
		               order_goods_list.get(i).put("validity_time", null);
		           }

		    }else{
		               order_goods_list.get(i).put("validity_time", null );
		    }
		    order_goods_list.get(i).remove("begin_time");
		    order_goods_list.get(i).remove("due_time");
		    
			List<Map<String, Object>> temp_goods_list = (List<Map<String, Object>>) order_list.get(order_id_list.indexOf((Integer) order_goods_list.get(i).get("order_id"))).get("goods_list");
			temp_goods_list.add(order_goods_list.get(i));
			Double discount_part = 0.0;
			for(int k=0;k<temp_goods_list.size();k++){
			    discount_part += Double.parseDouble(temp_goods_list.get(k).get("discount_part").toString());
			    temp_goods_list.get(k).remove("discount_part");
			}
			String code  = order_goods_list.get(i).get("code").toString();
			
			List<Map<String, Object>> findCouponByOrderId = orderDao.findCouponByOrderId(code);
			if(findCouponByOrderId.size()>0){
				for (Map<String, Object> map : findCouponByOrderId) {
					String method = (String) map.remove("property_value");
					Double discountPrice=Double.parseDouble(order_goods_list.get(i).get("total_counts").toString())* StringMath.discountPrice(method, Double.parseDouble(order_goods_list.get(i).get("current_price").toString()));
					map.put("discountPrice", discountPrice);
				}
			}else{
				Map<String, Object> map = new HashMap<>();
				map.put("discountName", "");
				map.put("discountPrice", "");
				findCouponByOrderId.add(map);
			}
			
			order_list.get(order_id_list.indexOf((Integer) order_goods_list.get(i).get("order_id"))).put("goods_list",temp_goods_list);
			order_list.get(order_id_list.indexOf((Integer) order_goods_list.get(i).get("order_id"))).put("goods_discount_list",findCouponByOrderId);
			order_list.get(order_id_list.indexOf((Integer) order_goods_list.get(i).get("order_id"))).put("order_name",temp_goods_list.get(0).get("name"));
			order_list.get(order_id_list.indexOf((Integer) order_goods_list.get(i).get("order_id"))).put("total_discount_part", discount_part );
		}

        Map<String,Object> map = new HashMap<>();
        map.put("my_order_list", order_list);
        return map;
    }

	
	
	/**
	 * @zhurenkui
	 * @param orderEntity
	 * @param properties 
	 * @return
	 * @throws HongZhiException
	 */
	public Object selectOrderDetailByCode(V4OrderEntity orderEntity, SessionProperty properties) throws HongZhiException{
		
		orderEntity.VCode();
		
		V5OrderDetailEntity order_detail = orderDao.selectOrderDetailByCode(orderEntity.getCode(),properties.getLanguage());
		
		Map<String,Object>  goods_usable = orderDao.usableGoodsByOrderId(order_detail.getOrder_id(),properties.getLanguage()).get(0);
		List<Map<String,Object>> goods_unusable = orderDao.unusableGoodsByOrderId(order_detail.getOrder_id());
		
		if(!ObjectUtil.isEmpty(goods_usable) && !ObjectUtil.isEmpty(goods_usable.get("goods_name"))){
		    order_detail.setOrder_name(goods_usable.get("goods_name").toString());
		}else if(!ObjectUtil.isEmpty(goods_unusable) && !ObjectUtil.isEmpty(goods_unusable.get(0).get("goods_name"))){
		    order_detail.setOrder_name(goods_unusable.get(0).get("goods_name").toString());
		}
		
		
		if(!ObjectUtil.isEmpty(goods_usable)){
            Long begin_time = Long.parseLong(goods_usable.get("begin_time").toString());
            Long end_time = Long.parseLong(goods_usable.get("due_time").toString());
            Long current_time = System.currentTimeMillis();
            if(current_time < begin_time){
                order_detail.setValidity_time(-1);
            }else if(begin_time <= current_time && current_time <= end_time){
                order_detail.setValidity_time(0);
            }else if (current_time>end_time){
                order_detail.setValidity_time(1);
                Map<String,Object> to_unusable = new HashMap<>();
                to_unusable.put("status_name", goods_usable.get("status_name") );
                to_unusable.put("goods_total_count", goods_usable.get("goods_total_count") );
                to_unusable.put("goods_name", goods_usable.get("goods_name") );
                goods_unusable.add(to_unusable);
            }else{
                order_detail.setValidity_time(null);
            }
            goods_usable.remove("begin_time");
            goods_usable.remove("status_name");
		}else{
                order_detail.setValidity_time(null);
		}
		
		if(!ObjectUtil.isEmpty(goods_usable) && !ObjectUtil.isEmpty(goods_usable.get("discount_type"))){
//		    order_detail.setDiscount_part(StringMath.discountPart(  Integer.parseInt(goods_usable.get("discount_type").toString())
//		                                                          , Double.parseDouble(goods_usable.get("origin_single_price").toString())
//		                                                          ,Double.parseDouble(goods_usable.get("param").toString())
//		                                                          ,goods_usable.get("method").toString()
//		                                                          ,Integer.parseInt(goods_usable.get("goods_total_count").toString()
//		                                                           )));
		    order_detail.setOrigin_price(Double.parseDouble(goods_usable.get("origin_price").toString()));
		    order_detail.setTotal_counts(Integer.parseInt( goods_usable.get("total_counts").toString()));
		    order_detail.setDiscount_part(order_detail.getOrigin_price()-order_detail.getTotal_price());
		    order_detail.setOrder_goods_pic(ObjectUtil.getProperty(goods_usable.get("picture_url"), goods_usable.get("picture_url").toString()).toString());
		    goods_usable.remove("discount_type");
		    goods_usable.remove("price");
		    goods_usable.remove("param");
		    goods_usable.remove("method");
		    goods_usable.remove("origin_price");
		    goods_usable.remove("origin_single_price");
		    goods_usable.remove("total_counts");
		    goods_usable.remove("picture_url");
		    goods_usable.put("due_date", new SimpleDateFormat("yyyy-MM-dd").format(new Date(Long.parseLong(goods_usable.get("due_time").toString()))) );
		    goods_usable.remove("due_time");
		    if(order_detail.getValidity_time()==1){
		        goods_usable.clear();
		    }
		}else{
		    order_detail.setDiscount_part(0.0);
		    order_detail.setOrigin_price(0.0);
		}
		
		
		order_detail.setGoods_list_usable(goods_usable);
		order_detail.setGoods_list_unusable(goods_unusable);
		Map<String,Object> out = new HashMap<>();
		
		
		
		Map<String, Object> findPirceByCode = orderDao.findPirceByCode(orderEntity.getCode());
		Integer total_counts =(Integer) findPirceByCode.get("total_counts");
		BigDecimal current_price  = (BigDecimal) findPirceByCode.get("current_price");
		Double price =current_price.doubleValue();
		
		
		
		
		List<Map<String, Object>> findCouponByOrderId = orderDao.findCouponByOrderId(orderEntity.getCode());
		if(findCouponByOrderId.size()>0){
			for (Map<String, Object> map : findCouponByOrderId) {
				String method = (String) map.remove("property_value");
				Double tem_price=StringMath.discountPrice(method, price);
				Double discountPrice=tem_price*total_counts;
				map.put("discountPrice", discountPrice);
			}
		}
		order_detail.setGoods_discount_list(findCouponByOrderId);
		out.put("order_detail", order_detail);
		return out;
	}

}
