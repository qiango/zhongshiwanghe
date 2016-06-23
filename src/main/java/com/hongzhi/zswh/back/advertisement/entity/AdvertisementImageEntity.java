package com.hongzhi.zswh.back.advertisement.entity;

/**   Twitter : @taylorwang789 
 * Creat time : May 31, 2016    4:13:46 PM
 */
public class AdvertisementImageEntity {
    
    private Integer image_id;
    private Integer ad_id;
    private String image_url;
    private Integer image_index;
    private String information_link;
    private Integer image_height;
    private Integer image_width;
    private String image_text;
    private String ad_width_high;
    
    
    public Integer getImage_id() {
        return image_id;
    }
    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }
    public Integer getAd_id() {
        return ad_id;
    }
    public void setAd_id(Integer ad_id) {
        this.ad_id = ad_id;
    }
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public Integer getImage_index() {
        return image_index;
    }
    public void setImage_index(Integer image_index) {
        this.image_index = image_index;
    }
    public String getInformation_link() {
        return information_link;
    }
    public void setInformation_link(String information_link) {
        this.information_link = information_link;
    }
    public Integer getImage_height() {
        return image_height;
    }
    public void setImage_height(Integer image_height) {
        this.image_height = image_height;
    }
    public Integer getImage_width() {
        return image_width;
    }
    public void setImage_width(Integer image_width) {
        this.image_width = image_width;
    }
	public String getImage_text() {
		return image_text;
	}
	public void setImage_text(String image_text) {
		this.image_text = image_text;
	}
	public String getAd_width_high() {
		return ad_width_high;
	}
	public void setAd_width_high(String ad_width_high) {
		this.ad_width_high = ad_width_high;
	}
    
	public void setAd_width_high() {
		if(image_width!=null || image_height!=null ){
			this.ad_width_high =image_width+"*"+image_height ;
		}
	}
    
    
}
