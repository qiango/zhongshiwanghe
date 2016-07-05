package com.hongzhi.zswh.app_v4.mall.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongzhi.zswh.app_v4.alipay.service.AlipayService;
import com.hongzhi.zswh.app_v4.mall.dao.OrderDao;
import com.hongzhi.zswh.app_v4.mall.entity.Payment;
import com.hongzhi.zswh.app_v4.mall.entity.V4OrderDetailEntity;
import com.hongzhi.zswh.app_v4.mall.entity.V4OrderEntity;
import com.hongzhi.zswh.app_v4.mall.entity.V4ReturnHistory;
import com.hongzhi.zswh.app_v4.wechatpay.service.WeChatPayService;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.calculate.StringMath;
import com.hongzhi.zswh.util.date.DateFormat;
import com.hongzhi.zswh.util.exception.HongZhiException;

@Service
public class V4OrderService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private WeChatPayService weChatPayService;
	@Autowired
	private AlipayService alipayService;
	/**
	 * @Description: TODO
	 * @param @param orderEntity
	 * @param @return
	 * @param @throws HongZhiException   
	 * @return Object  
	 * @throws
	 * @author Saxon
	 * @date 2016年5月17日
	 */
	public synchronized Object saveOrder(V4OrderEntity orderEntity)  throws HongZhiException{
		
		orderEntity.VPrice();
		orderEntity.VGoods_id();
		orderEntity.VProperties_id();
		orderEntity.setTotal_counts(orderEntity.VTotal_count());

		int total_counts = orderEntity.getTotal_count();// 一笔订单总数

		Map<String, Object> counts_map = orderDao.getCountsById(orderEntity.VUser_id(), orderEntity.getGoods_id());// 可以购买的有效票数和该商品限制购买的总数
		
		// if ((Integer.parseInt(counts_map.get("limit_counts").toString())-total_counts) <= (Integer.parseInt(counts_map.get("count_ticket").toString()))) {
		//
		// }
		if (  total_counts  > (Integer.parseInt(counts_map.get("count_ticket").toString())) ) { // 每下一笔订单都要校验一下购买的票数有么有上限，防止同一个用户同时操作下单
			throw new HongZhiException("1041");
		}

		String orderCode = getStringOrderNo(4);
		orderEntity.setCode(orderCode);
		if(orderEntity.getGoods_id() == 1 ){
		    orderEntity.setProperties_id(2);
		}

		Map<String, Object> discount_info = orderDao.getDiscountInfoByGoodsIdAndPropertiesId(orderEntity.getGoods_id(),
				orderEntity.getProperties_id());
		orderEntity.setGoods_price(StringMath.discountPrice(
				Integer.parseInt(discount_info.get("discount_type").toString()),
				Double.parseDouble(discount_info.get("price").toString()),
				Double.parseDouble(discount_info.get("param").toString()), discount_info.get("method").toString()));

		orderEntity.setPrice( (orderEntity.getGoods_price()*orderEntity.getTotal_counts())+"");
		orderDao.saveOrder(orderEntity);
		if (!ObjectUtil.isEmpty(orderEntity.getId())) {
			orderDao.saveOrderGoods(orderEntity);
			Map<String, String> map = new HashMap<>();
			map.put("order_code", orderCode);
			return map;
		} else {
			throw new HongZhiException("1011");
		}
		
	}
	
	/**
	 * @Description: TODO
	 * @param @param length
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author Saxon
	 * @date 2016年5月17日
	 */
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

	/**
	 * @Description: TODO
	 * @param @param orderCode
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author Saxon
	 * @date 2016年5月17日
	 */
	public boolean checkOrderCode(String orderCode) {
		List<V4OrderEntity> order_list = orderDao.checkOrderCode(orderCode);
		if (order_list.size() == 0) {
			return true;
		}
		return false;
	}
	
    /**   Twitter : @taylorwang789 
     * Creat time : May 16, 2016    2:53:13 PM
     * @param properties
     * @return
     */
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
			    if(!ObjectUtil.isEmpty(temp_goods_list.get(k).get("discount_part"))){
			        discount_part += Double.parseDouble(temp_goods_list.get(k).get("discount_part").toString());
			    }
//			    discount_part =discount_part * Integer.parseInt(temp_goods_list.get(k).get("total_counts").toString());
			    temp_goods_list.get(k).remove("discount_part");
			}
			
			order_list.get(order_id_list.indexOf((Integer) order_goods_list.get(i).get("order_id"))).put("goods_list",temp_goods_list);
			order_list.get(order_id_list.indexOf((Integer) order_goods_list.get(i).get("order_id"))).put("order_name",temp_goods_list.get(0).get("name"));
			order_list.get(order_id_list.indexOf((Integer) order_goods_list.get(i).get("order_id"))).put("total_discount_part", discount_part );
		}

        Map<String,Object> map = new HashMap<>();
        map.put("my_order_list", order_list);
        return map;
    }
	
	/**
	 * @author zhurenkui
	 * @throws HongZhiException
	 */
	public String updateOrderStatusById(V4OrderEntity orderEntity) throws HongZhiException {
		
		orderEntity.VCode();
			
		int tem_result = orderDao.updateOrderStatusById(orderEntity.getCode());
		if (1 == tem_result) {
			return null;
		} else {
			throw new HongZhiException("1069");
		}

	}
	/**
	 * @author zhurenkui
	 * @param orderEntity
	 * @return
	 * @throws HongZhiException
	 */
	public Object ticketCheck(V4OrderEntity orderEntity) throws HongZhiException{
	    // modify , May21,2016  @taylorwang789
	    orderEntity.VCode();
	    //orderEntity.setGoods_id(1);// modify,zhurenkui 2015 5 26 
	    
		Map<String,Object> ticket_info = orderDao.selectEffecCountByCode(orderEntity);
		
/*		List<Map<String,Object>> ticket_list  = new ArrayList<>();

		if(!ObjectUtil.isEmpty(ticket_info)){
		    Map<String,Object>  ticket = new HashMap<>();
		    ticket.put("ticket_name",  ticket_info.get("ticket_name"));
		    ticket.put("ticket_pic_url", ticket_info.get("ticket_pic_url"));
		    for(int i=0;i<Integer.parseInt(ticket_info.get("effect_counts").toString());i++){
		        ticket_list.add(ticket);
		    }
		    ticket_info.remove("ticket_name");
		    ticket_info.remove("ticket_pic_url");
		    ticket_info.put("ticket_list", ticket_list);
		}*/
		
		Long due_time = Long.parseLong(ticket_info.get("due_time").toString());
		Long current_time = System.currentTimeMillis();
		if(current_time > due_time ){
		    throw new HongZhiException("1072");
		}
		if(ticket_info.get("order_status").equals("5")){
		    ticket_info.put("effect_counts", 0);
		}
		
		Map<String,Object> out = new HashMap<>();
//		out.put("effect_counts", ticket_info.remove("effect_counts"));
//		out.put("user_nickname", ticket_info.remove("user_nickname"));
//		out.put("order_status_name", ticket_info.remove("order_status_name"));
//		out.put("order_status", ticket_info.remove("order_status"));
//		out.put("phone", ticket_info.remove("phone"));
		ticket_info.remove("due_time");
		out.put("ticket_info", ticket_info);
		
		return out;
	}

    /**   Twitter : @taylorwang789 
     * Creat time : May 17, 2016    3:52:20 PM
     * @param order_code
     * @return
     */
	public Map<String, Object> getOrderInfoToPay(String order_code) {
		return orderDao.getOrderInfoToPay(order_code);
	}

    /**   Twitter : @taylorwang789 
     * Creat time : May 17, 2016    3:52:30 PM
     * @param out_trade_no
     * @param trade_status
     */
	public void updatePayInfo(String out_trade_no, String trade_status) {
		switch (trade_status) {
			case "TRADE_SUCCESS":
				orderDao.updatePayInfo(out_trade_no, 2);
				break;
			case "TRADE_FINISHED":
				orderDao.updatePayInfo(out_trade_no, 2);
				break;
			case "TRADE_CLOSED":
				break;
			case "WAIT_BUYER_PAY":
				break;
			default:
				break;
		}
	}

	/**
	 * @Description: TODO
	 * @param @param user_id
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author Saxon
	 * @date 2016年5月17日
	 */
	public Object checkUserClubStatus(String user_id,String goods_id) {
		
		String club_status = orderDao.checkUserClubStatus(user_id);
		String count_ticket = orderDao.getEffectCountsByGoodsId(user_id, goods_id);

		Map<String, String> map = new HashMap<>();
		map.put("club_status", ObjectUtil.isEmpty(club_status)?"0":club_status );
		map.put("count_ticket", count_ticket);
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
		
		V4OrderDetailEntity order_detail = orderDao.selectOrderDetailByCode(orderEntity.getCode(),properties.getLanguage());
		
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
		out.put("order_detail", order_detail);
		return out;
	}
	/**
	 * @xuejian
	 * @return
	 * @throws HongZhiException
	 */
	public Object voteTickets(String orderCode) throws HongZhiException {
		
		if (ObjectUtil.isEmpty(orderCode)) {
			throw new HongZhiException("1060");
		}
		
		int voteTickets_count = orderDao.voteTickets(orderCode);
		if (1 == voteTickets_count) {
			return null;
		} else {
			throw new HongZhiException("1069");
		}
	}

    /**   Twitter : @taylorwang789 
     * Creat time : May 20, 2016    3:45:56 PM
     * @param pay
     */
    public void payment(Payment pay) {
        orderDao.setPayment(pay);
    }

    /**   Twitter : @taylorwang789 
     * Creat time : May 20, 2016    4:03:00 PM
     * @param out_trade_no
     * @param status 
     */
    @Transactional
    public void updatePaymentStatus(String out_trade_no, String way, String status, String transcation) {
        Payment pay = new Payment();
        pay.setOrder_code(out_trade_no);
        pay.setWay(way);
        pay.setStatus(status);
        pay.setTrace_code(transcation);
        orderDao.updatePayment(pay);
        orderDao.setPayment(pay);
        orderDao.payHistroyInsert(out_trade_no,status.equals(2)?4:8,2);  // insert data from wechat server info 
    }

	/**
	 * @xuejian
	 * @return
	 * @throws HongZhiException
	 */
    @Transactional
	public Object saveReturnHistory(V4ReturnHistory returnHistory) throws HongZhiException {
		
//		returnHistory.VUser_id();
		returnHistory.VGoods_id();
		returnHistory.VOrder_id();
		returnHistory.VProperties_id();
		returnHistory.VReturn_count();
		returnHistory.VDevice();
		
		int returnHistory_id =0;
		
		Map<String,Object> order_map = orderDao.selectOrderCodeById(returnHistory.getOrder_id());
		
		
		if (ObjectUtil.isEmpty(order_map)) {
			throw new HongZhiException("1070");
		}
		//判断能否继续退款（防止反复退票）
		if(returnHistory.VReturn_count() > Integer.parseInt(order_map.get("effect_counts").toString())){ 
		    throw new HongZhiException("1070");
		}
		
		if(Integer.parseInt(order_map.get("effect_counts").toString()) ==  returnHistory.VReturn_count()  ){
		    orderDao.updateOrderStatusByOrderId(returnHistory.getOrder_id(),"4");
		    orderDao.updateOrderEffectCountByOrderId(returnHistory.getOrder_id(),returnHistory.getGoods_id(),returnHistory.getProperties_id(),0);
		}else{
		    orderDao.updateOrderEffectCountByOrderId(returnHistory.getOrder_id(),returnHistory.getGoods_id(),returnHistory.getProperties_id(),Integer.parseInt(order_map.get("effect_counts").toString()) - returnHistory.VReturn_count());
		}
		
		
		if (!ObjectUtil.isEmpty(returnHistory.getId())) { //表示退款失败后，再次退款(失败后要给app端提供id，再次传给服务器端---待定)
			returnHistory.setStatus("3");
			orderDao.updateReturnHistoryById(returnHistory.getId());
		}

		// 保存在退货记录表中
		returnHistory.setStatus("1");
		returnHistory.setRefund_no(System.currentTimeMillis()+"");
		returnHistory_id = orderDao.saveReturnHistory(returnHistory);
		
		
//		List<V4ReturnHistory> list_returnHistory = orderDao.selectReturnHistoryById(returnHistory.getOrder_id(),
//				returnHistory.getGoods_id());
//		
//		if (list_returnHistory.size() == 0) { // 第一条退款记录
//			
//			// 保存在退货记录表中
//			 returnHistory_id =  orderDao.saveReturnHistory(returnHistory);
//			// 更新商品订单表
//			orderDao.updateOrderGoodsById(returnHistory.getOrder_id(), returnHistory.getGoods_id(),
//					returnHistory.getProperties_id(), returnHistory.getReturn_count());
//		} else {
//
//			// 已经有了退货记录，则更新end_time,并把新的退货记录存在退货记录表中start_time为now()，同时更新mall_order_goods的有效票数
//			orderDao.updateReturnHistory(returnHistory.getOrder_id(), returnHistory.getGoods_id());
//		
//			// 保存在退货记录表中
//			returnHistory_id = orderDao.saveReturnHistory(returnHistory);
//
//			// 更新商品订单表
//			orderDao.updateOrderGoodsById(returnHistory.getOrder_id(), returnHistory.getGoods_id(),
//					returnHistory.getProperties_id(), returnHistory.getReturn_count());
//		}
		
		orderDao.saveReturnReason(returnHistory);//保存在退货理由表
		
		//调用支付方式
		String order_code = order_map.get("code").toString();
		Double amount = orderDao.selectGoodsPriceById(returnHistory.getGoods_id(),returnHistory.getProperties_id(), returnHistory.VOrder_id() );
		String way = orderDao.selectPaymentByOrderId(returnHistory.getOrder_id());
		if(ObjectUtil.isEmpty(amount)){
		    amount=0.0;
		}
		if ("2".equals(way)) {//调用微信支付
			weChatPayService.refund(order_code, ((Double)(amount*returnHistory.getReturn_count()*100)).intValue() ,returnHistory.getDevice(), returnHistory.getRefund_no());
		}else if("1".equals(way)){//调用支付宝支付
			//weChatPayService.refund(order_code,Integer.valueOf(amount),returnHistory.getDevice(),String.valueOf(returnHistory_id));
		    alipayService.refund(order_code,amount*returnHistory.getReturn_count(), returnHistory.getDevice() , returnHistory.getRefund_no() );
		}
		
		return null;
	}


    /**   Twitter : @taylorwang789 
     * Creat time : May 21, 2016    1:27:49 PM
     * @param order_code
     * @param pay_status
     */
    public Map<String,Object>  clientPayNotify(String order_code, Integer pay_status) {
        //1:client success , 2:client fail , 4: server success , 8 :server fail 
        orderDao.payHistroyInsert(order_code,pay_status,1); 

        Map<String,Object> server_info = orderDao.getServerNotifyInfo(order_code);
        if(!ObjectUtil.isEmpty(server_info.get("server_trace_code"))){
            server_info.put("trace_code", server_info.get("server_trace_code") );
        }else if (!ObjectUtil.isEmpty(server_info.get("client_trace_code"))){
            server_info.put("trace_code", server_info.get("client_trace_code") );
        }else{
            server_info.put("trace_code", "" );
        }
        String  server_return ="";
        if(!ObjectUtil.isEmpty(server_info.get("server_return"))){
            server_return = server_info.get("server_return").toString();
        }
        server_info.remove("server_trace_code");
        server_info.remove("client_trace_code");
        server_info.remove("server_return");
        server_info.remove("client_return");
        
        if(!ObjectUtil.isEmpty(server_info) && !ObjectUtil.isEmpty(server_info.get("return_code"))  ){
            if(!ObjectUtil.isEmpty(server_return) && server_return.equals(4)){
               server_info.put("server_return", "serverSuccess");
               return server_info;
            }else{
               server_info.put("server_return", "serverFail");
               return server_info;
            }
        }else{
               server_info.put("server_return", "serverNoReturn");
               return server_info;
        }
    }

    /**   Twitter : @taylorwang789 
     * Creat time : May 21, 2016    2:52:21 PM
     * @param order_code
     * @return
     */
    public Map<String, Object> getOrderInfoToRefund(String order_code) {
        return orderDao.getOrderInfoToRefund(order_code);
    }

    /**   Twitter : @taylorwang789 
     * Creat time : May 21, 2016    5:35:56 PM
     * @param order_code
     */
    public void updateOrderStatus(String order_code, String status) {
        orderDao.updateOrderStatusByOrderCode(order_code,status);
    }

}
