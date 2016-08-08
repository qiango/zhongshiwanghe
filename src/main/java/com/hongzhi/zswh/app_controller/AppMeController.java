package com.hongzhi.zswh.app_controller;


import com.hongzhi.zswh.app_1_4.service.MeServce;
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

@Controller("/app_me_controller")
@RequestMapping("/{version}/me")
public class AppMeController {

    @Autowired
    private DictionaryUtil dic;
    @Autowired
    private SessionUtil sess;
    @Autowired
    private MeServce meServce;

    @ResponseBody
    @RequestMapping("/load_me")
    public String loadMe(HttpSession session, String session_id, @PathVariable String version) {
        SessionProperty properties;
        String language = "zh";
        try {
            switch (version) {
                case "v1.4":
                    properties = sess.sessionEffective(session, session_id, "/v1.4/load_me");
                    language = properties.getLanguage();
                    return ObjectUtil.jsonOut(meServce.loadMe(properties));
                default:
                    return "404";
            }
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language));
        }
    }

}

