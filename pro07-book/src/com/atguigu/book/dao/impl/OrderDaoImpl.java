package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.OrderDao;
import com.atguigu.book.javabean.Order;
import com.atguigu.book.javabean.User;
import com.atguigu.myssm.daoUtils.BaseDao;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public void addOrder(Order order) {
        String sql = "insert into t_order (orderNo, orderDate, orderUser, orderMoney, orderStatus) value (?, ?, ?, ?, 0)";
        int id = 0;
        try {
            id = super.executeUpdate(sql,true, order.getOrderNo(), order.getOrderDate(), order.getOrderUser().getUserId(), order.getOrderMoney());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        order.setOrderId(id);
    }

    @Override
    public List<Order> getOrderList(User user) {
        String sql = "select * from t_order where orderUser = ? and orderStatus = 0";
        try {
            return super.executeQuery(Order.class, sql, user.getUserId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
