package com.hongzhi.zswh.app_v5.entity;

/**   Twitter : @taylorwang789 
 * Creat time : May 31, 2016    4:18:48 PM
 */
public class AdvertisementPropertiesEntity {
    
    private Integer property_id;
    private Integer ad_id ;
    private String property_code ;
    private String property_value ;
    
    public Integer getProperty_id() {
        return property_id;
    }
    public void setProperty_id(Integer property_id) {
        this.property_id = property_id;
    }
    public Integer getAd_id() {
        return ad_id;
    }
    public void setAd_id(Integer ad_id) {
        this.ad_id = ad_id;
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
    
}
