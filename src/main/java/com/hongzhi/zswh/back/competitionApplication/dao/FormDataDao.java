package com.hongzhi.zswh.back.competitionApplication.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.util.page.PageModel;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 25, 2016    5:27:27 PM
 */
public interface FormDataDao {

	
	List<String> formTitles(int competition_id);
	List<Map<String,String>>  formData(PageModel pageModel);
     int  formDataCount(PageModel pageModel);
	/**   Twitter : @taylorwang789 
	 * Creat time : May 7, 2016    5:03:31 PM
	 * @param parseInt
	 * @param user_id_list
	 */
	int deleteRefuseData(@Param("compID") Integer comp_id, @Param("userList") List<Integer> user_id_list);

}
