package com.hongzhi.zswh.app_v6.service;


import com.hongzhi.zswh.app_v6.dao.V6NewsDao;
import com.hongzhi.zswh.app_v6.entity.NEWS;
import com.hongzhi.zswh.app_v6.entity.V6LatestNewsEntity;
import com.hongzhi.zswh.app_v6.entity.V6NewsImageEntity;
import com.hongzhi.zswh.app_v6.entity.V6VideoEntity;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 1, 2016    1:15:36 PM
 */
@Service
public class V6NewsService {
    
    @Autowired
    private V6NewsDao v6NewsDao;

    /**   Twitter : @taylorwang789
     * Creat time : Jun 1, 2016    1:19:29 PM
     * @param competition_id
     * @param club_id
     * @param page_number
     * @return
     */
    public List<V6LatestNewsEntity> latestNews(String competition_id, String club_id, Integer page_number,String news_type) {
        List<Integer> range_type  = new ArrayList<>();
        List<Integer> competition_id_list = new ArrayList<>();
        List<Integer> club_id_list = new ArrayList<>();
        range_type.add(NEWS.RANGE_PUBLIC);
        if(!ObjectUtil.isEmpty(competition_id)){
            competition_id_list = toIntegerList(competition_id);
        }
        if(!ObjectUtil.isEmpty(club_id)){
            club_id_list = toIntegerList(club_id);
        }
        if(ObjectUtil.isEmpty(page_number)){
            page_number=1;
        }

        String head = NEWS.PIC_HEAD ;
        List<String>  news_type_list = new ArrayList<>();
        if(news_type.toLowerCase().equals("latest")){
            news_type_list.add(NEWS.TYPE_DOCUMENT);
            news_type_list.add(NEWS.TYPE_IMAGES);
//            head=NEWS.PIC_HEAD;
        }else{
            news_type_list.add(NEWS.TYPE_VIDEO);
//            head=NEWS.VIDEO_HEAD;
        }

        int startRow = (page_number-1)*NEWS.PAGE_SIZE;
        List<V6LatestNewsEntity> news_list = v6NewsDao.latestNewsList(news_type_list,range_type,competition_id_list,club_id_list,startRow,NEWS.PAGE_SIZE,2);
        for(int i=0;i<news_list.size();i++){
            news_list.get(i).setPreview_image( news_list.get(i).getPreview_image().replaceFirst("HEAD", head));
        }
        return  news_list;
    }

    public Object  newsList(SessionProperty properties,Integer page_number, String news_type){
        String competition_id = "";
        String club_id = "";
        if(!ObjectUtil.isEmpty(properties) && !ObjectUtil.isEmpty(properties.getUser_id())  ){
            String[] user_id_array = getUserCompetitionAndClub(Integer.parseInt(properties.getUser_id()));
            competition_id = user_id_array[0] ;
            club_id = user_id_array[1] ;
        }
        Map<String,Object>  map = new HashMap<>();
        List<V6LatestNewsEntity> news_list =  latestNews(competition_id, club_id, page_number, news_type);
        List<Integer> news_id_list = new ArrayList<>();
        for(int i=0;i<news_list.size();i++){
            news_id_list.add(news_list.get(i).getNews_id());
        }
        List<V6NewsImageEntity> image_list = new ArrayList<>();
        if(news_id_list.size()>0){
            image_list = v6NewsDao.newsImageByNewsIdList(news_id_list);
        }
        String head = NEWS.PIC_HEAD ;
//        if(news_type.toLowerCase().equals("latest")){
//            head=NEWS.PIC_HEAD;
//        }else{
//            head=NEWS.VIDEO_HEAD;
//        }
        for(int i=0;i<image_list.size();i++){
            image_list.get(i).setMedia_url( image_list.get(i).getMedia_url().replaceFirst("HEAD", head));
            news_list.get(news_id_list.indexOf(image_list.get(i).getNews_id())).getImage_list().add(image_list.get(i));
        }

        if(news_type.toLowerCase().equals("latest")){
            map.put("news", news_list );
        }else{
            List<V6VideoEntity> video_list = new ArrayList<>();
            for(int i=0;i<news_list.size();i++){
                V6VideoEntity videoEntity = new V6VideoEntity();
                videoEntity.setNews_id(news_list.get(i).getNews_id());
                videoEntity.setNews_type(news_list.get(i).getNews_type());
                videoEntity.setNews_title(news_list.get(i).getNews_title());
                videoEntity.setNews_subtitle(news_list.get(i).getNews_subtitle());
                videoEntity.setNews_abstract(news_list.get(i).getNews_abstract());
                videoEntity.setCreate_time(news_list.get(i).getCreate_time());
                videoEntity.setWatch_count(news_list.get(i).getWatch_count());
                videoEntity.setPreview_image(news_list.get(i).getPreview_image().replace("HEAD", head));
                if(news_list.get(i).getImage_list().isEmpty()){
                    videoEntity.setVideo_url("");
                }else{
                    videoEntity.setVideo_url(news_list.get(i).getImage_list().get(0).getMedia_information());
                }
                video_list.add(videoEntity);
            }
            map.put("news", video_list);
        }
        return map;
    }
    
    
    
    public List<Integer> toIntegerList(String str){
        List<String> str_list = Arrays.asList(str.split(","));
        List<Integer> integer_list = new ArrayList<>();
        for(int i=0;i<str_list.size();i++){
            integer_list.add(Integer.parseInt(str_list.get(i)));
        }
        return integer_list;
    }



    
    //查用户的赛事和俱乐部,
    //数组第一个元素为用户参加的所有赛事的 赛事ID, 以逗号分隔
    //数组第二个元素为用户加入的俱乐部 俱乐部ID 
    public  String[]  getUserCompetitionAndClub(Integer user_id){
    	String[] str = new String[2];
		List<String> userCompetition = v6NewsDao.getUserCompetition(user_id);
		String competion_id="";
    	for(int i =0;i<userCompetition.size();i++){
    		if(i==(userCompetition.size()-1)){
    			competion_id+=userCompetition.get(i);
    		}else{
    			competion_id+=userCompetition.get(i)+",";
    		}
    		
    	}
		String club_id="";
    	List<String> userClub = v6NewsDao.getUserClub(user_id);
    	for(int i =0;i<userClub.size();i++){
    		if(i==(userClub.size()-1)){
    			club_id+=userClub.get(i);
    		}else{
    			club_id+=userClub.get(i)+",";
    		}
    		
    	}
    	str[0]=competion_id;
    	str[1]=club_id;
        return str;
    }

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 12, 2016    1:49:01 PM
     * @param properties
     * @param page_number
     * @return
     */
    public Object newsListRecommend(SessionProperty properties, Integer page_number, Integer news_id) {
        // TODO Auto-generated method stub
        Map<String,Object>  map  = (Map<String, Object>) newsList(properties,page_number,"video");
        List<V6VideoEntity> video_list = (List<V6VideoEntity>) map.get("news");
        List<Integer> video_id_list = new ArrayList<>();
        for(int i=0;i<video_list.size();i++){
            if(video_list.get(i).getNews_id().equals(news_id)){
                video_id_list.add(i);
            }
        }
        for(int i=0;i<video_id_list.size();i++){
            video_list.remove( video_id_list.get(i).intValue() );
        }
        map.put("news", video_list);
        return map;
    }


}
