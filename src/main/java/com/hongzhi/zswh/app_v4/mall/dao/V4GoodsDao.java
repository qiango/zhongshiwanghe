package com.hongzhi.zswh.app_v4.mall.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.app_v4.mall.entity.V4GoodsEntity;
import com.hongzhi.zswh.app_v4.mall.entity.V4GoodsPicture;
import com.hongzhi.zswh.app_v4.mall.entity.V4GoodsPrice;
import com.hongzhi.zswh.app_v4.mall.entity.V4GoodsProperties;

/**   Twitter : @taylorwang789 
 * Creat time : May 16, 2016    11:25:22 AM
 */
public interface V4GoodsDao {

    /**   Twitter : @taylorwang789 
     * Creat time : May 16, 2016    11:25:29 AM
     * @param goods_id
     * @param index   -1:all , 0:=0 , 1:<>0
     * @return
     */
    List<V4GoodsPicture> goodsPicturesByGoodsId(@Param("goods_id") Integer goodsId, @Param("index") Integer index);

    /**   Twitter : @taylorwang789 
     * Creat time : May 16, 2016    11:30:01 AM
     * @param id
     * @return
     */
    List<V4GoodsProperties> getAllPropertiesByGoodsId(Integer id);

    /**   Twitter : @taylorwang789 
     * Creat time : May 16, 2016    11:32:13 AM
     * @param id
     * @return
     */
    List<V4GoodsPrice> getAllPriceByGoodsId(Integer id);

    /**   Twitter : @taylorwang789 
     * Creat time : May 16, 2016    12:25:50 PM
     * @param i
     * @return
     */
    List<V4GoodsEntity> getGoodsById(@Param("goods_id") Integer goods_id);

    /**   Twitter : @taylorwang789 
     * Creat time : May 23, 2016    3:09:16 PM
     * @return
     */
    List<Map<String,Object>> presetRefundReasonList();

}
