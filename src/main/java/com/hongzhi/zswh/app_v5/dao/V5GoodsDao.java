package com.hongzhi.zswh.app_v5.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.app_v5.entity.V5GoodsEntity;
import com.hongzhi.zswh.app_v5.entity.V5GoodsPicture;
import com.hongzhi.zswh.app_v5.entity.V5GoodsPrice;
import com.hongzhi.zswh.app_v5.entity.V5GoodsProperties;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.app_v5.entity.V5Coupon;
import com.hongzhi.zswh.app_v5.entity.V5GoodsImage;
import com.hongzhi.zswh.app_v5.entity.V5GoodsInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface V5GoodsDao {
	
	
	V5GoodsInfoEntity selectGoodsInfoById(@Param("goods_id") Integer goods_id);
	List<V5GoodsImage> selectGoods_image(@Param("goods_id") Integer goods_id);
	List<V5Coupon> coupon_list(@Param("goods_id") Integer goods_id);
	List<Map<String,Object>> coupon_properties(@Param("coupon_id") Integer coupon_id);
	List<Map<String,Object>> couponPropertiesByGoodsID(@Param("couponIDList") List<Integer> coupon_id_list);
	List<Map<String,Object>> selectGoods_properties(@Param("goods_id") Integer goods_id);
	List<V5GoodsEntity> getGoodsById();

	List<V5GoodsProperties> getAllPropertiesByGoodsId(Integer id);

	List<V5GoodsPrice> getAllPriceByGoodsId(Integer id);

	List<V5GoodsPicture> goodsPicturesByGoodsId(@Param("goods_id") Integer goodsId, @Param("index") Integer index);

}
