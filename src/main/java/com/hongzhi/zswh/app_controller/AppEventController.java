package com.hongzhi.zswh.app_controller;

import com.hongzhi.zswh.app_1_4.entity.EventCreate;
import com.hongzhi.zswh.app_1_4.entity.EventEntity;
import com.hongzhi.zswh.app_1_4.service.EventService;
import com.hongzhi.zswh.app_1_5.entity.EventGroup;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.date.DateFormat;
import com.hongzhi.zswh.util.exception.HongZhiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller("app_event_controller")
@RequestMapping("/{version}/event")
public class AppEventController {

    @Autowired
    private SessionUtil sess;
    @Autowired
    private DictionaryUtil dic;
    @Autowired
    private EventService eventService;
    @Autowired
    private com.hongzhi.zswh.app_1_5.service.EventService v1_5_eventService;

    // event list
    @ResponseBody
    @RequestMapping(value = "/list")
    public String list(HttpSession session, String session_id,Integer club_id,Integer event_id, @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try{
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/list");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOutDT( eventService.events(property,club_id,event_id) , "MM月dd日 HH:mm");
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );
        }
    }
    @ResponseBody
    @RequestMapping(value = "latest_event")
    public String latestEvent(HttpSession session, String session_id, @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try {
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/latest_event");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOutDT( eventService.latestEvent(property),DateFormat.getFormatWithTime(language));
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return  ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }

    // create event
    @ResponseBody
    @RequestMapping(value = "/create")
    public String create(HttpServletRequest request, HttpSession session, String session_id, EventCreate event_create, @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try{
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/create");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOut( eventService.eventCreate(request,event_create,property) );
                case "v1.5":
                    property = sess.sessionEffective(session,session_id,"/v1.5/event/create");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOut( v1_5_eventService.eventCreate(request,event_create,property) );
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );
        }
    }

    @ResponseBody
    @RequestMapping(value = "/review")
    public String review(HttpSession session, String session_id, EventEntity event , @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try{
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/create");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOut( eventService.eventReview(event,property),DictionaryUtil.find("event_review_success","event",language) );
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );
        }
    }

    @ResponseBody
    @RequestMapping(value = "/my_event")
    public String myActivities(HttpSession session, String session_id, @PathVariable String version) {
        SessionProperty property;
        String language = "zh";
        try{
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/my_event");
                    language = property.getLanguage();
                    return ObjectUtil.jsonOutDT( eventService.myActivities(property),DateFormat.getFormatWithTime(language) );
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );
        }
    }
    @ResponseBody
    @RequestMapping(value = "/verify_event")
    public String verifyEvent(HttpSession session,String session_id,@PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try {
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/verify_event");
                    language = property.getLanguage();
                    return ObjectUtil.jsonOutDT( eventService.verifyEvent(property),DateFormat.getFormatWithTime(language) );
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );

        }
    }

    // event join

    @ResponseBody
    @RequestMapping(value = "/form")
    public String form(HttpSession session, String session_id, Integer event_id ,String user_id ,@PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try{
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/form");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOutDT( eventService.eventForm(event_id,user_id,property) , "MM月dd日 HH:mm");
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );
        }
    }


    @ResponseBody
    @RequestMapping(value = "/register")
    public String register(HttpSession session, String session_id, Integer event_id,  String profiles, @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try{
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/register");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOut( eventService.eventRegister(event_id,property,profiles) );
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );
        }
    }

    @ResponseBody
    @RequestMapping(value = "/unregister")
    public String unregister(HttpSession session, String session_id, Integer event_id , @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try{
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/unregister");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOut( eventService.eventUnregister(event_id,property) ,DictionaryUtil.find("unregister_success","event",language));
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );
        }
    }
/*    @ResponseBody
    @RequestMapping(value = "/verify_event_details")
    public String verifyEventDetails(HttpSession session, String session_id, String event_id , @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try {
            switch (version){
                case "v1,4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/unregister");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOut( eventService.verifyEventDetails(event_id,property) );
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );
        }
    }*/

    @ResponseBody
    @RequestMapping(value = "/register_information")
    public String registerInformation(HttpSession session, String session_id, @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try {
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/register_information");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOut( eventService.registerInformation() );
                default:
                    return "404";
            }

        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );
        }
    }
    @ResponseBody
    @RequestMapping(value = "/default_image")
    public String defaultImage(HttpSession session, String session_id, @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try {
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/default_image");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOut( eventService.defaultImage() );
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );
        }
    }

    @ResponseBody
    @RequestMapping(value = "/abort")
    public String abort(HttpSession session, String session_id,String event_id,String review_reason, @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try {
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/abort");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOut( eventService.abort(property,event_id,review_reason) ,DictionaryUtil.find("abort_success","event",language) );
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );
        }
    }

    @ResponseBody
    @RequestMapping(value = "/event_group")
    public String eventGroup(HttpSession session, String session_id,EventGroup eventGroup, @PathVariable String version) {
        SessionProperty property;
        String language = "zh";
        try {
            switch (version){
                case "v1.5":
                    property = sess.sessionEffective(session,session_id,"/v1.5/event/event_group");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOut( v1_5_eventService.eventGroup(property,eventGroup) );
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );

        }
    }


    @ResponseBody
    @RequestMapping(value = "/event_group_member")
    public String eventGroupMember(HttpSession session, String session_id,EventGroup eventGroup, @PathVariable String version) {
        SessionProperty property;
        String language = "zh";
        try {
            switch (version){
                case "v1.5":
                    property = sess.sessionEffective(session,session_id,"/v1.5/event/event_group_member");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOut( v1_5_eventService.eventGroupMember(property,eventGroup) );
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );

        }

    }

    @ResponseBody
    @RequestMapping(value = "/delete_group_member")
    public String deleteGroupMember(HttpSession session, String session_id, EventGroup eventGroup, @PathVariable String version) {
        SessionProperty property;
        String language = "zh";
        try {
            switch (version) {
                case "v1.5":
                    property = sess.sessionEffective(session, session_id, "/v1.5/event/delete_group_member");
                    language = property.getLanguage();
                    return ObjectUtil.jsonOut(v1_5_eventService.deleteGroupMember(property,eventGroup));
                default:
                    return "404";
            }
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), e.getMessage(), language));

        }
    }
    }
