package com.hongzhi.zswh.app_v4.wechatpay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hongzhi.zswh.app_v4.mall.dao.OrderDao;
import com.hongzhi.zswh.app_v4.mall.entity.V4ReturnHistory;
import com.hongzhi.zswh.util.basic.ObjectUtil;

/**   Twitter : @taylorwang789 
 * Creat time : May 23, 2016    10:09:51 AM
 */
@Component
public class WeChatTimer {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private WeChatPayService weChatPayService;
//    @Scheduled(cron="0/5 * * * * ? ")
    @Scheduled(cron="0 0 2 * * ?")
    public void checkOrder(){
      //  System.out.println("hello:from wechat Timer"+System.currentTimeMillis());

		List<V4ReturnHistory> list_returnHistory = orderDao.selectReturnHistory();
		
		if (list_returnHistory.size() != 0) {
			
			for (int i = 0; i < list_returnHistory.size(); i++) {
				String temp_type = weChatPayService.checkRefundStatus(list_returnHistory.get(i).getCode(),
						list_returnHistory.get(i).getId().toString(), list_returnHistory.get(i).getDevice());
				if ("2".equals(temp_type)) {
					//退款成功
					orderDao.updateReturnHistoryById(list_returnHistory.get(i).getId());
					
					list_returnHistory.get(i).setStatus("2");
					orderDao.addReturnHistory(list_returnHistory.get(i));
					
					// 更新商品订单表票数effect_counts
//					orderDao.updateOrderGoodsById(list_returnHistory.get(i).getOrder_id(), list_returnHistory.get(i).getGoods_id(),
//							list_returnHistory.get(i).getProperties_id(), list_returnHistory.get(i).getReturn_count());
					
				} else if ("3".equals(temp_type)) {
					//退款失败
					orderDao.updateReturnHistoryById(list_returnHistory.get(i).getId());
					
					list_returnHistory.get(i).setStatus("3");
					orderDao.addReturnHistory(list_returnHistory.get(i));
				}
			}
		}
	}

}
