package com.hongzhi.zswh.app_1_4.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * Created by taylor on 8/2/16.
 * twitter: @taylorwang789
 */
public class Test {
    public static void main(String[] args) {
        String str ="[{\"type\":\"text\",\"content\":\"content\",\"index\":\"1\"},{\"type\":\"image\",\"content\":\"/pic.htmls?p=/2016/0802/1470122922077_zecbAD.jpg\",\"index\":\"2\"}]";
        JsonParser parser = new JsonParser();
        JsonArray array = (JsonArray) parser.parse(str);
        System.out.println(array);
    }
}
