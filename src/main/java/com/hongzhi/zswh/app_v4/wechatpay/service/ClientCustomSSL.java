/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package com.hongzhi.zswh.app_v4.wechatpay.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.Properties;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.io.Resources;

import com.hongzhi.zswh.app_v4.wechatpay.config.WeChatConfig;

/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public class ClientCustomSSL {
    
    private static String iOS_cert_path = "";
    private static String Android_cert_path = "";

    static {
        Properties prop = new Properties();
        String resource = "config.properties";
        InputStream is ;
        try {
            is = Resources.getResourceAsStream(resource);
            prop.load(is);
            iOS_cert_path=prop.getProperty("iOSCertPath");
            Android_cert_path=prop.getProperty("AndroidCertPath");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  static String weChatRefund(String mch_id, String xml_string, String refund_url, String device){
        try{
//        String mch_id = WeChatConfig.iOS_mch_id;
//        String refund_url = "https://api.mch.weixin.qq.com/secapi/pay/refund";
//        String xml_string = "<xml> <appid>wxf335e214a840e01f</appid> <mch_id>1343259601</mch_id> <nonce_str>cSaqBzo7b</nonce_str> <op_user_id>1343259601</op_user_id> <out_trade_no>CJB2016052414361q12</out_trade_no> <out_refund_no>CJB2016052414361q12-1</out_refund_no> <refund_fee>16900</refund_fee> <total_fee>16900</total_fee> <sign>D499ED8DE589E22F1C1BD85F988EACEA</sign> </xml> ";
        String  cert_path = "";
        switch (device) {
        case "iOS":
            cert_path= iOS_cert_path;
            break;
        case "Android":
            cert_path= Android_cert_path;
            break;
        }
        
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(cert_path));
        try {
            keyStore.load(instream, mch_id.toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, mch_id.toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        StringBuffer sb = new StringBuffer();
        try {
            
            
            HttpPost httppost = new HttpPost(refund_url);
            HttpEntity postentity= new ByteArrayEntity(xml_string.getBytes("UTF-8"));
            
            httppost.setHeader("Content-Type", "text/xml");
            httppost.setEntity(postentity);

            System.out.println("executing request" + httppost.getRequestLine());

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();

//                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
//                        System.out.println(text);
                        sb.append(text);
                    }
                   
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println(sb.toString());
        return sb.toString();
        }catch (Exception e){
            return null;
        }

    }

}
