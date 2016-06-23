package com.hongzhi.zswh.app_v4.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.hongzhi.zswh.app_v4.me.dao.V4PasswordDao;
import com.hongzhi.zswh.app_v4.me.entity.V4PasswordParams;
import com.hongzhi.zswh.util.encryption.SHA256;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.sms.SMSVerify;

/**   Twitter : @taylorwang789 
 * Creat time : May 12, 2016    4:18:55 PM
 */
@Service
public class V4PasswordService {
	
	@Autowired
	private V4PasswordDao  passwordDao;

	/**   Twitter : @taylorwang789 
	 * Creat time : May 13, 2016    11:21:04 AM
	 * @param params
	 * @return
	 * @throws HongZhiException 
	 */
	public String reset(V4PasswordParams params) throws HongZhiException {
			SMSVerify.verify(params.Vdevice(), params.Vphone(), params.Vverify_code());
		
			String new_salt = SHA256.getSalt();
			String password = SHA256.encode(params.Vnew_password(), new_salt);

			int effect_count = passwordDao.resetPassword(new_salt,password,params.Vphone());

			if(effect_count==1){
				return  null ;
			}else{
				throw new HongZhiException("1018");
			}
	}


}
