package com.hongzhi.zswh.back.mallOrder.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongzhi.zswh.back.mallOrder.dao.MallOrderDao;
import com.hongzhi.zswh.back.mallOrder.entity.ParamComp;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.page.PageModel;

@Repository
public class MallOrderService {
	@Autowired
	private MallOrderDao mallOrderDao;
	
	public Object listByPage(ParamComp paramComp) {
		PageModel pageModel = new PageModel(paramComp.getPage_number(),paramComp.getPage_size(), paramComp.getLanguage(),"/mallOrder/list.htmls");
		Map<String,Object> param_map = new HashMap<>();
		param_map.put("order_code",paramComp.getOrder_code());
		param_map.put("order_status", paramComp.getOrder_status());
		param_map.put("goods_id", Integer.parseInt(ObjectUtil.getProperty(paramComp.getGoods_id(),0).toString()) );
		
		pageModel.setModel_object(param_map);
		//pageModel.setOther(params.get("order_code"));
		pageModel.setTotalDataCount(mallOrderDao.listMallOrderByPageCount(pageModel));
		pageModel.setResult(mallOrderDao.listMallOrderByPage(pageModel));
		pageModel.setPageParam(Arrays.asList("order_code","order_status","goods_id"));
		pageModel.setPageParamVal(Arrays.asList(paramComp.getOrder_code(),paramComp.getOrder_status(),ObjectUtil.getProperty(paramComp.getGoods_id(),"").toString()));
		pageModel.setPageString(pageModel.generatePageStr());
		
		return pageModel;
//		return dictionaryUtil.appOut(params.get("code"),params.get("language_abbreviation"), pageModel);
	}
	
	
	
	public Object selectOrderDetailByCode(ParamComp paramComp){
		return mallOrderDao.selectOrderDetailByCode(paramComp.getOrder_code());
	}
	
	public Object orderSumPriceAndCount(String goods_id){
	    if(ObjectUtil.isEmpty(goods_id)){
	        goods_id="0";
	    }
		return mallOrderDao.orderSumPriceAndCount(Integer.parseInt(goods_id));
	}
}
