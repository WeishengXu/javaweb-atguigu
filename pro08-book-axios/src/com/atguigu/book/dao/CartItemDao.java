package com.atguigu.book.dao;

import com.atguigu.book.javabean.CartItem;
import com.atguigu.book.javabean.User;

import java.util.List;

public interface CartItemDao {
    public CartItem getCartItem(Integer id);

    public CartItem getCartItem(Integer bookId, Integer userId);

    public List<CartItem> getCartItemList(User user);

    public boolean addCartItem(CartItem cartItem);

    public void addCartItem(CartItem cartItem, boolean return_primary_key);

    public boolean delCartItem(Integer id);

    public boolean updateCartItem(CartItem cartItem);
}
