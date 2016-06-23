package com.hongzhi.zswh.back.basic.service;

import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongzhi.zswh.util.basic.DictionaryUtil;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 8, 2016    12:43:39 PM
 */
@Service
public class ShowPicService {

	private static String basePath = "";
	@Autowired
	private DictionaryUtil dictionaryUtil;
	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 8, 2016    12:44:30 PM
	 * @param request
	 * @param response
	 * @param picName
	 */
	public void showPic(HttpServletRequest request, HttpServletResponse response, String picName) {
		try{ 
			  if(basePath.length()==0){
					basePath = dictionaryUtil.getCodeValue("uploadPath", "root", "zh");
				}
		        String str = picName; 
		        String realName = basePath+str; 
		        FileInputStream fs = new FileInputStream(realName); 
		        int b=fs.available(); //得到文件大小    
		        byte data[]=new byte[b];    
		        fs.read(data);  //读数据    
		        response.setContentType("image/*"); //设置返回的文件类型    
		        ServletOutputStream toClient=response.getOutputStream(); //得到向客户端输出二进制数据的对象    
		        toClient.write(data);  //输出数据    
		        toClient.flush(); 
		        toClient.close(); 
		        fs.close(); 
		    }catch(Exception e){ 
		      e.printStackTrace(); 
		    } 
	}

}
