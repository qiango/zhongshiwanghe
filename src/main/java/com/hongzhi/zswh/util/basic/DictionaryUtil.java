package com.hongzhi.zswh.util.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.hongzhi.zswh.util.basic.dictionaryDao.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongzhi.zswh.util.basic.dictionaryDao.Dictionary;
import com.hongzhi.zswh.util.basic.dictionaryDao.DictionaryDao;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 18, 2016    4:08:24 PM
 */
@Repository
public class DictionaryUtil {
	@Autowired
	private DictionaryDao dictionaryDao;
	
	@Autowired
	private SessionUtil sessionUtil;

    @Autowired
    private DictionaryService dicService;

    public static Map<String,String>  dics  = new HashMap<>();


    public void init() {
        List<Dictionary> dictionaries = dicService.dictionaries();

        dics.clear();

        for (Dictionary dic : dictionaries) {
            dics.put(dic.getCode()+"_"+dic.getP_code()+"_"+dic.getLanguage(),dic.getValue() );
        }
    }

    public String getValue(String... param) {
        if (ObjectUtil.isEmpty(dics) || dics.size() == 0) {
            init();
        }
        String key = "";
        for (int i = 0; i < param.length; i++) {
            key += param[i] ;
            if (i != ( param.length - 1 ) ) {
                key += "_" ;
            }
        }
        return dics.get(key);
    }
	
	public String getCodeValue(String code,String language){
		if(ObjectUtil.isEmpty(language)){
			language="zh";
		}
//		Dictionary dictionary=dictionaryDao.selectByCodeAndLanguage(code, language,"return_info");
//		return ObjectUtil.getProperty(dictionary, dictionary.getValue(),"").toString();
        return getValue(code,"return_info",language);
	}
	
	public String getCodeValue(String code,String p_code ,String language){
//		Dictionary dictionary=dictionaryDao.selectByCodeAndLanguage(code, language,p_code);
//		return ObjectUtil.getProperty(dictionary, dictionary.getValue(),"").toString();
        return getValue(code,p_code,language);
	}
	
	public void verifyData(boolean cond, String code) throws HongZhiException {
		      if(! cond){   // if false throw exception 
				throw new HongZhiException(code, "" );
		      }
	}
	public void verifyData(Object param ,String code) throws HongZhiException {
		if (ObjectUtil.isEmpty(param)) {
				throw new HongZhiException(code,"");
		}
	}
	
	
	
	
	public Dictionary  dicTree(Dictionary dictionary){
		dictionary.setChildren(dictionaryDao.getDataByPcode(dictionary.getCode()));
		List<Dictionary> children=dictionary.getChildren();
		for(int i=0;i<children.size();i++){
			children.set(i, dicTree(children.get(i)));
		}
		return dictionary;
	}
	
	private Map<String,Object> outmap=new HashMap<>();

	public String appOut(String code,String language,Object data){
		if(ObjectUtil.isEmpty(code)){
			code="e01";
		}
		if(ObjectUtil.isEmpty(language)){
			language="zh";
		}
		outmap.put("code", code);
		outmap.put("message", getCodeValue(code, language) );
		outmap.put("data", data );
		return ObjectUtil.toJson(outmap);
	}
	
	public String appOut(String code,Object data){
		if(ObjectUtil.isEmpty(code)){
			code="e01";
		}
		outmap.put("code", code);
		outmap.put("message", "" );
		outmap.put("data", data );
		return ObjectUtil.toJson(outmap);
	}
	public Map<String,String>  checkParams(String param_name,String[] params_ary,String errorcode) throws HongZhiException{
		Map<String, String> params = new HashMap<String, String>();
		String[] names = param_name.split(",");
		String[] codes = errorcode.split(",");
		
		for (int i = 0; i < names.length; i++) {
			if (names[i].trim().equals("language_abbreviation") || names[i].trim().equals("language")) {
				if (ObjectUtil.isEmpty(params_ary[i])) {
					params.put(names[i].trim(), "zh");
				} else {
					params.put(names[i].trim(), params_ary[i].substring(0, 2));
				}
			} else {
				params.put(names[i].trim(), params_ary[i]);
			}
		}

		String code = "0";

		String current_code = "0";
		for (int i = 0; i < names.length; i++) {
			if (!ObjectUtil.isEmpty(codes[i])) {
				current_code = codes[i];
			}
			if (!current_code.equals("0")) {
//				System.out.println(current_code + ":" + params_ary[i]);
				verifyData(params_ary[i], current_code);
			}
		}

		params.put("code", code);
		return params;
	}
	
	public Map<String,String>  checkParams(String param_name,String errorcode,Object paramObj,HttpSession session,String function_name) throws HongZhiException{
		SessionProperty sessionProperty = sessionUtil.getProperty(session,function_name);
		Map<String,String> params_input = ObjectUtil.entityToMap(paramObj);
		Map<String,String> params = new HashMap<>();
		String[] names = param_name.split(",");
		String[] codes = errorcode.split(",");
		String code = "0";
		String current_code = "0";

		for (int i = 0; i < names.length; i++) {
			if (!ObjectUtil.isEmpty(codes[i])) {
				current_code = codes[i];
			}
			if (!current_code.equals("0")) {
				verifyData( params_input.get(names[i])  , current_code);
			}
			params.put(names[i], params_input.get(names[i]));
		}

		params.put("code", code);

		params.put("user_id",sessionProperty.getUser_id());
		params.put("user_real_name", sessionProperty.getUser_real_name());
		params.put("language",sessionProperty.getLanguage());
		params.put("language_abbreviation",sessionProperty.getLanguage());

		return params;
	}
	
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    11:09:15 AM
	 * @param pcode
	 * @param language
	 * @return code,value
	 */
	public List<Map<String,String>> selectByPcodeAndLanuage(String pcode,String language){
		List<Map<String,String>> out=null;
		if(ObjectUtil.isEmpty(pcode)){
			return out;
		}else{
			if(ObjectUtil.isEmpty(language)){
				language="zh";
			}
			out=dictionaryDao.selectByPcodeAndLanuage(pcode, language);
		}
		return out;
	}
	
	
	
}
