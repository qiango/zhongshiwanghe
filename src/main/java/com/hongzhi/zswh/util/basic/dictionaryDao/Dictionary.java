package com.hongzhi.zswh.util.basic.dictionaryDao;

import java.util.List;

/**
 * Twitter : @taylorwang789 
 * Mar 16, 2016    10:20:23 PM
 */
public class Dictionary {
	private String code;
	private String value;
	private String p_code;
	private String language;
	private List<Dictionary>  children;
	

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		this.p_code = p_code;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public List<Dictionary> getChildren() {
		return children;
	}
	public void setChildren(List<Dictionary> children) {
		this.children = children;
	}
	

}
