package com.hongzhi.zswh.app.competition.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.app.competition.entity.AppCompetition;
import com.hongzhi.zswh.util.page.PageModel;

public interface AppCompetitionDao {
	List<AppCompetition> selectCompetitionPage(PageModel pagemodel);
	int listCompetitonByPageCount(PageModel pageModel);
	List<Map<String,Object>> getCompetitionById(@Param("competition_id") Integer competition_id);
	Map<String, Object> getJoinCompetition(@Param("user_id") Integer user_id, @Param("platform_id") Integer platform_id, @Param("competition_id") Integer competition_id);
}
