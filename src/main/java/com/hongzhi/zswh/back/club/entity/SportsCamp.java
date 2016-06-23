package com.hongzhi.zswh.back.club.entity;


public class SportsCamp {
	private int sports_camp_id;
	private int language_id;
	private String language_name;
	private int user_id;
	private String sports_camp_name;
	private String remark;
	private String is_delete;
	
	

	public String getLanguage_name() {
		return language_name;
	}

	public void setLanguage_name(String language_name) {
		this.language_name = language_name;
	}

	public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}

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

	
}
