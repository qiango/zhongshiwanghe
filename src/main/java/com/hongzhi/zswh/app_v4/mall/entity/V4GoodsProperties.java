package com.hongzhi.zswh.app_v4.mall.entity;

/**   Twitter : @taylorwang789 
 * Creat time : May 16, 2016    11:18:36 AM
 */
public class V4GoodsProperties {

    private Integer properties_id ;
    private Integer goods_id ;
    private String  property_code ;
    private String  property_value;
    private Integer price_effect ;
     
     
    public Integer getProperties_id() {
        return properties_id;
    }
    public void setProperties_id(Integer properties_id) {
        this.properties_id = properties_id;
    }
    public Integer getGoods_id() {
        return goods_id;
    }
    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }
    public String getProperty_code() {
        return property_code;
    }
    public void setProperty_code(String property_code) {
        this.property_code = property_code;
    }
    public String getProperty_value() {
        return property_value;
    }
    public void setProperty_value(String property_value) {
        this.property_value = property_value;
    }
    public Integer getPrice_effect() {
        return price_effect;
    }
    public void setPrice_effect(Integer price_effect) {
        this.price_effect = price_effect;
    }
}
