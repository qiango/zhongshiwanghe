package com.hongzhi.zswh.app_v4.wechatpay.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

/**   Twitter : @taylorwang789 
 * Creat time : May 18, 2016    4:17:22 PM
 */
public class WeChatConfig {
    
   public static String iOS_appid="wxf335e214a840e01f";
   public static String Android_appid="wx25bd909af595895f";
   public static String iOS_mch_id="1343259601";
   public static String Android_mch_id="1343460501";
   public static String device_info="WEB";
   public static String key="ev75TuCCxVwzBRnDAAkn1XvO9lYZyue0";
   public static String notify_url = "";
   
   static{
       Properties prop = new Properties();
       String resource = "config.properties";
       InputStream is ;
       try {
           is = Resources.getResourceAsStream(resource);
           prop.load(is);
           notify_url=prop.getProperty("NotifyURL")+"/v4/wechat/notify.htmls";
       } catch (IOException e) {
           e.printStackTrace();
       }
   }


}
