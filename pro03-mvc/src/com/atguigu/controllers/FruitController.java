package com.atguigu.controllers;

import com.atguigu.entities.Fruit;
import com.atguigu.utils.FruitDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class FruitController {
    private FruitDao fruitDao = new FruitDao();

    private String index(String keyword, Integer pageNo, HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<Fruit> fruitList = null;
        if (pageNo == null) {
            pageNo = 1;
        }
        if (keyword != null) {
            pageNo = 1;
            session.setAttribute("keyword", keyword);
        } else {
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
        } else {
            System.out.println("查询失败");
        }

        int fruitCount = fruitDao.getFruitCount(keyword);
        int pageCount = (fruitCount + 5 - 1) / 5;

        session.setAttribute("pageNo", pageNo);
        session.setAttribute("pageCount", pageCount);
        session.setAttribute("fruitList", fruitList);

//        super.processTemplate("index", request, response);
        return "index";
    }

    private String add(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
//            super.processTemplate("add", request, response);
            return "add";
        } else {
            String fname = request.getParameter("fname");
            int price = Integer.parseInt(request.getParameter("price"));
            int fcount = Integer.parseInt(request.getParameter("fcount"));
            String remark = request.getParameter("remark");

            boolean flag = fruitDao.addFruit(new Fruit(0, fname, price, fcount, remark));
            if (flag) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }

//            response.sendRedirect("fruit.do");
            return "redirect: fruit.do";
        }
    }

    private String delete(Integer fid) {
        boolean flag = fruitDao.delFruit(fid);
        if (flag) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
//        response.sendRedirect("fruit.do");
        return "redirect: fruit.do";
    }

    private String edit(Integer fid, HttpServletRequest request) {
        Fruit fruit = fruitDao.getFruit(fid);
        System.out.println(fruit);
        request.setAttribute("fruit", fruit);
//        super.processTemplate("edit", request, response);
        return "edit";
    }

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) {
        boolean flag = fruitDao.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        if (flag) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }

        return "redirect: fruit.do";
//        response.sendRedirect("fruit.do");
    }

}
