package com.hongzhi.zswh.back.competitionApplication.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.back.competitionApplication.entity.CompetitionApplication;
import com.hongzhi.zswh.back.competitionApplication.entity.Form;
import com.hongzhi.zswh.util.page.PageModel;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 24, 2016    3:44:56 PM
 */
public interface FormDao {
	
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 24, 2016    3:53:15 PM
	 * @param pageModel , other :  competition_name  like '%other%' 
	 * @return
	 */
	List<Form> listFormByPage(PageModel pageModel);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 24, 2016    3:51:42 PM
	 * @param pageModel
	 * @return
	 */
	int listFormByPageCount(PageModel pageModel);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    4:20:00 PM
	 * @param string
	 * @return
	 */
	int insertCompetitionApplication(@Param("value_sql") String value_sql);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    4:28:33 PM
	 * @param parseInt
	 * @return
	 */
	int deleteByCompeitionId(int competition_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 25, 2016    5:04:16 PM
	 * @param parseInt
	 * @return
	 */
	List<CompetitionApplication> selectByCompeitionID(int parseInt);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 8, 2016    11:37:06 AM
	 * @param parseInt
	 * @return
	 */
	String getCompetitionName(int competition_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 29, 2016    1:54:09 PM
	 * @param parseInt
	 * @return
	 */
	Map<String, Object> userLevel(int user_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 29, 2016    2:14:04 PM
	 * @param parseInt
	 * @return
	 */
	List<Integer> userRole(int parseInt);

}
