package com.hongzhi.zswh.app_1_4.entity;

/**
 * Created by taylor on 8/2/16.
 * twitter: @taylorwang789
 */
public class EventJoinMember {

    private Integer user_id;
    private String name="";
    private String profile_image="";
    private String phone="";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
       // this.profile_image = DictionaryUtil.find("picHead","data_alias","zh") + profile_image;
        this.profile_image =  profile_image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
