package com.hongzhi.zswh.app_v6.entity;

import java.util.Date;

/**
 * Created by admin on 2016/6/28.
 */
public class UserDetailEntity {
    private Integer club_id;
    private Integer user_id;
    private Integer jump_club_number;
    private Integer join_club_status;
    private Integer user_level;
    private Date create_time;
    private Date change_status_time;
    private Integer club_refuse_id;
    private Date create_time_bak;

    public Integer getClub_id() {
        return club_id;
    }

    public void setClub_id(Integer club_id) {
        this.club_id = club_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getJump_club_number() {
        return jump_club_number;
    }

    public void setJump_club_number(Integer jump_club_number) {
        this.jump_club_number = jump_club_number;
    }

    public Integer getJoin_club_status() {
        return join_club_status;
    }

    public void setJoin_club_status(Integer join_club_status) {
        this.join_club_status = join_club_status;
    }

    public Integer getUser_level() {
        return user_level;
    }

    public void setUser_level(Integer user_level) {
        this.user_level = user_level;
    }

    public Date getChange_status_time() {
        return change_status_time;
    }

    public void setChange_status_time(Date change_status_time) {
        this.change_status_time = change_status_time;
    }

    public Date getCreate_time_bak() {
        return create_time_bak;
    }

    public void setCreate_time_bak(Date create_time_bak) {
        this.create_time_bak = create_time_bak;
    }

    public Integer getClub_refuse_id() {
        return club_refuse_id;
    }

    public void setClub_refuse_id(Integer club_refuse_id) {
        this.club_refuse_id = club_refuse_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
