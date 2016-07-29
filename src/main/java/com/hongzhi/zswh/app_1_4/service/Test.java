package com.hongzhi.zswh.app_1_4.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by taylor on 7/28/16.
 * twitter: @taylorwang789
 */
public class Test {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
        String str = simpleDateFormat.format(new Date());
        System.out.println(str);

    }
}
