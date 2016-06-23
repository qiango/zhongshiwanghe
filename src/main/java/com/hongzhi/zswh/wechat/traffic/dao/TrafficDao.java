/**  
 * @Title: TrafficDao.java
 * @Package com.hongzhi.zswh.wechat.traffic.dao
 * @Description: TODO
 * @author Saxon Zhong
 * @date 2016年6月1日
 */
package com.hongzhi.zswh.wechat.traffic.dao;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.wechat.traffic.entity.Traffic;

/**
 * ClassName: TrafficDao
 * @Description: TODO
 * @author Saxon Zhong
 * @date 2016年6月1日
 */
public interface TrafficDao {

	/**
	 * @Description: TODO
	 * @param @param traffic   
	 * @return void  
	 * @throws
	 * @author Saxon
	 * @date 2016年6月1日
	 */
	void addTraffic(Traffic traffic);
	
	
	/**
	 * @Description: TODO
	 * @param @param phone
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author Saxon
	 * @date 2016年6月1日
	 */
	int findTrafficByPhone(String phone);


	/**
	 * @Description: TODO
	 * @param @param phone
	 * @param @param status   
	 * @return void  
	 * @throws
	 * @author Saxon
	 * @date Jun 8, 2016
	 */
	int updateTraffic(@Param("order_id") String order_id, @Param("status") String status);


	/**
	 * @Description: TODO
	 * @param @param order_id
	 * @param @param code
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author Saxon
	 * @date 2016年6月12日
	 */
	int updateTrafficByOrder(@Param("order_id") String order_id, @Param("code") String code);

}
