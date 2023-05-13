package com.atguigu.utils;

import com.atguigu.entities.Fruit;
import com.atguigu.exceptions.FruitDaoException;

import java.util.List;

public class FruitDao extends BaseDao {
    public int getFruitCount() {
        String sql = "select * from t_fruit";
        List<Fruit> fruitList = null;
        try {
            fruitList = super.executeQuery(Fruit.class, sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FruitDaoException("fruit dao wrong!");
        }
        return fruitList.size();
    }

    public int getFruitCount(String keyword) {
        String sql = "select * from t_fruit where fname like ? or remark like ?";
        List<Fruit> fruitList = null;
        try {
            fruitList = super.executeQuery(Fruit.class, sql, "%" + keyword + "%", "%" + keyword + "%");
        } catch (Exception e) {
            e.printStackTrace();
            throw new FruitDaoException("fruit dao wrong!");
        }
        return fruitList.size();
    }

    public List<Fruit> getFruitList() {
        String sql = "select * from t_fruit";
        try {
            return super.executeQuery(Fruit.class, sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FruitDaoException("fruit dao wrong!");
        }
    }

    public List<Fruit> getFruitList(Integer pageNo) {
        String sql = "select * from t_fruit limit ?, 5";
        try {
            return super.executeQuery(Fruit.class, sql, (pageNo - 1) * 5);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FruitDaoException("fruit dao wrong!");
        }
    }

    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        String sql = "select * from t_fruit where fname like ? or remark like ? limit ?, 5";
        try {
            return super.executeQuery(Fruit.class, sql, "%" + keyword + "%", "%" + keyword + "%", (pageNo - 1) * 5);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FruitDaoException("fruit dao wrong!");
        }
    }

    public Fruit getFruit(Integer fid) {
        String sql = "select * from t_fruit where fid=?";
        try {
            return super.executeQuery(Fruit.class, sql, fid).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FruitDaoException("fruit dao wrong!");
        }
    }

    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname=?, price=?, fcount=?, remark=? where fid=?";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FruitDaoException("fruit dao wrong!");
        }
        return rows > 0;
    }

    public boolean delFruit(Integer fid) {
        String sql = "delete from t_fruit where fid = ?";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, fid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FruitDaoException("fruit dao wrong!");
        }
        return rows > 0;
    }

    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit (fname, price, fcount, remark) value (?, ?, ?, ?)";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FruitDaoException("fruit dao wrong!");
        }
        return rows > 0;
    }
}
