/**  
 * @Title: HttpURLService.java
 * @Package com.hongzhi.zswh.wechat.traffic.service
 * @Description: TODO
 * @author Saxon Zhong
 * @date Jun 8, 2016
 */
package com.hongzhi.zswh.wechat.traffic.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**
 * ClassName: HttpURLService
 * @Description: TODO
 * @author Saxon Zhong
 * @date Jun 8, 2016
 */
public class HttpURLService {
	
	
	public String sendHttp(String send_url) throws HongZhiException{
		URL url = null;
		HttpURLConnection httpurlconnection = null;
		try {
			url = new URL(send_url);

			// 以post方式请求
			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.getOutputStream().flush();
			httpurlconnection.getOutputStream().close();

			// 获取响应代码
			int code = httpurlconnection.getResponseCode();
			System.out.println("code   " + code);

			// 获取页面内容
			java.io.InputStream in = httpurlconnection.getInputStream();
			BufferedReader breader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String str = breader.readLine();
			
			String status = "0000";
			
			while (str != null) {
				System.out.println(str);
				JsonParser parser = new JsonParser();
				JsonElement json = parser.parse(str);
				status = json.getAsJsonObject().get("status").toString().replace("\"", "");
				System.out.println("status:" + status);
				str = breader.readLine();
			}
			
			return status;
		} catch (Exception e) {
			throw new HongZhiException("1075");
		} finally {
			if (httpurlconnection != null)
				httpurlconnection.disconnect();
		}
	}
}
