package com.hongzhi.zswh.back.information.entity;

import net.sf.json.JSONObject;

public class InformationImage {

	private Integer information_image_id;
	private Integer information_id;
	private Integer user_id;
	private String information_image_url;

	public Integer getInformation_image_id() {
		return information_image_id;
	}

	public void setInformation_image_id(Integer information_image_id) {
		this.information_image_id = information_image_id;
	}

	public Integer getInformation_id() {
		return information_id;
	}

	public void setInformation_id(Integer information_id) {
		this.information_id = information_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getInformation_image_url() {
		return information_image_url;
	}

	public void setInformation_image_url(String information_image_url) {
		this.information_image_url = information_image_url;
	}

	public JSONObject toJsonObject() {
		JSONObject object = new JSONObject();
		object.put("", getInformation_image_id());
		object.put("", getInformation_id());
		object.put("", getUser_id());
		object.put("", getInformation_image_url());
		return object;
	}
}
