package com.atguigu.book.controller;

import com.atguigu.book.javabean.Order;
import com.atguigu.book.javabean.User;
import com.atguigu.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderController {
    private OrderService orderService;

    public String checkout(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");

        Date now = new Date();
        String nowStr = new SimpleDateFormat("yyyyMMddHHmmss").format(now);
        String orderNo = UUID.randomUUID().toString().replace("-", "") + "_" + nowStr;
        Order order = new Order(orderNo, now, user, user.getCart().getTotalMoney(), 0);
        orderService.addOrder(order, user);

        session.setAttribute("currentUser", user);
        session.setAttribute("currentOrder", order);

        return "cart/checkout";
    }

    public String getOrderList(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        List<Order> orderList = orderService.getOrderList(user);
        session.setAttribute("orderList", orderList);

        return "manager/order_manager";
    }
}
