package com.hongzhi.zswh.back.club.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hongzhi.zswh.back.club.dao.ClubDao;
import com.hongzhi.zswh.back.club.dao.ClubSportCampDao;
import com.hongzhi.zswh.back.club.dao.ClubWorldCityDao;
import com.hongzhi.zswh.back.club.entity.Club;
import com.hongzhi.zswh.back.club.entity.ClubSportCamp;
import com.hongzhi.zswh.back.club.entity.WorldCity;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.page.PageModel;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 22, 2016    3:39:22 PM
 */
@Repository
public class ClubService {

	
	@Autowired
	private ClubDao clubDao;
	@Autowired
	private ClubWorldCityDao clubWorldCityDao;
	@Autowired
	private ClubSportCampDao clubSportCampDao;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 22, 2016    3:42:31 PM
	 * @param params
	 * @return  club by pageModel
	 */
	public String listClubByPage(Map<String, String> params) {
		PageModel pageModel = new PageModel(params.get("page_number") , params.get("page_size"),params.get("language_abbreviation"),"/club/list.htmls" );
		pageModel.setOther(params.get("club_name"));
		pageModel.setTotalDataCount(clubDao.listClubByPageCount(pageModel));
		pageModel.setResult( clubDao.listClubByPage(pageModel)  );
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), pageModel);
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    10:38:43 AM
	 * @param params
	 * @return  language_list  world_city_list
	 */
	public String insertLoad(Map<String, String> params) {
		List<Map<String,String>> language_list = dictionaryUtil.selectByPcodeAndLanuage("language", params.get("language_abbreviation"));
//		List<WorldCity> world_city_list=clubDao.listWorldCity(0);
		List<WorldCity> world_city_list= clubWorldCityDao.rootCity();
		Map<String,Object> out=new HashMap<>();
			out.put("language_list", language_list);
			out.put("world_city_list", world_city_list);
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    1:35:40 PM
	 * @param params
	 * @return
	 */
	public String insertSave(Map<String, String> params) {
		params.put("club_create_date",  params.get("club_create_date").replaceAll("-", "") );
		int insertCount=clubDao.insertClub(params);
		if(insertCount==1){
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		}else{
			return dictionaryUtil.appOut("1011", params.get("language_abbreviation"), "false");
		}
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    1:39:39 PM
	 * @param params
	 * @return  Club
	 */
	public String modifyLoad(Map<String, String> params) {
		Club club_query=clubDao.selectById(params.get("club_id"), params.get("language_abbreviation"));
		List<Map<String,String>> language_list = dictionaryUtil.selectByPcodeAndLanuage("language", params.get("language_abbreviation"));
		List<WorldCity> root_city = clubWorldCityDao.rootCity();
		try {
			dictionaryUtil.verifyData(club_query, "1012"); //查无此数据
			Map<String,Object> out = new HashMap<>();
			out.put("club", club_query) ;
			out.put("language_list", language_list);
			out.put("city_list", root_city  ) ;
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());	
		}
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "" );
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    1:43:47 PM
	 * @param params
	 * @return
	 */
	public String modifySave(Map<String, String> params) {
		params.put("club_create_date",  params.get("club_create_date").replaceAll("-", "") );
		int update_count = clubDao.update(params);
		if(update_count==1){
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		}else{
			return dictionaryUtil.appOut("1033", params.get("language_abbreviation"), "false");
		}
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    1:45:10 PM
	 * @param params
	 * @return
	 */
	public String delete(Map<String, String> params) {
		int delete_count = clubDao.logicDelete(params.get("club_id"));
		if(delete_count==1){
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		}else{
			return dictionaryUtil.appOut("1034", params.get("language_abbreviation"), "false");
		}
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 28, 2016    4:10:12 PM
	 * @param user
	 * @param club_id
	 * @return
	 */
	public String listSportCamp(SessionProperty sp, String club_id) {
		List<Integer> sportCampIdList = clubSportCampDao.sprotCamp(Integer.parseInt(club_id));
		List<Map<String,Object>> sportsCamps_list = clubSportCampDao.allSportsCamps();
		Map<String,Object> out = new HashMap<>();
		out.put("sports_camps_id_list", sportCampIdList);
		out.put("sports_camps_all", sportsCamps_list);
		return dictionaryUtil.appOut("0", sp.getLanguage(), out);

	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 8, 2016    1:39:36 PM
	 * @param sp
	 * @param club_id
	 * @param sports_camp_id
	 * @return
	 */
	@Transactional
	public String saveSportCamp(SessionProperty sp, String club_id, String sports_camp_id) {
		int clubId = Integer.parseInt(club_id);
		List<ClubSportCamp> list = new ArrayList<>();
		String[] sportId = sports_camp_id.split(",");
		for(int i=0;i<sportId.length;i++) {
			ClubSportCamp cs = new ClubSportCamp();
			cs.setClub_id(clubId);
			cs.setSports_camp_id(Integer.parseInt(sportId[i]));
			list.add(cs);
		}
		clubSportCampDao.deleteClubSportsCamp(clubId);
		int insert_cnt = clubSportCampDao.insertClubSportsCamp(list);
		if(insert_cnt==sportId.length){
			return dictionaryUtil.appOut("0", "true");
		}else{
			return dictionaryUtil.appOut("1033", "false");
		}
	}
	
	
	
	public List<Map<String,Object>>  clubMemberRank(){
		return clubDao.clubMeberRank();
	}

}
