package com.hongzhi.zswh.util.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.language.LanguageUtil;

import net.sf.json.JSONObject;

public class ControllerUtil {

	
	
	/**
	 * @author taylor
	 * 若  param 是空 , 則抛出异常
	 * @param param  輸入的值
	 * @param code   报錯代碼
	 * @param language_abbreviation  語言
	 * @throws HongZhiException
	 */
	public static void verifyDataThrow(String param ,String code,String language_abbreviation) throws HongZhiException {
		if (ObjectUtil.isEmpty(param)) {
				throw new HongZhiException(code, ObjectUtil.getStringContext(code, language_abbreviation));
		}
	}
	
	/**
	 * @author taylor
	 * 若  param 是空 , 則抛出异常
	 * @param param  輸入的值
	 * @param code   报錯代碼
	 * @param language_abbreviation  語言
	 * @throws HongZhiException
	 */
	public static void verifyDataThrow(Object param ,String code,String language_abbreviation) throws HongZhiException {
		if (ObjectUtil.isEmpty(param)) {
				throw new HongZhiException(code, ObjectUtil.getStringContext(code, language_abbreviation));
		}
	}
	
	/**
	 * @author taylor
	 * @param cond   如果 cond 为 false  則抛出异常
	 * @param code   报錯代碼
	 * @param language_abbreviation  語言
	 * @throws HongZhiException
	 */
	public static void verifyDataThrow(boolean cond, String code,String language_abbreviation) throws HongZhiException {
		      if(! cond){   // if false throw exception 
				throw new HongZhiException(code, ObjectUtil.getStringContext(code, language_abbreviation));
		      }
	}
	
	public static void checkInputValue(String language_abbreviation,String user_id,String platform_id) throws HongZhiException{
		if (ObjectUtil.isEmpty(language_abbreviation)) {
			throw new HongZhiException("1025", ObjectUtil.getStringContext("1025", "zh"));//语言编号不能为空
		}
		if (ObjectUtil.isEmpty(user_id)) {
			throw new HongZhiException("1001", ObjectUtil.getStringContext("1001", language_abbreviation)); //1001=用户名为空..
		}

		if (ObjectUtil.isEmpty(platform_id)) {
			throw new HongZhiException("1024", ObjectUtil.getStringContext("1024", language_abbreviation)); //1024=平台编号不能为空
		}
	}
	
	
	public static void  writeResponse(HttpServletResponse response,String code,String message,JSONObject sonJsonObject){
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		object.put("code", code);
		object.put("message", message);
		object.put("data", sonJsonObject);
		writer.println(object.toString());
		writer.flush();	
		writer.close();
		
	}
	
	
	/**
	 * @author taylor
	 * 获得所有参数
	 * @param response
	 * @param request
	 * @param input_params 	"user_name,language_abbreviation,platform_id,phone,user_password"
	 * @param params_code 	"0,0,1024,1006,1007"
	 * @return
	 * @throws HongZhiException 
	 */
	public static Map<String,String>  getParams(HttpServletResponse response, HttpServletRequest request,String input_params,String params_code) throws HongZhiException{
		Map<String,String> params=new HashMap<String, String>();

		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("UTF-8"); 

		String[] params_ss=input_params.split(",");
		String[] code_ss=params_code.split(",");
		
		for(int i=0;i<params_ss.length;i++){
			if(params_ss[i].equals("language_abbreviation")){
				params.put(params_ss[i],  LanguageUtil.getLanguageAbbreviation(request.getParameter("language_abbreviation")));
			}else{
				params.put(params_ss[i],  request.getParameter(params_ss[i]) );
			}
		}
		
		String 	language_abbreviation=params.get("language_abbreviation");
		String code = "0";
		String message = ObjectUtil.getStringContext(code,language_abbreviation );
		
//		try {

			String current_code="0";
			for(int i=0;i<params_ss.length;i++){
				if( ! ObjectUtil.isEmpty(code_ss[i])){
					current_code=code_ss[i];
				}
				if( ! current_code.equals("0")){
					verifyDataThrow(params.get(params_ss[i]), current_code, language_abbreviation);
				}
			}
			
//		} catch (HongZhiException e) {
//			code = e.getCode();
//			message = e.getMessage();
//		}
		params.put("code", code);
		params.put("message", message);
		
		return params;
	}
	
	
	
	
}
