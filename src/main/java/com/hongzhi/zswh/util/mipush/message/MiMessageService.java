package com.hongzhi.zswh.util.mipush.message;

import com.hongzhi.zswh.util.basic.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by taylor on 7/21/16.
 * twitter: @taylorwang789
 */
@Service("miMessageService")
public class MiMessageService {

    @Autowired
    private MessageDao messageDao;


    public List<AppRegid> getRegidList(List<Integer> user_ids){
        if (!ObjectUtil.isEmpty(user_ids) && user_ids.size()>0) {
            List<AppRegid> regidList = messageDao.getRegId(user_ids);
            return regidList;
        } else {
            return null;
        }
    }

    public List<AppRegid> getRegidList(Integer user_id){
        if (!ObjectUtil.isEmpty(user_id)) {
            return getRegidList(Arrays.asList(user_id));
        } else {
            return null;
        }
    }
}
