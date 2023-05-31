package com.atguigu.qqzone.service;

import com.atguigu.qqzone.javabean.UserBasic;

import java.util.List;

public interface UserBasicService {
    public UserBasic login(String loginId, String pwd);

    public List<UserBasic> getFriendList(UserBasic userBasic);

    UserBasic getUserBasicById(Integer id);
}
