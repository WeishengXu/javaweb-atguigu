package com.atguigu.book.service;

import com.atguigu.book.javabean.Order;
import com.atguigu.book.javabean.User;

import java.util.List;

public interface OrderService {
    void addOrder(Order order, User user);

    List<Order> getOrderList(User user);
}
