package com.hongzhi.zswh.back.basic.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.back.basic.entity.UserInfo;
import com.hongzhi.zswh.util.page.PageModel;



public interface UserDao {
	List<Map<String,Object>>  listUserByPage(PageModel pageModel);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    1:42:57 PM
	 * @param pageModel
	 * @return
	 */
	int listUserByPageCount(PageModel pageModel);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    2:55:40 PM
	 * @param userInfo
	 * @return
	 */
	int insertUserInfo(UserInfo userInfo);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    3:09:10 PM
	 * @param phone
	 * @param user_login_name
	 * @return
	 */
	int selectByPhoneAndUserName(@Param("phone") String phone, @Param("user_login_name") String user_login_name);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    3:37:30 PM
	 * @param parseInt
	 * @return
	 */
	UserInfo selectByUserId(int user_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    3:49:18 PM
	 * @param userInfo
	 * @return
	 */
	int updateUserInfo(UserInfo userInfo);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 28, 2016    3:42:35 PM
	 * @param parseInt
	 * @return
	 */
	int logicDelete(int user_id);
	
	


	
	
	
}