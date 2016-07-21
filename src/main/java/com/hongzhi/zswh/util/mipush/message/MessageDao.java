package com.hongzhi.zswh.util.mipush.message;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by taylor on 7/20/16.
 * twitter: @taylorwang789
 */
@Repository("mipushmessagedao")
public interface MessageDao {

    List<AppRegid> getRegId(@Param("userIds") List<Integer> user_id);

}
