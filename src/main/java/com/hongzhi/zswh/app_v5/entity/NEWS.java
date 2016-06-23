package com.hongzhi.zswh.app_v5.entity;

/**   Twitter : @taylorwang789 
 * Creat time : May 31, 2016    1:20:53 PM
 */
public class NEWS {
    
   // news  news_type 
    public static String TYPE_DOCUMENT="0";
    public static String TYPE_IMAGES="1";
    public static String TYPE_VIDEO="2";
    
    // news status 
    public static String STATUS_DELETE="0";
    public static String STATUS_PUBLISH="1";
    public static String STATUS_BLOCK="2";

    
    // news range 
    public static int RANGE_PUBLIC=0;
    public static int RANGE_CLUB=1;
    public static int RANGE_COMPETITION=2;
    
    public static int PAGE_SIZE = 10;
    
    public static String  PIC_HEAD="/pic.htmls?p=";
    public static String  VIDEO_HEAD="";
    
    
}
