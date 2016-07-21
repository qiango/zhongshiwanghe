package com.hongzhi.zswh.app_controller;


import com.hongzhi.zswh.app_1_3.service.CompetitionService;

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

/**
 * Created with IntelliJ IDEA.
 * User: xuejian
 * Date: 2016/7/21
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */
@Controller("app_competition_controller")
@RequestMapping("/{version}/competition")
public class AppCompetitionController {
    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private SessionUtil sess;
    @Autowired
    private DictionaryUtil dic;

    @ResponseBody
    @RequestMapping("/is_join_competition")
    public  String isJoinCompetition(HttpSession session, String session_id,String platform_id,String competition_id, @PathVariable String version) {
        SessionProperty property;
        String language = "zh";
        try {
            switch (version) {
                case "v1.3":
                    property = sess.sessionEffective(session, session_id, "/v1.3/app_competition/is_join_competition");
                    return ObjectUtil.jsonOut(competitionService.getJoinCompetition(property, platform_id, competition_id));
                default:
                    return null;
            }
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language));
        }
    }
    @ResponseBody
    @RequestMapping("/news")
    public String loadClub(HttpSession session, String session_id,String competition_id,String page_number,String page_size,@PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try {
            switch (version){
                case "v1.3":
                    property = sess.sessionEffective(session, session_id, "/v1.3/competiton/news");
                    return ObjectUtil.jsonOut( competitionService.news(competition_id,page_number,page_size) );
                default:
                    return null;
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language));
        }
    }
}
