package com.hongzhi.zswh.back.club.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.club.entity.ParamObj;
import com.hongzhi.zswh.back.club.service.JoinClubService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 24, 2016    2:47:02 PM
 */
@Controller
@RequestMapping("/userdetail")
public class JoinClubController {
	
	@Autowired
	private DictionaryUtil dicUtil;
	@Autowired
	private JoinClubService joinClubService;
	@Autowired 
	private SessionUtil sessionUtil;
	
	@ResponseBody
	@RequestMapping("/list")
	public String waitingForConfirmList(HttpSession session){
//		User user=(User) session.getAttribute("user");
		try {
			SessionProperty sp =  sessionUtil.getProperty(session, "userdetail/list, show join list ");
			return  joinClubService.waittingJoinList(sp);
		} catch (HongZhiException e) {
		return dicUtil.appOut( e.getCode(), null , null);
		}
		
	}
	
	
	@ResponseBody
	@RequestMapping("/agree")
	public String waitingForConfirmListApply(HttpSession session,ParamObj paramObj ){
//		User user=(User) session.getAttribute("user");
//		String  param_name = "club_id,user_id";
//		String   errorcode = "1021,1038";
//		Map<String,String> params=new HashMap<>();
		SessionProperty sp ;
		try {
//				params=dicUtil.checkParams(param_name, errorcode,paramObj,session,"userdetail/agree, agree to join club user:"+ObjectUtil.getProperty(paramObj.getUser_id(), "null"));
			   sp = sessionUtil.sessionEffective(session, null, "userdetail/agree  club agree ");

				return joinClubService.apply(sp,paramObj);
			} catch (HongZhiException e) {
				return ObjectUtil.jsonOutError(e.getCode(), "" );
			}
		
	}

}
