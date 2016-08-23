package com.hongzhi.zswh.easemob.entity;

/**
 * Created with IntelliJ IDEA.
 * User: xuejian
 * Date: 2016/8/22
 * Time: 16:22
 * To change this template use File | Settings | File Templates.
 */
public class RestUser {
    private Integer    user_id ;
    private String     user_login_name ;
    private String     rest_user_name  ;
    private String    rest_user_password  ;


    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_login_name() {
        return user_login_name;
    }

    public void setUser_login_name(String user_login_name) {
        this.user_login_name = user_login_name;
    }

    public String getRest_user_name() {
        return rest_user_name;
    }

    public void setRest_user_name(String rest_user_name) {
        this.rest_user_name = rest_user_name;
    }

    public String getRest_user_password() {
        return rest_user_password;
    }

    public void setRest_user_password(String rest_user_password) {
        this.rest_user_password = rest_user_password;
    }
}
