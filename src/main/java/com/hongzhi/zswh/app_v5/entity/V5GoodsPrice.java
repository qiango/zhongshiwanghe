package com.hongzhi.zswh.app_v5.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.date.DateFormat;

/**   Twitter : @taylorwang789 
 * Creat time : May 16, 2016    11:16:50 AM
 */
public class V5GoodsPrice {
    private Integer id ;
    private Integer goods_id  ;
    private Integer properties_id ;
    private Double  goods_origin_price ;//原价
    private Double  goods_current_price ;//现价
    private Integer discount_id ;
    private Integer discount_type;
   // private Double  param;
   // private String  method;

    Map<String,Object>  property_map = new HashMap<>();
    
    List<V5GoodsProperties> properties = new ArrayList<>();
    

    
    public void  sortProperties(String language){
        for(int i=0;i<properties.size();i++){
            property_map.put(properties.get(i).getProperty_code(), properties.get(i).getProperty_value());
        }
        if(!ObjectUtil.isEmpty(property_map.get("begin_date"))){
            property_map.put("begin_date", DateFormat.getDateFromMillis(language, Long.parseLong(property_map.get("begin_date").toString())));
        }
        if(!ObjectUtil.isEmpty(property_map.get("due_date"))){
            property_map.put("due_date", DateFormat.getDateFromMillis(language, Long.parseLong(property_map.get("due_date").toString())));
        }
        
        String on_shelf_time_str = property_map.get("on_shelf_time").toString();
        String off_shelf_time_str = property_map.get("off_shelf_time").toString();
        if(!ObjectUtil.isEmpty(on_shelf_time_str) && !ObjectUtil.isEmpty(off_shelf_time_str)){
            Long  now_millsecond = System.currentTimeMillis();
            Long  on_shelf_time = Long.parseLong(on_shelf_time_str);
            Long  off_shelf_time = Long.parseLong(off_shelf_time_str);
            if( on_shelf_time < now_millsecond && now_millsecond<off_shelf_time ){
                property_map.put("validity_time",0);
            }else if(on_shelf_time > now_millsecond){
                property_map.put("validity_time",-1);
            }else{
                property_map.put("validity_time",1);
            }
        }else{
            property_map.put("validity_time",null);
        }
        
        properties = null;
    }
    
    
   // getter & setter  
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getProperties_id() {
        return properties_id;
    }

    public void setProperties_id(Integer properties_id) {
        this.properties_id = properties_id;
    }

    public Double getGoods_origin_price() {
		return goods_origin_price;
	}

	public void setGoods_origin_price(Double goods_origin_price) {
		this.goods_origin_price = goods_origin_price;
	}

	public Integer getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(Integer discount_id) {
        this.discount_id = discount_id;
    }

    public List<V5GoodsProperties> getProperties() {
        return properties;
    }

    public void setProperties(List<V5GoodsProperties> properties) {
        this.properties = properties;
    }

    public Integer getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(Integer discount_type) {
        this.discount_type = discount_type;
    }

//    public Double getParam() {
//        return param;
//    }
//
//    public void setParam(Double param) {
//        this.param = param;
//    }

//    public String getMethod() {
//        return method;
//    }
//
//    public void setMethod(String method) {
//        this.method = method;
//    }

	public Double getGoods_current_price() {
		return goods_current_price;
	}

	public void setGoods_current_price(Double goods_current_price) {
		this.goods_current_price = goods_current_price;
	}
    

}
