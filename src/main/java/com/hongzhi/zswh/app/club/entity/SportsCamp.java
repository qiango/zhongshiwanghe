package com.hongzhi.zswh.app.club.entity;

import net.sf.json.JSONObject;

public class SportsCamp {
	/**
	 * 运动派编号
	 */
	private String sports_camp_id;
	/**
	 * 语言编号
	 */
	private String language_id;
	/**
	 * 用户编号
	 */
	private String user_id;
	/**
	 * 运动派名称
	 */
	private String sports_camp_name;
	/**
	 * 备注
	 */
	private String remark;

	public String getSports_camp_id() {
		return sports_camp_id;
	}

	public void setSports_camp_id(String sprots_camp_id) {
		this.sports_camp_id = sprots_camp_id;
	}

	public String getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(String language_id) {
		this.language_id = language_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
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


}
