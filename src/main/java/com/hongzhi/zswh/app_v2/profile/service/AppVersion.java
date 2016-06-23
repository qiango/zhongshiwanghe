package com.hongzhi.zswh.app_v2.profile.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.util.basic.DictionaryUtil;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 6, 2016    4:46:47 PM
 */
@Service
public class AppVersion {
	
	@Autowired
	private DictionaryUtil dictionaryUtil;

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 6, 2016    4:48:26 PM
	 * @return
	 */
	public String getVersionInfo() {
		// currentversion#iOS#android     F:force update    N:not force update 
		// e.g.:   1.2.34#F#F    当前版本1.2.34, iOS & Andorid 全强制更新
		// e.g.:   1.2.34#N#F    当前版本1.2.34, iOS 可不更新 , Andorid 强制更新
		// e.g.:   1.2.34#N#N    当前版本1.2.34, iOS 可不更新 , Android 可不更新 
		String versionInfo = dictionaryUtil.getCodeValue("version","root", "zh");
		String[] str_arry = versionInfo.split("#");
		Map<String,String> out = new HashMap<>();
		out.put("current_version", str_arry[0]);
		out.put("iOS", str_arry[1].toUpperCase());
		out.put("Android", str_arry[2].toUpperCase());
		return  dictionaryUtil.appOut("0", out);
	}

}
