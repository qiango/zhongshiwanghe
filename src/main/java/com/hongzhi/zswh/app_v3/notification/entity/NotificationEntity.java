package com.hongzhi.zswh.app_v3.notification.entity;

import java.sql.Timestamp;

import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 3, 2016    4:02:01 PM
 */
public class NotificationEntity {
	private Integer id ;
	private Integer noti_from ;
	private Integer noti_to ;
	private String  noti_category ;
	private String  notification_body ;
	private Timestamp create_time ;
	private String  already_read ;
	private String  is_delete ;
	
	public Integer VNoti_from() throws HongZhiException {
		return (Integer) ExcepUtil.verify(noti_from, "1049");
	}
	public Integer VNoti_to() throws HongZhiException {
		return (Integer) ExcepUtil.verify(noti_to, "1050");
	}
	public String VNoti_category() throws HongZhiException {
		return ExcepUtil.verify(noti_category, "1051").toString();
	}
	public String VNotification_body() throws HongZhiException {
		return ExcepUtil.verify(notification_body, "1052").toString();
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNoti_from() {
		return noti_from;
	}
	public void setNoti_from(Integer noti_from) {
		this.noti_from = noti_from;
	}
	public Integer getNoti_to() {
		return noti_to;
	}
	public void setNoti_to(Integer noti_to) {
		this.noti_to = noti_to;
	}
	public String getNoti_category() {
		return noti_category;
	}
	public void setNoti_category(String noti_category) {
		this.noti_category = noti_category;
	}
	public String getNotification_body() {
		return notification_body;
	}
	public void setNotification_body(String notification_body) {
		this.notification_body = notification_body;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getAlready_read() {
		return already_read;
	}
	public void setAlready_read(String already_read) {
		this.already_read = already_read;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
}
