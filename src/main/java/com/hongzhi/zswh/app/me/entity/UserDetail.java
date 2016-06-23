package com.hongzhi.zswh.app.me.entity;


public class UserDetail {

	/**
	 * 俱乐部编号
	 */
	private Integer club_id;
	/**
	 * 用户编号
	 */
	private Integer user_id;
	/**
	 * 选择俱乐部跳过次数
	 */
	private Integer jump_club_number;
	/**
	 * 加入俱乐部状态 	0:待审核	99：完成	
	 */
	private String  join_club_status;
	
	public String getJoin_club_status() {
		return join_club_status;
	}

	public void setJoin_club_status(String join_club_status) {
		this.join_club_status = join_club_status;
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

	public Integer getJump_club_number() {
		return jump_club_number;
	}

	public void setJump_club_number(Integer jump_club_number) {
		this.jump_club_number = jump_club_number;
	}

	
}
