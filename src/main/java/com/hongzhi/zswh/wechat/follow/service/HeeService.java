package com.hongzhi.zswh.wechat.follow.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hongzhi.zswh.util.basic.ObjectUtil;


import com.hongzhi.zswh.wechat.follow.entity.WECHAT;
@Service
public class HeeService {
	
	public String getInfoById(String openId) throws Exception{
		
		if(!ObjectUtil.isEmpty(openId)){
	    	String urlStr ="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+WECHAT.ACCESS_TOKEN+"&openid="+openId+"&lang=zh_CN";  	

			URL url = new URL(urlStr);
			HttpURLConnection httpcon =(HttpURLConnection) url.openConnection();
			httpcon.setRequestMethod("GET");
			httpcon.setDoInput(true);
			httpcon.setRequestProperty("Content-Type", "text/xml");
			
			if(200==httpcon.getResponseCode()){
				BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream(), "utf-8"));
				StringBuilder sb = new StringBuilder();
				String readLine = br.readLine();
				while(!ObjectUtil.isEmpty(readLine)){
					sb.append(readLine);
					readLine=br.readLine();
				}
				br.close();
				return sb.toString();
			}			
		}
		return "parameter error";		
	}
	
	
	
	public String getBatchInfoByIds(String request_mesage) throws Exception{
		
    	String url_str = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token="+WECHAT.ACCESS_TOKEN;
		
    	URL url = new URL(url_str);
		HttpURLConnection httpcon =(HttpURLConnection) url.openConnection();
		httpcon.setRequestMethod("POST");
		httpcon.setDoOutput(true);
		httpcon.setDoInput(true);
		httpcon.setReadTimeout(3000);
		httpcon.setRequestProperty("Content-Type", "text/xml");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(httpcon.getOutputStream(),"utf-8"));
		bw.write(request_mesage, 0, request_mesage.length());
		bw.flush();
		bw.close();
		
		if(200==httpcon.getResponseCode()){
			BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream(),"utf-8"));
			String readLine = br.readLine();
			StringBuilder sb = new StringBuilder();
			while(!ObjectUtil.isEmpty(readLine)){
				sb.append(readLine);
				readLine=br.readLine();
			}
			return sb.toString();	
		}
		return "parameter error";
	}
	
	
	
	public String getSendMsg(String[] openIDs){
		if(!ObjectUtil.isEmpty(openIDs)){
			Map<String,List<Map<String,String>>> map_send = new HashMap<>();
			   
			List<Map<String,String>> list = new ArrayList<>();
	    	
	    	for (String opendid : openIDs) {
	    		Map<String,String> map = new HashMap<>();
	    		map.put("openid",opendid);
	    		map.put("lang", "zh-CN");
	    		list.add(map);
			}   
	    	
	    	map_send.put("user_list", list);
	    	Gson gsonWithnull = new GsonBuilder().serializeNulls().create();
	    	String request_mesage = gsonWithnull.toJson(map_send);
	    	System.out.println(request_mesage);
	    	return request_mesage;	
			
		}
		return "";
	}
	
}
