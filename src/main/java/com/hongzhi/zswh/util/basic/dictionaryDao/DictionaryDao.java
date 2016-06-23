package com.hongzhi.zswh.util.basic.dictionaryDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;



/**
 * @author taylor
 * Twitter : @taylorwang789 
 * Mar 16, 2016    11:16:04 PM
 */
public interface DictionaryDao {
	List<Dictionary> listAllDictionary();
	Dictionary selectByCodeAndLanguage(@Param("code") String code, @Param("language") String language, @Param("pcode") String p_code);
	List<Dictionary> getDataByPcode(String p_code);
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    11:00:23 AM
	 * @param pcode
	 * @param language
	 * @return code  value
	 */
	List<Map<String,String>> selectByPcodeAndLanuage(@Param("pcode") String pcode, @Param("language") String language);
	


}
