package com.hongzhi.zswh.back.basic.entity;

import java.util.List;

import com.hongzhi.zswh.util.basic.ObjectUtil;

/**
 * @author Saxon create date: 2016年3月14日 下午2:29:51
 */
public class User {
	private Integer user_id;
	private String user_real_name;
	private Integer club_id;
	private String club_name;
	private String language;
	private List<RoleInfo> role_list;
	
	
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		if(ObjectUtil.isEmpty(language)){
			this.language="zh";
		}else{
			this.language = language;
		}
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_real_name() {
		return user_real_name;
	}
	public void setUser_real_name(String user_real_name) {
		this.user_real_name = user_real_name;
	}
	public Integer getClub_id() {
		return club_id;
	}
	public void setClub_id(Integer club_id) {
		this.club_id = club_id;
	}
	public String getClub_name() {
		return club_name;
	}
	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}
	public List<RoleInfo> getRole_list() {
		return role_list;
	}
	public void setRole_list(List<RoleInfo> role_list) {
		this.role_list = role_list;
	}
	

}
