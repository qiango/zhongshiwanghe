package com.hongzhi.zswh.back.mallOrder.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.back.mallOrder.dao.MallGoodsDao;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.page.PageModel;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 7, 2016    4:15:45 PM
 */
@Service
public class MallGoodsService {
    
    @Autowired
    private MallGoodsDao goodsDao;

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 7, 2016    4:20:41 PM
     * @param properties
     * @param page_number
     * @param goods_status
     * @param search_string
     * @return
     */
    public Object list(SessionProperty properties, String page_number, String goods_status, String search_string) {
        PageModel pageModel = new PageModel(page_number, null , properties.getLanguage(), "/mallGoods/list.htmls");
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("goodsStatus", goods_status);
        paramMap.put("search", search_string);
        pageModel.setModel_object(paramMap);
        pageModel.setLanguage(properties.getLanguage());
        
        pageModel.setTotalDataCount(goodsDao.goodsListCount(pageModel));
        pageModel.setResult(goodsDao.goodsList(pageModel));
        pageModel.setPageParam(Arrays.asList("goods_status","search_string"));
        pageModel.setPageParam(Arrays.asList( goods_status , search_string ));
        
        return pageModel;
    }

}
