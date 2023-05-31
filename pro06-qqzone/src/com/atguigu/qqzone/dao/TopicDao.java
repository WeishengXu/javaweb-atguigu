package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.javabean.Topic;
import com.atguigu.qqzone.javabean.UserBasic;

import java.util.List;

public interface TopicDao {
    public List<Topic> getTopicList(UserBasic userBasic);

    public boolean addTopic(Topic topic);

    public boolean delTopic(Integer id);

    public Topic getTopicById(Integer id);
}
