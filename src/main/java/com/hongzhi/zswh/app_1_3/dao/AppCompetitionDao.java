package com.hongzhi.zswh.app_1_3.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xuejian
 * Date: 2016/7/21
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
@Repository("app_1_3_AppCompetitionDao")
public interface AppCompetitionDao {
    Map<String, Object> getJoinCompetition(@Param("user_id") Integer user_id, @Param("platform_id") Integer platform_id, @Param("competition_id") Integer competition_id);

}
