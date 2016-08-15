package com.hongzhi.zswh.app_1_4.entity;

import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by taylor on 7/28/16.
 * twitter: @taylorwang789
 */
public class EventCreate {

    private Integer event_id ;
    private String event_name ;
    private Integer club_id ;
    private String start_time ;
    private String end_time ;
    private String register_start_time ;
    private String register_end_time ;

    private Timestamp sql_start_time ;
    private Timestamp sql_end_time ;
    private Timestamp sql_register_start_time ;
    private Timestamp sql_register_end_time ;

    private Integer organizer_id ;
    private String event_address ;
    private Integer min_people ;
    private Integer max_people ;
    private String fee ;
    private String image ;
    private Integer event_status ;
    private Integer view_guests;
    private String organizer_join;
    private String event_detail ;
    private String event_notice ;
    private String form_item;

    private String event_detail_rich_text;



    private String dic_p_code = "event";

    public void verifyData() throws HongZhiException {

        ExcepUtil.verify(event_name,"event_name_null",dic_p_code);
        ExcepUtil.verify(organizer_id,"organizer_id_null",dic_p_code);
        ExcepUtil.verify(view_guests,"view_guests_null",dic_p_code);
        ExcepUtil.verify(form_item,"form_item_null",dic_p_code);
        ExcepUtil.verify(event_address,"event_address_null",dic_p_code);
        ExcepUtil.verify(club_id,"club_id_null",dic_p_code);
        ExcepUtil.verify(organizer_join,"organizer_join_null",dic_p_code);

        ExcepUtil.verify(start_time,"start_time_null",dic_p_code);
        ExcepUtil.verify(end_time,"end_time_null",dic_p_code);
        ExcepUtil.verify(register_start_time,"register_start_null",dic_p_code);
        ExcepUtil.verify(register_end_time,"register_end_null",dic_p_code);
        ExcepUtil.verify(image,"image_null",dic_p_code);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            sql_start_time = new Timestamp(simpleDateFormat.parse(start_time).getTime());
            sql_end_time = new Timestamp(simpleDateFormat.parse(end_time).getTime());
            sql_register_start_time = new Timestamp(simpleDateFormat.parse(register_start_time).getTime());
            sql_register_end_time = new Timestamp(simpleDateFormat.parse(register_end_time).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (    sql_end_time.getTime() < sql_start_time.getTime()
             || sql_register_end_time.getTime() < sql_register_start_time.getTime()
             || sql_register_end_time.getTime() > sql_end_time.getTime()
             || sql_start_time.getTime() < sql_register_start_time.getTime()
                ) {
            throw new HongZhiException("time_error","event") ;
        }
    }



    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public Integer getClub_id() {
        return club_id;
    }

    public void setClub_id(Integer club_id) {
        this.club_id = club_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getRegister_start_time() {
        return register_start_time;
    }

    public void setRegister_start_time(String register_start_time) {
        this.register_start_time = register_start_time;
    }

    public String getRegister_end_time() {
        return register_end_time;
    }

    public void setRegister_end_time(String register_end_time) {
        this.register_end_time = register_end_time;
    }

    public Timestamp getSql_start_time() {
        return sql_start_time;
    }

    public void setSql_start_time(Timestamp sql_start_time) {
        this.sql_start_time = sql_start_time;
    }

    public Timestamp getSql_end_time() {
        return sql_end_time;
    }

    public void setSql_end_time(Timestamp sql_end_time) {
        this.sql_end_time = sql_end_time;
    }

    public Timestamp getSql_register_start_time() {
        return sql_register_start_time;
    }

    public void setSql_register_start_time(Timestamp sql_register_start_time) {
        this.sql_register_start_time = sql_register_start_time;
    }

    public Timestamp getSql_register_end_time() {
        return sql_register_end_time;
    }

    public void setSql_register_end_time(Timestamp sql_register_end_time) {
        this.sql_register_end_time = sql_register_end_time;
    }

    public Integer getOrganizer_id() {
        return organizer_id;
    }

    public void setOrganizer_id(Integer organizer_id) {
        this.organizer_id = organizer_id;
    }

    public String getEvent_address() {
        return event_address;
    }

    public void setEvent_address(String event_address) {
        this.event_address = event_address;
    }

    public Integer getMin_people() {
        return min_people;
    }

    public void setMin_people(Integer min_people) {
        this.min_people = min_people;
    }

    public Integer getMax_people() {
        return max_people;
    }

    public void setMax_people(Integer max_people) {
        this.max_people = max_people;
    }

    public Double getFee() {
  //      return Double.valueOf(fee);
        return 0.0;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getEvent_status() {
        return event_status;
    }

    public void setEvent_status(Integer event_status) {
        this.event_status = event_status;
    }


    public String getOrganizer_join() {
        return organizer_join;
    }

    public void setOrganizer_join(String organizer_join) {
        this.organizer_join = organizer_join;
    }

    public String getEvent_detail() {
        return event_detail;
    }

    public void setEvent_detail(String event_detail) {
        this.event_detail = event_detail;
    }

    public String getEvent_notice() {
        return event_notice;
    }

    public void setEvent_notice(String event_notice) {
        this.event_notice = event_notice;
    }

    public String getDic_p_code() {
        return dic_p_code;
    }

    public void setDic_p_code(String dic_p_code) {
        this.dic_p_code = dic_p_code;
    }

    public Integer getView_guests() {
        return view_guests;
    }

    public void setView_guests(Integer view_guests) {
        this.view_guests = view_guests;
    }

    public String getForm_item() {
        return form_item;
    }

    public void setForm_item(String form_item) {
        this.form_item = form_item;
    }

    public String getEvent_detail_rich_text() {
        return event_detail_rich_text;
    }

    public void setEvent_detail_rich_text(String event_detail_rich_text) {
        this.event_detail_rich_text = event_detail_rich_text;
    }
}
