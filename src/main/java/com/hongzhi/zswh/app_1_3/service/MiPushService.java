package com.hongzhi.zswh.app_1_3.service;

import com.hongzhi.zswh.app_1_3.dao.MiPushDao;
import com.hongzhi.zswh.app_1_3.entity.MiPushRegid;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.mipush.config.MessageType;
import com.hongzhi.zswh.util.mipush.message.MiMessageBroadcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: xuejian
 * Date: 2016/7/20
 * Time: 10:18
 * To change this template use File | Settings | File Templates.
 */
@Service("app_1_3_MiPushService")
public class MiPushService {
    @Autowired
    private MiPushDao miPushDao;

    public Object saveRegid(MiPushRegid miPushRegid) throws HongZhiException {

        miPushRegid.Vregid();
        miPushRegid.Vapp_type();

        int temp = 0;
        if ("IOS".equals(miPushRegid.getApp_type().toUpperCase())) {
            miPushRegid.setApp_type("1");
            miPushRegid.setStatus("1");
            temp = miPushDao.saveRegid(miPushRegid);
        } else if ("ANDROID".equals(miPushRegid.getApp_type().toUpperCase())) {
            miPushRegid.setApp_type("2");
            miPushRegid.setStatus("1");
            temp = miPushDao.saveRegid(miPushRegid);
        }
        if (1 == temp) {
            return null;
        } else {
            throw new HongZhiException("1011");//保存失败
        }

    }

    public Object cancelRegid(MiPushRegid miPushRegid) throws HongZhiException {
        miPushRegid.Vregid();
        miPushDao.cancelRegid(miPushRegid.getRegid());
        return null;
    }

    public void saveRegidOnLogIn(MiPushRegid miPushRegid) {

        if ("IOS".equals(miPushRegid.getApp_type().toUpperCase())) {
            miPushRegid.setApp_type("1");
            miPushRegid.setStatus("1");
        } else if ("ANDROID".equals(miPushRegid.getApp_type().toUpperCase())) {
            miPushRegid.setApp_type("2");
            miPushRegid.setStatus("1");
        }
        miPushDao.saveRegid(miPushRegid);
    }

    public String broadcast(String message_title, String message_url, String message_type) {

        MessageType[] types = MessageType.values();
        boolean verify_type = false;
        for (int i = 0; i < types.length ; i++) {
            if (!ObjectUtil.isEmpty(message_type) && message_type.toUpperCase().equals(types[i].name()) ) {
                verify_type = true ;
            }
        }

        if (verify_type) {
            MiMessageBroadcast broadcast = new MiMessageBroadcast(message_type.toUpperCase());
            broadcast.sendMessage(message_title,message_url);
        } else  {
            MiMessageBroadcast broadcast = new MiMessageBroadcast(MessageType.NEWS);
            broadcast.sendMessage(message_title,message_url);
        }
        return  "";
    }
}
