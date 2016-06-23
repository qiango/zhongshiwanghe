package com.hongzhi.zswh.app.competition.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.app.competition.entity.CompetitionApplicationData;

public interface AppCompetitionApplicationDao {
	

List<Map<String, Object>> loadByCompetitionId(/*@Param("user_id") Integer user_id,
										      @Param("platform_id") Integer platform_id,*/
                                              @Param("competition_id") Integer competition_id);

			
			    int saveCompetitionApplicationData(List<CompetitionApplicationData> data_list);


				int saveUserCompetition(@Param("user_id") int user_id, @Param("competition_id") int competition_id);

}
