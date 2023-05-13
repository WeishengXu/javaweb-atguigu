package com.atguigu.utils;

import com.atguigu.entities.Fruit;

import java.sql.SQLException;
import java.util.List;

public class FruitDao extends BaseDao {
    public int getFruitCount() {
        String sql = "select * from t_fruit";
        List<Fruit> fruitList = null;
        try {
            fruitList = super.executeQuery(Fruit.class, sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return fruitList.size();
    }

    public int getFruitCount(String keyword) {
        String sql = "select * from t_fruit where fname like ? or remark like ?";
        List<Fruit> fruitList = null;
        try {
            fruitList = super.executeQuery(Fruit.class, sql, "%"+keyword+"%", "%"+keyword+"%");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return fruitList.size();
    }

    public List<Fruit> getFruitList() {
        String sql = "select * from t_fruit";
        try {
            return super.executeQuery(Fruit.class, sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Fruit> getFruitList(Integer pageNo) {
        String sql = "select * from t_fruit limit ?, 5";
        try {
            return super.executeQuery(Fruit.class, sql, (pageNo - 1) * 5);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        String sql = "select * from t_fruit where fname like ? or remark like ? limit ?, 5";
        try {
            return super.executeQuery(Fruit.class, sql, "%"+keyword+"%", "%"+keyword+"%", (pageNo - 1) * 5);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public Fruit getFruit(Integer fid) {
        String sql = "select * from t_fruit where fid=?";
        try {
            return super.executeQuery(Fruit.class, sql, fid).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname=?, price=?, fcount=?, remark=? where fid=?";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }

    public boolean delFruit(int fid) {
        String sql = "delete from t_fruit where fid = ?";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, fid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }

    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit (fname, price, fcount, remark) value (?, ?, ?, ?)";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }
}
