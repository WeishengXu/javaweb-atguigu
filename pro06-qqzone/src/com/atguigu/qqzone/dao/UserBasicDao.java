package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.javabean.UserBasic;

import java.util.List;

public interface UserBasicDao {
    public UserBasic getUserBasic(String loginId, String pwd);

    public List<UserBasic> getUserBasicList(UserBasic userBasic);

    public UserBasic getUserBasicById(Integer id);
}
