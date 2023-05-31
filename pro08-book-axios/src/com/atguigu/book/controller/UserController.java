package com.atguigu.book.controller;

import com.atguigu.book.javabean.Cart;
import com.atguigu.book.javabean.User;
import com.atguigu.book.service.UserService;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;

public class UserController {
    private UserService userService;

    public String index() {
        return "index";
    }

    public String login(String userName, String userPwd, HttpSession session) {
        User user = userService.getUser(userName, userPwd);
        if (user != null) {
            Cart cart = userService.getCart(user);
            user.setCart(cart);

            session.setAttribute("currentUser", user);
            return "redirect: book.do";
        } else {
            return "user/login";
        }
    }

    public String getCart(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        Cart cart = userService.getCart(user);
        Gson gson = new Gson();
        String cartJsonStr = gson.toJson(cart);
        return "json: " + cartJsonStr;
    }

    public String logout(HttpSession session) {
        session.setAttribute("currentUser", null);

        return "user/login";
    }

    public String register(String userName, String userPwd, String userPwd2, String userEmail, String code, HttpSession session) {
        String kaptchaSessionKey = (String) session.getAttribute("KAPTCHA_SESSION_KEY");
        if (code.equals(kaptchaSessionKey) && userPwd.equals(userPwd2)) {
            User user = new User(userName, userPwd, userEmail, 0);
            userService.addUser(user);
            return "user/login";
        } else {
            return "user/regist";
        }
    }

    public String checkUserName(String userName) {
        User user = userService.getUser(userName);
        if (user != null) {
            return "json: {'userName': '1'}";
        } else {
            return "json: {'userName': '0'}";
        }
    }
}
