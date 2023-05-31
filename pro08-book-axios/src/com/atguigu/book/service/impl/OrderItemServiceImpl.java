package com.atguigu.book.service.impl;

import com.atguigu.book.dao.OrderItemDao;
import com.atguigu.book.javabean.CartItem;
import com.atguigu.book.javabean.Order;
import com.atguigu.book.javabean.OrderItem;
import com.atguigu.book.javabean.User;
import com.atguigu.book.service.OrderItemService;

import java.util.ArrayList;
import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDao orderItemDao;

    @Override
    public void addOrderItem(Order order, User user) {
        order.setOrderItemList(new ArrayList<>());
        for (CartItem cartItem: user.getCart().getCartItemMap().values()) {
            OrderItem orderItem = new OrderItem(cartItem.getBook(), cartItem.getBuyCount(), new Order(order.getOrderId()));
            orderItemDao.addOrderItem(orderItem);
            order.getOrderItemList().add(orderItem);
        }
    }

    @Override
    public List<OrderItem> getOrderItemList(Order order) {
        return orderItemDao.getOrderItemList(order);
    }
}
