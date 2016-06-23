package com.hongzhi.zswh.app_v4.alipay.controller;

import java.util.HashMap;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.hongzhi.zswh.app_v4.alipay.config.AlipayConfig;
import com.hongzhi.zswh.util.basic.ObjectUtil;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 12, 2016    11:32:59 AM
 */
public class RefundTest {
    public static void main(String[] args) {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.refund_url,AlipayConfig.appid("Android"),AlipayConfig.private_key,"json",AlipayConfig.input_charset,AlipayConfig.ali_public_key);
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        Map<String,Object> map = new HashMap<>();
        map.put("out_trade_no", "CJB16061106504574");
        map.put("refund_amount", "35.00");
        map.put("refund_reason", "正常退款");
        map.put("out_request_no", "1465629516053");
        request.setBizContent(ObjectUtil.toJson(map));
        String msg = "";
        try {
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            String return_body = response.getBody();
            System.out.println(return_body);

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
