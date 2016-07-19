package com.hongzhi.zswh.util.mipush.config;

/**
 * Created by taylor on 7/13/16.
 * twitter: @taylorwang789
 */
public class MiPushConfig {


    public static String appID(DEVICE device){
        String appid = "" ;
        switch (device.toString().toLowerCase()) {
            case "iOS" : appid = MiPushiOS.AppID ; break ;
        }
        return  appid;
    }


    public static String appKey(DEVICE device){
        String appkey= "" ;
        switch (device.toString().toLowerCase()) {
            case "iOS" : appkey = MiPushiOS.AppID ; break ;
        }
        return  appkey ;
    }

    public static String appSecret(DEVICE device){
        String appsecret = "" ;
        switch (device.toString().toLowerCase()) {
            case "iOS" : appsecret = MiPushiOS.AppID ; break ;
        }
        return  appsecret ;
    }




}
