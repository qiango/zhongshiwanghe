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
import com.hongzhi.zswh.app_v4.alipay.util.UtilDate;
import com.hongzhi.zswh.util.basic.ObjectUtil;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 12, 2016    11:32:59 AM
 */
public class RefundTest {
    public static void main(String[] args) {
        System.out.println(UtilDate.getDate() );
    }
}
