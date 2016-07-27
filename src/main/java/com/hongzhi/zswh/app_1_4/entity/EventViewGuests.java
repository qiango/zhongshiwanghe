package com.hongzhi.zswh.app_1_4.entity;

import java.util.HashMap;

/**
 * Created by taylor on 7/27/16.
 * twitter: @taylorwang789
 */
public enum EventViewGuests {

     PUBLIC(0)
    ,PART(1)
    ,PRIVATE(2)
    ;

    private int value;

    private static HashMap<Integer,EventViewGuests> map = new HashMap<>();

    static {
        for (EventViewGuests eventViewGuests : EventViewGuests.values()) {
            map.put(eventViewGuests.getValue(),eventViewGuests);
        }
    }

    EventViewGuests(int v) {
        value = v;
    }

    public int getValue(){
        return  value;
    }

    public static EventViewGuests getEventViewGuests(int value) {
        return  map.get(value);
    }
}
