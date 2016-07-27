package com.hongzhi.zswh.app_1_4.entity;

import java.sql.Timestamp;

/**
 * Created by taylor on 7/26/16.
 * twitter: @taylorwang789
 */
public class Vote {

    private Integer vote_id ;
    private String vote_name ;
    private String image;
    private Timestamp start_time ;
    private Timestamp end_time ;
    private Integer vote_status ;
    private Integer vote_index ;

    public Integer getVote_id() {
        return vote_id;
    }

    public void setVote_id(Integer vote_id) {
        this.vote_id = vote_id;
    }

    public String getVote_name() {
        return vote_name;
    }

    public void setVote_name(String vote_name) {
        this.vote_name = vote_name;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getVote_status() {
        return vote_status;
    }

    public void setVote_status(Integer vote_status) {
        this.vote_status = vote_status;
    }

    public Integer getVote_index() {
        return vote_index;
    }

    public void setVote_index(Integer vote_index) {
        this.vote_index = vote_index;
    }
}
