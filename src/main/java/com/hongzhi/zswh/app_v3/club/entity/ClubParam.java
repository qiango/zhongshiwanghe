package com.hongzhi.zswh.app_v3.club.entity;

import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 26, 2016    2:10:25 PM
 */
public class ClubParam {
	
	 private String user_name;
	 private String language_abbreviation;
	 private String language;
	 private String user_password;
	 private String phone;
	 private String platform_id;
	 private String page;
	 private String is_focus;
	 private String club_user_id;
	 private String club_id;
	 private String join_club_status ;
	 private String session_id ;
	 private String reason;
	 

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
	public String Vis_focus() throws HongZhiException {
		return ExcepUtil.verify(is_focus, "1043").toString();
	}
	public String Vclub_user_id() throws HongZhiException {
		return ExcepUtil.verify(club_user_id, "1043").toString();
	}
	public String Vclub_id() throws HongZhiException {
		return ExcepUtil.verify(club_id, "1021").toString();
	}
	public String Vjoin_club_status() throws HongZhiException {
		return ExcepUtil.verify(join_club_status, "1045").toString();
	}
	public String Vreason() throws HongZhiException {
		return ExcepUtil.verify(reason, "1056").toString();
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
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getIs_focus() {
		return is_focus;
	}
	public void setIs_focus(String is_focus) {
		this.is_focus = is_focus;
	}
	public String getClub_user_id() {
		return club_user_id;
	}
	public void setClub_user_id(String club_user_id) {
		this.club_user_id = club_user_id;
	}
	public String getClub_id() {
		return club_id;
	}
	public void setClub_id(String club_id) {
		this.club_id = club_id;
	}
	public String getJoin_club_status() {
		return join_club_status;
	}
	public void setJoin_club_status(String join_club_status) {
		this.join_club_status = join_club_status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	 

}
