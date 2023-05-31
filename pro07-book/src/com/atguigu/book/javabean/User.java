package com.atguigu.book.javabean;

import java.util.Map;

public class User {
    private Integer userId;
    private String userName;
    private String userPwd;
    private String userEmail;
    private Integer userRole;
    private Cart cart;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(String userName, String userPwd, String userEmail, Integer userRole) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.cart = new Cart();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRole=" + userRole +
                ", cart=" + cart +
                '}';
    }
}
