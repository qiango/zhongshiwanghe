package com.hongzhi.zswh.app_v6.service;

import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 1, 2016    5:38:45 PM
 */
@Service
public class V6HomeService {
    
    @Autowired
    private V6AdService adService;
    @Autowired
    private V6NewsService newsService;

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 1, 2016    5:40:43 PM
     * @param properties
     * @return
     */
    public Object homePage(SessionProperty properties) {
        Map<String,Object> map = new HashMap<>();
        map.put("advertisement", adService.homeAd());
        map.put("home_news",newsService.newsList(properties,1,"latest") );
        map.put("home_video",newsService.newsList(properties,1,"video") );
        return map;
    }

   
}
