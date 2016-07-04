package com.hongzhi.zswh.app_v4.alipay_refund.util;

import com.hongzhi.zswh.app_v4.alipay.config.AlipayConfig;
import com.hongzhi.zswh.app_v4.alipay_trade_query.util.AlipayQuery;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by taylor on 7/4/16.
 */
public class AlipayRefund {
   public static String refund(String out_trade_no,String batch_no,String amount){
       Map<String, String> sParaTemp = new HashMap<String, String>();
       sParaTemp.put("service", "refund_fastpay_by_platform_nopwd");
       sParaTemp.put("partner", AlipayConfig.partner);
       sParaTemp.put("_input_charset", AlipayConfig.input_charset);
       sParaTemp.put("notify_url", AlipayConfig.notify_url);
       sParaTemp.put("batch_no", UtilDate.getDate()+batch_no );
       sParaTemp.put("refund_date", UtilDate.getDateFormatter() );
       sParaTemp.put("batch_num", "1" );
       sParaTemp.put("detail_data", AlipayQuery.query(out_trade_no)+"^"+amount+"^正常退款");
       try {
           String sHtmlText  = AlipaySubmit.buildRequest("","",sParaTemp);
           System.out.println(sHtmlText);
           return  "success";
       } catch (Exception e) {
           e.printStackTrace();
           return "fail";
       }
   }

}
