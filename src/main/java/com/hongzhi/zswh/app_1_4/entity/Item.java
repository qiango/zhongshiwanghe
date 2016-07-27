package com.hongzhi.zswh.app_1_4.entity;

/**
 * Created by taylor on 7/27/16.
 * twitter: @taylorwang789
 */
public class Item {

    private Integer item_id;
    private Integer vote_id;
    private String name;
    private String image;
    private String describe;
    private Integer votes;

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public Integer getVote_id() {
        return vote_id;
    }

    public void setVote_id(Integer vote_id) {
        this.vote_id = vote_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
