package com.atguigu.book.service.impl;

import com.atguigu.book.dao.UserDao;
import com.atguigu.book.javabean.Cart;
import com.atguigu.book.javabean.CartItem;
import com.atguigu.book.javabean.User;
import com.atguigu.book.service.CartItemService;
import com.atguigu.book.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private CartItemService cartItemService;

    @Override
    public User getUser(String userName) {
        return userDao.getUser(userName);
    }

    @Override
    public User getUser(String userName, String userPwd) {
        return userDao.getUser(userName, userPwd);
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public Cart getCart(User user) {
        Map<Integer, CartItem> cartItemMap = new HashMap<>();
        Integer totalCount = 0;
        double totalMoney = 0.0;
        List<CartItem> cartItemList = cartItemService.getCartItemList(user);
        for (CartItem cartItem: cartItemList) {
            cartItemMap.put(cartItem.getBook().getBookId(), cartItem);
            totalCount += cartItem.getBuyCount();
            totalMoney += cartItem.getBook().getBookPrice() * cartItem.getBuyCount();
        }

        return new Cart(cartItemMap, totalCount, totalMoney);
    }
}
