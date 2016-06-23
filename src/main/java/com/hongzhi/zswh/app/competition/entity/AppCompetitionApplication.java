package com.hongzhi.zswh.app.competition.entity;

public class AppCompetitionApplication {
	private Integer competition_application_id;
	private Integer controls_id;
	private Integer competition_id;
	private String  controls_placeholder;
	private String title_name;
	private String controls_data;
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
	public Integer getCompetition_id() {
		return competition_id;
	}
	public void setCompetition_id(Integer competition_id) {
		this.competition_id = competition_id;
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
	
}
