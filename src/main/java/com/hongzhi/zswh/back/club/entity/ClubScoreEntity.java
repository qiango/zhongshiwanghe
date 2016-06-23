package com.hongzhi.zswh.back.club.entity;

import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 9, 2016    11:29:17 AM
 */
public class ClubScoreEntity {
	
	private Integer club_id;
	private Integer competition_id;
	private	Integer rank;
	private String  clubRank; // clubID:rank,clubId2:rank2
	
	
	public Integer Vclub_id() throws HongZhiException {
		return  (Integer)ExcepUtil.verify(club_id, "");
	}
	public Integer Vcompetition_id() throws HongZhiException {
		return  (Integer)ExcepUtil.verify(competition_id, "");
	}
	public Integer Vrank() throws HongZhiException {
		return (Integer)ExcepUtil.verify(rank, "");
	}
	public String VclubRank() throws HongZhiException {
		return ExcepUtil.verify(clubRank, "").toString();
	}
	
	
	public Integer getClub_id() {
		return club_id;
	}
	public void setClub_id(Integer club_id) {
		this.club_id = club_id;
	}
	public Integer getCompetition_id() {
		return competition_id;
	}
	public void setCompetition_id(Integer competition_id) {
		this.competition_id = competition_id;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getClubRank() {
		return clubRank;
	}
	public void setClubRank(String clubRank) {
		this.clubRank = clubRank;
	}
	
	
}
