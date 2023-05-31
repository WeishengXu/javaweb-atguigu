package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.TopicDao;
import com.atguigu.qqzone.javabean.HostReply;
import com.atguigu.qqzone.javabean.Reply;
import com.atguigu.qqzone.javabean.Topic;
import com.atguigu.qqzone.javabean.UserBasic;
import com.atguigu.qqzone.service.HostReplyService;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    private TopicDao topicDao;
    private UserBasicService userBasicService;
    private ReplyService replyService;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        List<Topic> topicList = topicDao.getTopicList(userBasic);
        return topicList;
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = topicDao.getTopicById(id);
        UserBasic author = userBasicService.getUserBasicById(topic.getAuthor().getId());
        List<Reply> replyList = replyService.getReplyList(topic);

        topic.setAuthor(author);
        topic.setReplyList(replyList);
        return topic;
    }

    public boolean addTopic(Topic topic) {
        return topicDao.addTopic(topic);
    }

    @Override
    public boolean delTopic(Integer id) {
        Topic topic = topicDao.getTopicById(id);
        List<Reply> replyList = replyService.getReplyList(topic);
        for (Reply reply : replyList) {
            if (!replyService.delReply(reply.getId())) {
                return false;
            }
        }
        return topicDao.delTopic(id);
    }
}
