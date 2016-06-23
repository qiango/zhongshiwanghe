package com.hongzhi.zswh.back.competitionApplication.entity;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 24, 2016    3:46:08 PM
 */
public class Form {
	
//      <th>赛事ID</th>
//      <th>赛事名称</th>
//      <th>已报名人数</th>
//      <th>操作</th>
	
	private  Integer competition_id;
	private String competition_name;
	private Integer numberOfSignUp;
	
	
	
	public Integer getCompetition_id() {
		return competition_id;
	}
	public void setCompetition_id(Integer competition_id) {
		this.competition_id = competition_id;
	}
	public String getCompetition_name() {
		return competition_name;
	}
	public void setCompetition_name(String competition_name) {
		this.competition_name = competition_name;
	}
	public Integer getNumberOfSignUp() {
		return numberOfSignUp;
	}
	public void setNumberOfSignUp(Integer numberOfSignUp) {
		this.numberOfSignUp = numberOfSignUp;
	}
	
	
	

}
