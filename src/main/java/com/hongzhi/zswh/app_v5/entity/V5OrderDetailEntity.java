package com.hongzhi.zswh.app_v5.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**   Twitter : @taylorwang789 
 * Creat time : May 20, 2016    5:47:47 PM
 */
public class V5OrderDetailEntity {
        private Integer order_id;
        private String order_code;
        private String order_status;
        private String order_status_name;
        private String payment;
        private Timestamp create_time;
        private Timestamp pay_time;
        private Double total_price;
        private Double origin_price;
        private Double discount_part;
        private String trace_code;
        private Integer  total_counts;
        private String  order_goods_pic;
        private String order_name;
        private Integer validity_time;
        
        private List<Map<String,Object>>goods_discount_list;

        private Map<String,Object> goods_list_usable;
        private List<Map<String,Object>> goods_list_unusable;
        
        
        public String getOrder_code() {
            return order_code;
        }
        public void setOrder_code(String order_code) {
            this.order_code = order_code;
        }
        public String getOrder_status() {
            return order_status;
        }
        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }
        public String getOrder_status_name() {
            return order_status_name;
        }
        public void setOrder_status_name(String order_status_name) {
            this.order_status_name = order_status_name;
        }
        public String getPayment() {
            return payment;
        }
        public void setPayment(String payment) {
            this.payment = payment;
        }
        public Timestamp getCreate_time() {
            return create_time;
        }
        public void setCreate_time(Timestamp create_time) {
            this.create_time = create_time;
        }
        public Timestamp getPay_time() {
            return pay_time;
        }
        public void setPay_time(Timestamp pay_time) {
            this.pay_time = pay_time;
        }
        public Double getTotal_price() {
            return total_price;
        }
        public void setTotal_price(Double total_price) {
            this.total_price = total_price;
        }
        public Double getOrigin_price() {
            return origin_price;
        }
        public void setOrigin_price(Double origin_price) {
            this.origin_price = origin_price;
        }
        public Double getDiscount_part() {
            return discount_part;
        }
        public void setDiscount_part(Double discount_part) {
            this.discount_part = discount_part;
        }
        public Map<String, Object> getGoods_list_usable() {
            return goods_list_usable;
        }
        public void setGoods_list_usable(Map<String, Object> goods_list_usable) {
            this.goods_list_usable = goods_list_usable;
        }
        public List<Map<String, Object>> getGoods_list_unusable() {
            return goods_list_unusable;
        }
        public void setGoods_list_unusable(List<Map<String, Object>> goods_list_unusable) {
            this.goods_list_unusable = goods_list_unusable;
        }
        public Integer getOrder_id() {
            return order_id;
        }
        public void setOrder_id(Integer order_id) {
            this.order_id = order_id;
        }
        public String getTrace_code() {
            return trace_code;
        }
        public void setTrace_code(String trace_code) {
            this.trace_code = trace_code;
        }
        public Integer getTotal_counts() {
            return total_counts;
        }
        public void setTotal_counts(Integer total_counts) {
            this.total_counts = total_counts;
        }
        public String getOrder_goods_pic() {
            return order_goods_pic;
        }
        public void setOrder_goods_pic(String order_goods_pic) {
            this.order_goods_pic = order_goods_pic;
        }
        public String getOrder_name() {
            return order_name;
        }
        public void setOrder_name(String order_name) {
            this.order_name = order_name;
        }
        public Integer getValidity_time() {
            return validity_time;
        }
        public void setValidity_time(Integer validity_time) {
            this.validity_time = validity_time;
        }
		public List<Map<String,Object>> getGoods_discount_list() {
			return goods_discount_list;
		}
		public void setGoods_discount_list(List<Map<String,Object>> goods_discount_list) {
			this.goods_discount_list = goods_discount_list;
		}
	
        
        
}
