package com.hongzhi.zswh.app_v6.dao;


import com.hongzhi.zswh.app_v6.entity.V6LatestNewsEntity;
import com.hongzhi.zswh.app_v6.entity.V6NewsImageEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 1, 2016    1:20:28 PM
 */
public interface V6NewsDao {

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 1, 2016    1:27:46 PM
     * @param range_type
     * @param club_id_list 
     * @param competition_id_list
     * @return
     */
    List<V6LatestNewsEntity> latestNewsList(
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
    List<V6NewsImageEntity> newsImageByNewsIdList(@Param("newsIDList") List<Integer> news_id_list);

    List<String> getUserCompetition(@Param("user_id") Integer user_id);
    
    List<String> getUserClub(@Param("user_id") Integer user_id);

}
