package com.hongzhi.zswh.app_v5.entity;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 6, 2016    5:11:26 PM
 */
public class IQIYI {
    
    public static String ACCESS_TOKEN = "";
    public static String APP_KEY= "8b830c2fe10b44bc9c4a400f032f8ee4";
    public static String APP_SECRET= "b4178d68469d4fe550ffba6a37a78325";
    
    
    public static String  URL_ACCESS_TOKEN= "https://openapi.iqiyi.com/api/iqiyi/authorize";
    public static String  URL_VIDEOS_INFO= "http://openapi.iqiyi.com/api/file/videoListForExternal";
    public static String  URL_URLLIST= "http://openapi.iqiyi.com/api/file/urllist";
    public static String  URL_FULL_STATUS = "http://openapi.iqiyi.com/api/file/fullStatus";
    
    
 // 视频格式
//    pc 端 以及移动端 m3u8:
//    96-->极速 
//    1 --> 流畅
//    2 --> 高清 
//    3 --> 超清  Ultra-clear
//    4 --> 720P 
//    5 --> 1080P
//    移动端 mp4:
//    1 --> 流畅 (200K)
//    2 --> 高清(400K)
    public static String  MP4_1 ="mp4";
    public static String  MP4_2 ="mp4_HD";
    public static String  M3U8_1="m3u8_fluent";
    public static String  M3U8_2="m3u8_HD";
    public static String  M3U8_3="m3u8_UC";
    public static String  M3U8_4="m3u8_720P";
    public static String  M3U8_5="m3u8_1080P";
    public static String  M3U8_96="m3u8_fast";


}
