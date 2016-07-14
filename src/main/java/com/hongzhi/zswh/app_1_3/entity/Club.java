package com.hongzhi.zswh.app_1_3.entity;


import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

public class Club {
	private Integer club_id;
	private Integer user_id;
	private Integer city_id;
	private String club_name;
	private String club_applicant_name;
	private String create_time;
	private String create_date;
	private String club_status;
	private String club_description;
	private String club_pic;

	public String getClub_pic() {
		return club_pic;
	}

	public void setClub_pic(String club_pic) {
		this.club_pic = club_pic;
	}

	public String getClub_description() {
		return club_description;
	}

	public void setClub_description(String club_description) {
		this.club_description = club_description;
	}

	public String Vclub_name() throws HongZhiException {
		return ExcepUtil.verify(club_name, "1081").toString();
	}
	public String Vcity_id() throws HongZhiException {
		return ExcepUtil.verify(city_id, "1082").toString();
	}
	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
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



}
