package com.hongzhi.zswh.back.competitionApplication.entity;

public class CompetitionApplication {

	/**
	 * 赛事报名编号
	 */
	private Integer competition_application_id;
	/**
	 * 控件编号
	 */
	private Integer controls_id;
	/**
	 * 用户编号
	 */
	private Integer user_id;
	/**
	 * 赛事编号
	 */
	private Integer competition_id;
	/**
	 * 语言编号
	 */
	private Integer language_id;
	/**
	 * 创建时间
	 */
	private String create_time;
	/**
	 * 创建日期
	 */
	private String create_date;
	/**
	 * 控件占位符
	 */
	private String controls_placeholder;
	/**
	 * 标题名称
	 */
	private String title_name;
	/**
	 * 控件数据
	 */
	private String controls_data;
	/**
	 * 控件顺序
	 */
	private Integer controls_order;

	public Integer getCompetition_application_id() {
		return competition_application_id;
	}

	public void setCompetition_application_id(Integer competition_application_id) {
		this.competition_application_id = competition_application_id;
	}

	public Integer getControls_id() {
		return controls_id;
	}

	public void setControls_id(Integer controls_id) {
		this.controls_id = controls_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getCompetition_id() {
		return competition_id;
	}

	public void setCompetition_id(Integer competition_id) {
		this.competition_id = competition_id;
	}

	public Integer getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(Integer language_id) {
		this.language_id = language_id;
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

	public String getControls_placeholder() {
		return controls_placeholder;
	}

	public void setControls_placeholder(String controls_placeholder) {
		this.controls_placeholder = controls_placeholder;
	}

	public String getTitle_name() {
		return title_name;
	}

	public void setTitle_name(String title_name) {
		this.title_name = title_name;
	}

	public String getControls_data() {
		return controls_data;
	}

	public void setControls_data(String controls_data) {
		this.controls_data = controls_data;
	}

	

	public Integer getControls_order() {
		return controls_order;
	}

	public void setControls_order(Integer controls_order) {
		this.controls_order = controls_order;
	}

}
