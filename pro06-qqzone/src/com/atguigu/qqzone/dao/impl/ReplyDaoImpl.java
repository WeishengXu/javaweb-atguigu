package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.daoUtils.BaseDao;
import com.atguigu.qqzone.dao.ReplyDao;
import com.atguigu.qqzone.javabean.Reply;
import com.atguigu.qqzone.javabean.Topic;

import java.sql.SQLException;
import java.util.List;

public class ReplyDaoImpl extends BaseDao implements ReplyDao {
    @Override
    public List<Reply> getReplyList(Topic topic) {
        String sql = "select * from t_reply where topic = ?";
        try {
            return super.executeQuery(Reply.class, sql, topic.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Reply getReplyById(Integer id) {
        String sql = "select * from t_reply where id = ?";
        List<Reply> replyList = null;
        try {
            replyList = super.executeQuery(Reply.class, sql, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (replyList != null && replyList.size() > 0) {
            return replyList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean addReply(Reply reply) {
        String sql = "insert into t_reply (content, replyDate, author, topic) value (?, ?, ?, ?)";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, reply.getContent(), reply.getReplyDate(), reply.getAuthor().getId(), reply.getTopic().getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }

    @Override
    public boolean delReply(Integer id) {
        String sql = "delete from t_reply where id = ?";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }
}
