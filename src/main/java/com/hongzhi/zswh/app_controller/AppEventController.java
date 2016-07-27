package com.hongzhi.zswh.app_controller;

import com.hongzhi.zswh.app_1_4.service.EventService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
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

    @ResponseBody
    @RequestMapping(value = "/list")
    public String list(HttpSession session, String session_id,Integer club_id, @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try{
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/event/list");
                    return ObjectUtil.jsonOut( eventService.events(club_id) );
                default:
                    return "hello";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }





}
