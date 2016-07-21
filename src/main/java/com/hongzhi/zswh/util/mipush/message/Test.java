package com.hongzhi.zswh.util.mipush.message;

import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.mipush.config.DEVICE;

/**
 * Created by taylor on 7/19/16.
 * twitter: @taylorwang789
 */
public class Test {

    public static void main(String[] args) {
//        SendMessage sendMessage = new SendMessage();
////        sendMessage.iOSSend();
////        sendMessage.broadcast();
//        sendMessage.send();
//        System.out.println("end");

        String s1 = "abc";
        String s2 = null;
        String s3 = null;
        System.out.println(ObjectUtil.coalesce(s1,s2,s3,"fjdka" ));


    }
}
