package com.atguigu.book.service.impl;

import com.atguigu.book.dao.CartItemDao;
import com.atguigu.book.javabean.Book;
import com.atguigu.book.javabean.Cart;
import com.atguigu.book.javabean.CartItem;
import com.atguigu.book.javabean.User;
import com.atguigu.book.service.BookService;
import com.atguigu.book.service.CartItemService;

import java.util.List;

public class CartItemServiceImpl implements CartItemService {
    private CartItemDao cartItemDao;
    private BookService bookService;

    @Override
    public CartItem getCartItem(Integer id) {
        return cartItemDao.getCartItem(id);
    }

    @Override
    public void addCartItem(Integer bookId, User user) {
        CartItem cartItem = user.getCart().getCartItemMap().get(bookId);
        if (cartItem != null) {
            cartItem.setBuyCount(cartItem.getBuyCount() + 1);
            cartItemDao.updateCartItem(cartItem);
        } else {
            Book book = bookService.getBook(bookId);
            cartItem = new CartItem(book, 1, new User(user.getUserId()));
            cartItemDao.addCartItem(cartItem, true);
            user.getCart().getCartItemMap().put(bookId, cartItem);
        }
        user.getCart().setTotalCount(user.getCart().getTotalCount() + 1);
        user.getCart().setTotalMoney(user.getCart().getTotalMoney() + cartItem.getBook().getBookPrice());
    }

    @Override
    public boolean addCartItem(CartItem cartItem) {
        return cartItemDao.addCartItem(cartItem);
    }

    @Override
    public void editCartItem(Integer cartItemId, Integer buyCount, Cart cart) {
        CartItem cartItem;
        for (Integer bookId : cart.getCartItemMap().keySet()) {
            cartItem = cart.getCartItemMap().get(bookId);
            if (cartItem.getId() == cartItemId) {
                cart.setTotalCount(cart.getTotalCount() + buyCount - cartItem.getBuyCount());
                cart.setTotalMoney(cart.getTotalMoney() + (buyCount - cartItem.getBuyCount()) * cartItem.getBook().getBookPrice());
                if (buyCount == 0) {
                    boolean flag = cartItemDao.delCartItem(cartItemId);
                    cart.getCartItemMap().remove(bookId);
                } else {
                    cartItem.setBuyCount(buyCount);
                    cartItemDao.updateCartItem(cartItem);
                }

                break;
            }
        }
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) {
        return cartItemDao.updateCartItem(cartItem);
    }

    @Override
    public void delCartItem(Cart cart) {
        for (CartItem cartItem : cart.getCartItemMap().values()) {
            cartItemDao.delCartItem(cartItem.getId());
        }
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        List<CartItem> cartItemList = cartItemDao.getCartItemList(user);
        for (CartItem cartItem : cartItemList) {
            Book book = bookService.getBook(cartItem.getBook().getBookId());
            cartItem.setBook(book);
        }
        return cartItemList;
    }
}
