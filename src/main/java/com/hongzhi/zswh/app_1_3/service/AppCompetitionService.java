package com.hongzhi.zswh.app_1_3.service;

import com.hongzhi.zswh.app_1_3.dao.AppCompetitionDao;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xuejian
 * Date: 2016/7/21
 * Time: 14:51
 * To change this template use File | Settings | File Templates.
 */
@Service("app_1_3_AppCompetitionService")
public class AppCompetitionService {
    @Autowired
    private AppCompetitionDao appCompetitionDao;
    @Autowired
    private DictionaryUtil dictionaryUtil;

    public Object getJoinCompetition(SessionProperty property, String platform_id, String competition_id) {
        try {
            dictionaryUtil.verifyData(Integer.valueOf(platform_id)==2, "1024");
        } catch (HongZhiException e) {
            e.printStackTrace();
        }
        Map<String, Object> out=appCompetitionDao.getJoinCompetition(Integer.valueOf(property.getUser_id()),Integer.valueOf(platform_id),Integer.valueOf(competition_id));

        if (ObjectUtil.isEmpty(out)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("join_club_status", "0");
            map.put("user_competition_status", "0");
            map.put("club_status","3");
            return map;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("join_club_status", out.get("join_club_status"));
            map.put("user_competition_status",out.get("user_competition_status"));
            map.put("club_status",out.get("club_status"));
            return map;
        }
    }
}
