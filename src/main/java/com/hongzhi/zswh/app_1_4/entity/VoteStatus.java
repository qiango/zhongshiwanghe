package com.hongzhi.zswh.app_1_4.entity;

import java.util.HashMap;

/**
 * Created by taylor on 7/26/16.
 * twitter: @taylorwang789
 */
public enum VoteStatus {
//    0:未开始,1:进行中,2:已结束

     NOT_START(0)
    ,PROCESSING(1)
    ,OVER(2);

    private int value;

    private static HashMap<Integer,VoteStatus> voteStatusMap = new HashMap<>();

    static {
        for (VoteStatus voteStatus: VoteStatus.values()) {
            voteStatusMap.put(voteStatus.getValue(),voteStatus);
        }
    }

    VoteStatus(int v) {
        value = v;
    }

    public int getValue(){
        return  value;
    }

    public static VoteStatus getVoteStatus(int value) {
        return  voteStatusMap.get(value);
    }

}

