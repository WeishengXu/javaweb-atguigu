package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.ReplyDao;
import com.atguigu.qqzone.javabean.HostReply;
import com.atguigu.qqzone.javabean.Reply;
import com.atguigu.qqzone.javabean.Topic;
import com.atguigu.qqzone.javabean.UserBasic;
import com.atguigu.qqzone.service.HostReplyService;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    private ReplyDao replyDao;
    private UserBasicService userBasicService;
    private HostReplyService hostReplyService;

    @Override
    public List<Reply> getReplyList(Topic topic) {
        List<Reply> replyList = replyDao.getReplyList(topic);
        for (Reply reply : replyList) {
            UserBasic author = userBasicService.getUserBasicById(reply.getAuthor().getId());
            HostReply hostReply = hostReplyService.getHostReply(reply);

            reply.setHostReply(hostReply);
            reply.setAuthor(author);
        }
        return replyList;
    }

    @Override
    public boolean addReply(Reply reply) {
        return replyDao.addReply(reply);
    }

    @Override
    public boolean delReply(Integer id) {
        Reply reply = replyDao.getReplyById(id);
        if (reply != null) {
            HostReply hostReply = hostReplyService.getHostReply(reply);
            if (hostReply != null) {
                if (hostReplyService.delHostReply(reply)) {
                    return replyDao.delReply(id);
                }
            } else {
                return replyDao.delReply(id);
            }
        }
        return false;
    }
}
