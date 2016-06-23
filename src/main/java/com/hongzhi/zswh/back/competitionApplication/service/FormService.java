package com.hongzhi.zswh.back.competitionApplication.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hongzhi.zswh.app_v3.notification.service.NotificationService;
import com.hongzhi.zswh.back.basic.entity.User;
import com.hongzhi.zswh.back.competitionApplication.dao.CompetitionApplicationDao;
import com.hongzhi.zswh.back.competitionApplication.dao.ControlsDao;
import com.hongzhi.zswh.back.competitionApplication.dao.FormDao;
import com.hongzhi.zswh.back.competitionApplication.dao.FormDataDao;
import com.hongzhi.zswh.back.competitionApplication.entity.Competition;
import com.hongzhi.zswh.back.competitionApplication.entity.CompetitionApplication;
import com.hongzhi.zswh.back.competitionApplication.entity.Controls;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.page.PageModel;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 24, 2016    3:42:52 PM
 */
@Repository
public class FormService {

	@Autowired
	private FormDao formDao;
	@Autowired
	private ControlsDao controlsDao;
	@Autowired
	private CompetitionApplicationDao competitionDao;
	@Autowired
	private FormDataDao formDataDao;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	@Autowired
	private NotificationService notiSender;
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 24, 2016    3:43:22 PM
	 * @param params
	 * @return
	 */
	public String list(Map<String, String> params) {
		List<Integer>  userRole = formDao.userRole(Integer.parseInt(params.get("user_id")));
		// administrator 
		if(userRole.contains(8)){ 
			PageModel pageModel = new PageModel(params.get("page_number"), params.get("page_size"),params.get("language"),"/competitionApplication/list.htmls");
			pageModel.setOther(params.get("competition_name"));
			pageModel.setTotalDataCount(formDao.listFormByPageCount(pageModel));
			pageModel.setResult(formDao.listFormByPage(pageModel));
			return dictionaryUtil.appOut(params.get("code"), params.get("language"), pageModel);
		}else{
			// club manager 
			Map<String,Object>  clubManger = formDao.userLevel(Integer.parseInt(params.get("user_id")));
			if(!ObjectUtil.isEmpty(clubManger.get("user_level"))  ||  userRole.contains(9) ){
				PageModel pageModel = new PageModel(params.get("page_number"), params.get("page_size"),params.get("language"),"/competitionApplication/list.htmls");
				pageModel.setModel_object(clubManger);
				pageModel.setOther(params.get("competition_name"));
				pageModel.setTotalDataCount(formDao.listFormByPageCount(pageModel));
				pageModel.setResult(formDao.listFormByPage(pageModel));
				return dictionaryUtil.appOut(params.get("code"), params.get("language"), pageModel);
			}else{
				return "";
			}

		}
		
		
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    3:59:21 PM
	 * @param user
	 * @return
	 */
	public String newForm(SessionProperty sp) {
		List<Controls> controls_list = controlsDao.allControls();
		List<Competition> competition_list =  competitionDao.withoutForms(sp.getUser_id().toString());
		Map<String,Object > out = new HashMap<>() ;
		out.put("competition_list", competition_list);
		out.put("controls_list", controls_list);
		return dictionaryUtil.appOut("0", sp.getLanguage(), out);
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    4:17:57 PM
	 * @param user
	 * @param form_input 
	 * @return
	 */
	public String saveNewForm(SessionProperty sp , String form_input) { 
		if (insertForm(sp, form_input)) {
			return dictionaryUtil.appOut("0", sp.getLanguage(), "true");
		} else {
			return dictionaryUtil.appOut("1011", sp.getLanguage(), "false");
		}
		
	}
	
     public boolean insertForm(SessionProperty sp, String form_input) {
		// TODO Auto-generated method stub
		form_input=form_input.replaceAll(";;;;", ";;    ;;");
		form_input=form_input.replaceAll(";;\\$\\$", ";;    \\$\\$");
		if(form_input.endsWith(";;")){
			form_input+="    ";
		}
		String[] competition_items = form_input.split("\\$\\$");
		StringBuffer value_sql_buffer = new StringBuffer();
		String user_id = sp.getUser_id().toString();

		// 每条数据
		// competition_id ,title_name ,controls_id ,controls_data
		// ,controls_placeholder
		// ,user_id ,language_id ,create_time ,create_date ,controls_order
		List<Integer> int_list = new ArrayList<>();
		int_list.add(0);
		int_list.add(2);

		for (int i = 0; i < competition_items.length; i++) {
			String[] item_fields = competition_items[i].split(";;");
			if (i == 0) {
				value_sql_buffer.append("(");
			} else {
				value_sql_buffer.append(",(");
			}
			// 字段
			for (int j = 0; j < item_fields.length; j++) {
				if (j != 0) {
					value_sql_buffer.append(",");
				}
				if (int_list.contains(j)) {
					value_sql_buffer.append(item_fields[j]);
				} else {
					value_sql_buffer.append("'" + item_fields[j] + "'");
				}

			}
			value_sql_buffer.append("," + user_id);

			value_sql_buffer.append(")");
		}
		int insert_cnt=formDao.insertCompetitionApplication(value_sql_buffer.toString());
		
		if(insert_cnt>0){
			return  true;
		}else{
			return  false; 
		}
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    4:27:01 PM
	 * @param user
	 * @param competition_id
	 * @return
	 */
	public String delete(SessionProperty sp, String competition_id) {
		int delete_count = formDao.deleteByCompeitionId(Integer.parseInt(competition_id));
		if (delete_count > 0) {
			return dictionaryUtil.appOut("0", sp.getLanguage(), "true");
		} else {
			return dictionaryUtil.appOut("1034", sp.getLanguage(), "false");
		}
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    4:35:57 PM
	 * @param user
	 * @param competition_id
	 * @return
	 */
	public String formByCompetitionID(SessionProperty sp, String competition_id) {
		List<Controls> controls_list = controlsDao.allControls();
		List<CompetitionApplication> competition_application_list = formDao.selectByCompeitionID(Integer.parseInt(competition_id));
		Map<String,Object > out = new HashMap<>() ;
		out.put("competition_application_list", competition_application_list);
		out.put("controls_list", controls_list);
		out.put("competition_name", formDao.getCompetitionName(Integer.parseInt(competition_id)));
		return dictionaryUtil.appOut("0", sp.getLanguage(), out);
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    5:10:34 PM
	 * @param user
	 * @param form_input
	 * @return
	 */
	@Transactional
	public String updateForm(SessionProperty sp, String form_input) {
		String comp_id = form_input.substring(0,form_input.indexOf(";;"));
		formDao.deleteByCompeitionId(Integer.parseInt(comp_id));
		insertForm(sp, form_input);
		return dictionaryUtil.appOut("0", sp.getLanguage(), "");
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    5:26:33 PM
	 * @param user
	 * @param competition_id
	 * @param page_number
	 * @param page_size
	 * @param search 
	 * @return
	 */
	public String eyeFrom(SessionProperty sp, String competition_id, String page_number, String page_size, String search,String search_category) {
		List<Integer>  userRole = formDao.userRole(Integer.parseInt(sp.getUser_id()));
		// administrator 
		if(userRole.contains(8)){
				PageModel pageModel=new PageModel(page_number, page_size);
				pageModel.setOther(competition_id);
				Map<String,String> searchMap = new HashMap<>();
				if(!ObjectUtil.isEmpty(search) &&  !"null".equals(search)){
					searchMap.put("search", search);
				}
				if(!ObjectUtil.isEmpty(search_category)  &&  !"null".equals(search_category) ){
					 searchMap.put("category", " a.user_competition_status ='"+search_category+"'  "); 
//					switch (search_category) {
//					// 1:审核中   
//					case "1": searchMap.put("category", " b.user_competition_status ='1'  "); break;
//					// 99:己报名
//					case "99": searchMap.put("category", " b.user_competition_status ='99'  "); break;
//
//					default: break;
//					}
				}
				pageModel.setModel_object(searchMap);
				List<String > title_name_list = formDataDao.formTitles(Integer.parseInt(competition_id));
//				 user_id 
//	    		    ,coalesce(max(case when title_name = '姓名'  then  user_value  else null  end ),'') as col1 
//	    		    ,coalesce(max(case when title_name = '性別'  then  user_value  else  null  end ),'') as col2 
//	    		    ,coalesce(max(case when title_name = '爱好'  then  user_value  else  null  end ),'') as col3 
//	    		    ,coalesce(max(case when title_name = '报名地区'  then  user_value  else null end ),'')  as col4 
//	    		    ,coalesce(max(case when title_name = '所在地区'  then  user_value  else null end ),'') as col5 
//	    		    ,coalesce(max(case when title_name = '現居地'  then  user_value  else null  end  ),'') as col6  
				String fields="";
				for(int i=0;i<title_name_list.size();i++){
					fields+=",coalesce(max(case when title_name = '"
							+title_name_list.get(i)
							+"'  then  user_value  else null  end ),'') as col"+(i+1);
				}
		
				pageModel.setUrl(fields);
				pageModel.setLanguage(sp.getLanguage());
				pageModel.setTotalDataCount(formDataDao.formDataCount(pageModel));
				pageModel.setResult(formDataDao.formData(pageModel));
				pageModel.setUrl("/competitionApplication/eye.htmls");
				pageModel.setPageParam(Arrays.asList("competition_id","search","search_category"));
				pageModel.setPageParamVal(Arrays.asList(competition_id,search,search_category));
				pageModel.setPageString(pageModel.generatePageStr());
//				pageModel.setPageString("");
		
				Map<String,Object> out = new HashMap<>();
				out.put("competition_id", competition_id);
				out.put("title_name",title_name_list);
				out.put("pageModel", pageModel);
				return dictionaryUtil.appOut("0", sp.getLanguage(), out);
		}else{
			// club manager 
				Map<String,Object>  clubManger = formDao.userLevel(Integer.parseInt(sp.getUser_id()));
				if(!ObjectUtil.isEmpty(clubManger.get("user_level"))  ||  userRole.contains(9)  ){
						PageModel pageModel=new PageModel(page_number, "10000");
						pageModel.setOther(competition_id);
						if(!ObjectUtil.isEmpty(search)){
							clubManger.put("search", search);
						}
						List<String > title_name_list = formDataDao.formTitles(Integer.parseInt(competition_id));
//						 user_id 
//	    				    ,coalesce(max(case when title_name = '姓名'  then  user_value  else null  end ),'') as col1 
//	    				    ,coalesce(max(case when title_name = '性別'  then  user_value  else  null  end ),'') as col2 
//	    				    ,coalesce(max(case when title_name = '爱好'  then  user_value  else  null  end ),'') as col3 
//	    				    ,coalesce(max(case when title_name = '报名地区'  then  user_value  else null end ),'')  as col4 
//	    				    ,coalesce(max(case when title_name = '所在地区'  then  user_value  else null end ),'') as col5 
//	    				    ,coalesce(max(case when title_name = '現居地'  then  user_value  else null  end  ),'') as col6  
						String fields="";
						for(int i=0;i<title_name_list.size();i++){
							fields+=",coalesce(max(case when title_name = '"
									+title_name_list.get(i)
									+"'  then  user_value  else null  end ),'') as col"+(i+1);
						}
		
						pageModel.setModel_object(clubManger);
						pageModel.setUrl(fields);
						pageModel.setLanguage(sp.getLanguage());
						pageModel.setTotalDataCount(formDataDao.formDataCount(pageModel));
						pageModel.setResult(formDataDao.formData(pageModel));

						pageModel.setUrl("/competitionApplication/eye.htmls");
						pageModel.setPageParam(Arrays.asList("competition_id","search"));
						pageModel.setPageParamVal(Arrays.asList(competition_id,search));
						pageModel.setPageString(pageModel.generatePageStr());
		
						Map<String,Object> out = new HashMap<>();
						out.put("competition_id", competition_id);
						out.put("title_name",title_name_list);
						out.put("pageModel", pageModel);
						return dictionaryUtil.appOut("0", sp.getLanguage(), out);
				}else{
					return "";
				}
		}
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 29, 2016    10:57:50 AM
	 * @param competition_id
	 * @param user_id
	 * @param sp 
	 * @return
	 * @throws HongZhiException 
	 * @throws NumberFormatException 
	 */
	public String eyeApply(String competition_id, String user_id, SessionProperty sp) throws  HongZhiException {
		List<Integer> user_id_list = new ArrayList<>();
		String[] user_id_array = user_id.split(",");
		for(int i=0;i<user_id_array.length;i++){
			user_id_list.add(Integer.parseInt(user_id_array[i]));
		}
		int update_count = competitionDao.userCompetitionApply(Integer.parseInt(competition_id),user_id_list);
		if(update_count == user_id_array.length) {
			String compName=competitionDao.getCompetitionName(Integer.parseInt(competition_id));
			notiSender.sendNoti(Integer.parseInt(sp.getUser_id()), user_id_list ,null, "1" , "尊敬的用户，您的"+compName+"赛事报名已通过。");
			return dictionaryUtil.appOut("0", "" , "true");
		}else{
			return dictionaryUtil.appOut("1033", "" , "false");
		}
			
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 29, 2016    3:32:22 PM
	 * @param competition_id
	 * @param user_id
	 * @param reason
	 * @param sp 
	 * @return
	 * @throws HongZhiException 
	 * @throws  
	 */
	@Transactional
	public String eyeRefuse(String competition_id, String user_id, String reason, SessionProperty sp) throws  HongZhiException {
		List<Integer> user_id_list = new ArrayList<>();
		String[] user_id_array = user_id.split(",");
		for(int i=0;i<user_id_array.length;i++){
			user_id_list.add(Integer.parseInt(user_id_array[i]));
		}
		int update_count = competitionDao.userCompetitionRefuse(Integer.parseInt(competition_id),user_id_list);
		formDataDao.deleteRefuseData(Integer.parseInt(competition_id),user_id_list);
		if(ObjectUtil.isEmpty(reason)){
			reason="null";
		}
		competitionDao.userRefuseReason(Integer.parseInt(competition_id),user_id_list,reason);
		if(update_count == user_id_array.length) {
			String compName=competitionDao.getCompetitionName(Integer.parseInt(competition_id));
			notiSender.sendNoti(Integer.parseInt(sp.getUser_id()), user_id_list ,null, "1" , "尊敬的用户，很抱歉，您的"+compName+"赛事报名未通过。"+ (ObjectUtil.isEmpty(reason)?"":"原因为"+reason) );
			return dictionaryUtil.appOut("0", "" , "true");
		}else{
			return dictionaryUtil.appOut("1033", "" , "false");
		}
	}
	
	
	

}
