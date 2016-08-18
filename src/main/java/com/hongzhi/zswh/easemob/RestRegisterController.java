package com.hongzhi.zswh.easemob;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hongzhi.zswh.easemob.client.EASEMOB;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xuejian
 * Date: 2016/8/17
 * Time: 15:06
 * To change this template use File | Settings | File Templates.
 */
@Controller("rest_register_controller")
@RequestMapping("/rest")
public class RestRegisterController {
    @Autowired
    private SessionUtil sess;
    @Autowired
    private DictionaryUtil dic;


    /**
     * 注册 IM 用户[单个]

     */
    @ResponseBody
    @RequestMapping(value = "/register_user")
    public String restRegister(){

        ClientResponse response = null;
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL+"/"+EASEMOB.ORG_NAME+"/"+EASEMOB.APP_NAME+"/"+EASEMOB.USERS);
            Map<String,String> param_map = new HashMap<>();
            param_map.put("username","15755352552");
            param_map.put("password","20160510");

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + "YWMttVPrkGTxEeaoO6HkDk8LjwAAAVfQoQohGardK6gIlFM3DoFiDB2-t2jxKEM").post(ClientResponse.class, ObjectUtil.toJson(param_map));

            if(response.getStatus() == 200) {
                JsonElement jelement = new JsonParser().parse( response.getEntity(String.class) );
                JsonObject result = jelement.getAsJsonObject();

                System.out.print( result.get("uri").getAsString());
            }
        }catch (Exception e){
            e.getMessage();
        }finally {

            if (response != null) {
                response.close();
            }
        }

            return "success";
    }

    /**
     * 注册 IM 用户[批量]
     * @return
     */
    public  String restRegisterUsers(){

        ClientResponse response = null;
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL+"/"+EASEMOB.ORG_NAME+"/"+EASEMOB.APP_NAME+"/"+EASEMOB.USERS);
            Map<String,String> param_map = new HashMap<>();
            param_map.put("username","15755352552");
            param_map.put("password","20160510");

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + "YWMttVPrkGTxEeaoO6HkDk8LjwAAAVfQoQohGardK6gIlFM3DoFiDB2-t2jxKEM").post(ClientResponse.class, ObjectUtil.toJson(param_map));

        }catch (Exception e){
            e.getMessage();
        }finally {
            if (response != null) {
                response.close();
            }
        }

        return  "success";
    }

    /**
     * 获取 IM 用户[单个]
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query_user")
  public String queryUser(){

        ClientResponse response = null;
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL+"/"+EASEMOB.ORG_NAME+"/"+EASEMOB.APP_NAME+"/"+EASEMOB.USERS+"/"+"15755352552");

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + "YWMttVPrkGTxEeaoO6HkDk8LjwAAAVfQoQohGardK6gIlFM3DoFiDB2-t2jxKEM").get(ClientResponse.class);

            if(response.getStatus() == 200) {
                JsonElement jelement = new JsonParser().parse( response.getEntity(String.class) );
                JsonObject result = jelement.getAsJsonObject();

                System.out.print( result.get("uri").getAsString());
            }
        }catch(Exception e){
            e.getMessage();
        }finally {

            if (response != null) {
                response.close();
            }
        }

      return "success";

  }



    @ResponseBody
    @RequestMapping(value = "/query_users")
    public String queryUsers(){

        ClientResponse response = null;

        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL+"/"+EASEMOB.ORG_NAME+"/"+EASEMOB.APP_NAME+"/"+EASEMOB.USERS+"?limit=20");

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + "YWMttVPrkGTxEeaoO6HkDk8LjwAAAVfQoQohGardK6gIlFM3DoFiDB2-t2jxKEM").get(ClientResponse.class);

            if(response.getStatus() == 200) {
                JsonElement jelement = new JsonParser().parse( response.getEntity(String.class) );
                JsonObject result = jelement.getAsJsonObject();

            }
        }catch (Exception e){
            e.getMessage();
        }finally {

            if (response != null) {
                response.close();
            }
        }

        return "success";

    }



    @ResponseBody
    @RequestMapping(value = "/query_users_pages")
    public String queryUsersPages(){

        ClientResponse response = null;

        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL+"/"+EASEMOB.ORG_NAME+"/"+EASEMOB.APP_NAME+"/"+EASEMOB.USERS+"?limit=2&cursor=LTU2ODc0MzQzOnNmdTlxdF9LRWVPaVFvMWlBZmc4S3c");

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + "YWMttVPrkGTxEeaoO6HkDk8LjwAAAVfQoQohGardK6gIlFM3DoFiDB2-t2jxKEM").get(ClientResponse.class);

            if(response.getStatus() == 200) {
                JsonElement jelement = new JsonParser().parse( response.getEntity(String.class) );
                JsonObject result = jelement.getAsJsonObject();

                System.out.print( result.get("uri").getAsString());
            }
        }catch (Exception e){
            e.getMessage();
        }finally {

            if (response != null) {
                response.close();
            }
        }

        return "success";

    }

    /**
     * 查询用户在线状态
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query_user_status")
    public String queryUserStatus(String user_name){
        ClientResponse response = null;
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL+"/"+EASEMOB.ORG_NAME+"/"+EASEMOB.APP_NAME+"/"+EASEMOB.USERS+"/"+user_name+"/"+"status");

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + "YWMttVPrkGTxEeaoO6HkDk8LjwAAAVfQoQohGardK6gIlFM3DoFiDB2-t2jxKEM").get(ClientResponse.class);

            if(response.getStatus() == 200) {
                JsonElement jelement = new JsonParser().parse( response.getEntity(String.class) );
                JsonObject result = jelement.getAsJsonObject();

                System.out.print( result.get("uri").getAsString());
            }
        }catch (Exception e){
            e.getMessage();
        }finally {

            if (response != null) {
                response.close();
            }
        }

        return "success";
    }
}
