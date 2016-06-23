package com.hongzhi.zswh.app_v4.mall.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.app_v4.mall.dao.V4SettingDao;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

@Service
public class V4SettingService {

	@Autowired
	private V4SettingDao v4SettingDao;
	
	public Object isTicketCheckerById(String user_id) throws HongZhiException{
		//判断用户id是否存在
		if(ObjectUtil.isEmpty(user_id)){
			throw new HongZhiException("1003");
		}
		//创建
		HashMap<String, Map<String,Object>> special_authority_list = new HashMap<String,Map<String,Object>>();
		HashMap<String, Object> tem_map = new HashMap<String,Object>();
		//判断是否
		int tem_val = v4SettingDao.isTicketCheckerById(user_id);
		if(0==tem_val){
			tem_map.put("verify_ticket", 0);
		}else{
			tem_map.put("verify_ticket", 1);
		}
		special_authority_list.put("special_authority_list",tem_map);
		//返回
		return special_authority_list;
	}


}
