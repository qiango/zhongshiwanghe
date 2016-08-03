package com.hongzhi.zswh.app_1_4.entity;

/**
 * Created by taylor on 8/2/16.
 * twitter: @taylorwang789
 */
public class EventCreateRichText {

    private String type;
    private String content;
    private String index;
    private Integer width=0;
    private Integer height=0;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
