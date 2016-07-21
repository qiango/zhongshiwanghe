package com.hongzhi.zswh.app_1_3.service;

import com.hongzhi.zswh.app_1_3.dao.CompetitionDao;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by taylor on 7/21/16.
 * twitter: @taylorwang789
 */

@Service("app_1_3_competition_service")
public class CompetitionService {

    @Autowired
    private CompetitionDao competitionDao;
    @Autowired
    private DictionaryUtil dictionaryUtil;
    private int default_size = 20 ;

    public Object news(String competition_id,String page_number,String page_size) {
        int start_row = 0;
        int page = Integer.valueOf(ObjectUtil.coalesce(page_number,1).toString());
        page_size =  ObjectUtil.coalesce(page_size,default_size).toString() ;
        int size  = Integer.valueOf(page_size) ;
        start_row = ( page - 1 ) * size;

        Map<String,Object>  map = new HashMap<>();
        map.put("news", competitionDao.competitionNews(competition_id,start_row,size));
        return  map;
    }


    public Object getJoinCompetition(SessionProperty property, String platform_id, String competition_id) {
        try {
            dictionaryUtil.verifyData(Integer.valueOf(platform_id)==2, "1024");
        } catch (HongZhiException e) {
            e.printStackTrace();
        }
        Map<String, Object> out=competitionDao.getJoinCompetition(Integer.valueOf(property.getUser_id()),Integer.valueOf(platform_id),Integer.valueOf(competition_id));

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
