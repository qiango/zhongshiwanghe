package com.hongzhi.zswh.util.sms;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 14, 2016    5:33:03 PM
 */
public class MsgSender {
	
	public static void send(String phone, String verifyCode){
		SMSThread smsThread = new SMSThread(phone, verifyCode);
		smsThread.start();
	}
	

}
