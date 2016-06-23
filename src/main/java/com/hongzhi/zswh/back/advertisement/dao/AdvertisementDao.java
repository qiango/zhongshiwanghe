package com.hongzhi.zswh.back.advertisement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.back.advertisement.entity.AdParam;
import com.hongzhi.zswh.back.advertisement.entity.AdvertisementEntity;
import com.hongzhi.zswh.back.advertisement.entity.AdvertisementImageEntity;
import com.hongzhi.zswh.back.advertisement.entity.AdvertisementPropertiesEntity;
import com.hongzhi.zswh.util.page.PageModel;

/**   Twitter : @taylorwang789 
 * Creat time : May 31, 2016    4:24:18 PM
 */
public interface AdvertisementDao {

    /**   Twitter : @taylorwang789 
     * Creat time : May 31, 2016    4:36:38 PM
     * @param adParam
     */
    int saveNewAd(AdParam adParam);


    /**   Twitter : @taylorwang789 
     * Creat time : May 31, 2016    4:54:34 PM
     * @param property_list
     */
    int savePropertys(@Param("propertyList") List<AdvertisementPropertiesEntity> property_list);

    /**   Twitter : @taylorwang789 
     * Creat time : May 31, 2016    5:09:58 PM
     * @param pageModel
     * @return
     */
    int listCount(PageModel pageModel);

    /**   Twitter : @taylorwang789 
     * Creat time : May 31, 2016    5:10:05 PM
     * @param pageModel
     * @return
     */
    List<AdvertisementEntity> list(PageModel pageModel);

    /**   Twitter : @taylorwang789 
     * Creat time : May 31, 2016    6:23:46 PM
     * @param news_id
     * @return
     */
    List<AdvertisementEntity> getAdvertisement(@Param("ad_id") Integer ad_id);

    /**   Twitter : @taylorwang789 
     * Creat time : May 31, 2016    6:28:35 PM
     * @param news_id
     * @return
     */
    List<AdvertisementImageEntity> getAdImages(@Param("ad_id") Integer ad_id);

    /**   Twitter : @taylorwang789 
     * Creat time : May 31, 2016    6:38:06 PM
     * @param image_entity_list
     */
    int saveImages(@Param("images") List<AdvertisementImageEntity> image_entity_list);


    /**   Twitter : @taylorwang789 
     * Creat time : May 31, 2016    6:44:30 PM
     * @param news_id
     * @return
     */
    List<AdvertisementPropertiesEntity> getAdProperties(@Param("ad_id") Integer ad_id);


    /**   Twitter : @taylorwang789 
     * Creat time : Jun 1, 2016    9:46:55 AM
     * @param adParam
     */
    int updateAdById(AdParam adParam);


    /**   Twitter : @taylorwang789 
     * Creat time : Jun 1, 2016    9:47:05 AM
     * @param ad_id
     */
    int deleteImagesByNewsId(Integer ad_id);


    /**   Twitter : @taylorwang789 
     * Creat time : Jun 1, 2016    9:47:14 AM
     * @param ad_id
     */
    int deletePropertysByNewsId(Integer ad_id);


	int updateAdvertisementById(String ad_id);
	
	
	List<Map<String,Object>> adLocationList();
	
	List<Map<String,Object>> adPixelList();


	List<Map<String,Object>> selectAdDetailById(String ad_id);

    

}
