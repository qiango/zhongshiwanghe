package com.hongzhi.zswh.app_v5.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hongzhi.zswh.app_v5.entity.V5GoodsEntity;
import com.hongzhi.zswh.app_v5.entity.V5GoodsPrice;
import com.hongzhi.zswh.app_v5.entity.V5GoodsProperties;
import com.hongzhi.zswh.app_v5.dao.V5GoodsDao;



import com.hongzhi.zswh.app_v5.entity.V5Coupon;
import com.hongzhi.zswh.app_v5.entity.V5GoodsImage;
import com.hongzhi.zswh.app_v5.entity.V5GoodsInfoEntity;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.date.DateFormat;


/**   Twitter : @taylorwang789 
 * Creat time : Jun 12, 2016    4:06:14 PM
 */
@Service
public class V5GoodsService {

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 12, 2016    4:08:29 PM
     * @return
     */

	@Autowired
	private V5GoodsDao goodsDao;

    public Object goodsList() {
        /**
         * V5GooodsInfo
         * goods_id
         * goods_name
         * goods_image
         * goods_describe
         * goods_origin_price
         * goods_current_price
         */
    	
    	
        List<V5GoodsEntity>  goods_list = goodsDao.getGoodsById();

        for(int i=0;i<goods_list.size();i++){
            List<V5GoodsProperties> all_properties = goodsDao.getAllPropertiesByGoodsId(goods_list.get(i).getId());
            
            List<V5GoodsPrice> all_price = goodsDao.getAllPriceByGoodsId(goods_list.get(i).getId());
            
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
    

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 12, 2016    4:19:21 PM
     * @param goods_id
     * @return
     */
    public Object goodsInfo(Integer goods_id) {
        /**
         * V5GooodsInfo
         * goods_id
         * goods_name
         * goods_describe
         * goods_origin_price
         * goods_current_price
         * List<V5GoodsImage> goods_image
         *               image_url
         *               show_index
         *               
         * List<V5Coupon> coupon_list 
         *              coupon_id
         *              coupon_name
         *              coupon_describe
         *              Map<String,Object>  coupon_properties
         * 
         * Map<String,Object>  goods_properties                      
         *              
         */
        String language = "zh";
    	V5GoodsInfoEntity goods_Info = goodsDao.selectGoodsInfoById(goods_id);
    	//goods_properties  
    	List<Map<String, Object>> tem_goods_properties = goodsDao.selectGoods_properties(goods_id);

    	HashMap<String, Object> goods_properties = new HashMap<>();
    	List<String> need_convert_date = Arrays.asList("begin_date","due_date");
    	for (Map<String, Object> map : tem_goods_properties) {
    	    
    	    if(need_convert_date.contains(map.get("property_code"))){
    	        goods_properties.put((String) map.get("property_code"), DateFormat.getDateFromMillis(language, Long.parseLong(map.get("property_value").toString())) );
    	    }else{
    	        goods_properties.put((String) map.get("property_code"), map.get("property_value"));
    	    }
    	    
		}
    	if(!ObjectUtil.isEmpty(goods_properties.get("on_shelf_time")) && ! ObjectUtil.isEmpty(goods_properties.get("off_shelf_time"))){
    	    String on_shelf_time_str = goods_properties.get("on_shelf_time").toString();
    	    String off_shelf_time_str = goods_properties.get("off_shelf_time").toString();
    	    if(!ObjectUtil.isEmpty(on_shelf_time_str) && !ObjectUtil.isEmpty(off_shelf_time_str)){
    	        Long  now_millsecond = System.currentTimeMillis();
    	        Long  on_shelf_time = Long.parseLong(on_shelf_time_str);
    	        Long  off_shelf_time = Long.parseLong(off_shelf_time_str);
    	        if( on_shelf_time < now_millsecond && now_millsecond<off_shelf_time ){
    	            goods_properties.put("validity_time",0);
    	        }else if(on_shelf_time > now_millsecond){
    	            goods_properties.put("validity_time",-1);
    	        }else{
    	            goods_properties.put("validity_time",1);
    	        }
    	    }else{
    	        goods_properties.put("validity_time",null);
    	    }
    	    goods_properties.remove("on_shelf_time");
    	    goods_properties.remove("off_shelf_time");
    	}
    	
    	goods_Info.setGoods_properties(goods_properties);
    	//pic
    	List<V5GoodsImage> goods_image = goodsDao.selectGoods_image(goods_id);
    	if(!ObjectUtil.isEmpty(goods_image)){
    		goods_Info.setGoods_image(goods_image);
    	}
    	
    	//coupon
    	List<V5Coupon> coupon_list = goodsDao.coupon_list(goods_id);
    	List<Integer> coupon_id_list =new ArrayList<>();
    	for(int i=0;i<coupon_list.size();i++){
    	    coupon_id_list.add(coupon_list.get(i).getCoupon_id());
    	}
    	if(!ObjectUtil.isEmpty(coupon_id_list) && coupon_id_list.size()>0){
    	    List<Map<String,Object>> coupon_properties_list = goodsDao.couponPropertiesByGoodsID(coupon_id_list);
    	    for(int i=0;i<coupon_properties_list.size();i++){
    	        coupon_list.get(coupon_id_list.indexOf(  Integer.parseInt( coupon_properties_list.get(i).get("coupon_id").toString())  )).getCoupon_properties().put(coupon_properties_list.get(i).get("property_code").toString(), coupon_properties_list.get(i).get("property_value"));
    	    }
    	}
    	
//    	for (V5Coupon v5Coupon : coupon_list) {
//    		List<Map<String, Object>> tem_list = goodsDao.coupon_properties(v5Coupon.getCoupon_id());
//    		HashMap<String, Object> coupon_properties = new HashMap<>();
//    		
//    		for (Map<String, Object> map : tem_list) {
//    			coupon_properties.put((String) map.get("property_code"), map.get("property_value"));
//			}
//    		v5Coupon.setCoupon_properties(coupon_properties);	
//		}
    	
    	
    	goods_Info.setCoupon_list(coupon_list);
    	
        return goods_Info;
    }

}
