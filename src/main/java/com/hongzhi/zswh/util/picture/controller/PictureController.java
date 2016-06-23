package com.hongzhi.zswh.util.picture.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.picture.service.PictureService;

/**   Twitter : @taylorwang789 
 * Creat time : May 30, 2016    2:29:55 PM
 */
@Controller
public class PictureController {
    @Autowired
    private PictureService picService;
    
    
    @ResponseBody
    @RequestMapping("/picUpload")  //  picutre upload 
    public String  springUpload(HttpServletRequest request) throws IllegalStateException, IOException {
            // return file upload path
            return picService.picUpload(request).toString();
    }
    
    @RequestMapping("/pic")  // p : path 
    public void showPic(HttpServletRequest request,HttpServletResponse response,String p) {
        picService.showPic(request,response,p);
    }
    


}
