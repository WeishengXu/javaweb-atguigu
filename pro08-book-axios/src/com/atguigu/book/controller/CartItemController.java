package com.atguigu.book.controller;

import com.atguigu.book.javabean.User;
import com.atguigu.book.service.CartItemService;

import javax.servlet.http.HttpSession;

public class CartItemController {
    private CartItemService cartItemService;

    public String index() {
        return "cart/cart";
    }

    public String addCart(Integer bookId, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");

        cartItemService.addCartItem(bookId, user);

        session.setAttribute("currentUser", user);

        return "cart/cart";
    }

    public String editCartItem(Integer id, Integer buyCount, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        cartItemService.editCartItem(id, buyCount, user.getCart());
        session.setAttribute("currentUser", user);

        return "cart/cart";
    }

}
