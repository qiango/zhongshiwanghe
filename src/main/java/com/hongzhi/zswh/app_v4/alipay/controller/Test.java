package com.hongzhi.zswh.app_v4.alipay.controller;

import java.util.HashMap;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.hongzhi.zswh.app_v4.alipay.config.AlipayConfig;
import com.hongzhi.zswh.util.basic.ObjectUtil;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 12, 2016    11:32:59 AM
 */
public class Test {
    public static void main(String[] args) {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.appid("Android"),AlipayConfig.private_key,"json",AlipayConfig.input_charset,AlipayConfig.ali_public_key);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        Map<String,Object> map = new HashMap<>();
        map.put("out_trade_no", "CJB16061106504574");
        request.setBizContent(ObjectUtil.toJson(map));
        try {
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            String return_body = response.getBody();
            System.out.println(return_body);

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
