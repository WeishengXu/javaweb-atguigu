package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.UserBasicDao;
import com.atguigu.qqzone.javabean.UserBasic;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.List;

public class UserBasicServiceImpl implements UserBasicService {

    private UserBasicDao userBasicDao;

    @Override
    public UserBasic login(String loginId, String pwd) {
        UserBasic userBasic = userBasicDao.getUserBasic(loginId, pwd);
        return userBasic;
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        List<UserBasic> userBasicList = userBasicDao.getUserBasicList(userBasic);
        return userBasicList;
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        UserBasic userBasic = userBasicDao.getUserBasicById(id);
        return userBasic;
    }
}
