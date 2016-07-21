package com.hongzhi.zswh.app_1_3.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taylor on 7/21/16.
 * twitter: @taylorwang789
 */
public class CompetitionNews {
    private Integer news_id ;
    private String news_type ;
    private String news_title ;
    private String news_subtitle ;
    private String news_abstract ;
    private List<CompetitionNewsImage> images = new ArrayList<>();

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

    public String getNews_subtitle() {
        return news_subtitle;
    }

    public void setNews_subtitle(String news_subtitle) {
        this.news_subtitle = news_subtitle;
    }

    public String getNews_abstract() {
        return news_abstract;
    }

    public void setNews_abstract(String news_abstract) {
        this.news_abstract = news_abstract;
    }

    public List<CompetitionNewsImage> getImages() {
        return images;
    }

    public void setImages(List<CompetitionNewsImage> images) {
        this.images = images;
    }
}
