package com.hongzhi.zswh.back.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.back.basic.service.MenuService;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 28, 2016    7:08:09 PM
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@ResponseBody
	@RequestMapping("/list")
	public String listallMenu(String menu_name){
		return menuService.allMenus(menu_name);
	}

}
