package com.hongzhi.zswh.app_v3.club.service;


import com.hongzhi.zswh.app_v3.club.dao.V3ClubAuditDao;
import com.hongzhi.zswh.app_v3.club.dao.V3ClubManageDao;
import com.hongzhi.zswh.app_v3.club.entity.ClubManageEntity;
import com.hongzhi.zswh.app_v3.club.entity.ClubParam;
import com.hongzhi.zswh.app_v3.club.entity.ClubQueryEntity;
import com.hongzhi.zswh.app_v3.notification.service.NotificationService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 26, 2016    1:32:30 PM
 */
@Service
public class V3ClubService {
	
	@Autowired
	private V3ClubManageDao  dao ;
	@Autowired
	private V3ClubAuditDao  auditDao;
	@Autowired
	private DictionaryUtil dictionaryUtil;


	private int defaultPageSize=30;
	
	
	@Autowired
	private NotificationService notiSender;
	

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 26, 2016    1:38:40 PM
	 * @return
	 * @throws HongZhiException 
	 */
	public Object loadClubManage(SessionProperty properties) throws HongZhiException {
		
		ClubQueryEntity  clubInfo = dao.clubInfo( Integer.parseInt(properties.getUser_id()),properties.getLanguage());
		if(ObjectUtil.isEmpty(clubInfo)){
			clubInfo = new ClubQueryEntity();
		}
		ClubManageEntity out = new ClubManageEntity();
		String defaultVal = "0";
		out.setClub_member_number(ObjectUtil.getProperty(clubInfo.getClub_member(), defaultVal).toString());
		out.setSports_camp_number(ObjectUtil.getProperty(clubInfo.getSports_camp(), defaultVal).toString());
		out.setJoin_club_status(ObjectUtil.getProperty(clubInfo.getJoin_club_status(), defaultVal).toString());
		out.setJoin_club_status_name(ObjectUtil.getProperty(clubInfo.getJoin_club_status_name(), "").toString());
		out.setActivity_number(defaultVal);
		out.setComplete_training_number(defaultVal);
		out.setContinuous_training_number(defaultVal);
		out.setTotal_training_number(defaultVal);
		out.setRanking(defaultVal);
		out.setComprehensive_integral(defaultVal);
		out.setMy_club_ranking(clubInfo.getMy_club_rank());
		out.setClub(dao.userClub(Integer.parseInt(properties.getUser_id())));
		String club_id ="0";
		if(!ObjectUtil.isEmpty(clubInfo.getClub_id())){
			club_id=clubInfo.getClub_id();
		}
		out.setClub_ranking_list(dao.clubRanking("   limit 3   " ,Integer.parseInt(club_id) ));
		Map<String,Object> map = new HashMap<>();
		map.put("club_interface_data", out);
		return map;
	}
	
	public Object loadClubManageNotLogIn() {
		
		ClubManageEntity out = new ClubManageEntity();
		String defaultVal = "0";
		out.setClub_member_number(defaultVal);
		out.setSports_camp_number(defaultVal);
		out.setJoin_club_status(defaultVal);
		out.setJoin_club_status_name(defaultVal);
		out.setActivity_number(defaultVal);
		out.setComplete_training_number(defaultVal);
		out.setContinuous_training_number(defaultVal);
		out.setTotal_training_number(defaultVal);
		out.setRanking(defaultVal);
		out.setComprehensive_integral(defaultVal);
		out.setMy_club_ranking(defaultVal);
		out.setClub(null);
		out.setClub_ranking_list(dao.clubRanking("   limit 3   " ,0));
		Map<String,Object> map = new HashMap<>();
		map.put("club_interface_data", out);
		return map;
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    10:16:47 AM
	 * @param properties
	 * @return
	 */
	public Object loadClubRank(SessionProperty properties) {
		Map<String,Object>  userClub = dao.userClub(Integer.parseInt(properties.getUser_id()));
		String club_id = "0";
		if(!ObjectUtil.isEmpty(userClub) &&  !ObjectUtil.isEmpty(userClub.get("club_id"))){
			club_id=userClub.get("club_id").toString();
		}
		Map<String,Object> out = new HashMap<>();
		out.put("club_ranking_list", dao.clubRanking(null,Integer.parseInt(club_id)) );
		return out;
	}

	public Object loadClubRankNotLogIn( ) {
		
		Map<String,Object> out = new HashMap<>();
		out.put("club_ranking_list", dao.clubRanking(null,0) );
		return out;
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    10:34:54 AM
	 * @param properties
	 * @param page 
	 * @return
	 */
	public Object clubMembers(SessionProperty properties, String page) {
		int startRow = 0;
		if(!ObjectUtil.isEmpty(page)){
			startRow = (Integer.parseInt(page) -1 ) * defaultPageSize ;
		}
		Map<String,Object> club = dao.ownClubMemberCount(Integer.parseInt(properties.getUser_id()));
		String memberCount  = club.get("club_member").toString();
		List<Map<String,Object>>   club_member = dao.ownClubMemberList((Integer)club.get("user_id"),(Integer)club.get("club_id"),startRow,defaultPageSize,properties.getLanguage());
		
		Map<String,Object> out = new HashMap<>();
		out.put("members_audit_member", memberCount);
		out.put("manager_flag",  ObjectUtil.getProperty(club.get("user_level") ,"99"));
		out.put("club_member_list", club_member);
		return out;
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    11:42:00 AM
	 * @param properties
	 * @param vis_focus
	 * @param club_user_id 
	 * @return
	 * @throws HongZhiException 
	 */
	public Object memberFollow(SessionProperty properties, String vis_focus, String club_user_id) throws HongZhiException {
		int data_count = 0;
		switch (vis_focus) {
		case "0": // follow 
           data_count = dao.follow(Integer.parseInt(club_user_id),Integer.parseInt(properties.getUser_id().toString()) );
			break;
		case "1" :  // unfollow
           data_count = dao.unfollow(Integer.parseInt(club_user_id),Integer.parseInt(properties.getUser_id().toString()) );
			break;
		default:
			break;
		}
		if(data_count == 1 ){
			return  data_count;
		}else{
			throw new HongZhiException("1041");
		}
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    1:24:54 PM
	 * @param properties
	 * @param vclub_id
	 * @param page 
	 * @return
	 * @throws HongZhiException 
	 */
	public Object loadClubAudit(SessionProperty properties, String vclub_id, String page) throws HongZhiException {
		int startRow = 0;
		if(!ObjectUtil.isEmpty(page)){
			startRow = (Integer.parseInt(page) -1 ) * defaultPageSize ;
		}
		int loginUserRole = auditDao.userRole(Integer.parseInt(properties.getUser_id()));
		if(loginUserRole == 0 ){
			throw new HongZhiException("1044");
		}
		
		List<Map<String,Object>>  waitingForJoin = auditDao.waitingForJoin(Integer.parseInt(vclub_id),properties.getLanguage(), startRow ,defaultPageSize);
		
		Map<String,Object> out = new HashMap<>();
		out.put("club_member_list", waitingForJoin);
		return out;
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    2:35:12 PM
	 * @param properties
	 * @param vclub_id
	 * @param vclub_user_id
	 * @param vjoin_club_status
	 * @param clubParam 
	 * @return
	 * @throws HongZhiException 
	 */
	public Object clubJoin(SessionProperty properties, String vclub_id, String vclub_user_id,
			String vjoin_club_status, ClubParam clubParam) throws HongZhiException {
		String reason = null ;
		Integer reasonID = null;
		if(vjoin_club_status.equals("0") || vjoin_club_status.equals("97")){
			reason = clubParam.Vreason();
			vjoin_club_status = "97";
			reasonID = auditDao.refuseReason(reason);
		}
		
		int loginUserRole = auditDao.userRole(Integer.parseInt(properties.getUser_id()));
		if(loginUserRole == 0 ){
			throw new HongZhiException("1044");
		}

		List<String> userIdList = Arrays.asList(vclub_user_id.split(","));
		List<Integer> userId = new ArrayList<>();
		for(int i =0;i<userIdList.size();i++){
			userId.add(Integer.parseInt(userIdList.get(i)));
		}
		
		int effectiveCount = auditDao.apply(Integer.parseInt(vclub_id),vjoin_club_status,userId,reasonID);
		
		if(effectiveCount == userId.size()){
			String club_name = dao.getClubName(Integer.parseInt(vclub_id));
			if(vjoin_club_status.equals("0") || vjoin_club_status.equals("97")){
				notiSender.sendNoti(Integer.parseInt(properties.getUser_id()), userId ,null, "1" , "尊敬的用户，很抱歉，您申请加入"+club_name+"未通过。" + ( ObjectUtil.isEmpty(reason) ?"":"原因为:"+reason ) );
			}else if(vjoin_club_status.equals("99")){
				notiSender.sendNoti(Integer.parseInt(properties.getUser_id()), userId ,null, "1" , "尊敬的用户，您的俱乐部申请已通过，您现在是"+club_name+"的一员了。");
			}
			return "success";
		}else{
			throw new HongZhiException("1011");
		}
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    2:55:44 PM
	 * @param properties
	 * @param vclub_id
	 * @return
	 * @throws HongZhiException 
	 */
	public Object clubQuit(SessionProperty properties, String vclub_id) throws HongZhiException {
		
		int  effectiveCount = auditDao.updateUserDetail(Integer.parseInt(properties.getUser_id()),Integer.parseInt(vclub_id) , 98 );
			
		if(effectiveCount == 1){
			return "success";
		}else{
			throw new HongZhiException("1046");
		}
		
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : May 3, 2016    3:38:50 PM
	 * @param properties
	 * @param vclub_id
	 * @return
	 * @throws HongZhiException 
	 */
	public Object saveUserClub(SessionProperty properties, String vclub_id) throws HongZhiException {
		int insert_count = dao.saveUserClub(Integer.parseInt(properties.getUser_id()), Integer.parseInt(vclub_id));
		if (insert_count == 1 || insert_count == 2) {
			List<Integer> mgrId = dao.getClubMgrId(Integer.parseInt(vclub_id));
			String userName = dao.getUserName(Integer.parseInt(properties.getUser_id()));
			if (mgrId.size() > 0) {
				notiSender.sendNoti(Integer.parseInt(properties.getUser_id()), mgrId, null, "1", userName + dictionaryUtil.getCodeValue("noti_join_club", "data_alias", properties.getLanguage()));
			}
			List<Integer> club_members_list = dao.selectClubMembers(vclub_id);//根据俱乐部id查询俱乐部当前人数
			if (!ObjectUtil.isEmpty(club_members_list)) {
				int club_min_member = Integer.valueOf(dictionaryUtil.getCodeValue("club_min_member", "data_alias", properties.getLanguage()));
				if (club_members_list.size() >= club_min_member) {
					dao.updateClubStatusByClubId(vclub_id);//组建的俱乐部成员达到三个人（加入状态）时，改变club的状态（2筹备中--99启用）
				}
			}
			return "success";
		} else {
			throw new HongZhiException("1041");
		}
		
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : May 9, 2016    3:05:10 PM
	 * @param competition_id
	 * @param club_search
	 * @return
	 * @throws HongZhiException 
	 */
	public Object scoreRankIndex(String competition_id, String club_search) throws HongZhiException {
		if(ObjectUtil.isEmpty(competition_id)){
			throw new HongZhiException("1023");
		}
		String club_score_publish = dao.getPublishInfo(Integer.parseInt(competition_id));
		List<Map<String,Object>>  clubList = null;
		Map<String,Object> out = new HashMap<>();
		if(!ObjectUtil.isEmpty(club_score_publish) && club_score_publish.equals("1")){
          clubList = dao.clubScoreRankList(Integer.parseInt(competition_id),club_search);
		}
		out.put("clubScoreRankList", clubList);
		out.put("infoPublish",club_score_publish );
		return out;
	}

}
