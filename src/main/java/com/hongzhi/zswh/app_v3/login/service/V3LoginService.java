package com.hongzhi.zswh.app_v3.login.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.hongzhi.zswh.app_1_3.entity.MiPushRegid;
import com.hongzhi.zswh.app_1_3.service.MiPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.app.me.dao.AppSportsCampDao;
import com.hongzhi.zswh.app.me.dao.AppUserDetailDao;
import com.hongzhi.zswh.app.me.dao.AppUserInfoDao;
import com.hongzhi.zswh.app.me.entity.SportsCamp;
import com.hongzhi.zswh.app.me.entity.UserDetail;
import com.hongzhi.zswh.app.me.entity.UserInfo;
import com.hongzhi.zswh.app_v3.login.dao.V3LoginDao;
import com.hongzhi.zswh.app_v3.login.entity.LoginParam;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.encryption.SHA256;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 26, 2016    2:01:29 PM
 */
@Service
public class V3LoginService {
	
	@Autowired
	private V3LoginDao  dao;
	@Autowired
	private DictionaryUtil dictionary;
	@Autowired
	private SessionUtil sess;
	
	@Autowired
	private AppUserDetailDao appUserDetailDao;
	@Autowired
	private AppSportsCampDao appSportsCampDao;
	@Autowired
	private AppUserInfoDao appUserInfoDao;

    @Autowired
    private MiPushService miPushService;

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 26, 2016    2:30:00 PM
	 * @param loginParam
	 * @param session
	 * @return
	 */
	public String login(LoginParam loginParam, HttpSession session) {
		try {
			String inputPassword = loginParam.VUser_password();
			Map<String,Object> userInfo = dao.login(loginParam.VPhone(),loginParam.VPlatform_id());
			if(ObjectUtil.isEmpty(userInfo)){
				throw new HongZhiException("1013");
			}else if(!SHA256.verifyPassword(inputPassword, userInfo.get("user_password").toString(), userInfo.get("salt") )){
				throw new HongZhiException("1013");
			}
			List<String > attribute_string = new ArrayList<>();
			attribute_string.add("user_id,"+ userInfo.get("user_id"));
			attribute_string.add("user_real_name,"+ObjectUtil.getProperty(userInfo.get("user_real_name"),"  "));
			attribute_string.add("language,"+ loginParam.getLanguage() );
			attribute_string.add("platform,"+ loginParam.getPlatform_id());
            attribute_string.add("club_id,"+ userInfo.get("club_id"));
            attribute_string.add("club_user_level,"+ userInfo.get("club_user_level") );

			int sess_id = sess.createNewSession(session.getId(), attribute_string);
//			session.setAttribute("id", sess_id);
			session.setAttribute("user_id", userInfo.get("user_id"));

			
			UserDetail userDetail_query=appUserDetailDao.selectByID(Integer.parseInt(userInfo.get("user_id").toString()));
			List<SportsCamp> user_sports_camp = appSportsCampDao.selectSportsCampsByUserId(Integer.parseInt(userInfo.get("user_id").toString()));
			String sport_camp_id = "";
			if (!ObjectUtil.isEmpty(user_sports_camp)) {
				for (int i = 0; i < user_sports_camp.size(); i++) {
					if (i != 0) {
						sport_camp_id += ",";
					}
					sport_camp_id += user_sports_camp.get(i).getSports_camp_id();
				}
			}
			Map<String,Object> out=new HashMap<>();
			out.put("user_id", userInfo.get("user_id").toString());
//			out.put("session_id", session.getId());
			out.put("session_id", sess_id );
			out.put("phone", userInfo.get("phone"));
			out.put("jump_club_number",  ObjectUtil.isEmpty(userDetail_query) ? "0" : ObjectUtil.getProperty(userDetail_query.getJump_club_number(), "0") );
			out.put("join_club_status",  ObjectUtil.isEmpty(userDetail_query) ? "0" : ObjectUtil.getProperty(userDetail_query.getJoin_club_status(), "0") );
			out.put("user_name", userInfo.get("user_real_name"));
			out.put("sports_camp_id", sport_camp_id);
			Map<String, Object> aa = new HashMap<>();
			aa.put("user_info", out);

            // mi push
            if( !ObjectUtil.isEmpty(loginParam.getApp_type()) && !ObjectUtil.isEmpty(loginParam.getRegid())){
                MiPushRegid miPushRegid = new MiPushRegid();
                miPushRegid.setUser_id(userInfo.get("user_id").toString());
                miPushRegid.setStatus("1");
                miPushRegid.setApp_type(loginParam.getApp_type());
                miPushRegid.setRegid(loginParam.getRegid());
                miPushService.saveRegidOnLogIn(miPushRegid);
            }

			return  ObjectUtil.jsonOut(aa);
		} catch (HongZhiException e) {
			return  ObjectUtil.jsonOutError(e.getCode(), dictionary.getCodeValue(e.getCode(), loginParam.getLanguage() ));
		}
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 26, 2016    2:30:08 PM
	 * @param loginParam
	 * @param session
	 * @return
	 */
	public String register(LoginParam loginParam, HttpSession session) {
		//判断数据库有没有该phone
		try {
				String phone = loginParam.VPhone();
				
				Integer user_id_temp = appUserInfoDao.getIdbyPhone(phone);
				
					if (!ObjectUtil.isEmpty(user_id_temp)) {
						throw new HongZhiException("1003");
					} 
					// 定义要插入数据库的数据
					String salt = SHA256.getSalt();
					UserInfo user_input = new UserInfo();
					user_input.setPhone(loginParam.VPhone());
					user_input.setUser_login_name(loginParam.VPhone());
					user_input.setPlatform_id(Integer.parseInt(loginParam.VPlatform_id()));
					user_input.setUser_password(SHA256.encode(loginParam.VUser_password(), salt));
					user_input.setUser_status("1");
					user_input.setIs_delete("0");
					user_input.setUser_real_name("");
					user_input.setSalt(salt);
					// 插入数据库
					int insert_id = appUserInfoDao.insertUserInfo(user_input);
					
					if (insert_id > 0) {
						Integer user_id = user_input.getUser_id();
						session.setAttribute("user_id", user_id.toString());
						Map<String, Object> userInfo = new HashMap<>();
						userInfo.put("user_id", user_id.toString());
						userInfo.put("phone", loginParam.VPhone());
						userInfo.put("user_name", user_input.getUser_real_name());
						
						List<String > attribute_string = new ArrayList<>();
						attribute_string.add("user_id,"+userInfo.get("user_id"));
						attribute_string.add("user_real_name,"+ObjectUtil.getProperty(userInfo.get("user_real_name"),"  "));
						attribute_string.add("language,"+ loginParam.getLanguage() );
						attribute_string.add("platform,"+ loginParam.getPlatform_id());
                        attribute_string.add("club_id,"+ 0);
                        attribute_string.add("club_user_level,"+ 99 );

						int sess_id = sess.createNewSession(session.getId(), attribute_string);
//						session.setAttribute("id", sess_id);
						
						userInfo.put("session_id",sess_id);
						Map<String, Object> out = new HashMap<>();
						out.put("user_info", userInfo);

                        // mi push
                        if( !ObjectUtil.isEmpty(loginParam.getApp_type()) && !ObjectUtil.isEmpty(loginParam.getRegid())){
                            MiPushRegid miPushRegid = new MiPushRegid();
                            miPushRegid.setUser_id(userInfo.get("user_id").toString());
                            miPushRegid.setStatus("1");
                            miPushRegid.setApp_type(loginParam.getApp_type());
                            miPushRegid.setRegid(loginParam.getRegid());
                            miPushService.saveRegidOnLogIn(miPushRegid);
                        }

						return  ObjectUtil.jsonOut(out);
					} else {
						throw new HongZhiException("1011");
					}
		} catch (HongZhiException e) {
			return  ObjectUtil.jsonOutError(e.getCode(), dictionary.getCodeValue(e.getCode(), loginParam.getLanguage() ));
		}
	}
	
	
	
	

}
