package com.atguigu.book.javabean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, CartItem> cartItemMap;
    private Integer totalCount;
    private Double totalMoney;

    public Cart() {
        cartItemMap = new HashMap<>();
        totalCount = 0;
        totalMoney = 0.0;
    }

    public Cart(Map<Integer, CartItem> cartItemMap, Integer totalCount, Double totalMoney) {
        this.cartItemMap = cartItemMap;
        this.totalCount = totalCount;
        this.totalMoney = totalMoney;
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Double getTotalMoney() {
        totalMoney = Double.valueOf(String.format("%.2f", new BigDecimal(String.valueOf(totalMoney))));
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItemMap=" + cartItemMap +
                ", totalCount=" + totalCount +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
