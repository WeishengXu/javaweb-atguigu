package com.atguigu.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao {

    protected Connection connection;

    protected PreparedStatement preparedStatement;

    public int executeUpdate(String sql, Object... params) throws SQLException {
        // 获取连接
        connection = JdbcUtils.getConnection();

        // 创建prepared statement对象，并传入动态值
        preparedStatement = connection.prepareStatement(sql);
        if (params != null && params.length != 0) {
            for (int i = 1; i <= params.length; i++) {
                preparedStatement.setObject(i, params[i - 1]);
            }
        }

        // 执行语句
        int rows = preparedStatement.executeUpdate();

        // 回收资源
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            JdbcUtils.freeConnection();
        }

        return rows;
    }

    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // 获取连接
        connection = JdbcUtils.getConnection();

        // 创建prepared statement对象
        preparedStatement = connection.prepareStatement(sql);
        if (params != null && params.length != 0) {
            for (int i = 1; i <= params.length; i++) {
                preparedStatement.setObject(i, params[i - 1]);
            }
        }

        // 执行语句
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();

        // 解析结果
        List<T> list = new ArrayList<>();
        while (resultSet.next()) {
            T t = clazz.newInstance();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                // 获取属性名和属性值
                String columnLabel = metaData.getColumnLabel(i);
                Object value = resultSet.getObject(i);

                // 反射，给对象的属性值赋值
                Field field = clazz.getDeclaredField(columnLabel);
                field.setAccessible(true);  // 属性可以设置，打破private的修饰限制
                field.set(t, value);
            }
            list.add(t);
        }

        // 关闭资源
        resultSet.close();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            JdbcUtils.freeConnection();
        }

        return list;
    }
}
