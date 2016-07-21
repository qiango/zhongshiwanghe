package com.hongzhi.zswh.app_1_3.service;

import com.hongzhi.zswh.app_1_3.dao.CompetitionDao;
import com.hongzhi.zswh.util.basic.ObjectUtil;
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

    private int default_size = 20 ;

    public Object news(String competition_id,String page_number,String page_size) {
        int start_row = 0;
        int page = Integer.valueOf(ObjectUtil.coalesce(page_number,1).toString());
        page_size =  ObjectUtil.coalesce(page_size,default_size).toString() ;
        int size  = Integer.valueOf(page_size) ;
        start_row = ( page - 1 ) * size;

        Map<String,Object>  map = new HashMap<>();
        map.put("news", competitionDao.competitionNews(competition_id,start_row,size));
        map.put("news_detail_url", "/v5/news/detail.htmls?news_id=");
        return  map;
    }



}
