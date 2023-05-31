package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.javabean.Topic;
import com.atguigu.qqzone.javabean.UserBasic;
import com.atguigu.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

public class TopicController {
    private TopicService topicService;

    public String topicDetail(Integer id, HttpSession session) {
        Topic topic = topicService.getTopicById(id);

        session.setAttribute("topic", topic);

        return "frames/detail";
    }

    public String addTopic(String title, String content, HttpSession session) {
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        boolean flag = topicService.addTopic(new Topic(title, content, new Date(), userBasic));
        if (flag) {
            System.out.println("topic add successfully!");
        } else {
            System.out.println("fail to add topic!");
        }

        List<Topic> topicList = topicService.getTopicList(userBasic);
        userBasic.setTopicList(topicList);
        session.setAttribute("userBasic", userBasic);

        return "frames/main";
    }

    public String delTopic(Integer topicId, HttpSession session) {
        boolean flag = topicService.delTopic(topicId);
        if (flag) {
            System.out.println("topic delete successfully!");
        } else {
            System.out.println("fail to delete topic!");
        }

        UserBasic friend = (UserBasic) session.getAttribute("friend");
        List<Topic> topicList = topicService.getTopicList(friend);
        friend.setTopicList(topicList);
        session.setAttribute("friend", friend);

        return "frames/main";
    }

}
