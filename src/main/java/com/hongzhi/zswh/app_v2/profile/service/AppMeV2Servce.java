package com.hongzhi.zswh.app_v2.profile.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.app_v2.profile.dao.AppMeV2Dao;
import com.hongzhi.zswh.util.basic.DictionaryUtil;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 7, 2016    6:37:11 PM
 */
@Service
public class AppMeV2Servce {
	
	@Autowired
	private AppMeV2Dao appMeV2Dao;
	@Autowired
	private DictionaryUtil dictionaryUtil;

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 7, 2016    6:40:10 PM
	 * @param params
	 * @return
	 */
	public String loadMe(Map<String, String> params) {
		int user_id=Integer.parseInt(params.get("user_id"));
		int platform_id = Integer.parseInt(params.get("platform_id"));
		String language=params.get("language_abbreviation");
		Map<String,Object> map=appMeV2Dao.loadMe(user_id,platform_id,language);
			if(map==null){
				Map<String,Object> out=new HashMap<String, Object>();
				out.put("join_club_status","0");  // 用用加入俱乐部狀态
				out.put("user_competition_number","0"); // 用户加入赛事的数量
				out.put("user_sports_camp_number", "0"); // 用户加入运动派的数量
				out.put("user_name","");
				out.put("profile_picture","");
				return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);	
			}else{
//				Map<String,Object> out=new HashMap<String, Object>();
//				out.put("join_club_status",map.get("join_club_status"));  // 用用加入俱乐部狀态
//				out.put("user_competition_number",map.get("user_competition_number")); // 用户加入赛事的数量
//				out.put("user_sports_camp_number", map.get("user_sports_camp_number")); // 用户加入运动派的数量
//				out.put("user_name",map.get("user_name"));
				return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), map );	
			}
	}

}
