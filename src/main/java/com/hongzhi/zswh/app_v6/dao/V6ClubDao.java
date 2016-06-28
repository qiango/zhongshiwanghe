package com.hongzhi.zswh.app_v6.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by XieCaij on 2016/6/28.
 */
@Repository
public interface V6ClubDao {
    int saveClubPic(@Param("picUrl")String picUrl,@Param("club_id") String club_id);
}
