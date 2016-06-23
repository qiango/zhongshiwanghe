package com.hongzhi.zswh.app.competition.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongzhi.zswh.app.competition.dao.AppCompetitionApplicationDao;
import com.hongzhi.zswh.app.competition.entity.CompetitionApplicationData;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;



@Repository
public class AppCompetitionApplicationService {
	
	@Autowired
	private AppCompetitionApplicationDao appCompetitionApplicationDao;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	public String loadByCompetitionId(Map<String, String> params) {
	    Integer competition_id=Integer.valueOf(params.get("competition_id"));
	    Integer platform_id=Integer.valueOf(params.get("platform_id"));
	    try {
			dictionaryUtil.verifyData(platform_id==2, "1024");
		} catch (HongZhiException e) {
			e.printStackTrace();
		}
		List<Map<String,Object>> appCompetitionApplication_list=appCompetitionApplicationDao.loadByCompetitionId(competition_id);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("competition_application_list", appCompetitionApplication_list);
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), map);
	}

	public String saveCompetitionApplicationData(Map<String, String> params) {
//		"user_id,language_abbreviation,platform_id,competition_id,controls_ids,competition_application_ids,user_values,title_names"
		 String[] controls_id_array = params.get("controls_ids").split("\\$\\$");
		 String[] competition_application_id_array = params.get("competition_application_ids").split("\\$\\$");
		 String[] user_values_array = params.get("user_values").split("\\$\\$");
		 String[] title_name_array = params.get("title_names").split("\\$\\$");
		
		 List<CompetitionApplicationData> data_build_list = new ArrayList<>();
		 for(int i=0;i<competition_application_id_array.length;i++){
			 CompetitionApplicationData build = new CompetitionApplicationData();
			 build.setControls_id(Integer.parseInt(controls_id_array[i]));
			 build.setCompetition_application_id(Integer.parseInt(competition_application_id_array[i]));
			 build.setUser_id(Integer.parseInt(params.get("user_id")));
			 build.setCompetition_id(Integer.parseInt(params.get("competition_id")));
			 build.setTitle_name(title_name_array[i]);
			 build.setUser_value(user_values_array[i]);
			 build.setControls_order(i);
			 data_build_list.add(build);
		 }
		 int save_status=appCompetitionApplicationDao.saveCompetitionApplicationData(data_build_list);
		 int user_com_count = appCompetitionApplicationDao.saveUserCompetition(Integer.parseInt(params.get("user_id")),Integer.parseInt(params.get("competition_id")));
		 if(save_status==competition_application_id_array.length && (user_com_count==1 || user_com_count==2 ) ){
				Map<String,Object> out=new HashMap<String, Object>();
				out.put("save_status", "true");
			return dictionaryUtil.appOut(params.get("code"),
					params.get("language_abbreviation"), out);
		} else {
			Map<String, Object> out = new HashMap<String, Object>();
			out.put("save_status", "false");
			return dictionaryUtil.appOut("1011",params.get("language_abbreviation"), out);
		}
		
	}
		
		
		
		
		
	
	

}
