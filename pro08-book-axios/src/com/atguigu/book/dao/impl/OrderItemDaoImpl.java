package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.OrderItemDao;
import com.atguigu.book.javabean.Order;
import com.atguigu.book.javabean.OrderItem;
import com.atguigu.myssm.daoUtils.BaseDao;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item (book, buyCount, `order`) value (?, ?, ?)";
        int id = 0;
        try {
            id = super.executeUpdate(sql, orderItem.getBook().getBookId(), orderItem.getBuyCount(), orderItem.getOrder().getOrderId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        orderItem.setId(id);
    }

    @Override
    public List<OrderItem> getOrderItemList(Order order) {
        String sql = "select * from t_order_item where `order` = ?";
        try {
            return super.executeQuery(OrderItem.class, sql, order.getOrderId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
