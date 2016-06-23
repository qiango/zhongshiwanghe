package com.hongzhi.zswh.app_v5.entity;



import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 13, 2016    2:06:47 PM
 */
public class V5OrderEntity {
                       
	    private Integer    id             ;
        private String     price          ;
    	private Integer    goods_id	   ;
    	private Integer    total_counts  ;//购票数量
        private String 	   coupon_id;//优惠类型ID以，拼接的字符串
        private Integer    user_id        ;
        private String     code           ;
        private Integer    status         ;
        private Integer    properties_id ;
        private Double     goods_price    ;//商品单价
        private Integer    order_id	   ;
        
        
    	public String VCoupon_id() throws HongZhiException {
    		return ExcepUtil.verify(coupon_id, "").toString();
    	}
        
    	public Double VPrice() throws HongZhiException {
    		return Double.parseDouble(ExcepUtil.verify(price, "1063").toString());
    	}
    	public Integer VGoods_id() throws HongZhiException {
    		return (Integer)ExcepUtil.verify(goods_id, "1064");
    	}
    
    	public Integer VTotal_counts() throws HongZhiException {
    		return (Integer)ExcepUtil.verify(total_counts, "1066");
    	}
    	
    	public Integer VUser_id() throws HongZhiException {
    		return (Integer) ExcepUtil.verify(user_id, "1000");
    	}
    	
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public Integer getGoods_id() {
			return goods_id;
		}
		public void setGoods_id(Integer goods_id) {
			this.goods_id = goods_id;
		}
		public Integer getTotal_counts() {
			return total_counts;
		}
		public void setTotal_counts(Integer total_counts) {
			this.total_counts = total_counts;
		}
		public String getCoupon_id() {
			return coupon_id;
		}
		public void setCoupon_id(String coupon_id) {
			this.coupon_id = coupon_id;
		}
		public Integer getUser_id() {
			return user_id;
		}
		public void setUser_id(Integer user_id) {
			this.user_id = user_id;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getProperties_id() {
			return properties_id;
		}
		public void setProperties_id(Integer properties_id) {
			this.properties_id = properties_id;
		}
		public Double getGoods_price() {
			return goods_price;
		}
		public void setGoods_price(Double goods_price) {
			this.goods_price = goods_price;
		}
		public Integer getOrder_id() {
			return order_id;
		}
		public void setOrder_id(Integer order_id) {
			this.order_id = order_id;
		}
    
    	

		

}
