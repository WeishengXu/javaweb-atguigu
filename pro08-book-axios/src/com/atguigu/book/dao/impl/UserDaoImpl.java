package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.UserDao;
import com.atguigu.book.javabean.User;
import com.atguigu.myssm.daoUtils.BaseDao;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User getUser(String userName) {
        String sql = "select * from t_user where userName = ?";
        List<User> userList = null;
        try {
            userList = super.executeQuery(User.class, sql, userName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User getUser(String userName, String userPwd) {
        String sql = "select * from t_user where userName = ? and userPwd = ?";
        List<User> userList = null;
        try {
            userList = super.executeQuery(User.class, sql, userName, userPwd);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    public void addUser(User user) {
        String sql = "insert into t_user (userName, userPwd, userEmail, userRole) value (?, ?, ?, ?)";
        int id = 0;
        try {
            id = super.executeUpdate(sql, true, user.getUserName(), user.getUserPwd(), user.getUserEmail(), user.getUserRole());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        user.setUserId(id);
    }
}
