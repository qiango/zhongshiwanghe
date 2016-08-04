package com.hongzhi.zswh.app_1_4.dao;

import com.hongzhi.zswh.app_1_4.entity.OutputEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("app_1_4_MeDao")
public interface MeDao {

	OutputEntity loadMe(@Param("user_id") int user_id, @Param("platform_id") int platform_id, @Param("language") String language);

	int selectMyJoinEvent(@Param("user_id") String user_id);
}
