package com.hongzhi.zswh.app.me.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.app.me.entity.UserInfo;
import com.hongzhi.zswh.util.page.PageModel;

public interface MeDao {
        
	UserInfo selectByPhoneAndPlatform(@Param("phone") String phone, @Param("platform_id") int platform_id);
	int insertUserInfo(UserInfo userinfo); // 用户注册, 插入新数据, 返回用户ID
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 21, 2016    4:47:33 PM
	 * @param user_id
	 * @param platform_id
	 * @param language
	 * @return
	 */
	Map<String, Object> loadMe(@Param("user_id") int user_id, @Param("platform_id") int platform_id, @Param("language") String language);
	
	List<Map<String, Object>> loadMeCompetitons(PageModel pageModel);
	
	List<Map<String,Object>> loadMeSportsCamps(PageModel pageModel);
	
	int updatePassword(@Param("user_id") Integer user_id, @Param("phone") String phone, @Param("newPasswordTwo") String newPasswordTwo, @Param("salt") String salt);
	
	Map<String, String> getPassword(@Param("phone") String phone, @Param("user_id") Integer user_id);
	
}