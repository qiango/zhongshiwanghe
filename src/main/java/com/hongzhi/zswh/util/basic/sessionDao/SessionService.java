package com.hongzhi.zswh.util.basic.sessionDao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;



/**   Twitter : @taylorwang789 
 * Creat time : Apr 14, 2016    11:27:47 AM
 */
@Service
public class SessionService {

	
	@Autowired
	private SessionDao  m00sess;
	@Autowired
	private SessionUtil sessionUtil;
	
	private  String   appSessionStr = " INTERVAL 7 day ";    //  1 day 
	private  int      appSessionInt = (3600*1000)*24*7 ;    //  milli second 
//	private  int      appSessionInt = 86400000 ;    //  milli second 

	private  String   sessionStr   = " INTERVAL  7 day  " ;
	private  int      sessionInt   = (3600*1000)*24*7  ;  // milli second
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 10, 2016    1:57:59 PM
	 * @param session_id
	 * @return
	 */
	public  int  createNewSession(String session_id,Map<String,String> attributeMap){

		SessionTime newSess = new SessionTime();
		newSess.setSession_id(session_id);

		// insert to db 
		m00sess.createSession(newSess);
		List<SessionAttribute> attributeList = new ArrayList<>();
		for( Entry<String, String> entry : attributeMap.entrySet() ){
			SessionAttribute sa = new SessionAttribute();
			sa.setId(newSess.getId());
			sa.setKey_name(entry.getKey());
			sa.setValue_content(entry.getValue());
			attributeList.add(sa);
		}
		m00sess.insertAttribute(attributeList);

		// set memory 
		sessionUtil.setSession(newSess);
		sessionUtil.setAttribute(newSess.getId(), attributeMap);
		return newSess.getId();
	}
	
	
	private void updateLastUsetime(int id){
		String platform = sessionUtil.getAttribute(id).getPlatform();
		String timeSql = "";
		int    timeMem = 0;
		if("1".equals(platform)){
			timeSql = sessionStr ;
			timeMem = sessionInt ;
		}else{
			timeSql = appSessionStr ;
			timeMem = appSessionInt ;
		}
		// update db 
		m00sess.updateSessionTime(id,timeSql);
		
		// update memory 
		SessionTime sessMem = sessionUtil.getSession(id);
		sessMem.setLast_use_time(new Timestamp(System.currentTimeMillis()));
		sessMem.setDue_time(new Timestamp(System.currentTimeMillis()+timeMem));
		sessionUtil.setSession(sessMem);
	}
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 10, 2016    1:58:15 PM
	 */
	public SessionProperty sessionEffective(int id,String logContent) throws HongZhiException{
		SessionTime currentSess = sessionUtil.getSession(id);
		if(ObjectUtil.isEmpty(currentSess)){
			throw new HongZhiException("1000",null);
		}else if(  currentSess.getDue_time().getTime() < System.currentTimeMillis() ){
			sessionUtil.removeSessionAndAttribute(id);
			throw new HongZhiException("1042",null);
		}else{
			m00sess.saveSessionLog(id,logContent);
			updateLastUsetime(id);
			return  sessionUtil.getAttribute(id);
		}
	}


	
	public  SessionProperty getSessionAttribute(int id) {
		return  m00sess.getProperty(id);
	}

	
	public Map<Integer, SessionTime> listSession() {
		List<SessionTime> sessions = m00sess.listSession();
		Map<Integer,SessionTime> smap = new HashMap<>();
		for(int i=0;i<sessions.size();i++){
			smap.put(sessions.get(i).getId(), sessions.get(i));
		}
		return smap;
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 26, 2016    6:00:44 PM
	 * @return
	 */
	public Map<Integer, SessionProperty> listSessionAttribute() {
		List<Map<String,Object>>  attrList = m00sess.listAttribute();
        List<Map<String,Object>>  userClubInfo = m00sess.clubInfo();

        List<Integer> userIds = new ArrayList<>();
        for (int i = 0; i < userClubInfo.size(); i++) {
            userIds.add(Integer.valueOf(userClubInfo.get(i).get("user_id").toString()));
        }

		Map<Integer,SessionProperty>  map = new HashMap<>();
		for(int i=0;i<attrList.size();i++){
			SessionProperty sessionProperty = new SessionProperty();
			sessionProperty.setId(Integer.parseInt(attrList.get(i).get("id").toString()));
			sessionProperty.setLanguage(attrList.get(i).get("language").toString());
			sessionProperty.setPlatform(attrList.get(i).get("platform").toString());
			sessionProperty.setUser_id(attrList.get(i).get("user_id").toString());
			sessionProperty.setUser_real_name(attrList.get(i).get("user_real_name").toString());

            if (userIds.contains(Integer.valueOf(attrList.get(i).get("user_id").toString()))) {
                Map<String,Object>  userClub = userClubInfo.get( userIds.indexOf(Integer.valueOf(attrList.get(i).get("user_id").toString())) );
                sessionProperty.setClub_id(Integer.valueOf(ObjectUtil.coalesce(userClub.get("club_id"),0).toString().replace("null","0")));
                sessionProperty.setClub_user_level(ObjectUtil.coalesce(userClub.get("club_user_level"),"").toString());
            } else {
                sessionProperty.setClub_id(0);
                sessionProperty.setClub_user_level("");
            }

//            sessionProperty.setClub_id(Integer.valueOf( ObjectUtil.coalesce(attrList.get(i).get("club_id"),0).toString().replace("null","0") ));
//            sessionProperty.setClub_user_level(attrList.get(i).get("club_user_level").toString());
			map.put(Integer.parseInt(attrList.get(i).get("id").toString()), sessionProperty);
		}
		return map;
	}

}
