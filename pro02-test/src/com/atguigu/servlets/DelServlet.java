package com.atguigu.servlets;

import com.atguigu.utils.FruitDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet{
    private FruitDao fruitDao = new FruitDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int fid = Integer.parseInt(request.getParameter("fid"));
        boolean flag = fruitDao.delFruit(fid);
        if (flag) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
        response.sendRedirect("index");
    }
}
