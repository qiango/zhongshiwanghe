package com.hongzhi.zswh.app_v5.entity;

import java.util.ArrayList;
import java.util.List;

import com.hongzhi.zswh.util.basic.ObjectUtil;

import net.sf.json.JSONObject;

public class V5NewsDetailEntity {

	/**
	 * 资讯编号
	 */
//	private Integer information_id;
//	private String information_title;
//	private String information_content;
//	private String create_date;
//	private String create_time;

	private Integer news_id;
	private String news_title;
	private String news_abstract;
	private String news_content;
	private String create_time;
	private String news_type;
	private List<V5NewsImageEntity> image_list = new ArrayList<>();
	
	
    public Integer getNews_id() {
        return news_id;
    }
    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }
    public String getNews_title() {
        return news_title;
    }
    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }
    public String getNews_content() {
        return news_content;
    }
    public void setNews_content(String news_content) {
        this.news_content = news_content;
    }
    public String getCreate_time() {
        return create_time;
    }
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
    public List<V5NewsImageEntity> getImage_list() {
        return image_list;
    }
    public void setImage_list(List<V5NewsImageEntity> image_list) {
        this.image_list = image_list;
    }
    public String getNews_type() {
        return news_type;
    }
    public void setNews_type(String news_type) {
        this.news_type = news_type;
    }
    public String getNews_abstract() {
        return news_abstract;
    }
    public void setNews_abstract(String news_abstract) {
        this.news_abstract = news_abstract;
    }
    
	
}
