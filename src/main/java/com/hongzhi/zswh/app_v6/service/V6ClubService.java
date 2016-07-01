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


    public String OutOfClub(Integer userId)throws HongZhiException{

        List<Map<String, Objects>> list = v6ClubDao.selectCompetitionByUserId(userId);
        if(list.size()>0){
            //不允许退出
            throw new HongZhiException("1077");
        }
        UserDetailEntity userDetail = v6ClubDao.selectUserDetailEntity(userId);
        if(null==userDetail){
            return "success";
        }
        v6ClubDao.insetIntoUserDetail(userDetail);
        v6ClubDao.deleteUserDetailByUserId(userId);
        return "success";
    }


    public Object saveClubPic(String picUrl, String club_id) throws HongZhiException {
        if (!ObjectUtil.isEmpty(club_id)){
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
