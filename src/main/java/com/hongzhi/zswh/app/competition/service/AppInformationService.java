package com.hongzhi.zswh.app.competition.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongzhi.zswh.app.competition.dao.AppInformationDao;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.page.PageModel;

@Repository
public class AppInformationService {
	
	@Autowired
	private AppInformationDao appInformationDao;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	public String loadInformationByCompetitionId(Map<String, String> params) {
		PageModel pageModel = new PageModel(params.get("page_number"),params.get("page_size"), params.get("language_abbreviation"));
		pageModel.setOther(params.get("competition_id"));
		Map<String, Object> out=new HashMap<String, Object>();
		List<Map<String,Object>> info_list = appInformationDao.loadInformationByCompetitionId(pageModel);
		
		List<Integer> info_id_list = new ArrayList<>();
		for (int i = 0; i < info_list.size(); i++) {
			Map<String, Object> info_map = info_list.get(i);
			List<Map<String, String>> url_list = new ArrayList<>();
			info_list.get(i).put("image_list", url_list);
			info_id_list.add((Integer) info_map.get("information_id"));
		}
		
		/**
		 * @author Saxon
		 * 判断info_id_list数组中有没有数据
		 */
		
		if (null != info_id_list && 0 != info_id_list.size()) {
			List<Map<String,Object>> image_list = appInformationDao.getImageByInfoId(info_id_list);
			
			for(int i=0;i<image_list.size();i++){
				List<Map<String,String>> list = (List<Map<String,String>>) info_list.get(info_id_list.indexOf( image_list.get(i).get("information_id") )).get("image_list");
				Map<String,String> map = new HashMap<>();
				map.put("information_image_url", (String) image_list.get(i).get("information_image_url"));
				list.add(map);
				info_list.get(info_id_list.indexOf( image_list.get(i).get("information_id") )).put("image_list", list);
			}
			out.put("information_detail_url", "/information/index.htmls?information_id=");
			out.put("information_list", info_list);
		} else {
		}
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
	}
	
	
	public String loadInformationDetailByInformationId(Map<String, String> params) {
		Integer information_id = Integer.valueOf(params.get("information_id"));
		Integer platform_id = Integer.valueOf(params.get("platform_id"));
		try {
			dictionaryUtil.verifyData(platform_id == 2, "1024");
		} catch (HongZhiException e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> map = appInformationDao.loadInformationDetailByInformationId(information_id);
		Map<String, Object> out = new HashMap<String, Object>();
		out.put("information", map);
		return dictionaryUtil.appOut(params.get("code"),params.get("language_abbreviation"), out);
	}
}
