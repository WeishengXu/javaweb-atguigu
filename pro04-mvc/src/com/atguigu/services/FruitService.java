package com.atguigu.services;

import com.atguigu.entities.Fruit;
import com.atguigu.utils.FruitDao;
import com.atguigu.utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class FruitService {
    private FruitDao fruitDao;

    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        try {
            System.out.println("JdbcUtils.getConnection() = " + JdbcUtils.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fruitDao.getFruitList(keyword, pageNo);
    }

    public boolean addFruit(Fruit fruit) {
        return fruitDao.addFruit(fruit);
    }

    public boolean delFruit(Integer fid) {
        return fruitDao.delFruit(fid);
    }

    public boolean updateFruit(Fruit fruit) {
        return fruitDao.updateFruit(fruit);
    }

    public Fruit getFruit(Integer fid) {
        return fruitDao.getFruit(fid);
    }

    public int getPageCount(String keyword) {
        try {
            System.out.println("JdbcUtils.getConnection() = " + JdbcUtils.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int fruitCount = fruitDao.getFruitCount(keyword);
        int pageCount = (fruitCount + 5 - 1) / 5;
        return pageCount;
    }
}
