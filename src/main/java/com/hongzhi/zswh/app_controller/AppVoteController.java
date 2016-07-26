package com.hongzhi.zswh.app_controller;

import com.hongzhi.zswh.app_1_3.entity.MiPushRegid;
import com.hongzhi.zswh.app_1_3.service.MiPushService;
import com.hongzhi.zswh.app_1_4.service.VoteService;
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
 * Date: 2016/7/20
 * Time: 10:03
 * To change this template use File | Settings | File Templates.
 */
@Controller("app_vote_controller")
@RequestMapping("/{version}/vote")
public class AppVoteController {

    @Autowired
    private SessionUtil sess;
    @Autowired
    private DictionaryUtil dic;
    @Autowired
    private VoteService voteService;

    @ResponseBody
    @RequestMapping(value = "/list")
    public String cancelRegid(HttpSession session, String session_id,Integer vote_id, @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try{
            switch (version){
                case "v1.4":
                    property = sess.sessionEffective(session,session_id,"/v1.4/vote/list");
                    return ObjectUtil.jsonOutDT( voteService.votes(vote_id) , property.getLanguage());
                default:
                    return "hello";
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }



}
