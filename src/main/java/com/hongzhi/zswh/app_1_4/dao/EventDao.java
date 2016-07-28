package com.hongzhi.zswh.app_1_4.dao;

import com.hongzhi.zswh.app_1_4.entity.Event;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by taylor on 7/27/16.
 * twitter: @taylorwang789
 */
public interface EventDao {
    List<Event> events(@Param("club_id") Integer club_id, @Param("event_id") Integer event_id);

    Map<String,Object> statusInfo(@Param("userID") Integer user_id,@Param("eventID") Integer event_id);
}
