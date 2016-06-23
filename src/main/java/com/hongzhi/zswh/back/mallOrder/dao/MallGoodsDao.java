package com.hongzhi.zswh.back.mallOrder.dao;

import java.util.List;
import java.util.Map;

import com.hongzhi.zswh.util.page.PageModel;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 7, 2016    4:29:21 PM
 */
public interface MallGoodsDao {

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 7, 2016    4:34:56 PM
     * @param pageModel
     * @return
     */
    int goodsListCount(PageModel pageModel);

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 7, 2016    4:35:04 PM
     * @param pageModel
     * @return
     */
    List<Map<String,Object>> goodsList(PageModel pageModel);

}
