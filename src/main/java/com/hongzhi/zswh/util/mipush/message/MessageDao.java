package com.hongzhi.zswh.util.mipush.message;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by taylor on 7/20/16.
 * twitter: @taylorwang789
 */
public interface MessageDao {

    List<AppRegid> getRegId(@Param("userIds") List<Integer> user_id);

}
