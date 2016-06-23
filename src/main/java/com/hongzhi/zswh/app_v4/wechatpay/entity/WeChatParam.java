package com.hongzhi.zswh.app_v4.wechatpay.entity;

/**   Twitter : @taylorwang789 
 * Creat time : May 18, 2016    3:43:58 PM
 */
public class WeChatParam {
    // not null
    private String appid;
    private String body;
    private String mch_id;
    private String nonce_str;
    private String notify_url;
    private String out_trade_no;
    private String spbill_create_ip;
    private Integer total_fee;
    private String trade_type;
    private String sign;
    
//    // can be null
//    private String device_info;
//    private String detail;
//    private String attach;
//    private String fee_type;
//    private String time_start;
//    private String time_expire;
//    private String goods_tag;
//    private String limit_pay;

    public String getAppid() {
        return appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getMch_id() {
        return mch_id;
    }
    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }
    public String getNonce_str() {
        return nonce_str;
    }
    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getOut_trade_no() {
        return out_trade_no;
    }
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
    public Integer getTotal_fee() {
        return total_fee;
    }
    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }
    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }
    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }
    public String getNotify_url() {
        return notify_url;
    }
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }
    public String getTrade_type() {
        return trade_type;
    }
    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
   
   
}
