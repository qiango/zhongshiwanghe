package com.hongzhi.zswh.app_1_4.entity;

import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;

import java.util.HashMap;

/**
 * Created by taylor on 8/3/16.
 * twitter: @taylorwang789
 */
public enum UserLevel {
     CLUB_MANAGER(0)
    ,CLUB_MEMBER(99)

    ,EVENT_ORGANIZER(1)
    ,NOT_JOIN_CLUB(3)
    ,EVENT_MEMBER(4)
    ;

    private int value;

    private static HashMap<Integer,UserLevel> map = new HashMap<>();

    static {
        for (UserLevel userLevel: UserLevel.values()) {
            map.put(userLevel.getValue(),userLevel);
        }
    }

    UserLevel (int v) {
        value = v;
    }

    public int getValue(){
        return  value;
    }

    public static UserLevel getUserLevel(int value) {
        return  map.get(value);
    }

    public static String findDictionary(String level,String language){
        if (ObjectUtil.isEmpty(language)) {
            language = "zh";
        }
        return DictionaryUtil.find(level,"event_enum",language);
    }

    public static String findDictionary(int level,String language){
        if (ObjectUtil.isEmpty(language)) {
            language = "zh";
        }
        return DictionaryUtil.find(getUserLevel(level).name(),"event_enum",language);
    }
}
