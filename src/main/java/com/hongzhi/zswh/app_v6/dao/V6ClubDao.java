package com.hongzhi.zswh.app_v6.dao;


import com.hongzhi.zswh.app_v6.entity.UserDetailEntity;


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
}
