package com.hongzhi.zswh.app_v2.profile.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.hongzhi.zswh.app_v2.profile.dao.AppUserProfileDao;
import com.hongzhi.zswh.util.basic.DictionaryUtil;

/**
 * Twitter : @taylorwang789 Creat time : Apr 7, 2016 2:59:36 PM
 */
@Service
public class AppUserProfileService {

	@Autowired
	private AppUserProfileDao appUserProfileDao;
	@Autowired
	private DictionaryUtil dictionaryUtil;

	// private static String basePath =
	// "/Users/taylor/eclipseProjects/springmvc_mybatis_demo/src/main/webapp/upload/";
	private static String basePath = "";

	public String profilePicUpload(HttpServletRequest request, String user_id)
			throws IllegalStateException, IOException {
		if (basePath.length() == 0) {
			basePath = dictionaryUtil.getCodeValue("uploadPath", "root", "zh");
		}
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		String file_name = "";
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next()
						.toString());
				if (file != null) {
					String fileOriginalName = file.getOriginalFilename();
					
					/**
					 * 日期:2016-04-12
					 * @author Saxon
					 * android 上传文件是没有后缀的,需要try catch处理没有后缀问题
					 */
					String type = ".jpg";
					try { 
						type = file.getOriginalFilename().substring(fileOriginalName.lastIndexOf("."));
					} catch (Exception e) {
					}
					
					Date curr_date = new Date();
					file_name = "profile" + curr_date.getTime() + "" + type;
					// +file.getOriginalFilename();
					String path = basePath + file_name;
					// 上传
					file.transferTo(new File(basePath + file_name));
					int set_count = appUserProfileDao.saveProfilePic(
							Integer.parseInt(user_id),
							"/showPic.htmls?picName=" + file_name);
					if (set_count == 1) {
						return dictionaryUtil.appOut("0",
								"/showPic.htmls?picName=" + file_name);
					}
				}
			}
		}
		return dictionaryUtil.appOut("1041", "", "/showPic.htmls?picName="
				+ file_name);
	}

}
