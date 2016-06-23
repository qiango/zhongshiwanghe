package com.hongzhi.zswh.app_v3.verstion.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.app_v3.verstion.dao.VersionDao;
import com.hongzhi.zswh.app_v3.verstion.entity.VersionInfo;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 26, 2016    10:44:34 AM
 */
@Service
public class VersionService {
	
	@Autowired
	private VersionDao  vdao;

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 26, 2016    10:45:55 AM
	 * @return
	 */
	public  VersionInfo  upgradeInfo() {
		VersionInfo  version = vdao.upgradeInfo();
		return version; 
	}

}
