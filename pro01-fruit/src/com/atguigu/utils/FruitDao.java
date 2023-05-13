package com.atguigu.utils;

import com.atguigu.fruit.Fruit;

import java.sql.SQLException;
import java.util.List;

public class FruitDao extends BaseDao{
    //查询库存列表
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

    //新增库存
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit (fname, price, fcount, remark) values (?, ?, ?, ?)";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }

    //修改库存
    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fcount = ? where fid = ?";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, fruit.getFid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }

    //根据名称查询特定库存
    public Fruit getFruitByFname(String fname) {
        String sql = "select * from t_fruit where fname like ?";
        try {
            return super.executeQuery(Fruit.class, sql, fname).get(0);
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

    //删除特定库存记录
    public boolean delFruit(String fname) {
        String sql = "delete from t_fruit where fname like ?";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, fname);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }
}
