package com.hongzhi.zswh.app.me.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongzhi.zswh.app.me.dao.AppWorldCityDao;
import com.hongzhi.zswh.app.me.entity.WorldCity;
import com.hongzhi.zswh.back.basic.entity.Menu;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;
@Repository
public class AppWorldCityService {
	
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private AppWorldCityDao appWorldCityDao;

	public String loadWorldCity(Map<String, String> params) {
		Integer platform_id=Integer.valueOf(params.get("platform_id"));
		try {
			dictionaryUtil.verifyData(platform_id==2, "1024");
		} catch (HongZhiException e) {
			e.printStackTrace();
		}
		//List<Map<String,Object>> world_city_list=appWorldCityDao.loadWorldCity();
		
		//
		WorldCity wc = new WorldCity();
		wc.setId(0);
		wc = cityTreeAll(wc);
		
		
		///\
		
		
		
		Map<String,Object> out=new HashMap<String, Object>();
		out.put("world_city_list", wc.getSon_world_city_list());
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
	}
	
	
	public WorldCity cityTreeAll(WorldCity worldCity){
		List<WorldCity> citys = appWorldCityDao.getAllCitys();
		Map<Integer,List<Integer>> pid_map = new HashMap<>();
		//  p_id     index in menus
		for(int i=0;i<citys.size();i++){
			if(ObjectUtil.isEmpty( pid_map.get( citys.get(i).getParent_id()) )){
				List<Integer>  indexs = new ArrayList<>();
				indexs.add(i);
				pid_map.put( citys.get(i).getParent_id() , indexs );
			}else{
				List<Integer> indexa =  pid_map.get(  citys.get(i).getParent_id()  );
				indexa.add(i);
				pid_map.put( citys.get(i).getParent_id() , indexa );
			}
		}

		worldCity = tree(worldCity, citys,pid_map);
		return worldCity;
	}
	
	
	

	private WorldCity tree(WorldCity root,  List<WorldCity> citys,  Map<Integer,List<Integer>> pid_map ){
		List<WorldCity> children =  new ArrayList<>();
		List<Integer> child_index = pid_map.get(root.getId());
		if(null != child_index ) {
			for(int i=0;i< child_index.size() ;i++){
				children.add(  citys.get( child_index.get(i)   )  );
			}
		  	root.setSon_world_city_list(children);
		  	for(int i=0;i<children.size();i++){
		  		children.set(i, tree(children.get(i),citys,pid_map) );
		  	}
		}
		return root;
	}
	
	

	

}
