package com.hongzhi.zswh.util.picture.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hongzhi.zswh.util.basic.dictionaryDao.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.picture.service.PictureService;
import org.springframework.web.servlet.ModelAndView;

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

    @ResponseBody
    @RequestMapping("/appPicUpload")  //  picutre upload
    public String  appspringUpload(HttpServletRequest request) throws IllegalStateException, IOException {
        // return file upload path
        return picService.picUpload(request,"/pic.htmls?p=").toString();
    }

    @RequestMapping("/webPicUpload")  //  picutre upload
    public String  webspringUpload(HttpServletRequest request, Model model) throws IllegalStateException, IOException {
        String picUrl = picService.picUpload(request).toString();
        model.addAttribute("picUrl",picUrl);
        return "/init/upload";
    }

    @RequestMapping("/pic")  // p : path 
    public void showPic(HttpServletRequest request,HttpServletResponse response,String p) {
        picService.showPic(request,response,p);
    }
    


}
