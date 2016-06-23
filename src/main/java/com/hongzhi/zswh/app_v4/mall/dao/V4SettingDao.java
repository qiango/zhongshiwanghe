package com.hongzhi.zswh.app_v4.mall.dao;




import org.apache.ibatis.annotations.Param;

/**   Twitter : @taylorwang789 
 * Creat time : May 16, 2016    11:25:22 AM
 */
public interface V4SettingDao {
	/**
	 * @author zhurenkui
	 * @param user_id
	 * @return
	 *根据用户的id判断该用户是否是verify_ticket
	 */
	int isTicketCheckerById(@Param("user_id") String user_id);
	
}
