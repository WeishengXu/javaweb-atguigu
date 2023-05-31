package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.daoUtils.BaseDao;
import com.atguigu.qqzone.dao.HostReplyDao;
import com.atguigu.qqzone.javabean.HostReply;
import com.atguigu.qqzone.javabean.Reply;

import java.sql.SQLException;
import java.util.List;

public class HostReplyDaoImpl extends BaseDao implements HostReplyDao {
    @Override
    public HostReply getHostReply(Reply reply) {
        String sql = "select * from t_host_reply where reply = ?";
        try {
            List<HostReply> hostReplyList = super.executeQuery(HostReply.class, sql, reply.getId());
            if (hostReplyList.size() > 0) {
                return hostReplyList.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delHostReply(Reply reply) {
        String sql = "delete from t_host_reply where reply = ?";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, reply.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }
}
