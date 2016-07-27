package com.hongzhi.zswh.back.basic.controller;

import com.hongzhi.zswh.back.basic.service.MenuService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**   Twitter : @taylorwang789 
 * Creat time : Mar 28, 2016    7:08:09 PM
 */
@Controller
@RequestMapping("/dic")
public class DictionaryController {
	
	@Autowired
    private DictionaryUtil dictionaryUtil;

	@ResponseBody
	@RequestMapping("/refresh")
	public String refresh(){
        dictionaryUtil.init();
        return  "OK";
	}

}
