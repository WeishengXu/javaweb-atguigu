package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.daoUtils.BaseDao;
import com.atguigu.qqzone.dao.UserBasicDao;
import com.atguigu.qqzone.javabean.UserBasic;

import java.util.List;

public class UserBasicDaoImpl extends BaseDao implements UserBasicDao {
    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        String sql = "select * from t_user_basic where loginId = ? and pwd = ?";
        try {
            return super.executeQuery(UserBasic.class, sql, loginId, pwd).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {
        String sql = "select t_user_basic.id, loginId, nickName, pwd, headImg" +
                " from t_user_basic left join t_friend on t_user_basic.id = t_friend.fid where t_friend.uid = ?";
        try {
            return super.executeQuery(UserBasic.class, sql, userBasic.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        String sql = "select * from t_user_basic where id = ?";
        try {
            return super.executeQuery(UserBasic.class, sql, id).get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
