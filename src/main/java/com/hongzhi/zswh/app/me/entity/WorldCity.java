package com.hongzhi.zswh.app.me.entity;

import java.util.List;

public class WorldCity {
private Integer id;
private String name;
private Integer parent_id;
private String remark;
private String name_en;

private List<WorldCity> son_world_city_list;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Integer getParent_id() {
	return parent_id;
}

public void setParent_id(Integer parent_id) {
	this.parent_id = parent_id;
}

public String getRemark() {
	return remark;
}

public void setRemark(String remark) {
	this.remark = remark;
}

public String getName_en() {
	return name_en;
}

public void setName_en(String name_en) {
	this.name_en = name_en;
}

public List<WorldCity> getSon_world_city_list() {
	return son_world_city_list;
}

public void setSon_world_city_list(List<WorldCity> son_world_city_list) {
	this.son_world_city_list = son_world_city_list;
}

}
