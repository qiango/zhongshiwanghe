package com.hongzhi.zswh.util.basic.sessionDao;

import java.sql.Timestamp;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 5, 2016    5:00:05 PM
 */
public class SessionTime {
	
	private Integer id;
	private String session_id;
	private Timestamp create_time ;
	private Timestamp last_use_time;
	private Timestamp due_time;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getLast_use_time() {
		return last_use_time;
	}
	public void setLast_use_time(Timestamp last_use_time) {
		this.last_use_time = last_use_time;
	}
	public Timestamp getDue_time() {
		return due_time;
	}
	public void setDue_time(Timestamp due_time) {
		this.due_time = due_time;
	}
	
	
	
	
	
	

}
