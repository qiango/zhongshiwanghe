package com.hongzhi.zswh.back.news.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.back.news.dao.NewsDao;
import com.hongzhi.zswh.util.basic.ObjectUtil;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 17, 2016    3:53:39 PM
 */
@Service
public class VideoService {
    
    @Autowired
    private NewsDao newsDao;
    

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 17, 2016    4:00:01 PM
     * @return
     */
    public Object toUpload() {

        List<Map<String,Object>> news_category_list = newsDao.newsCategoryList();
        Map<String,Object> root = new HashMap<>();
        Map<String, Object> out = new HashMap<>();
//      out.put("language_list", language_list);
        root.put("category_id", 3);
        out.put("category_video", ObjectUtil.getTree(root, news_category_list, "category_id", "parent_category_id", "children").get("children"));
        return out;
    }
    
    
}
