package com.hongzhi.zswh.app_v3.login.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 26, 2016    2:32:01 PM
 */
public interface V3LoginDao {

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 26, 2016    2:33:54 PM
	 * @param vPhone
	 * @param vUser_password
	 * @param vPlatform_id
	 * @return
	 */
	Map<String, Object> login(@Param("phone") String vPhone, @Param("platform") String vPlatform_id);
	
	

}
