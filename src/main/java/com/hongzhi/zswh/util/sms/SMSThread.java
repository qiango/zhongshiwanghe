package com.hongzhi.zswh.util.sms;

import com.bcloud.msg.http.HttpSender;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 14, 2016    5:33:48 PM
 */
public class SMSThread extends Thread{
	
	
	private String verifyCode;
	private String phone;
	
	

	/**
	 * @param verifyCode
	 * @param phone
	 */
	public SMSThread(String phone, String verifyCode) {
		super();
		this.phone = phone;
		this.verifyCode = verifyCode;
	}



	public void run(){
		String url = "http://222.73.117.158/msg/";// 应用地址
		String account = "jiekou-clcs-05";// 账号
		String pswd = "Tch599999";// 密码
		String mobile = phone;
		String msg = "您好，您的验证码是:"+verifyCode;// 短信内容
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String product = null;// 产品ID
		String extno = null;// 扩展码
		try {
			String returnString = HttpSender.batchSend(url, account, pswd, mobile, msg, needstatus, product, extno);
//			System.out.println(returnString);
			// TODO 处理返回值,参见HTTP协议文档
		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}
	}
	

	

}
