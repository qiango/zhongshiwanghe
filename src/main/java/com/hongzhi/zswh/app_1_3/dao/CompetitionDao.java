package com.hongzhi.zswh.app_1_3.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by taylor on 7/21/16.
 * twitter: @taylorwang789
 */
@Repository("app_1_3_competition_dao")
public interface CompetitionDao {

    List<Map<String,Object>> competitionNews(@Param("compID") String competition_id,@Param("startRow") Integer start_row,@Param("size") Integer page_size);

    Map<String, Object> getJoinCompetition(@Param("user_id") Integer user_id, @Param("platform_id") Integer platform_id, @Param("competition_id") Integer competition_id);
}
