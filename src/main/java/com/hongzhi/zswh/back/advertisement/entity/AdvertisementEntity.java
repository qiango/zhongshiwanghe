package com.hongzhi.zswh.back.advertisement.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hongzhi.zswh.util.basic.ObjectUtil;

/**   Twitter : @taylorwang789 
 * Creat time : May 31, 2016    4:12:05 PM
 */
public class AdvertisementEntity {
    
    private Integer ad_id;
    private String  ad_name;
    private String  ad_type;
    private String  ad_status;
    private String ad_location;
    private Timestamp ad_start_time;
    private Timestamp ad_end_time;
    private List<AdvertisementImageEntity> image_list;
    //位置列表
    private List<Map<String,Object>> adLocationList;
    //像素列表
    private List<Map<String,String>> pixel_list;
    private Map<String,Object> property_map;
    private List<AdvertisementPropertiesEntity> property_list;

    
    
   public void sortProperty(){
       if(!ObjectUtil.isEmpty(property_list)){
           property_map = new HashMap<>();
           for(int i=0;i<property_list.size();i++){
               property_map.put(property_list.get(i).getProperty_code(), property_list.get(i).getProperty_value());
           }
       }
   }
    
    
    
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
    public String getAd_status() {
        return ad_status;
    }
    public void setAd_status(String ad_status) {
        this.ad_status = ad_status;
    }
    public Timestamp getAd_start_time() {
        return ad_start_time;
    }
    public void setAd_start_time(Timestamp ad_start_time) {
        this.ad_start_time = ad_start_time;
    }
    public Timestamp getAd_end_time() {
        return ad_end_time;
    }
    public void setAd_end_time(Timestamp ad_end_time) {
        this.ad_end_time = ad_end_time;
    }
    public List<AdvertisementImageEntity> getImage_list() {
        return image_list;
    }
    public void setImage_list(List<AdvertisementImageEntity> image_list) {
        this.image_list = image_list;
    }
    public Map<String, Object> getProperty_map() {
        return property_map;
    }
    public void setProperty_map(Map<String, Object> property_map) {
        this.property_map = property_map;
    }
    public List<AdvertisementPropertiesEntity> getProperty_list() {
        return property_list;
    }
    public void setProperty_list(List<AdvertisementPropertiesEntity> property_list) {
        this.property_list = property_list;
    }



	public String getAd_location() {
		return ad_location;
	}



	public void setAd_location(String ad_location) {
		this.ad_location = ad_location;
	}



	public List<Map<String,Object>> getAdLocationList() {
		return adLocationList;
	}



	public void setAdLocationList(List<Map<String,Object>> adLocationList) {
		this.adLocationList = adLocationList;
	}



	public List<Map<String,String>> getPixel_list() {
		return pixel_list;
	}



	public void setPixel_list(List<Map<String,String>> pixel_list) {
		this.pixel_list = pixel_list;
	}








    
    

}
