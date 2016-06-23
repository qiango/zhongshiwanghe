package com.hongzhi.zswh.back.competition.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.back.club.entity.WorldCity;
import com.hongzhi.zswh.back.competition.entity.Competition;
import com.hongzhi.zswh.util.page.PageModel;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 23, 2016    2:48:02 PM
 */
public interface CompetitionDao {
	
	List<Competition> listCompetitonByPage(PageModel pageModel);
	int listCompetitonByPageCount(PageModel pageModel);
	List<WorldCity>  listWorldCity(int parent_id);
	int insertCompetition(Map<String, String> params);
	int competitionDelete(String competition_id);
	int updateCompetition(Map<String, String> params);
	Competition selectByID(@Param("competition_id") String competition_id, @Param("language") String language);
	int changeStatus(@Param("competition_id") String competition_id, @Param("new_status") String new_status);
	List<Competition>  listCompetitiony();
}
