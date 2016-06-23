package com.hongzhi.zswh.back.club.dao;

import java.util.List;
import java.util.Map;

import com.hongzhi.zswh.back.club.entity.ClubSportCamp;


/**   Twitter : @taylorwang789 
 * Creat time : Mar 28, 2016    5:04:23 PM
 */
public interface ClubSportCampDao {

	List<Integer> sprotCamp(int club_id);
	
	List<Map<String,Object>> allSportsCamps();
	
	int deleteClubSportsCamp(int club_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 8, 2016    1:56:04 PM
	 * @param list
	 * @return
	 */
	int insertClubSportsCamp(List<ClubSportCamp> list);

}
