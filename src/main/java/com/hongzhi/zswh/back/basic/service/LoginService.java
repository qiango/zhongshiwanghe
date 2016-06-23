package com.hongzhi.zswh.back.basic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongzhi.zswh.back.basic.dao.LoginDao;
import com.hongzhi.zswh.back.basic.entity.Menu;
import com.hongzhi.zswh.back.basic.entity.User;
import com.hongzhi.zswh.back.club.service.ClubService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.encryption.SHA256;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 22, 2016    1:32:06 PM
 */
@Repository
public class LoginService {
	
	@Autowired
	private LoginDao backLoginDao;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private MenuService menuService;
	@Autowired
	private SessionUtil sessionUtil;
	
	
	@Autowired
	private ClubService  clubService;
	

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 22, 2016    1:36:12 PM
	 * @param params
	 * @param session
	 * @return
	 */
	public String login(Map<String, String> params, HttpSession session) {
		Map<String,Object>  password_query=backLoginDao.selectUserIdAndPassword(params.get("user_login_name"));
		try {
			// user not exist 
			dictionaryUtil.verifyData(password_query, "1002"); 
			
			// verify password 
//			dictionaryUtil.verifyData(password_query.get("user_password").equals(params.get("user_password")), "1013"); 
			dictionaryUtil.verifyData( SHA256.verifyPassword(params.get("user_password").toString(), password_query.get("user_password").toString(), password_query.get("salt")) , "1013"); 
			
			// login success , query user basic information 
			User user=backLoginDao.selectUser((int)password_query.get("user_id"));
			user.setRole_list(backLoginDao.userRoles((int)password_query.get("user_id")));
			user.setLanguage(params.get("language_abbreviation"));
			session.setAttribute("user", user);
			
			// new session opt , Apr 5 ,2016
			List<String > attribute_string = new ArrayList<>();
			attribute_string.add("user_id,"+user.getUser_id());
			attribute_string.add("user_real_name,"+user.getUser_real_name());
			attribute_string.add("language,"+user.getLanguage());
			attribute_string.add("platform,"+"1");
			

			String sess_id = session.getId();
			int session_table_id =  sessionUtil.createNewSession(sess_id, attribute_string);
			session.setAttribute("id", session_table_id);
			// end sess
			
			// package out String 
			Map<String,Object> out = new HashMap<>();
			out.put("url", "/init/index");
			// out.put("user", user);
			session.setAttribute("user", user);
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), null);
	}



	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 24, 2016    10:52:15 AM
	 * @param session
	 * @return  menu_tree
	 */
	public String init(HttpSession session) {
		// User user_session=(User) session.getAttribute("user");
		SessionProperty sp = null;
		try {
			sp = sessionUtil.getProperty(session,"init");
			// define menu
			Menu menu=new  Menu();
			menu.setMenu_id(27); 
			Map<String,Object> out = new HashMap<>();
			out.put("user", sp);
			out.put("menu_tree", menuService.menuTree(menu,Integer.parseInt(sp.getUser_id())));
			out.put("clubRank", clubService.clubMemberRank());
			return dictionaryUtil.appOut("0", sp.getLanguage() ,out);
		} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode(), "" ,"");
		}
	}

}
