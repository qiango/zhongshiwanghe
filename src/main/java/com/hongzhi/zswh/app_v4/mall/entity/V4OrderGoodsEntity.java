package com.hongzhi.zswh.app_v4.mall.entity;

/**   Twitter : @taylorwang789 
 * Creat time : May 13, 2016    2:20:00 PM
 */
public class V4OrderGoodsEntity {
	
	private  Integer id	           ;
	private  Integer order_id	   ;
	private  Integer goods_id	   ;
	private  Integer properties_id ;
	private  Integer total_counts  ;
	private  Integer effect_count  ;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
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
	public Integer getTotal_counts() {
		return total_counts;
	}
	public void setTotal_counts(Integer total_counts) {
		this.total_counts = total_counts;
	}
	public Integer getEffect_count() {
		return effect_count;
	}
	public void setEffect_count(Integer effect_count) {
		this.effect_count = effect_count;
	}

}
