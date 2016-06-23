package com.hongzhi.zswh.app_v3.club.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 27, 2016    3:13:30 PM
 */
public interface V3SportsCampDao {

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    3:16:17 PM
	 * @param i 
	 * @param parseInt
	 * @return
	 */
	List<Map<String, Object>> listSportsCamp(@Param("club") int club_id, @Param("user") int user_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    4:07:56 PM
	 * @param parseInt
	 * @return
	 */
	List<Map<String, Object>> subscribeList(int user_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    4:26:47 PM
	 * @param parseInt
	 * @param parseInt2
	 * @return
	 */
	int subscribe(@Param("user") int user_id, @Param("sport") int sport_camp);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    4:29:41 PM
	 * @param parseInt
	 * @param parseInt2
	 * @return
	 */
	int unsubscribe(@Param("user") int user_id, @Param("sport") int sport_camp);

	
}
