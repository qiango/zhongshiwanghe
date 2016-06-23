package com.hongzhi.zswh.app.club.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongzhi.zswh.app.club.dao.AppClubDao;
import com.hongzhi.zswh.app.club.entity.SportsCamp;
import com.hongzhi.zswh.app.club.entity.UserSportsCamp;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.page.PageModel;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 22, 2016    3:39:22 PM
 */
@Repository
public class AppClubService {

	
	@Autowired
	private AppClubDao clubDao;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 22, 2016    3:42:31 PM
	 * @param params
	 * @return  club by pageModel
	 */
	public String listClub(Map<String, String> params) {
	//Integer user_id=Integer.parseInt(params.get("user_id"));
    //Integer platform_id=Integer.parseInt(params.get("platform_id"));
		
		Map<String,Object> out = new HashMap<>();
		out.put("club_list", clubDao.listClub() );
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 28, 2016    4:10:12 PM
	 * @param user
	 * @param club_id
	 * @return
	 */
	public String listSportCamp(Map<String,String> params) {
		List<SportsCamp> club_sprots = clubDao.clubSports(Integer.parseInt(params.get("club_id")));
		Map<String,Object> out = new HashMap<>();
		out.put("sport_camp_list", club_sprots);
		return dictionaryUtil.appOut("0", params.get("language_abbreviation"), out);
	}


	public String jumpSelectClub(Map<String, String> params) {
		Integer user_id = Integer.valueOf(params.get("user_id"));
		Integer platform_id = Integer.valueOf(params.get("platform_id"));
		if (platform_id == 2) {
			Map<String, Object> out = clubDao.selectByID(user_id);
			return dictionaryUtil.appOut(params.get("code"),params.get("language_abbreviation"), out);
		} else {
			return dictionaryUtil.appOut("1024",params.get("language_abbreviation"), null);
		}
	}
	public String saveUserSportCampAndClub(Map<String, String> params) {
		String[] sprorts_camp_id_array = params.get("sports_camp_id").split(",");
		Integer user_id=Integer.valueOf(params.get("user_id"));
		Integer club_id=Integer.valueOf(params.get("club_id"));
		
		List<UserSportsCamp> user_sports_camp_list = new ArrayList<>();
		for(int i=0;i<sprorts_camp_id_array.length ;i++){
			UserSportsCamp userSportsCamp = new UserSportsCamp();
			userSportsCamp.setUser_id(Integer.parseInt(params.get("user_id")));
			userSportsCamp.setSports_camp_id(Integer.parseInt(sprorts_camp_id_array[i]));
			user_sports_camp_list.add(userSportsCamp);
		}
		
		 int save_con_sport=clubDao.saveUserSportCamp(user_sports_camp_list);
		 int save_con_club=clubDao.saveUserClub(user_id,club_id);
		 if(save_con_sport == sprorts_camp_id_array.length  && save_con_club==1){
			 return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		 }else{
			 return dictionaryUtil.appOut("1011", params.get("language_abbreviation"), "false");
		 }
	}


	public String loadClubManage(Map<String, String> params) {
		Integer user_id=Integer.valueOf(params.get("user_id"));
		Integer platform_id=Integer.valueOf(params.get("platform_id"));
		if(platform_id==2){
		Map<String,Object> out= new HashMap<>();
		Map<String,Object> info= new HashMap<>();
		Map<String,Object> query_result = clubDao.getBasicClubInfo(user_id);
		info.put("club_member_number", ObjectUtil.isEmpty(query_result)?"0":query_result.get("club_member_number").toString());
		info.put("complete_training_number","0");
		info.put("comprehensive_integral","0");
		info.put("continuous_training_number","0");
		info.put("join_club_status", ObjectUtil.isEmpty(query_result)?"0": query_result.get("join_club_status").toString());
		info.put("medal_number","0");
		info.put("ranking","0");
		info.put("sports_camp_number",ObjectUtil.isEmpty(query_result)?"0": query_result.get("sports_camp_number").toString());
		info.put("total_training_number","0");
		info.put("club",ObjectUtil.notNullEntity( clubDao.selectClubInfoByUserIdApp(user_id) ));
		out.put("club_interface_data",info);
		   return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
		}else{
		   return dictionaryUtil.appOut("1024", params.get("language_abbreviation"), null);
		}
	}

}
