package com.hongzhi.zswh.back.competitionApplication.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.basic.entity.User;
import com.hongzhi.zswh.back.competitionApplication.entity.ParamForm;
import com.hongzhi.zswh.back.competitionApplication.service.FormService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 24, 2016    3:28:26 PM
 */
@Controller
@RequestMapping("/competitionApplication")
public class FormController {
	
	@Autowired
	private DictionaryUtil dicUtil;
	@Autowired
	private FormService formService;
	@Autowired
	private SessionUtil sessionUtil;
	
	
	
	
	@ResponseBody
	@RequestMapping("/list")
	public String listFroms(ParamForm paramForm,HttpSession session){
//		User user=(User) session.getAttribute("user");
		String  param_name = "competition_name,page_number,page_size";
		String   errorcode = "0,0,0";
		Map<String,String> params=new HashMap<>();
		try {
				params=dicUtil.checkParams(param_name, errorcode,paramForm, session, "competitionApplication/list" );
				return formService.list(params);
			} catch (HongZhiException e) {
				return dicUtil.appOut( e.getCode() , params.get("language") , null);
			}
	}
	
	
	@ResponseBody
	@RequestMapping("/insertLoad")
	public String  formGenerate(HttpSession session){
//		User user=(User) session.getAttribute("user");
		try {
			SessionProperty sp =  sessionUtil.getProperty(session, "competitionApplication/insertLoad");
			return formService.newForm(sp);
		} catch (HongZhiException e) {
			return dicUtil.appOut(e.getCode(), "" , "");
		}
	}
	
	@ResponseBody
	@RequestMapping("/insertSave")
	public String registerFormSave( String form_input ,HttpSession session ){
//		User user=(User) session.getAttribute("user");
		try {
			SessionProperty sp =  sessionUtil.getProperty(session, "competitionApplication/insertSave");
			return formService.saveNewForm(sp,form_input);
		} catch (HongZhiException e) {
			return dicUtil.appOut(e.getCode(), "" , "");
		}
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public  String  competitionApplicationDelete(String competition_id, HttpSession session) { 
//			User user=(User) session.getAttribute("user");
			try {
				dicUtil.verifyData(competition_id, "1022");
				SessionProperty sp =  sessionUtil.getProperty(session, "competitionApplication/delete/"+competition_id);
				return formService.delete(sp,competition_id);
			} catch (HongZhiException e) {
				return dicUtil.appOut(e.getCode(), "", "");
			}
	}

	
	@ResponseBody
	@RequestMapping("/modifyLoad")
    public String modifyLoad(HttpSession session, String competition_id) {
//			User user=(User) session.getAttribute("user");
			try {
				dicUtil.verifyData(competition_id, "1023");
				SessionProperty sp =  sessionUtil.getProperty(session, "competitionApplication/modfiyLoad/"+competition_id);
				return formService.formByCompetitionID(sp,competition_id);
			} catch (HongZhiException e) {
				return dicUtil.appOut(e.getCode(), "" , "");
			}
	}
	
	@ResponseBody
	@RequestMapping("/modifySave")
	public String update(String form_input , HttpSession session) { 
//		User user=(User) session.getAttribute("user");
		try {
			SessionProperty sp =  sessionUtil.getProperty(session, "competitionApplication/update/");
			return formService.updateForm(sp,form_input);
		} catch (HongZhiException e) {
			return dicUtil.appOut(e.getCode(), "", "");
		}
	}

	
	@ResponseBody
	@RequestMapping("/eye")
    public String eyeLoad( String competition_id,  String page_number, String page_size ,String search,String search_category, HttpSession session) {
//		User user=(User) session.getAttribute("user");
		try {
			dicUtil.verifyData(competition_id, "1023");
			SessionProperty sp =  sessionUtil.getProperty(session, "competitionApplication/eye/"+competition_id);
			return formService.eyeFrom(sp,competition_id,page_number,page_size,search,search_category);
		} catch (HongZhiException e) {
			return dicUtil.appOut(e.getCode(), "" , "");
		}
	}
	
	@ResponseBody
	@RequestMapping("/apply")
	public  String  eyeApply (String competition_id ,  String  user_id  ,HttpSession session) {
		   SessionProperty sp ;
			try {
				sp =  sessionUtil.sessionEffective(session, null , "competitionApplication/apply/competition_id:"+competition_id);
//				sessionUtil.usable(session, "competitionApplication/apply/competition_id:"+competition_id+",user_id:"+user_id);
				dicUtil.verifyData(competition_id, "1023");
				dicUtil.verifyData(user_id, "1036");
				return  formService.eyeApply(competition_id,user_id,sp);
			} catch (HongZhiException e) {
			return 	dicUtil.appOut(e.getCode(), "" , "");
			}
		
	}
	
	@ResponseBody
	@RequestMapping("/refuse")
	public  String  refuse (String competition_id ,String  reason, String  user_id  ,HttpSession session) {
		   SessionProperty sp ;
			try {
				sp =  sessionUtil.sessionEffective(session, null , "competitionApplication/refuse /competition_id:"+competition_id);
//				sessionUtil.usable(session, "competitionApplication/apply/competition_id:"+competition_id+",user_id:"+user_id);
				dicUtil.verifyData(competition_id, "1023");
				dicUtil.verifyData(user_id, "1036");
				return  formService.eyeRefuse(competition_id,user_id,reason,sp);
			} catch (HongZhiException e) {
			return 	dicUtil.appOut(e.getCode(), "" , "");
			}
		
	}



	
	
	
}
