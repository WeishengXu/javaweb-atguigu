package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.javabean.Reply;
import com.atguigu.qqzone.javabean.Topic;
import com.atguigu.qqzone.javabean.UserBasic;
import com.atguigu.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.util.Date;

public class ReplyController {
    private ReplyService replyService;
    public String addReply(String content, Integer topicId, HttpSession session) {
        UserBasic author = (UserBasic) session.getAttribute("userBasic");
        Reply reply = new Reply(content, new Date(), author, new Topic(topicId));

        boolean flag = replyService.addReply(reply);
        if (flag) {
            System.out.println("reply add successfully!");
        } else {
            System.out.println("fail to add reply!");
        }

        return "redirect: topic.do?operate=topicDetail&id=" + topicId;
    }

    public String delReply(Integer replyId, Integer topicId) {
        boolean flag = replyService.delReply(replyId);
        if (flag) {
            System.out.println("reply delete successfully!");
        } else {
            System.out.println("fail to delete reply!");
        }

        return "redirect: topic.do?operate=topicDetail&id=" + topicId;
    }
}
