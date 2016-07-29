package com.hongzhi.zswh.app_1_4.dao;

import com.hongzhi.zswh.app_1_4.entity.Event;
import com.hongzhi.zswh.app_1_4.entity.EventCreate;
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

    int createEvent(EventCreate event_create);

    int organizerJoin(EventCreate event_create);

    List<Integer> eventIDs(Integer club_id);

    int passReview(@Param("eventID") Integer event_id,@Param("status") Integer status);

    List<Event> latestEventList(@Param("club_id") Integer club_id);

    List<Event> myJoinEvent(@Param("user_id") String user_id, @Param("club_id") Integer club_id);

    List<Event> mySetEvent(@Param("user_id") String user_id, @Param("club_id") Integer club_id);

    List<Event> verifyEvent(@Param("club_id") Integer club_id);
}
