package com.hongzhi.zswh.back.basic.dao;

import java.util.List;
import java.util.Map;

import com.hongzhi.zswh.back.basic.entity.RoleInfo;
import com.hongzhi.zswh.back.basic.entity.User;



/**   Twitter : @taylorwang789 
 * Creat time : Mar 22, 2016    1:39:09 PM
 */
public interface LoginDao {

	 /**   Twitter : @taylorwang789 
	 * Creat time : Mar 22, 2016    1:45:25 PM
	 * @param user_login_name
	 * @return user_id ,user_password
	 */
	Map<String,Object>  selectUserIdAndPassword(String user_login_name);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 22, 2016    1:58:49 PM
	 * @param  int user_id 
	 * @return  User 
	 */
	User selectUser(int user_id);
	List<RoleInfo>  userRoles(int user_id);
	
	
}
