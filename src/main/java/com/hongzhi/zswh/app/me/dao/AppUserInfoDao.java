package com.hongzhi.zswh.app.me.dao;

import com.hongzhi.zswh.app.me.entity.UserProfile;
import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.app.me.entity.UserInfo;

import java.util.List;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 24, 2016    5:10:10 PM
 */
public interface AppUserInfoDao {
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 24, 2016    5:16:05 PM
	 * select user basic information 
	 * @param user_id
	 * @param language
	 * @return
	 */
	UserInfo selectUserByID(@Param("user_id") int user_id, @Param("language") String language);
	
	int updateUserInfo(UserInfo userInfo);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    2:25:58 PM
	 * @param string
	 * @param parseInt
	 * @return
	 */
	UserInfo selectByPhoneAndPlatform(@Param("phone") String phone, @Param("platform_id") int parseInt);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    2:36:10 PM
	 * @param user_input
	 * @return
	 */
	int insertUserInfo(UserInfo user_input);

	Integer getIdbyPhone(String phone);

	void saveNewUserProfile(@Param("profiles") List<UserProfile> profiles);
}
