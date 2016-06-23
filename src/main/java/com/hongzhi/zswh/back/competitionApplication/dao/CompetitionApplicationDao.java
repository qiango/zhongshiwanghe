package com.hongzhi.zswh.back.competitionApplication.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.back.competitionApplication.entity.Competition;


/**   Twitter : @taylorwang789 
 * Creat time : Mar 25, 2016    4:04:34 PM
 */
public interface CompetitionApplicationDao {
	
	List<Competition> withoutForms(@Param("user_id") String user_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 29, 2016    11:02:58 AM
	 * @param i 
	 * @param user_id_list 
	 * @return
	 */
	int userCompetitionApply(@Param("comp_id") int comp_id, @Param("user_id_list") List<Integer> user_id_list);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 29, 2016    3:32:55 PM
	 * @param parseInt
	 * @param user_id_list
	 * @return
	 */
	int userCompetitionRefuse(@Param("comp_id") int comp_id, @Param("user_id_list") List<Integer> user_id_list);

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 29, 2016    3:36:14 PM
	 * @param parseInt
	 * @param user_id_list
	 * @param reason
	 * @return
	 */
	int userRefuseReason(@Param("comp_id") int comp_id, @Param("user_id_list") List<Integer> user_id_list, @Param("reason") String reason);

	/**   Twitter : @taylorwang789 
	 * Creat time : May 6, 2016    3:15:15 PM
	 * @param parseInt
	 * @return
	 */
	String getCompetitionName(int parseInt);
	
	

}
