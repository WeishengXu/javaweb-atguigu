package com.atguigu.servlets;

import com.atguigu.entities.Fruit;
import com.atguigu.utils.FruitDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    private FruitDao fruitDao = new FruitDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        int pageNo = 1;

        List<Fruit> fruitList = null;
        String keyword = request.getParameter("keyword");
        if (keyword != null) {
            session.setAttribute("keyword", keyword);
        } else {
            String pageNoStr = request.getParameter("pageNo");
            if (pageNoStr != null) {
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }

        fruitList = fruitDao.getFruitList(keyword, pageNo);
        if (fruitList.size() > 0) {
            System.out.println("查询成功");
            for (int i = 0; i < fruitList.size(); i++) {
                System.out.println(fruitList.get(i));
            }
        }
        else {
            System.out.println("查询失败");
        }

        int fruitCount = fruitDao.getFruitCount(keyword);
        int pageCount = (fruitCount + 5 - 1) / 5;

        session.setAttribute("pageNo", pageNo);
        session.setAttribute("pageCount", pageCount);
        session.setAttribute("fruitList", fruitList);

        super.processTemplate("index", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
        doGet(request, response);
    }
}
