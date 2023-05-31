package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.javabean.Topic;
import com.atguigu.qqzone.javabean.UserBasic;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserBasicController {

    private UserBasicService userBasicService;
    private TopicService topicService;

    public String login(String loginId, String pwd, HttpSession session) {
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if (userBasic != null) {
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);

            session.setAttribute("userBasic", userBasic);
            session.setAttribute("friend", userBasic);

            return "index";
        } else {
            return "login";
        }
    }

    public String friend(Integer id, HttpSession session) {
        UserBasic currentFriend = userBasicService.getUserBasicById(id);
        List<Topic> topicList = topicService.getTopicList(currentFriend);

        currentFriend.setTopicList(topicList);

        session.setAttribute("friend", currentFriend);

        return "index";
    }
}
