package com.atguigu.servlets;

import com.atguigu.entities.Fruit;
import com.atguigu.utils.FruitDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet{
    private FruitDao fruitDao = new FruitDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if (fidStr != null && !fidStr.equals("")) {
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDao.getFruit(fid);
            System.out.println(fruit);
            request.setAttribute("fruit", fruit);
            super.processTemplate("edit", request, response);
        }
    }
}
