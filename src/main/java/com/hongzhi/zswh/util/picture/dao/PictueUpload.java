package com.hongzhi.zswh.util.picture.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by taylor on 7/5/16.
 */
public interface PictueUpload {
    int saveUploadPictureName(@Param("originName") String originName,@Param("newName") String newName );
}
