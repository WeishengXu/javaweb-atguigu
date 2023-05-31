package com.atguigu.book.dao;

import com.atguigu.book.javabean.Order;
import com.atguigu.book.javabean.OrderItem;

import java.util.List;

public interface OrderItemDao {
    void addOrderItem(OrderItem orderItem);

    List<OrderItem> getOrderItemList(Order order);
}
