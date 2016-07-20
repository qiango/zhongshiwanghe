package com.hongzhi.zswh.util.mipush.message;

import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.mipush.config.DEVICE;
import com.hongzhi.zswh.util.mipush.config.MiPushConfig;
import com.xiaomi.push.sdk.ErrorCode;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by taylor on 7/20/16.
 * twitter: @taylorwang789
 */
public class MiMessage {

    @Autowired
    private MessageDao messageDao;

    private List<AppRegid> regidList  = new ArrayList<>();

    private String msgContent;

    private String iOS = "";

    private String android = "";

    public void setMessage(String content , Integer user_id){
        try {
            if (ObjectUtil.isEmpty(user_id)) {
               throw new HongZhiException("");
            }
            regidList = messageDao.getRegId(Arrays.asList(user_id));
            msgContent = content;
        } catch (HongZhiException e) {
            e.printStackTrace();
        }
     }

    public void setMessage(String content , List<Integer> user_id){
        try {
            if (ObjectUtil.isEmpty(user_id) && user_id.size() == 0 ) {
                throw new HongZhiException("");
            }
            regidList = messageDao.getRegId(user_id);
            msgContent = content;
        } catch (HongZhiException e) {
            e.printStackTrace();
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
        sendiOS(regids_iOS, regids_iOS_badge );
        sendAndroid(regids_Android);
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
                .badge(regids_iOS_badge+1)
                .category("action")
                .extra("key","value")
                .build();
        System.out.println(MiPushConfig.appSecret(DEVICE.iOS));
        Sender sender = new Sender(MiPushConfig.appSecret(DEVICE.iOS));

        try {
            Result result =  sender.send(message,regids,0);
            ErrorCode ec = result.getErrorCode();
            System.out.println(ec.getDescription());
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
                .build();

        Sender sender = new Sender(MiPushConfig.appSecret(DEVICE.Android));
        try {
            Result result = sender.send(message, regids, 0);
            ErrorCode ec = result.getErrorCode();
            System.out.println(ec.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}








