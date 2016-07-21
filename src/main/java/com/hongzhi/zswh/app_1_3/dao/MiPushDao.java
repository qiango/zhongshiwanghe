package com.hongzhi.zswh.app_1_3.dao;

import com.hongzhi.zswh.app_1_3.entity.MiPushRegid;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: xuejian
 * Date: 2016/7/20
 * Time: 10:23
 * To change this template use File | Settings | File Templates.
 */
@Repository("app_1_3_MiPushDao")
public interface MiPushDao {

    int saveRegid(MiPushRegid miPushRegid);

    void cancelRegid(String regid);
}
