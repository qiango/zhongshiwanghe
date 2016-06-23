package com.hongzhi.zswh.app_v3.club.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.app_v3.club.dao.V3SportsCampDao;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 27, 2016    3:08:47 PM
 */
@Service
public class V3SportsCampService {
	
	@Autowired
	private V3SportsCampDao  sdao;
	@Autowired
	private DictionaryUtil dic;
	
	

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    3:13:16 PM
	 * @param properties
	 * @param vclub_id
	 * @return
	 */
	public Object loadSportsCamp(SessionProperty properties, String vclub_id) {
		List<Map<String,Object>> sportsCampList = sdao.listSportsCamp( Integer.parseInt(vclub_id) , Integer.parseInt(properties.getUser_id()) );
		Map<String,Object>  out  = new HashMap<>();
		out.put("sport_camp_list", sportsCampList);
		return out;
	}



	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 27, 2016    4:01:29 PM
	 * @param properties
	 * @param sport_camp_id
	 * @param subscirbe
	 * @return
	 * @throws HongZhiException 
	 */
	public Object subscirbe(SessionProperty properties, String sport_camp_id, String subscirbe_flag) throws HongZhiException {
		if(ObjectUtil.isEmpty(sport_camp_id) && ObjectUtil.isEmpty(subscirbe_flag)) {
			 List<Map<String,Object>>  subscribeList = sdao.subscribeList(Integer.parseInt(properties.getUser_id()));
			 return subscribeList;
		}else if( ( ! ObjectUtil.isEmpty(sport_camp_id) ) && ( ! ObjectUtil.isEmpty(subscirbe_flag)) ){
	       //  subscirbe_flag   0:subscribe   1:unsubscribe
			int effectiveCount = 0;
			switch (subscirbe_flag) {
			case "0":
				  effectiveCount = sdao.subscribe(Integer.parseInt(properties.getUser_id()), Integer.parseInt(sport_camp_id) );
				break;
			case  "1":
				  effectiveCount = sdao.unsubscribe(Integer.parseInt(properties.getUser_id()), Integer.parseInt(sport_camp_id) );
				break;
			default:
				break;
			}
			if(effectiveCount == 1) {
				if("0".equals(subscirbe_flag)){
					return  dic.getCodeValue("1047", properties.getLanguage());
				}else{
					return  dic.getCodeValue("1048", properties.getLanguage());
				}
			}else{
				throw new HongZhiException("1033");
			}
		}else{
				throw new HongZhiException("1033");
		}
			
	}

}
