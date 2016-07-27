package com.hongzhi.zswh.app_1_4.entity;

import java.util.HashMap;

/**
 * Created by taylor on 7/27/16.
 * twitter: @taylorwang789
 */
public enum EventStatus {

     UNDER_REVIEW(0)
    ,NORMAL(1)
    ,FAIL_REVIEW(2)
    ,OVER(3);

    private int value;

    private static HashMap<Integer,EventStatus> map = new HashMap<>();

    static {
        for (EventStatus eventStatus: EventStatus.values()) {
            map.put(eventStatus.getValue(),eventStatus);
        }
    }

    EventStatus(int v) {
        value = v;
    }

    public int getValue(){
        return  value;
    }

    public static EventStatus getEventStatus(int value) {
        return  map.get(value);
    }

}
