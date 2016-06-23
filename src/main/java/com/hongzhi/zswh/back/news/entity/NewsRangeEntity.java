package com.hongzhi.zswh.back.news.entity;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 3, 2016    2:16:20 PM
 */
public class NewsRangeEntity {
    
    private Integer range_id;
    private Integer news_id;
    private Integer range_type;
    private Integer circle_id;
    
    
    public Integer getRange_id() {
        return range_id;
    }
    public void setRange_id(Integer range_id) {
        this.range_id = range_id;
    }
    public Integer getNews_id() {
        return news_id;
    }
    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }
    public Integer getRange_type() {
        return range_type;
    }
    public void setRange_type(Integer range_type) {
        this.range_type = range_type;
    }
    public Integer getCircle_id() {
        return circle_id;
    }
    public void setCircle_id(Integer circle_id) {
        this.circle_id = circle_id;
    }

}
