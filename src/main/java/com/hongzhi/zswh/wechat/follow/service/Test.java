package com.hongzhi.zswh.wechat.follow.service;

import com.hongzhi.zswh.wechat.follow.entity.WECHAT;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 3, 2016    5:13:46 PM
 */
public class Test {
    
    public static void main(String[] args) {
        System.out.println(WECHAT.ACCESS_TOKEN);
        WeChatTokenTimer time = new WeChatTokenTimer();
        time.runTask();
        System.out.println(WECHAT.ACCESS_TOKEN);
       
    }

}
