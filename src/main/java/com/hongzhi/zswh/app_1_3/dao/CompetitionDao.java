package com.hongzhi.zswh.app_1_3.dao;

import com.hongzhi.zswh.app_1_3.entity.CompetitionNews;
import com.hongzhi.zswh.app_1_3.entity.CompetitionNewsImage;
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

    List<CompetitionNews> competitionNews(@Param("compID") String competition_id, @Param("startRow") Integer start_row, @Param("size") Integer page_size);

    List<CompetitionNewsImage> competitionNewsImages(@Param("newsID") List<Integer> news_id_list);

    Map<String, Object> getJoinCompetition(@Param("user_id") Integer user_id, @Param("platform_id") Integer platform_id, @Param("competition_id") Integer competition_id);
}
