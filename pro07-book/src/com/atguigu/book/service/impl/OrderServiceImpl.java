package com.atguigu.book.service.impl;

import com.atguigu.book.dao.OrderDao;
import com.atguigu.book.javabean.Order;
import com.atguigu.book.javabean.OrderItem;
import com.atguigu.book.javabean.User;
import com.atguigu.book.service.CartItemService;
import com.atguigu.book.service.OrderItemService;
import com.atguigu.book.service.OrderService;

import java.util.HashMap;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private OrderItemService orderItemService;
    private CartItemService cartItemService;

    @Override
    public void addOrder(Order order, User user) {
        orderDao.addOrder(order);
        orderItemService.addOrderItem(order, user);
        cartItemService.delCartItem(user.getCart());

        user.getCart().setCartItemMap(new HashMap<>());
        user.getCart().setTotalCount(0);
        user.getCart().setTotalMoney(0.0);
    }

    @Override
    public List<Order> getOrderList(User user) {
        List<Order> orderList = orderDao.getOrderList(user);
        for (Order order: orderList) {
            List<OrderItem> orderItemList = orderItemService.getOrderItemList(order);
            order.setOrderItemList(orderItemList);
        }
        return orderList;
    }
}
