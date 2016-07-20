package com.hongzhi.zswh.util.mipush.message;

import com.hongzhi.zswh.util.mipush.config.DEVICE;
import com.hongzhi.zswh.util.mipush.config.MiPushConfig;
import com.sun.tools.internal.jxc.ap.Const;
import com.xiaomi.push.sdk.ErrorCode;
import com.xiaomi.xmpush.server.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taylor on 7/13/16.
 * twitter: @taylorwang789
 */
public class SendMessage {


    public void send() {
//        Constants.useOfficial();
        Constants.useSandbox();
        Message message = new Message.Builder()
                .title("test")
                .description("descjfkdasj").payload("msg")
                .restrictedPackageName("")
                .passThrough(1)  //消息使用透传方式
                .notifyType(1)     // 使用默认提示音提示
                .build();

        Sender sender = new Sender(MiPushConfig.appSecret(DEVICE.iOS));
        try {
            sender.send(message, "GoD8C4zSJL/l5rGvEb4Y1yNC2mT0YHtEhXZcTXlliqo=", 0);
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

}
