package com.hongzhi.zswh.app_v3.notification.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.app_v3.notification.entity.NotificationEntity;
import com.hongzhi.zswh.app_v3.notification.service.NotificationService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.date.DateFormat;
import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 3, 2016    4:41:56 PM
 */
@Controller
@RequestMapping("/notification_V3")
public class NotificationController {
	
	@Autowired
	private NotificationService notiSrv;
	@Autowired
	private DictionaryUtil dic;
	@Autowired
	private SessionUtil sess;
	
	
	@ResponseBody
	@RequestMapping("/categoryList")
	public  String  category(){
		return ObjectUtil.jsonOut( notiSrv.cagtegoryList() );
	}
	
	@ResponseBody
	@RequestMapping("/userNotiList")
	public  String  userState(HttpSession session, String session_id){
		SessionProperty properties ;
		String language = "zh";
		try {
			properties = sess.sessionEffective(session,session_id , " notification_V3  userList user_id:");
			language = properties.getLanguage();
			return ObjectUtil.jsonOut( notiSrv.userNotiList(properties.getUser_id()) );
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}

	@ResponseBody
	@RequestMapping("/saveNoti")
	public  String  save(HttpSession session ,String session_id, NotificationEntity notificationEntity ,String receiver_user){
		SessionProperty properties ;
		String language = "zh";
		try {
			properties = sess.sessionEffective(session,session_id , " notification_V3  save notification ");
			language = properties.getLanguage();
			notificationEntity.setNoti_from(Integer.parseInt(properties.getUser_id()));
			return ObjectUtil.jsonOut( notiSrv.saveNoti(notificationEntity,receiver_user) );
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	@ResponseBody
	@RequestMapping("/readNoti")
	public  String  read(HttpSession session  , String session_id ,String id, String category_id ,String search){
		SessionProperty properties ;
		String language = "zh";
		try {
			properties = sess.sessionEffective(session,session_id , " notification_V3  read  notification ");
			language = properties.getLanguage();
			return ObjectUtil.jsonOutDT( notiSrv.readNotification(properties.getUser_id(),id,category_id,search,properties.getLanguage()) ,DateFormat.getFormatWithTime(language));
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/deleteNoti")
	public  String  delete (HttpSession session  , String session_id ,String id){
		SessionProperty properties ;
		String language = "zh";
		try {
			properties = sess.sessionEffective(session,session_id , " notification_V3  delete  notification notification_id:"+id);
			language = properties.getLanguage();
			ExcepUtil.verify(id, "1053");
			return ObjectUtil.jsonOut( notiSrv.deleteNotification(properties.getUser_id(),id) );
		} catch (HongZhiException e) {
			return ObjectUtil.jsonOutError(e.getCode(), dic.getCodeValue(e.getCode(), language ) );
		}
	}

}
