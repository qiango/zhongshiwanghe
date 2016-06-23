package com.hongzhi.zswh.back.sportsCamp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongzhi.zswh.back.basic.entity.User;
import com.hongzhi.zswh.back.sportsCamp.dao.SportsCampDao;
import com.hongzhi.zswh.back.sportsCamp.entity.SportsCamp;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.page.PageModel;


/**   Twitter : enjun.zhu
 * Creat time : Mar 24, 2016    11:21:33 AM
 */

@Repository
public class SportsCampService {
	@Autowired
	private SportsCampDao sportsCampDao;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	
	/**   Twitter : enjun.zhu 
	 * Creat time : Mar 23, 2016    2:57:51 PM
	 * @param params
	 * @return
	 */
	public String listByPage(Map<String, String> params) {
		PageModel pageModel=new PageModel(params.get("page_number"),params.get("page_size"),params.get("language_abbreviation"),"/sportscamp/list.htmls") ;
		pageModel.setOther(params.get("sports_camp_name"));
		pageModel.setTotalDataCount(sportsCampDao.listSportsCampByPageCount(pageModel));
		pageModel.setResult(sportsCampDao.listSportsCampByPage(pageModel));
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), pageModel);
	}
	
	/**   Twitter : enjun.zhu 
	 * Creat time : Mar 23, 2016    13:07:56 PM
	 * @param params
	 * @return
	 */
	public String insertLoad(SessionProperty sp) {
		List<Map<String,String>> language_list = dictionaryUtil.selectByPcodeAndLanuage("language", sp.getLanguage());
		Map<String,Object> out=new HashMap<>();
			out.put("language_list", language_list);
		return dictionaryUtil.appOut("0",sp.getLanguage(), out);
	}
	
	/**   Twitter : enjun.zhu 
	 * Creat time : Mar 24, 2016    13:07:56 PM
	 * @param params
	 * @param user 
	 * @return
	 */
	public String insertSave(Map<String, String> params) {
		int insertCount=sportsCampDao.insertSportsCamp(params);
		if(insertCount==1){
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		}else{
			return dictionaryUtil.appOut("1011", params.get("language_abbreviation"), "false");
		}
	}
	
	/**   worker : @zhuenjun 
	 * Creat time : Mar 24, 2016    13:28 PM
	 * @param params
	 * @return
	 */
	public String delete(Map<String, String> params) {
		int delete_count = sportsCampDao.SportsCampDelete(params.get("sports_camp_id"));
		if(delete_count==1){
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		}else{
			return dictionaryUtil.appOut("1034", params.get("language_abbreviation"), "false");
		}
	}
	
	/**   worker : @zhuenjun 
	 * Creat time : Mar 24, 2016    13:54:00 PM
	 * @param params
	 * @return
	 */
	public String modifyLoad(Map<String, String> params) {
		SportsCamp sportsCamp_query=sportsCampDao.selectById(params.get("sports_camp_id"), params.get("language_abbreviation"));
		List<Map<String,String>> language_list = dictionaryUtil.selectByPcodeAndLanuage("language", params.get("language_abbreviation"));
		try {
			dictionaryUtil.verifyData(sportsCamp_query, "1012"); //查无此数据
			Map<String,Object> out=new HashMap<>();
			out.put("language_list", language_list);
			out.put("sports_camp", sportsCamp_query);
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());	
		}
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "" );
	}
	
	/**   worker : @zhuenjun 
	 * Creat time : Mar 24, 2016    14:30 PM
	 * @param params
	 * @return
	 */
	public String modifySave(Map<String, String> params) {
		int update_count = sportsCampDao.update(params);
		if(update_count==1){
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		}else{
			return dictionaryUtil.appOut("1033", params.get("language_abbreviation"), "false");
		}
	}
	
	
	
	
	
}
