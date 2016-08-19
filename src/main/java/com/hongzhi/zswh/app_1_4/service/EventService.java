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
import java.text.SimpleDateFormat;
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

    @Autowired
    private NotificationService notificationService;

    public Object events(SessionProperty property, Integer club_id, Integer event_id) throws HongZhiException {
        List<Integer> club_member_list = eventDao.queryClubMember(property.getUser_id(), property.getClub_id());

        if (club_member_list.size() == 0) {

            throw new HongZhiException("1087");
        }

        Map<String, Object> map = new HashMap<>();

        if (property.getClub_id() != 0) {
// && "0".equals(property.getClub_user_level())
            List<Event> events = eventDao.events(property.getClub_id(), event_id, EventStatus.NORMAL.getValue());
//            List<Event> events_review = eventDao.events(property.getClub_id(), event_id, EventStatus.UNDER_REVIEW.getValue());

            String club_user_level = "";
            Integer organizer_level = 3;
            boolean abort_event = false;
            boolean user_join_event = false;
            // detail
            if (!ObjectUtil.isEmpty(event_id)) {
                Map<String, Object> info = eventDao.statusInfo(Integer.valueOf(property.getUser_id()), event_id);
                events.get(0).setButton_show_code(Boolean.valueOf(info.get("is_registered").toString()), Integer.valueOf(info.get("registered_count").toString()), property.getLanguage());
                events.get(0).setButton_show_content(dictionaryUtil.getValue(events.get(0).getButton_show_code().toLowerCase(), "event_button", property.getLanguage()));
                Gson gson = new Gson();
                if (!ObjectUtil.isEmpty(info.get("event_detail"))) {
                    events.get(0).setEvent_detail(gson.fromJson(info.get("event_detail").toString(), EventCreateRichText[].class));
                }
                EventJoinMember organizer = new EventJoinMember();
                organizer.setUser_id(events.get(0).getOrganizer_id());
                organizer.setName(info.get("organizer_name").toString());
                organizer.setProfile_image(info.get("profile_image").toString());
                organizer.setPhone(info.get("phone").toString());
                events.get(0).setOrganizer(organizer);
                events.get(0).setMembers(eventDao.eventMembers(event_id));
                organizer_level = Integer.valueOf(info.get("organizer_level").toString());

                club_user_level = userLevel(property, events.get(0).getOrganizer_id(), events.get(0).getEvent_id());

                if (events.get(0).getOrganizer_id().equals(Integer.valueOf(property.getUser_id())) && !events.get(0).getEvent_status().equals(EventStatus.OVER.getValue())) {
                    if (events.get(0).getStart_time().getTime() > System.currentTimeMillis()) {
                        abort_event = true;
                    } else if (events.get(0).getStart_time().getTime() <= System.currentTimeMillis()) {
                        abort_event = false;
                    } else if (events.get(0).getMembers().size() == 0) {
                        abort_event = true;
                    } else {
                        abort_event = false;
                    }
                }

                List<Integer> joinEventUserIDs = new ArrayList<>();
                for (int i = 0; i < events.get(0).getMembers().size(); i++) {
                    joinEventUserIDs.add(events.get(0).getMembers().get(i).getUser_id());
                }
                if (joinEventUserIDs.contains(Integer.valueOf(property.getUser_id()))) {

                    user_join_event = true;
                }

            } else {
                club_user_level = userLevel(property, 0, 0);
            }

            SimpleDateFormat format_month = new SimpleDateFormat("MM月dd日");
            SimpleDateFormat format_year = new SimpleDateFormat("yy年MM月dd日");
            SimpleDateFormat sdf = new SimpleDateFormat("yy");

            for (Event event : events) {
                Date date = new Date();

                String start_date = sdf.format(event.getStart_time());
                String end_date = sdf.format(event.getEnd_time());
                String now_date = sdf.format(date);

                if (!now_date.equals(start_date)) {
                    String event_start_date = format_year.format(event.getStart_time());
                    event.setEvent_start_time(event_start_date);

                } else {
                    String event_start_date = format_month.format(event.getStart_time());
                    event.setEvent_start_time(event_start_date);
                }
                if (!now_date.equals(end_date)) {

                    String event_end_date = format_year.format(event.getEnd_time());
                    event.setEvent_end_time(event_end_date);

                } else {
                    String event_end_date = format_month.format(event.getEnd_time());
                    event.setEvent_end_time(event_end_date);
                }

            }

            for (int i = 0; i < events.size(); i++) {
                if (events.get(i).getStart_time().getTime() > System.currentTimeMillis() && events.get(i).getEvent_status() == 1) {
                    events.get(i).setEvent_status_name(events.get(i).getEvent_start_time() + DictionaryUtil.find("event_start", "event", "zh"));
                } else if (events.get(i).getStart_time().getTime() <= System.currentTimeMillis() && events.get(i).getEvent_status() == 1) {
                    events.get(i).setEvent_status_name(DictionaryUtil.find("event_ongoing", "event", "zh"));
                } else if (3 == events.get(i).getEvent_status()) {
                    events.get(i).setEvent_status_name(DictionaryUtil.find("OVER", "event_enum", "zh"));
                } else if (events.get(i).getEnd_time().getTime() <= System.currentTimeMillis() && events.get(i).getEvent_status() == 1) {
                    events.get(i).setEvent_status_name(DictionaryUtil.find("event_end", "event", "zh"));
                }
            }

            map.put("club_user_level", club_user_level);
            map.put("organizer_level_name", UserLevel.findDictionary(organizer_level, property.getLanguage()));
            map.put("events", events);
            map.put("abort_event", abort_event);
            map.put("abort_event_name", DictionaryUtil.find("abort_event", "event", property.getLanguage()));
            map.put("user_join_event", user_join_event);

            if (property.getClub_user_level().equals("0")) {
                int counts = eventDao.selectEventByClubId(property.getClub_id());
                map.put("review_counts", counts);
            } else {
                map.put("review_counts", 0);
            }

        } else if (property.getClub_id() == 0) {

            map.put("club_user_level", UserLevel.NOT_JOIN_CLUB.name());
            map.put("organizer_level_name", "");
            map.put("review_counts", 0);
            map.put("events", new ArrayList<>());
            map.put("abort_event", false);
            map.put("abort_event_name", "");
            map.put("user_join_event", false);
        }

        return map;
    }

    private String userLevel(SessionProperty property, Integer organizer_id, Integer event_id) {
        String level = "";
        switch (property.getClub_user_level()) {
            case "0":
                level = UserLevel.CLUB_MANAGER.name();
                break;
            case "99":
                if (property.getUser_id().equals(organizer_id.toString())) {
                    level = UserLevel.EVENT_ORGANIZER.name();
                } else {
                    List<Event> myEvents = eventDao.myJoinEvent(property.getUser_id(), property.getClub_id());
                    List<Integer> myEventsIDs = new ArrayList<>();
                    for (int i = 0; i < myEvents.size(); i++) {
                        myEventsIDs.add(myEvents.get(i).getEvent_id());
                    }
                    if (myEventsIDs.contains(event_id)) {
                        level = UserLevel.EVENT_MEMBER.name();
                    } else {
                        level = UserLevel.CLUB_MEMBER.name();
                    }
                }
                break;
            default:
                level = UserLevel.NOT_JOIN_CLUB.name();
                break;
        }
        return level;
    }

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
        System.out.println(event_create.getEvent_detail_rich_text());

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

        if (event_create.getOrganizer_join().toLowerCase().equals("true")) {
            eventDao.organizerJoin(event_create);
        }
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
     * 活动审核
     *
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

                            notificationService.sendNoti(1, null, Integer.valueOf(event_ids.get(i).get("organizer_id").toString()), "1", dictionaryUtil.getCodeValue("review_event_fail", "event", "zh") + event_ids.get(i).get("event_name").toString() + dictionaryUtil.getCodeValue("event_fail_message", "event", "zh") + event.getReview_reason());
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

        List<Event> events_list = eventDao.latestEventList(property.getClub_id(), Integer.valueOf(property.getUser_id()));

        int club_events_counts = eventDao.clubEventsCount(property.getClub_id());

        Map<String, Object> map = new HashMap<>();

        map.put("event_counts", club_events_counts);


        SimpleDateFormat format_month = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat format_year = new SimpleDateFormat("yy年MM月dd日");
        SimpleDateFormat sdf = new SimpleDateFormat("yy");


        for (Event event : events_list) {

            Date date = new Date();

            String start_date = sdf.format(event.getStart_time());

            String now_date = sdf.format(date);

            if (!now_date.equals(start_date)) {
                String event_start_date = format_year.format(event.getStart_time());
                event.setEvent_start_time(event_start_date);

            } else {
                String event_start_date = format_month.format(event.getStart_time());
                event.setEvent_start_time(event_start_date);
            }
        }
        for (int i = 0; i < events_list.size(); i++) {
            if (events_list.get(i).getStart_time().getTime() > System.currentTimeMillis() && events_list.get(i).getEvent_status() == 1) {
                events_list.get(i).setEvent_status_name(events_list.get(i).getEvent_start_time() + DictionaryUtil.find("event_start", "event", "zh"));
            } else if (events_list.get(i).getStart_time().getTime() <= System.currentTimeMillis() && events_list.get(i).getEvent_status() == 1) {
                events_list.get(i).setEvent_status_name(DictionaryUtil.find("event_ongoing", "event", "zh"));
            } else if (3 == events_list.get(i).getEvent_status()) {
                events_list.get(i).setEvent_status_name(DictionaryUtil.find("OVER", "event_enum", "zh"));
            } else if (events_list.get(i).getEnd_time().getTime() <= System.currentTimeMillis() && events_list.get(i).getEvent_status() == 1) {
                events_list.get(i).setEvent_status_name(DictionaryUtil.find("event_end", "event", "zh"));
            }
        }

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

        SimpleDateFormat format_month = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat format_year = new SimpleDateFormat("yy年MM月dd日");
        SimpleDateFormat sdf = new SimpleDateFormat("yy");

        if (my_join_event_list.size() > 0) {

            for (Event event : my_join_event_list) {

                Date date = new Date();

                String start_date = sdf.format(event.getStart_time());

                String now_date = sdf.format(date);

                if (!now_date.equals(start_date)) {
                    String event_start_date = format_year.format(event.getStart_time());
                    event.setEvent_start_time(event_start_date);

                } else {
                    String event_start_date = format_month.format(event.getStart_time());
                    event.setEvent_start_time(event_start_date);
                }
            }

            for (int i = 0; i < my_join_event_list.size(); i++) {
                if (my_join_event_list.get(i).getStart_time().getTime() > System.currentTimeMillis() && my_join_event_list.get(i).getEvent_status() == 1) {

                    my_join_event_list.get(i).setEvent_status_name(my_join_event_list.get(i).getEvent_start_time() + DictionaryUtil.find("event_start", "event", "zh"));
                } else if (my_join_event_list.get(i).getStart_time().getTime() <= System.currentTimeMillis() && my_join_event_list.get(i).getEnd_time().getTime() >= System.currentTimeMillis()&& my_join_event_list.get(i).getEvent_status() == 1) {
                    my_join_event_list.get(i).setEvent_status_name(DictionaryUtil.find("event_ongoing", "event", "zh"));
                } else if (3 == my_join_event_list.get(i).getEvent_status()) {
                    my_join_event_list.get(i).setEvent_status_name(DictionaryUtil.find("OVER", "event_enum", "zh"));
                } else if (my_join_event_list.get(i).getEnd_time().getTime() < System.currentTimeMillis() && my_join_event_list.get(i).getEvent_status() == 1) {
                    my_join_event_list.get(i).setEvent_status_name(DictionaryUtil.find("event_end", "event", "zh"));
                }else if (0 == my_join_event_list.get(i).getEvent_status()){
                    my_join_event_list.get(i).setEvent_status_name(DictionaryUtil.find("UNDER_REVIEW", "event_enum", "zh"));

                }else if (2 == my_join_event_list.get(i).getEvent_status()){
                    my_join_event_list.get(i).setEvent_status_name(DictionaryUtil.find("fail_review", "event_button", "zh"));

                }
            }
            map.put("my_join_event_list", my_join_event_list);
        } else {

            map.put("my_join_event_list", new ArrayList<>());
        }
        if (my_set_event_list.size() > 0) {

            for (Event event : my_set_event_list) {

                Date date = new Date();

                String start_date = sdf.format(event.getStart_time());

                String now_date = sdf.format(date);

                if (!now_date.equals(start_date)) {
                    String event_start_date = format_year.format(event.getStart_time());
                    event.setEvent_start_time(event_start_date);

                } else {
                    String event_start_date = format_month.format(event.getStart_time());
                    event.setEvent_start_time(event_start_date);
                }
            }
            for (int i = 0; i < my_set_event_list.size(); i++) {
                if (my_set_event_list.get(i).getStart_time().getTime() > System.currentTimeMillis() && my_set_event_list.get(i).getEvent_status() == 1) {

                    my_set_event_list.get(i).setEvent_status_name(my_set_event_list.get(i).getEvent_start_time() + DictionaryUtil.find("event_start", "event", "zh"));
                } else if (my_set_event_list.get(i).getStart_time().getTime() <= System.currentTimeMillis()&& my_set_event_list.get(i).getEnd_time().getTime() >= System.currentTimeMillis() && my_set_event_list.get(i).getEvent_status() == 1) {
                    my_set_event_list.get(i).setEvent_status_name(DictionaryUtil.find("event_ongoing", "event", "zh"));
                } else if (my_set_event_list.get(i).getEvent_status() == 3) {
                    my_set_event_list.get(i).setEvent_status_name(DictionaryUtil.find("OVER", "event_enum", "zh"));
                } else if (my_set_event_list.get(i).getEnd_time().getTime() < System.currentTimeMillis() && my_set_event_list.get(i).getEvent_status() == 1) {
                    my_set_event_list.get(i).setEvent_status_name(DictionaryUtil.find("event_end", "event", "zh"));
                }else if (0 == my_set_event_list.get(i).getEvent_status()){
                    my_set_event_list.get(i).setEvent_status_name(DictionaryUtil.find("UNDER_REVIEW", "event_enum", "zh"));

                }else if (2 == my_set_event_list.get(i).getEvent_status()){
                    my_set_event_list.get(i).setEvent_status_name(DictionaryUtil.find("fail_review", "event_button", "zh"));
                }
            }

            map.put("my_set_event_list", my_set_event_list);
        } else {
            map.put("my_set_event_list", new ArrayList<>());
        }

        return map;
    }

    public Object eventForm(Integer event_id, String user_id, SessionProperty property) throws HongZhiException {
        if (ObjectUtil.isEmpty(user_id)) {
            Event eventInfo = eventDao.events(property.getClub_id(), event_id, EventStatus.NORMAL.getValue()).get(0);
            List<Map<String, Object>> formItems = eventDao.formItems(event_id, Integer.valueOf(property.getUser_id()));

//       map : a.event_id ,a.club_id ,b.item_code ,c.item_name, item_value

            if (!formItems.get(0).get("club_id").equals(property.getClub_id())) {
                throw new HongZhiException("not_own_club", "event");
            }
            List<Map<String, Object>> otherItems = eventDao.otherItems(event_id);

            Map<String, Object> map = new HashMap<>();

            if (otherItems.size() > 0) {
                for (int i = 0; i < formItems.size(); i++) {
                    for (int j = 0; j < otherItems.size(); j++) {
                        if (formItems.get(i).get("item_code").toString().equals(otherItems.get(j).get("item_code").toString())) {
                            formItems.get(i).put("item_value", "");
                            break;
                        }
                    }
                }
            }
            map.put("items", formItems);
            map.put("event_info", eventInfo);
            return map;
        } else {
            Event eventInfo = eventDao.events(property.getClub_id(), event_id, EventStatus.NORMAL.getValue()).get(0);
            List<Map<String, Object>> formItems = eventDao.formItems(event_id, Integer.valueOf(user_id));
            if (!formItems.get(0).get("club_id").equals(property.getClub_id())) {
                throw new HongZhiException("not_own_club", "event");
            }
      /*      Map<String, Object> event_user_profile = new HashMap<>();
            List<Map<String,Object>> list  = new ArrayList<>();*/

            List<Map<String, Object>> otherItems = eventDao.otherItems(event_id);
            List<Map<String, Object>> eventUserProfile = eventDao.selectEventUserProfile(event_id, user_id);
            if (otherItems.size() > 0) {
                for (int i = 0; i < formItems.size(); i++) {
                    for (int j = 0; j < eventUserProfile.size(); j++) {
                        if (formItems.get(i).get("item_code").toString().equals(eventUserProfile.get(j).get("item_code").toString())) {
                            formItems.get(i).put("item_value", eventUserProfile.get(j).get("item_value"));
                            break;
                        }
                    }
                }
            }

     /*       if (otherItems.size() > 0 && eventUserProfile.size() > 0){

                for (int i = 0; i < eventUserProfile.size(); i++){
                    for (int j = 0; j < otherItems.size(); j++){
                        if(eventUserProfile.get(i).get("item_code").toString().equals(otherItems.get(j).get("item_code"))){

                            event_user_profile.put(eventUserProfile.get(i).get("item_code").toString(),eventUserProfile.get(i).get("item_value"));
                            list.add(event_user_profile);
                            break;
                        }
                    }

                }
            }*/
            Map<String, Object> map = new HashMap<>();
           /* if (list.size() > 0){
                map.put("other_items",list);
            }else{
                map.put("other_items",new ArrayList<>());
            }*/

            map.put("items", formItems);
            map.put("event_info", eventInfo);
            return map;
        }

    }

    public Object eventRegister(Integer event_id, SessionProperty property, String profiles) throws HongZhiException {

        int effect_count = eventDao.saveUserRegister(event_id, Integer.valueOf(property.getUser_id()));

        // save new data
        if (!ObjectUtil.isEmpty(profiles)) {
            Gson gson = new Gson();
            UserProfile[] profileArray = gson.fromJson(profiles, UserProfile[].class);
            List<UserProfile> inputProfiles = Arrays.asList(profileArray);
            for (int i = 0; i < inputProfiles.size(); i++) {
                if (!ObjectUtil.isEmpty(inputProfiles.get(i).getItem_code())) {
                    List<UserProfile> list = new ArrayList<>();
                    list.add(inputProfiles.get(i));
                    eventDao.saveUserProfile(Integer.valueOf(property.getUser_id()), list);
                    //活动--用户
                    eventDao.saveEventUserProfile(Integer.valueOf(property.getUser_id()), event_id, list);

                }
            }
        }

        if (1 != effect_count) {
            throw new HongZhiException("register_fail", "event");
        } else {

            Map<String, Object> map = eventDao.selectOrganizerIdByEventId(event_id);

            if (!(map.get("organizer_id").toString()).equals(property.getUser_id())) {

                List<Integer> multiple_receiver = new ArrayList<>();
                multiple_receiver.add(Integer.valueOf(map.get("organizer_id").toString()));
                if (multiple_receiver.size() != 0) {
                    notificationService.sendNoti(1, multiple_receiver, null, "1", dictionaryUtil.getCodeValue("join_event", "event", "zh") + property.getUser_real_name() + dictionaryUtil.getCodeValue("join_event_message", "event", "zh") + map.get("event_name"));
                }

            }

            return null;
        }
    }

    public Object eventUnregister(Integer event_id, SessionProperty property) throws HongZhiException {

        int effect_count = eventDao.unregister(event_id, Integer.valueOf(property.getUser_id()));
        eventDao.updateEventUserProfile(event_id, property.getUser_id());
        if (0 != effect_count) {

            Map<String, Object> event_map = eventDao.selectOrganizerIdByEventId(event_id);

            if (!(event_map.get("organizer_id").toString()).equals(property.getUser_id())) {

                List<Integer> multiple_receiver = new ArrayList<>();
                multiple_receiver.add(Integer.valueOf(event_map.get("organizer_id").toString()));
                if (multiple_receiver.size() != 0) {
                    notificationService.sendNoti(1, multiple_receiver, null, "1", dictionaryUtil.getCodeValue("unregister_event", "event", "zh") + property.getUser_real_name() + dictionaryUtil.getCodeValue("unregister_event_m", "event", "zh") + event_map.get("event_name"));
                }
            }

            Map<String, String> map = new HashMap<>();
            map.put("status", "success");
            return map;
        } else {
            throw new HongZhiException("unregister_fail", "event");
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
        } else {
            map.put("verify_event_list", new ArrayList<>());
        }

        return map;
    }

    /**
     * 选择报名信息
     *
     * @return
     */
    public Object registerInformation() {

        List<Map<String, Object>> register_info_list = eventDao.selectEventFormItem();

        Map<String, Object> map = new HashMap<>();
        map.put("register_info_list", register_info_list);

        return map;
    }

    /**
     * 选择默认图片
     *
     * @return
     */
    public Object defaultImage() {

        List<Map<String, Object>> default_image_list = eventDao.selectDefaultImage();

        Map<String, Object> map = new HashMap<>();
        map.put("default_image", default_image_list);

        return map;
    }

    public Object abort(SessionProperty property, String event_id, String review_reason) throws HongZhiException {

        if (ObjectUtil.isEmpty(review_reason)) {
            throw new HongZhiException("1087");
        }
        int effectCount = eventDao.abort(Integer.valueOf(property.getUser_id()), Integer.valueOf(event_id));
        eventDao.updateEventRegistration(Integer.valueOf(event_id));
        if (1 == effectCount) {
            List<Integer> multiple_receiver = eventDao.selectJoinEvent(event_id);

            Map<String, Object> event_map = eventDao.selectOrganizerIdByEventId(Integer.valueOf(event_id));
            if (multiple_receiver.size() != 0) {
                notificationService.sendNoti(1, multiple_receiver, null, "1", dictionaryUtil.getCodeValue("abort_event", "event", "zh") + event_map.get("event_name") + dictionaryUtil.getCodeValue("abort_event_message", "event", "zh") + review_reason);
            }

            return null;
        } else {
            throw new HongZhiException("abort_fail", "event");
        }
    }


/*    public Object verifyEventDetails(String event_id, SessionProperty property) throws HongZhiException {
        ExcepUtil.verify(event_id,"event_name_null","event");

        return null;
    }*/
}
