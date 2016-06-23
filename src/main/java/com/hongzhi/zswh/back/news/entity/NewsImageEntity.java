package com.hongzhi.zswh.back.news.entity;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 2, 2016    3:36:12 PM
 */
public class NewsImageEntity {
    
    private Integer news_id;
    private String media_url;
    private String media_information;

    public Integer getNews_id() {
        return news_id;
    }
    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }
    public String getMedia_url() {
        return media_url;
    }
    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }
    public String getMedia_information() {
        return media_information;
    }
    public void setMedia_information(String media_information) {
        this.media_information = media_information;
    }

}
