package com.hongzhi.zswh.app_v6.service;

import com.hongzhi.zswh.app_v6.dao.V6ClubDao;
import com.hongzhi.zswh.app_v6.entity.UserDetailEntity;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.page.PageModel;
import com.hongzhi.zswh.util.picture.service.PictureService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by XieCaij on 2016/6/28.
 */
@Service
public class V6ClubService {
    @Autowired
    private V6ClubDao v6ClubDao;
    @Autowired
    private PictureService picService;

    /**
     * 会员退出俱乐部，管理员不能退出
     * @param club_id
     * @param userId
     * @return
     * @throws HongZhiException
     */
    public String OutOfClub(String club_id,String userId)throws HongZhiException{
        if (ObjectUtil.isEmpty(club_id)){
           throw new HongZhiException("1021");
        }

        List is_club_admin =  v6ClubDao.selectClubAdmin(club_id,userId);//查看是否是俱乐部管理员
        if (is_club_admin.size()==0){
            throw new HongZhiException("1079");//俱乐部管理员不能退出
        }

        List<Map<String, Objects>> list = v6ClubDao.selectCompetitionByUserId(Integer.valueOf(userId));
        if(list.size()>0){
            //不允许退出
            throw new HongZhiException("1077");
        }

        UserDetailEntity userDetail = v6ClubDao.selectUserDetailEntity(Integer.valueOf(userId));
        if(null==userDetail){
            return "success";
        }
        v6ClubDao.insetIntoUserDetail(userDetail);
        v6ClubDao.deleteUserDetailByUserId(Integer.valueOf(userId));
        return "success";
    }

    /**
     * 俱乐部管理员可以跟换俱乐部头像
     * @param request
     * @param club_id
     * @param user_id
     * @return
     * @throws HongZhiException
     */
    public Object saveClubPic(HttpServletRequest request, String club_id, String user_id) throws HongZhiException {

        if (!ObjectUtil.isEmpty(club_id)){
           List list =  v6ClubDao.selectClubAdmin(club_id,user_id);//查看是否是俱乐部管理员
            if (list.size()==0){
                throw new HongZhiException("1078");
            }

            String picUrl = null;
            try {
                picUrl =  picService.picUpload(request).toString();
            } catch (IOException e) {
                throw new HongZhiException("1011");
            }
            //{"picUrl":"/2016/0705/1467690672409_touxiang.jpeg"}
            if (!ObjectUtil.isEmpty(picUrl)){//解析调用图片上传后返回的json字符串
                JSONObject jsonObject = new JSONObject(picUrl) ;
                picUrl = jsonObject.getString("picUrl");
            }
            int clubPic =   v6ClubDao.saveClubPic(picUrl,club_id);
            if (1 == clubPic){
                Map<String,Object> map = new HashMap<>();
                picUrl = "/pic.htmls?p="+picUrl;
                map.put("pic",picUrl);
                return map;
            } else {
                throw new HongZhiException("1069");
            }
        }else {
            throw new HongZhiException("1021");
        }
    }

    /**
     * 管理员转让
     * @param user_id
     * @param club_id
     * @param userId
     * @return
     * @throws HongZhiException
     */
    public Object transferClub(String user_id, String club_id, String userId) throws HongZhiException {
        if (!ObjectUtil.isEmpty(club_id)){
            if (ObjectUtil.isEmpty(user_id)){//user_id要转让的用户id
                throw new HongZhiException("1005");//用户为空
            }
            List list =  v6ClubDao.selectClubAdmin(club_id,userId);//查看当前用户是否是俱乐部管理员
            if (list.size()==0){
                throw new HongZhiException("1078");//普通管理员无权限转让
            }
                v6ClubDao.transferClubByUserId(club_id,userId,"99");//管理员转变成普通用户
                v6ClubDao.transferClubByUserId(club_id,club_id,"0");//普通用户转变成管理员

        }else {
            throw new HongZhiException("1021");//俱乐部为空
        }

        return null;
    }

    /**
     * 俱乐部列表分页（待定，未完成）
     * @param page_number
     * @param club_name
     * @return
     */
    public Object clubList(String page_number, String club_name) {
        PageModel pageModel = new PageModel(page_number, "20",
                null, "/v6/club/clubList");
        pageModel.setOther(club_name);
        pageModel.setTotalDataCount(v6ClubDao.listClubByPageCount(pageModel));
        pageModel.setResult(v6ClubDao.listClubByPage(pageModel));
        pageModel.setPageParam(Arrays.asList("club_name"));
        pageModel.setPageParamVal(Arrays.asList(club_name));
        return pageModel;
    }

    /**
     * 判断当前用户是否是俱乐部管理员
     * @param property
     * @param club_id
     * @return
     * @throws HongZhiException
     */
    public Object queryClubAdmin(SessionProperty property,String club_id) throws HongZhiException {
        if (!ObjectUtil.isEmpty(club_id)) {
            List list = v6ClubDao.selectClubAdmin(club_id, property.getUser_id());//查看是否是俱乐部管理员
           // List list = v6ClubDao.selectClubAdmin("32", "375");//查看是否是俱乐部管理员
            Map<String,Object> map = new HashMap<>();
            if (list.size() == 0) {
                map.put("user_level",99);//普通会员
            }else {
                map.put("user_level",0);//管理员
            }
            return map;
        }else {
          throw new HongZhiException("1021");//俱乐部为空
         }
    }
}
