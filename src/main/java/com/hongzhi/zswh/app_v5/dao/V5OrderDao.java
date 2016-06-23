package com.hongzhi.zswh.app_v5.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.app_v4.mall.entity.V4OrderDetailEntity;
import com.hongzhi.zswh.app_v5.entity.V5OrderDetailEntity;
import com.hongzhi.zswh.app_v5.entity.V5OrderEntity;

public interface V5OrderDao {

	int saveOrder(V5OrderEntity orderEntity);

	List<V5OrderEntity> checkOrderCode(String orderCode);

	void saveOrderGoods(V5OrderEntity orderEntity);

	void saveOrderCouponUse(@Param("order_id") Integer order_id, @Param("list") List<String> list_coupon_id);
	
	
	V5OrderDetailEntity selectOrderDetailByCode(@Param("code") String code, @Param("language") String language);

    List<Map<String, Object>> usableGoodsByOrderId(@Param("order_id") Integer order_id, @Param("language") String language);

    List<Map<String, Object>> unusableGoodsByOrderId(Integer order_id);
    
    List<Map<String ,Object>> findCouponByOrderId(@Param("order_code") String order_code);

    
    Map<String,Object> findPirceByCode(@Param("order_code") String order_code);
    

    List<Map<String, Object>> myOrderList(@Param("user_id") String user_id, @Param("language") String language);
    List<Map<String, Object>> myOrderGoodsList(@Param("user_id") String user_id, @Param("language") String language);


    
}
