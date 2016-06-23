package com.hongzhi.zswh.app_v4.me.dao;

import org.apache.ibatis.annotations.Param;

/**   Twitter : @taylorwang789 
 * Creat time : May 12, 2016    4:19:20 PM
 */
public interface V4PasswordDao {

	int resetPassword(@Param("new_salt") String new_salt, @Param("password") String password, @Param("phone") String phone);

}
