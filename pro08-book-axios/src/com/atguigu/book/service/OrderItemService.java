package com.atguigu.book.service;

import com.atguigu.book.javabean.Order;
import com.atguigu.book.javabean.OrderItem;
import com.atguigu.book.javabean.User;

import java.util.List;

public interface OrderItemService {
    void addOrderItem(Order order, User user);

    List<OrderItem> getOrderItemList(Order order);
}
