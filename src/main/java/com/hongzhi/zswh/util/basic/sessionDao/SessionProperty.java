package com.hongzhi.zswh.util.basic.sessionDao;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 5, 2016    6:23:37 PM
 */
public class SessionProperty {
	
	private Integer id;
	private String user_id;
	private String language;
	private String user_real_name;
	private String platform;
	
	public String getUser_real_name() {
		return user_real_name;
	}
	public void setUser_real_name(String user_real_name) {
		this.user_real_name = user_real_name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	
	
	
	
}
