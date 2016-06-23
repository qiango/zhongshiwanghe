package com.hongzhi.zswh.back.news.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongzhi.zswh.back.news.dao.NewsDao;

import com.hongzhi.zswh.back.competition.dao.PlatformDao;
import com.hongzhi.zswh.back.competition.entity.Platform;
import com.hongzhi.zswh.back.news.entity.NEWS;
import com.hongzhi.zswh.back.news.entity.NewsImageEntity;
import com.hongzhi.zswh.back.news.entity.NewsParam;
import com.hongzhi.zswh.back.news.entity.NewsRangeEntity;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;

import com.hongzhi.zswh.util.page.PageModel;

/**
 * Twitter : @taylorwang789 Creat time : May 31, 2016 2:07:37 PM
 */
@Service
public class NewsService {

	@Autowired
	private NewsDao newsDao;
	@Autowired
	private PlatformDao platformDao;

	public String logicDelete(String news_id) throws HongZhiException {
		// 校检
		if (ObjectUtil.isEmpty(news_id)) {
			throw new HongZhiException("1073");
		}

		// 删除
		int count = newsDao.logicDelete(news_id);

		// 对返回结果进行判断
		if (1 == count) {
			return "success";
		} else {
			throw new HongZhiException("1034");
		}

	}

	public Object list(NewsParam params) {

		PageModel pageModel = new PageModel(params.getPage_number(), params.getPage_size(),
				params.getLanguage(), "/news/list.htmls");
		pageModel.setOther(params.getNews_title());
		pageModel.setTotalDataCount(newsDao.listNewsByPageCount(pageModel));
		pageModel.setResult(newsDao.listNewsByPage(pageModel));
		pageModel.setPageParam(Arrays.asList("news_title"));
		pageModel.setPageParamVal(Arrays.asList(params.getNews_title()));

		return pageModel;
	}

	public Map<String,Object> newLoad(SessionProperty properties) {

//		List<Map<String, String>> language_list = dictionaryUtil.selectByPcodeAndLanuage("language", properties.getLanguage());
		List<Map<String,Object>> competition_list = newsDao.listCompetitiony();
		List<Map<String,Object>> club_list = newsDao.listClub();
		List<Platform> platform_list = platformDao.listPlatform();
		List<Map<String, String>> information_type_list = new ArrayList<>();
		String[] code_arr = { "0", "1" };
//		String[] value_arr = { "图文", "图片集", "视频" }; , "2"
		String[] value_arr = { "图文", "图片集" };
		for (int i = 0; i < 2; i++) {
			Map<String, String> information_type = new HashMap<>();
			information_type.put("news_type", code_arr[i]);
			information_type.put("zh_value", value_arr[i]);
			information_type_list.add(information_type);
		}
		
		List<Map<String,Object>> range_list = new ArrayList<>();
		Integer[] range_type_array = {0,1,2};
		String[] range_name_array = {"公共","俱乐部","赛事"};
		for(int i=0;i< 3 ;i++){
		    Map<String,Object> range_type = new HashMap<>();
		    range_type.put("range_type", range_type_array[i]);
		    range_type.put("range_name", range_name_array[i]);
		    range_list.add(range_type);
		}
		
		List<Map<String,Object>> news_category_list = newsDao.newsCategoryList();
		Map<String,Object> root = new HashMap<>();
		
		Map<String, Object> out = new HashMap<>();
//		out.put("language_list", language_list);
		out.put("competition_list", competition_list);
		out.put("club_list", club_list);
		out.put("range_list", range_list);
		root.put("category_id", 1);
		out.put("category_document", ObjectUtil.getTree(root, news_category_list, "category_id", "parent_category_id", "children").get("children"));
		root.put("category_id", 2);
		out.put("category_images", ObjectUtil.getTree(root, news_category_list, "category_id", "parent_category_id", "children").get("children"));
		root.put("category_id", 3);
		out.put("category_video", ObjectUtil.getTree(root, news_category_list, "category_id", "parent_category_id", "children").get("children"));
		out.put("platform_list", platform_list);
		out.put("information_type_list", information_type_list);
		return out;
	}

	public Object newSave(NewsParam newsParam) throws HongZhiException {
		newsParam.setNews_status(NEWS.STATUS_PUBLISH);

		newsDao.newSave(newsParam);// 返回的主键
		
		if(newsParam.getNews_type().equals("2")){
		    newsParam.setMedia_information(newsParam.getFile_id());
		}

		if (!ObjectUtil.isEmpty(newsParam.getNews_id())) { // 图片，图片集保存在media_information表
		    saveImageRangeCategory(newsParam);
			return null;
		} else {
			throw new HongZhiException("1011");
		}
	}
	
	
	private void saveImageRangeCategory(NewsParam newsParam){
	    // save image 
        String media_url = newsParam.getSubmit_file();
        List<String> list_media_url = Arrays.asList(media_url.split(","));
        List<String> list_media_information = Arrays.asList(ObjectUtil.getProperty(newsParam.getMedia_information(),"").toString().split("#\\$#"));
        List<NewsImageEntity> image_list = new ArrayList<>();
        for(int i=0;i<list_media_url.size();i++){
            NewsImageEntity image_entity = new NewsImageEntity();
            image_entity.setNews_id(newsParam.getNews_id());
            image_entity.setMedia_url(list_media_url.get(i));
            image_entity.setMedia_information( i < list_media_information.size() ? list_media_information.get(i):"");
            image_list.add(image_entity);
        }
        if (!ObjectUtil.isEmpty(media_url)) {
            newsDao.saveNewsMultimedia(image_list);
        }
        
        // save range 
        List<Integer> range_type = new ArrayList<>();
        List<Integer> circle_id = new ArrayList<>();

        List<String> range_type_list = new ArrayList<>();
        if(!ObjectUtil.isEmpty(newsParam.getNews_range())){
            range_type_list = Arrays.asList(newsParam.getNews_range().split(","));
        }
        for(int i=0;i<range_type_list.size();i++){
            range_type.add(Integer.parseInt(range_type_list.get(i)));
        }
        List<String> circle_id_list = new ArrayList<>();
        if(!ObjectUtil.isEmpty(newsParam.getCircle_id())){
            circle_id_list = Arrays.asList(newsParam.getCircle_id().split(","));
        }else{
        	 circle_id.add(0);
        }
        
        for(int i=0;i<circle_id_list.size();i++){
            if(ObjectUtil.isEmpty(circle_id_list.get(i))){
                circle_id.add(0);
            }else{
                circle_id.add(Integer.parseInt(circle_id_list.get(i)));
            }
        }
        
        List<NewsRangeEntity> range_list = new ArrayList<>();
        for(int i=0;i<range_type.size();i++){
            NewsRangeEntity range_entity = new NewsRangeEntity();
            range_entity.setNews_id(newsParam.getNews_id());
            range_entity.setRange_type(range_type.get(i));
            range_entity.setCircle_id(circle_id.get(i));
            range_list.add(range_entity);
        }
        if(!range_list.isEmpty()){
            newsDao.saveNewsRange(range_list);
        }
        
        // save category 
        List<Integer> category_id_list = new ArrayList<>();
        List<String> category_input = new ArrayList<>();
        if(!ObjectUtil.isEmpty(newsParam.getCategory_id())){
            category_input = Arrays.asList(newsParam.getCategory_id().split(","));
        }
        for(int i=0;i<category_input.size();i++){
            category_id_list.add(Integer.parseInt(category_input.get(i)));
        }
        if(!category_id_list.isEmpty()){
            newsDao.saveNewsCategory(category_id_list,newsParam.getNews_id());
        }
	}

	public Map<String, Object> modifyLoad(String news_id) throws HongZhiException {
//		// 校检
//		if (ObjectUtil.isEmpty(news_id)) {
//			throw new HongZhiException("1073");
//		}
//		//平台和语言
//		List<Platform> platform_list = platformDao.listPlatform();
//		List<Map<String, String>> information_type_list = new ArrayList<>();
//		String[] code_arr = { "0", "1", "2" };
//		String[] value_arr = { "图片", "图片集", "视频" };
//		for (int i = 0; i < 3; i++) {
//			Map<String, String> information_type = new HashMap<>();
//			information_type.put("code", code_arr[i]);
//			information_type.put("zh_value", value_arr[i]);
//			information_type_list.add(information_type);
//		}
//		// 加载该id对应的数据
		Map<String, Object> modifyLoad = newsDao.modifyLoad(news_id);
//		//对应的图片信息
		List<Map<String, Object>> picture = newsDao.findPictureBynews_id(news_id);
		modifyLoad.put("picture", picture);
		//资讯范围
		Map<String, Object> newsRang = newsDao.findNewsRangeById(news_id);
		modifyLoad.put("newsrang",newsRang);
		// 返回结果
		Map<String,Object> out =  newLoad(null);
		out.put("news", modifyLoad);
//		out.put("platform_list", platform_list);
//		out.put("information_type_list", information_type_list);
		return out;
	}

	@Transactional
	public String modifySave(NewsParam newsParam) throws HongZhiException {
		// 判断
		if (ObjectUtil.isEmpty(newsParam.getNews_id())) {
			throw new HongZhiException("1073");
		}

		// 根据news_id修改数据库中对应的记录
		int modifySave = newsDao.modifySave(newsParam);
		//先删除该news_id 对应的记录
		newsDao.logicDeletePictureBynew_id(newsParam.getNews_id());
		newsDao.logicDeleteRangeByNewsID(newsParam.getNews_id());
		newsDao.logicDeleteCategoryByNewsID(newsParam.getNews_id());
		
		saveImageRangeCategory(newsParam);

//		//重新保存图片
//		String media_url = newsParam.getSubmit_file();
//		List<String> list_media_url = Arrays.asList(media_url.split(","));
//		List<String> list_media_information = Arrays.asList(ObjectUtil.getProperty(newsParam.getMedia_information(),"").toString().split("#\\$#"));
//
//		List<NewsImageEntity> image_list = new ArrayList<>();
//		for(int i=0;i<list_media_url.size();i++){
//		    NewsImageEntity image_entity = new NewsImageEntity();
//		    image_entity.setNews_id(newsParam.getNews_id());
//		    image_entity.setMedia_url(list_media_url.get(i));
//		    image_entity.setMedia_information( i < list_media_information.size() ? list_media_information.get(i):"");
//		    image_list.add(image_entity);
//		}
//
//		if (!ObjectUtil.isEmpty(media_url)) {
//			newsDao.saveNewsMultimedia(image_list);
//		}

		// 对结果进行判断
		if (modifySave == 1) {
			return "success";
		} else {
			throw new HongZhiException("1033");
		}

	}
}
