package com.hongzhi.zswh.app_1_3.service;

import com.hongzhi.zswh.app_1_3.dao.ClubDao;
import com.hongzhi.zswh.app_1_3.entity.UserDetailEntity;
import com.hongzhi.zswh.app_v3.notification.service.NotificationService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.exception.HongZhiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: xuejian
 * Date: 2016/7/21
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CheckClubTime {
    @Autowired
    private ClubDao clubDao;
    @Autowired
    private DictionaryUtil dictionaryUtil;
    @Autowired
    private NotificationService notificationService;

    @Scheduled(cron = "0 */1 * * * ?")
    // @Scheduled(cron = "0 0 0/1 * * ?")
    public void checkClub() throws HongZhiException {

        List<Map<String, Object>> club_list = clubDao.selectClub();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        if (club_list.size() != 0) {
            List<Integer> club_id_effective_list = new ArrayList<>();
            List<Integer> club_id_reminder_list = new ArrayList<>();

            int club_effective_days = Integer.valueOf(dictionaryUtil.getCodeValue("club_effective_days", "data_alias", "zh"));

            int club_reminder_days = Integer.valueOf(dictionaryUtil.getCodeValue("club_reminder_days", "data_alias", "zh"));


            for (int i = 0; i < club_list.size(); i++) {

                String club_create_time = club_list.get(i).get("club_create_time").toString();

                try {

                    Date club_create_time_date = df.parse(club_create_time);
                    Date now_date = new Date();

                    int days = daysBetween(club_create_time_date, now_date);


                    if (days > club_effective_days) {

                        club_id_effective_list.add(Integer.valueOf(club_list.get(i).get("club_id").toString()));

                    } else if (days == club_reminder_days && "0".equals(club_list.get(i).get("reminder_mark").toString())) {

                        club_id_reminder_list.add(Integer.valueOf(club_list.get(i).get("club_id").toString()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (club_id_effective_list.size() > 0) {

                clubDao.updateClubStatus(club_id_effective_list);

                List<Integer> multiple_receiver = clubDao.selectClubMembersByClubId(club_id_effective_list);

                //send message
                notificationService.sendNoti(1, multiple_receiver, null, "1", dictionaryUtil.getCodeValue("break_club_message", "data_alias", "zh"));

                List<UserDetailEntity> user_detail_list = clubDao.queryClub(multiple_receiver);

                clubDao.insetIntoUserDetail(user_detail_list);

                clubDao.clubUnbuild(multiple_receiver);

            } else if (club_id_reminder_list.size() > 0) {

                clubDao.updateClubReminderMark(club_id_reminder_list);

                List<Integer> multiple_receiver = clubDao.selectClubMembersByClubId(club_id_reminder_list);

                //send message
                notificationService.sendNoti(1, multiple_receiver, null, "1", dictionaryUtil.getCodeValue("reminder_club", "data_alias", "zh"));

            }
        }
    }


    /**
     * 计算两个日期之间相差的天数
     *
     * @param start_date 较小的时间
     * @param end_date   较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date start_date, Date end_date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        start_date = sdf.parse(sdf.format(start_date));
        end_date = sdf.parse(sdf.format(end_date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(start_date);
        long start_time = cal.getTimeInMillis();
        cal.setTime(end_date);
        long end_time = cal.getTimeInMillis();

        long between_days = (end_time - start_time) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }
}
