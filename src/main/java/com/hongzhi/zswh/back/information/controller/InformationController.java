package com.hongzhi.zswh.back.information.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.basic.entity.User;
import com.hongzhi.zswh.back.information.entity.Information;
import com.hongzhi.zswh.back.information.entity.ParamInfo;
import com.hongzhi.zswh.back.information.service.InformationService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;
import org.springframework.ui.Model;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 22, 2016    3:35:25 PM
 */
@Controller
@RequestMapping("/information")
public class InformationController {
	
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private InformationService informationService;
	@Autowired
	private SessionUtil sessionUtil;
	
	@ResponseBody
	@RequestMapping("/list")
	public  String list(ParamInfo paramInfo , HttpSession session){
		String  param_name = "search_string,page_number,page_size";
		String   errorcode = "0,0,0";
		Map<String,String> params=new HashMap<>();
		try {
			params = dictionaryUtil.checkParams(param_name, errorcode, paramInfo, session , "information/list");
			return informationService.listInformationByPage(params);
		} catch (HongZhiException e) {
			return dictionaryUtil.appOut( e.getCode() , params.get("language_abbreviation") , null);
		}
	}


	@ResponseBody
	@RequestMapping("/insertLoad")
	public  String insertLoad(HttpSession session){
		try {
			SessionProperty sp = sessionUtil.getProperty(session, "information/insertLoad");
				return informationService.insertLoad(sp);
			} catch (HongZhiException e) {
				return dictionaryUtil.appOut( e.getCode() , null , null);
			}
	}
	
	@ResponseBody
	@RequestMapping("/insertSave")
	public  String insertSave(String submit_file,String language_abbreviation,Information information,HttpSession session){
		String competition_id=information.getCompetition_id().toString();
		String platform_id=information.getPlatform_id().toString();
		String information_abstract=information.getPlatform_id().toString();
		String information_content=information.getInformation_content();
		String information_status=information.getInformation_status();
		String information_title=information.getInformation_title();
		String information_type=information.getInformation_type();
		String remark=information.getRemark();
//		User user=(User) session.getAttribute("user");
		Map<String,String> params=new HashMap<>();
		SessionProperty sp = null;
		try {
			//验证是否已经登录
			sp = sessionUtil.getProperty(session, "information/insertSave/"+ObjectUtil.getProperty(information.getInformation_id(), "null"));
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
		String  param_name = "submit_file,competition_id,platform_id,information_type"
				           + ",information_title,information_abstract,information_content,information_status,remark";
		String[] param_ary = {submit_file,competition_id,platform_id,information_type
							 ,information_title,information_abstract,information_content,information_status,remark};
		String   errorcode = "0,1023,1024,0"
				           + ",0,0,0,0,0";
		try {
			params=dictionaryUtil.checkParams(param_name, param_ary, errorcode);
			return informationService.insertNewInformation(information,submit_file,sp);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	}
	
	@ResponseBody
	@RequestMapping("/modifyLoad")
	public String modifyLoad(ParamInfo paramInfo , HttpSession session) {
		String  param_name = "information_id";
		String   errorcode = "1035";
		Map<String,String> params=new HashMap<>();
		try {
			params = dictionaryUtil.checkParams(param_name, errorcode, paramInfo, session, "information/modifyLoad/"+ObjectUtil.getProperty(paramInfo.getInformation_id(), "null"));
			return informationService.modifyLoad(params);
		} catch (HongZhiException e) {
			params.put("code", e.getCode());
		}
		return dictionaryUtil.appOut( params.get("code"), params.get("language_abbreviation") , null);
	}
	
	@ResponseBody
	@RequestMapping("/modifySave")
	public  String modifySave(ParamInfo paramInfo,HttpSession session){
//		User user=(User) session.getAttribute("user");
//		String user_id=user.getUser_id().toString();
		String  param_name = "information_id,competition_id,platform_id,information_type"
						  + ",information_title,information_abstract,information_content,information_status,remark";
		String   errorcode = "1035,1023,1024,0"
						  + ",0,0,0,0,0";
		Map<String,String> params=new HashMap<>();
		try {
			params = dictionaryUtil.checkParams(param_name, errorcode, paramInfo, session, "information/modifySave/"+ObjectUtil.getProperty(paramInfo.getInformation_id(), "null"));
			return informationService.updateInfo(params);
		} catch (HongZhiException e) {
			return dictionaryUtil.appOut( e.getCode() , params.get("language_abbreviation") , null);
		}
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(ParamInfo paramInfo, HttpSession session) {
		String  param_name = "information_id";
		String   errorcode = "1035";
		Map<String,String> params=new HashMap<>();
		try {
			params = dictionaryUtil.checkParams(param_name, errorcode, paramInfo, session,"information/delete/"+ObjectUtil.getProperty(paramInfo.getInformation_id(), "null"));
			return informationService.logicDelete(params);
		} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode() , params.get("language_abbreviation") , null);
		}
	}
	
	
	@RequestMapping("/index")
	public  String  toIndex(Model model ,String information_id) {
		return informationService.informationDetail(information_id,model);
	}
	
}
