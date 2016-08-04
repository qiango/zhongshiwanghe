package com.hongzhi.zswh.app_1_4.service;

import com.hongzhi.zswh.app_1_4.dao.MeDao;
import com.hongzhi.zswh.app_1_4.entity.OutputEntity;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("app_1_4_MeServce")
public class MeServce {
	
	@Autowired
	private MeDao meDao;

	public Object loadMe(SessionProperty properties) {

		int user_id=Integer.parseInt(properties.getUser_id());

		int platform_id = Integer.parseInt(properties.getPlatform());

		String language=properties.getLanguage();

		OutputEntity out =meDao.loadMe(user_id,platform_id,language);

		if (properties.getClub_id() != 0 || !ObjectUtil.isEmpty(properties.getClub_id())){
			int event_counts = meDao.selectMyJoinEvent(properties.getUser_id());
			out.setEvent_counts(String.valueOf(event_counts));
		}else{
			out.setEvent_counts("0");
		}

		return out;

	}

}
