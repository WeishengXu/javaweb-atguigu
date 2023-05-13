package com.atguigu.servlets;

import com.atguigu.entities.Fruit;
import com.atguigu.utils.FruitDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {
    private FruitDao fruitDao = new FruitDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int fid = Integer.parseInt(request.getParameter("fid"));
        String fname = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");

        boolean flag = fruitDao.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        if (flag) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }

        response.sendRedirect("index");
    }
}
