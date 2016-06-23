package com.hongzhi.zswh.app_v3.club.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v3.club.entity.ClubParam;
import com.hongzhi.zswh.app_v3.club.service.V3ClubService;
import com.hongzhi.zswh.app_v3.club.service.V3SportsCampService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 26, 2016    1:32:15 PM
 */
@Controller
@RequestMapping("/appClub_V3")
public class V3ClubController {
	
	
	@Autowired
	private V3ClubService club;
	@Autowired
	private DictionaryUtil dic;
	@Autowired
	private SessionUtil sess;
	@Autowired
	private V3SportsCampService sportsCamp;
	
	
	@ResponseBody
	@RequestMapping("/loadClubManage")
	public String loadClubManage ( HttpSession session ,ClubParam clubParam ){
			SessionProperty properties ;
		try {
			properties = sess.sessionEffective(session ,clubParam.getSession_id(), "load club manage ");
			return ObjectUtil.jsonOut(club.loadClubManage(properties));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOut(club.loadClubManageNotLogIn());
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/loadClubRank")
	public String  loadCubRanking (HttpSession session, ClubParam clubParam) {
			SessionProperty properties ;
			String language = clubParam.getLanguage();
		try {
			properties = sess.sessionEffective(session , clubParam.getSession_id(), "load club rank");
			return ObjectUtil.jsonOut(club.loadClubRank(properties));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOut(club.loadClubRankNotLogIn());
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/loadClubMembers")
	public String loadClubMembers (HttpSession session, ClubParam clubParam) {
			SessionProperty properties ;
			String language = clubParam.getLanguage();
		try {
			properties = sess.sessionEffective(session,clubParam.getSession_id(), "load club members ");
			return ObjectUtil.jsonOut(club.clubMembers(properties,clubParam.getPage()));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	
	}
	
	@ResponseBody 
	@RequestMapping("/clubMembersFocus")
	public String memberFollow ( HttpSession session, ClubParam clubParam ) {
			SessionProperty properties ;
			String language = clubParam.getLanguage();
		try {
			properties = sess.sessionEffective(session,clubParam.getSession_id(), " club members focus ");
			return ObjectUtil.jsonOut(club.memberFollow(properties,clubParam.Vis_focus(),clubParam.Vclub_user_id()));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/loadClubMembersAudit")
	public String loadMembersAudit (HttpSession session, ClubParam clubParam ){
		SessionProperty properties ;
		String language = clubParam.getLanguage();
		try {
			properties = sess.sessionEffective(session,clubParam.getSession_id(), " club members audit ");
			language = properties.getLanguage();
			return ObjectUtil.jsonOut(club.loadClubAudit(properties,clubParam.Vclub_id() , clubParam.getPage() ));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	@ResponseBody
	@RequestMapping("/clubMembersJoinClub")
	public String joinClub (HttpSession session , ClubParam clubParam){
		SessionProperty properties ;
		String language = clubParam.getLanguage();
		try {
			properties = sess.sessionEffective(session,clubParam.getSession_id(), " club members join club ");
			language = properties.getLanguage();
			return ObjectUtil.jsonOut(club.clubJoin(properties,clubParam.Vclub_id() , clubParam.Vclub_user_id() ,clubParam.Vjoin_club_status(),clubParam));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	@ResponseBody
	@RequestMapping("/clubMembersOutOfClub")
	public String quitClub (HttpSession session, ClubParam clubParam ) {
		SessionProperty properties ;
		String language = clubParam.getLanguage();
		try {
			properties = sess.sessionEffective(session, clubParam.getSession_id() ," club members quit club ");
			language = properties.getLanguage();
			return ObjectUtil.jsonOut(club.clubQuit(properties,clubParam.Vclub_id() ));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/saveUserClub")   // modify from v1
	public String saveUserSportCampAndClub(HttpSession session, ClubParam clubParam){
//		String  param_name="user_id,language_abbreviation,platform_id,sports_camp_id,club_id";
		SessionProperty properties ;
		String language = clubParam.getLanguage();
		try {
			properties = sess.sessionEffective(session,clubParam.getSession_id() , " load Sprots camp  ");
			language = properties.getLanguage();
			return ObjectUtil.jsonOut( club.saveUserClub(properties,clubParam.Vclub_id())  );
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	
	////////////////////////////////////////////sports camp 
	
	
	@ResponseBody
	@RequestMapping("/loadSportsCamp")
	public String loadSportsCamp (HttpSession session , ClubParam clubParam) {
		SessionProperty properties ;
		String language = clubParam.getLanguage();
		try {
			properties = sess.sessionEffective(session,clubParam.getSession_id() , " load Sprots camp  ");
			language = properties.getLanguage();
			return ObjectUtil.jsonOut( sportsCamp.loadSportsCamp(properties,clubParam.Vclub_id())  );
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	@ResponseBody
	@RequestMapping("/subscribeSportsCamp")
	public String subscribeSportsCamp (HttpSession session ,ClubParam clubParam ,String sport_camp_id, String subscribe_flag ) {
		SessionProperty properties ;
		String language = clubParam.getLanguage();
		try {
			properties = sess.sessionEffective(session,clubParam.getSession_id(), " subscribe Sprots camp  ");
			language = properties.getLanguage();
			return ObjectUtil.jsonOut( sportsCamp.subscirbe(properties,sport_camp_id,subscribe_flag)  );
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	

	@ResponseBody
	@RequestMapping("/clubCompetitionRank")
	public String scoreindex(HttpSession session,String session_id ,String competition_id,String club_search){
//		SessionProperty sp ;
		String language = "zh";
		try {
//			sp=sess.sessionEffective(session, session_id , "app v3 club clubCompetitionRank");
//			language=sp.getLanguage();
			return  ObjectUtil.jsonOut(club.scoreRankIndex(competition_id,club_search));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	

}
