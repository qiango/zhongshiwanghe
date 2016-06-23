package com.hongzhi.zswh.util.page;

import java.util.List;
import java.util.Map;

import com.hongzhi.zswh.util.basic.ObjectUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * @author Saxon
 * create date: 2016年3月2日 下午8:04:35
 * 处理分页工具对象
 */
public class PageObject {
	private List<Map<String, Object>> list;
	private String pageString;
	
	private List<Object> obj_list;
	
	
	
	

	public List<Object> getObj_list() {
		return obj_list;
	}

	public void setObj_list(List<Object> obj_list) {
		this.obj_list = obj_list;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
	}
	
	
	public String[] toJsonString(){
		JSONObject obj=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<list.size();i++){
			jsonArray.add( ObjectUtil.mapToJson(list.get(i))  );
		}
		obj.put("data_list", jsonArray);
		obj.put("pageString", getPageString());
		JSONObject  out=new JSONObject();
		out.put("pageObject", obj.toString());
		String [] out_ss={out.toString(), jsonArray.toString(), getPageString() };
		// 全部pageObject, jsonArray, page_string
		return out_ss;
	}
	
	

}
