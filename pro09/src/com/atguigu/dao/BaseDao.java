package com.atguigu.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T> {
    public final String DRIVER = "com.mysql.jdbc.Driver";
    public final String URL = "jdbc:mysql://10.106.129.74:3306/atguigu";
    public final String USER = "root";
    public final String PASSWORD = "abc123...";
    protected Connection conn;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    private Class entityClass;

    public BaseDao() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];
        try {
            entityClass = Class.forName(actualTypeArgument.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConn() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection conn) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setParams(PreparedStatement preparedStatement, Object... params) throws SQLException {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1, params[i]);
            }
        }
    }

    private void setValue(Object obj, String property, Object propertyValue) {
        Class clazz = obj.getClass();
        try {
            Field field = clazz.getDeclaredField(property);
            if (field != null) {
                field.setAccessible(true);
                field.set(obj, propertyValue);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public List<T> executeQuery(String sql, Object... params) throws SQLException, InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        conn = getConn();
        preparedStatement = conn.prepareStatement(sql);
        setParams(preparedStatement, params);
        resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        while (resultSet.next()) {
            T entity = (T) entityClass.newInstance();
            for (int i = 0; i < columnCount; i++) {
                String columnName = resultSetMetaData.getColumnName(i + 1);
                Object columnValue = resultSet.getObject(i + 1);
                setValue(entity, columnName, columnValue);
            }
            list.add(entity);
        }
        return list;
    }
}
