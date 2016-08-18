package com.hongzhi.zswh.app_v3.club.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.app_v3.club.entity.ClubQueryEntity;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 26, 2016    4:16:16 PM
 */
public interface V3ClubManageDao {

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 26, 2016    4:18:15 PM
	 * @param parseInt
	 * @return
	 */
	ClubQueryEntity clubInfo(@Param("userId") int userId, @Param("language") String language);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 26, 2016    4:41:45 PM
	 * @param user_id
	 * @return
	 */
	Map<String, Object> userClub(int user_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 26, 2016    4:59:05 PM
	 * @param i 
	 * @return
	 */
	List<Map<String,Object>> clubRanking(@Param("limitSql") String limitSql, @Param("userClubId") int userClubId);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    10:43:29 AM
	 * @param parseInt
	 * @return
	 */
	Map<String,Object> ownClubMemberCount(int owner);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    10:43:35 AM
	 * @param startRow
	 * @param startRow2 
	 * @return
	 */
	List<Map<String, Object>> ownClubMemberList(@Param("user") int user_id, @Param("club") int club_id, @Param("start") int startRow, @Param("pageSize") int pageSize, @Param("language") String language);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    11:48:15 AM
	 * @param club_user_id
	 * @param user_id
	 * @return
	 */
	int follow(@Param("user") int user, @Param("follower") int follower_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    1:01:17 PM
	 * @param parseInt
	 * @param parseInt2
	 * @return
	 */
	int unfollow(@Param("user") int user, @Param("follower") int follower_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 3, 2016    3:46:39 PM
	 * @param parseInt
	 * @param parseInt2
	 * @return
	 */
	int saveUserClub(@Param("user_id") int user_id, @Param("club_id") int club_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 6, 2016    3:04:04 PM
	 * @param parseInt
	 * @return
	 */


	/**   Twitter : @taylorwang789 
	 * Creat time : May 9, 2016    1:47:56 PM
	 * @param parseInt
	 * @return
	 */
	List<Integer> getClubMgrId(int clubID);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 9, 2016    3:06:40 PM
	 * @param parseInt
	 * @param club_search
	 * @return
	 */
	List<Map<String, Object>> clubScoreRankList(@Param("compID") Integer compID, @Param("search") String club_search);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 9, 2016    6:39:36 PM
	 * @param parseInt
	 * @return
	 */
	String getPublishInfo(int parseInt);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 9, 2016    11:33:08 PM
	 * @param parseInt
	 * @return
	 */
	String getUserName(int parseInt);


	List<Integer> selectClubMembers(String club_id);
	void updateClubStatusByClubId(String club_id);


	Map<String,String> getClubName(int club_id);

	List<Integer> queryClubMember(@Param("user_id") String user_id, @Param("club_id") Integer club_id);
}
