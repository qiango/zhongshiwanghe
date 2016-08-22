package com.hongzhi.zswh.easemob.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xuejian
 * Date: 2016/8/22
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */

@Repository("app_1_4_RestServiceDao")
public interface RestServiceDao {
    List<Map<String, String>> selectUserInfo();

    void saveRestUser(@Param("user_id") String user_id, @Param("user_login_name") String user_login_name,@Param("rest_user_name") String rest_user_name);
}
