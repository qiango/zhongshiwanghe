package com.hongzhi.zswh.app_1_4.service;

import com.hongzhi.zswh.app_1_4.dao.VoteDao;
import com.hongzhi.zswh.app_1_4.entity.Item;
import com.hongzhi.zswh.app_1_4.entity.Vote;
import com.hongzhi.zswh.app_1_4.entity.VoteStatus;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taylor on 7/26/16.
 * twitter: @taylorwang789
 */
@Service("v1_4_voteService")
public class VoteService {

    @Autowired
    private VoteDao voteDao;

    public Object votes(Integer vote_id ) {
        List<Integer>  statuses = new ArrayList<>();
        statuses.add(VoteStatus.PROCESSING.getValue());

        List<Vote> votes = voteDao.listVotes(vote_id,statuses);

        Map<String,Object> map = new HashMap<>();
        map.put("vote_list",votes);
        return  map;
    }


    public Object items(Integer vote_id, Integer page_number, Integer page_size) {

        Integer start_row = (page_number - 1 ) * page_size ;

        List<Item> items = voteDao.items(vote_id,start_row,page_size);

        Map<String,Object> map = new HashMap<>();
        map.put("items",items);
        return map;
    }

    public Object vote(SessionProperty property, Integer vote_id, Integer item_id) {

        List<Integer> user_ids = voteDao.userIds(vote_id,Integer.valueOf(property.getUser_id()));

        if (user_ids.contains(Integer.valueOf(property.getUser_id()))) {
            int  count = voteDao.vote(Integer.valueOf(property.getUser_id()),vote_id,item_id);

        } else {
            // can't vote
        }

        return null;
    }
}
