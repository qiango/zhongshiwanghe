package com.hongzhi.zswh.app_1_5.dao;

import com.hongzhi.zswh.app_1_4.entity.EventCreate;
import com.hongzhi.zswh.app_1_5.entity.EventGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by taylor on 7/27/16.
 * twitter: @taylorwang789
 */
@Repository("v1_5_EventDao")
public interface EventDao {

    int createEvent(EventCreate event_create);

    int saveEventItems(@Param("eventID") Integer event_id, @Param("items") List<String> items);

    int organizerJoin(EventCreate event_create);

    List<Integer> selectClubAmin(Integer club_id);

    void saveEventGroup(@Param("event_id") Integer event_id, @Param("group_id") String group_id, @Param("user_id") String user_id);

    void saveEventGroupUser(EventGroup eventGroup);

    List<Map<String,Object>> eventGroupMember(EventGroup eventGroup);

    void deleteGroupMember(EventGroup eventGroup);

    void EventUserProfile(@Param("user_id") String user_id, @Param("event_id") Integer event_id, @Param("items") List<String> items);

}
