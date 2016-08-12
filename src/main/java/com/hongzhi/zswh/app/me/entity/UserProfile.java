package com.hongzhi.zswh.app.me.entity;

/**
 * Created with IntelliJ IDEA.
 * User: xuejian
 * Date: 2016/8/12
 * Time: 10:41
 * To change this template use File | Settings | File Templates.
 */
public class UserProfile {

    private Integer user_id;
    private String item_code;
    private String item_value;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getItem_value() {
        return item_value;
    }

    public void setItem_value(String item_value) {
        this.item_value = item_value;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }
}
