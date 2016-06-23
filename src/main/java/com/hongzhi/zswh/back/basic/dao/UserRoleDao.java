package com.hongzhi.zswh.back.basic.dao;

import java.util.List;

import com.hongzhi.zswh.back.basic.entity.UserRole;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 24, 2016    1:13:42 PM
 */
public interface UserRoleDao {
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 24, 2016    1:06:11 PM
	 * @param user_id
	 * @return
	 */
	List<Integer> userRoleIds(int user_id);
	

	// update user roles 
	int deleteRolesOfUser(int user_id);
	int insertRolesOfUser(List<UserRole> user_role_list);

}
