package com.atguigu.book.service;

import com.atguigu.book.javabean.Cart;
import com.atguigu.book.javabean.User;


public interface UserService {
    User getUser(String userName);

    User getUser(String userName, String userPwd);

    void addUser(User user);

    Cart getCart(User user);
}
