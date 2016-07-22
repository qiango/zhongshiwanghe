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
import java.util.ArrayList;
import java.util.List;


/**
 * Created by taylor on 7/20/16.
 * twitter: @taylorwang789
 */
public class MiMessageBroadcast {


    private String msgContent="";

    private String messageType = "";

    private String msgUrl = "";

    public MiMessageBroadcast(MessageType messageType) {
        this.messageType = messageType.toString();
    }

    public MiMessageBroadcast(String messageType) {
        this.messageType = messageType ;
    }

    public void sendMessage(String content ,String url){
        msgContent = content;
        msgUrl = url;
        send();
    }

    public void send() {
        sendIOS();
        sendAndroid();
    }



    private void sendIOS(){
        Constants.useOfficial();
        Message message = new Message.IOSBuilder()
                .description(msgContent)
                .soundURL("default")
//                .badge(1)
                .category("action")
                .extra("pushType", String.valueOf(ObjectUtil.coalesce(messageType)))
                .extra("content",ObjectUtil.coalesce(msgUrl,"").toString())
                .build();

        Sender sender = new Sender(MiPushConfig.appSecret(DEVICE.iOS));
        try {
            Result result =  sender.broadcastAll(message,0);
            ErrorCode ec = result.getErrorCode();
            System.out.println("iOS broadcast:"+ec.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void sendAndroid(){
        Constants.useOfficial();
        Message message = new Message.Builder()
                .title("ChengJuBei")
                .description(msgContent).payload("msg")
                .restrictedPackageName("com.chengjubei.activity")
                .notifyType(1)     // 使用默认提示音提示
                .extra("pushType", String.valueOf(ObjectUtil.coalesce(messageType)))
                .extra("content",ObjectUtil.coalesce(msgUrl,"").toString())
                .build();

        Sender sender = new Sender(MiPushConfig.appSecret(DEVICE.Android));
        try {
            Result result = sender.broadcastAll(message,0);
            ErrorCode ec = result.getErrorCode();
            System.out.println("Android broadcast:"+ec.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}








