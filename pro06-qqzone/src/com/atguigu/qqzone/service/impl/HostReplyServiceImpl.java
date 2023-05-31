package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.HostReplyDao;
import com.atguigu.qqzone.javabean.HostReply;
import com.atguigu.qqzone.javabean.Reply;
import com.atguigu.qqzone.service.HostReplyService;

public class HostReplyServiceImpl implements HostReplyService {
    private HostReplyDao hostReplyDao;
    @Override
    public HostReply getHostReply(Reply reply) {
        HostReply hostReply = hostReplyDao.getHostReply(reply);
        return hostReply;
    }

    @Override
    public boolean delHostReply(Reply reply) {
        return hostReplyDao.delHostReply(reply);
    }
}
