package com.hongzhi.zswh.easemob.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by taylor on 6/27/16.
 */
public class GetToken {

    public static void getToken(){
        Client client = Client.create();
        WebResource webResource = client.resource(EASEMOB.URL+"/"+EASEMOB.ORG_NAME+"/"+EASEMOB.APP_NAME+"/"+EASEMOB.TOKEN_TAIL);
        Map<String,String> param_map = new HashMap<>();
        param_map.put("grant_type",EASEMOB.GRANT_TYPE);
        param_map.put("client_id",EASEMOB.CLIENT_ID);
        param_map.put("client_secret",EASEMOB.CLIENT_SECRET);
        ClientResponse response = webResource.accept("application/json").post(ClientResponse.class,ObjectUtil.toJson(param_map));
        if(response.getStatus() == 200){
            JsonElement jelement = new JsonParser().parse( response.getEntity(String.class) );
            JsonObject  result = jelement.getAsJsonObject();
            EASEMOB.ACCESS_TOKEN = result.get("access_token").getAsString();
            EASEMOB.EXPIRES_IN = System.currentTimeMillis() + ( 1000 * result.get("expires_in").getAsLong() - ( 3600*24 ) ) ;
            EASEMOB.APPLICATION = result.get("application").getAsString();
        }
    }
}
