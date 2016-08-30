package com.hongzhi.zswh.app_1_5.service;

import com.google.gson.Gson;
import com.hongzhi.zswh.app_1_4.entity.EventCreate;
import com.hongzhi.zswh.app_1_4.entity.EventCreateRichText;
import com.hongzhi.zswh.app_1_4.entity.EventStatus;
import com.hongzhi.zswh.app_1_5.dao.EventDao;
import com.hongzhi.zswh.app_1_5.entity.EventGroup;
import com.hongzhi.zswh.app_v3.notification.service.NotificationService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.picture.service.Picture;
import com.hongzhi.zswh.util.picture.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by taylor on 7/27/16.
 * twitter: @taylorwang789
 */
@Service("v1_5_eventservice")
public class EventService {
    @Autowired
    private DictionaryUtil dictionaryUtil;
    @Autowired
    private PictureService pictureService;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private EventDao eventDao;

    public Object eventCreate(HttpServletRequest request, EventCreate event_create, SessionProperty property) throws HongZhiException {

        event_create.setOrganizer_id(Integer.valueOf(property.getUser_id()));
        event_create.setClub_id(property.getClub_id());
        event_create.verifyData();

        String return_info = "";

        if (property.getClub_user_level().equals("0")) {
            event_create.setEvent_status(EventStatus.NORMAL.getValue());
            return_info = EventStatus.NORMAL.name();
        } else {
            event_create.setEvent_status(EventStatus.UNDER_REVIEW.getValue());
            return_info = EventStatus.UNDER_REVIEW.name();
        }

        List<Picture> pictures = new ArrayList<>();
        // upload picture
        try {
            pictures = pictureService.picUploadMore(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        EventCreateRichText[] richText = gson.fromJson(event_create.getEvent_detail_rich_text(), EventCreateRichText[].class);

        int j = 0;
        for (int i = 0; i < richText.length; i++) {
            if (richText[i].getType().toLowerCase().equals("image") && pictures.size() >= j && !ObjectUtil.isEmpty(pictures.get(j))) {
                richText[i].setContent(dictionaryUtil.getValue("picHead", "data_alias", property.getLanguage()) + pictures.get(j).getNewName());
                richText[i].setHeight(pictures.get(j).getHeight());
                richText[i].setWidth(pictures.get(j).getWidth());
                j++;
            }
        }
        event_create.setEvent_detail(gson.toJson(richText).toString());


        eventDao.createEvent(event_create);

        List<String> items = Arrays.asList(event_create.getForm_item().split(","));

        eventDao.saveEventItems(event_create.getEvent_id(), items);

        eventDao.EventUserProfile(property.getUser_id(),event_create.getEvent_id(), items);


        if (event_create.getOrganizer_join().toLowerCase().equals("true")) {
            eventDao.organizerJoin(event_create);
        }

        //创建活动群号
        eventDao.saveEventGroup(event_create.getEvent_id(),event_create.getGroup_id(),property.getUser_id());

        if (!"0".equals(property.getClub_user_level())) {

            List<Integer> admin_list = eventDao.selectClubAmin(property.getClub_id());

            if (admin_list.size() != 0) {
                try {
                    notificationService.sendNoti(1, admin_list, null, "1", dictionaryUtil.getCodeValue("create_event", "event", "zh") + property.getUser_real_name() + dictionaryUtil.getCodeValue("create_event_message", "event", "zh") + event_create.getEvent_name());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Map<String, String> map = new HashMap<>();
        map.put("info", return_info);
        return map;
    }

    /**
     * 加入活动群
     * @param property
     * @param eventGroup
     * @return
     * @throws HongZhiException
     */
    public Object eventGroup(SessionProperty property,EventGroup eventGroup) throws HongZhiException {

        eventGroup.Vevent_id();
        eventGroup.Vgroup_id();

        eventGroup.setUser_id(property.getUser_id());

        eventDao.saveEventGroupUser(eventGroup);

        return null;
    }

    /**
     * 查询群成员
     * @param property
     * @param eventGroup
     * @return
     * @throws HongZhiException
     */
    public Object eventGroupMember(SessionProperty property,EventGroup eventGroup) throws HongZhiException {

        eventGroup.Vevent_id();
        eventGroup.Vgroup_id();

        List<Map<String,Object>>  event_group_member_list =  eventDao.eventGroupMember(eventGroup);

        Map<String, Object> map = new HashMap<>();

        if (event_group_member_list.size() > 0){
            map.put("event_group_member",event_group_member_list);

        }else {
            map.put("event_group_member",new ArrayList<>());

        }

        return map;

    }

    /**
     * 删除群成员
     * @param property
     * @param eventGroup
     * @return
     * @throws HongZhiException
     */
    public Object deleteGroupMember(SessionProperty property,EventGroup eventGroup) throws HongZhiException {

        eventGroup.Vevent_id();
        eventGroup.Vgroup_id();
        eventGroup.Vuser_id();

        eventDao.deleteGroupMember(eventGroup);

        return null;
    }
}
