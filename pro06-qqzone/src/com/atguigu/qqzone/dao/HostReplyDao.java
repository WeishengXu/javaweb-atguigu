package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.javabean.HostReply;
import com.atguigu.qqzone.javabean.Reply;

public interface HostReplyDao {
    public HostReply getHostReply(Reply reply);

    public boolean delHostReply(Reply reply);
}
