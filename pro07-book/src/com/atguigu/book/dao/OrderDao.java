package com.atguigu.book.dao;

import com.atguigu.book.javabean.Order;
import com.atguigu.book.javabean.User;

import java.util.List;

public interface OrderDao {
    void addOrder(Order order);

    List<Order> getOrderList(User user);
}
