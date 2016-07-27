package com.hongzhi.zswh.back.news.entity;


import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

public class PushRecord {

    private String  news_type   ;
    private String  monday_date  ;
    private String  sundays_date    ;
    private String user_id;
	private String news_title;
	private String news_id;

	public String Vnews_title() throws HongZhiException {
		return ExcepUtil.verify(news_title, "1084").toString();
	}

	public String getNews_id() {
		return news_id;
	}

	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}

	public String getNews_title() {
		return news_title;
	}

	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}

	public String getNews_type() {
		return news_type;
	}

	public void setNews_type(String news_type) {
		this.news_type = news_type;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getMonday_date() {
		return monday_date;
	}

	public void setMonday_date(String monday_date) {
		this.monday_date = monday_date;
	}

	public String getSundays_date() {
		return sundays_date;
	}

	public void setSundays_date(String sundays_date) {
		this.sundays_date = sundays_date;
	}
}
