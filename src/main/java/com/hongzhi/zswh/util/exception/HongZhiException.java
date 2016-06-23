package com.hongzhi.zswh.util.exception;



/**
 * @author Saxon
 *
 */
public class HongZhiException extends Exception {

	private static final long serialVersionUID = 6465903487438263410L;
	private String code;
	private String message;
	

	public HongZhiException(String code) {
		this.code = code;
		this.message = "";
	}

	public HongZhiException(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
