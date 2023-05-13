package com.atguigu.trans;

import com.atguigu.utils.JdbcUtils;

import java.sql.SQLException;

public class TransactionManager {
    public static void beginTrans() throws SQLException {
        JdbcUtils.getConnection().setAutoCommit(false);
    }

    public static void commit() throws SQLException {
        JdbcUtils.getConnection().commit();
        JdbcUtils.freeConnection();
    }

    public static void rollback() throws SQLException {
        JdbcUtils.getConnection().rollback();
        JdbcUtils.freeConnection();
    }
}
