package com.hongzhi.zswh.back.competitionApplication.entity;


public class Competition {
	private Integer competition_id;
	private Integer user_id;
	private Integer language_id;
	private String language_name;
	private Integer platform_id;
	private String platform_name;
	private String competition_name;
	private String competition_level_id;
	private String competition_level;
	private String competition_description;
	private String competition_status;
	private String create_time;
	private String create_date;
	private String competition_publicity_pictures;
	private Integer competition_primary_id;
	private String registration_start_date;
	private String registration_end_date;
	private String competition_start_date;
	private String competition_end_date;
	private Integer id;  // city id
	private String city_name;
	private String submit_file;
	private String is_delete;


	public String getLanguage_name() {
		return language_name;
	}

	public void setLanguage_name(String language_name) {
		this.language_name = language_name;
	}

	public String getPlatform_name() {
		return platform_name;
	}

	public void setPlatform_name(String platform_name) {
		this.platform_name = platform_name;
	}

	public String getCompetition_level_id() {
		return competition_level_id;
	}

	public void setCompetition_level_id(String competition_level_id) {
		this.competition_level_id = competition_level_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}

	public String getSubmit_file() {
		return submit_file;
	}

	public void setSubmit_file(String submit_file) {
		this.submit_file = submit_file;
	}

	// 1, 预热
	// 2, 报名中
	// 3, 赛事准备中
	// 4, 赛事进行中
	// 5, 赛事结束
	private String compertition_live_status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompetition_id() {
		return competition_id;
	}

	public void setCompetition_id(Integer competition_id) {
		this.competition_id = competition_id;
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

	public Integer getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(Integer platform_id) {
		this.platform_id = platform_id;
	}

	public String getCompetition_name() {
		return competition_name;
	}

	public void setCompetition_name(String competition_name) {
		this.competition_name = competition_name;
	}

	public String getCompetition_level() {
		return competition_level;
	}

	public void setCompetition_level(String competition_level) {
		this.competition_level = competition_level;
	}

	public String getCompetition_description() {
		return competition_description;
	}

	public void setCompetition_description(String competition_description) {
		this.competition_description = competition_description;
	}

	public String getCompetition_status() {
		return competition_status;
	}

	public void setCompetition_status(String competition_status) {
		this.competition_status = competition_status;
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

	public String getCompetition_publicity_pictures() {
		return competition_publicity_pictures;
	}

	public void setCompetition_publicity_pictures(
			String competition_publicity_pictures) {
		this.competition_publicity_pictures = competition_publicity_pictures;
	}

	public Integer getCompetition_primary_id() {
		return competition_primary_id;
	}

	public void setCompetition_primary_id(Integer competition_primary_id) {
		this.competition_primary_id = competition_primary_id;
	}

	public String getRegistration_start_date() {
		return registration_start_date;
	}

	public void setRegistration_start_date(String registration_start_date) {
		this.registration_start_date = registration_start_date;
	}

	public String getRegistration_end_date() {
		return registration_end_date;
	}

	public void setRegistration_end_date(String registration_end_date) {
		this.registration_end_date = registration_end_date;
	}

	public String getCompetition_start_date() {
		return competition_start_date;
	}

	public void setCompetition_start_date(String competition_start_date) {
		this.competition_start_date = competition_start_date;
	}

	public String getCompetition_end_date() {
		return competition_end_date;
	}

	public void setCompetition_end_date(String competition_end_date) {
		this.competition_end_date = competition_end_date;
	}

	public String getCompertition_live_status() {
		return compertition_live_status;
	}

	public void setCompertition_live_status(String compertition_live_status) {
		this.compertition_live_status = compertition_live_status;
	}

	
}
