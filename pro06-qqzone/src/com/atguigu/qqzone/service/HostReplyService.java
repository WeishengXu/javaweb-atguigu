package com.atguigu.qqzone.service;

import com.atguigu.qqzone.javabean.HostReply;
import com.atguigu.qqzone.javabean.Reply;

public interface HostReplyService {
    public HostReply getHostReply(Reply reply);

    boolean delHostReply(Reply reply);
}
