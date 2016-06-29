package com.hongzhi.zswh.back.news.dao;

import com.hongzhi.zswh.back.news.entity.NewsImageEntity;
import com.hongzhi.zswh.back.news.entity.NewsParam;
import com.hongzhi.zswh.back.news.entity.NewsRangeEntity;
import com.hongzhi.zswh.util.page.PageModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NewsDao {

	int logicDelete(@Param("news_id") String news_id);

	Map<String, Object> modifyLoad(@Param("news_id") String news_id);

	int modifySave(NewsParam newsParam);

	int listNewsByPageCount(PageModel pageModel);

//	List<NewsParam> listNewsByPage(PageModel pageModel);

	int newSave(NewsParam newsParam);

	void saveNewsMultimedia(@Param("imageList") List<NewsImageEntity> list);
	
	List<Map<String,Object>> findPictureBynews_id(@Param("news_id") String news_id);
	
	int logicDeletePictureBynew_id(@Param("news_id") int news_id);

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 3, 2016    1:28:21 PM
     * @return
     */
    List<Map<String, Object>> newsCategoryList();

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 3, 2016    1:38:51 PM
     * @return
     */
    List<Map<String, Object>> listCompetitiony();

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 3, 2016    1:39:01 PM
     * @return
     */
    List<Map<String, Object>> listClub();

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 3, 2016    2:20:52 PM
     * @param range_list
     */
    int saveNewsRange(@Param("rangeList") List<NewsRangeEntity> range_list);

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 3, 2016    2:33:26 PM
     * @param category_id_list
     * @param news_id
     */
    int saveNewsCategory(@Param("categoryList") List<Integer> category_id_list, @Param("newsID") Integer news_id);

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 3, 2016    2:48:09 PM
     * @param news_id
     */
    int logicDeleteCategoryByNewsID(Integer news_id);

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 3, 2016    2:48:49 PM
     * @param news_id
     */
    int logicDeleteRangeByNewsID(Integer news_id);
    /**
     * zhurenkui
     * @param news_id
     * @return
     */
    Map<String ,Object> findNewsRangeById(@Param("news_id") String news_id);

    int selectMaxByWeights_order();


    List<NewsParam>  listNewsByPage(@Param("pageModel")PageModel pageModel,@Param("max") int max);
}
