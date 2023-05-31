create table t_user_basic
(
    id       int         not null auto_increment primary key,
    loginId  varchar(20) not null unique,
    nickName varchar(50) not null,
    pwd      varchar(20) not null,
    headImg  varchar(20) default null
) comment '用户登陆信息';

create table t_user_detail
(
    id        int not null primary key,
    realName  varchar(20) default null,
    telephone varchar(11) default null,
    email     varchar(30) default null,
    birth     datetime    default null,
    star      varchar(10) default null,
    foreign key (id) references t_user_basic (id)
) comment '用户详情信息';

create table t_friend
(
    id  int not null auto_increment primary key,
    uid int default null,
    fid int default null,
    foreign key (uid) references t_user_basic (id),
    foreign key (fid) references t_user_basic (id)
) comment '朋友关系';

create table t_topic
(
    id        int          not null auto_increment primary key,
    title     varchar(100) not null,
    content   varchar(500) not null,
    topicDate timestamp     not null,
    author    int          not null,
    foreign key (author) references t_user_basic (id)
) comment '日志';

create table t_reply
(
    id        int          not null auto_increment primary key,
    content   varchar(500) not null,
    replyDate datetime     not null,
    author    int          not null,
    topic     int          not null,
    foreign key (author) references t_user_basic (id),
    foreign key (topic) references t_topic (id)
) comment '回复';

create table t_host_reply
(
    id            int          not null auto_increment primary key,
    content       varchar(500) not null,
    hostReplyDate datetime     not null,
    author        int          not null,
    reply         int          not null,
    foreign key (author) references t_user_basic (id),
    foreign key (reply) references t_reply (id)
) comment '主人回复';

INSERT INTO t_user_basic(id, loginId, nickName, pwd, headImg)
VALUES (1, 'u001', 'jim', 'ok', 'h1.jpeg'),
       (2, 'u002', 'tom', 'ok', 'h2.jpeg'),
       (3, 'u003', 'kate', 'ok', 'h3.jpeg'),
       (4, 'u004', 'lucy', 'ok', 'h4.jpeg'),
       (5, 'u005', '张三丰', 'ok', 'h5.jpeg');

INSERT INTO t_friend(id, uid, fid)
VALUES (1, 1, 2),
       (2, 1, 3),
       (3, 1, 4),
       (4, 1, 5),
       (5, 2, 3),
       (6, 2, 1),
       (7, 2, 4),
       (8, 3, 1),
       (9, 3, 2),
       (10, 5, 1);

INSERT INTO t_topic(id, title, content, topicDate, author)
VALUES (1, '我的空间开通了，先做自我介绍！', '大家好，我是铁锤妹妹！', '2021-06-18 11:25:30', 2),
       (2, '我的空间', '我的空间', '2021-07-14 16:16:40', 1);

INSERT INTO t_reply(id, content, replyDate, author, topic)
VALUES (1, '回复', '2021-07-14 16:16:54', 2, 2),
       (2, '回复2222', '2021-07-14 16:17:11', 3, 2),
       (3, '这里是第三个回复', '2021-07-14 16:30:49', 1, 2);


select *
from t_user_basic
         left join t_friend on t_user_basic.id = t_friend.fid
where t_friend.uid = 1;

select *
from t_topic;

INSERT INTO t_topic(title, content, topicDate, author)
VALUES ('测试删除3', '测试删除', '2021-06-18 11:25:30', 1),
       ('测试删除4', '测试删除', '2021-06-18 11:25:30', 1),
       ('测试删除5', '测试删除', '2021-06-18 11:25:30', 1),
       ('测试删除6', '测试删除', '2021-06-18 11:25:30', 1),
       ('测试删除7', '测试删除', '2021-06-18 11:25:30', 1),
       ('测试删除8', '测试删除', '2021-06-18 11:25:30', 1);
