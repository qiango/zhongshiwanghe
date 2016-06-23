/**  
 * @Title: AppAdvertisingDao.java
 * @Package com.hongzhi.zswh.app_v3.advertising.dao
 * @Description: TODO
 * @author Saxon Zhong
 * @date 2016年4月26日
 */
package com.hongzhi.zswh.app_v3.advertising.dao;

import java.util.Map;

/**
 * ClassName: AppAdvertisingDao
 * @Description: TODO
 * @author Saxon Zhong
 * @date 2016年4月26日
 */
public interface AppAdvertisingDao {

	/**
	 * @Description: TODO
	 * @param @param advertising_id
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author Saxon
	 * @date 2016年4月26日
	 */
	Map<String, Object> queryAdvertisingById(String advertising_id);

}
