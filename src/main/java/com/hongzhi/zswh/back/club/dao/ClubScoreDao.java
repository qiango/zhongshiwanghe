package com.hongzhi.zswh.back.club.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.back.club.entity.ClubScoreEntity;

/**   Twitter : @taylorwang789 
 * Creat time : May 9, 2016    10:44:49 AM
 */
public interface ClubScoreDao {

	/**   Twitter : @taylorwang789 
	 * Creat time : May 9, 2016    11:03:46 AM
	 * @return
	 */
	List<Map<String, Object>> compList();

	/**   Twitter : @taylorwang789 
	 * Creat time : May 9, 2016    11:04:16 AM
	 * @param club_search 
	 * @param i 
	 * @return
	 */
	List<Map<String, Object>> clubList(@Param("compID") Integer compID, @Param("search") String club_search);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 9, 2016    12:40:26 PM
	 * @param list
	 * @return
	 */
	int insertScore(@Param("clubentity") List<ClubScoreEntity> list);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 9, 2016    1:39:38 PM
	 * @param clubScore
	 * @return
	 */
	int modifyScore(ClubScoreEntity clubScore);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 10, 2016    2:55:28 PM
	 * @param competition_id
	 * @param string 
	 * @param i 
	 */
	int publish(@Param("compID") Integer competition_id, @Param("pub") String publish);

}
