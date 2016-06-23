package com.hongzhi.zswh.back.basic.entity;


public class RoleInfo {
	/**
	 * 角色编号
	 */
	private Integer role_id;
	/**
	 * 角色名称
	 */
	private String role_name;
	/**
	 * 备注
	 */
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}


}
