package com.hongzhi.zswh.back.club.entity;


public class WorldCity {
	/**
	 * 编号
	 */
	private Integer id;

	/**
	 * 英文名称 
	 */
	private String name_en;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 父编号
	 */
	private Integer parent_id;
	/**
	 * 备注
	 */
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


}
