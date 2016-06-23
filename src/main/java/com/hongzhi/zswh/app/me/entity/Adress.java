package com.hongzhi.zswh.app.me.entity;

public class Adress {
	/*
	 * 配送地址ID
	 */
	private Integer shipping_id;
	/*
	 * 用户ID
	 */
	private Integer user_id;
	/*
	 * 地点标记
	 */
	private String city_code;
	/*
	 * 详细地址
	 */
	private String detail_address;
	/*
	 * 收货人
	 */
	private String receiver_name;
	/*
	 * 收货人电话
	 */
	private String receiver_phone;
	/*
	 * 是否是默认地址,  0: 否, 1: 是
	 */
	private String is_default;
	/*
	 * 城市名字
	 */
	private String city_name;
	
	
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public Integer getShipping_id() {
		return shipping_id;
	}
	public void setShipping_id(Integer shipping_id) {
		this.shipping_id = shipping_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getDetail_address() {
		return detail_address;
	}
	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_phone() {
		return receiver_phone;
	}
	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}
	public String getIs_default() {
		return is_default;
	}
	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}
	
	
}
