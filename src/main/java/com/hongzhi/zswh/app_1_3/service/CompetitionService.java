package com.hongzhi.zswh.app_1_3.service;

import com.hongzhi.zswh.app_1_3.dao.CompetitionDao;
import com.hongzhi.zswh.app_1_3.entity.CompetitionNews;
import com.hongzhi.zswh.app_1_3.entity.CompetitionNewsImage;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        List<CompetitionNews> news_list =  competitionDao.competitionNews(competition_id,start_row,size)  ;
        List<Integer> news_id_list = new ArrayList<>();
        for (int i = 0; i < news_list.size() ; i++) {
            news_id_list.add(news_list.get(i).getNews_id());
        }
        if (!ObjectUtil.isEmpty(news_id_list) && news_id_list.size() > 0) {
            List<CompetitionNewsImage> image_list = competitionDao.competitionNewsImages(news_id_list) ;

            for (int i = 0; i < image_list.size() ; i++) {
                news_list.get( news_id_list.indexOf(image_list.get(i).getNews_id())  ).getImages().add(image_list.get(i));
            }
        }


        Map<String,Object>  map = new HashMap<>();
        map.put("news", news_list);
        map.put("news_detail_url", "/v5/news/detail.htmls?news_id=");
        return  map;
    }



}
