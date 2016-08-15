package com.hongzhi.zswh.easemob.client;

/**
 * Created by taylor on 6/27/16.
 */
public class EASEMOB {
    public static String URL = "https://a1.easemob.com";
    public static String ORG_NAME = "zhongshiwanghe";
    public static String APP_NAME = "chengjubei";

    public static String TOKEN_TAIL = "token";

    public static String GRANT_TYPE = "client_credentials";
    public static String CLIENT_ID = "YXA6u9JhQDgeEeaNIvsrL_c7cQ";
    public static String CLIENT_SECRET = "YXA6PJf0O1I18Uu-Ws07tehvnTZN79I" ;


    public static String ACCESS_TOKEN="";
    public static long   EXPIRES_IN = 0 ;
    public static String APPLICATION ="";


    public static String TOKEN(){
        if(System.currentTimeMillis() > EXPIRES_IN ){
            GetToken.getToken();
        }
        return ACCESS_TOKEN;
    }

    static {
       GetToken.getToken();
    }







}
