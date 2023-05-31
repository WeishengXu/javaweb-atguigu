package com.atguigu.qqzone.service;

import com.atguigu.qqzone.javabean.Reply;
import com.atguigu.qqzone.javabean.Topic;

import java.util.List;

public interface ReplyService {
    public List<Reply> getReplyList(Topic topic);

    public boolean addReply(Reply reply);

    public boolean delReply(Integer id);
}
