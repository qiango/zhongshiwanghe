package com.hongzhi.zswh.back.sportsCamp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.basic.entity.User;
import com.hongzhi.zswh.back.sportsCamp.entity.ParamSc;
import com.hongzhi.zswh.back.sportsCamp.service.SportsCampService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;



/**	 Auther:enjun.zhu
 *  Creat time: Mar 24,2016		11:28:30 AM
 * */
@Controller
@RequestMapping("/sportscamp")
public class SportsCampController {
	
	@Autowired
	private DictionaryUtil dictionaryUtil;
	@Autowired
	private SportsCampService sportsCampService;
	@Autowired
	private SessionUtil sessionUtil;
	
	/**	 Auther:enjun.zhu
	 *  Creat time: Mar 24,2016		12:59:30 PM
	 * */
	//列表和查找
	@ResponseBody
	@RequestMapping("/list")
	public String listSportsCamp(ParamSc paramSc , HttpSession session){
		String  param_name = "sports_camp_name,page_number,page_size";
		String   errorcode = "0,0,0";
		Map<String,String> params=new HashMap<>();
		try {
			params = dictionaryUtil.checkParams(param_name, errorcode, paramSc, session, "sprotscamp/list");
			return sportsCampService.listByPage(params);
		} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode(), params.get("language_abbreviation") , null);
		}
	}
	
	/**	 Auther:enjun.zhu
	 *  Creat time: Mar 24,2016		13:24:30 PM
	 * */
	//插入读取
	@ResponseBody
	@RequestMapping("/insertLoad")
	public  String insertLoad(HttpSession session){
		try {
			SessionProperty sp =  sessionUtil.getProperty(session, "sprotscamp/insertLoad");
			return sportsCampService.insertLoad(sp);
		} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode(), null , null);
		}
	}
	/**	 Auther:enjun.zhu
	 *  Creat time: Mar 24,2016		13:24:30 PM
	 * */
	//插入保存
	@ResponseBody
	@RequestMapping("/insertSave")
	public  String insertSave(HttpSession session,ParamSc paramSc){
		String  param_name = "language_id,sports_camp_name,remark";
		String   errorcode = "0,0,0";
//		User user = (User) session.getAttribute("user") ;
		Map<String,String> params=new HashMap<>();
		try {
			params = dictionaryUtil.checkParams(param_name, errorcode,paramSc, session, "sportscamp/insertSave/"+ObjectUtil.getProperty(paramSc.getSports_camp_name(), "null"));
			return sportsCampService.insertSave(params);
		} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode() , params.get("language_abbreviation") , null);
		}
	}
	
	
	//删除
	@ResponseBody
	@RequestMapping("/delete")
	public  String delete(ParamSc paramSc, HttpSession session){
		String  param_name = "sports_camp_id";
		String   errorcode = "1023";
		Map<String,String> params=new HashMap<>();
		try {
			params = dictionaryUtil.checkParams(param_name, errorcode,paramSc, session, "sportscamp/delete/"+ObjectUtil.getProperty(paramSc.getSports_camp_id(), "null"));
			return sportsCampService.delete(params);
		} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode() , params.get("language_abbreviation") , null);
		}
	}
	
	//修改加载
	@ResponseBody
	@RequestMapping("/modifyLoad")
	public  String modifyLoad(ParamSc paramSc , HttpSession session){
		String  param_name = "sports_camp_id";
		String   errorcode = "1023";
		Map<String,String> params=new HashMap<>();
		try {
			params = dictionaryUtil.checkParams(param_name, errorcode,paramSc, session, "sprotscamp/modifyLoad/"+ObjectUtil.getProperty(paramSc.getSports_camp_id(),"null"));
			return sportsCampService.modifyLoad(params);
		} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode() , params.get("language_abbreviation") , null);
		}
	}

	//修改保存
	@ResponseBody
	@RequestMapping("/modifySave")
	public  String modifySave(ParamSc paramSc,HttpSession session){
//		User user = (User) session.getAttribute("user") ;
//		String user_id=user.getUser_id().toString();
		String  param_name = "sports_camp_id,language_id,sports_camp_name,remark";
		String   errorcode = "0,0,0,0";
		Map<String,String> params=new HashMap<>();
		try {
			params = dictionaryUtil.checkParams(param_name, errorcode, paramSc, session, "sprotscamp/modfiySave/"+ObjectUtil.getProperty(paramSc.getSports_camp_id(), "null"));
			return sportsCampService.modifySave(params);
		} catch (HongZhiException e) {
			return dictionaryUtil.appOut(e.getCode() , params.get("language_abbreviation") , null);
		}
	}
}
