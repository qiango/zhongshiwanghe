package com.hongzhi.zswh.app.me.dao;

import java.util.List;

import com.hongzhi.zswh.app.me.entity.SportsCamp;

public interface AppSportsCampDao {
	List<SportsCamp> selectSportsCampsByUserId(int user_id);
	
}
