package com.hongzhi.zswh.back.sportsCamp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.back.sportsCamp.entity.SportsCamp;
import com.hongzhi.zswh.util.page.PageModel;


/**   Twitter : enjun.zhu 
 * Creat time : Mar 24, 2016    11:05:02 AM
 */


public interface SportsCampDao {

	List<SportsCamp> listSportsCampByPage(PageModel pageModel);

	int listSportsCampByPageCount(PageModel pageModel);

	int insertSportsCamp(Map<String, String> params);

	int SportsCampDelete(String sportscamp_id);

	int update(Map<String, String> params);

	SportsCamp selectById(@Param("sports_camp_id") String sports_camp_id, @Param("language") String language);
}
