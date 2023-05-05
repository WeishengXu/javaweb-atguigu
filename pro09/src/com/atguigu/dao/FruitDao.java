package com.atguigu.dao;

import com.atguigu.Entity.Fruit;

import java.sql.SQLException;
import java.util.List;

public class FruitDao extends BaseDao<Fruit>{
    public List<Fruit> getFruitList() {
        try {
            return super.executeQuery("select * from t_fruit");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
