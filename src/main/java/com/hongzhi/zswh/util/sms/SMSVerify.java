package com.hongzhi.zswh.util.sms;


import com.google.gson.Gson;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 13, 2016    3:46:27 PM
 */
public class SMSVerify {
	
	public  static Integer checkcode(String appKey,String phone,String zone,String code) {
		
		String address = "https://webapi.sms.mob.com/sms/verify";
		MobClient client = null;
		try {
			client = new MobClient(address);
			client.addParam("appkey", appKey).addParam("phone", phone)
					.addParam("zone", zone).addParam("code", code);
			client.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			client.addRequestProperty("Accept", "application/json");
			String result = client.post();
			Gson gson = new Gson();
			SMSStatusEntity  sms = gson.fromJson(result, SMSStatusEntity.class);
			System.out.println(sms.getStatus());
			return sms.getStatus();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.release();
		}
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		
//		String s = new SMSVerify().checkcode("124089a216b92","18678781092","86", "5849");
//		System.out.println(s);
//		Gson gson = new Gson();
//		SMSStatusEntity  sms = gson.fromJson(s, SMSStatusEntity.class);
//		System.out.println(sms.getStatus());
		
	}
	
	
	public static boolean  verify(String iOSorAndroid ,String phone , String verifyCode) throws HongZhiException{
		  String iOS_appKey = "124089a216b92";
		  String android_appKey = "12434128c4158";
		  Integer  return_value = 0;
		  switch (iOSorAndroid) {
		  	case "iOS":  
		  		return_value = checkcode(iOS_appKey, phone, "86", verifyCode);
		  		break;
		  	case "Android": 
		  		return_value = checkcode(android_appKey, phone, "86", verifyCode);
		  		break;
		  	default: break;
		  	}
		  if(return_value.equals(200)){
			  return true;
		  }else{
			  throw new HongZhiException("1057");
		  }
	}
	

}
