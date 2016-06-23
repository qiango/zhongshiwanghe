package com.hongzhi.zswh.app_v4.wechatpay.entity;

import java.math.BigDecimal;

/**   Twitter : @taylorwang789 
 * Creat time : May 21, 2016    3:00:34 PM
 */
public class WeChatRefundParamEntity {
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String op_user_id;
    private String out_refund_no;
    private String out_trade_no;
    private Integer refund_fee;
    private Integer total_fee;
    private String sign ;
    
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
    public String getOp_user_id() {
        return op_user_id;
    }
    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }
    public String getOut_trade_no() {
        return out_trade_no;
    }
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
    public String getOut_refund_no() {
        return out_refund_no;
    }
    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }
    public Integer getRefund_fee() {
        return refund_fee;
    }
    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }
    
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public Integer getTotal_fee() {
        return total_fee;
    }
    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }
   
   
    
}
