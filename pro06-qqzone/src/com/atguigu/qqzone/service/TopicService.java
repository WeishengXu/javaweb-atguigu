package com.atguigu.qqzone.service;

import com.atguigu.qqzone.javabean.Topic;
import com.atguigu.qqzone.javabean.UserBasic;

import java.util.List;

public interface TopicService {
    public List<Topic> getTopicList(UserBasic userBasic);

    public Topic getTopicById(Integer id);

    public boolean addTopic(Topic topic);

    public boolean delTopic(Integer id);
}
