package com.hongzhi.zswh.app_v4.alipay.entity;

import java.util.Map;

import org.springframework.jdbc.support.nativejdbc.JBossNativeJdbcExtractor;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hongzhi.zswh.app_v4.alipay.config.AlipayConfig;
import com.hongzhi.zswh.app_v4.alipay.service.AlipayService;
import com.hongzhi.zswh.app_v4.alipay.sign.RSA;

import net.sf.json.groovy.JsonSlurper;

/**   Twitter : @taylorwang789 
 * Creat time : May 19, 2016    10:39:18 AM
 */
public class TestKeyPair {
    
    public static void main(String[] args) {
        System.out.println(RSA.sign("a=123", AlipayConfig.private_key, AlipayConfig.input_charset));
        
        
        
//        Gson gson = new Gson();
//        
//        String jsonLine = "{ \"alipay_trade_refund_response\":{ \"buyer_logon_id\":\"159****5620\", \"buyer_user_id\":\"2088101117955611\", \"code\":\"10000\", \"fund_change\":\"Y\", \"gmt_refund_pay\":\"2014-11-27 15:45:57\", \"msg\":\"Success\", \"open_id\":\"2088102122524333\", \"out_trade_no\":\"6823789339978248\", \"refund_detail_item_list\":[{ \"amount\":10, \"fund_channel\":\"ALIPAYACCOUNT\" }], \"refund_fee\":88.88, \"send_back_fee\":\"1.8\", \"store_name\":\"望湘园联洋店\", \"trade_no\":\"支付宝交易号\" } } ";
//        JsonElement jelement = new JsonParser().parse(jsonLine);
//        JsonObject  jobject = jelement.getAsJsonObject();
//        jobject = jobject.getAsJsonObject("alipay_trade_refund_response");
////        JsonArray jarray = jobject.getAsJsonArray("translations");
////        jobject = jarray.get(0).getAsJsonObject();
////        String result = jobject.get("buyer_logon_id").toString();
//        System.out.println(jobject.get("code").toString());
//        
////        Map<String,Object> map = gson.fromJson(jsonLine, Map.class);
////        System.out.println(map.get("alipay_trade_refund_response"));
//                
        
                
    }

}
