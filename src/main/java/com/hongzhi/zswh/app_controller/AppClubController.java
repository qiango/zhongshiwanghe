package com.hongzhi.zswh.app_controller;

import com.hongzhi.zswh.app_1_3.entity.Club;
import com.hongzhi.zswh.app_1_3.service.ClubService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Xuejian on 2016/6/28.
 */
@Controller("app_club_controller")
@RequestMapping("/{version}/club")
public class AppClubController {
    @Autowired
    private ClubService clubService;
    @Autowired
    private SessionUtil sess;
    @Autowired
    private DictionaryUtil dic;
    @ResponseBody
    @RequestMapping(value = "/load_club_manage")
    public String loadClubManage(HttpSession session, String session_id,@PathVariable String version) {

        SessionProperty property;
        try {
            switch (version) {
                case "v1.3":
                    property = sess.sessionEffective(session, session_id, "/v1.3/club/load_club_manage");
                    return ObjectUtil.jsonOut(clubService.loadClubManage(property));
                default:
                    return null;
            }
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOut(clubService.loadClubManageNotLogIn());
        }
    }

    @ResponseBody
    @RequestMapping("/set_club")
    public String setClub(HttpServletRequest request,HttpSession session,String session_id,Club club,@PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try {
            switch (version) {
                case "v1.3":
                    property = sess.sessionEffective(session,session_id,"/v1.3/club/set_club");
                    return ObjectUtil.jsonOut(clubService.setClub(request,property,club));
                default:
                    return null;
            }

        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }

    @ResponseBody
    @RequestMapping("/query_club_admin")
    public String queryClubAdmin(HttpSession session, String session_id,@PathVariable String version) {
        SessionProperty property;
        String language = "zh";
        try {
            switch (version) {
                case "v1.3":
                    property = sess.sessionEffective(session, session_id, "/v1.3/club/query_club_admin");
                    language = property.getLanguage();
                    return ObjectUtil.jsonOut(clubService.queryClubAdmin(property));
                default:
                    return null;
            }
        } catch (HongZhiException e) {
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language));
        }

    }
    @ResponseBody
    @RequestMapping("/load_club")
    public String loadClub(HttpSession session, String session_id,String city_id,@PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try {
            switch (version){
                case "v1.3":
                    property = sess.sessionEffective(session, session_id, "/v1.3/club/load_club");
                    return ObjectUtil.jsonOut(clubService.listClub(city_id));
                default:
                    return null;
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language));
        }
}
}
