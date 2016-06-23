package com.hongzhi.zswh.app_v2.profile.dao;

import org.apache.ibatis.annotations.Param;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 7, 2016    2:59:18 PM
 */
public interface AppUserProfileDao {

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 7, 2016    3:50:18 PM
	 * @param parseInt
	 * @param string
	 */
	int saveProfilePic(@Param("user_id") int user_id, @Param("path") String profilePicPath);

}
