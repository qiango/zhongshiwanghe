package com.hongzhi.zswh.app_v4.mall.dao;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.app_v4.mall.entity.Payment;
import com.hongzhi.zswh.app_v4.mall.entity.V4OrderDetailEntity;
import com.hongzhi.zswh.app_v4.mall.entity.V4OrderEntity;
import com.hongzhi.zswh.app_v4.mall.entity.V4ReturnHistory;

public interface OrderDao {
	/**
	 * @author zhurenkui
	 * @return
	 */
	int updateOrderStatusById(@Param("code") String code);
	int saveOrder(V4OrderEntity orderEntity); 

    /**   Twitter : @taylorwang789 
     * Creat time : May 16, 2016    2:59:39 PM
     * @return
     */
    List<Map<String, Object>> myOrderList(@Param("user_id") String user_id, @Param("language") String language);
    /**   Twitter : @taylorwang789 
     * Creat time : May 16, 2016    5:01:11 PM
     * @return
     */
    List<Map<String, Object>> myOrderGoodsList(@Param("user_id") String user_id, @Param("language") String language);

	void saveOrderGoods(V4OrderEntity orderEntity);

	/**
	 * @author zhurenkui
	 * @return
	 */ 
	Map<String,Object> selectEffecCountByCode(V4OrderEntity orderEntity);

    /**   Twitter : @taylorwang789 
     * Creat time : May 16, 2016    6:01:18 PM
     * @return
     */
    Map<String, Object> getDiscountInfoByGoodsIdAndPropertiesId(@Param("goods_id") Integer goods_id, @Param("property") Integer properties_id);
	List<V4OrderEntity> checkOrderCode(String orderCode);
    /**   Twitter : @taylorwang789 
     * Creat time : May 17, 2016    3:53:44 PM
     * @return
     */
    Map<String, Object> getOrderInfoToPay(String order_code);
    /**   Twitter : @taylorwang789 
     * Creat time : May 17, 2016    4:05:13 PM
     */
    void updatePayInfo(@Param("order_code") String out_trade_no, @Param("status") int status);
	String checkUserClubStatus(String user_id);
	String getEffectCountsByGoodsId(@Param("user_id") String user_id, @Param("goods_id") String goods_id);
    /**   Twitter : @taylorwang789 
     * Creat time : May 20, 2016    3:49:15 PM
     */
    void setPayment(Payment pay);
    /**   Twitter : @taylorwang789 
     * Creat time : May 20, 2016    4:03:51 PM
     */
    int updatePayment(Payment pay);
	

	int voteTickets(String orderCode);

	/**
	 * @author zhurenkui
	 * @return
	 */
	V4OrderDetailEntity selectOrderDetailByCode(@Param("code") String code, @Param("language") String language);
	/**
	 * @author zhurenkui
	 * @return
	 */
	List<Map<String,Object>>selectReturnTicketByCode(@Param("code") String code);

	int saveReturnHistory(V4ReturnHistory returnHistory);

    /**   Twitter : @taylorwang789 
     * Creat time : May 20, 2016    6:16:09 PM
     * @return
     */
    List<Map<String, Object>> usableGoodsByOrderId(@Param("order_id") Integer order_id, @Param("language") String language);
    /**   Twitter : @taylorwang789 
     * Creat time : May 20, 2016    6:31:14 PM
     * @return
     */
    List<Map<String, Object>> unusableGoodsByOrderId(Integer order_id);

	List<V4ReturnHistory> selectReturnHistoryById(@Param("order_id") Integer order_id, @Param("goods_id") Integer goods_id);
	void updateOrderGoodsById(@Param("order_id") Integer order_id, @Param("goods_id") Integer goods_id, @Param("properties_id") Integer properties_id, @Param("return_count") Integer return_count);
	void updateReturnHistory(@Param("order_id") Integer order_id, @Param("goods_id") Integer goods_id);

    /**   Twitter : @taylorwang789 
     * Creat time : May 21, 2016    1:06:24 PM
     */
    int payHistroyInsert(@Param("order_code") String out_trade_no, @Param("return") Integer status, @Param("source") Integer src);
    /**   Twitter : @taylorwang789 
     * Creat time : May 21, 2016    1:57:12 PM
     * @return
     */
    Map<String, Object> getServerNotifyInfo(String order_code);
    /**   Twitter : @taylorwang789 
     * Creat time : May 21, 2016    2:52:52 PM
     * @return
     */
    Map<String, Object> getOrderInfoToRefund(String order_code);
	Map<String,Object> selectOrderCodeById(Integer order_id);
	Double selectGoodsPriceById(@Param("goods_id") Integer goods_id, @Param("properties_id") Integer properties_id, @Param("order_id") Integer order_id);
    /**   Twitter : @taylorwang789 
     * Creat time : May 21, 2016    5:36:47 PM
     * @param order_code
     * @param status
     */
    void updateOrderStatusByOrderCode(@Param("order_code") String order_code, @Param("status") String status);
	String selectPaymentByOrderId(Integer order_id);
	void saveReturnReason(V4ReturnHistory returnHistory);
	Map<String, Object> getCountsById(@Param("user_id") Integer user_id, @Param("goods_id") Integer goods_id);
	List<V4ReturnHistory> selectReturnHistory();
	void updateReturnHistoryById(Integer id);
	void addReturnHistory(V4ReturnHistory v4ReturnHistory);
	void updateReturnHistorySecond(@Param("order_id") Integer order_id, @Param("goods_id") Integer goods_id, @Param("refund_no") String refund_no);
    /**   Twitter : @taylorwang789 
     * Creat time : May 25, 2016    10:55:50 AM
     */
    void updateOrderStatusByOrderId(@Param("order_id") Integer order_id, @Param("status") String to_status);
    /**   Twitter : @taylorwang789 
     * Creat time : May 25, 2016    1:47:33 PM
     */
    void updateOrderEffectCountByOrderId(@Param("order_id") Integer order_id, @Param("goods_id") Integer goods_id, @Param("property") Integer properties_id, @Param("effect_count") Integer effect_count);
	List<Map<String, Object>> selectMallGoodsList();




}
