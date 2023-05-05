package com.atguigu.servlet;

import com.atguigu.Entity.Fruit;
import com.atguigu.dao.FruitDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FruitDao fruitDao = new FruitDao();
        List<Fruit> fruitList = fruitDao.getFruitList();
        if (fruitList.size() > 0) {
            System.out.println("查询成功");
            for (int i = 0; i < fruitList.size(); i++) {
                System.out.println(fruitList.get(i));
            }
        }
        else {
            System.out.println("查询失败");
        }
        HttpSession session = request.getSession();
        session.setAttribute("fruitList", fruitList);
        super.processTemplate("index", request, response);
    }
}
