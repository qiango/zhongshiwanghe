package com.hongzhi.zswh.back.information.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongzhi.zswh.back.basic.entity.User;
import com.hongzhi.zswh.back.competition.dao.CompetitionDao;
import com.hongzhi.zswh.back.competition.dao.PlatformDao;
import com.hongzhi.zswh.back.competition.entity.Competition;
import com.hongzhi.zswh.back.competition.entity.Platform;
import com.hongzhi.zswh.back.information.dao.InformationDao;
import com.hongzhi.zswh.back.information.entity.Information;
import com.hongzhi.zswh.back.information.entity.InformationImage;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.page.PageModel;
import org.springframework.ui.Model;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 23, 2016    5:13:29 PM
 */
@Repository
public class InformationService {
	@Autowired
	private InformationDao infoDao;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private PlatformDao platformDao;
	@Autowired
	private CompetitionDao competitionDao;

	/**   Twitter : @taylorwang789 
	 *  show information(with image) list by page
	 * Creat time : Mar 23, 2016    5:15:08 PM
	 * @param params
	 * @return
	 */
	public String listInformationByPage(Map<String, String> params) {
		PageModel pageModel = new PageModel(params.get("page_number") , params.get("page_size"),params.get("language_abbreviation") ,"/information/list.htmls");
		pageModel.setOther(params.get("search_string"));
		pageModel.setTotalDataCount(infoDao.listInformationByPageCount(pageModel));
		List<Information> informationList=infoDao.listInformationByPage(pageModel);
		for (int i = 0;i <informationList.size();i++){
			Map<String, Object> information=(Map<String, Object>) informationList.get(i);
			String information_id=information.get("information_id").toString();
			List<InformationImage> infomationImagesList=infoDao.listInformationImagesById(information_id.toString());
			information.put("image_list", infomationImagesList);
		}
		pageModel.setResult(informationList);
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), pageModel);
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    5:23:16 PM
	 * insert a new record information
	 * @param params  Information entity
	 * @return
	 */
	public String insertNewInformation(Information information,String submit_file,SessionProperty sp) {
		int user_id=Integer.parseInt(sp.getUser_id());
		information.setUser_id(user_id);
		int informationCount=infoDao.insertInformation(information);
		int information_id=information.getInformation_id();
		InformationImage informationImage=new InformationImage();
		informationImage.setInformation_id(information_id);
		informationImage.setInformation_image_url(submit_file);
		informationImage.setUser_id(user_id);
		int informationImageCount=infoDao.insertInformationImages(informationImage);
		if(informationCount==1&&informationImageCount==1){
			return dictionaryUtil.appOut("0",sp.getLanguage() , "true");
		}else{
			return dictionaryUtil.appOut("1011", sp.getLanguage() , "false");
		}
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    5:30:44 PM
	 * @param params   information_id
	 * @return
	 */
	public String modifyLoad(Map<String, String> params) {
		Information info_query=infoDao.selectById(Integer.parseInt(params.get("information_id")));
		try {
			dictionaryUtil.verifyData(info_query, "1012"); //查无此数据
			String information_id=info_query.getInformation_id().toString();
			List<InformationImage> infomationImagesList=infoDao.listInformationImagesById(information_id.toString());
			info_query.setImage_list(infomationImagesList);
			Map<String,Object> out=new HashMap<>();
			List<Competition> competition_list=competitionDao.listCompetitiony();
			List<Platform> platform_list=platformDao.listPlatform();
			List<Map<String, String>> information_type_list=new ArrayList<>();
			String[] code_arr={"0","1","2"};
			String[] value_arr={"图片","图片集","视频"};
			for(int i = 0;i<3;i++){
				Map<String, String> information_type=new HashMap<>();
				information_type.put("code", code_arr[i]);
				information_type.put("zh_value", value_arr[i]);
				information_type_list.add(information_type);
			}
			out.put("competition_list", competition_list);
			out.put("platform_list", platform_list);
			out.put("information_type_list", information_type_list);
			//information_result的value之为修改某个资讯的时候查到到的这个资讯对象
			out.put("info_query", info_query);
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());	
		}
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "" );
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    5:32:20 PM
	 * @param params  language_abbreviation
	 * @return  competition_list  platform_list  info_type
	 */
	public String insertLoad(SessionProperty sp ) {
		List<Map<String,String>> language_list = dictionaryUtil.selectByPcodeAndLanuage("language", sp.getLanguage());
		List<Competition> competition_list=competitionDao.listCompetitiony();
		List<Platform> platform_list=platformDao.listPlatform();
		List<Map<String, String>> information_type_list=new ArrayList<>();
		String[] code_arr={"0","1","2"};
		String[] value_arr={"图片","图片集","视频"};
		for(int i = 0;i<3;i++){
			Map<String, String> information_type=new HashMap<>();
			information_type.put("code", code_arr[i]);
			information_type.put("zh_value", value_arr[i]);
			information_type_list.add(information_type);
		}
		Map<String,Object> out=new HashMap<>();
		out.put("language_list", language_list);
		out.put("competition_list", competition_list);
		out.put("platform_list", platform_list);
		out.put("information_type_list", information_type_list);
		return dictionaryUtil.appOut("0",sp.getLanguage(), out);
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    5:33:43 PM
	 * @param params  Information entity 
	 * @return  update true or false
	 */
	public String updateInfo(Map<String, String> params) {
		int update_count = infoDao.update(params);
		if(update_count==1){
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		}else{
			return dictionaryUtil.appOut("1033", params.get("language_abbreviation"), "false");
		}
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    5:37:28 PM
	 * logic delete , actually is update is_delete field  
	 * @param params
	 * @return
	 */
	public String logicDelete(Map<String, String> params) {
		int delete_count = infoDao.delete(params.get("information_id"));
		if(delete_count==1){
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		}else{
			return dictionaryUtil.appOut("1034", params.get("language_abbreviation"), "false");
		}
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 29, 2016    10:17:06 AM
	 * @param information_id
	 * @param model 
	 * @return
	 */
	public String informationDetail(String information_id, Model model) {
		Information information = infoDao.selectById(Integer.parseInt(information_id));
		information.setImage_list(  infoDao.listInformationImagesById(information_id));
		model.addAttribute("information", information );
		return "/information/appInformationDetail";
	}

}
