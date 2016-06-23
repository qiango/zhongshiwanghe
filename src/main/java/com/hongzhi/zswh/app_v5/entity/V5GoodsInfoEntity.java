package com.hongzhi.zswh.app_v5.entity;


import java.util.List;
import java.util.Map;


public class V5GoodsInfoEntity {

	private Integer goods_id;
	private String goods_name;
	private String goods_describe;
	private String goods_origin_price;
	private String goods_current_price;
	private Map<String,Object>  goods_properties; 
	private List<V5GoodsImage> goods_image;
	private List<V5Coupon> coupon_list;
	
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_describe() {
		return goods_describe;
	}
	public void setGoods_describe(String goods_describe) {
		this.goods_describe = goods_describe;
	}
	public String getGoods_origin_price() {
		return goods_origin_price;
	}
	public void setGoods_origin_price(String goods_origin_price) {
		this.goods_origin_price = goods_origin_price;
	}
	public String getGoods_current_price() {
		return goods_current_price;
	}
	public void setGoods_current_price(String goods_current_price) {
		this.goods_current_price = goods_current_price;
	}
	public List<V5GoodsImage> getGoods_image() {
		return goods_image;
	}
	public void setGoods_image(List<V5GoodsImage> goods_image) {
		this.goods_image = goods_image;
	}
	public List<V5Coupon> getCoupon_list() {
		return coupon_list;
	}
	public void setCoupon_list(List<V5Coupon> coupon_list) {
		this.coupon_list = coupon_list;
	}
	public Map<String,Object> getGoods_properties() {
		return goods_properties;
	}
	public void setGoods_properties(Map<String,Object> goods_properties) {
		this.goods_properties = goods_properties;
	}
}
