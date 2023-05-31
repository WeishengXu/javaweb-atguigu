package com.atguigu.book.dao;

import com.atguigu.book.javabean.User;

public interface UserDao {
    User getUser(String userName);

    User getUser(String userName, String userPwd);

    void addUser(User user);
}
