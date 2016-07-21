package com.hongzhi.zswh.app_v3.notification.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hongzhi.zswh.util.mipush.message.MiMessage;
import com.hongzhi.zswh.util.mipush.message.MiMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongzhi.zswh.app_v3.notification.dao.NotificationUserStateDao;
import com.hongzhi.zswh.app_v3.notification.entity.NotificationEntity;
import com.hongzhi.zswh.app_v3.notification.entity.NotificationUserState;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 3, 2016    4:38:38 PM
 */
@Service
public class NotificationService {
	
	
	@Autowired
	private NotificationUserStateDao notiStateDao;

    @Autowired
    private MiMessageService miMessageService;

	

	/**   Twitter : @taylorwang789 
	 * Creat time : May 3, 2016    4:45:25 PM
	 * @param user_id
	 * @return
	 */
	public Object userNotiList(String user_id) {
		Map<String,Object>  userState = notiStateDao.userNotiList(user_id);
		NotificationUserState out = new NotificationUserState();
		if(!ObjectUtil.isEmpty(userState)){
			out.setUser_id( userState.get("user_id").toString() );
			out.setSystem( userState.get("system").toString() );
			out.setSubscribe( userState.get("subscribe").toString());
		}
		return out;
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : May 4, 2016    2:48:44 PM
	 * @param notificationEntity
	 * @return
	 * @throws HongZhiException 
	 */
	public Object saveNoti(NotificationEntity notificationEntity) throws HongZhiException {
		notificationEntity.VNoti_from();
		notificationEntity.VNoti_to();
		notificationEntity.VNoti_category();
		notificationEntity.VNotification_body();
		
		int saveCount = notiStateDao.saveNewNoti(notificationEntity);
		
		if(saveCount==1){
		   notiStateDao.updateNotificationState(notificationEntity.getNoti_to());
            MiMessage message = new MiMessage();
            message.sendMessage(notificationEntity.VNotification_body(),miMessageService.getRegidList(Integer.valueOf(notificationEntity.VNoti_to())));
            message.send();
			return  "success";
		}else{
			throw new HongZhiException("1011");
		}
	}
	
	
	public Object sendNoti(Integer from_user_id , List<Integer> multiple_receiver,Integer single_receiver ,String notificationCategory, String notificationBody) throws HongZhiException {
		if(ObjectUtil.isEmpty(multiple_receiver) &&  !ObjectUtil.isEmpty(single_receiver)){
				NotificationEntity notificationEntity = new NotificationEntity();
				notificationEntity.setNoti_from(from_user_id);
				notificationEntity.setNoti_to(single_receiver);
				notificationEntity.setNoti_category(notificationCategory);
				notificationEntity.setNotification_body(notificationBody);
				notificationEntity.VNoti_from();
				notificationEntity.VNoti_to();
				notificationEntity.VNoti_category();
				notificationEntity.VNotification_body();
				return saveNoti(notificationEntity);
		}else if(!ObjectUtil.isEmpty(multiple_receiver)){
			NotificationEntity notificationEntity = new NotificationEntity();
			notificationEntity.setNoti_from(from_user_id);
			notificationEntity.setNoti_category(notificationCategory);
			notificationEntity.setNotification_body(notificationBody);
			notificationEntity.VNoti_from();
			notificationEntity.VNoti_category();
			notificationEntity.VNotification_body();
			if(!ObjectUtil.isEmpty(single_receiver)){
				multiple_receiver.add(single_receiver);
			}

		    int saveCount = notiStateDao.saveMultipleNoti(notificationEntity,multiple_receiver);

			if(saveCount==multiple_receiver.size()){
				   notiStateDao.updateNotificationStateMultipleUser(multiple_receiver);

                    MiMessage message = new MiMessage();
                    message.sendMessage(notificationEntity.VNotification_body(), miMessageService.getRegidList(multiple_receiver));

					return  "success";
			}else{
					throw new HongZhiException("1011");
			}
		}else{
					throw new HongZhiException("1011");
		}
		
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : May 4, 2016    3:09:36 PM
	 * @return
	 */
	public Object cagtegoryList() {
		List<Map<String,Object>>   list = notiStateDao.categoryList("zh");
		return list;
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : May 4, 2016    3:14:31 PM
	 * @param id
	 * @param category_id 
	 * @param language 
	 * @return
	 */
	@Transactional
	public Object readNotification(String user_id, String id, String category_id,String search, String language) {
		Integer notificationID =null;
		if(!ObjectUtil.isEmpty(id)){
			notificationID=Integer.parseInt(id);
		}
		List<Map<String,Object>>  notification = notiStateDao.readNotification(Integer.parseInt(user_id),notificationID,category_id,search,language);
		notiStateDao.markRead(Integer.parseInt(user_id),notificationID,category_id);
		notiStateDao.updateNotificationState(Integer.parseInt(user_id));
		Map<String,Object>   out = new HashMap<String, Object>();
		out.put("notificationList", notification);
		return out;
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : May 4, 2016    3:26:02 PM
	 * @param user_id
	 * @param id
	 * @return
	 * @throws HongZhiException 
	 */
	public Object deleteNotification(String user_id, String id) throws HongZhiException {
		int count = notiStateDao.logicDelete(Integer.parseInt(user_id),Integer.parseInt(id));
		if(count==1){
			notiStateDao.updateNotificationState(Integer.parseInt(user_id));
			return "success";
		}else{
			throw new HongZhiException("1034");
		}
	}

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 1, 2016    10:56:24 AM
     * @param notificationEntity
     * @param receiver_user
     * @return
     * @throws HongZhiException 
     */
    public Object saveNoti(NotificationEntity notificationEntity, String receiver_user) throws HongZhiException {
        notificationEntity.VNoti_from();
        notificationEntity.VNoti_category();
        notificationEntity.VNotification_body();
        if(receiver_user.equals("all")){
              List<Integer> all_user_id = notiStateDao.allUserId();
              sendNoti(notificationEntity.getNoti_from(), all_user_id , null, notificationEntity.getNoti_category(), notificationEntity.getNotification_body());
        }else{
            String[] user_id_array = receiver_user.split(",");
            if(user_id_array.length==1){
                notificationEntity.setNoti_to(Integer.parseInt(user_id_array[0]));
                saveNoti(notificationEntity);
            }else{
                List<Integer> user_id_list = new ArrayList<>();
                for(int i=0;i<user_id_array.length;i++){
                    user_id_list.add(Integer.parseInt(user_id_array[i]));
                }
                sendNoti(notificationEntity.getNoti_from(), user_id_list , null, notificationEntity.getNoti_category(), notificationEntity.getNotification_body());
            }
        }
        return null;
    }
	
	
	
	

}
