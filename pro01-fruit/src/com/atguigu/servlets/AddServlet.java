package com.atguigu.servlets;

import com.atguigu.fruit.Fruit;
import com.atguigu.utils.FruitDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String fname = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");

        FruitDao fruitDao = new FruitDao();
        boolean flag = fruitDao.addFruit(new Fruit(0, fname, price, fcount, remark));
        System.out.println(flag ? "添加成功" : "添加失败");
    }
}
