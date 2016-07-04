package com.hongzhi.zswh.app_v4.alipay.controller;

import java.util.HashMap;
import java.util.Map;

import com.hongzhi.zswh.app_v4.alipay.config.AlipayConfig;
import com.hongzhi.zswh.app_v4.alipay_trade_query.util.AlipaySubmit;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 19, 2016    12:47:22 PM
 */
public class QueryTest {
    public static void main(String[] args) {
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "single_trade_query");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("out_trade_no", "CJB160531163364XK");
        try {
            String sHtmlText  = AlipaySubmit.buildRequest("","",sParaTemp);
            System.out.println("aaaaaaaa:"+sHtmlText);

            System.out.println(sHtmlText.substring(sHtmlText.indexOf("<trade_no>")+10,sHtmlText.indexOf("</trade_no>")) );


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("bbbbbbb:");
        }

    }
}
