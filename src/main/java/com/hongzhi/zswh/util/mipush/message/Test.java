package com.hongzhi.zswh.util.mipush.message;

import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.mipush.config.DEVICE;
import com.hongzhi.zswh.util.mipush.config.MessageType;
import com.hongzhi.zswh.util.mipush.config.MiPushConfig;
import com.xiaomi.push.sdk.ErrorCode;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by taylor on 7/22/16.
 * twitter: @taylorwang789
 */
public class Test {
    public static void main(String[] args) {
        Constants.useOfficial();
//        String regids = "dgVKkBBAdMeSGivTVh+6qU23P+EXST3QEPD+JAng7no=";
        String regids =  "FHalADCHGdsUbM78HrfR8PO0+nX60UAwqWOgxTG1XHA=";
        Message message = new Message.IOSBuilder()
                .description("jfksajf")
                .soundURL("default")
                .badge(1)
                .category("action")
                .extra("pushType", String.valueOf(MessageType.NOTIFICATION))
                .extra("content","")
                .build();

        Sender sender = new Sender(MiPushConfig.appSecret(DEVICE.iOS));
        try {
            Result result =  sender.send(message,regids,0);
            ErrorCode ec = result.getErrorCode();
            System.out.println("iOS:"+regids+":"+ec.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
