package com.hongzhi.zswh.app_v3.phoneInfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.app_v3.phoneInfo.dao.V3DeviceInfoDao;
import com.hongzhi.zswh.app_v3.phoneInfo.entity.DeviceInfo;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 27, 2016    6:48:25 PM
 */
@Service
public class V3DeviceInfoService {
	
	@Autowired
	private V3DeviceInfoDao dao;

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    6:49:13 PM
	 * @param properties 
	 * @param deviceInfo
	 * @return
	 * @throws HongZhiException 
	 */
	public Object saveInfo(SessionProperty properties, DeviceInfo deviceInfo) throws HongZhiException {
		deviceInfo.setUser_id(Integer.parseInt(properties.getUser_id()));
		int  insertCount = dao.saveDevice(deviceInfo);
		if(insertCount == 1 ){
			return "success";
		}else{
			throw new HongZhiException("1041");
		}
	}

}
