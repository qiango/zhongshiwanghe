package com.hongzhi.zswh.app_v2.profile.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 7, 2016    6:41:08 PM
 */
public interface AppMeV2Dao {

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 7, 2016    6:42:24 PM
	 * @param user_id
	 * @param platform_id
	 * @param language
	 * @return
	 */
	Map<String, Object> loadMe(@Param("user_id") int user_id, @Param("platform_id") int platform_id, @Param("language") String language);
}
