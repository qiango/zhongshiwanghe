package com.hongzhi.zswh.app_v4.wechatpay.service;

import com.hongzhi.zswh.app_v4.wechatpay.entity.WeChatNotifyEntity;
import com.hongzhi.zswh.app_v4.wechatpay.entity.WeChatRefundQueryResult;
import com.hongzhi.zswh.app_v4.wechatpay.entity.WeChatRefundResult;
import com.hongzhi.zswh.app_v4.wechatpay.entity.WeChatResult;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**   Twitter : @taylorwang789 
 * Creat time : May 18, 2016    4:04:23 PM
 */
public class JavaObjectXml {
    
    private static XStream x = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
    private static XStream xorder= new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
    private static XStream xrefund = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
    private static XStream xnotify= new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
    private static XStream xrefundquery= new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));

    public static String toXML(Object obj){
        x.alias("xml",obj.getClass());
        return x.toXML(obj) ;
    }
    
    public static Object fromXMlL(String  xmlString){
        xorder.alias("xml", WeChatResult.class);
        return  xorder.fromXML(xmlString);
    }
  
    public static Object fromXMlLRefund(String  xmlString){
        xrefund.alias("xml", WeChatRefundResult.class);
        return  xrefund.fromXML(xmlString);
    }
    
    public static Object fromXMlLNotify(String  xmlString){
        xnotify.alias("xml", WeChatNotifyEntity.class);
        return  xnotify.fromXML(xmlString);
    }
    
    public static Object fromXMlLRefundQuery(String  xmlString){
        xrefundquery.alias("xml", WeChatRefundQueryResult.class);
        return  xrefundquery.fromXML(xmlString);
    }
    
    

}
