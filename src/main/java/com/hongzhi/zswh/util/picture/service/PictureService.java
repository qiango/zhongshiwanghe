package com.hongzhi.zswh.util.picture.service;

import com.google.gson.JsonObject;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.encryption.SHA256;
import com.hongzhi.zswh.util.picture.dao.PictueUpload;
import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;


/**   Twitter : @taylorwang789 
 * Creat time : May 30, 2016    2:29:15 PM
 */
@Service
public class PictureService {
    
    private static String basePath = "";

    @Autowired
    private PictueUpload pictueUpload;

    
    static{
        Properties prop = new Properties();
        String resource = "config.properties";
        InputStream is ;
        try {
            is = Resources.getResourceAsStream(resource);
            prop.load(is);
            basePath=prop.getProperty("pictureBasePath");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String picUpload(HttpServletRequest request,String head) throws IllegalStateException, IOException {
        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver( request.getSession().getServletContext());
        // 检查form中是否有enctype="multipart/form-data"
        String file_name = "";
        if (multipartResolver.isMultipart(request)) {
            // 将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next() .toString());
                if (file != null) {
                    String originName = file.getOriginalFilename();
                    String fileOriginalName = file.getOriginalFilename().replaceAll(",", "-").replaceAll(" ", "-");

                    String type = "" ;
                    if(fileOriginalName.lastIndexOf(".") == -1){
                        type = ".jpg";
                    }else{
                        type = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
                    }

                    file_name =  newFilePath(basePath) + System.currentTimeMillis() +"_"+ SHA256.getSalt(6) + type;
                    // 上传
                    file.transferTo(new File( basePath + file_name ));
                    // save to db
                    pictueUpload.saveUploadPictureName(originName,file_name);
                }
            }
        }

        Map<String,String> map = new HashMap<>();
        map.put("picUrl",head+file_name);
        return ObjectUtil.jsonOut(map);
    }
    
    public String picUpload(HttpServletRequest request) throws IllegalStateException, IOException {
        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver( request.getSession().getServletContext());
        // 检查form中是否有enctype="multipart/form-data"
        String file_name = "";
        if (multipartResolver.isMultipart(request)) {
            // 将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next() .toString());
                if (file != null) {
                    String originName = file.getOriginalFilename();
                    String fileOriginalName = file.getOriginalFilename().replaceAll(",", "-").replaceAll(" ", "-");

                    String type = "" ;
                    if(fileOriginalName.lastIndexOf(".") == -1){
                        type = ".jpg";
                    }else{
                        type = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
                    }
                    
                    file_name =  newFilePath(basePath) + System.currentTimeMillis() +"_"+ SHA256.getSalt(6) + type;
                    // 上传
                    file.transferTo(new File( basePath + file_name ));
                    // save to db 
                    pictueUpload.saveUploadPictureName(originName,file_name);
                }
            }
        }
        JsonObject obj = new JsonObject();
        obj.addProperty("picUrl",file_name);
        return obj.toString();
    }

    public List<Picture> picUploadMore(HttpServletRequest request) throws IllegalStateException, IOException {
        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver( request.getSession().getServletContext());
        // 检查form中是否有enctype="multipart/form-data"
        List<Picture> pictures = new ArrayList<>();
        String file_name = "";
        if (multipartResolver.isMultipart(request)) {
            // 将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 获取multiRequest 中所有的文件名
//            Iterator iter = multiRequest.getFileNames();
            List<MultipartFile> files = multiRequest.getFiles("ClubActivity");
            int i=1;
//            while (iter.hasNext()) {
            for (int j = 0; j < files.size(); j++) {
//                MultipartFile file = multiRequest.getFile(iter.next() .toString());
                MultipartFile file = files.get(j);
                if (file != null) {
                    String originName = file.getOriginalFilename();
                    String fileOriginalName = file.getOriginalFilename().replaceAll(",", "-").replaceAll(" ", "-");

                    String type = "" ;
                    if(fileOriginalName.lastIndexOf(".") == -1){
                        type = ".jpg";
                    }else{
                        type = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
                    }

                    file_name =  newFilePath(basePath) + System.currentTimeMillis() +"_"+ SHA256.getSalt(6) + type;
                    // 上传
                    File newFile = new File( basePath + file_name );
                    file.transferTo(newFile);
                    // save to db
                    pictueUpload.saveUploadPictureName(originName,file_name);

                    Picture picture = new Picture();
                    BufferedImage image = ImageIO.read(newFile);
                    picture.setWidth(image.getWidth());
                    picture.setHeight(image.getHeight());
                    picture.setIndex(i++);
                    picture.setOriginName(originName);
                    picture.setNewName(file_name);
                    pictures.add(picture);
                }
            }
        }
        return pictures;
    }

    /**   Twitter : @taylorwang789 
     * Creat time : May 30, 2016    2:48:58 PM
     * @param request
     * @param response
     * @param p
     */
    public void showPic(HttpServletRequest request, HttpServletResponse response, String p) {
        try{ 
              String realName = basePath+p;
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
    
    public  String newFilePath(String pathHead){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMdd");
        Date date = new Date(System.currentTimeMillis());
        String  newDir = "/"+sdf.format(date)+"/" ;
        pathHead +=newDir;
        File file = new File(pathHead);
        if(!file.exists()){
            file.mkdirs();
        }
        return newDir;
    }

}
