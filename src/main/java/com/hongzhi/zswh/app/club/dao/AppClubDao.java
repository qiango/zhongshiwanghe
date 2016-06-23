package com.hongzhi.zswh.app.club.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.app.club.entity.SportsCamp;
import com.hongzhi.zswh.app.club.entity.UserSportsCamp;
import com.hongzhi.zswh.util.page.PageModel;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 22, 2016    4:23:23 PM
 */
public interface AppClubDao {
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 22, 2016    4:32:38 PM
	 * @param pageModel  ,  language , other , pageStartRow , pageRecorders
	 * @return 城市,语言,俱乐部名称,申请人,俱乐部成立日期,俱乐部qq群,俱乐部状态
	 */
	List<Map<String,Object>>  listClub();
	int listClubByPageCount(PageModel pageModel);
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 29, 2016    1:08:53 PM
	 * @param parseInt
	 * @return
	 */
	List<SportsCamp> clubSports(int club_id);
	
	Map<String,Object> selectByID(int user_id);
	int saveUserClub(@Param("user_id") Integer user_id, @Param("club_id") Integer club_id);
	List<Map<String, Object>> loadClubManage(Integer user_id);
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 29, 2016    6:29:19 PM
	 * @param user_sports_camp_list
	 * @return
	 */
	int saveUserSportCamp(List<UserSportsCamp> user_sports_camp_list);
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 29, 2016    6:44:29 PM
	 * @param user_id
	 * @return
	 */
	 Map<String,Object> selectClubInfoByUserIdApp(Integer user_id);
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 29, 2016    6:55:50 PM
	 * @param user_id
	 * @return
	 */
	String joinClubStatus(Integer user_id);
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 29, 2016    6:59:50 PM
	 * @param user_id
	 * @return
	 */
	int clubMemberNumber(Integer user_id);
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 29, 2016    7:08:46 PM
	 * @param user_id
	 * @return
	 */
	Map<String, Object> getBasicClubInfo(Integer user_id);
}
