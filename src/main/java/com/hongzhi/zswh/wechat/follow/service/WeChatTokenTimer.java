package com.hongzhi.zswh.wechat.follow.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.wechat.follow.entity.WECHAT;



/**   Twitter : @taylorwang789 
 * Creat time : Jun 3, 2016    4:15:25 PM
 */
@Component
public class WeChatTokenTimer {

    @Scheduled(cron="0 0 * * * ?")
    public void runTask() {
         testIt();
    }
     
    private void testIt(){

       String https_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WECHAT.APPID+"&secret="+WECHAT.SECRET;
       URL url;
       String result = "";
       try {

          url = new URL(https_url);
          HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
             
          //dumpl all cert info
          print_https_cert(con);
             
          //dump all the content
          result = print_content(con);
             
       } catch (MalformedURLException e) {
          e.printStackTrace();
       } catch (IOException e) {
          e.printStackTrace();
       }
       setAccessToken(result);
    }
    
    private void setAccessToken(String result){
         JsonObject json = new JsonParser().parse(result).getAsJsonObject();
         JsonElement ele = json.get("access_token");
         if(!ObjectUtil.isEmpty(ele)){
             WECHAT.ACCESS_TOKEN = ele.getAsString() ;
         }
    }
     
    private void print_https_cert(HttpsURLConnection con){
      
     if(con!=null){
             
       try {
                 
               System.out.println("Response Code : " + con.getResponseCode());
               System.out.println("Cipher Suite : " + con.getCipherSuite());
               System.out.println("\n");
                           
               Certificate[] certs = con.getServerCertificates();
               for(Certificate cert : certs){
                  System.out.println("Cert Type : " + cert.getType());
                  System.out.println("Cert Hash Code : " + cert.hashCode());
                  System.out.println("Cert Public Key Algorithm : " 
                                               + cert.getPublicKey().getAlgorithm());
                  System.out.println("Cert Public Key Format : " 
                                               + cert.getPublicKey().getFormat());
                  System.out.println("\n");
               }
                           
               } catch (SSLPeerUnverifiedException e) {
                   e.printStackTrace();
               } catch (IOException e){
                   e.printStackTrace();
               }

      }
     
    }
     
    private String print_content(HttpsURLConnection con){
        String out = "";
        if(con!=null){
             
             try {
                 
                System.out.println("****** Content of the URL ********");            
                BufferedReader br = 
                 new BufferedReader(
                     new InputStreamReader(con.getInputStream()));
                         
                String input;
                         
                while ((input = br.readLine()) != null){
                    out+=input;
                   System.out.println(input);
                }
                br.close();
                         
             } catch (IOException e) {
                e.printStackTrace();
             }
             
        }
        return out;
    }
}
