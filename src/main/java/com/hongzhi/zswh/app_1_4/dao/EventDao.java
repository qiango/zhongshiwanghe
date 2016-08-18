package com.hongzhi.zswh.app_1_4.dao;

import com.hongzhi.zswh.app_1_4.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by taylor on 7/27/16.
 * twitter: @taylorwang789
 */
public interface EventDao {
    List<Event> events(@Param("club_id") Integer club_id, @Param("event_id") Integer event_id,@Param("eventStatus") Integer status);

    Map<String,Object> statusInfo(@Param("userID") Integer user_id,@Param("eventID") Integer event_id);

    int createEvent(EventCreate event_create);

    int organizerJoin(EventCreate event_create);

    List<Map<String, Object>> eventIDs(Integer club_id);

    int passReview(@Param("eventID") Integer event_id,@Param("status") Integer status);

//    List<Event> latestEventList();

    int saveEventItems(@Param("eventID") Integer event_id,@Param("items") List<String> items);

    List<Map<String,Object>> formItems(@Param("eventID") Integer event_id,@Param("userID") Integer user_id);

    int saveUserRegister(@Param("eventID") Integer event_id,@Param("userID") Integer user_id);

    int unregister(@Param("eventID") Integer event_id,@Param("userID") Integer user_id) ;

    List<Event> latestEventList(@Param("club_id") Integer club_id,@Param("userID") Integer user_id);

    List<Event> myJoinEvent(@Param("user_id") String user_id, @Param("club_id") Integer club_id);

    List<Event> mySetEvent(@Param("user_id") String user_id, @Param("club_id") Integer club_id);

    List<Event> verifyEvent(@Param("club_id") Integer club_id);

    int saveUserProfile(@Param("userID") Integer user_id ,@Param("profiles") List<UserProfile> inputProfiles);


    int selectMyEvent(@Param("user_id") String user_id);

    int saveReviewReason(EventEntity event);

    int clubEventsCount(@Param("clubID") Integer club_id );

    List<EventJoinMember> eventMembers(@Param("eventID") Integer event_id);
    int selectEventByClubId(Integer club_id);

    List<Map<String,Object>> selectEventFormItem();

    List<Map<String,Object>> selectDefaultImage();

    int abort(@Param("userID") Integer user_id,@Param("eventID") Integer event_id);

    Map<String, Object> selectOrganizerIdByEventId(Integer event_id);

    List<Integer> selectJoinEvent(String event_id);

    void updateEventRegistration(Integer event_id);

    List<Integer> selectClubAmin(Integer club_id);

    void saveEventUserProfile(@Param("userID") Integer user_id, @Param("eventID") Integer event_id,@Param("profiles") List<UserProfile> list);

    List<Map<String,Object>> otherItems(Integer event_id);

    List<Map<String,Object>> selectEventUserProfile(@Param("event_id") Integer event_id, @Param("user_id") String user_id);

    void updateEventUserProfile(@Param("event_id") Integer event_id, @Param("user_id") String user_id);

    List<Integer> queryClubMember(@Param("user_id") String user_id, @Param("club_id") Integer club_id);
}
