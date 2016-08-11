package com.hongzhi.zswh.app_1_3.service;


import com.hongzhi.zswh.app_1_3.dao.ClubDao;
import com.hongzhi.zswh.app_1_3.entity.Club;
import com.hongzhi.zswh.app_1_3.entity.ClubManageEntity;
import com.hongzhi.zswh.app_1_3.entity.ClubQueryEntity;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;
import com.hongzhi.zswh.util.picture.service.PictureService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;


@Service("app_1_3_ClubService")
public class ClubService {
    @Autowired
    private ClubDao clubDao;
    @Autowired
    private PictureService picService;


    /**
     * @param property
     * @return
     * @throws HongZhiException
     */
    public Object loadClubManage(SessionProperty property) throws HongZhiException {

        ClubQueryEntity club_info = clubDao.clubInfo(Integer.parseInt(property.getUser_id()), property.getLanguage());

        if (ObjectUtil.isEmpty(club_info)) {
            club_info = new ClubQueryEntity();

        }

        ClubManageEntity out = new ClubManageEntity();

        List is_admin_list = clubDao.selectClubAdmin(club_info.getClub_id(), property.getUser_id());//查看是否是俱乐部管理员

        if (is_admin_list.size() == 0) {
            out.setUser_level("99");//普通会员
        } else {
            out.setUser_level("0");//管理员
        }

        String defaultVal = "0";
        out.setClub_member_number(ObjectUtil.getProperty(club_info.getClub_member(), defaultVal).toString());
        out.setSports_camp_number(ObjectUtil.getProperty(club_info.getSports_camp(), defaultVal).toString());
        out.setJoin_club_status(ObjectUtil.getProperty(club_info.getJoin_club_status(), defaultVal).toString());
        out.setJoin_club_status_name(ObjectUtil.getProperty(club_info.getJoin_club_status_name(), "").toString());
        out.setActivity_number(defaultVal);
        out.setComplete_training_number(defaultVal);
        out.setContinuous_training_number(defaultVal);
        out.setTotal_training_number(defaultVal);
        out.setRanking(defaultVal);
        out.setComprehensive_integral(defaultVal);
        out.setMy_club_ranking(club_info.getMy_club_rank());
        out.setClub(clubDao.userClub(Integer.parseInt(property.getUser_id())));

        String club_id = "0";

        if (!ObjectUtil.isEmpty(club_info.getClub_id())) {
            club_id = club_info.getClub_id();
        }

        out.setClub_ranking_list(clubDao.clubRanking("   limit 3   ", Integer.parseInt(club_id)));

        Map<String, Object> map = new HashMap<>();
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
        out.setClub_ranking_list(clubDao.clubRanking("   limit 3   ", 0));
        Map<String, Object> map = new HashMap<>();
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
    public Object setClub(HttpServletRequest request, SessionProperty property, Club club) throws HongZhiException {

        club.Vclub_name();
        club.Vcity_id();

        List<String> club_name_list = clubDao.checkClubName(club.getClub_name());

        if (club_name_list.size()> 0){ //俱乐部名不能重复
            throw new HongZhiException("1083");
        }

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

        String club_applicant_name = clubDao.selectUserInfoByUserId(property.getUser_id());//查询创建俱乐部人

        club.setClub_applicant_name(club_applicant_name);
        club.setClub_status("2");//刚组建时候俱乐部状态：筹备中

        clubDao.saveSetClub(club); //保存club表

        int temp = clubDao.saveUserDetail(club);//保存user_detail表
        if (1 == temp) {
            return null;
        } else {
            throw new HongZhiException("1011");//保存失败
        }

    }

    /**
     *
     * @param property
     * @return
     * @throws HongZhiException
     */
    public Object queryClubAdmin(SessionProperty property) throws HongZhiException {

            Map<String,Object>  club_map = clubDao.queryClubLevel( property.getUser_id());//查看是否是俱乐部管理员
            String club_id = club_map.get("club_id").toString();

            List<Integer> club_member_list  = clubDao.selectClubMembers(club_id);

            List<Map<String, Objects>> list = clubDao.selectCompetitionByUserId(Integer.valueOf( property.getUser_id()));

            if(list.size()>0){
                club_map.put("user_competition_status","1");//1表示有赛事没结束
            }else {
                club_map.put("user_competition_status","0");//0表示没有赛事
            }
            club_map.put("club_member_size",club_member_list.size() == 0 ? 0:club_member_list.size());

            return club_map;

    }

    public Object listClub(String city_id) {
        Map<String, Object> map = new HashMap<>();

        map.put("club_list", clubDao.listClub());

        if (!ObjectUtil.isEmpty(city_id)){
            map.put("club_list_city_id", clubDao.listClubByCityId(city_id));
        }else{
            map.put("club_list_city_id", new ArrayList<>());
        }


        return map;

    }


}
