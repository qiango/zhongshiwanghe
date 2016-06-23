package com.hongzhi.zswh.back.advertisement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongzhi.zswh.back.advertisement.dao.AdvertisementDao;
import com.hongzhi.zswh.back.advertisement.entity.ADVERTISEMENT;
import com.hongzhi.zswh.back.advertisement.entity.AdParam;
import com.hongzhi.zswh.back.advertisement.entity.AdvertisementEntity;
import com.hongzhi.zswh.back.advertisement.entity.AdvertisementImageEntity;
import com.hongzhi.zswh.back.advertisement.entity.AdvertisementPropertiesEntity;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.page.PageModel;

/**   Twitter : @taylorwang789 
 * Creat time : May 31, 2016    3:48:58 PM
 */
@Service
public class AdService {
    
    @Autowired
    private AdvertisementDao adDao;

    /**   Twitter : @taylorwang789 
     * Creat time : May 31, 2016    4:11:30 PM
     * @param adParam
     * @param properties 
     * @return
     */
    public Object list(AdParam adParam, SessionProperty properties) {
        PageModel pageModel = new PageModel(adParam.getPage_number(), adParam.getPage_size(),properties.getLanguage() ,"/ad/list.htmls" );
        pageModel.setOther(adParam.getAd_name());
        pageModel.setTotalDataCount(adDao.listCount(pageModel));
        pageModel.setResult(adDao.list(pageModel));
        pageModel.setPageParam(Arrays.asList("ad_name"));
        pageModel.setPageParamVal(Arrays.asList(adParam.getAd_name()));
        pageModel.setPageString(pageModel.generatePageStr());
        return pageModel;
    }

    /**   Twitter : @taylorwang789 
     * Creat time : May 31, 2016    4:34:10 PM
     * @param adParam
     * @param properties
     * @return
     */
    @Transactional
    public Object newAds(AdParam adParam, SessionProperty properties) {
        
        adParam.VAd_name();
        adParam.VAd_type();

        List<String> image_list = null;
        List<String> image_text_list = null;
        List<String> image_information_link = null;
        List<String> image_hight_width = null;
        List<String> property_code_list = null;
        List<String> property_value_list = null;
        
        if(!ObjectUtil.isEmpty(adParam.getSubmit_file())){
            image_list = Arrays.asList(adParam.getSubmit_file().split(","));
        }
        if ("1".equals(adParam.getAd_type()) || "2".equals(adParam.getAd_type())) {
        	 image_information_link= Arrays.asList(adParam.getInformation_link_ad().split("#\\$#"));
		}else if("3".equals(adParam.getAd_type())){
			image_information_link= Arrays.asList(adParam.getInformation_link().split(","));
		}
//        if(!ObjectUtil.isEmpty(adParam.getInformation_link())){
//            image_information_link= Arrays.asList(adParam.getInformation_link().split(","));
//        }
        
        
        if ("1".equals(adParam.getAd_type()) || "2".equals(adParam.getAd_type())) {
        	image_text_list= Arrays.asList(adParam.getImage_information().split("#\\$#"));
		}else if("3".equals(adParam.getAd_type())){
			image_text_list= Arrays.asList(adParam.getImage_text().split(","));
		}
        
      /*  if(!ObjectUtil.isEmpty(adParam.getImage_text())){
        	image_text_list= Arrays.asList(adParam.getImage_text().split(","));
        }   */     
        if(!ObjectUtil.isEmpty(adParam.getProperty_code())){
            property_code_list = Arrays.asList(adParam.getProperty_code().split(","));
        }
        if(!ObjectUtil.isEmpty(adParam.getProperty_value())){
            property_value_list = Arrays.asList(adParam.getProperty_value().split(","));
        }
        if(!ObjectUtil.isEmpty(adParam.getAd_width_high())){
        	image_hight_width = Arrays.asList(adParam.getAd_width_high().split(","));
        }
        adParam.setAd_status(ADVERTISEMENT.STATUS_PUBLISH);
        adDao.saveNewAd(adParam);
        if(!ObjectUtil.isEmpty(image_list)){
            List<AdvertisementImageEntity> image_entity_list = new ArrayList<>();
            for(int i=0;i<image_list.size();i++){
                AdvertisementImageEntity temp_image = new AdvertisementImageEntity();
                temp_image.setAd_id(adParam.getAd_id());
                temp_image.setImage_url(image_list.get(i));
               // temp_image.setInformation_link(ObjectUtil.getProperty(image_information_link.get(i), "").toString());
                temp_image.setImage_text(i<(image_text_list==null ? 0 : image_text_list.size()) ? image_text_list.get(i):"");
                temp_image.setInformation_link(i<(image_information_link==null ? 0 :image_information_link.size()) ? image_information_link.get(i):"");
                 // temp_image.setImage_text(image_text_list.get(i));
                 String[] height_width = ObjectUtil.getProperty(image_hight_width.get(0), "*").toString().split("[*]");
               
                temp_image.setImage_width(Integer.parseInt(height_width[0]));
                temp_image.setImage_height(Integer.parseInt(height_width[1]));
                image_entity_list.add(temp_image);
            }
            adDao.saveImages(image_entity_list);
        }
        if(!ObjectUtil.isEmpty(property_code_list) && !ObjectUtil.isEmpty(property_value_list) ){
            List<AdvertisementPropertiesEntity> property_list = new ArrayList<>();
            for(int i=0;i<property_code_list.size();i++){
                AdvertisementPropertiesEntity  temp_property = new AdvertisementPropertiesEntity();
                temp_property.setAd_id(adParam.getAd_id());
                temp_property.setProperty_code(property_code_list.get(i));
                temp_property.setProperty_value(property_value_list.get(i));
                property_list.add(temp_property);
            }
            adDao.savePropertys(property_list);
        }
        return null;
    }

    /**   Twitter : @taylorwang789 
     * Creat time : May 31, 2016    6:22:13 PM
     * @param parseInt
     * @return
     */
    public Object modifyLoad(int ad_id) {
        AdvertisementEntity ad = adDao.getAdvertisement(ad_id).get(0);
        List<AdvertisementImageEntity> adImages = adDao.getAdImages(ad_id);
        for(int i =0;i<adImages.size();i++){
        	AdvertisementImageEntity advertisementImageEntity = adImages.get(i);
        	advertisementImageEntity.setAd_width_high();
        }
        ad.setImage_list(adImages);
        
        ad.setProperty_list(adDao.getAdProperties(ad_id));
        //位置列表
        ad.setAdLocationList(adDao.adLocationList());
        //像素列表
		List<Map<String, Object>> adPixelList = adDao.adPixelList();
		List<Map<String, String>> pixel_list =new ArrayList<>();
		for (Map<String, Object> map : adPixelList) {
			HashMap<String, String> piexlmap = new HashMap<>();
			piexlmap.put("pixel", map.get("width").toString()+"*"+map.get("height").toString());
			pixel_list.add(piexlmap);
		}        
        ad.setPixel_list(pixel_list);
        
        
        ad.sortProperty();
      
        Map<String,Object> out = new HashMap<>();
        out.put("adEntity", ad);
        return out;
    }

    /**   Twitter : @taylorwang789 
     * Creat time : May 31, 2016    10:08:04 PM
     * @param adParam
     * @return
     * @throws HongZhiException 
     */
    @Transactional
    public Object modifySave(AdParam adParam) throws HongZhiException {
        adParam.VAd_id();
      //  adParam.VAd_name();
       // adParam.VAd_type();

        List<String> image_list = null;
        List<String> image_information_link = null;
        List<String> image_text_list = null;
        List<String> image_hight_width = null;
        List<String> property_code_list = null;
        List<String> property_value_list = null;
        
        if(!ObjectUtil.isEmpty(adParam.getSubmit_file())){
            image_list = Arrays.asList(adParam.getSubmit_file().split(","));
        }
        //根据ad_type 获取图片的链接
        if("3".equals(adParam.getAd_type())){
        	 if(!ObjectUtil.isEmpty(adParam.getInformation_link())){
 	            image_information_link= Arrays.asList(adParam.getInformation_link().split(","));
 	        }
        	 
             if(!ObjectUtil.isEmpty(adParam.getImage_text())){
             	image_text_list= Arrays.asList(adParam.getImage_text().split(","));
             } 
        	
        }else{
	        if(!ObjectUtil.isEmpty(adParam.getInformation_link_ad())){
	            image_information_link= Arrays.asList(adParam.getInformation_link_ad().split("#\\$#"));
	        }
	        
	        if(!ObjectUtil.isEmpty(adParam.getImage_information())){
             	image_text_list= Arrays.asList(adParam.getImage_information().split("#\\$#"));
             } 
        }
 
        if(!ObjectUtil.isEmpty(adParam.getProperty_code())){
            property_code_list = Arrays.asList(adParam.getProperty_code().split(","));
        }
        if(!ObjectUtil.isEmpty(adParam.getProperty_value())){
            property_value_list = Arrays.asList(adParam.getProperty_value().split(","));
        }
        if(!ObjectUtil.isEmpty(adParam.getAd_width_high())){
        	image_hight_width = Arrays.asList(adParam.getAd_width_high().split(","));
        }
        
        adParam.setAd_status(ADVERTISEMENT.STATUS_PUBLISH);
        adDao.updateAdById(adParam);
        if(!ObjectUtil.isEmpty(image_list)){
            List<AdvertisementImageEntity> image_entity_list = new ArrayList<>();
            for(int i=0;i<image_list.size();i++){
                AdvertisementImageEntity temp_image = new AdvertisementImageEntity();
                temp_image.setAd_id(adParam.getAd_id());
                
                temp_image.setImage_url(image_list.get(i));
             //   int size = image_text_list.size();
                temp_image.setImage_text(i<(image_text_list==null ? 0 : image_text_list.size()) ? image_text_list.get(i):"");
                temp_image.setInformation_link(i<(image_information_link==null ? 0 :image_information_link.size()) ? image_information_link.get(i):"");
                
             
                String[] height_width = ObjectUtil.getProperty(image_hight_width.get(0), "*").toString().split("[*]");
              
                temp_image.setImage_width(Integer.parseInt(height_width[0]));
                temp_image.setImage_height(Integer.parseInt(height_width[1]));
                
                image_entity_list.add(temp_image);
            }
            adDao.deleteImagesByNewsId(adParam.getAd_id());
            adDao.saveImages(image_entity_list);
        }
        if(!ObjectUtil.isEmpty(property_code_list) && !ObjectUtil.isEmpty(property_value_list) ){
            List<AdvertisementPropertiesEntity> property_list = new ArrayList<>();
            for(int i=0;i<property_code_list.size();i++){
                AdvertisementPropertiesEntity  temp_property = new AdvertisementPropertiesEntity();
                temp_property.setAd_id(adParam.getAd_id());
                temp_property.setProperty_code(property_code_list.get(i));
                temp_property.setProperty_value(property_value_list.get(i));
                property_list.add(temp_property);
            }
            adDao.deletePropertysByNewsId(adParam.getAd_id());
            adDao.savePropertys(property_list);
        }
        return null;
    }

	public Object updateAdvertisementById(String ad_id) throws HongZhiException {

		int temp = adDao.updateAdvertisementById(ad_id);
		if (1 == temp) {
			return null;
		}else{
			throw new HongZhiException("1034");
		}
	}


	public  Object insertLoad(){
		List<Map<String, Object>> adLocationList = adDao.adLocationList();
		List<Map<String, Object>> adPixelList = adDao.adPixelList();
		List<Map<String, String>> pixel_list =new ArrayList<>();
		for (Map<String, Object> map : adPixelList) {
		//map.get("height");
		 //map.get("width");
			//String pixel = height+"*"+width;
			//String index = (String) map.get("index");
			HashMap<String, String> piexlmap = new HashMap<>();
			
			piexlmap.put("pixel", map.get("width").toString()+"*"+map.get("height").toString());
			pixel_list.add(piexlmap);
		}
		HashMap<String, Object> out = new HashMap<>();
		out.put("adLocationList", adLocationList);
		out.put("pixel_list", pixel_list);
		return out;
	}

	public Object selectAdDetailById(String ad_id) {
		
		
		return adDao.selectAdDetailById(ad_id);
	}



}
