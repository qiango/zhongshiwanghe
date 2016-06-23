package com.hongzhi.zswh.back.competition.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongzhi.zswh.back.club.entity.WorldCity;
import com.hongzhi.zswh.back.competition.dao.CompetitionDao;
import com.hongzhi.zswh.back.competition.dao.PlatformDao;
import com.hongzhi.zswh.back.competition.entity.Competition;
import com.hongzhi.zswh.back.competition.entity.Platform;
import com.hongzhi.zswh.util.basic.DbFile;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.page.PageModel;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 23, 2016    2:55:33 PM
 */
@Repository
public class CompetitionService {
	
	@Autowired
	private CompetitionDao competitionDao;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private PlatformDao platformDao;
	

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    2:57:51 PM
	 * @param params
	 * @return
	 */
	public String listByPage(Map<String, String> params) {
		PageModel pageModel = new PageModel(params.get("page_number"),params.get("page_size"), params.get("language_abbreviation"),"/competition/list.htmls");
		pageModel.setOther(params.get("competion_name"));
		pageModel.setTotalDataCount(competitionDao.listCompetitonByPageCount(pageModel));
		pageModel.setResult(competitionDao.listCompetitonByPage(pageModel));
		return dictionaryUtil.appOut(params.get("code"),params.get("language_abbreviation"), pageModel);
	}

	/**   worker : @zhuenjun
	 * Creat time : Mar 23, 2016    17:08 PM
	 * @param params
	 * @return  language_list  world_city_list
	 */
	public String insertLoad(SessionProperty sp ) {
		List<Map<String,String>> language_list = dictionaryUtil.selectByPcodeAndLanuage("language", sp.getLanguage() );
		List<WorldCity> world_city_list = competitionDao.listWorldCity(0);
		List<Platform> platform_list=platformDao.listPlatform();
		Map<String, Object> out = new HashMap<>();
		out.put("language_list", language_list);
		out.put("world_city_list", world_city_list);
		out.put("platform_list",platform_list);
		return dictionaryUtil.appOut("0", sp.getLanguage() , out);
	}
	
	/**   worker : @zhuenjun 
	 * Creat time : Mar 23, 2016    17:08 PM
	 * @param params
	 * @return
	 */
	public String insertSave(Map<String, String> params) {
		int insertCount = competitionDao.insertCompetition(params);
		if (insertCount == 1) {
			// Apr8,2016
				PageModel pm = new PageModel("1","1000");
				String out_str = dictionaryUtil.appOut("0",competitionDao.listCompetitonByPage(pm) );
				DbFile.writeFile(out_str, "competition.txt");
		   // end 
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		} else {
			return dictionaryUtil.appOut("1011", params.get("language_abbreviation"), "false");
		}
	}
	
	/**   worker : @zhuenjun 
	 * Creat time : Mar 23, 2016    18:19 PM
	 * @param params
	 * @return
	 */
	public String delete(Map<String, String> params) {
		int delete_count = competitionDao.competitionDelete(params.get("competition_id"));
		if (delete_count == 1) {
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		} else {
			return dictionaryUtil.appOut("1034", params.get("language_abbreviation"), "false");
		}
	}
	/**   worker : @zhuenjun 
	 * Creat time : Mar 23, 2016    17:10 PM
	 * @param params
	 * @return
	 */
	public String modifyLoad(Map<String, String> params) {
		Competition competition_query = competitionDao.selectByID(params.get("competition_id"),
				params.get("language_abbreviation"));
		List<WorldCity> world_city_list = competitionDao.listWorldCity(0);
		List<Map<String, String>> language_list = dictionaryUtil.selectByPcodeAndLanuage("language",
				params.get("language_abbreviation"));
		List<Platform> platform_list = platformDao.listPlatform();
		try {
			dictionaryUtil.verifyData(competition_query, "1012"); // 查无此数据
			Map<String, Object> out = new HashMap<>();
			out.put("competition_query", competition_query);
			out.put("world_city_list", world_city_list);
			out.put("language_list", language_list);
			out.put("platform_list", platform_list);
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), world_city_list );
	}
	
	/**   worker : @zhuenjun 
	 * Creat time : Mar 23, 2016    17:10 PM
	 * @param params
	 * @return
	 */
	public String modifySave(Map<String, String> params) {
		int update_count = competitionDao.updateCompetition(params);
		if (update_count == 1) {
			// Apr8,2016
			PageModel pm = new PageModel("1","1000");
			String out_str = dictionaryUtil.appOut("0",competitionDao.listCompetitonByPage(pm) );
			DbFile.writeFile(out_str, "competition.txt");
			//
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		} else {
			return dictionaryUtil.appOut("1033", params.get("language_abbreviation"), "false");
		}
	}

	public String changeStatus(Map<String, String> params) {
		int save_count=competitionDao.changeStatus(params.get("competition_id"),params.get("new_status"));
		if (save_count == 1) {
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		} else {
			return dictionaryUtil.appOut("1033", params.get("language_abbreviation"), "false");
		}
	}
}
