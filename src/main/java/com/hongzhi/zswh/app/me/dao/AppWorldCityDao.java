package com.hongzhi.zswh.app.me.dao;

import java.util.List;
import java.util.Map;

import com.hongzhi.zswh.app.me.entity.WorldCity;

public interface AppWorldCityDao {

	List<Map<String, Object>> loadWorldCity();
	
	List<WorldCity> getChildren(int parent_id);
	
	
	List<WorldCity> getAllCitys();
	
}
