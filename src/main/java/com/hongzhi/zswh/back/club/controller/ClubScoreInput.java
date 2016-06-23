package com.hongzhi.zswh.back.club.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.club.entity.ClubScoreEntity;
import com.hongzhi.zswh.back.club.service.ClubScoreService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 9, 2016    10:31:00 AM
 */
@Controller
@RequestMapping("/clubScore")
public class ClubScoreInput {
	
	@Autowired
	private SessionUtil sess;
	@Autowired
	private DictionaryUtil dic;
	@Autowired
	private ClubScoreService  css;

	
	@ResponseBody
	@RequestMapping("/index")
	public String scoreindex(HttpSession session,String competition_id,String club_search){
		SessionProperty sp ;
		String language = "zh";
		try {
			sp=sess.sessionEffective(session, null, "clubScore/index");
			language=sp.getLanguage();
			return  ObjectUtil.jsonOut(css.scoreIndex(competition_id,club_search));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/input")
	public String scoreinput(HttpSession session,ClubScoreEntity clubScore){
		SessionProperty sp ;
		String language = "zh";
		try {
			sp=sess.sessionEffective(session, null, "clubScore/input");
			language=sp.getLanguage();
			return  ObjectUtil.jsonOut(css.scoreInput(clubScore));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	@ResponseBody
	@RequestMapping("/modify")
	public String scoreModify(HttpSession session,ClubScoreEntity clubScore){
		SessionProperty sp ;
		String language = "zh";
		try {
			sp=sess.sessionEffective(session, null, "clubScore/modfiy");
			language=sp.getLanguage();
			return  ObjectUtil.jsonOut(css.scoreModify(clubScore));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	@ResponseBody
	@RequestMapping("/publish")
	public String scorpublish(HttpSession session,ClubScoreEntity clubScore,String publish){
		SessionProperty sp ;
		String language = "zh";
		try {
			sp=sess.sessionEffective(session, null, "clubScore publish ");
			language=sp.getLanguage();
			return  ObjectUtil.jsonOut(css.scorePublish(clubScore,publish));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	

}
