/**  
 * @Title: PropertiesUtil.java
 * @Package com.hongzhi.zswh.util.properties
 * @Description: TODO
 * @author Saxon Zhong
 * @date 2016年5月31日
 */
package com.hongzhi.zswh.util.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.hongzhi.zswh.util.basic.ObjectUtil;

/**
 * ClassName: PropertiesUtil
 * 
 * @Description: TODO
 * @author Saxon Zhong
 * @date 2016年5月31日
 */
@SuppressWarnings({ "unused", "rawtypes" })
public class PropertiesUtil {

	public static final String SYSTEM_PROPERTIES = "/trafficConfig.properties";

	private static Map<String, String> propertieMap = null;
	
	public static Map<String, String> getPropertieMap() {
		if (ObjectUtil.isEmpty(propertieMap)) {
			getValue();
		}
		return propertieMap;
	}
	
	/**
	 * @Description: TODO
	 * @param    
	 * @return void  
	 * @throws
	 * @author Saxon
	 * @date 2016年6月1日
	 */
	private static void getValue() {
		/* 1.sheenmesakfjsdlkf */
		propertieMap = new HashMap<String, String>();
		
		/* 2.sheenmesakfjsdlkf */
		Properties properties = init(SYSTEM_PROPERTIES);
		Iterator it = properties.keySet().iterator();
		String propertiesStorePath = properties.getProperty("path");
		String key;
		Properties p;

		/* 3.sheenmesakfjsdlkf */
		while (it.hasNext()) {
			key = (String) it.next();
			String value = properties.getProperty(key);
			value = value.trim();
			propertieMap.put(key, value);
		}
	}

	private static Properties init(String propertyFile) {
		Properties p = new Properties();
		try {
			p.load(PropertiesUtil.class.getResourceAsStream(propertyFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
