package com.hongzhi.zswh.app_v3.me.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.app_v3.me.entity.FollowersEntity;
import com.hongzhi.zswh.app_v3.me.entity.FollowingEntity;
import com.hongzhi.zswh.app_v3.me.entity.OutputEntity;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 7, 2016    6:41:08 PM
 */
public interface AppMeV3Dao {

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 7, 2016    6:42:24 PM
	 * @param user_id
	 * @param platform_id
	 * @param language
	 * @return
	 */
	OutputEntity loadMe(@Param("user_id") int user_id, @Param("platform_id") int platform_id, @Param("language") String language);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 5, 2016    3:51:03 PM
	 * @param parseInt
	 * @return
	 */
	List<FollowersEntity> followers(@Param("user_id") int user_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 5, 2016    3:55:07 PM
	 * @param parseInt
	 * @return
	 */
	List<FollowingEntity> following(int user_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 5, 2016    4:16:51 PM
	 * @param parseInt
	 * @param parseInt2
	 * @return
	 */
	int follow(@Param("user_id") Integer user_id, @Param("follower_id") Integer follower_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 5, 2016    4:17:39 PM
	 * @param parseInt
	 * @param parseInt2
	 * @return
	 */
	int unfollow(@Param("user_id") Integer user_id, @Param("follower_id") Integer follower_id);

	/**
	 * @author Saxon
	 * @param @param follow_user_id
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author Saxon
	 * @date 2016年5月8日
	 */
	List<String> findFollowUserInfoById(@Param("follow_user_id") Integer follow_user_id);
}
