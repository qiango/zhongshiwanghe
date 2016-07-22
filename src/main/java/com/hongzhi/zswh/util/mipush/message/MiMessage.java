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
public class MiMessage {


    private List<AppRegid> regidList  = new ArrayList<>();

    private String msgContent="";

    private String iOS = "1";

    private String android = "2";

    private String messageType = "";

    public MiMessage(MessageType messageType) {
        this.messageType = messageType.toString();
    }

    public void sendMessage(String content , List<AppRegid> regids){
        msgContent = content;
        regidList = regids;
        if (!ObjectUtil.isEmpty(regidList) && regidList.size()>0) {
            send();
        }
    }

    public void send() {
        List<String> regids_iOS = new ArrayList<>();
        List<Integer> regids_iOS_badge = new ArrayList<>();
        List<String> regids_Android = new ArrayList<>();

        for (int i = 0; i < regidList.size(); i++) {
            if(regidList.get(i).getApp_type().equals(iOS)){
                regids_iOS.add(regidList.get(i).getRegid());
                regids_iOS_badge.add((Integer) ObjectUtil.getProperty(regidList.get(i).getNoti_count(),0));
            }
            if(regidList.get(i).getApp_type().equals(android)){
                regids_Android.add(regidList.get(i).getRegid());
            }
        }
        if (!ObjectUtil.isEmpty(regids_iOS) && regids_iOS.size()>0) {
            sendiOS(regids_iOS, regids_iOS_badge );
        }
        if (!ObjectUtil.isEmpty(regids_Android) && regids_Android.size() > 0 ) {
            sendAndroid(regids_Android);
        }
    }


    private void sendiOS(List<String> regids, List<Integer> regids_iOS_badge){
        for (int i = 0; i < regids.size() ; i++) {
            sendiOS(regids.get(i), (Integer) ObjectUtil.getProperty(regids_iOS_badge.get(i),0));
        }
    }


    private void sendiOS(String regids,Integer regids_iOS_badge){
        Constants.useOfficial();
        Message message = new Message.IOSBuilder()
                .description(msgContent)
                .soundURL("default")
                .badge(regids_iOS_badge)
                .category("action")
                .extra("pushType", String.valueOf(ObjectUtil.coalesce(messageType)))
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

    private void sendAndroid(List<String> regids){
        Constants.useOfficial();
        Message message = new Message.Builder()
                .title("ChengJuBei")
                .description(msgContent).payload("msg")
                .restrictedPackageName("com.chengjubei.activity")
                .notifyType(1)     // 使用默认提示音提示
                .extra("pushType", String.valueOf(ObjectUtil.coalesce(messageType)))
                .extra("content","")
                .build();

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








