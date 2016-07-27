package com.hongzhi.zswh.app_1_4.dao;

import com.hongzhi.zswh.app_1_4.entity.Item;
import com.hongzhi.zswh.app_1_4.entity.Vote;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by taylor on 7/26/16.
 * twitter: @taylorwang789
 */
@Repository("v1_4_voteDao")
public interface VoteDao {

    List<Vote> listVotes(@Param("voteID") Integer vote_id,@Param("statuses") List<Integer> statuses);

    List<Item> items(@Param("voteID") Integer vote_id,@Param("startRow") Integer start_row,@Param("pageSize") Integer page_size);

    List<Integer> userIds(@Param("voteID") Integer vote_id,@Param("userID") Integer user_id);

    int vote(@Param("userID") Integer user_id,@Param("voteID") Integer vote_id,@Param("itemID") Integer item_id);
}
