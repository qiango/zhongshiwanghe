package com.hongzhi.zswh.app_v6.service;

import com.hongzhi.zswh.app_v6.entity.ADVERTISEMENT;
import com.hongzhi.zswh.app_v6.entity.AdvertisementEntity;
import com.hongzhi.zswh.app_v6.entity.AdvertisementImageEntity;
import com.hongzhi.zswh.app_v6.dao.V6AdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**   Twitter : @taylorwang789 
 * Creat time : Jun 1, 2016    9:58:12 AM
 */
@Service
public class V6AdService {
    
    @Autowired
    private V6AdDao v6AdDao;

    /**   Twitter : @taylorwang789 
     * Creat time : Jun 1, 2016    9:59:19 AM
     * @return
     */
    public Object homeAd() {
        List<AdvertisementEntity> ad_list = v6AdDao.homeAdList(ADVERTISEMENT.HOME_MIN,ADVERTISEMENT.HOME_MAX);
        List<Integer> ad_id_list = new ArrayList<>();
//        List<Integer> ad_location_list = new ArrayList<>();
        for(int i=0;i<ad_list.size();i++){
            ad_id_list.add(ad_list.get(i).getAd_id());
//            ad_location_list.add(ad_list.get(i).getAd_location());
        }
        List<AdvertisementImageEntity> image_list = v6AdDao.imageListByAdIdList(ad_id_list);
        for(int i=0;i<image_list.size();i++){
            ad_list.get( ad_id_list.indexOf( image_list.get(i).getAd_id() ) ).getImage_list().add(image_list.get(i));
        }

        return ad_list;
    }
    

      /**   Twitter : @taylorwang789 
     * Creat time : Jun 1, 2016    10:19:41 AM
     * @return
     */
    public Object shoppingAd() {
        // TODO Auto-generated method stub
        return null;
    }

}
