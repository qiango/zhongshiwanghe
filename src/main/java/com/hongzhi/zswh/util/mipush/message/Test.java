package com.hongzhi.zswh.util.mipush.message;

import com.hongzhi.zswh.util.mipush.config.DEVICE;

/**
 * Created by taylor on 7/19/16.
 * twitter: @taylorwang789
 */
public class Test {

    public static void main(String[] args) {
        SendMessage sendMessage = new SendMessage();
//        sendMessage.iOSSend();
//        sendMessage.broadcast();
        sendMessage.send();
        System.out.println("end");


    }
}