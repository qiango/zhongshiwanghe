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
        Message message = new Message.Builder()
                .title("ChengJuBei")
                .description("messagejkfdjak").payload("msg")
                .restrictedPackageName("com.chengjubei.activity")
                .notifyType(1)     // 使用默认提示音提示
                .extra("pushType", MessageType.NOTIFICATION.toString() )
                .extra("content","")
                .extra(Constants.EXTRA_PARAM_NOTIFY_FOREGROUND, "0")
                .build();

        String regids = "RKEx4ZPd8z/k2NAXIZeFlu/07FOuhT1I7eyttXMeSIE=";
        Sender sender = new Sender(MiPushConfig.appSecret(DEVICE.Android));
        try {
            Result result = sender.send(message, regids, 0);
            ErrorCode ec = result.getErrorCode();
            System.out.println("Android:"+ec.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
