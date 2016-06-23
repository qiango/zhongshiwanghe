package com.hongzhi.zswh.back.club.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongzhi.zswh.app_v3.notification.service.NotificationService;
import com.hongzhi.zswh.back.club.dao.JoinClubDao;
import com.hongzhi.zswh.back.club.entity.JoinClub;
import com.hongzhi.zswh.back.club.entity.ParamObj;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 24, 2016    2:52:39 PM
 */
@Repository
public class JoinClubService {
	
	
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private JoinClubDao joinClubDao;
	
	@Autowired
	private NotificationService  notiSender ;

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 24, 2016    2:53:45 PM
	 * @param user
	 * @return
	 */
	public String waittingJoinList(SessionProperty sp) {
		List<Integer> roles = joinClubDao.userRoles(Integer.parseInt(sp.getUser_id()));
		List<JoinClub> waittingToJoin = new ArrayList<>();
		if(roles.contains(8)){
		  waittingToJoin = joinClubDao.ClubJoinUserByAdmin();
		}else{
		  waittingToJoin = joinClubDao.ClubJoinUserByAdminID(Integer.parseInt(sp.getUser_id()));
		}
		Map<String,Object> out = new HashMap<>();
		out.put("user_list",waittingToJoin );
		return dictionaryUtil.appOut("0", sp.getLanguage(), out);
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 24, 2016    3:13:04 PM
	 * @param sp
	 * @param paramObj 
	 * @return
	 * @throws HongZhiException 
	 * @throws  
	 */
	public String apply(SessionProperty sp, ParamObj paramObj) throws  HongZhiException {
		int update_count = joinClubDao.applyToJoinClub(Integer.parseInt(paramObj.getUser_id()),Integer.parseInt(paramObj.getClub_id())  );
		if(update_count==1){
			String clubName = joinClubDao.getClubName(Integer.parseInt(paramObj.getClub_id()));
			notiSender.sendNoti(Integer.parseInt(sp.getUser_id()), null , Integer.parseInt(paramObj.getUser_id()), "1" , "尊敬的用户，您的俱乐部申请已通过，您现在是"+clubName+"俱乐部的一员了。");
			return  ObjectUtil.jsonOut("true");
		}else{
			return  ObjectUtil.jsonOutError("1033", "false");
		}
	}

}
