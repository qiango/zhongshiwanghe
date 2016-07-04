package com.hongzhi.zswh.app_v4.alipay_trade_query.util;

import com.hongzhi.zswh.app_v4.alipay.config.AlipayConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by taylor on 7/4/16.
 */
public class AlipayQuery {

    public static String query(String out_trade_no){
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "single_trade_query");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("out_trade_no", out_trade_no);
        try {
            String sHtmlText  = AlipaySubmit.buildRequest("","",sParaTemp);
            String trade_no = sHtmlText.substring(sHtmlText.indexOf("<trade_no>")+10,sHtmlText.indexOf("</trade_no>")) ;
            System.out.println("trade_no:"+trade_no);
            return trade_no;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
