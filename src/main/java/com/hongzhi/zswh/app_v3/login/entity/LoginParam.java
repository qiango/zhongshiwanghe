package com.hongzhi.zswh.app_v3.login.entity;

import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 26, 2016    2:10:25 PM
 */
public class LoginParam {
	
	 private String user_name;
	 private String language_abbreviation;
	 private String language;
	 private String user_password;
	 private String phone;
	 private String platform_id;

// verify
	public String VUser_password() throws HongZhiException {
		return ExcepUtil.verify(user_password, "1024").toString();
	}
	public String VPhone() throws HongZhiException {
		return ExcepUtil.verify(phone, "1006").toString();
	}
	public String VPlatform_id() throws HongZhiException {
		return ExcepUtil.verify(platform_id, "1007").toString();
	}
	
	
///  getter and setter 	
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getLanguage_abbreviation() {
		return language_abbreviation;
	}
	public void setLanguage_abbreviation(String language_abbreviation) {
		this.language_abbreviation = language_abbreviation;
	}
	public String getLanguage() {
		if(ObjectUtil.isEmpty(language)){
			   if(ObjectUtil.isEmpty(language_abbreviation) || language_abbreviation.length()<2){
				   this.language="zh";
			   }else if(language_abbreviation.length()>2){
				  this.language = this.language_abbreviation.substring(0, 2); 
			   }else{
				   this.language=this.language_abbreviation;
			   }
		}else if(language.length() < 2 ){
			this.language="zh";
		}else if (language.length() > 2 ){
			this.language = this.language_abbreviation.substring(0, 2); 
		}
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPlatform_id() {
		if(ObjectUtil.isEmpty(platform_id)){
			this.platform_id="2";
		}
		return platform_id;
	}
	public void setPlatform_id(String platform_id) {
		this.platform_id = platform_id;
	}
	 

}
