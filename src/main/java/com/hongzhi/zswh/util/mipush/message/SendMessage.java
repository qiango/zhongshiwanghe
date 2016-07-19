package com.hongzhi.zswh.util.mipush.message;

import com.hongzhi.zswh.util.mipush.config.DEVICE;
import com.hongzhi.zswh.util.mipush.config.MiPushConfig;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Sender;
import com.xiaomi.xmpush.server.TargetedMessage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taylor on 7/13/16.
 * twitter: @taylorwang789
 */
public class SendMessage {


    public void send(){
        Constants.useOfficial();
         Message message = new Message.Builder()
                .title("test")
                .description("descjfkdasj").payload("msg")
                .restrictedPackageName("")
                .passThrough(1)  //消息使用透传方式
                .notifyType(1)     // 使用默认提示音提示
                .build();

        Sender sender = new Sender(MiPushConfig.appSecret(DEVICE.iOS));
        try {
            sender.send(message,"",0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}
