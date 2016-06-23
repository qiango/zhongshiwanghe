package com.hongzhi.zswh.back.information.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.back.club.entity.WorldCity;
import com.hongzhi.zswh.back.information.entity.Information;
import com.hongzhi.zswh.back.information.entity.InformationImage;
import com.hongzhi.zswh.util.page.PageModel;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 23, 2016    5:06:36 PM
 */
public interface InformationDao {
	
      /**   Twitter : @taylorwang789 
     * Creat time : Mar 23, 2016    5:11:18 PM
     *  query by page 
     * @param pageModel
     * @return  imformation list 
     */
    List<Information>  listInformationByPage(PageModel pageModel);
    int listInformationByPageCount(PageModel pageModel);
    
    List<WorldCity>  listWorldCity(int parent_id);
    
    int insertInformation(Information information);
    
    /**   Twitter : @taylorwang789 
	 * Creat time : Mar 23, 2016    3:24:28 PM
	 * @param club_id
	 * @param language
	 * @return club
	 */
    Information selectById(@Param("information_id") int information_id);
    
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
	int delete(String information_id);
	
	List<InformationImage>  listInformationImagesById(@Param("information_id") String information_id);
	
	int insertInformationImages(InformationImage image);
	

}
