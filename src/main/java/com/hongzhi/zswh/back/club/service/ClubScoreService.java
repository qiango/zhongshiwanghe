package com.hongzhi.zswh.back.club.service;

import com.hongzhi.zswh.back.club.dao.ClubScoreDao;
import com.hongzhi.zswh.back.club.entity.ClubScoreEntity;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   Twitter : @taylorwang789 
 * Creat time : May 9, 2016    10:41:12 AM
 */
@Service
public class ClubScoreService {
	
	@Autowired
	private ClubScoreDao scoredao;

	/**   Twitter : @taylorwang789 
	 * Creat time : May 9, 2016    10:42:51 AM
	 * @param competition_id 
	 * @param club_search 
	 * @return
	 */
	public Object scoreIndex(String competition_id, String club_search) {
		List<Map<String,Object>>  compList = null;
		if(ObjectUtil.isEmpty(competition_id)){
			competition_id="0";
		    compList = scoredao.compList();
		}
		List<Map<String,Object>>  clubList = scoredao.clubList(Integer.parseInt(competition_id),club_search);
		Map<String,Object> out = new HashMap<>();
		out.put("compList", compList);
		out.put("clubList", clubList);
		return out;
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : May 9, 2016    12:34:52 PM
	 * @param clubScore
	 * @return
	 * @throws HongZhiException 
	 */
	public Object scoreInput(ClubScoreEntity clubScore) throws HongZhiException {
		int competitionID = clubScore.Vcompetition_id();
		
		List<ClubScoreEntity> list = new ArrayList<>();
		String[] clubRankArray = clubScore.VclubRank().split(",");
		for(int i=0;i<clubRankArray.length;i++){
			String [] clubRank = clubRankArray[i].split(":");
			ClubScoreEntity cse = new ClubScoreEntity();
			cse.setClub_id(Integer.parseInt(clubRank[0]));
			cse.setRank(Integer.parseInt(clubRank[1]));
			cse.setCompetition_id(competitionID);
			list.add(cse);
		}
		int  insertCount = scoredao.insertScore(list);
		if(insertCount>0){
			return "success";
		}else{
			throw new HongZhiException("1041");
		}
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : May 9, 2016    1:38:22 PM
	 * @param clubScore
	 * @return
	 * @throws HongZhiException 
	 */
	public Object scoreModify(ClubScoreEntity clubScore) throws HongZhiException {
		clubScore.Vclub_id();
		clubScore.Vcompetition_id();
		clubScore.Vrank();
		int  effCount = scoredao.modifyScore(clubScore);
		if(1 == effCount || 2 == effCount){
			return "success";
		}else{
			throw new HongZhiException("1034");
		}
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : May 10, 2016    2:54:19 PM
	 * @param clubScore
	 * @param publish
	 * @return
	 * @throws HongZhiException 
	 */
	public Object scorePublish(ClubScoreEntity clubScore, String publish) throws HongZhiException {
		int effCnt=0;
		if("1".equals(publish)){
			effCnt=scoredao.publish(clubScore.getCompetition_id(),"1");
		}else{
			effCnt=scoredao.publish(clubScore.getCompetition_id(),"0");
		}
		if(effCnt==1){
			return "success";
		}else{
			throw new HongZhiException("1033");
		}
	}

	

}
