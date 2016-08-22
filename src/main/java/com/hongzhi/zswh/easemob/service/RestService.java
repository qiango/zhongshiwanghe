package com.hongzhi.zswh.easemob.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hongzhi.zswh.easemob.client.EASEMOB;
import com.hongzhi.zswh.easemob.dao.RestServiceDao;
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
    public Object restRegister(String user_id) {

        ClientResponse response = null;
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS);
            Map<String, String> param_map = new HashMap<>();
            param_map.put("username", "15755352552");
            param_map.put("password", "20160510");

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + "YWMttVPrkGTxEeaoO6HkDk8LjwAAAVfQoQohGardK6gIlFM3DoFiDB2-t2jxKEM").post(ClientResponse.class, ObjectUtil.toJson(param_map));

            if (response.getStatus() == 200) {
                JsonElement jelement = new JsonParser().parse(response.getEntity(String.class));
                JsonObject result = jelement.getAsJsonObject();

                System.out.print(result.get("uri").getAsString());
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
     * 注册 IM 用户[批量]
     * @return
     */
    public Object restRegisterUsers() {

        List<Map<String,String>> user_map = restServiceDao.selectUserInfo();

        List<Map<String,String>> rest_users = new ArrayList<>();
        String token = EASEMOB.ACCESS_TOKEN;
        if (user_map.size() > 0){
            for (Map<String,String> map :user_map){

                String rest_user_name = getRestUserName(6);
                Map<String, String> param_map = new HashMap<>();
                param_map.put("username",rest_user_name);
                param_map.put("password", "000000");
                rest_users.add(param_map);

                restServiceDao.saveRestUser(map.get("user_id"),map.get("user_login_name"),rest_user_name);
            }

        }

         ClientResponse response = null;
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS);

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + EASEMOB.ACCESS_TOKEN).post(ClientResponse.class, ObjectUtil.toJson(rest_users));

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

            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS + "?limit=20");

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + "YWMttVPrkGTxEeaoO6HkDk8LjwAAAVfQoQohGardK6gIlFM3DoFiDB2-t2jxKEM").get(ClientResponse.class);

            if (response.getStatus() == 200) {
                JsonElement jelement = new JsonParser().parse(response.getEntity(String.class));
                JsonObject result = jelement.getAsJsonObject();
                return result;
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
     * @param user_name
     * @return
     */
    public Object queryUser(String user_name) {
        ClientResponse response = null;
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS + "/" + user_name);

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + "YWMttVPrkGTxEeaoO6HkDk8LjwAAAVfQoQohGardK6gIlFM3DoFiDB2-t2jxKEM").get(ClientResponse.class);

            if (response.getStatus() == 200) {
                JsonElement jelement = new JsonParser().parse(response.getEntity(String.class));
                JsonObject result = jelement.getAsJsonObject();

                System.out.print(result.get("uri").getAsString());
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {

            if (response != null) {
                response.close();
            }
        }

        return "success";

    }

    /**
     *删除 IM 用户[单个]
     * @param user_name
     * @return
     */
    public Object deleteUser(String user_name) {
        ClientResponse response = null;
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS + "/"+user_name );

            Map<String, String> param_map = new HashMap<>();
            param_map.put("username", "15755352552");
            param_map.put("password", "20160510");
            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + "YWMttVPrkGTxEeaoO6HkDk8LjwAAAVfQoQohGardK6gIlFM3DoFiDB2-t2jxKEM").delete(ClientResponse.class,ObjectUtil.toJson(param_map));

            if (response.getStatus() == 200) {
                JsonElement jelement = new JsonParser().parse(response.getEntity(String.class));
                JsonObject result = jelement.getAsJsonObject();

                System.out.print(result.get("uri").getAsString());
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {

            if (response != null) {
                response.close();
            }
        }

        return "success";

    }

    public Object deleteUsers() {
        ClientResponse response = null;
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(EASEMOB.URL + "/" + EASEMOB.ORG_NAME + "/" + EASEMOB.APP_NAME + "/" + EASEMOB.USERS +"?limit=20" );

            response = webResource.accept("application/json").header(HttpHeaders.AUTHORIZATION, "Bearer " + "YWMttVPrkGTxEeaoO6HkDk8LjwAAAVfQoQohGardK6gIlFM3DoFiDB2-t2jxKEM").delete(ClientResponse.class);

            if (response.getStatus() == 200) {
                JsonElement jelement = new JsonParser().parse(response.getEntity(String.class));
                JsonObject result = jelement.getAsJsonObject();

                System.out.print(result.get("uri").getAsString());
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
