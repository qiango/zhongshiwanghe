package com.hongzhi.zswh.back.basic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.back.basic.entity.RoleInfo;
import com.hongzhi.zswh.back.basic.entity.RoleMenu;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 24, 2016    11:32:19 AM
 */
public interface RoleDao {


	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 24, 2016    1:06:08 PM
	 * @return
	 */
	List<RoleInfo> allRoles();
	
	List<RoleInfo> allRolesByRoleName(@Param("role_name") String role_name);
	
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 24, 2016    2:25:54 PM
	 * @param role.role_naem  role.remark
	 * @return
	 */
	int saveNewRole(RoleInfo role);
	
	
	int countByRoleName(String role_name);
	
	
	RoleInfo selectById(int role_id);
	
	
	int updateRole(RoleInfo roleInfo);
	
	int logicDelete(int role_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 7, 2016    11:00:26 AM
	 * @param parseInt
	 * @return
	 */
	List<Integer> roleMenuIds(int role_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 7, 2016    11:14:50 AM
	 * @param parseInt
	 */
	int deleteRoleMenu(int role_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 7, 2016    11:46:07 AM
	 * @param role_menu_list
	 * @return
	 */
	int insertRoleMenu(List<RoleMenu> role_menu_list);
	
	
}
