package com.hongzhi.zswh.app_controller;

import com.hongzhi.zswh.app_1_3.entity.MiPushRegid;
import com.hongzhi.zswh.app_1_3.service.MiPushService;
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
@Controller("app_mipush_controller")
@RequestMapping("/{version}/mi_push")
public class AppMiPushController {

    @Autowired
    private SessionUtil sess;
    @Autowired
    private DictionaryUtil dic;
    @Autowired
    private MiPushService miPushService;
    @ResponseBody
    @RequestMapping(value = "/regid")
    public String saveRegid(HttpSession session, String session_id, MiPushRegid miPushRegid, @PathVariable String version) {

        SessionProperty property;
        String language = "zh";
        try {
            switch (version){
                case "v1.3":
                    property = sess.sessionEffective(session,session_id,"/v1.3/mi_push/regid");
                    miPushRegid.setUser_id(property.getUser_id());
                    return ObjectUtil.jsonOut(miPushService.saveRegid(miPushRegid));
                default:
                    return null;
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }
    @ResponseBody
    @RequestMapping(value = "/cancel_regid")
    public String cancelRegid(HttpSession session, String session_id, MiPushRegid miPushRegid, @PathVariable String version){
        SessionProperty property;
        String language = "zh";
        try{
            switch (version){
                case "v1.3":
                    property = sess.sessionEffective(session,session_id,"/v1.3/mi_push/cance_regid");
                    return ObjectUtil.jsonOut(miPushService.cancelRegid(miPushRegid));
                default:
                    return null;
            }
        }catch (HongZhiException e){
            return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
        }
    }
}
