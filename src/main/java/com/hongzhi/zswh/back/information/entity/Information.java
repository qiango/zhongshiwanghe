package com.hongzhi.zswh.back.information.entity;

import java.util.ArrayList;
import java.util.List;

import com.hongzhi.zswh.util.basic.ObjectUtil;

import net.sf.json.JSONObject;

public class Information {

	/**
	 * 资讯编号
	 */
	private Integer information_id;
	/**
	 * 语言编号
	 */
	private Integer competition_id;
	/**
	 * 用户编号
	 */
	private Integer user_id;
	/**
	 * 平台编号
	 */
	private Integer platform_id;
	/**
	 * 资讯类型 0：图片 1：图片集 2：视频
	 */
	private String information_type;
	/**
	 * 资讯标题
	 */
	private String information_title;

	/**
	 * 资讯摘要
	 */
	private String information_abstract;
	/**
	 * 资讯内容
	 */
	private String information_content;
	/**
	 * 资讯状态 0：禁用 1：启用
	 */
	private String information_status;
	/**
	 * 备注
	 */
	private String create_date;
	private String create_time;
	private String remark;

	private String is_delete;

	private String submit_file;

	private List<InformationImage> image_list = new ArrayList<>();

	public Integer getInformation_id() {
		return information_id;
	}

	public void setInformation_id(Integer information_id) {
		this.information_id = information_id;
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

	public Integer getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(Integer platform_id) {
		this.platform_id = platform_id;
	}

	public String getInformation_type() {
		return information_type;
	}

	public void setInformation_type(String information_type) {
		this.information_type = information_type;
	}

	public String getInformation_title() {
		return information_title;
	}

	public void setInformation_title(String information_title) {
		this.information_title = information_title;
	}

	public String getInformation_abstract() {
		return information_abstract;
	}

	public void setInformation_abstract(String information_abstract) {
		this.information_abstract = information_abstract;
	}

	public String getInformation_content() {
		return information_content;
	}

	public void setInformation_content(String information_content) {
		this.information_content = information_content;
	}

	public String getInformation_status() {
		return information_status;
	}

	public void setInformation_status(String information_status) {
		this.information_status = information_status;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}

	public List<InformationImage> getImage_list() {
		return image_list;
	}

	public void setImage_list(List<InformationImage> image_list) {
		this.image_list = image_list;
	}

	public String getSubmit_file() {
		return submit_file;
	}

	public void setSubmit_file(String submit_file) {
		this.submit_file = submit_file;
	}

	public JSONObject toJsonObject() {
		JSONObject object = new JSONObject();
		object.put("information_id", getInformation_id());
		object.put("user_id", getUser_id());
		object.put("platform_id", getPlatform_id());
		// 资讯类型 0：图片 1：图片集 2：视频
		String type = "";
		if (!ObjectUtil.isEmpty(getInformation_type())) {
			if (getInformation_type().equals("0")) {
				type = "图文";
			} else if (getInformation_type().equals("1")) {
				type = "图片集";
			} else {
				type = "视频";
			}
		}
		object.put("information_type", type);
		object.put("information_title", getInformation_title());
		object.put("information_abstract", getInformation_abstract());
		object.put("information_content", getInformation_content());
		String status = "开放";
		if (!ObjectUtil.isEmpty(getInformation_status()) && getInformation_status().equals("0")) {
			status = "关闭";
		}
		object.put("information_status", status);
		object.put("create_date", getCreate_date());
		object.put("create_time", getCreate_time());
		object.put("remark", getRemark());
		return object;
	}
}
