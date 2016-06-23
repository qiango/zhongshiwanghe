package com.hongzhi.zswh.back.mallOrder.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.back.mallOrder.entity.MallOrder;
import com.hongzhi.zswh.util.page.PageModel;

public interface MallOrderDao {

	int listMallOrderByPageCount(PageModel pageModel);

	List<MallOrder> listMallOrderByPage(PageModel pageModel);
	
	List<Map<String,Object>> selectOrderDetailByCode(@Param("order_code") String order_code);
	
	//查询订单总数和所有订单总额
	Map<String,String> orderSumPriceAndCount(@Param("goodsID") Integer goods_id);
	
}
