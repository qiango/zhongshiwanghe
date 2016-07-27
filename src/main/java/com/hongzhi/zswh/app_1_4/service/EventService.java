package com.hongzhi.zswh.app_1_4.service;

import com.hongzhi.zswh.app_1_4.dao.EventDao;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by taylor on 7/27/16.
 * twitter: @taylorwang789
 */
@Service("v1_4_eventservice")
public class EventService {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private DictionaryUtil dictionaryUtil;


    public Object events(Integer club_id) {

        return dictionaryUtil.getValue("aa","data_alias","zh");
    }
}
