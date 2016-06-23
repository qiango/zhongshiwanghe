package com.hongzhi.zswh.app.me.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongzhi.zswh.app.me.dao.AppSportsCampDao;
import com.hongzhi.zswh.app.me.dao.AppUserDetailDao;
import com.hongzhi.zswh.app.me.dao.AppUserInfoDao;
import com.hongzhi.zswh.app.me.entity.SportsCamp;
import com.hongzhi.zswh.app.me.entity.UserDetail;
import com.hongzhi.zswh.app.me.entity.UserInfo;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.encryption.SHA256;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 25, 2016    2:20:31 PM
 */
@Repository
public class AppLogInService {

	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private AppUserInfoDao appUserInfoDao;
	@Autowired
	private AppUserDetailDao appUserDetailDao;
	@Autowired
	private AppSportsCampDao appSportsCampDao;
	
	
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    2:22:12 PM
	 * @param params
	 * @param session
	 * @return
	 */
	public String selectUserInfoByPhone(Map<String, String> params, HttpSession session) {
		Map<String,Object> out=new HashMap<>();
		try {
			// 查询数据库及数据处理
			UserInfo user_query = appUserInfoDao.selectByPhoneAndPlatform(params.get("phone"), Integer.parseInt(params.get("platform_id")) );
			String sport_camp_id = "";
			int user_id = 0;
			
			String inputPassword = params.get("user_password");
			
			
			dictionaryUtil.verifyData(user_query, "1002");
//			dictionaryUtil.verifyData(user_query.getUser_password().equals(params.get("user_password")), "1013");
			dictionaryUtil.verifyData(SHA256.verifyPassword(inputPassword, user_query.getUser_password(), user_query.getSalt()), "1013");
			dictionaryUtil.verifyData(user_query.getUser_status().equals("1"), "1015");
			
		    UserDetail userDetail_query=appUserDetailDao.selectByID(user_query.getUser_id());
			List<SportsCamp> user_sports_camp = appSportsCampDao.selectSportsCampsByUserId(user_query.getUser_id());

			if (!ObjectUtil.isEmpty(user_sports_camp)) {
				for (int i = 0; i < user_sports_camp.size(); i++) {
					if (i != 0) {
						sport_camp_id += ",";
					}
					sport_camp_id += user_sports_camp.get(i).getSports_camp_id();
				}
			}
			user_id = user_query.getUser_id();
			session.setAttribute("user_id", user_id);
			
			// 整理输出数据
			out.put("user_id", user_id);
			out.put("session_id", session.getId());
			out.put("phone", user_query.getPhone() );
			out.put("jump_club_number",  ObjectUtil.isEmpty(userDetail_query) ? "0" : ObjectUtil.getProperty(userDetail_query.getJump_club_number(), "0") );
			out.put("join_club_status",  ObjectUtil.isEmpty(userDetail_query) ? "0" : ObjectUtil.getProperty(userDetail_query.getJoin_club_status(), "0") );
			out.put("user_name", user_query.getUser_real_name());
			out.put("sports_camp_id", sport_camp_id);
			Map<String, Object> aa = new HashMap<>();
			aa.put("user_info", out);
			
			return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , aa);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    2:22:21 PM
	 * @param params
	 * @param session
	 * @return
	 */
	public String register(Map<String, String> params, HttpSession session) {
		//判断数据库有没有该phone
		String phone = params.get("phone");
		Integer user_id_temp = appUserInfoDao.getIdbyPhone(phone);
		
		if (!ObjectUtil.isEmpty(user_id_temp)) {
			// 注册失败
			return dictionaryUtil.appOut("1003", params.get("language_abbreviation"), "false");
		} else {
			// 定义要插入数据库的数据
			String salt = SHA256.getSalt();
			UserInfo user_input = new UserInfo();
			user_input.setPhone(params.get("phone"));
			user_input.setUser_login_name(params.get("phone"));
			user_input.setPlatform_id(Integer.parseInt(params.get("platform_id")));
			user_input.setUser_password(SHA256.encode(params.get("user_password"), salt));
			user_input.setUser_status("1");
			user_input.setIs_delete("0");
			user_input.setUser_real_name("");
			user_input.setSalt(salt);
			
			// 插入数据库
			int insert_id = appUserInfoDao.insertUserInfo(user_input);
			
			if (insert_id > 0) {
				Integer user_id = user_input.getUser_id();
				session.setAttribute("user_id", user_id.toString());
				Map<String, String> userInfo = new HashMap<>();
				userInfo.put("user_id", user_id.toString());
				userInfo.put("session_id", session.getId());
				userInfo.put("phone", params.get("phone"));
				userInfo.put("user_name", user_input.getUser_real_name());
				Map<String, Object> out = new HashMap<>();
				out.put("user_info", userInfo);
				return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
			} else {
				// 保存失败
				return dictionaryUtil.appOut("1011", params.get("language_abbreviation"), null);
			}
		}
	}

}
