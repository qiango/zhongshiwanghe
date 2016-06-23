package com.hongzhi.zswh.app_v3.club.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 27, 2016    1:27:45 PM
 */
public interface V3ClubAuditDao {

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    1:29:00 PM
	 * @param parseInt
	 * @return
	 */
    int userRole(int userID);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    1:36:06 PM
	 * @param parseInt
	 * @param startRow 
	 * @param string 
	 * @return
	 */
	List<Map<String, Object>> waitingForJoin(@Param("club") int club_id, @Param("language") String language, @Param("start") int startRow, @Param("pageSize") int pageSize);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    2:42:10 PM
	 * @param parseInt
	 * @param parseInt2
	 * @param userIdList
	 * @param reasonID 
	 * @return
	 */
	int apply(@Param("club") int clubID, @Param("status") String status, @Param("userList") List<Integer> userIdList, @Param("reasonID") Integer reasonID);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    2:57:31 PM
	 * @param parseInt
	 * @param parseInt2
	 * @param i
	 * @return
	 */
	int updateUserDetail(@Param("user") int user_id, @Param("club") int club_id, @Param("status") int status);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 6, 2016    5:08:43 PM
	 * @param reason
	 * @return
	 */
	int refuseReason(String reason);

}
