package com.hongzhi.zswh.app_v6.controller;

import com.hongzhi.zswh.app_v6.service.V6ClubService;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by XieCaij on 2016/6/28.
 */
@Controller
public class V6ClubController {
    @Autowired
    private V6ClubService clubService;
    @Autowired
    private SessionUtil sess;
    @Autowired
    private DictionaryUtil dic;
}
