package com.hongzhi.zswh.app_v3.me.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.app_v3.me.dao.AppMeV3Dao;
import com.hongzhi.zswh.app_v3.me.entity.FollowersEntity;
import com.hongzhi.zswh.app_v3.me.entity.FollowingEntity;
import com.hongzhi.zswh.app_v3.me.entity.OutputEntity;
import com.hongzhi.zswh.app_v3.notification.dao.NotificationUserStateDao;
import com.hongzhi.zswh.app_v3.notification.entity.NotificationEntity;
import com.hongzhi.zswh.app_v3.notification.service.NotificationService;
import com.hongzhi.zswh.back.basic.dao.UserDao;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 7, 2016    6:37:11 PM
 */
@Service
public class AppMeV3Servce {
	
	@Autowired
	private AppMeV3Dao appMeDao;
	
	@Autowired
	private NotificationUserStateDao notificationUserStateDao;
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 7, 2016    6:40:10 PM
	 * @param properties
	 * @return
	 */
	public Object loadMe(SessionProperty properties) {
		int user_id=Integer.parseInt(properties.getUser_id());
		int platform_id = Integer.parseInt(properties.getPlatform());
		String language=properties.getLanguage();
		OutputEntity out =appMeDao.loadMe(user_id,platform_id,language);
		return out;	
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : May 5, 2016    1:02:10 PM
	 * @param properties
	 * @return
	 */
	public Object following(SessionProperty properties) {
		List<FollowingEntity>  list = appMeDao.following(Integer.parseInt(properties.getUser_id())); 
		return ObjectUtil.packMap(new String[] {"followingList"},new Object[]{list});
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : May 5, 2016    3:35:57 PM
	 * @param properties
	 * @return
	 */
	public Object followers(SessionProperty properties) {
		List<FollowersEntity>  list = appMeDao.followers(Integer.parseInt(properties.getUser_id())); 
		return ObjectUtil.packMap(new String[] {"followersList"},new Object[]{list});
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : May 5, 2016    4:14:50 PM
	 * @param properties
	 * @param follow_user_id
	 * @return
	 * @throws HongZhiException 
	 */
	public Object follow(SessionProperty properties, String follow_user_id) throws HongZhiException {
		int insert_count = appMeDao.follow(Integer.parseInt(follow_user_id),Integer.parseInt(properties.getUser_id()));
		if(insert_count==1){
			/**
			 * @author Saxon 
			 * add notification
			 * date time:20160508
			 * */
			List<FollowingEntity> list = appMeDao.findFollowUserInfoById(Integer.parseInt(properties.getUser_id()));
			
			NotificationEntity notificationEntity = new NotificationEntity();
			notificationEntity.setNoti_from(1);
			notificationEntity.setNoti_to(Integer.parseInt(follow_user_id));
			notificationEntity.setNoti_category("1");
			notificationEntity.setNotification_body(list.get(0).getUser_name() + "关注了您。");
			
			notificationUserStateDao.saveNewNoti(notificationEntity);
			return "success";
		}else{
			throw new HongZhiException("1041");
		}
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : May 5, 2016    4:14:56 PM
	 * @param properties
	 * @param unfollow_user_id
	 * @return
	 * @throws HongZhiException 
	 */
	public Object unfollow(SessionProperty properties, String unfollow_user_id) throws HongZhiException {
		int delete_count = appMeDao.unfollow(Integer.parseInt(unfollow_user_id),Integer.parseInt(properties.getUser_id()));
		if(delete_count==1){
			return "success";
		}else{
			throw new HongZhiException("1041");
		}
	}

}
