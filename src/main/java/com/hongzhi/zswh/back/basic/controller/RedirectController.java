package com.hongzhi.zswh.back.basic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hongzhi.zswh.back.basic.service.ShowPicService;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 23, 2016    6:17:47 PM
 */
@Controller
public class RedirectController {
	
	@Autowired
	private  ShowPicService showPicService;
	
	@RequestMapping("/redirect")
	public String  redirect(String url){
		url=url.replace("-","/");
		return url;
	}
	
	@RequestMapping("/showPic")
	public void showPic(HttpServletRequest request,HttpServletResponse response,String picName) {
		showPicService.showPic(request,response,picName);
	}
	

}
