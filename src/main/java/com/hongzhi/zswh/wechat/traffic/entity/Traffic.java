/**  
 * @Title: Traffic.java
 * @Package com.hongzhi.zswh.wechat.traffic.entity
 * @Description: TODO
 * @author Saxon Zhong
 * @date 2016年6月1日
 */
package com.hongzhi.zswh.wechat.traffic.entity;

/**
 * ClassName: Traffic
 * 
 * @Description: TODO
 * @author Saxon Zhong
 * @date 2016年6月1日
 */
public class Traffic {
	private String order_id;
	private String level_id;
	private String phone;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getLevel_id() {
		return level_id;
	}

	public void setLevel_id(String level_id) {
		this.level_id = level_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
