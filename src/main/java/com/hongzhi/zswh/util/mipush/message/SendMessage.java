package com.hongzhi.zswh.util.mipush.message;

import com.hongzhi.zswh.util.mipush.config.DEVICE;
import com.hongzhi.zswh.util.mipush.config.MiPushConfig;
import com.xiaomi.push.sdk.ErrorCode;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by taylor on 7/13/16.
 * twitter: @taylorwang789
 */
public class SendMessage {


    public void send() {
        Constants.useOfficial();
//        Constants.useSandbox();
        Message message = new Message.Builder()
                .title("test")
                .description("hello").payload("msg")
                .restrictedPackageName("com.chengjubei.activity")
                .notifyType(1)     // 使用默认提示音提示
                .build();

        System.out.println(MiPushConfig.appSecret(DEVICE.Android));
        Sender sender = new Sender(MiPushConfig.appSecret(DEVICE.Android));
        try {
            Result result = sender.send(message, "K8IZ6TzKBtRHGOUnodH/1Yl9EmEvzIWtTBkzDZYp3qU=", 0);
            ErrorCode ec = result.getErrorCode();
            System.out.println(ec.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    public void iOSSend(){
//        Constants.useOfficial();
        Constants.useSandbox();
        Message message = new Message.IOSBuilder()
                .description("sandbox")
                .soundURL("default")
                .badge(34526754)
                .category("action")
                .extra("key","value")
                .build();
        System.out.println(MiPushConfig.appSecret(DEVICE.iOS));
        Sender sender = new Sender(MiPushConfig.appSecret(DEVICE.iOS));

        try {
            Result result =  sender.send(message,"GoD8C4zSJL/l5rGvEb4Y1yNC2mT0YHtEhXZcTXlliqo=",0);
            ErrorCode ec = result.getErrorCode();
            System.out.println(ec.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void broadcast(){
        Constants.useSandbox();
        Message message = new Message.IOSBuilder()
                .description("from sandbox")
                .soundURL("default")
                .badge(34526754)
                .category("action")
                .extra("key","value")
                .build();
        System.out.println(MiPushConfig.appSecret(DEVICE.iOS));
        Sender sender = new Sender(MiPushConfig.appSecret(DEVICE.iOS));

        try {
            Result result = sender.broadcast(message,"broadcast test",0);
            ErrorCode ec = result.getErrorCode();
            System.out.println(ec.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}
