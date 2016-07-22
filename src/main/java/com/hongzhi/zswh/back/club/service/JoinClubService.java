package com.hongzhi.zswh.back.club.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongzhi.zswh.app_v3.notification.service.NotificationService;
import com.hongzhi.zswh.back.club.dao.JoinClubDao;
import com.hongzhi.zswh.back.club.entity.JoinClub;
import com.hongzhi.zswh.back.club.entity.ParamObj;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**
 * Twitter : @taylorwang789
 * Creat time : Mar 24, 2016    2:52:39 PM
 */
@Repository
public class JoinClubService {


    @Autowired
    private DictionaryUtil dictionaryUtil;
    @Autowired
    private JoinClubDao joinClubDao;

    @Autowired
    private NotificationService notiSender;
    @Autowired
    private NotificationService notificationService;

    /**
     * Twitter : @taylorwang789
     * Creat time : Mar 24, 2016    2:53:45 PM
     *
     * @param user
     * @return
     */
    public String waittingJoinList(SessionProperty sp) {
        List<Integer> roles = joinClubDao.userRoles(Integer.parseInt(sp.getUser_id()));
        List<JoinClub> waittingToJoin = new ArrayList<>();
        if (roles.contains(8)) {
            waittingToJoin = joinClubDao.ClubJoinUserByAdmin();
        } else {
            waittingToJoin = joinClubDao.ClubJoinUserByAdminID(Integer.parseInt(sp.getUser_id()));
        }
        Map<String, Object> out = new HashMap<>();
        out.put("user_list", waittingToJoin);
        return dictionaryUtil.appOut("0", sp.getLanguage(), out);
    }

    /**
     * Twitter : @taylorwang789
     * Creat time : Mar 24, 2016    3:13:04 PM
     *
     * @param sp
     * @param paramObj
     * @return
     * @throws HongZhiException
     * @throws
     */
    public String apply(SessionProperty sp, ParamObj paramObj) throws HongZhiException {
        int update_count = joinClubDao.applyToJoinClub(Integer.parseInt(paramObj.getUser_id()), Integer.parseInt(paramObj.getClub_id()));
        if (update_count == 1) {
            Map<String, String> map = joinClubDao.getClubName(Integer.parseInt(paramObj.getClub_id()));
            notiSender.sendNoti(Integer.parseInt(sp.getUser_id()), null, Integer.parseInt(paramObj.getUser_id()), "1", dictionaryUtil.getCodeValue("check_join_club_t", "data_alias", sp.getLanguage()) + map.get("club_name") + dictionaryUtil.getCodeValue("join_club_t", "data_alias", sp.getLanguage()));

            if ("2".equals(map.get("club_status"))) {
                List<Integer> club_members_list = joinClubDao.selectClubMembers(paramObj.getClub_id());//根据俱乐部id查询俱乐部当前人数
                if (!ObjectUtil.isEmpty(club_members_list)) {
                    int club_min_member = Integer.valueOf(dictionaryUtil.getCodeValue("club_min_member", "data_alias", sp.getLanguage()));
                    if (club_members_list.size() >= club_min_member) {
                        joinClubDao.updateClubStatusByClubId(paramObj.getClub_id());//组建的俱乐部成员达到三个人（加入状态）时，改变club的状态（2筹备中--99启用）
                    }
                }
            }
            Map<String, Object> user_info_map = joinClubDao.queryUserInfoByUserId(paramObj.getUser_id());

            String message = user_info_map.get("phone").toString();

            if (!ObjectUtil.isEmpty(user_info_map.get("user_real_name"))) {
                message += "," + user_info_map.get("user_real_name").toString();
            } else if (!ObjectUtil.isEmpty(map.get("nickname"))) {
                message += "," + user_info_map.get("nickname").toString();
            }

            List<Integer> user_id_list = joinClubDao.queryClubAdmin(paramObj.getClub_id());

            if (user_id_list.size() > 0) {
                notificationService.sendNoti(1, user_id_list, null, "1", message + dictionaryUtil.getCodeValue("out_club_message", "data_alias", "zh"));
            }

            return ObjectUtil.jsonOut("true");
        } else {
            return ObjectUtil.jsonOutError("1033", "false");
        }
    }

}
