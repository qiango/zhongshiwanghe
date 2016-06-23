package com.hongzhi.zswh.app.competition.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;









import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongzhi.zswh.app.competition.dao.AppCompetitionDao;
import com.hongzhi.zswh.app.competition.entity.AppCompetition;
import com.hongzhi.zswh.util.basic.DbFile;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.page.PageModel;

@Repository
public class AppCompetitionService {

	@Autowired
	private AppCompetitionDao competitionDao;
	@Autowired
	private DictionaryUtil dictionaryUtil;

	public String loadCompetition(Map<String, String> params) {
		PageModel pageModel = new PageModel(params.get("page_number"),params.get("page_size"), params.get("language_abbreviation"));
		Map<String,Object> out= new HashMap<>();
		out.put("competition_list", competitionDao.selectCompetitionPage(pageModel));
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
		// Apr8,2016 
//		String out_str = DbFile.getFileContext("competition.txt");
//		if( ObjectUtil.isEmpty(out_str) ){
//			PageModel pm = new PageModel("1","1000");
//			out_str = dictionaryUtil.appOut("0", out_str);
//			DbFile.writeFile(out_str, "competition.txt");
//		}
//		return out_str;
	}

	public String getCompetitionById(Map<String, String> params) {
//		Integer user_id=Integer.valueOf(params.get("user_id"));
//		Integer platform_id=Integer.valueOf(params.get("platform_id"));
		Integer competition_id=Integer.valueOf(params.get("competition_id"));
		List<Map<String,Object>> appCompetition=competitionDao.getCompetitionById(competition_id);
		//Map<String, Object> out=new HashMap<String, Object>();
		//out.put("date", appCompetition);
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), appCompetition.get(0));
	}

	public String getJoinCompetition(Map<String, String> params) {
		Integer user_id=Integer.valueOf(params.get("user_id"));
		Integer platform_id=Integer.valueOf(params.get("platform_id"));
		Integer competition_id=Integer.valueOf(params.get("competition_id"));
		try {
			dictionaryUtil.verifyData(platform_id==2, "1024");
		} catch (HongZhiException e) {
			e.printStackTrace();
		}
		Map<String, Object> out=competitionDao.getJoinCompetition(user_id,platform_id,competition_id);
		if (ObjectUtil.isEmpty(out)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("join_club_status", "0");
			map.put("user_competition_status", "0");
			return dictionaryUtil.appOut(params.get("code"),params.get("language_abbreviation"), map);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("join_club_status", out.get("join_club_status"));
			map.put("user_competition_status",out.get("user_competition_status"));
			return dictionaryUtil.appOut(params.get("code"),params.get("language_abbreviation"), map);
		}
	}
	}

