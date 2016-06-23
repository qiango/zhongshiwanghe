package com.hongzhi.zswh.app_v3.notification.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.app_v3.notification.entity.NotificationEntity;

/**   Twitter : @taylorwang789 
 * Creat time : May 3, 2016    4:40:28 PM
 */
public interface NotificationUserStateDao {

	/**   Twitter : @taylorwang789 
	 * Creat time : May 3, 2016    4:58:46 PM
	 * @param user_id
	 * @return
	 */
	Map<String, Object> userNotiList(String user_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 4, 2016    3:02:50 PM
	 * @param notificationEntity
	 * @return
	 */
	int saveNewNoti(NotificationEntity notificationEntity);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 4, 2016    3:10:44 PM
	 * @return
	 */
	List<Map<String, Object>> categoryList(String language);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 4, 2016    3:16:57 PM
	 * @param user_id
	 * @param id
	 * @param language 
	 * @param l 
	 * @return
	 */
	List<Map<String, Object>> readNotification(@Param("user_id") Integer user_id, @Param("noti_id") Integer id, @Param("category") String category_id, @Param("search") String search, @Param("language") String language);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 4, 2016    3:23:36 PM
	 * @param user_id
	 * @param id
	 * @param category_id 
	 */
	int markRead(@Param("user_id") int user_id, @Param("noti_id") Integer id, @Param("category") String category_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 4, 2016    3:27:30 PM
	 * @param user_id
	 * @param id
	 * @return
	 */
	int logicDelete(@Param("user_id") int user_id, @Param("noti_id") int id);

	
	int updateNotificationState(Integer user_id);

	int updateNotificationStateMultipleUser(@Param("userlist") List<Integer> user_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 6, 2016    2:54:59 PM
	 * @param notificationEntity
	 * @param multiple_receiver
	 * @return
	 */
	int saveMultipleNoti(@Param("notiEntity") NotificationEntity notificationEntity, @Param("userList") List<Integer> multiple_receiver);

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 1, 2016    11:06:30 AM
     * @return
     */
    List<Integer> allUserId();

}
