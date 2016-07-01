package com.hongzhi.zswh.app_v6.service;

import com.hongzhi.zswh.app_v6.dao.V6ClubDao;

import com.hongzhi.zswh.app_v6.entity.UserDetailEntity;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by XieCaij on 2016/6/28.
 */
@Service
public class V6ClubService {
    @Autowired
    private V6ClubDao v6ClubDao;


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


    public Object saveClubPic(String picUrl, String club_id,String user_id) throws HongZhiException {
        if (!ObjectUtil.isEmpty(club_id)){
           List list =  v6ClubDao.selectClubAdmin(club_id,user_id);//查看是否是俱乐部管理员
            if (list.size()==0){
                throw new HongZhiException("1078");
            }
            int clubPic =   v6ClubDao.saveClubPic(picUrl,club_id);
            if (1 == clubPic){
                return null;
            } else {
                throw new HongZhiException("1069");
            }
        }else {
            throw new HongZhiException("1021");
        }
    }

}
