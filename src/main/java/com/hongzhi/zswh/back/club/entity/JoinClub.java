package com.hongzhi.zswh.back.club.entity;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 24, 2016    2:54:56 PM
 */
public class JoinClub {
	
	private int club_id;
	private int user_id;
	private String user_real_name;
	private String phone;
	private String club_name;
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getClub_id() {
		return club_id;
	}
	public void setClub_id(int club_id) {
		this.club_id = club_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_real_name() {
		return user_real_name;
	}
	public void setUser_real_name(String user_real_name) {
		this.user_real_name = user_real_name;
	}
	public String getClub_name() {
		return club_name;
	}
	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}
	
	
}
