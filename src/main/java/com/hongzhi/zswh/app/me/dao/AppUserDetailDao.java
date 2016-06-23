package com.hongzhi.zswh.app.me.dao;


import com.hongzhi.zswh.app.me.entity.UserDetail;

public interface AppUserDetailDao {
	UserDetail selectByID(int user_id);
	
}
