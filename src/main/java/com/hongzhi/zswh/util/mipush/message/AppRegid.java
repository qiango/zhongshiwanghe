package com.hongzhi.zswh.util.mipush.message;

/**
 * Created by taylor on 7/20/16.
 * twitter: @taylorwang789
 */
public class AppRegid {
    private Integer user_id;
    private String regid;
    private String status;
    private String app_type;
    private Integer noti_count;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public Integer getNoti_count() {
        return noti_count;
    }

    public void setNoti_count(Integer noti_count) {
        this.noti_count = noti_count;
    }
}

