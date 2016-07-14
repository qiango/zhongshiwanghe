package com.hongzhi.zswh.app_v6.dao;


import com.hongzhi.zswh.app_v6.entity.ClubQueryEntity;
import com.hongzhi.zswh.app_v6.entity.UserDetailEntity;
import com.hongzhi.zswh.app_v6.entity.V6Club;
import com.hongzhi.zswh.util.page.PageModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * Created by XieCaij on 2016/6/28.
 */
@Repository
public interface V6ClubDao {

    /**
     * 查询用户参加过的赛事
     * @param userId
     * @return
     */
    List<Map<String,Objects>> selectCompetitionByUserId(@Param("userId")Integer userId);

    /**
     *查找用户
     * @param userId
     * @return
     */
    UserDetailEntity selectUserDetailEntity(Integer userId);

    /**
     * 插入备份
     * @param useretail
     */
    void insetIntoUserDetail(UserDetailEntity useretail);
    /**
     * 删除
     * @param userId
     */
    void deleteUserDetailByUserId(@Param("userId") Integer userId);

    int saveClubPic(@Param("picUrl")String picUrl,@Param("club_id") String club_id);

    List selectClubAdmin(@Param("club_id") String club_id,@Param("user_id") String user_id);

    int transferClubByUserId(@Param("club_id")String club_id, @Param("id")String id,@Param("user_level") String user_level);

    int listClubByPageCount(PageModel pageModel);

    List<Map<String,Object>>  listClubByPage(PageModel pageModel);

    Map<String,Object> ownClubMemberCount(int owner);

    List<Map<String,Object>> ownClubMemberList(@Param("user_id") Integer user_id, @Param("club_id") Integer club_id);

    ClubQueryEntity clubInfo(@Param("userId") int userId, @Param("language") String language);

    Map<String, Object> userClub(int user_id);

    List<Map<String,Object>> clubRanking(@Param("limitSql") String limitSql, @Param("userClubId") int userClubId);

    Map<String,Object>  queryClubLevel(@Param("club_id") String club_id,@Param("user_id") String user_id);

    int  saveSetClub(V6Club club);

    String selectUserInfoByUserId(String user_id);

    int  saveUserDetail(V6Club club);

    List<Integer> selectClubMembers(String club_id);

    void updateClubStatus(@Param("club_status")String club_status,@Param("club_id") String club_id);
}
