package com.hongzhi.zswh.app_1_4.service;

import com.hongzhi.zswh.app_1_4.dao.VoteDao;
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


}
