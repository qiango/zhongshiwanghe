package com.hongzhi.zswh.app_v4.alipay.controller;

import java.util.HashMap;
import java.util.Map;

import com.hongzhi.zswh.app_v4.alipay.config.AlipayConfig;
import com.hongzhi.zswh.app_v4.alipay_refund.util.AlipaySubmit;
import com.hongzhi.zswh.app_v4.alipay_refund.util.UtilDate;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 12, 2016    11:32:59 AM
 */
public class Test {
    public static void main(String[] args) {
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "refund_fastpay_by_platform_nopwd");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("notify_url", AlipayConfig.notify_url);
        sParaTemp.put("batch_no", UtilDate.getDate()+"32543333");
        sParaTemp.put("refund_date", UtilDate.getDateFormatter() );
        sParaTemp.put("batch_num", "1" );
        sParaTemp.put("detail_data", "2016060221001004070299798549^0.01^正常退款");
        try {
            String sHtmlText  = AlipaySubmit.buildRequest("","",sParaTemp);
            System.out.println("aaaaaaaa:"+sHtmlText);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("bbbbbbb:");
        }

    }
}
