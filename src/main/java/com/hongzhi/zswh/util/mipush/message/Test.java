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
        MiMessageBroadcast  miMessageBroadcast = new MiMessageBroadcast(MessageType.NEWS);
        miMessageBroadcast.sendMessage("bbc news , hello","test broadcast");

        System.out.println("end");
    }
}
