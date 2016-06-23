/**  
 * @Title: AppAdvertisingService.java
 * @Package com.hongzhi.zswh.app_v3.advertising.service
 * @Description: TODO
 * @author Saxon Zhong
 * @date 2016年4月26日
 */
package com.hongzhi.zswh.app_v3.advertising.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.app_v3.advertising.dao.AppAdvertisingDao;

/**
 * ClassName: AppAdvertisingService
 * @Description: TODO
 * @author Saxon Zhong
 * @date 2016年4月26日
 */
@Service
public class AppAdvertisingService {

	@Autowired
	private AppAdvertisingDao appAdvertisingDao;
	/**
	 * @Description: TODO
	 * @param @param advertising_id
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author Saxon
	 * @date 2016年4月26日
	 */
	public Map<String, Object> queryAdvertisingById(String advertising_id) {
		return appAdvertisingDao.queryAdvertisingById(advertising_id);
	}


}
