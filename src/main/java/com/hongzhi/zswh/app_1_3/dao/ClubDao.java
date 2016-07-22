package com.hongzhi.zswh.app_1_3.dao;


import com.hongzhi.zswh.app_1_3.entity.Club;
import com.hongzhi.zswh.app_1_3.entity.ClubQueryEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository("app_1_3_ClubDao")
public interface ClubDao {

    ClubQueryEntity clubInfo(@Param("userId") int userId, @Param("language") String language);

    List selectClubAdmin(@Param("club_id") String club_id, @Param("user_id") String user_id);

    Map<String, Object> userClub(int user_id);

    List<Map<String,Object>> clubRanking(@Param("limitSql") String limitSql, @Param("userClubId") int userClubId);

    String selectUserInfoByUserId(String user_id);

    int  saveSetClub(Club club);

    int  saveUserDetail(Club club);

    /**
     * 查询用户参加过的赛事
     * @param userId
     * @return
     */
    List<Map<String,Objects>> selectCompetitionByUserId(@Param("userId")Integer userId);

    Map<String,Object> queryClubLevel(String user_id);

    List<Integer> selectClubMembers(String club_id);

    List<String> checkClubName(String club_name);

    List<Map<String,Object>>  listClub();

    List<Map<String,Object>>  listClubByCityId(@Param("city_id")String city_id);

    List<Map<String, Object>>  selectClub();


    void updateClubStatus(String club_id);

    List<Integer> selectClubMembersByClubId(String club_id);
}
