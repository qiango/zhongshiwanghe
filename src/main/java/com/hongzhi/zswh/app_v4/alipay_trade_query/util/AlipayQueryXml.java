package com.hongzhi.zswh.app_v4.alipay_trade_query.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * Created by taylor on 7/4/16.
 */
public class AlipayQueryXml {

    private static XStream x = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));

//
//    public static AlipayQueryEntity fromXMlL(String  xmlString){
//        x.alias("?xml version=\"1.0\" encoding=\"utf-8\"?", AlipayQueryEntity.class);
//        return (AlipayQueryEntity) x.fromXML(xmlString);
//    }


}
