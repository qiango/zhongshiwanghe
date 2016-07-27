package com.hongzhi.zswh.back.club.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.back.club.entity.JoinClub;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 24, 2016    2:54:06 PM
 */
public interface JoinClubDao {
	
	List<JoinClub> ClubJoinUserByAdminID(int admin_id);
	
//	int applyToJoinClub(List<Integer> user_id_list);
	
	List<Integer>  userRoles(int user_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 7, 2016    5:35:37 PM
	 * @return
	 */
	List<JoinClub> ClubJoinUserByAdmin();

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 8, 2016    2:57:49 PM
	 * @param parseInt
	 * @param parseInt2
	 * @return
	 */
	int applyToJoinClub(@Param("user_id") int user_id, @Param("club_id") int club_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 6, 2016    3:34:45 PM
	 * @param parseInt
	 * @return
	 */
	Map<String, String>  getClubName(int parseInt);

	List<Integer> selectClubMembers(String club_id);

	void updateClubStatusByClubId(String club_id);

	Map<String,Object> queryUserInfoByUserId(String user_id);

	List<Integer> queryClubAdmin(String club_id);
}
