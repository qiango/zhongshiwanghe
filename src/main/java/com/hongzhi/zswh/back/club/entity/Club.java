package com.hongzhi.zswh.back.club.entity;


public class Club {
	private Integer club_id;
	private Integer user_id;
	private Integer language_id;
	private String language_name;
	private String club_name;
	private String club_name_short;
	private String club_applicant_name;
	private String create_time;
	private String create_date;
	private String club_status;
	private String club_description;
	private String club_create_date;
	private String club_qq;
	private Integer id;
	private String  city_name;


	public String getClub_name_short() {
		return club_name_short;
	}

	public void setClub_name_short(String club_name_short) {
		this.club_name_short = club_name_short;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClub_description() {
		return club_description;
	}

	public void setClub_description(String club_description) {
		this.club_description = club_description;
	}

	public String getClub_create_date() {
		return club_create_date;
	}

	public void setClub_create_date(String club_create_time) {
		this.club_create_date = club_create_time;
	}

	public String getClub_qq() {
		return club_qq;
	}

	public void setClub_qq(String club_qq) {
		this.club_qq = club_qq;
	}

	public Integer getClub_id() {
		return club_id;
	}

	public void setClub_id(Integer club_id) {
		this.club_id = club_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(Integer language_id) {
		this.language_id = language_id;
	}

	public String getClub_name() {
		return club_name;
	}

	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}

	public String getClub_applicant_name() {
		return club_applicant_name;
	}

	public void setClub_applicant_name(String club_applicant_name) {
		this.club_applicant_name = club_applicant_name;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getClub_status() {
		return club_status;
	}

	public void setClub_status(String club_status) {
		this.club_status = club_status;
	}

	public String getLanguage_name() {
		return language_name;
	}

	public void setLanguage_name(String language_name) {
		this.language_name = language_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	

}
