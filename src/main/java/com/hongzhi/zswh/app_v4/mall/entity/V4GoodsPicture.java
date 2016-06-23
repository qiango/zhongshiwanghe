package com.hongzhi.zswh.app_v4.mall.entity;

/**   Twitter : @taylorwang789 
 * Creat time : May 16, 2016    11:10:43 AM
 */
public class V4GoodsPicture {
    private Integer id;
//    private Integer goods_id;
    private String  picture_url;
    private Integer show_index;

//    private Integer status;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPicture_url() {
        return picture_url;
    }
    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }
    public Integer getShow_index() {
        return show_index;
    }
    public void setShow_index(Integer show_index) {
        this.show_index = show_index;
    }
   
}
