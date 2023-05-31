package com.atguigu.book.service;

import com.atguigu.book.javabean.Cart;
import com.atguigu.book.javabean.CartItem;
import com.atguigu.book.javabean.User;

import java.util.List;

public interface CartItemService {

    CartItem getCartItem(Integer id);

    void addCartItem(Integer bookId, User user);

    boolean addCartItem(CartItem cartItem);

    void editCartItem(Integer cartItemId, Integer buyCount, Cart cart);

    boolean updateCartItem(CartItem cartItem);

    void delCartItem(Cart cart);

    List<CartItem> getCartItemList(User user);
}
