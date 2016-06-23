package com.hongzhi.zswh.app_v4.alipay.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hongzhi.zswh.app_v4.alipay.config.AlipayConfig;
import com.hongzhi.zswh.app_v4.alipay.sign.RSA;
import com.hongzhi.zswh.util.basic.ObjectUtil;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 19, 2016    12:47:22 PM
 */
public class QueryTest {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String   dateStr = sdf.format(new Date(System.currentTimeMillis()));
        String http = "https://openapi.alipay.com/gateway.do?";

        Map<String,Object> map = new HashMap<>();
        map.put("out_trade_no", "CJB16061106504574");
        String biz_content = ObjectUtil.toJson(map);

        String str = ""
           +"app_id="+AlipayConfig.appid("Android")
           +"&biz_content="+biz_content
           +"&charset="+AlipayConfig.input_charset
           +"&method="+"alipay.trade.query"
           +"&sign_type="+AlipayConfig.sign_type
           +"&timestamp="+dateStr
           +"&version="+"1.0";


       try {
        String sign="&sign="+URLEncoder.encode(RSA.sign(str, AlipayConfig.private_key, AlipayConfig.input_charset),"UTF-8") ;
        
        System.out.println(http+str+sign);
        
        
    } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    }
}
