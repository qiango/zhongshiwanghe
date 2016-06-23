package com.hongzhi.zswh.app_v5.entity;

import java.util.HashMap;
import java.util.Map;

public class V5Coupon {
	private Integer coupon_id;
	private String coupon_name;
	private Integer coupon_type;
	private Integer use_scope;
	private String coupon_describe;
	
	private Integer discount_type;
	private Map<String,Object>  coupon_properties = new HashMap<>();
	public Integer getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(Integer coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getCoupon_name() {
		return coupon_name;
	}
	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}
	public String getCoupon_describe() {
		return coupon_describe;
	}
	public void setCoupon_describe(String coupon_describe) {
		this.coupon_describe = coupon_describe;
	}
	public Map<String, Object> getCoupon_properties() {
		return coupon_properties;
	}
	public void setCoupon_properties(Map<String, Object> coupon_properties) {
		this.coupon_properties = coupon_properties;
	}
	public Integer getDiscount_type() {
		return discount_type;
	}
	public void setDiscount_type(Integer discount_type) {
		this.discount_type = discount_type;
	}
	public Integer getCoupon_type() {
		return coupon_type;
	}
	public void setCoupon_type(Integer coupon_type) {
		this.coupon_type = coupon_type;
	}
    public Integer getUse_scope() {
        return use_scope;
    }
    public void setUse_scope(Integer use_scope) {
        this.use_scope = use_scope;
    }
	
}
