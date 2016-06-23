package com.hongzhi.zswh.app_v5.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hongzhi.zswh.app_v5.entity.IQIYI;
import com.hongzhi.zswh.util.basic.ObjectUtil;

/**
 * Twitter : @taylorwang789 Creat time : Jun 6, 2016 4:57:44 PM
 */
@Service
public class V5iQiYiHttp {

    public String query(String urlString, Map<String, String> paramMap) {

        String outputString = "";
        
        if(!ObjectUtil.isEmpty(paramMap) && !paramMap.isEmpty()){
            urlString+="?";
            int i=0;
            for(Entry<String, String> entry :paramMap.entrySet() ){
                if(i++ != 0 ){
                    urlString+="&";
                }
                urlString+=entry.getKey()+"="+entry.getValue();
            }
        }
        
        System.out.println(urlString);

        try {

//            URL url = new URL( "https://openapi.iqiyi.com/api/iqiyi/authorize?client_id=8b830c2fe10b44bc9c4a400f032f8ee4&client_secret=b4178d68469d4fe550ffba6a37a78325");
            URL url = new URL( urlString );
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output = "";
            while ((output = br.readLine()) != null) {
                outputString += output;
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputString;
    }
    
    
    public JsonObject  getJsonObject(String urlString, Map<String, String> paramMap, boolean need_token ) {
        JsonParser parser = new JsonParser();
        if(need_token){
            refreshToken();
            System.out.println(IQIYI.ACCESS_TOKEN);
            paramMap.put("access_token", IQIYI.ACCESS_TOKEN);
        }
        JsonObject json_obj = parser.parse(query(urlString, paramMap).replace("\"{", "{").replace("}\"", "}").replace("\\", "") ).getAsJsonObject();
        return json_obj;
    }
    
    
    public JsonObject  getJsonObject(String urlString, Map<String, String> paramMap ) {
        JsonParser parser = new JsonParser();
        String str = query(urlString, paramMap).replace("\"{", "{").replace("}\"", "}").replace("\\", "") ;
        JsonObject json_obj = parser.parse(str).getAsJsonObject();
        return json_obj;
    }
    
    
    public  void refreshToken(){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("client_id", IQIYI.APP_KEY);
        paramMap.put("client_secret", IQIYI.APP_SECRET);
        JsonObject obj = getJsonObject(IQIYI.URL_ACCESS_TOKEN, paramMap);
        if(obj.get("code").getAsString().equals("A00000")){
            IQIYI.ACCESS_TOKEN=obj.get("data").getAsJsonObject().get("access_token").getAsString();
        }
    }
    
    
}
