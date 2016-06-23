package com.hongzhi.zswh.app_v4.mall.entity;

import java.sql.Timestamp;

/**   Twitter : @taylorwang789 
 * Creat time : May 20, 2016    3:41:52 PM
 */
public class Payment {
    
    
    private Integer order_id;
    private String order_code;
    private String way;
    private String status;
    private Timestamp start_time;
    private Timestamp end_time;
    private String trace_code;
    
    
    public Integer getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
    public String getOrder_code() {
        return order_code;
    }
    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }
    public String getWay() {
        return way;
    }
    public void setWay(String way) {
        this.way = way;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
    public String getTrace_code() {
        return trace_code;
    }
    public void setTrace_code(String trace_code) {
        this.trace_code = trace_code;
    }
    
}
