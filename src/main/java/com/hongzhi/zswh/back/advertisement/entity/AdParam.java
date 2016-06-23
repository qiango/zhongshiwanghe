package com.hongzhi.zswh.back.advertisement.entity;

import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 31, 2016    4:08:18 PM
 */
public class AdParam {
   private Integer  ad_id;
   private String  page_number;
   private String  page_size;
   private String  ad_name;
   private String  ad_type;
   private String  ad_status;
   private Integer image_id;
   private String  image_url;
   private Integer image_height;
   private Integer image_width;
   private String  image_index;
   private String  image_text;
   private Integer property_id;
   private String  property_code;
   private String  property_value;
   private String  ad_start_time;
   private String  ad_end_time;
   private String information_link;
   private Integer ad_location;
   private String ad_status_name;

   private String submit_file;
   private String ad_width_high;   

   private String ad_type_name;
   private String location_name;
   
   private String  information_link_ad;
   private String  image_information;


public Integer VAd_id() throws HongZhiException {
	return (Integer)ExcepUtil.verify(ad_id, "");
}

public String VPage_number() {
    return page_number;
}


public String VPage_size() {
    return page_size;
}
public String VAd_name() {
    return ad_name;
}
public String VAd_type() {
    return ad_type;
}
public String VAd_status() {
    return ad_status;
}
public Integer VImage_id() {
    return image_id;
}
public String VImage_url() {
    return image_url;
}
public String VImage_index() {
    return image_index;
}
public Integer VProperty_id() {
    return property_id;
}
public String VProperty_code() {
    return property_code;
}
public String VProperty_value() {
    return property_value;
}


public String getPage_number() {
    return page_number;
}
public void setPage_number(String page_number) {
    this.page_number = page_number;
}
public String getPage_size() {
    return page_size;
}
public void setPage_size(String page_size) {
    this.page_size = page_size;
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

public String getImage_url() {
    return image_url;
}
public void setImage_url(String image_url) {
    this.image_url = image_url;
}
public String getImage_index() {
    return image_index;
}
public void setImage_index(String image_index) {
    this.image_index = image_index;
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
public Integer getAd_id() {
    return ad_id;
}
public void setAd_id(Integer ad_id) {
    this.ad_id = ad_id;
}
public Integer getImage_id() {
    return image_id;
}
public void setImage_id(Integer image_id) {
    this.image_id = image_id;
}
public Integer getProperty_id() {
    return property_id;
}
public void setProperty_id(Integer property_id) {
    this.property_id = property_id;
}
public String getAd_start_time() {
    return ad_start_time;
}
public void setAd_start_time(String ad_start_time) {
    this.ad_start_time = ad_start_time;
}
public String getAd_end_time() {
    return ad_end_time;
}
public void setAd_end_time(String ad_end_time) {
    this.ad_end_time = ad_end_time;
}
public String getInformation_link() {
    return information_link;
}
public void setInformation_link(String information_link) {
    this.information_link = information_link;
}
public Integer getImage_height() {
    return image_height;
}
public void setImage_height(Integer image_height) {
    this.image_height = image_height;
}
public Integer getImage_width() {
    return image_width;
}
public void setImage_width(Integer image_width) {
    this.image_width = image_width;
}

public String getAd_status_name() {
	return ad_status_name;
}
public void setAd_status_name(String ad_status_name) {
	this.ad_status_name = ad_status_name;
}

public String getSubmit_file() {
	return submit_file;
}
public void setSubmit_file(String submit_file) {
	this.submit_file = submit_file;
}
public String getImage_text() {
	return image_text;
}
public void setImage_text(String image_text) {
	this.image_text = image_text;
}
public Integer getAd_location() {
	return ad_location;
}
public void setAd_location(Integer ad_location) {
	this.ad_location = ad_location;
}
public String getAd_width_high() {
	return ad_width_high;
}
public void setAd_width_high(String ad_width_high) {
	this.ad_width_high = ad_width_high;
}

public String getAd_type_name() {
	return ad_type_name;
}
public void setAd_type_name(String ad_type_name) {
	this.ad_type_name = ad_type_name;
}
public String getLocation_name() {
	return location_name;
}
public void setLocation_name(String location_name) {
	this.location_name = location_name;
}
public String getInformation_link_ad() {
	return information_link_ad;
}
public void setInformation_link_ad(String information_link_ad) {
	this.information_link_ad = information_link_ad;
}
public String getImage_information() {
	return image_information;
}
public void setImage_information(String image_information) {
	this.image_information = image_information;
}



  
}
