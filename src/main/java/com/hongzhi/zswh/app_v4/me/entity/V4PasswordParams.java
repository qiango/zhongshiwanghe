package com.hongzhi.zswh.app_v4.me.entity;

import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 12, 2016    4:21:37 PM
 */
public class V4PasswordParams {
	
	private String  phone;
	private String  new_password;
	private String  device;
	private String  verify_code;

// verify
	public String Vphone() throws HongZhiException {
		return  ExcepUtil.verify(phone, "1006").toString();
	}
	public String Vnew_password() throws HongZhiException {
		return  ExcepUtil.verify(new_password, "1007").toString();
	}
	public String Vdevice() throws HongZhiException{
		return  ExcepUtil.verify(device, "1058").toString();
	}
	public String Vverify_code() throws HongZhiException{
		return  ExcepUtil.verify(verify_code, "1059").toString();
	}
	
// getter & setter	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNew_password() {
		return new_password;
	}
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getVerify_code() {
		return verify_code;
	}
	public void setVerify_code(String verify_code) {
		this.verify_code = verify_code;
	}
	
	

}
