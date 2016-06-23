package com.hongzhi.zswh.app_v4.wechatpay.entity;

/**   Twitter : @taylorwang789 
 * Creat time : May 21, 2016    5:15:47 PM
 */
public class WeChatRefundQueryResult {
//    <xml>
//    <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
//    <mch_id><![CDATA[10000100]]></mch_id>
//    <nonce_str><![CDATA[TeqClE3i0mvn3DrK]]></nonce_str>
//    <out_refund_no_0><![CDATA[1415701182]]></out_refund_no_0>
//    <out_trade_no><![CDATA[1415757673]]></out_trade_no>
//    <refund_count>1</refund_count>
//    <refund_fee_0>1</refund_fee_0>
//    <refund_id_0><![CDATA[2008450740201411110000174436]]></refund_id_0>
//    <refund_status_0><![CDATA[PROCESSING]]></refund_status_0>
//    <result_code><![CDATA[SUCCESS]]></result_code>
//    <return_code><![CDATA[SUCCESS]]></return_code>
//    <return_msg><![CDATA[OK]]></return_msg>
//    <sign><![CDATA[1F2841558E233C33ABA71A961D27561C]]></sign>
//    <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>
//    </xml>

    private String appid;
    private String mch_id;
    private String nonce_str;
    private String out_refund_no_0;
    private String out_trade_no;
    private Integer refund_count;
    private Integer refund_fee_0;
    private String refund_id_0;
    //退款状态：
    //SUCCESS—退款成功
    //FAIL—退款失败
    //PROCESSING—退款处理中
    //NOTSURE—未确定，需要商户原退款单号重新发起
    //CHANGE—转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
    private String refund_status_0; 
    private String result_code;
    private String return_code;
    private String return_msg;
    private String sign;
    private String transaction_id;
    
    private String err_code;
    private String  err_code_des;
    private String trade_type;
    private String  prepay_id;

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
    public String getOut_refund_no_0() {
        return out_refund_no_0;
    }
    public void setOut_refund_no_0(String out_refund_no_0) {
        this.out_refund_no_0 = out_refund_no_0;
    }
    public String getOut_trade_no() {
        return out_trade_no;
    }
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
    public Integer getRefund_count() {
        return refund_count;
    }
    public void setRefund_count(Integer refund_count) {
        this.refund_count = refund_count;
    }
    public Integer getRefund_fee_0() {
        return refund_fee_0;
    }
    public void setRefund_fee_0(Integer refund_fee_0) {
        this.refund_fee_0 = refund_fee_0;
    }
    public String getRefund_id_0() {
        return refund_id_0;
    }
    public void setRefund_id_0(String refund_id_0) {
        this.refund_id_0 = refund_id_0;
    }
    public String getRefund_status_0() {
        return refund_status_0;
    }
    public void setRefund_status_0(String refund_status_0) {
        this.refund_status_0 = refund_status_0;
    }
    public String getResult_code() {
        return result_code;
    }
    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }
    public String getReturn_code() {
        return return_code;
    }
    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }
    public String getReturn_msg() {
        return return_msg;
    }
    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getTransaction_id() {
        return transaction_id;
    }
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
    public String getErr_code() {
        return err_code;
    }
    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }
    public String getErr_code_des() {
        return err_code_des;
    }
    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }
    public String getTrade_type() {
        return trade_type;
    }
    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
    public String getPrepay_id() {
        return prepay_id;
    }
    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }
    
    
}
