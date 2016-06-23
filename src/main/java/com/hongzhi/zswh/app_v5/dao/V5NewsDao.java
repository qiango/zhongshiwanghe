package com.hongzhi.zswh.app_v5.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.app_v5.entity.V5LatestNewsEntity;
import com.hongzhi.zswh.app_v5.entity.V5NewsDetailEntity;
import com.hongzhi.zswh.app_v5.entity.V5NewsImageEntity;
import com.hongzhi.zswh.app_v5.entity.V5VideoiQiYiEntity;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 1, 2016    1:20:28 PM
 */
public interface V5NewsDao {

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 1, 2016    1:27:46 PM
     * @param range_type
     * @param club_id_list 
     * @param competition_id_list 
     * @param i 
     * @param pAGE_SIZE 
     * @param page_number 
     * @return
     */
    List<V5LatestNewsEntity> latestNewsList(
            @Param("newsType") List<String> news_type
            , @Param("rangeList") List<Integer> range_type
            , @Param("competitionList") List<Integer> competition_id_list
            , @Param("clubList") List<Integer> club_id_list
            , @Param("startRow") Integer start_row
            , @Param("pageSize") Integer page_size
            , @Param("platformId") Integer platformID
    );

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 2, 2016    5:34:13 PM
     * @param news_id_list
     * @return
     */
    List<V5NewsImageEntity> newsImageByNewsIdList(@Param("newsIDList") List<Integer> news_id_list);

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 3, 2016    10:39:25 AM
     * @param parseInt
     * @return
     */
    V5NewsDetailEntity selectNewsById(int parseInt);
    
    
    List<String> getUserCompetition(@Param("user_id") Integer user_id);
    
    List<String> getUserClub(@Param("user_id") Integer user_id);

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 6, 2016    6:05:19 PM
     * @param parseInt
     * @return
     */
    V5VideoiQiYiEntity getVideoByNewsID(int parseInt);
    
    

}
