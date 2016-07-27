package com.hongzhi.zswh.app_1_4.service;


import java.sql.Timestamp;

/**
 * Created by taylor on 7/27/16.
 * twitter: @taylorwang789
 */
public class Test {
    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Timestamp set = new Timestamp(System.currentTimeMillis()+1000);
        System.out.println(timestamp);
        System.out.println(set);

        if ( timestamp.getTime() < set.getTime() ) {
            System.out.println("ok");
        }
    }
}
