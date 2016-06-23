package com.hongzhi.zswh.app_v5.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hongzhi.zswh.app_v5.service.V5iQiYiService;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 12, 2016    5:29:33 PM
 */
public class Test {
    public static void main(String[] args) {
//        String line = "ThisorderwasplacedforQT3000OK";
////        String pattern = "([A-Za-z])(\\d+)(.*)";
//        String pattern = "([A-Za-z]+)(\\d+)(\\w+)";
//
//        // 创建 Pattern 对象
//        Pattern r = Pattern.compile(pattern);
//
//        // 现在创建 matcher 对象
//        Matcher m = r.matcher(line);
//        if (m.find( )) {
//            System.out.println(m.groupCount());
//           System.out.println("Found value: " + m.group(0) );
//           System.out.println("Found value: " + m.group(1) );
//           System.out.println("Found value: " + m.group(2) );
//           System.out.println("Found value: " + m.group(3) );
//        } else {
//           System.out.println("NO MATCH");
//        }
        V5iQiYiService  iqiyi = new V5iQiYiService();
        iqiyi.getVideoForShare("");
        
        
        
    }
}
