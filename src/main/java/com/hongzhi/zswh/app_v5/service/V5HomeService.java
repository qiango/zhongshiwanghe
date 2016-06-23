package com.hongzhi.zswh.app_v5.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 1, 2016    5:38:45 PM
 */
@Service
public class V5HomeService {
    
    @Autowired
    private V5AdService adService;
    @Autowired
    private V5NewsService newsService;

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
