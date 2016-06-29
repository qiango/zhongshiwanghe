package com.hongzhi.zswh.app_v4.alipay.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088221740268323";
	// 商户的私钥
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL4oVY3ViYGvmQyFlRsUBcs9mTNqvUR96UPiltaJ1I1wG5n6iM2oMIi0YyLfuxAxoOLTUOHeomtheNGaQFFhSsHIboCUtPG7W6OMdJPRQtEuiQOaPK8DkCPaPNBgGqw77HbYO3aUXnI4SQ1E3b/vgQ8MtUHp0lXv5EBhktDf5YKjAgMBAAECgYAQ/0X2cZni77E8f0w+qWGDMzRYdjgIwUBygvR8pMFNCzJMZyqHR3tfHNCns1vjLKx22fCm3+Ggu4uzIJiTLaEi3bCvuaFBoiXcQyi9IhLb2Ahn/Phg3lFomwLbpQZL9O8+wlXdIJmjcHWfGfzaZo3FgoTnbUZPU04QBDGjQApcUQJBAOU0/dgDJCEkEUkyMiDMLf2IqydS3fjnXRusmX7t/rBxm9xJo83pC5IKj5kb4Teez/SmgTUUcpjS8pR1ee/Q5s0CQQDUYso3zQRt7SyoQIiMMSgXNPXjCK0tK8BYgF2w7meoLr3lzgMWkpdqU9YNH/wcecxo+UEXbhBeb6Phfo4D2a8vAkBdr/9iYEZQVZHLPX1H8q2JyGdNN44Lw2ZHfZFwVI+aR5XRwTf3Xp6gHzj6+G5iccPG2JhPvf/pf7AwBqTE5bqxAkEAgJ0ehjIiF4uD+pOx3Ou7smoA6S6M6IjRHwcFNar5nLBJkQtzdUzqYppISoRvALOFZKBkmZKuWPHUVS51Q8GkQwJBAKAIuo4C5geVY13F4O9r4y22mNNfdW6XPDsRY1gDDla1KlZ/5p3KIJpow/sdb2iIbzrYVw/Sia/OTUxDLCrcQJA=";

    public static String md5_key = "bml0f6p46kgheqs3qlpg6kxd9k9xvnq3";

	
  	// 支付宝的公钥，无需修改该值
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
//	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+KFWN1YmBr5kMhZUbFAXLPZkzar1EfelD4pbWidSNcBuZ+ojNqDCItGMi37sQMaDi01Dh3qJrYXjRmkBRYUrByG6AlLTxu1ujjHST0ULRLokDmjyvA5Aj2jzQYBqsO+x22Dt2lF5yOEkNRN2/74EPDLVB6dJV7+RAYZLQ3+WCowIDAQAB";
	
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	public static String notify_url = "";
	
	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "/Users/taylor/doc/temp/zswhssl/";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";
    public static String sign_type_refund = "MD5";
	
	// refund url 
	public static String refund_url = "https://openapi.alipay.com/gateway.do";
	
	public static String iOS_appid ="2016042801347164";
	public static String Android_appid ="2016042801347170";
	
	public static String appid(String device){
	    String current_appid="";
	    switch (device) {
        case "iOS":
            current_appid=iOS_appid;
            break;
        case "Android":
            current_appid=Android_appid;
            break;
        }
	    return current_appid;
	}
	
	
	 static{
	       Properties prop = new Properties();
	       String resource = "config.properties";
	       InputStream is ;
	       try {
	           is = Resources.getResourceAsStream(resource);
	           prop.load(is);
	           notify_url=prop.getProperty("NotifyURL")+"/v4/alipay/notify.htmls";
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	   }

}
