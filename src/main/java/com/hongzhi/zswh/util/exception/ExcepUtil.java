package com.hongzhi.zswh.util.exception;

import com.hongzhi.zswh.util.basic.ObjectUtil;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 14, 2016    10:46:01 AM
 */
public class ExcepUtil {
	
	public static Object verify( Object obj , String code )  throws HongZhiException {
		if (ObjectUtil.isEmpty(obj)) {
			throw new HongZhiException(code);
		}
		return obj;
	}
	
	

}
