package com.hongzhi.zswh.app_v4.alipay.service;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.hongzhi.zswh.app_v4.alipay.util.AlipayNotify;
import com.hongzhi.zswh.app_v4.alipay_refund.util.AlipayRefund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hongzhi.zswh.app_v4.alipay.config.AlipayConfig;
import com.hongzhi.zswh.app_v4.alipay.entity.AlipayNotifyParam;
import com.hongzhi.zswh.app_v4.alipay.sign.RSA;
import com.hongzhi.zswh.app_v4.mall.entity.CurrentPayment;
import com.hongzhi.zswh.app_v4.mall.entity.Payment;
import com.hongzhi.zswh.app_v4.mall.service.V4OrderService;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 17, 2016    11:20:45 AM
 */
@Service
public class AlipayService {
    
//    private String notify_server = "http://monquery.com:8080/springmvc_mybatis_demo/";
    
    @Autowired
    private  V4OrderService orderService;

    /**   Twitter : @taylorwang789 
     * Creat time : May 17, 2016    11:22:20 AM
     * @param order_code
     * @return
     * @throws HongZhiException 
     */
    public synchronized Object newOrder(String order_code) throws HongZhiException {
        
        if(CurrentPayment.payMap.containsKey(order_code)  && ( CurrentPayment.payMap.get(order_code) > System.currentTimeMillis() )  ){
            throw new HongZhiException("1071");
        }
        CurrentPayment.payMap.put(order_code, System.currentTimeMillis()+180);
        
        Map<String, Object> order_info = orderService.getOrderInfoToPay(ExcepUtil.verify(order_code, "1060").toString());
        
        if(ObjectUtil.isEmpty(order_info)){
           throw new HongZhiException("1069") ;
        }
        String paramters_string = ""
               + "partner=\""+AlipayConfig.partner+"\""
               + "&seller_id=\"zhongshiwanghe@qq.com\""
               + "&out_trade_no=\""+order_code+"\""
               + "&subject=\""+order_info.get("goods_name")+"\""
               + "&body=\""+order_info.get("goods_name")+"\""
               + "&total_fee=\""+order_info.get("total_fee")+"\""
               + "&notify_url=\""+AlipayConfig.notify_url+"\""
               + "&service=\"mobile.securitypay.pay\""
               + "&payment_type=\"1\""
               + "&_input_charset=\""+AlipayConfig.input_charset+"\""
               + "&it_b_pay=\"30m\""
//               + "&return_url=\"m.alipay.com\""
//             + "&total_fee=\""+order_info.get("total_fee").toString()+"\"";
                ;
        
        Map<String,String> map = new HashMap<>();
//        map.put("sign", RSA.sign(paramters_string, AlipayConfig.private_key, AlipayConfig.input_charset) );
        try {
            map.put("paramters", paramters_string+"&sign=\""+URLEncoder.encode(RSA.sign(paramters_string, AlipayConfig.private_key, AlipayConfig.input_charset),"UTF-8") +"\"&sign_type=\""+AlipayConfig.sign_type+"\"" );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Payment pay = new Payment();
        pay.setWay("1");
        pay.setStatus("1");
        pay.setOrder_code(order_code);
        pay.setTrace_code("");
        orderService.payment(pay);
        return map;
    }
    

    /**   Twitter : @taylorwang789 
     * Creat time : May 17, 2016    3:42:47 PM
     * @param notifyParam
     * @return
     */
    public String reciveNotify(AlipayNotifyParam notifyParam) {
        Map<String,String> params = new HashMap<>();
        params.put("notify_id",notifyParam.getNotify_id());
        params.put("sign",notifyParam.getSign() );
        if(AlipayNotify.verify(params)){
            orderService.updatePayInfo(notifyParam.getOut_trade_no(),notifyParam.getTrade_status());
            return "success";
        }else{
            return "";
        }
    }


    /**   Twitter : @taylorwang789 
     * Creat time : May 23, 2016    4:35:12 PM
     * @return
     */
    public String refund(String order_code, Double  amount ,String device,String refund_no) {
//        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.refund_url,AlipayConfig.appid(device),AlipayConfig.private_key,"json",AlipayConfig.input_charset,AlipayConfig.ali_public_key);
//        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
//        Map<String,Object> map = new HashMap<>();
//        map.put("out_trade_no", order_code);
//        map.put("trade_no","" );
//        map.put("refund_amount", amount);
//        map.put("refund_reason", "正常退款");
//        map.put("out_request_no", refund_no);
////        map.put("operator_id", "OP001");
////        map.put("store_id", "NJ_S_001");
////        map.put("terminal_id", "NJ_T_001");
//        request.setBizContent(ObjectUtil.toJson(map));
//        String msg = "";
//        try {
//            AlipayTradeRefundResponse response = alipayClient.execute(request);
//            String return_body = response.getBody();
//
//            JsonElement jelement = new JsonParser().parse(return_body);
//            JsonObject  jobject = jelement.getAsJsonObject();
//            jobject = jobject.getAsJsonObject("alipay_trade_refund_response");
//            String code = jobject.get("code").toString();
//            msg = jobject.get("msg").toString().toUpperCase();
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        return msg;
        System.out.println("refund");
        System.out.println("order_code:"+order_code);
        System.out.println("refund_no:"+refund_no);
        System.out.println("amount:"+amount);
        return AlipayRefund.refund(order_code,refund_no,amount.toString());

    }

}
