package com.hongzhi.zswh.app.competition.entity;

import net.sf.json.JSONObject;

public class CompetitionApplicationData {

	/**
	 * 赛事报名数据
	 */
	private Integer competition_application_data_id;
	/**
	 * 控件编号
	 */
	private Integer controls_id;
	/**
	 * 赛事报名编号
	 */
	private Integer competition_application_id;
	/**
	 * 用户编号
	 */
	private Integer user_id;
	/**
	 * 赛事编号
	 */
	private Integer competition_id;
	/**
	 * 创建时间
	 */
	private String create_time;
	/**
	 * 创建日期
	 */
	private String create_date;
	/**
	 * 标题名称
	 */
	private String title_name;
	/**
	 * 控件顺序
	 */
	private Integer controls_order;
	/**
	 * 用户数据
	 */
	private String user_value;

	public Integer getCompetition_application_data_id() {
		return competition_application_data_id;
	}

	public void setCompetition_application_data_id(Integer competition_application_data_id) {
		this.competition_application_data_id = competition_application_data_id;
	}

	public Integer getControls_id() {
		return controls_id;
	}

	public void setControls_id(Integer controls_id) {
		this.controls_id = controls_id;
	}

	public Integer getCompetition_application_id() {
		return competition_application_id;
	}

	public void setCompetition_application_id(Integer competition_application_id) {
		this.competition_application_id = competition_application_id;
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

	public String getTitle_name() {
		return title_name;
	}

	public void setTitle_name(String title_name) {
		this.title_name = title_name;
	}

	public Integer getControls_order() {
		return controls_order;
	}

	public void setControls_order(Integer controls_order) {
		this.controls_order = controls_order;
	}

	public String getUser_value() {
		return user_value;
	}

	public void setUser_value(String user_value) {
		this.user_value = user_value;
	}

	public JSONObject toJsonObject() {
		JSONObject object = new JSONObject();
		object.put("competition_application_data_id", getCompetition_application_data_id());
		object.put("controls_id", getControls_id());
		object.put("competition_application_id", getCompetition_application_id());
		object.put("user_id", getUser_id());
		object.put("competition_id", getCompetition_id());
		object.put("create_time", getCreate_time());
		object.put("create_date", getCreate_date());
		object.put("title_name", getTitle_name());
		object.put("controls_order", getControls_order());
		object.put("user_value", getUser_value());
		return object;
	}
}
