package com.hongzhi.zswh.app_v4.mall.entity;

import java.sql.Timestamp;

import com.hongzhi.zswh.util.exception.ExcepUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

public class V4ReturnHistory {
	
	private Integer id;
	private Integer user_id;
	private Integer order_id;
	private Integer goods_id;
	private Integer return_count; //退票数量
    private Integer	properties_id ;//商品属性id
    private String	device ;//设备型号
    private String	return_reason ;//退货理由
	private String status;
	private String refund_no;//订单的唯一标识
	private Integer preset_reason_id;//退款列表理由id
    private String	code ;//订单编号
    
    
    public Integer VPreset_reason_id() throws HongZhiException {
  		return (Integer) ExcepUtil.verify(preset_reason_id, "");
  	}
    public Integer VUser_id() throws HongZhiException {
		return (Integer) ExcepUtil.verify(user_id, "1000");
	}
    public Integer VOrder_id() throws HongZhiException {
		return (Integer) ExcepUtil.verify(order_id, "1068");
	}
    public Integer VGoods_id() throws HongZhiException {
		return (Integer)ExcepUtil.verify(goods_id, "1064");
	}
	public Integer VProperties_id() throws HongZhiException {
		return (Integer)ExcepUtil.verify(properties_id, "1065");
	}
	public Integer VReturn_count() throws HongZhiException {
		return (Integer)ExcepUtil.verify(return_count, "1066");
	}
	public String VDevice() throws HongZhiException {
		return ExcepUtil.verify(device, "1058").toString();
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}

	public Integer getReturn_count() {
		return return_count;
	}

	public void setReturn_count(Integer return_count) {
		this.return_count = return_count;
	}

	public Integer getProperties_id() {
		return properties_id;
	}

	public void setProperties_id(Integer properties_id) {
		this.properties_id = properties_id;
	}
	public String getReturn_reason() {
		return return_reason;
	}
	public void setReturn_reason(String return_reason) {
		this.return_reason = return_reason;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getPreset_reason_id() {
		return preset_reason_id;
	}
	public void setPreset_reason_id(Integer preset_reason_id) {
		this.preset_reason_id = preset_reason_id;
	}
	public String getRefund_no() {
		return refund_no;
	}
	public void setRefund_no(String refund_no) {
		this.refund_no = refund_no;
	}
      
}
