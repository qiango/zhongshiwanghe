package com.hongzhi.zswh.app_controller;

import com.hongzhi.zswh.app_1_4.entity.EventCreate;
import com.hongzhi.zswh.app_1_4.entity.UserProfile;
import com.hongzhi.zswh.app_1_4.service.EventService;
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
    public String create(HttpSession session, String session_id,  EventCreate event_create, @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try{
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/create");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOut( eventService.eventCreate(event_create,property) );
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );
        }
    }

    @ResponseBody
    @RequestMapping(value = "/review")
    public String review(HttpSession session, String session_id, Integer event_id , @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try{
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/create");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOut( eventService.eventReview(event_id,property) );
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
    public String form(HttpSession session, String session_id, Integer event_id , @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try{
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/form");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOut( eventService.eventForm(event_id,property) );
                default:
                    return "404";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(),e.getMessage(), language ) );
        }
    }


    @ResponseBody
    @RequestMapping(value = "/register")
    public String register(HttpSession session, String session_id, Integer event_id, String new_info , UserProfile[] profiles, @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try{
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/register");
                    language=property.getLanguage();
                    return ObjectUtil.jsonOut( eventService.eventRegister(event_id,property,new_info,profiles) );
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
                    return ObjectUtil.jsonOut( eventService.eventUnregister(event_id,property) );
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



}
