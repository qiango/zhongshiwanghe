package com.hongzhi.zswh.app.competition.dao;

import java.util.List;
import java.util.Map;

import com.hongzhi.zswh.util.page.PageModel;

public interface AppInformationDao {
	
	List<Map<String,Object>> loadInformationByCompetitionId(PageModel pageModel);
	
	List<Map<String,Object>> getImageByInfoId(List<Integer> list);

	List<Map<String,Object>> loadInformationDetailByInformationId(Integer information_id);
}
