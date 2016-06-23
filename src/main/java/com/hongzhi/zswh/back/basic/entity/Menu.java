package com.hongzhi.zswh.back.basic.entity;

import java.util.List;

public class Menu {
	//  refrence  database , menu_info table 

	 // 菜单编号
	private int menu_id;
	 // 菜单名称
	private String menu_name;
	 // 菜单地址
	private String menu_url;
	 //菜单父节点
	private String menu_parent_id;
	 // flag  back or front menu 
	private String flag;
	// childrens node of current node 
	private List<Menu> children;
	
	
	
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public String getMenu_parent_id() {
		return menu_parent_id;
	}
	public void setMenu_parent_id(String menu_parent_id) {
		this.menu_parent_id = menu_parent_id;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	

}
