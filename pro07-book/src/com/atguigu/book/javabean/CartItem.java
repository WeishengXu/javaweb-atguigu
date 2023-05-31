package com.atguigu.book.javabean;

import java.math.BigDecimal;

public class CartItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User user;

    private Double buyMoney;

    public CartItem() {
    }

    public Integer getId() {
        return id;
    }

    public CartItem(Book book, Integer buyCount, User user) {
        this.book = book;
        this.buyCount = buyCount;
        this.user = user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getBuyMoney() {
        BigDecimal bigDecimalPrice = new BigDecimal(String.valueOf(book.getBookPrice()));
        BigDecimal bigDecimalBuyCount = new BigDecimal(String.valueOf(buyCount));
        BigDecimal bigDecimalBuyMoney = bigDecimalPrice.multiply(bigDecimalBuyCount);
        buyMoney = bigDecimalBuyMoney.doubleValue();
        return buyMoney;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", book=" + book +
                ", buyCount=" + buyCount +
                ", user=" + user +
                ", buyMoney=" + buyMoney +
                '}';
    }
}
