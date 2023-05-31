package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.javabean.Reply;
import com.atguigu.qqzone.javabean.Topic;

import java.util.List;

public interface ReplyDao {
    public List<Reply> getReplyList(Topic topic);

    public Reply getReplyById(Integer id);

    public boolean addReply(Reply reply);

    public boolean delReply(Integer id);
}
