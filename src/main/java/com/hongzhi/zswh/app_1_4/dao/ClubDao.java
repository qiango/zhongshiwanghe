package com.hongzhi.zswh.app_1_4.dao;



import com.hongzhi.zswh.app_1_4.entity.ClubQueryEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("app_1_4_ClubDao")
public interface ClubDao {

    ClubQueryEntity clubInfo(@Param("userId") int userId, @Param("language") String language);

    List selectClubAdmin(@Param("club_id") String club_id, @Param("user_id") String user_id);

    Map<String, Object> userClub(int user_id);

    List<Map<String,Object>> clubRanking(@Param("limitSql") String limitSql, @Param("userClubId") int userClubId);

    List<Map<String,Object>>  listClub();

    List<Integer> queryEvent(String club_id);

    List<Integer> queryClub(String club_id);
}
