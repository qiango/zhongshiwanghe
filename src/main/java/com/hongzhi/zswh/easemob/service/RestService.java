package com.hongzhi.zswh.easemob.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hongzhi.zswh.easemob.client.EASEMOB;
import com.hongzhi.zswh.easemob.dao.RestServiceDao;
import com.hongzhi.zswh.easemob.entity.RestUser;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: xuejian
 * Date: 2016/8/22
 * Time: 10:25
 * To change this template use File | Settings | File Templates.
 */
@Service("v1_4_restService")
public class RestService {
    @Autowired
    private RestServiceDao restServiceDao;

    /**
     * 注册 IM 用户[单个]
     * @return
     * @param user_id
     */
    public void restRegister(String user_id,String user_login_name) {

        String rest_user_name = getRestUserName(6);

        Map<String, String> param_map = new HashMap<>();

        param_map.put("username",rest_user_name);
        param_map.put("password", "000000");

        restServiceDao.saveRestUser(user_id,rest_user_name,user_login_name);

        ClientResponse response = null;
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS);

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + EASEMOB.TOKEN()).post(ClientResponse.class, ObjectUtil.toJson(param_map));

 /*           if (response.getStatus() == 200) {
                //JsonElement jelement = new JsonParser().parse(response.getEntity(String.class));
               // JsonObject result = jelement.getAsJsonObject();

               // System.out.print(result.get("uri").getAsString());
                return null;
            }else {
                return "false";
            }*/
        } catch (Exception e) {
            e.getMessage();
        } finally {

            if (response != null) {
                response.close();
            }
        }

      //  return null;

    }

    /**
     * 注册 IM 用户[批量]
     * @return
     */
    public Object restRegisterUsers() {

        List<Map<String,String>> user_map = restServiceDao.selectUserInfo();

        List<Map<String,String>> rest_users = new ArrayList<>();

        System.out.print( EASEMOB.ACCESS_TOKEN);

        List<RestUser> rest_user_entity = new ArrayList<>();

        if (user_map.size() > 0){
            for (Map<String,String> map :user_map){

                String rest_user_name = getRestUserName(6);

                Map<String, String> param_map = new HashMap<>();

                param_map.put("username",rest_user_name);
                param_map.put("password", "000000");
                rest_users.add(param_map);

                RestUser user_entity = new RestUser();

                user_entity.setUser_id(Integer.valueOf(map.get("user_id")));
                user_entity.setRest_user_name(rest_user_name);
                user_entity.setUser_login_name(map.get("user_login_name"));
                rest_user_entity.add(user_entity);

            }

            restServiceDao.saveRestUserInfo(rest_user_entity);

        }

         ClientResponse response = null;

        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS);

           // response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + EASEMOB.TOKEN()).post(ClientResponse.class, ObjectUtil.toJson(rest_users));

            if (response.getStatus() == 200) {

                return "success";
            }else{
                return "false";
            }


        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return null;
    }

    /**
     * 获取环信用户名
     * @param length
     * @return
     */
    public String getRestUserName(int length) {

        StringBuffer buffer = new StringBuffer();
        buffer.append("CJB");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        buffer.append(sdf.format(d));

        Random random = new Random();

        String randomString= "";

        // 参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNumber = random.nextInt(2) % 2 == 0 ? "char" : "number";
            // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNumber)) {
                // 输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                randomString += (char) (random.nextInt(26) + temp);
            } else if ("number".equalsIgnoreCase(charOrNumber)) {
                randomString += String.valueOf(random.nextInt(10));
            }
        }
        String orderCode = buffer.append(randomString).toString();
        return orderCode;
    }
    /**
     * 获取 IM 用户[批量]
     * @return
     */
    public Object queryUsers() {
        ClientResponse response = null;

        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS + "?limit=600");

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + EASEMOB.TOKEN()).get(ClientResponse.class);

            if (response.getStatus() == 200) {
                JsonElement jelement = new JsonParser().parse(response.getEntity(String.class));
                JsonObject result = jelement.getAsJsonObject();
                return result;
            }else {
                return "false";
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {

            if (response != null) {
                response.close();
            }
        }

        return null;
    }

    /**
     * 获取 IM 用户[单个]
     * @param rest_user_name
     * @return
     */
    public Object queryUser(String rest_user_name) {
        ClientResponse response = null;
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS + "/" + rest_user_name);

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + EASEMOB.TOKEN()).get(ClientResponse.class);

            if (response.getStatus() == 200) {
                JsonElement jelement = new JsonParser().parse(response.getEntity(String.class));
                JsonObject result = jelement.getAsJsonObject();

                System.out.print(result.get("uri").getAsString());
                return result;
            }else{
                return "false";
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {

            if (response != null) {
                response.close();
            }
        }

        return null;

    }

    /**
     *删除 IM 用户[单个]
     * @param rest_user_name
     * @return
     */
    public Object deleteUser(String rest_user_name) {
        ClientResponse response = null;
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS + "/"+rest_user_name );

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + EASEMOB.TOKEN()).delete(ClientResponse.class);

            if (response.getStatus() == 200) {
               /* JsonElement jelement = new JsonParser().parse(response.getEntity(String.class));
                JsonObject result = jelement.getAsJsonObject();

                System.out.print(result.get("uri").getAsString());*/
                return null;
            }else{
                return "false";
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {

            if (response != null) {
                response.close();
            }
        }

        return null;

    }

    /**
     * 删除 IM 用户[批量]
     * @return
     */
    public Object deleteUsers() {
        ClientResponse response = null;
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS +"?limit=20" );

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + EASEMOB.TOKEN()).delete(ClientResponse.class);

            if (response.getStatus() == 200) {
                JsonElement jelement = new JsonParser().parse(response.getEntity(String.class));
                JsonObject result = jelement.getAsJsonObject();

                return null;
            }else{
                return "false";
            }
        } catch (Exception e) {
            e.getMessage();

        } finally {

            if (response != null) {
                response.close();
            }
        }

        return null;

    }

    /**
     * 查看用户在线状态
     * @param rest_user_name
     * @return
     */
    public Object queryUserStatus(String rest_user_name) {

        ClientResponse response = null;
        Map<String, Object> map = new HashMap<>();
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS + "/" + rest_user_name + "/" + EASEMOB.STATUS);

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " +  EASEMOB.TOKEN()).get(ClientResponse.class);



            if (response.getStatus() == 200) {
                JsonElement jelement = new JsonParser().parse(response.getEntity(String.class));
                JsonObject result = jelement.getAsJsonObject();

                System.out.print(result.get("data").toString());

                if(result.get("data").toString().contains("online")){
                    map.put("status","online");
                }else{
                    map.put("status","offline");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {

            if (response != null) {
                response.close();
            }
        }

        return map;
    }

    /**
     * 重置 IM 用户密码
     * @param rest_user_name
     * @param new_password
     * @return
     */
    public Object updateUserPassword(String rest_user_name, String new_password) {

        ClientResponse response = null;
        Map<String, Object> map = new HashMap<>();
        try {
            Client client = Client.create();

            Map<String, String> param_map = new HashMap<>();

            param_map.put("newpassword",new_password);

            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS + "/" + rest_user_name + "/" + EASEMOB.PASSWORD);

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " +  EASEMOB.TOKEN()).put(ClientResponse.class,ObjectUtil.toJson(param_map));

            if (response.getStatus() == 200) {
                return null;
            }else{
                return "false";
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {

            if (response != null) {
                response.close();
            }
        }

        return null;

    }


    /**
     * 修改用户昵称
     * @param rest_user_name
     * @param new_nickname
     * @return
     */
    public Object updateUserName(String rest_user_name, String new_nickname) {

        ClientResponse response = null;
        Map<String, Object> map = new HashMap<>();
        try {
            Client client = Client.create();

            Map<String,String> param_map = new HashMap<>();

            param_map.put("nickname",new_nickname);

            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS + "/" + rest_user_name);

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " +  EASEMOB.TOKEN()).put(ClientResponse.class,ObjectUtil.toJson(param_map));

            if (response.getStatus() == 200) {
                return null;
            }else{
                return "false";
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {

            if (response != null) {
                response.close();
            }
        }

        return null;

    }

    /**
     * 强制用户下线
     * @param rest_user_name
     * @return
     */
    public Object disconnect(String rest_user_name) {

        ClientResponse response = null;
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS + "/" + rest_user_name + "/" + "disconnect");
            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " +  EASEMOB.TOKEN()).get(ClientResponse.class);

            if (response.getStatus() == 200) {
                JsonElement jelement = new JsonParser().parse(response.getEntity(String.class));
                JsonObject result = jelement.getAsJsonObject();

                System.out.print(result.get("data").toString());

                if(result.get("data").toString().contains("true")){ // true表示强制下线成功，false表示强制用户下线失败
                    return null;
                }else{
                    return "false";
                }

            } else if (404 == response.getStatus()) {
                //此用户不存在
            } else if (401 == response.getStatus()) {
                //未授权[无token、token错误、token过期]
            }

        } catch (Exception e) {
            e.getMessage();
        } finally {

            if (response != null) {
                response.close();
            }
        }
        return null;

    }
}
