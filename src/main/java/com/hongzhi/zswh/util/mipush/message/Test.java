package com.hongzhi.zswh.util.mipush.message;

import com.hongzhi.zswh.util.mipush.config.MessageType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taylor on 7/22/16.
 * twitter: @taylorwang789
 */
public class Test {
    public static void main(String[] args) {
//        MiMessageBroadcast  miMessageBroadcast = new MiMessageBroadcast(MessageType.NEWS);
//        miMessageBroadcast.sendMessage("mi api test","http://dev.xiaomi.com/doc/?p=533");

        MessageType[] types = MessageType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println(types[i].name());
            System.out.println("COMPETITION".equals(types[i].name()));
        }

        System.out.println("end");
    }
}
