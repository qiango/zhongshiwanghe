package com.hongzhi.zswh.util.basic;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.hongzhi.zswh.util.date.DateUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

import java.util.Map.Entry;

import org.apache.ibatis.io.Resources;

import java.util.Properties;

import net.sf.json.JSONObject;

public class ObjectUtil {

	/**
	 * 判断String类型对象是否为空(空串或null)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断对象是否为空(空串或null)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null || obj.toString().trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断一个对象数组是否为空(没有成员)
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(Object[] array) {
		if (array == null || array.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取值为0.00的BigDecimal对象
	 * 
	 * @return
	 */
	public static BigDecimal getZeroBigDecimal() {
		return new BigDecimal(0);
	}

	public static Map<String,String> entityToMap(Object obj){
		 Field[] fields = obj.getClass().getDeclaredFields(); 
		 Map<String,String> map = new HashMap<>();
		 for(int i=0;i<fields.length;i++){
			 try {
				Field f = obj.getClass().getDeclaredField(fields[i].getName());
				f.setAccessible(true);  
               Object o = f.get(obj);  
               map.put(fields[i].getName(), o.toString());  
			} catch (Exception e) {
			} 
		 }
		 return map;
	}
	
	
	/**
	 * @param map
	 * 把 map<String,Object>  数据转成 JSONObject
	 * @return
	 */
	public static JSONObject mapToJson(Map<String,Object> map){
		Set<Entry<String, Object>> map_set = map.entrySet();
		JSONObject obj = new JSONObject();

		for (Entry<String, Object> entry : map_set) {
			Object value = "";
			if (!ObjectUtil.isEmpty(entry.getValue())) {
				value = entry.getValue();
			}
			
			obj.put(entry.getKey(), value);
		}

		return obj;
	}
	
	/**
	 * @param map
	 * @param fileds  id,name,birth_date,e-mail
	 * @return
	 */
	public static JSONObject mapToJson(Map<String,Object> map,String fields){
		 String [] fields_ss=fields.split(",");
		 JSONObject obj = new JSONObject();
		 for(int i=0;i<fields_ss.length;i++){
			 obj.put(fields_ss[i], spaceStringIfNull( map.get(fields_ss[i]))  ) ;
		 }
		return obj;
	}
	
//formatInternationalizationDate    yyyymmdd  yyyy-mm-dd	
	/**
	 * @param map
	 * @param fileds  id,name,birth_date,e_mail,create_time
	 * @param type    0,0,D,0,T
	 * @return
	 */
	public static JSONObject mapToJson(Map<String,Object> map,String fields,String type,String language){
		 String [] fields_ss=fields.split(",");
		 String [] type_ss=type.split(",");
		 if(ObjectUtil.isEmpty(language)){
			 language="zh";
		 }
		 JSONObject obj = new JSONObject();
		 for(int i=0;i<fields_ss.length;i++){
			 String type_item="0";
			 if( ! ObjectUtil.isEmpty(type_ss[i])){
				 type_item=type_ss[i];
			 }
			 
			 if(type_item.toUpperCase().equals("D") && spaceStringIfNull( map.get(fields_ss[i])).trim().length()==8 ){
				 obj.put(fields_ss[i], 
					 DateUtil.formatInternationalizationDate(spaceStringIfNull( map.get(fields_ss[i])) ,language)
					 ) ;
			 }else if(type_item.toUpperCase().equals("T") &&  spaceStringIfNull( map.get(fields_ss[i])).trim().length()==8 ){
				 obj.put(fields_ss[i],
					 DateUtil.formatInternationalizationTime(spaceStringIfNull( map.get(fields_ss[i])) )
						 ) ;
			 }else{
				 obj.put(fields_ss[i], spaceStringIfNull( map.get(fields_ss[i]))  ) ;
			 }
		 }
		return obj;
	}
	
	
	
	/**
	 * map 数据转为 json 格式, 8 位日期 转 10 位, 6位时間转 8 位; 
	 * @param map
	 * @param times
	 * @param dates   String date_field="competition_end_date,registration_end_date,competition_start_date,registration_start_date";
	 * @param language
	 * @return
	 */
	public static JSONObject mapToJsonByDateAndTime(Map<String,Object> map,String times,String dates ,String language){
		 String [] time_fs=times.split(",");
		 String [] date_fs=dates.split(",");
		 List<String> time_list=new ArrayList<>();
		 List<String> date_list=new ArrayList<>();
		 for(int i=0;i<time_fs.length;i++) { time_list.add(time_fs[i].toLowerCase()); }
		 for(int i=0;i<date_fs.length;i++) { date_list.add(date_fs[i].toLowerCase()); }

		 if(ObjectUtil.isEmpty(language)){ language="zh"; }
		 
		 Set<Entry<String, Object>> map_set = map.entrySet();
			JSONObject obj = new JSONObject();

			for (Entry<String, Object> entry : map_set) {
				Object value = "";
				String key=entry.getKey();
				if (!ObjectUtil.isEmpty(key)) {
					value = entry.getValue();
				}

				if(time_list.contains(key.toLowerCase())){
					obj.put(key,
							 DateUtil.formatInternationalizationTime(spaceStringIfNull( value ) )   ) ;
				}else
				if(date_list.contains(key.toLowerCase())){
					obj.put(key,
					 DateUtil.formatInternationalizationDate(spaceStringIfNull( value ) ,language  )) ;
				}else{
					obj.put(key, value);
				}
			}
		return obj;
	}
	
	/**
	 * 抛出字符串是空的异常
	 * @param str
	 * @param code
	 * @param message
	 * @throws HongZhiException
	 */
	public static  void   stringIsEmptyException(String str,String code,String message) throws HongZhiException{
		if(ObjectUtil.isEmpty(str)){
			throw new HongZhiException(code, message);
		}
	}

	/**
	 * 參數獲取, 國際化
	 * 
	 * @param code
	 * @param language
	 * @return
	 */
	private static Properties properties = new Properties();

	public static String getStringContext(String code, String language) {
		String resource = language + ".properties";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			properties.load(inputStream);
		} catch (IOException e) {
			System.out.println("file not found");
			e.printStackTrace();
		}
		return properties.getProperty(code);
	}
	
	
	
	/**
	 * input any not null string at not use param
	 * @param language_abbreviation
	 * @param user_id
	 * @param platform_id
	 * @throws HongZhiException
	 * 
	 */
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
	
	
	/** 
	 * @param str
	 * @return 
	 * 當輸入的是null值时, 输出空格
	 */
	public static String  spaceStringIfNull(String str){
		  if(isEmpty(str)){
			  return " ";
		  }else{
			  return str;
		  }
	}

	public static String  spaceStringIfNull(Object str){
		  if(isEmpty(str)){
			  return " ";
		  }else{
			  return str.toString();
		  }
	}	
	
	public static Object  spaceStringIfNullObj(Object str){
		  if(isEmpty(str)){
			  return "";
		  }else{
			  return str;
		  }
	}

	public static String  zeroStringIfNull(Object str){
		  if(isEmpty(str)){
			  return "0";
		  }else{
			  return str.toString();
		  }
	}	
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 18, 2016    4:16:06 PM
	 * @param obj
	 * @param property
	 * @return
	 * user   user.name 
	 * first check user verify  , if not null then check user.name 
	 */
	public static Object  getProperty(Object obj,Object property,Object defaultValue){
		if( ! isEmpty(obj)){
			if( ! isEmpty(property)){
				return property;
			}else{
				return defaultValue; 
			}
		}else{
			return defaultValue; 
		}
	}
	
	public static Object  getProperty(Object obj,Object defaultValue){
		if( ! isEmpty(obj)){
				return obj; 
		}else{
			return defaultValue; 
		}
	}
	
	private static Gson gson=new Gson();
	
	public static String toJson(Object obj){
		String out= gson.toJson(obj).replace("\\u003d", "=").replace("\\u0026", "&");
		return out;
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 8, 2016    2:44:48 PM
	 * @return
	 */
	public static Object notNullEntity(Object obj) {
		if(isEmpty(obj)){
			return  new HashMap<String,String>();
		}else{
			return obj;
		}
	}


	public static String jsonOut(Object obj){
		Map<String,Object> outmap = new HashMap<>();
		outmap.put("code", "0");
		outmap.put("message", "" );
		outmap.put("data", null == obj ? new JsonObject() : obj );
		Gson gsonWithnull = new GsonBuilder().serializeNulls().create();
		return  gsonWithnull.toJson(outmap).replace("\\u003d", "=").replace("\\u0026", "&");
	}


    public static String jsonOut(Object obj,String message){
        Map<String,Object> outmap = new HashMap<>();
        outmap.put("code", "0");
        outmap.put("message", null == message ? "" : message );
        outmap.put("data", null == obj ? new JsonObject() : obj );
        Gson gsonWithnull = new GsonBuilder().serializeNulls().create();
        return  gsonWithnull.toJson(outmap).replace("\\u003d", "=").replace("\\u0026", "&");
    }
	
	public static String jsonOutDT(Object obj,String dateFormat){
		Map<String,Object> outmap = new HashMap<>();
		outmap.put("code", "0");
		outmap.put("message", "" );
		outmap.put("data", null==obj ? new JsonObject() : obj );
		Gson gsonWithDate = new GsonBuilder().setDateFormat(dateFormat).serializeNulls().create();
		String str=gsonWithDate.toJson(outmap);
		return str.replace("\\u003d", "=").replace("\\u0026", "&");
	}
	
	public static String jsonOutError(String code,String message){
		Map<String,Object> outmap = new HashMap<>();
		outmap.put("code", code);
		outmap.put("message", message );
		outmap.put("data", new JsonObject() );
		return toJson(outmap);
	}
	
	public static Map<String,Object>  packMap(String[] keys ,Object[] value){
		Map<String,Object> map = new HashMap<>();
		for(int i=0;i<keys.length;i++){
			map.put(keys[i], value[i]);
		}
		return map;
	}
		

    public static Map<String,Object> getTree( Map<String,Object> root, List<Map<String,Object>> entityList , String id , String p_id , String childrenListName) { 
            // pid      indexs
        Map<Object,List<Integer>> p_id_map = new  HashMap<>();
        for(int i=0;i<entityList.size();i++){
             if( isEmpty(p_id_map.get(entityList.get(i).get(p_id)) ) ){
                 List<Integer> indexs = new ArrayList<>();
                 indexs.add(i);
                 p_id_map.put(entityList.get(i).get(p_id), indexs );
             }else{
                 p_id_map.get( entityList.get(i).get(p_id) ).add(i);
             }
        }
        return  tree(root,entityList,p_id_map,id,p_id,childrenListName);
    }
    
    private static Map<String,Object> tree(Map<String, Object> root, List<Map<String, Object>> entityList, Map<Object, List<Integer>> p_id_map, String id, String p_id, String childrenList){
        List<Map<String,Object>> children = new ArrayList<>();
        List<Integer>  child_index = p_id_map.get(root.get(id));
        if( ! isEmpty(child_index) )  {
            for(int i=0;i<child_index.size();i++) {
                children.add( entityList.get(child_index.get(i)) );
            }
            root.put(childrenList, children);
            for(int i=0;i<children.size();i++){
                children.set(i, tree(children.get(i), entityList, p_id_map, id, p_id, childrenList));
            }   
        }
        return root;
    }

    public static  Object coalesce(Object... objs) {
        for (Object obj: objs ) {
            if (!isEmpty(obj)) {
                return obj;
            }
        }
        return "";
    }



}

