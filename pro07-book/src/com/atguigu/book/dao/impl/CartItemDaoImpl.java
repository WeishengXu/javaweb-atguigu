package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.CartItemDao;
import com.atguigu.book.javabean.CartItem;
import com.atguigu.book.javabean.User;
import com.atguigu.myssm.daoUtils.BaseDao;

import java.sql.SQLException;
import java.util.List;

public class CartItemDaoImpl extends BaseDao implements CartItemDao {
    @Override
    public CartItem getCartItem(Integer id) {
        String sql = "select * from t_cart_item where id = ?";
        List<CartItem> cartItemList = null;
        try {
            cartItemList = super.executeQuery(CartItem.class, sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cartItemList != null && cartItemList.size() > 0) {
            return cartItemList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public CartItem getCartItem(Integer bookId, Integer userId) {
        String sql = "select * from t_cart_item where book = ? and user = ?";
        List<CartItem> cartItemList = null;
        try {
            cartItemList = super.executeQuery(CartItem.class, sql, bookId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cartItemList != null && cartItemList.size() > 0) {
            return cartItemList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        String sql = "select * from t_cart_item where user = ?";
        try {
            return super.executeQuery(CartItem.class, sql, user.getUserId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addCartItem(CartItem cartItem) {
        String sql = "insert into t_cart_item (book, buyCount, user) value (?, ?, ?)";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, cartItem.getBook().getBookId(), cartItem.getBuyCount(), cartItem.getUser().getUserId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }

    @Override
    public void addCartItem(CartItem cartItem, boolean return_primary_key) {
        String sql = "insert into t_cart_item (book, buyCount, user) value (?, ?, ?)";
        int id = 0;
        try {
            id = super.executeUpdate(sql, return_primary_key, cartItem.getBook().getBookId(), cartItem.getBuyCount(), cartItem.getUser().getUserId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cartItem.setId(id);
    }

    @Override
    public boolean delCartItem(Integer id) {
        String sql = "delete from t_cart_item where id = ?";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) {
        String sql = "update t_cart_item set buyCount = ? where id = ?";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, cartItem.getBuyCount(), cartItem.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }
}
