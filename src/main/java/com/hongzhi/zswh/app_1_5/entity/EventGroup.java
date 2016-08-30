package com.hongzhi.zswh.app_1_5.entity;

import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**
 * Created by taylor on 7/28/16.
 * twitter: @taylorwang789
 */
public class EventGroup {

    private String event_id ;
    private String user_id ;
    private String group_id ;
    private String group_level ;
    private String status ;


    public String Vevent_id() throws HongZhiException {
        return ExcepUtil.verify(event_id, "1089").toString();
    }

    public String Vuser_id() throws HongZhiException {
        return ExcepUtil.verify(user_id, "1090").toString();
    }

    public String Vgroup_id() throws HongZhiException {
        return ExcepUtil.verify(group_id, "1091").toString();
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_level() {
        return group_level;
    }

    public void setGroup_level(String group_level) {
        this.group_level = group_level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
