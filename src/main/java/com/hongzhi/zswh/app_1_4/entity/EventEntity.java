package com.hongzhi.zswh.app_1_4.entity;

/**
 * Created with IntelliJ IDEA.
 * User: xuejian
 * Date: 2016/8/2
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class EventEntity {
    private String event_id;
    private String review_type;
    private String review_reason;
    private String reason_type;

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getReview_type() {
        return review_type;
    }

    public void setReview_type(String review_type) {
        this.review_type = review_type;
    }

    public String getReview_reason() {
        return review_reason;
    }

    public void setReview_reason(String review_reason) {
        this.review_reason = review_reason;
    }

    public String getReason_type() {
        return reason_type;
    }

    public void setReason_type(String reason_type) {
        this.reason_type = reason_type;
    }
}
