package com.hongzhi.zswh.app_v2.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.app_v2.profile.dao.AppDeviceDao;
import com.hongzhi.zswh.app_v2.profile.entity.DeviceInformation;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 6, 2016    4:31:35 PM
 */
@Service
public class AppDeviceService {
	
	@Autowired
	private AppDeviceDao appDeviceDao;

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 6, 2016    4:35:14 PM
	 * @param devInfo
	 * @param sp
	 * @return
	 */
	public String saveDevInfo(DeviceInformation devInfo, SessionProperty sp) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
