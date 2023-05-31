package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.daoUtils.BaseDao;
import com.atguigu.qqzone.dao.TopicDao;
import com.atguigu.qqzone.javabean.Topic;
import com.atguigu.qqzone.javabean.UserBasic;

import java.sql.SQLException;
import java.util.List;

public class TopicDaoImpl extends BaseDao implements TopicDao {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        String sql = "select * from t_topic where author = ?";
        try {
            return super.executeQuery(Topic.class, sql, userBasic.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addTopic(Topic topic) {
        String sql = "insert into t_topic (title, content, topicDate, author) value (?, ?, ?, ?)";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, topic.getTitle(), topic.getContent(), topic.getTopicDate(), topic.getAuthor().getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }

    @Override
    public boolean delTopic(Integer id) {
        String sql = "delete from t_topic where id = ?";
        int rows = 0;
        try {
            rows = super.executeUpdate(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }

    @Override
    public Topic getTopicById(Integer id) {
        String sql = "select * from t_topic where id = ?";
        try {
            return super.executeQuery(Topic.class, sql, id).get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
