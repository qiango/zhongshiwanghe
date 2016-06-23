package com.hongzhi.zswh.app_v4.wechatpay.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongzhi.zswh.app_v4.mall.entity.CurrentPayment;
import com.hongzhi.zswh.app_v4.mall.entity.Payment;
import com.hongzhi.zswh.app_v4.mall.service.V4OrderService;
import com.hongzhi.zswh.app_v4.wechatpay.config.WeChatConfig;
import com.hongzhi.zswh.app_v4.wechatpay.entity.WeChatNotifyEntity;
import com.hongzhi.zswh.app_v4.wechatpay.entity.WeChatParam;
import com.hongzhi.zswh.app_v4.wechatpay.entity.WeChatRefundParamEntity;
import com.hongzhi.zswh.app_v4.wechatpay.entity.WeChatRefundQueryParam;
import com.hongzhi.zswh.app_v4.wechatpay.entity.WeChatRefundQueryResult;
import com.hongzhi.zswh.app_v4.wechatpay.entity.WeChatRefundResult;
import com.hongzhi.zswh.app_v4.wechatpay.entity.WeChatResult;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.encryption.SHA256;
import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 18, 2016    3:19:15 PM
 */
@Service
public class WeChatPayService {
    
    @Autowired
    private  V4OrderService orderService;
    
    private String wechat_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private String refund_url = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    private String refund_query_url="https://api.mch.weixin.qq.com/pay/refundquery";
    
    public String queryWeChat(String input_url,String body){
        String inputLine = "";
        try {
            URL url = new URL(input_url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "text/xml");
            urlConnection.setRequestProperty("Accept-Charset", "utf-8");  
            urlConnection.setRequestProperty("contentType", "utf-8");
            urlConnection.setDoOutput(true);
            
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter((urlConnection.getOutputStream()),"UTF-8"));
            writer.write(body, 0, body.length());
            writer.flush();
            writer.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            inputLine = in.readLine().toString();
            String read = in.readLine();
            while (!ObjectUtil.isEmpty(read)) {
                inputLine+=read;
                read = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            inputLine = "";
        }
        return inputLine;
    }

    /**   Twitter : @taylorwang789 
     * Creat time : May 18, 2016    3:20:56 PM
     * @param order_code
     * @param device 
     * @return
     * @throws HongZhiException 
     */
    @Transactional
    public  synchronized  Object createOrder(String order_code, String device) throws HongZhiException {

        if(CurrentPayment.payMap.containsKey(order_code)  && ( CurrentPayment.payMap.get(order_code) > System.currentTimeMillis() )  ){
            throw new HongZhiException("1071");
        }
        CurrentPayment.payMap.put(order_code, System.currentTimeMillis()+180);

        Map<String, Object> order_info = orderService.getOrderInfoToPay(ExcepUtil.verify(order_code, "1060").toString());
        
        if(ObjectUtil.isEmpty(order_info)){
            throw new HongZhiException("1069") ;
         }

        Integer fee = (int) (Double.parseDouble(order_info.get("total_fee").toString()) * 100) ;

        WeChatParam  wechat_pay_param = new WeChatParam();
        
        switch (ExcepUtil.verify(device, "1058").toString()) {
        case "iOS":
            wechat_pay_param.setAppid(WeChatConfig.iOS_appid);
            wechat_pay_param.setMch_id(WeChatConfig.iOS_mch_id);
            break;
        case "Android":
            wechat_pay_param.setAppid(WeChatConfig.Android_appid);
            wechat_pay_param.setMch_id(WeChatConfig.Android_mch_id);
            break;
        default:
            break;
        }
        wechat_pay_param.setBody(order_info.get("goods_name").toString());//商品或支付单简要描述
//        wechat_pay_param.setBody("test");//商品或支付单简要描述
        wechat_pay_param.setNonce_str(SHA256.getSalt());
        wechat_pay_param.setNotify_url(WeChatConfig.notify_url);
        wechat_pay_param.setOut_trade_no(order_code);
        wechat_pay_param.setTotal_fee(fee); // test use 1
        wechat_pay_param.setSpbill_create_ip("10.240.181.111");
        wechat_pay_param.setTrade_type("APP");
        
        String param_string = ""
                    +"appid="+wechat_pay_param.getAppid()
                    +"&body="+wechat_pay_param.getBody()
                    +"&mch_id="+wechat_pay_param.getMch_id()
                    +"&nonce_str="+wechat_pay_param.getNonce_str()
                    +"&notify_url="+wechat_pay_param.getNotify_url()
                    +"&out_trade_no="+wechat_pay_param.getOut_trade_no()
                    +"&spbill_create_ip="+wechat_pay_param.getSpbill_create_ip()
                    +"&total_fee="+wechat_pay_param.getTotal_fee()
                    +"&trade_type="+wechat_pay_param.getTrade_type()
                    +"&key="+WeChatConfig.key ;
       
        wechat_pay_param.setSign(DigestUtils.md5Hex(param_string).toUpperCase());
        System.out.println(JavaObjectXml.toXML(wechat_pay_param));
        String return_str = queryWeChat(wechat_url,JavaObjectXml.toXML(wechat_pay_param));
        System.out.println("#############################################################");
        System.out.println(return_str);
        
//        WeChatResult wechat_result =  JavaObjectXml.fromXMlL(return_str);
        WeChatResult wechat_result =  (WeChatResult) JavaObjectXml.fromXMlL(return_str);
        String timeStamp = (System.currentTimeMillis()/1000)+"" ;
        //appid=wxf335e214a840e01f&noncestr=JzGNEs&package=Sign=WXPay&partnerid=1343259601&prepayid=wx201605201027105eb01aaa440960597190&timestamp=1463715022
        String sign_second = "" 
                        +"appid="+wechat_pay_param.getAppid()
                        +"&noncestr="+wechat_pay_param.getNonce_str()
                        +"&package=Sign=WXPay"
                        +"&partnerid="+wechat_pay_param.getMch_id()
                        +"&prepayid="+wechat_result.getPrepay_id()
                        +"&timestamp="+timeStamp
                        +"&key="+WeChatConfig.key ;
        wechat_result.setSign(DigestUtils.md5Hex(sign_second).toUpperCase());
        wechat_result.setTime_stamp(timeStamp);
        wechat_result.setPackage_name("Sign=WXPay");
        wechat_result.setNonce_str(wechat_pay_param.getNonce_str());

        Map<String,Object> return_map  = new HashMap<>();
        return_map.put("prepay_order",wechat_result);
        return_map.put("params", wechat_pay_param);
        
        Payment pay = new Payment();
        pay.setWay("2");
        pay.setStatus("1");
        pay.setOrder_code(order_code);
        pay.setTrace_code(wechat_result.getPrepay_id());
        orderService.payment(pay);
        
//        orderService.updateOrderStatus(order_code,"2");
        
        return return_map;
    }

    /**   Twitter : @taylorwang789 
     * Creat time : May 20, 2016    3:02:43 PM
     * @param request
     * @return
     */
    public String reciveNotify(HttpServletRequest request) {
        System.out.println(System.currentTimeMillis());
        String inputLine = "";
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            inputLine = in.readLine().toString();
            String read = in.readLine();
            while (!ObjectUtil.isEmpty(read)) {
                inputLine+=read;
                read = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        WeChatNotifyEntity wechat_notify_entity = new WeChatNotifyEntity();
        wechat_notify_entity = (WeChatNotifyEntity) JavaObjectXml.fromXMlLNotify(inputLine);
        if(wechat_notify_entity.getReturn_code().equals("SUCCESS")){
            orderService.updatePaymentStatus(wechat_notify_entity.getOut_trade_no(),"2","2",wechat_notify_entity.getTransaction_id());
        }else{
            orderService.updatePaymentStatus(wechat_notify_entity.getOut_trade_no(),"2","3",wechat_notify_entity.getTransaction_id());
        }
        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>" ;
    }

    /**   Twitter : @taylorwang789 
     * Creat time : May 20, 2016    5:29:39 PM
     * @param code
     * @param status
     * @return
     */
    public String test(String code, String status) {
            orderService.updatePaymentStatus(code,"2",status,"");
        return "success";
    }

    /**   Twitter : @taylorwang789 
     * Creat time : May 21, 2016    1:27:02 PM
     * @param order_code
     * @param pay_status
     * @return
     * @throws HongZhiException 
     */
    public Object clentNotify(String order_code, Integer pay_status) throws HongZhiException {
           CurrentPayment.payMap.remove(order_code);
           ExcepUtil.verify(order_code, "1060");
           Map<String,Object> server_info =  orderService.clientPayNotify(order_code,pay_status);
           if(pay_status.equals(1)){
               orderService.updateOrderStatus(order_code,"2");
           }
           Map<String,Object> map = new HashMap<>();
           map.put("server_info", server_info);
           return map;
    }

    

    /////////////////////////////////////////////////////////////////////// refund 
    
    
    
    
    /**   Twitter : @taylorwang789 
     * Creat time : May 21, 2016    2:38:14 PM
     * @param order_code ,订单号
     * @param amount  , 金额, 分
     * @param device  , iOS, Android
     * @return
     */
    public Object refund(String order_code, Integer amount, String device,String refund_no) {
        String appID = "";
        String mech_id = "";
        String nonce_string = SHA256.getSalt();
        switch (device) {
        case "iOS":
            appID=WeChatConfig.iOS_appid;
            mech_id=WeChatConfig.iOS_mch_id;
            break;
        case "Android":
            appID=WeChatConfig.Android_appid;
            mech_id=WeChatConfig.Android_mch_id;
            break;
        default:
            break;
        }
        
        Map<String,Object> order_info = orderService.getOrderInfoToRefund(order_code);
        
        WeChatRefundParamEntity refund_param_entity = new WeChatRefundParamEntity();
        refund_param_entity.setAppid(appID);
        refund_param_entity.setMch_id(mech_id);
        refund_param_entity.setNonce_str(nonce_string);
        refund_param_entity.setOp_user_id(mech_id);
        refund_param_entity.setOut_trade_no(order_code);
        refund_param_entity.setOut_refund_no(order_code+"-"+refund_no);
        refund_param_entity.setRefund_fee(amount);
        refund_param_entity.setTotal_fee( ((BigDecimal) order_info.get("total_pay")).intValue() );
        
        String param_string = ""
                +"appid="+appID
                +"&mch_id="+mech_id
                +"&nonce_str="+nonce_string
                +"&op_user_id="+mech_id
                +"&out_refund_no="+refund_param_entity.getOut_refund_no()
                +"&out_trade_no="+order_code
                +"&refund_fee="+amount
                +"&total_fee="+refund_param_entity.getTotal_fee()
                +"&key="+WeChatConfig.key ;

        refund_param_entity.setSign(DigestUtils.md5Hex(param_string).toUpperCase());
        String reuqestXml = JavaObjectXml.toXML(refund_param_entity);

        String refund_return_xml = ClientCustomSSL.weChatRefund(mech_id, reuqestXml, refund_url,device);

        WeChatRefundResult refund_result = (WeChatRefundResult) JavaObjectXml.fromXMlLRefund(refund_return_xml);
        Map<String,Object> map = new HashMap<>();
        map.put("refund_result", refund_result);
        return map;
    }
    
    
    // return result 
    //  1: 退款中 , 2:已退款成功 , 3:退款失败
    public  String  checkRefundStatus(String order_code,String refund_no,String device){
        WeChatRefundQueryParam  refund_query = new WeChatRefundQueryParam();
        String appID = "";
        String mech_id = "";
        String nonce_string = SHA256.getSalt();
        switch (device) {
        case "iOS":
            appID=WeChatConfig.iOS_appid;
            mech_id=WeChatConfig.iOS_mch_id;
            break;
        case "Android":
            appID=WeChatConfig.Android_appid;
            mech_id=WeChatConfig.Android_mch_id;
            break;
        default:
            break;
        }
        refund_query.setAppid(appID);
        refund_query.setMch_id(mech_id);
        refund_query.setNonce_str(nonce_string);
        refund_query.setOut_refund_no(order_code+"-"+refund_no);
        
        String param_string = ""
                +"appid="+appID
                +"&mch_id="+mech_id
                +"&nonce_str="+nonce_string
                +"&out_refund_no="+order_code+"-"+refund_no
                +"&key="+WeChatConfig.key ;
        refund_query.setSign(DigestUtils.md5Hex(param_string).toUpperCase());
        String refund_query_xml = queryWeChat(refund_query_url, JavaObjectXml.toXML(refund_query));
        WeChatRefundQueryResult result = (WeChatRefundQueryResult) JavaObjectXml.fromXMlLRefundQuery(refund_query_xml);
        if(result.getRefund_status_0().equals("SUCCESS")){
            orderService.updatePaymentStatus(order_code, "2" , "2", result.getTransaction_id());
            return "2";
        }else if(result.getRefund_status_0().equals("SUCCESS")){
            orderService.updatePaymentStatus(order_code, "2" , "3", result.getTransaction_id());
            return "3";
        }else{
            return "1";
        }
        
    }
    
    
}
