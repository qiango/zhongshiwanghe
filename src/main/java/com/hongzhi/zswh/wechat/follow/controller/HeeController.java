package com.hongzhi.zswh.wechat.follow.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hongzhi.zswh.wechat.follow.service.HeeService;


@Controller
@RequestMapping("/wechat")
public class HeeController {
	
	@Autowired  
	private HeeService hee;

    /**
     * 通过 OpenID 来获取用户基本信息
     * @author zhurenkui
     * @return 微信返回的用户信息
     */
    @ResponseBody
    @RequestMapping("/getinfo")
    public String getInfo(String openId){
    	
    	try {   		
			return hee.getInfoById(openId);						
		} catch (Exception e) {			
			 e.printStackTrace();
			 return "parameter error";
		}   	
    	
    }

 
    
}
