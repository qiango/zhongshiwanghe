package com.hongzhi.zswh.app.me.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.app.me.entity.Adress;


/**   Author : enjun.zhu 
 * Creat time : Mar 25, 2016    11:23;00 AM
 */
public interface AdressDao {
	
	List<Map<String,Object>> selectAdressByID(@Param("user_id") int user_id);
	int insertAdress(Adress adress);
	int deleteAdress(@Param("shipping_id") String shipping_id);
	Adress selectAdressByShippingID(@Param("shipping_id") int shipping_id);
	int updateAdress(Adress address);
	int updateAdressDefaultAll(int user_id);
	
	Map<String,Object> selectcitybyparent_id(int parent_id);
	
}
