package com.hongzhi.zswh.front;

import java.util.Calendar;
import java.util.regex.Pattern;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 11, 2016    7:34:18 PM
 */
public class TestGson {
	public static void main(String[] args) {

        Calendar calendar =  Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,5);
        calendar.set(Calendar.HOUR_OF_DAY,00);
        calendar.set(Calendar.MINUTE,30);

        System.out.println(calendar.getTime());
        System.out.println(calendar.getTimeInMillis());

        calendar.set(Calendar.HOUR_OF_DAY,18);
        calendar.set(Calendar.MINUTE,00);
         System.out.println(calendar.getTime());
        System.out.println(calendar.getTimeInMillis());



    }
}
