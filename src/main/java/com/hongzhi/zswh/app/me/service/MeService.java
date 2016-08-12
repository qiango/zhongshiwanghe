package com.hongzhi.zswh.app.me.service;

import com.hongzhi.zswh.app.me.dao.AdressDao;
import com.hongzhi.zswh.app.me.dao.AppUserInfoDao;
import com.hongzhi.zswh.app.me.dao.MeDao;
import com.hongzhi.zswh.app.me.entity.Adress;
import com.hongzhi.zswh.app.me.entity.UserInfo;
import com.hongzhi.zswh.app.me.entity.UserProfile;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.encryption.SHA256;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.page.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 21, 2016    3:59:24 PM
 */
@Repository
public class MeService {

	@Autowired
	private  DictionaryUtil dictionaryUtil;
	
	@Autowired
	private  MeDao meDao;
	@Autowired
	private AppUserInfoDao userInfoDao;
	@Autowired
	private AdressDao adressDao;
	
	
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 21, 2016    4:01:49 PM
	 * @param params
	 * @return
	 */
	public String loadMe(Map<String, String> params) {
		
		int user_id = Integer.parseInt(params.get("user_id"));
		int platform_id = Integer.parseInt(params.get("platform_id"));
		String language = params.get("language_abbreviation");
		
		Map<String, Object> map = meDao.loadMe(user_id, platform_id, language);
		
		if (map == null) {
			Map<String, Object> out = new HashMap<String, Object>();
			// 用用加入俱乐部狀态
			out.put("join_club_status", "0"); 
			// 用户加入赛事的数量
			out.put("user_competition_number", "0"); 
			// 用户加入运动派的数量
			out.put("user_sports_camp_number", "0"); 
			out.put("user_name", "");
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
		} else {
			Map<String, Object> out = new HashMap<String, Object>();
			// 用用加入俱乐部狀态
			out.put("join_club_status", map.get("join_club_status"));
			// 用户加入赛事的数量
			out.put("user_competition_number", map.get("user_competition_number"));
			// 用户加入运动派的数量
			out.put("user_sports_camp_number", map.get("user_sports_camp_number"));
			out.put("user_name", map.get("user_name"));
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
		}
	}
	

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 21, 2016    4:06:15 PM
	 * @param params
	 * @return
	 */
	public String loadMeCompetitons(Map<String, String> params) {
		Integer user_id=Integer.valueOf(params.get("user_id"));
		Integer platform_id=Integer.valueOf(params.get("platform_id"));
		try {
			dictionaryUtil.verifyData(platform_id==2, "1024");
		} catch (HongZhiException e) {
			e.printStackTrace();
		}
		PageModel pageModel = new PageModel(params.get("page_number"),params.get("page_size"), params.get("language_abbreviation"));
		pageModel.setOther(user_id.toString());
		List<Map<String,Object>> out=meDao.loadMeCompetitons(pageModel);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("competition_list", out);
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), map);
		
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 21, 2016    4:09:24 PM
	 * @param params
	 * @return
	 */
	public String loadMeSportsCamps(Map<String, String> params) {
		Integer platform_id=Integer.valueOf(params.get("platform_id"));
		try {
			dictionaryUtil.verifyData(platform_id==2, "1024");
		} catch (HongZhiException e) {
			e.printStackTrace();
		}
		PageModel pageModel=new PageModel(params.get("page_number"),params.get("page_size"), params.get("language_abbreviation"));
		pageModel.setOther(params.get("user_id"));
		Map<String, Object> out=new HashMap<String, Object>();
		out.put("user_sports_camp", meDao.loadMeSportsCamps(pageModel));
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 24, 2016    10:35:09 AM
	 * @param params
	 * @return
	 */
	public String loadMeDetail(Map<String, String> params) {
		Integer platform_id=Integer.valueOf(params.get("platform_id"));
		try {
			dictionaryUtil.verifyData(platform_id==2, "1024");
		} catch (HongZhiException e) {
			e.printStackTrace();
		}
		UserInfo userInfo = userInfoDao.selectUserByID(Integer.parseInt(params.get("user_id")), params.get("language_abbreviation"));
		Map<String,Object> out=new HashMap<>();
		out.put("user_detail", userInfo);
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 24, 2016    5:55:01 PM
	 * @param params
	 *  " user_id, language_abbreviation, platform_id, session_id, user_name, user_nickname,user_gender, user_birth_date, user_mail "
	 * @return
	 */
	public String saveMeDetail(Map<String, String> params) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUser_id(Integer.parseInt(params.get("user_id")));
		userInfo.setPlatform_id(Integer.parseInt(params.get("platform_id")));
		userInfo.setBirthdate(params.get("birthdate"));
		userInfo.setNickname(params.get("nickname"));
		userInfo.setGender(params.get("gender"));
		userInfo.setMail_address(params.get("user_mail"));
		userInfo.setUser_real_name(params.get("user_name"));
		int update_count = userInfoDao.updateUserInfo(userInfo);

		updateUserProfile(params);

		if(update_count==1){
			return dictionaryUtil.appOut(params.get("code"), "");
		}else{
			return dictionaryUtil.appOut( "1011" , "");
		}
	}

	private void updateUserProfile(Map<String, String> params){
		List<UserProfile> profiles = new ArrayList<>();

		if (!ObjectUtil.isEmpty(params.get("user_name"))){
			UserProfile profile1 = new UserProfile();
			profile1.setUser_id(Integer.valueOf(params.get("user_id")));
			profile1.setItem_code("name");
			profile1.setItem_value(params.get("user_name"));
			profiles.add(profile1);
		}
		if (!ObjectUtil.isEmpty(params.get("nickname"))){
			UserProfile profile2 = new UserProfile();
			profile2.setUser_id(Integer.valueOf(params.get("user_id")));
			profile2.setItem_code("nickname");
			profile2.setItem_value(params.get("nickname"));
			profiles.add(profile2);
		}
		if (!ObjectUtil.isEmpty(params.get("user_mail"))){
			UserProfile profile3 = new UserProfile();
			profile3.setUser_id(Integer.valueOf(params.get("user_id")));
			profile3.setItem_code("email");
			profile3.setItem_value(params.get("user_mail"));
			profiles.add(profile3);
		}
		if (profiles.size() > 0){
			userInfoDao.saveNewUserProfile(profiles);
		}
	}

	public String loadMeAddress(Map<String, String> params) {
		int user_id=Integer.parseInt(params.get("user_id"));
		int platform_id=Integer.parseInt(params.get("platform_id"));
		Map<String,Object> out=new HashMap<>();
		try {
			dictionaryUtil.verifyData(platform_id==2, "1024");
		} catch (HongZhiException e) {
			e.printStackTrace();
		}
		List<Map<String,Object>> adressList=adressDao.selectAdressByID(user_id);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		for(int i=0;i<adressList.size();i++){
			Map<String,Object> map=new HashMap<>();
			map.put("shipping_id",adressList.get(i).get("shipping_id"));
			map.put("user_id",adressList.get(i).get("user_id"));
			map.put("detail_address", adressList.get(i).get("detail_address"));
			map.put("receiver_name", adressList.get(i).get("receiver_name"));
			map.put("receiver_phone", adressList.get(i).get("receiver_phone"));
			map.put("is_default", adressList.get(i).get("is_default"));
			map.put("city_code", adressList.get(i).get("city_code"));
			int parent_id= (int) adressList.get(i).get("parent_id");
			Map<String, Object> map1=adressDao.selectcitybyparent_id(parent_id);
			int parent_parent_id=(int) map1.get("parent_id");
			Map<String, Object> map2=adressDao.selectcitybyparent_id(parent_parent_id);
			map.put("city_name",map2.get("name").toString()+map1.get("name").toString()+adressList.get(i).get("city_name").toString());
			list.add(map);
		}
		
		
		out.put("adressList", list);
		
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
	}
	
	public String insertAddress(Map<String, String> params){
		Adress address=new Adress();
		address.setUser_id(Integer.parseInt(params.get("user_id")));
		address.setReceiver_name(params.get("receiver_name"));
		address.setDetail_address(params.get("detail_address"));
		address.setCity_code(params.get("city_code"));
		address.setReceiver_phone(params.get("receiver_phone"));
		address.setIs_default(params.get("is_default"));
		if (params.get("is_default").equals("1")) {
			int update_is_default_all = adressDao.updateAdressDefaultAll(Integer.parseInt(params.get("user_id")));
			if(update_is_default_all==1){
				System.out.println("update default success");
			}
		}
		int insert_count=adressDao.insertAdress(address);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("shipping_id", address.getShipping_id().toString());
		if(insert_count==1){
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), map);
		}else{
			return dictionaryUtil.appOut("1011", params.get("language_abbreviation"), "false");
		}
		
		
		
	}

	public String deleteAdress(Map<String, String> params) {
		int deleteAdress=adressDao.deleteAdress(params.get("shipping_id"));
		if(deleteAdress==1){
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		}else{
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "false");
		}
	}

	public String loadAddress(Map<String, String> params) {
		int shipping_id=Integer.parseInt(params.get("shipping_id"));
		int platform_id=Integer.parseInt(params.get("platform_id"));
		Map<String,Object> out=new HashMap<>();
		try {
			dictionaryUtil.verifyData(platform_id==2, "1024");
		} catch (HongZhiException e) {
			
			e.printStackTrace();
		}
		Adress adressList=adressDao.selectAdressByShippingID(shipping_id);
		out.put("adressList", adressList);
		
		return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), out);
		
		
		
	}

	public String saveAddress(Map<String, String> params) {
		Adress address=new Adress();
		address.setUser_id(Integer.parseInt(params.get("user_id")));
		address.setShipping_id(Integer.parseInt(params.get("shipping_id")));
		address.setReceiver_name(params.get("receiver_name"));
		address.setDetail_address(params.get("detail_address"));
		address.setCity_code(params.get("city_code"));
		address.setReceiver_phone(params.get("receiver_phone"));
		address.setIs_default(params.get("is_default"));
		//默认地址为1,设置该用户其他地址的is_default=0
		if(params.get("is_default").equals("1")){
			int update_is_default_all=adressDao.updateAdressDefaultAll(Integer.parseInt(params.get("user_id")));
			if(update_is_default_all==1){
				System.out.println("update default success");
			}
		}

		int save_count=adressDao.updateAdress(address);
		
		if(save_count==1){
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		}else{
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "false");
		}
		
		
	}

	public String updatePassword(Map<String, String> params) {
		Integer platform_id=Integer.valueOf(params.get("platform_id"));
		Integer user_id=Integer.valueOf(params.get("user_id"));
		String phone=params.get("phone");
		String newPasswordOne=params.get("new_password_one");
		String newPasswordTwo=params.get("new_password_two");
		String password=params.get("password");
		String newSalt = SHA256.getSalt();
		try {
			dictionaryUtil.verifyData(platform_id==2, "1024");
		} catch (HongZhiException e) {
			e.printStackTrace();
		}
		Map<String,String> query_result = meDao.getPassword(phone,user_id);
		String user_password= query_result.get("password");
//		if(! SHA256.encode(password, query_result.get("salt")).equals(user_password)){
		if(!SHA256.verifyPassword(password, user_password, query_result.get("salt"))  ){
			return dictionaryUtil.appOut("1016", params.get("language_abbreviation"), "false");
		}else if(!newPasswordOne.equals(newPasswordTwo)){
			return dictionaryUtil.appOut("1039", params.get("language_abbreviation"), "false");
		}
	//	int update_count=meDao.updatePassword(user_id,phone,newPasswordTwo);
		else if(meDao.updatePassword(user_id,phone,SHA256.encode(newPasswordTwo, newSalt) ,newSalt )==1){
			return dictionaryUtil.appOut(params.get("code"), params.get("language_abbreviation"), "true");
		}else{
			return dictionaryUtil.appOut("1017", params.get("language_abbreviation"), "false");
		}
	}
}
