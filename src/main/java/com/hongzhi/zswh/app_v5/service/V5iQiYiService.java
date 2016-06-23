package com.hongzhi.zswh.app_v5.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hongzhi.zswh.app_v5.dao.V5NewsDao;
import com.hongzhi.zswh.app_v5.entity.IQIYI;
import com.hongzhi.zswh.app_v5.entity.NEWS;
import com.hongzhi.zswh.app_v5.entity.V5VideoiQiYiEntity;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 6, 2016    4:53:03 PM
 */
@Service
public class V5iQiYiService {
    
    @Autowired
    private V5iQiYiHttp  http ;
    
    @Autowired
    private V5NewsDao newsDao ;

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 6, 2016    5:48:12 PM
     * @param news_id
     * @return
     */
    public Object getVideo(String news_id) {
        V5VideoiQiYiEntity  video = newsDao.getVideoByNewsID(Integer.parseInt(news_id));
        video.setChannel_image(video.getChannel_image().replace("HEAD", NEWS.PIC_HEAD));
        Map<String,String>  paramMap = new HashMap<>();
        paramMap.put("file_ids", video.getFile_id());
        JsonObject video_info = http.getJsonObject(IQIYI.URL_VIDEOS_INFO, paramMap, true);
//        video.setPreview_image(video_info.get("data").getAsJsonArray().get(0).getAsJsonObject().get("img").getAsString());
        video.setWatch_count(video_info.get("data").getAsJsonArray().get(0).getAsJsonObject().get("playCount").getAsInt());
        
        paramMap.remove("file_ids");
        paramMap.put("file_id", video.getFile_id());
        JsonObject video_url = http.getJsonObject(IQIYI.URL_URLLIST, paramMap, true);
        if(video_url.get("code").getAsString().equals("A00000")){
           video.getVideo_url().put(IQIYI.MP4_1, video_url.get("data").getAsJsonObject().get("mp4").getAsJsonObject().get("1").getAsString());
           video.getVideo_url().put(IQIYI.MP4_2, video_url.get("data").getAsJsonObject().get("mp4").getAsJsonObject().get("2").getAsString());
           video.getVideo_url().put(IQIYI.M3U8_1, video_url.get("data").getAsJsonObject().get("m3u8").getAsJsonObject().get("1").getAsString());
           video.getVideo_url().put(IQIYI.M3U8_2, video_url.get("data").getAsJsonObject().get("m3u8").getAsJsonObject().get("2").getAsString());
           video.getVideo_url().put(IQIYI.M3U8_96, video_url.get("data").getAsJsonObject().get("m3u8").getAsJsonObject().get("96").getAsString());
        }
        JsonParser parser = new JsonParser();
        List<String> second_query = Arrays.asList(IQIYI.MP4_1,IQIYI.MP4_2);
        for( int i =0;i<second_query.size();i++ ){
             String final_url = http.query(video.getVideo_url().get(second_query.get(i)), null);
             final_url=final_url.substring(final_url.indexOf("data:")+5,final_url.lastIndexOf("};"));
             JsonObject json_obj = parser.parse(final_url).getAsJsonObject();
             video.getVideo_url().put( second_query.get(i) , json_obj.get("l").getAsString());
        }
        
        video.setChannel_image(video.getChannel_image().replace("HEAD", NEWS.PIC_HEAD  ));
        video.setPreview_image(video.getPreview_image().replace("HEAD", NEWS.PIC_HEAD  ));
        
        return video;
    }
        
    
    public V5VideoiQiYiEntity getVideoForShare(String news_id) {
        V5VideoiQiYiEntity  video = newsDao.getVideoByNewsID(Integer.parseInt(news_id));
        video.setChannel_image(video.getChannel_image().replace("HEAD", NEWS.PIC_HEAD  ));
        video.setPreview_image(video.getPreview_image().replace("HEAD", NEWS.PIC_HEAD  ));
        Map<String,String>  paramMap = new HashMap<>();
        paramMap.put("file_id", video.getFile_id() );
        JsonObject video_info = http.getJsonObject(IQIYI.URL_FULL_STATUS , paramMap, true);
        JsonObject video_info_watch_count = http.getJsonObject(IQIYI.URL_VIDEOS_INFO, paramMap, true);
        video.setWatch_count(video_info_watch_count.get("data").getAsJsonArray().get(0).getAsJsonObject().get("playCount").getAsInt());

        Map<String,String> video_url = new HashMap<>();
        video_url.put("swfurl", video_info.get("data").getAsJsonObject().get("swfurl").getAsString() );
        video.setVideo_url(video_url);

        return video;
    }
        
    
    
        
}
