package com.hongzhi.zswh.app_v4.mall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.app_v4.mall.dao.OrderDao;
import com.hongzhi.zswh.app_v4.mall.dao.V4GoodsDao;
import com.hongzhi.zswh.app_v4.mall.entity.V4GoodsEntity;
import com.hongzhi.zswh.app_v4.mall.entity.V4GoodsPrice;
import com.hongzhi.zswh.app_v4.mall.entity.V4GoodsProperties;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 16, 2016    11:04:39 AM
 */
@Service
public class V4GoodsService {

	@Autowired
	private V4GoodsDao goodsDao;
	@Autowired
	private OrderDao  orderDao ;

    /**   Twitter : @taylorwang789 
     * Creat time : May 16, 2016    11:08:23 AM
     * @param goods_id 
     * @return
     */
    public Object listGoods(Integer goods_id) {
        if(ObjectUtil.isEmpty(goods_id)){
            goods_id = 1;
        }
        List<V4GoodsEntity>  goods_list = goodsDao.getGoodsById(goods_id);

        for(int i=0;i<goods_list.size();i++){
			List<V4GoodsProperties> all_properties = goodsDao.getAllPropertiesByGoodsId(goods_list.get(i).getId());
			
			List<V4GoodsPrice> all_price = goodsDao.getAllPriceByGoodsId(goods_list.get(i).getId());
            
            goods_list.get(i).setPictures(goodsDao.goodsPicturesByGoodsId(goods_list.get(i).getId(),1));

            goods_list.get(i).dbPrice(all_properties,all_price);
        }
        
        for(int i=0;i<goods_list.get(0).getPrice().size();i++){
            goods_list.get(0).getPrice().get(i).sortProperties("zh");
        }
        
		Map<String, Object> map = new HashMap<>();
		map.put("goods_info", goods_list);
		return map;
    }

    /**   Twitter : @taylorwang789 
     * Creat time : May 23, 2016    3:08:39 PM
     * @return
     */
    public Object presetRefundReasonList() {
        Map<String,Object> out = new HashMap<>();
        out.put("preset_reason", goodsDao.presetRefundReasonList() );
        return  out;
    }

    /**   Twitter : @taylorwang789 
     * Creat time : May 24, 2016    7:09:29 PM
     * @param order_code
     * @return
     */
    public Object canRefundCount(String order_id) {
        Map<String,Object> map = orderDao.selectOrderCodeById(Integer.parseInt(order_id));
        if(ObjectUtil.isEmpty(map)){
            Map<String,Object>  map_null = new HashMap<>();
            map_null.put("current_limit", 0);
            return map_null;
        }else{
            map.remove("code");
            map.remove("goods_id");
            map.put("current_limit", map.get("effect_counts"));
            map.remove("effect_counts");
            return map;
        }
    }

    /**   Twitter : @taylorwang789 
     * Creat time : May 26, 2016    10:33:06 AM
     * @return
     */
    public Object allGoodsList() {

        List<V4GoodsEntity>  goods_list = goodsDao.getGoodsById(null);

        for(int i=0;i<goods_list.size();i++){
            List<V4GoodsProperties> all_properties = goodsDao.getAllPropertiesByGoodsId(goods_list.get(i).getId());
            
            List<V4GoodsPrice> all_price = goodsDao.getAllPriceByGoodsId(goods_list.get(i).getId());
            
            goods_list.get(i).setPictures(goodsDao.goodsPicturesByGoodsId(goods_list.get(i).getId(),0));

            goods_list.get(i).dbPrice(all_properties,all_price);
        }
        
        for(int j=0;j<goods_list.size();j++){
            for(int i=0;i<goods_list.get(j).getPrice().size();i++){
                goods_list.get(j).getPrice().get(i).sortProperties("zh");
            }
        }
        
        Map<String, Object> map = new HashMap<>();
        map.put("mall_goods_list", goods_list);
        return map;
    }

}
