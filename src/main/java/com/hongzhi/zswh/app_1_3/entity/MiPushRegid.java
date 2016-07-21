package com.hongzhi.zswh.app_1_3.entity;

import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**
 * Created with IntelliJ IDEA.
 * User: xuejian
 * Date: 2016/7/20
 * Time: 10:28
 * To change this template use File | Settings | File Templates.
 */
public class MiPushRegid {
    private String user_id;
    private String regid;
    private String app_type;
    private String status;

    public String Vregid() throws HongZhiException {
        return ExcepUtil.verify(regid, "1053").toString();
    }
    public String Vapp_type() throws HongZhiException {
        return ExcepUtil.verify(app_type, "1058").toString();
    }
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
