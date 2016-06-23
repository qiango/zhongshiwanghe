package com.hongzhi.zswh.util.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hongzhi.zswh.util.basic.sessionDao.SessionAttribute;
import com.hongzhi.zswh.util.basic.sessionDao.SessionDao;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.basic.sessionDao.SessionService;
import com.hongzhi.zswh.util.basic.sessionDao.SessionTime;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 5, 2016    4:57:52 PM
 */
@Repository
public class SessionUtil {
	
	@Autowired
	private SessionDao sessionDao;
	@Autowired
	private SessionService sessionBiz;

	//        session table -> id  ,  session attribute 
	private static Map<Integer,SessionProperty> attribute;
	
	private static Map<Integer,SessionTime> sess;
	
	private  int  sessionMaxSize = 10000 ;
	
// 后台可用 session , app 用sessionId	
	public SessionProperty sessionEffective(HttpSession session,String session_id ,String logContent) throws HongZhiException{
		Object obj = session.getAttribute("id");
		
		if(ObjectUtil.isEmpty(sess) || ObjectUtil.isEmpty(attribute) ){
			refreshSession();
			refreshAttribute();
		}
		if( null != sess  &&  sess.size() > sessionMaxSize ){
			refreshSession();
			refreshAttribute();
		}
		if(ObjectUtil.isEmpty(obj)){
			if(ObjectUtil.isEmpty(session_id)){
				throw new HongZhiException("1000");
			}else{
				obj = session_id;
			}
		}
		if(!obj.toString().matches("\\d+")){
				throw new HongZhiException("1000");
		}
		int id = Integer.parseInt(obj.toString());
		return sessionBiz.sessionEffective(id, logContent);
	}
	
	
	
	@Transactional
	public int createNewSession(String session_id,List<String> attribute_string){
		//  session 
		SessionTime st = new SessionTime();
		st.setSession_id(session_id);
		int id = sessionDao.createSession(st);

		// attribute 
		List<SessionAttribute> attributes = new ArrayList<>();
		for(int i=0;i<attribute_string.size();i++){
			String[] attr_array = attribute_string.get(i).split(",");
			SessionAttribute sa = new SessionAttribute();
			sa.setId(st.getId());
			sa.setKey_name(attr_array[0]);
			sa.setValue_content(attr_array[1]);
			attributes.add(sa);
		}
		sessionDao.insertAttribute(attributes);
		refreshSession();
		refreshAttribute();
		return st.getId();
	}
	
	
	
	
	
	public boolean  usable(HttpSession session,String function_name) throws HongZhiException{
		if(ObjectUtil.isEmpty(session.getAttribute("id"))){
			throw new HongZhiException("1000"); // not login 
		}
		int s_id = Integer.parseInt(session.getAttribute("id").toString()); 
		int session_count = sessionDao.sessionUsable(s_id);
		if(session_count==0){
			throw new HongZhiException("1042");  // session timeout
		}else{
			sessionDao.updateSessionTime(s_id,"  INTERVAL 1 day  ");
			sessionDao.saveSessionLog(s_id,function_name);
			return true;
		}
	}

	
	


	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 5, 2016    6:33:13 PM
	 * @param attribute
	 * @return
	 * @throws HongZhiException 
	 */
	public SessionProperty getProperty(HttpSession session,String function_name) throws HongZhiException {
		usable(session,function_name);
		int s_id = Integer.parseInt(session.getAttribute("id").toString()); 
		SessionProperty sessionProperty = sessionDao.getProperty(s_id);
		return sessionProperty;
	}
	
	public void removeSessionAndAttribute(int id){
		SessionUtil.sess.remove(id);
		SessionUtil.attribute.remove(id);
	}

	public void refreshAttribute() {
		SessionUtil.attribute = sessionBiz.listSessionAttribute();
	}
	public void refreshSession(){
		SessionUtil.sess = sessionBiz.listSession();
	}

	
	public SessionTime getSession(int id){
		if(ObjectUtil.isEmpty(sess) || ObjectUtil.isEmpty(attribute) ){
			refreshSession();
			refreshAttribute();
		}
		return  SessionUtil.sess.get(id);
	}



	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 26, 2016    6:22:00 PM
	 * @param id
	 * @return
	 */
	public SessionProperty getAttribute(int id) {
		if(ObjectUtil.isEmpty(sess) || ObjectUtil.isEmpty(attribute) ){
			refreshSession();
			refreshAttribute();
		}
		return SessionUtil.attribute.get(id);
	}



	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 26, 2016    6:22:52 PM
	 * @param sessMem
	 */
	public void setSession(SessionTime sessMem) {
		SessionUtil.sess.put(sessMem.getId(), sessMem);
	}



	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 26, 2016    6:27:46 PM
	 * @param id
	 * @param attributeMap
	 */
	public void setAttribute(Integer id, Map<String, String> attributeMap) {
		SessionProperty sessionProperty = new SessionProperty();
		sessionProperty.setId(Integer.parseInt(attributeMap.get("id")));
		sessionProperty.setLanguage(attributeMap.get("language"));
		sessionProperty.setPlatform(attributeMap.get("platform"));
		sessionProperty.setUser_id(attributeMap.get("user_id"));
		sessionProperty.setUser_real_name(attributeMap.get("user_real_name"));
		SessionUtil.attribute.put(sessionProperty.getId(), sessionProperty);
	}



	

}
