package com.hongzhi.zswh.back.club.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.back.club.entity.Club;
import com.hongzhi.zswh.back.club.entity.WorldCity;
import com.hongzhi.zswh.util.page.PageModel;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 22, 2016    4:23:23 PM
 */
public interface ClubDao {
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 22, 2016    4:32:38 PM
	 * @param pageModel  ,  language , other , pageStartRow , pageRecorders
	 * @return 城市,语言,俱乐部名称,申请人,俱乐部成立日期,俱乐部qq群,俱乐部状态
	 */
	List<Map<String,Object>>  listClubByPage(PageModel pageModel);
	int listClubByPageCount(PageModel pageModel);
	
	List<WorldCity>  listWorldCity(int parent_id);
	
	int insertClub(Map<String, String> params);
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    3:24:28 PM
	 * @param club_id
	 * @param language
	 * @return club
	 */
	Club selectById(@Param("club_id") String club_id, @Param("language") String language);
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    3:42:02 PM
	 * @param params
	 * @return  update_count
	 */
	int update(Map<String, String> params);
	
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    3:46:22 PM
	 * @param club_id
	 * @return  delete_count 
	 * actually is update
	 */
	int logicDelete(String club_id);
	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 28, 2016    1:48:22 PM
	 * @param i
	 * @return
	 */
	List<Map<String, Object>> clubMeberRank();

}
