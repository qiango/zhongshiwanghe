package com.hongzhi.zswh.app_v6.entity;

import java.util.ArrayList;
import java.util.List;

//import java.sql.Timestamp;
//import java.util.Map;

/**   Twitter : @taylorwang789 
 * Creat time : May 31, 2016    4:12:05 PM
 */
public class AdvertisementEntity {
    
    private Integer ad_id;
    private String  ad_name;
    private String  ad_type;
    private Integer ad_location;
//    private String  ad_status;
//    private Timestamp ad_start_time;
//    private Timestamp ad_end_time;
    private List<com.hongzhi.zswh.app_v6.entity.AdvertisementImageEntity> image_list = new ArrayList<>();
//    private Map<String,Object> property_map;
//    private List<AdvertisementPropertiesEntity> property_list;


    public Integer getAd_id() {
        return ad_id;
    }
    public void setAd_id(Integer ad_id) {
        this.ad_id = ad_id;
    }
    public String getAd_name() {
        return ad_name;
    }
    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }
    public String getAd_type() {
        return ad_type;
    }
    public void setAd_type(String ad_type) {
        this.ad_type = ad_type;
    }
    public Integer getAd_location() {
        return ad_location;
    }
    public void setAd_location(Integer ad_location) {
        this.ad_location = ad_location;
    }
    public List<com.hongzhi.zswh.app_v6.entity.AdvertisementImageEntity> getImage_list() {
        return image_list;
    }
    public void setImage_list(List<AdvertisementImageEntity> image_list) {
        this.image_list = image_list;
    }

    
//    
//   public void sortProperty(){
//       for(int i=0;i<property_list.size();i++){
//           property_map.put(property_list.get(i).getProperty_code(), property_list.get(i).getProperty_value());
//       }
//   }
    
    

}
