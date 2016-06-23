package com.hongzhi.zswh.app_v4.mall.entity;

import java.util.ArrayList;
import java.util.List;


/**   Twitter : @taylorwang789 
 * Creat time : May 13, 2016    2:24:31 PM
 */
public class V4GoodsEntity {
	
    private Integer id  ;
    private String  goods_code  ;
    private Integer category   ;
    private String  name    ;
    private String  goods_describe   ;
//    private String  status  ;
//    private Timestamp  create_time ;
//    private Integer  create_user ;
    
    private List<V4GoodsPicture>  pictures = new ArrayList<>();
    private List<V4GoodsPrice> price = new ArrayList<>();
    
    
   
 // price 
    public void dbPrice( List<V4GoodsProperties> all_properties ,List<V4GoodsPrice>  all_price  ){
//        List<V4GoodsProperties> all_properties = goodsDao.getAllPropertiesByGoodsId(id);
//        List<V4GoodsPrice>  all_price = goodsDao.getAllPriceByGoodsId(id);
        List<V4GoodsProperties> all_properties_public = new ArrayList<>(); 
        for(int i=0;i<all_properties.size();i++){
            if(all_properties.get(i).getProperties_id().equals(0)){
                all_properties_public.add(all_properties.get(i));
            }
        }
        all_properties.removeAll(all_properties_public);
        
        List<Integer> price_propertes_id = new ArrayList<>();
        for(int i=0;i<all_price.size();i++){
            all_price.get(i).recalculatePrice();
            price_propertes_id.add(all_price.get(i).getProperties_id());
        }
        
        for(int i=0; i<all_properties.size(); i++){
            for(int j=0;j<all_properties_public.size();j++){
                all_price.get( price_propertes_id.indexOf(all_properties.get(i).getProperties_id())  ).getProperties().add(all_properties_public.get(j));
            }
            all_price.get( price_propertes_id.indexOf(all_properties.get(i).getProperties_id())  ).getProperties().add(all_properties.get(i));
//            V4GoodsProperties prop = all_properties.get(i);
//            List<V4GoodsProperties>  prop_list =all_price.get( price_propertes_id.indexOf(all_properties.get(i).getProperties_id())  ).getProperties(); 
//            prop_list.add(prop);
//            all_price.get( price_propertes_id.indexOf(all_properties.get(i).getProperties_id())  ).setProperties(prop_list);
        }
        if(all_properties.size()==0 && all_properties_public.size()!=0){
            for(int j=0;j<all_properties_public.size();j++){
                all_price.get( price_propertes_id.indexOf(all_properties_public.get(j).getProperties_id())  ).getProperties().add(all_properties_public.get(j));
            }
        }
        
        setPrice(all_price); 
    }
    
    
    
 // getter & setter    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getGoods_code() {
        return goods_code;
    }
    public void setGoods_code(String goods_code) {
        this.goods_code = goods_code;
    }
    public Integer getCategory() {
        return category;
    }
    public void setCategory(Integer category) {
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
   
    public String getGoods_describe() {
        return goods_describe;
    }

    public void setGoods_describe(String goods_describe) {
        this.goods_describe = goods_describe;
    }

    public List<V4GoodsPicture> getPictures() {
        return pictures;
    }
    public void setPictures(List<V4GoodsPicture> pictures) {
        this.pictures = pictures;
    }
    public List<V4GoodsPrice> getPrice() {
        return price;
    }
    public void setPrice(List<V4GoodsPrice> price) {
        this.price = price;
    }

}
