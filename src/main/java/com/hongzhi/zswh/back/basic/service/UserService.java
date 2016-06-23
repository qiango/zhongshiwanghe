package com.hongzhi.zswh.back.basic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hongzhi.zswh.back.basic.dao.RoleDao;
import com.hongzhi.zswh.back.basic.dao.UserDao;
import com.hongzhi.zswh.back.basic.dao.UserRoleDao;
import com.hongzhi.zswh.back.basic.entity.RoleInfo;
import com.hongzhi.zswh.back.basic.entity.UserInfo;
import com.hongzhi.zswh.back.basic.entity.UserRole;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.page.PageModel;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 25, 2016    1:36:50 PM
 */
@Repository
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    1:38:41 PM
	 * @param user
	 * @param user_name
	 * @param page_size 
	 * @param page_number 
	 * @return
	 */
	public String userList(SessionProperty sp, String user_name, String page_number, String page_size) {
		PageModel pageModel = new PageModel( page_number , page_size, sp.getLanguage() ,"/user/list.htmls");
		pageModel.setOther(user_name);
		pageModel.setTotalDataCount( userDao.listUserByPageCount(pageModel) );
		pageModel.setResult(  userDao.listUserByPage(pageModel) );
		return dictionaryUtil.appOut("0", sp.getLanguage(), pageModel);
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    2:12:42 PM
	 * @param user 
	 * @return
	 */
	public String insertLoad(SessionProperty sp) {
		List<RoleInfo> roles = roleDao.allRoles();
		Map<String,Object> out = new HashMap<>();
		List<Map<String,String>> platform_list  =  dictionaryUtil.selectByPcodeAndLanuage("platform",  sp.getLanguage() );
		out.put("roles", roles);
		out.put("platform_list", platform_list);
		return dictionaryUtil.appOut("0", sp.getLanguage() , out);
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    2:49:04 PM
	 * @param userInfo
	 * @param user 
	 * @param user_roles_array
	 * @return
	 */
	@Transactional
	public String insertSave(UserInfo userInfo, String user_roles,SessionProperty sp) {
		String [] user_roles_array = user_roles.split(",");
		int  selectByPhone = userDao.selectByPhoneAndUserName(userInfo.getPhone(),userInfo.getUser_login_name());
		try {
			dictionaryUtil.verifyData(selectByPhone == 0 , "1020" );
			List<UserRole> roles_list = new ArrayList<>();
			 userDao.insertUserInfo(userInfo);
			for(int i=0;i<user_roles_array.length;i++){
				UserRole userRole = new UserRole();
				userRole.setUser_id(userInfo.getUser_id());
				userRole.setRole_id(Integer.parseInt(user_roles_array[i]));
				roles_list.add(userRole);
			}
			int roles_insert_count =  userRoleDao.insertRolesOfUser(roles_list);
			if( userInfo.getUser_id() > 0 && roles_insert_count == user_roles_array.length ){
				 return dictionaryUtil.appOut("0",sp.getLanguage() , "true");
			}else{
				return dictionaryUtil.appOut("1011", sp.getLanguage() , "false");
			}
		} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode(), sp.getLanguage() , "false");
		}
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    3:35:19 PM
	 * @param user_id
	 * @param user
	 * @return
	 */
	public String modifyLoad(String user_id, SessionProperty sp) {
		UserInfo   user_query = userDao.selectByUserId(Integer.parseInt(user_id));
		List<Map<String,String>> platform_list  =  dictionaryUtil.selectByPcodeAndLanuage("platform", sp.getLanguage());
		List<Integer> user_roles = userRoleDao.userRoleIds(Integer.parseInt(user_id));
		List<RoleInfo> roles = roleDao.allRoles();

		Map<String, Object> out = new HashMap<>();
		out.put("user_info", user_query);
		out.put("platform_list", platform_list);
		out.put("user_roles", user_roles);
		out.put("roles", roles);
		return dictionaryUtil.appOut("0", sp.getLanguage() , out);
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    3:45:17 PM
	 * @param userInfo
	 * @param user
	 * @param users_roles 
	 * @return
	 */
	@Transactional
	public String modifySave(UserInfo userInfo, SessionProperty sp, String users_roles) {
		int update_count = userDao.updateUserInfo(userInfo);
		String[] user_roles_array = users_roles.split(",") ;
		List<UserRole> roles_list = new ArrayList<>();
		for(int i=0;i<user_roles_array.length;i++){
			UserRole userRole = new UserRole();
			userRole.setUser_id(userInfo.getUser_id());
			userRole.setRole_id(Integer.parseInt(user_roles_array[i]));
			roles_list.add(userRole);
		}
			 userRoleDao.deleteRolesOfUser(userInfo.getUser_id());
			 userRoleDao.insertRolesOfUser(roles_list);
		if(update_count==1){
			return dictionaryUtil.appOut("0", sp.getLanguage() , "true");
		}else{
			return dictionaryUtil.appOut("1033", sp.getLanguage() , "false");
		}
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 28, 2016    3:39:07 PM
	 * @param user_id
	 * @param user
	 * @return
	 */
	public String userDelete(String user_id, SessionProperty sp) {
		int delete_count = userDao.logicDelete(Integer.parseInt(user_id));
		if(delete_count==1){
			return dictionaryUtil.appOut("0", sp.getLanguage() , "true");
		}else{
			return dictionaryUtil.appOut("1034", sp.getLanguage() , "false");
		}
	}

}
