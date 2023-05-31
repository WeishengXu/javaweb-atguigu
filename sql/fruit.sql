show databases;
use atguigu;
show tables;

select *
from t_user;

select count(1) cnt
from t_user;


create table t_bank
(
    id      int primary key auto_increment comment '账号主键',
    account varchar(20) not null unique comment '账号',
    money   int unsigned comment '金额，非负'
);

insert into t_bank (account, money)
values ('daboniu', 1000),
       ('wsheng', 1000);

select *
from t_bank;

create table t_customer
(
    id     int primary key auto_increment comment '客户主键',
    name   varchar(20) comment '客户名称',
    gender varchar(4) comment '客户性别',
    age    int comment '客户年龄',
    salary double(8, 1) comment '客户工资',
    phone  varchar(11) comment '客户电话'
);

select *
from t_customer;

create table if not exists t_fruit
(
    fid    int primary key auto_increment comment '水果主键',
    fname  varchar(20) not null comment '水果名称',
    price  int         default null comment '水果价格',
    fcount int         default null comment '水果库存',
    remark varchar(50) default null comment '备注'
);

insert into t_fruit(fid, fname, price, fcount, remark)
values (1, '红富士', 5, 16, '红富士也是苹果!'),
       (2, '大瓜', 5, 100, '王校长的瓜真香'),
       (3, '南瓜', 4, 456, '水果真好吃'),
       (4, '苦瓜', 5, 55, '苦瓜很好吃'),
       (5, '莲雾', 9, 99, '莲雾是一种神奇的水果'),
       (6, '羊角蜜', 4, 30, '羊角蜜是一种神奇的瓜'),
       (7, '啃大瓜', 13, 123, '孤瓜');

insert into t_fruit(fid, fname, price, fcount, remark)
values (8, '水果1', 5, 16, '水果!'),
       (9, '水果2', 5, 16, '水果!'),
       (10, '水果3', 5, 16, '水果!'),
       (11, '水果4', 5, 16, '水果!');

select *
from t_fruit;

