package com.hongzhi.zswh.app_1_4.service;

import com.google.gson.Gson;
import com.hongzhi.zswh.app_1_4.dao.EventDao;
import com.hongzhi.zswh.app_1_4.entity.*;
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
@Service("v1_4_eventservice")
public class EventService {

    @Autowired
    private EventDao eventDao;
    @Autowired
    private DictionaryUtil dictionaryUtil;
    @Autowired
    private PictureService pictureService;


    private NotificationService notificationService;

    public Object events(SessionProperty property, Integer club_id, Integer event_id) {

        Map<String, Object> map = new HashMap<>();

        if (property.getClub_id() != 0 && "0".equals(property.getClub_user_level())) {

            List<Event> events = eventDao.events(property.getClub_id(), event_id);

            int counts = eventDao.selectEventByClubId(property.getClub_id());

            if (!ObjectUtil.isEmpty(event_id)) {
                Map<String, Object> info = eventDao.statusInfo(Integer.valueOf(property.getUser_id()), event_id);
                events.get(0).setButton_show_code(Boolean.valueOf(info.get("is_registered").toString()), Integer.valueOf(info.get("registered_count").toString()), property.getLanguage());
                events.get(0).setButton_show_content(dictionaryUtil.getValue(events.get(0).getButton_show_code().toLowerCase(), "event_button", property.getLanguage()));
                Gson gson = new Gson();
                events.get(0).setEvent_detail(gson.fromJson(info.get("event_detail").toString(), EventCreateRichText[].class));
                EventJoinMember organizer = new EventJoinMember();
                organizer.setName(info.get("organizer_name").toString());
                organizer.setProfile_image(info.get("profile_image").toString());
                organizer.setPhone(info.get("phone").toString());
                events.get(0).setOrganizer(organizer);
                events.get(0).setMembers(eventDao.eventMembers(event_id));
                map.put("club_user_level", property.getClub_user_level());
                map.put("counts", counts);
                map.put("events", events);
            }
        }else if (property.getClub_id() == 0) {

            map.put("club_user_level", property.getClub_user_level());
            map.put("counts", 0);
            map.put("events", new ArrayList<>());
        }

        return map;
    }

    public Object eventCreate(HttpServletRequest request, EventCreate event_create, SessionProperty property) throws HongZhiException {
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

        List<Picture> pictures = new ArrayList<>();
        // upload picture
        try {
            pictures = pictureService.picUploadMore(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson  gson = new Gson();
        System.out.println(event_create.getEvent_detail_rich_text());

        EventCreateRichText[] richText = gson.fromJson(event_create.getEvent_detail_rich_text(),EventCreateRichText[].class);

        int j =0;
        for (int i = 0; i < richText.length ;i++) {
            if (richText[i].getType().toLowerCase().equals("image") && pictures.size() >= j && !ObjectUtil.isEmpty(pictures.get(j))) {
                richText[i].setContent(dictionaryUtil.getValue("picHead","data_alias",property.getLanguage())+pictures.get(j).getNewName());
                j++;
            }
        }
        event_create.setEvent_detail(gson.toJson(richText).toString());


        eventDao.createEvent(event_create);

        List<String> items = Arrays.asList(event_create.getForm_item().split(","));

        eventDao.saveEventItems(event_create.getEvent_id(), items);

        if (event_create.getOrganizer_join().toLowerCase().equals("true")) {
            eventDao.organizerJoin(event_create);
        }

        Map<String, String> map = new HashMap<>();
        map.put("info", return_info);
        return map;
    }

    /**
     * 活动审核
     * @param event
     * @param property
     * @return
     * @throws HongZhiException
     */
    public Object eventReview(EventEntity event, SessionProperty property) throws HongZhiException {

        List<Map<String, Object>> event_ids = eventDao.eventIDs(property.getClub_id());

        if (event_ids.size() > 0) {
            for (int i = 0; i < event_ids.size(); i++) {
                if (event_ids.get(i).get("event_id").toString().equals(event.getEvent_id())) {

                    if ("1".equals(event.getReview_type())) { //审核通过

                        int cnt = eventDao.passReview(Integer.valueOf(event.getEvent_id()), EventStatus.NORMAL.getValue());

                        if (1 == cnt) {

                            notificationService.sendNoti(1, null, Integer.valueOf(event_ids.get(i).get("organizer_id").toString()), "1", dictionaryUtil.getCodeValue("review_event_true", "event", "zh") + event_ids.get(i).get("event_name").toString() + dictionaryUtil.getCodeValue("event_true_message", "event", "zh"));
                            break;
                        } else {
                            throw new HongZhiException("review_fail", "event");
                        }

                    } else if ("2".equals(event.getReview_type())) {//审核未通过
                        event.setReason_type("REVIEW_FAIL");
                        int cnt_event = eventDao.passReview(Integer.valueOf(event.getEvent_id()), EventStatus.FAIL_REVIEW.getValue());

                        int cnt_review_reason = eventDao.saveReviewReason(event);

                        if (1 == cnt_event && 1 == cnt_review_reason) {

                            notificationService.sendNoti(1, null, Integer.valueOf(event_ids.get(i).get("organizer_id").toString()), "1", dictionaryUtil.getCodeValue("review_event_fail", "event", "zh") + event_ids.get(i).get("event_name").toString() + dictionaryUtil.getCodeValue("event_fail_message", "event", "zh")+event.getReview_reason());
                            break;
                        } else {
                            throw new HongZhiException("review_fail", "event");
                        }
                    }
                }
            }
        } else {
            throw new HongZhiException("no_authority_review", "event");
        }

        Map<String, String> map = new HashMap<>();

        map.put("status", "success");
        return map;

/*       List<Integer> event_ids = eventDao.eventIDs(property.getClub_id());

        if (event_ids.contains(event_id)) {

            int cnt = eventDao.passReview(event_id, EventStatus.NORMAL.getValue());

            Map<String, String> map = new HashMap<>();
            if (1 == cnt) {
                map.put("status", "success");
                return map;
            } else {
                throw new HongZhiException("review_fail", "event");
            }
        } else {
            throw new HongZhiException("no_authority_review", "event");
        }*/
    }

    /**
     * 最新活动
     *
     * @param property
     * @return
     */
    public Object latestEvent(SessionProperty property) {

        List<Event> events_list = eventDao.latestEventList(property.getClub_id());

        int club_events_counts = eventDao.clubEventsCount(property.getClub_id());

        Map<String, Object> map = new HashMap<>();

        map.put("event_counts",club_events_counts);

        if (events_list.size() > 0) {
            map.put("events_list", events_list);
        } else {
            map.put("events_list", new ArrayList<>());
        }

        return map;
    }

    /**
     * 我的活动+我发起的活动
     *
     * @param property
     * @return
     */
    public Object myActivities(SessionProperty property) {

        List<Event> my_join_event_list = eventDao.myJoinEvent(property.getUser_id(), property.getClub_id());

        List<Event> my_set_event_list = eventDao.mySetEvent(property.getUser_id(), property.getClub_id());

        Map<String, Object> map = new HashMap<>();

        if (my_join_event_list.size() > 0) {
            map.put("my_join_event_list", my_join_event_list);
        } else {
            map.put("my_join_event_list", new ArrayList<>());
        }
        if (my_set_event_list.size() > 0) {
            map.put("my_set_event_list", my_set_event_list);
        } else {
            map.put("my_set_event_list", new ArrayList<>());
        }

        return map;
    }

    public Object eventForm(Integer event_id, SessionProperty property) throws HongZhiException {

        List<Map<String, Object>> formItems = eventDao.formItems(event_id, Integer.valueOf(property.getUser_id()));
//       map : a.event_id ,a.club_id ,b.item_code ,c.item_name, item_value

        if (!formItems.get(0).get("club_id").equals(property.getClub_id())) {
            throw new HongZhiException("not_own_club", "event");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("items", formItems);
        return map;
    }

    public Object eventRegister(Integer event_id, SessionProperty property, String new_info, UserProfile[] profiles) throws HongZhiException {

        int effect_count = eventDao.saveUserRegister(event_id, Integer.valueOf(property.getUser_id()));

        // save new data
        if (Boolean.valueOf(new_info)) {
            List<UserProfile>  inputProfiles = Arrays.asList(profiles);
            eventDao.saveUserProfile(Integer.valueOf(property.getUser_id()),inputProfiles);
        }

        if (1 != effect_count) {
            throw new HongZhiException("register_fail", "event");
        } else {
            return null;
        }
    }

    public Object eventUnregister(Integer event_id, SessionProperty property) throws HongZhiException {

        int effect_count = eventDao.unregister(event_id, Integer.valueOf(property.getUser_id()));

        if ( 1 == effect_count ) {
            Map<String,String> map = new HashMap<>();
            map.put("status","success");
            return  map;
        } else {
            throw  new HongZhiException("unregister_fail","event");
        }
    }

    /**
     * 活动审核列表
     *
     * @param property
     * @param property
     * @return
     */
    public Object verifyEvent(SessionProperty property) {

        List<Event> verify_event_list = eventDao.verifyEvent(property.getClub_id());

        Map<String, Object> map = new HashMap<>();

        if (verify_event_list.size() > 0) {
            for (Event event : verify_event_list) {
                event.setEvent_status_name(dictionaryUtil.getValue(event.getEvent_status_code().toLowerCase(), "event_button", property.getLanguage()));
            }
            map.put("verify_event_list", verify_event_list);
        }else{
            map.put("verify_event_list", new ArrayList<>());
        }

        return map;
    }

    public Object registerInformation() {

        List<Map<String,Object>> register_info_list  = eventDao.selectEventFormItem();

        Map<String, Object> map = new HashMap<>();
        map.put("register_info_list", register_info_list);

        return map;
    }


/*    public Object verifyEventDetails(String event_id, SessionProperty property) throws HongZhiException {
        ExcepUtil.verify(event_id,"event_name_null","event");

        return null;
    }*/
}
