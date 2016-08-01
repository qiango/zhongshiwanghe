package com.hongzhi.zswh.app_1_4.entity;

import java.util.List;
import java.util.Map;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 26, 2016    1:39:39 PM
 */
public class ClubManageEntity {
	
	private  String  club_member_number;  // 成员数
	private  String  sports_camp_number;   // 运动派数
	private  String  activity_number;   //  活动数
	private  String  complete_training_number;   //  完成訓練
	private  String  continuous_training_number;   // 连续訓練
	private  String  total_training_number;   //  总共訓練
	private  String  ranking;   //  名次
	private  String  comprehensive_integral;   //  綜合积分
	private  String  my_club_ranking;   //  我的俱乐部排名
	private  String  join_club_status;
	private  String  join_club_status_name;
	private String   user_level;
	private  String show_event;
	private  String show_club;
	
	private Map<String,Object> club;
	private List<Map<String,Object>> club_ranking_list;
	private Map<String,Object> event;

	public String getShow_event() {
		return show_event;
	}

	public void setShow_event(String show_event) {
		this.show_event = show_event;
	}

	public String getShow_club() {
		return show_club;
	}

	public void setShow_club(String show_club) {
		this.show_club = show_club;
	}

	public Map<String, Object> getEvent() {
		return event;
	}

	public void setEvent(Map<String, Object> event) {
		this.event = event;
	}

	public String getUser_level() {
		return user_level;
	}

	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}

	public String getClub_member_number() {
		return club_member_number;
	}
	public void setClub_member_number(String club_member_number) {
		this.club_member_number = club_member_number;
	}
	public String getSports_camp_number() {
		return sports_camp_number;
	}
	public void setSports_camp_number(String sports_camp_number) {
		this.sports_camp_number = sports_camp_number;
	}
	public String getActivity_number() {
		return activity_number;
	}
	public void setActivity_number(String activity_number) {
		this.activity_number = activity_number;
	}
	
	public String getContinuous_training_number() {
		return continuous_training_number;
	}
	public void setContinuous_training_number(String continuous_training_number) {
		this.continuous_training_number = continuous_training_number;
	}
	public String getTotal_training_number() {
		return total_training_number;
	}
	public void setTotal_training_number(String total_training_number) {
		this.total_training_number = total_training_number;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	public String getComprehensive_integral() {
		return comprehensive_integral;
	}
	public void setComprehensive_integral(String comprehensive_integral) {
		this.comprehensive_integral = comprehensive_integral;
	}
	public String getMy_club_ranking() {
		return my_club_ranking;
	}
	public void setMy_club_ranking(String my_club_ranking) {
		this.my_club_ranking = my_club_ranking;
	}
	public Map<String, Object> getClub() {
		return club;
	}
	public void setClub(Map<String, Object> club) {
		this.club = club;
	}
	public List<Map<String, Object>> getClub_ranking_list() {
		return club_ranking_list;
	}
	public void setClub_ranking_list(List<Map<String, Object>> club_ranking_list) {
		this.club_ranking_list = club_ranking_list;
	}
	public String getJoin_club_status() {
		return join_club_status;
	}
	public void setJoin_club_status(String join_club_status) {
		this.join_club_status = join_club_status;
	}
	public String getJoin_club_status_name() {
		return join_club_status_name;
	}
	public void setJoin_club_status_name(String join_club_status_name) {
		this.join_club_status_name = join_club_status_name;
	}
	public String getComplete_training_number() {
		return complete_training_number;
	}
	public void setComplete_training_number(String complete_training_number) {
		this.complete_training_number = complete_training_number;
	}
	

}
