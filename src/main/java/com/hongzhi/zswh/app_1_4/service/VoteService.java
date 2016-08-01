package com.hongzhi.zswh.app_1_4.service;

import com.hongzhi.zswh.app_1_4.dao.VoteDao;
import com.hongzhi.zswh.app_1_4.entity.Item;
import com.hongzhi.zswh.app_1_4.entity.Vote;
import com.hongzhi.zswh.app_1_4.entity.VoteStatus;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.date.DateUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by taylor on 7/26/16.
 * twitter: @taylorwang789
 */
@Service("v1_4_voteService")
public class VoteService {

    @Autowired
    private VoteDao voteDao;
    @Autowired
    private DictionaryUtil dic;

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

    public Object vote(SessionProperty property, Integer vote_id, Integer item_id) throws HongZhiException {

        if ( "true".equals(dic.getValue("limit","vote",property.getLanguage()).toLowerCase()) ) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String firstDayOfWeek = simpleDateFormat.format(DateUtil.getFirstDayOfWeek(Calendar.MONDAY));
            String today = simpleDateFormat.format(new Date(System.currentTimeMillis()));

            Map<String,Object>  vote_data = voteDao.userVoteData(vote_id,Integer.valueOf(property.getUser_id()),today,firstDayOfWeek);

            if ( Integer.valueOf(vote_data.get("today_vote").toString()) >= Integer.valueOf(dic.getValue("day_limit","vote",property.getLanguage())) ) {
                throw new HongZhiException("today_up_to_limit","vote");
            }
            if ( Integer.valueOf(vote_data.get("week_vote").toString()) >= Integer.valueOf(dic.getValue("week_limit","vote",property.getLanguage())) ) {
                throw new HongZhiException("week_up_to_limit","vote");
            }

        }

        List<Integer> user_ids = voteDao.userIds(vote_id,Integer.valueOf(property.getUser_id()));

        if (user_ids.contains(Integer.valueOf(property.getUser_id()))) {
            int  count = voteDao.vote(Integer.valueOf(property.getUser_id()),vote_id,item_id);

            if ( 1 == count ) {
                voteDao.updateItemVotes(item_id);
                return "";
            } else {
                throw  new HongZhiException("vote_fail","vote");
            }
        } else {
            // can't vote
            throw  new HongZhiException("no_authority","vote");
        }

    }
}
