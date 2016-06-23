package com.hongzhi.zswh.util.basic.sessionDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 5, 2016    5:06:37 PM
 */
@Repository
public interface SessionDao {

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 5, 2016    5:10:28 PM
	 * @return
	 */
	int createSession(SessionTime st);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 5, 2016    5:23:47 PM
	 */
	int insertAttribute(List<SessionAttribute> attributes);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 5, 2016    5:36:08 PM
	 * @return
	 */
	int sessionUsable(int s_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 5, 2016    5:38:36 PM
	 */
	void updateSessionTime(@Param("id") int s_id, @Param("time") String timeSql);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 5, 2016    6:34:30 PM
	 * @return
	 */
	SessionProperty getProperty(int s_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 6, 2016    9:57:02 AM
	 */
	int saveSessionLog(@Param("id") int s_id, @Param("function") String function_name);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 26, 2016    6:02:08 PM
	 * @return
	 */
	List<Map<String, Object>> listAttribute();

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 26, 2016    6:11:54 PM
	 */
	List<SessionTime> listSession();


}
