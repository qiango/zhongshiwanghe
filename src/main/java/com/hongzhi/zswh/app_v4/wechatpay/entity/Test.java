package com.hongzhi.zswh.app_v4.wechatpay.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.hongzhi.zswh.app_v4.wechatpay.service.JavaObjectXml;
import com.hongzhi.zswh.app_v4.wechatpay.service.WeChatPayService;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.date.DateFormat;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 20, 2016    10:16:54 AM
 */
public class Test {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTimeZone());
        System.out.println(cal.getTimeInMillis());
        System.out.println(System.currentTimeMillis());
        
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.DAY_OF_MONTH, 3);
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        System.out.println(cal.getTime());
        System.out.println(cal.getTimeInMillis());
//
//        cal.set(Calendar.MONTH, 6);
//        cal.set(Calendar.DAY_OF_MONTH, 5);
//        cal.set(Calendar.HOUR_OF_DAY, 23);
//        cal.set(Calendar.MINUTE, 59);
//        cal.set(Calendar.SECOND, 59);
//        
//        System.out.println(cal.getTime());
//        System.out.println(cal.getTimeInMillis());
//        
//
//        
//        cal.set(Calendar.ZONE_OFFSET,0);
//
//        System.out.println(cal.getTime());
//        System.out.println(cal.getTimeInMillis());
        
//        System.out.println(DateFormat.getDateFromMillis("zh", System.currentTimeMillis()));
//        millSecondToString();
                
    }
    
    public static  void millSecondToString (){
        Long currentMillSec = System.currentTimeMillis();
        Date date = new Date(currentMillSec);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd E HH:mm",Locale.ENGLISH);
        System.out.println(sdf.format(date).replaceAll("Fri", "周五"));
        
    }
    
    
}
