package com.hongzhi.zswh.app_1_4.service;

import com.hongzhi.zswh.app_1_4.dao.EventDao;
import com.hongzhi.zswh.app_1_4.entity.Event;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
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
    @Autowired
    private DictionaryUtil dictionaryUtil;


    public Object events(SessionProperty property, Integer club_id, Integer event_id) {

        List<Event> events = eventDao.events(club_id, event_id);

        if (!ObjectUtil.isEmpty(event_id)) {

            Map<String, Object> info = eventDao.statusInfo(Integer.valueOf(property.getUser_id()), event_id);
            events.get(0).setButton_show_code(Boolean.valueOf(info.get("is_registered").toString()), Integer.valueOf(info.get("registered_count").toString()), property.getLanguage());
            events.get(0).setButton_show_content( dictionaryUtil.getValue(events.get(0).getButton_show_code().toLowerCase(),"event_button",property.getLanguage()));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("events", events);
        return map;
    }

    public Object latestEvent() {

        List<Event> events = eventDao.events(null, null);


        return null;
    }
}
