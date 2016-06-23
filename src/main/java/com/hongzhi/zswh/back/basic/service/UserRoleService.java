package com.hongzhi.zswh.back.basic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hongzhi.zswh.back.basic.dao.RoleDao;
import com.hongzhi.zswh.back.basic.dao.UserRoleDao;
import com.hongzhi.zswh.back.basic.entity.RoleInfo;
import com.hongzhi.zswh.back.basic.entity.UserRole;
import com.hongzhi.zswh.util.basic.DictionaryUtil;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 24, 2016    11:14:31 AM
 */
@Repository
public class UserRoleService {
	
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private UserRoleDao userRoleDao;
	

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 24, 2016    11:28:28 AM
	 * @param params
	 * @return
	 */
	public String roleOfUser(Map<String, String> params) {
		List<Integer> user_role_ids = userRoleDao.userRoleIds(Integer.parseInt(params.get("user_id")));
		List<RoleInfo> all_roles = roleDao.allRoles();
		Map<String,Object> out=new HashMap<>();
		out.put("user_role_ids", user_role_ids);
		out.put("all_roles", all_roles);
		return dictionaryUtil.appOut(params.get("code"), params.get("language"), out);
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 24, 2016    1:02:43 PM
	 * @param params  user_id role_ids
	 * @return
	 */
	@Transactional
	public String updateUserRoles(Map<String, String> params) {
		int user_id=Integer.parseInt(params.get("user_id"));
		userRoleDao.deleteRolesOfUser(user_id);
		List<UserRole> user_role_list=new ArrayList<>();
		String [] roles=params.get("role_ids").split(",");
		for(int i=0;i<roles.length;i++){
			UserRole userRole=new UserRole();
			userRole.setUser_id(user_id);
			userRole.setRole_id(Integer.parseInt(roles[i]));
			user_role_list.add(userRole);
		}
		int insert_count=userRoleDao.insertRolesOfUser(user_role_list);
		if(insert_count==roles.length){
			return dictionaryUtil.appOut(params.get("code"), params.get("language"), insert_count);
		}else{
			return dictionaryUtil.appOut("1033", params.get("language"), null);
		}
	}

}
