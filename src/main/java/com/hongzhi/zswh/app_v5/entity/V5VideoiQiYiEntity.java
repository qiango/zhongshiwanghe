package com.hongzhi.zswh.app_v5.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 1, 2016    1:26:28 PM
 */
public class V5VideoiQiYiEntity {
    
    private Integer news_id;
    private String news_type;
    private String news_title;
    private String news_abstract;
    private Timestamp create_time;
    private Integer watch_count;
    private String preview_image ;
    private String file_id ;
    
    private String channel_id; 
    private String channel_name;
    private String channel_describe;
    private String channel_image;
    
    
    private Map<String,String> video_url = new HashMap<>();

    public Integer getNews_id() {
        return news_id;
    }
    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }
    public String getNews_type() {
        return news_type;
    }
    public void setNews_type(String news_type) {
        this.news_type = news_type;
    }
    public String getNews_title() {
        return news_title;
    }
    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }
    public String getNews_abstract() {
        return news_abstract;
    }
    public void setNews_abstract(String news_abstract) {
        this.news_abstract = news_abstract;
    }
    public Timestamp getCreate_time() {
        return create_time;
    }
    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
    public Integer getWatch_count() {
        return watch_count;
    }
    public void setWatch_count(Integer watch_count) {
        this.watch_count = watch_count;
    }
   public String getFile_id() {
        return file_id;
    }
    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }
    public String getPreview_image() {
        return preview_image;
    }
    public void setPreview_image(String preview_image) {
        this.preview_image = preview_image;
    }
    public Map<String, String> getVideo_url() {
        return video_url;
    }
    public void setVideo_url(Map<String, String> video_url) {
        this.video_url = video_url;
    }
    public String getChannel_id() {
        return channel_id;
    }
    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }
    public String getChannel_name() {
        return channel_name;
    }
    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }
    public String getChannel_describe() {
        return channel_describe;
    }
    public void setChannel_describe(String channel_describe) {
        this.channel_describe = channel_describe;
    }
    public String getChannel_image() {
        return channel_image;
    }
    public void setChannel_image(String channel_image) {
        this.channel_image = channel_image;
    }
        
    
}