package com.hongzhi.zswh.app_v6.entity;



import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 1, 2016    1:26:28 PM
 */
public class V6LatestNewsEntity {
    
    private Integer news_id;
    private String news_type;
    private String news_title;
    private String news_abstract;
    private Timestamp create_time;
    private Integer watch_count;
    private String preview_image ;
    private String news_subtitle;
    private List<V6NewsImageEntity> image_list = new ArrayList<>();

    public String getNews_subtitle() {
        return news_subtitle;
    }

    public void setNews_subtitle(String news_subtitle) {
        this.news_subtitle = news_subtitle;
    }

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
    public String getPreview_image() {
        return preview_image;
    }
    public void setPreview_image(String preview_image) {
        this.preview_image = preview_image;
    }
    public List<V6NewsImageEntity> getImage_list() {
        return image_list;
    }
    public void setImage_list(List<V6NewsImageEntity> image_list) {
        this.image_list = image_list;
    }
    

}
