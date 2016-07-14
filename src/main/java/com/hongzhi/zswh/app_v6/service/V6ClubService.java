package com.hongzhi.zswh.app_v6.service;

import com.hongzhi.zswh.app_v6.dao.V6ClubDao;
import com.hongzhi.zswh.app_v6.entity.ClubManageEntity;
import com.hongzhi.zswh.app_v6.entity.ClubQueryEntity;
import com.hongzhi.zswh.app_v6.entity.UserDetailEntity;
import com.hongzhi.zswh.app_v6.entity.V6Club;
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
    public String OutOfClub(String userId)throws HongZhiException{

/*        if (ObjectUtil.isEmpty(club_id)) {
            throw new HongZhiException("1021");
        }*/

     /*   List is_club_admin =  v6ClubDao.selectClubAdmin(club_id,userId);//查看是否是俱乐部管理员
        if (is_club_admin.size()!=0){
            throw new HongZhiException("1079");//俱乐部管理员不能退出
        }*/
        List<Map<String, Objects>> list = v6ClubDao.selectCompetitionByUserId(Integer.valueOf(userId));
        if(list.size()>0){
            //不允许退出
            throw new HongZhiException("1077");
        }
        Map<String,Object> clubMap =  v6ClubDao.queryClubLevel(null,userId);
        if ("0".equals(clubMap.get("user_level"))){

            String club_id =  clubMap.get("club_id").toString();
            List<Integer> club_member_list  =   v6ClubDao.selectClubMembers(club_id);

            if (club_member_list.size()==1){
                //只剩下管理员一个人可以退出俱乐部，且俱乐部解散
                String club_status = "3";
                v6ClubDao.updateClubStatus(club_status,club_id);
            }else if (club_member_list.size()!=1){

                throw new HongZhiException("1079");//俱乐部管理员不能退出
            }

        }

        UserDetailEntity userDetail = v6ClubDao.selectUserDetailEntity(Integer.valueOf(userId));
        if(null==userDetail){
            return "success";
        }
        v6ClubDao.insetIntoUserDetail(userDetail);
        v6ClubDao.deleteUserDetailByUserId(Integer.valueOf(userId));

         if ("1".equals(clubMap.get("join_club_status"))){
             throw new HongZhiException("1080");//您已取消，欢迎下次再来
        } else {
             return "success";
         }

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
                throw new HongZhiException("1078");//普通无权限转让
            }
                v6ClubDao.transferClubByUserId(club_id,userId,"99");//管理员转变成普通用户
                v6ClubDao.transferClubByUserId(club_id,user_id,"0");//普通用户转变成管理员

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
            Map<String,Object>  clubMap = v6ClubDao.queryClubLevel(club_id, property.getUser_id());//查看是否是俱乐部管理员
          //  Map<String,Object>  club_list = v6ClubDao.queryClubLevel("44", "280");//查看是否是俱乐部管理员

//            Map<String,Object> map = new HashMap<>();
//            map.put("user_level", club_list.get(0).("user_level"));//判断当前用户级别
//            map.put("join_club_status", club_list.get(0).("join_club_status"));//当前用户参加的俱乐部的状态
         /*   if (list.size() == 0) {
                map.put("user_level",99);//普通会员
            }else {
                map.put("user_level",0);//管理员
            }*/
            List<Map<String, Objects>> list = v6ClubDao.selectCompetitionByUserId(Integer.valueOf( property.getUser_id()));
            if(list.size()>0){
                clubMap.put("user_competition_status","1");//1表示有赛事没结束
            }else {
                clubMap.put("user_competition_status","0");//0表示没有赛事
            }
            return clubMap;
        }else {
          throw new HongZhiException("1021");//俱乐部为空
         }
    }

    public Object clubMembers(SessionProperty property) {
        Map<String,Object> club = v6ClubDao.ownClubMemberCount(Integer.parseInt(property.getUser_id()));
       // String memberCount  = club.get("club_member").toString();
        List<Map<String,Object>>   club_member = v6ClubDao.ownClubMemberList((Integer)club.get("user_id"),(Integer)club.get("club_id"));

        Map<String,Object> map = new HashMap<>();
        map.put("manager_flag",  ObjectUtil.getProperty(club.get("user_level") ,"99"));
        map.put("club_member_list", club_member);
        return map;
    }

    public Object loadClubManage(SessionProperty property) throws HongZhiException {

        ClubQueryEntity clubInfo = v6ClubDao.clubInfo( Integer.parseInt(property.getUser_id()),property.getLanguage());
        if(ObjectUtil.isEmpty(clubInfo)){
            clubInfo = new ClubQueryEntity();

        }
        ClubManageEntity out = new ClubManageEntity();
        List list = v6ClubDao.selectClubAdmin(clubInfo.getClub_id(), property.getUser_id());//查看是否是俱乐部管理员
        // List list = v6ClubDao.selectClubAdmin("32", "375");//查看是否是俱乐部管理员
        if (list.size() == 0) {
            out.setUser_level("99");//普通会员
        }else {
            out.setUser_level("0");//管理员
        }
        String defaultVal = "0";
        out.setClub_member_number(ObjectUtil.getProperty(clubInfo.getClub_member(), defaultVal).toString());
        out.setSports_camp_number(ObjectUtil.getProperty(clubInfo.getSports_camp(), defaultVal).toString());
        out.setJoin_club_status(ObjectUtil.getProperty(clubInfo.getJoin_club_status(), defaultVal).toString());
        out.setJoin_club_status_name(ObjectUtil.getProperty(clubInfo.getJoin_club_status_name(), "").toString());
        out.setActivity_number(defaultVal);
        out.setComplete_training_number(defaultVal);
        out.setContinuous_training_number(defaultVal);
        out.setTotal_training_number(defaultVal);
        out.setRanking(defaultVal);
        out.setComprehensive_integral(defaultVal);
        out.setMy_club_ranking(clubInfo.getMy_club_rank());
        out.setClub(v6ClubDao.userClub(Integer.parseInt(property.getUser_id())));
        String club_id ="0";
        if(!ObjectUtil.isEmpty(clubInfo.getClub_id())){
            club_id=clubInfo.getClub_id();
        }
        out.setClub_ranking_list(v6ClubDao.clubRanking("   limit 3   " ,Integer.parseInt(club_id) ));
        Map<String,Object> map = new HashMap<>();
        map.put("club_interface_data", out);
        return map;
    }
    public Object loadClubManageNotLogIn() {

        ClubManageEntity out = new ClubManageEntity();
        String defaultVal = "0";
        out.setClub_member_number(defaultVal);
        out.setSports_camp_number(defaultVal);
        out.setJoin_club_status(defaultVal);
        out.setJoin_club_status_name(defaultVal);
        out.setActivity_number(defaultVal);
        out.setComplete_training_number(defaultVal);
        out.setContinuous_training_number(defaultVal);
        out.setTotal_training_number(defaultVal);
        out.setRanking(defaultVal);
        out.setComprehensive_integral(defaultVal);
        out.setMy_club_ranking(defaultVal);
        out.setClub(null);
        out.setClub_ranking_list(v6ClubDao.clubRanking("   limit 3   " ,0));
        Map<String,Object> map = new HashMap<>();
        map.put("club_interface_data", out);
        return map;
    }

    /**
     * 组建俱乐部
     *
     * @param request
     * @param property
     * @param club
     * @return
     * @throws HongZhiException
     */
    public Object setClub(HttpServletRequest request, SessionProperty property, V6Club club) throws HongZhiException {

        club.Vclub_name();
        club.Vcity_id();

        String pic_url = null;
        try {
            pic_url = picService.picUpload(request).toString();
        } catch (IOException e) {
            throw new HongZhiException("1011");
        }

        //解析调用图片上传后返回的json字符串
        JSONObject jsonObject = new JSONObject(pic_url);
        pic_url = jsonObject.getString("picUrl");

        if (!ObjectUtil.isEmpty(pic_url)) {
            club.setClub_pic(pic_url);
        }
        club.setUser_id(Integer.valueOf(property.getUser_id()));

        String club_applicant_name = v6ClubDao.selectUserInfoByUserId(property.getUser_id());//查询创建俱乐部人

        club.setClub_applicant_name(club_applicant_name);
        club.setClub_status("2");//刚组建时候俱乐部状态：筹备中

        v6ClubDao.saveSetClub(club); //保存club表

        int temp = v6ClubDao.saveUserDetail(club);//保存user_detail表
        if (1 == temp) {
            return null;
        } else {
            throw new HongZhiException("1011");//保存失败
        }

    }
}
