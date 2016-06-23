package com.hongzhi.zswh.back.sportsCamp.entity;

import net.sf.json.JSONObject;

public class SportsCamp {
	/**
	 * 运动派编号
	 */
	private int sports_camp_id;
	/**
	 * 语言编号
	 */
	private int language_id;
	/**
	 * 用户编号
	 */
	private int user_id;
	/**
	 * 运动派名称
	 */
	private String sports_camp_name;
	/**
	 * 备注
	 */
	private String remark;

	public int getSports_camp_id() {
		return sports_camp_id;
	}

	public void setSports_camp_id(int sprots_camp_id) {
		this.sports_camp_id = sprots_camp_id;
	}

	public int getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(int language_id) {
		this.language_id = language_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getSports_camp_name() {
		return sports_camp_name;
	}

	public void setSports_camp_name(String sports_camp_name) {
		this.sports_camp_name = sports_camp_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public JSONObject toJsonObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sports_camp_id", getSports_camp_id());
		jsonObject.put("language_id", getLanguage_id());
		jsonObject.put("user_id", getUser_id());
		jsonObject.put("sports_camp_name", getSports_camp_name());
		jsonObject.put("remark", getRemark());
		return jsonObject;
	}
}
