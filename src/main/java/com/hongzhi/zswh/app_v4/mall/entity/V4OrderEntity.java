package com.hongzhi.zswh.app_v4.mall.entity;

import java.sql.Timestamp;
import java.util.List;

import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**   Twitter : @taylorwang789 
 * Creat time : May 13, 2016    2:06:47 PM
 */
public class V4OrderEntity {
                       
        private Integer    id             ;
        private String     code           ;
        private Integer    user_id        ;
        private Timestamp  create_time    ;
        private Integer    status         ;
        private Integer    delivery       ;
        private String     price          ;
        private Double     goods_price    ;
        private Integer    discount_id    ;
    	private Integer    order_id	   ;
    	private Integer    goods_id	   ;
    	private Integer    properties_id ;
    	private Integer    total_counts  ;
    	private Integer    total_count  ;
    	private Integer    effect_count  ;


        private List<V4OrderGoodsEntity>  order_goods ;
        
    	public String VId() throws HongZhiException {
    		return ExcepUtil.verify(id, "1068").toString();
    	}
    	public String VCode() throws HongZhiException {
    		return ExcepUtil.verify(code, "1060").toString();
    	}
    	public Integer VUser_id() throws HongZhiException {
    		return (Integer) ExcepUtil.verify(user_id, "1000");
    	}
    	public Integer VStatus() throws HongZhiException {
    		return (Integer) ExcepUtil.verify(status, "1061");
    	}
    
    	public Integer VDiscount_id() throws HongZhiException {
    		return (Integer)ExcepUtil.verify(discount_id, "1062");
    	}
    	public Integer VDelivery() throws HongZhiException {
    		return (Integer)ExcepUtil.verify(delivery, "1067");
    	}
    	public Double VPrice() throws HongZhiException {
    		return Double.parseDouble(ExcepUtil.verify(price, "1063").toString());
    	}
    	public Integer VGoods_id() throws HongZhiException {
    		return (Integer)ExcepUtil.verify(goods_id, "1064");
    	}
    	public Integer VProperties_id() throws HongZhiException {
    		return (Integer)ExcepUtil.verify(properties_id, "1065");
    	}
    	public Integer VTotal_counts() throws HongZhiException {
    		return (Integer)ExcepUtil.verify(total_counts, "1066");
    	}
    	public Integer VTotal_count() throws HongZhiException {
            return (Integer)ExcepUtil.verify(total_count, "1066");
        }
    	
		public Integer getOrder_id() {
			return order_id;
		}
		public void setOrder_id(Integer order_id) {
			this.order_id = order_id;
		}
		public Integer getGoods_id() {
			return goods_id;
		}
		public void setGoods_id(Integer goods_id) {
			this.goods_id = goods_id;
		}
		public Integer getProperties_id() {
			return properties_id;
		}
		public void setProperties_id(Integer properties_id) {
			this.properties_id = properties_id;
		}
		public Integer getTotal_counts() {
			return total_counts;
		}
		public void setTotal_counts(Integer total_counts) {
			this.total_counts = total_counts;
		}
		public Integer getEffect_count() {
			return effect_count;
		}
		public void setEffect_count(Integer effect_count) {
			this.effect_count = effect_count;
		}
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Integer getUser_id() {
			return user_id;
		}

		public void setUser_id(Integer user_id) {
			this.user_id = user_id;
		}

		public Timestamp getCreate_time() {
			return create_time;
		}

		public void setCreate_time(Timestamp create_time) {
			this.create_time = create_time;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Integer getDelivery() {
			return delivery;
		}

		public void setDelivery(Integer delivery) {
			this.delivery = delivery;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public Integer getDiscount_id() {
			return discount_id;
		}

		public void setDiscount_id(Integer discount_id) {
			this.discount_id = discount_id;
		}

		public List<V4OrderGoodsEntity> getOrder_goods() {
			return order_goods;
		}

		public void setOrder_goods(List<V4OrderGoodsEntity> order_goods) {
			this.order_goods = order_goods;
		}
        public Double getGoods_price() {
            return goods_price;
        }
        public void setGoods_price(Double goods_price) {
            this.goods_price = goods_price;
        }
        public Integer getTotal_count() {
            return total_count;
        }
        public void setTotal_count(Integer total_count) {
            this.total_count = total_count;
        }
        
		

}
