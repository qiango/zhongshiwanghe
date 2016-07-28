package com.hongzhi.zswh.app_1_4.service;

import com.hongzhi.zswh.app_1_4.dao.EventDao;
import com.hongzhi.zswh.app_1_4.entity.Event;
import com.hongzhi.zswh.app_1_4.entity.EventCreate;
import com.hongzhi.zswh.app_1_4.entity.EventStatus;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taylor on 7/27/16.
 * twitter: @taylorwang789
 */
@Service("v1_4_eventservice")
public class EventService {

    @Autowired
    private EventDao eventDao;



    public Object events(SessionProperty property, Integer club_id, Integer event_id) {

        List<Event> events = eventDao.events(club_id,event_id);

        if (!ObjectUtil.isEmpty(event_id)) {
            Map<String,Object> info = eventDao.statusInfo(Integer.valueOf(property.getUser_id()),event_id);
            events.get(0).setButton_show_code(Boolean.valueOf(info.get("is_registered").toString()),Integer.valueOf(info.get("registered_count").toString()),property.getLanguage());
        }

        Map<String,Object> map = new HashMap<>();
        map.put("events",events) ;
        return map;
    }

    public Object eventCreate(EventCreate event_create, SessionProperty property) throws HongZhiException {
        event_create.setOrganizer_id(Integer.valueOf(property.getUser_id()));
        event_create.verifyData();

        String return_info = "";

        if (property.getClub_user_level().equals("0")) {
            event_create.setEvent_status(EventStatus.NORMAL.getValue());
            return_info = EventStatus.NORMAL.name();
        } else {
            event_create.setEvent_status(EventStatus.UNDER_REVIEW.getValue());
            return_info = EventStatus.UNDER_REVIEW.name();
        }

        eventDao.createEvent(event_create);

        if (event_create.getOrganizer_join().toLowerCase().equals("true")) {
            eventDao.organizerJoin(event_create);
        }

        Map<String,String > map = new HashMap<>() ;
        map.put("info",return_info);
        return  map ;
    }


}
