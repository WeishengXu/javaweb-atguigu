drop table if exists t_order_item;
drop table if exists t_cart_item;
drop table if exists t_order;
drop table if exists t_user;
drop table if exists t_book;

create table t_book
(
    bookId        int          not null primary key auto_increment,
    bookImg       varchar(200) not null,
    bookName      varchar(20)   default null,
    bookPrice     double(10, 2) default null,
    bookAuthor    varchar(20)   default null,
    bookSaleCount int           default null,
    bookCount     int           default null,
    bookStatus    int           default 0
);

create table t_user
(
    userId    int         not null primary key auto_increment,
    userName  varchar(20) not null unique,
    userPwd   varchar(32) not null,
    userEmail varchar(100) default null,
    userRole  int          default null
);

create table t_order
(
    orderId     int          not null primary key auto_increment,
    orderNo     varchar(128) not null unique,
    orderDate   timestamp     default null,
    orderUser   int           default null,
    orderMoney  double(10, 2) default null,
    orderStatus int           default null,
    foreign key (orderUser) references t_user (userId)
);

create table t_order_item
(
    id       int not null primary key auto_increment,
    book   int default null,
    buyCount int default null,
    `order`  int default null,
    foreign key (book) references t_book (bookId),
    foreign key (`order`) references t_order (orderId)
);

create table t_cart_item
(
    id       int not null primary key auto_increment,
    book     int default null,
    buyCount int default null,
    `user`   int default null,
    foreign key (book) references t_book (bookId),
    foreign key (`user`) references t_user (userId)
);

insert into `t_book`(`bookId`, `bookImg`, `bookName`, `bookPrice`, `bookAuthor`, `bookSaleCount`, `bookCount`, `bookStatus`)
values (1, 'cyuyanrumenjingdian.jpg', 'C语言入门经典', 99.00, '亚历山大', 8, 197, 0),
       (2, 'santi.jpg', '三体', 48.95, '周杰伦', 18, 892, 0),
       (3, 'ailuntulingzhuan.jpg', '艾伦图灵传', 50.00, '刘若英', 12, 143, 0),
       (4, 'bainiangudu.jpg', '百年孤独', 40.00, '王力宏', 3, 98, 0),
       (5, 'biancheng.jpg', '边城', 30.00, '刘德华', 2, 99, 0),
       (6, 'jieyouzahuodian.jpg', '解忧杂货店', 27.00, '东野圭吾', 5, 100, 0),
       (7, 'zhongguozhexueshi.jpg', '中国哲学史', 45.00, '冯友兰', 3, 100, 0),
       (8, 'huranqiri.jpg', '忽然七日', 19.00, '劳伦', 50, 200, 0),
       (9, 'sudongpozhuan.jpg', '苏东坡传', 20.00, '林语堂', 50, 300, 0),
       (10, 'fusang.jpg', '扶桑', 20.00, '严歌岑', 10, 89, 0),
       (11, 'geihaizideshi.jpg', '给孩子的诗', 23.00, '北岛', 5, 99, 0);

insert into `t_user`(`userId`, `userName`, `userPwd`, `userEmail`, `userRole`)
values (1, 'lina', 'ok', 'lina@sina.com.cn', 0),
       (2, 'kate', 'ok', 'hello_kate@126.com', 1),
       (3, '鸠摩智', 'ok', 'jiujiu@126.com', 0),
       (4, '宝2021', 'ok', 'bao2021@sohu.com.cn', 0),
       (5, '宝2022', '123', 'bao2022@sohu.com.cn', 0);

insert into `t_order`(`orderId`, `orderNo`, `orderDate`, `orderUser`, `orderMoney`, `orderStatus`)
values (1, '5eaab6146dc54e0482fdb8b6120c229b_20211025112519', '2021-10-25 11:25:20', 1, 506.90, 0),
       (2, 'f5a22aac925d42eabc6b49c45a3eb74f_20211025113004', '2021-10-25 11:30:04', 1, 48.95, 0),
       (3, '8a245df4359e4224b531cf121c4acab3_20211025113019', '2021-10-25 11:30:20', 1, 0.00, 0),
       (4, 'b521cd49ab2943f0bbc0630c95978f1c_20211025113039', '2021-10-25 11:30:40', 1, 48.95, 0),
       (5, 'd4f366a82cd4491c9899b181753804b4_20211025113151', '2021-10-25 11:31:52', 1, 48.95, 0),
       (6, '8f5869a839f4483e947bd2c3163f3c23_20211025113159', '2021-10-25 11:31:59', 1, 48.95, 0),
       (7, 'c5fcd95dbe7f49669f96b4ad6444ae6b_20211025120531', '2021-10-25 12:05:32', 1, 147.95, 0),
       (8, '6240ec3e5ac04e3583e1beb75a9e94ec_20211025120542', '2021-10-25 12:05:42', 1, 147.95, 0);

insert into `t_order_item`(`id`, `book`, `buyCount`, `order`)
values (1, 1, 1, 1),
       (2, 2, 2, 1),
       (3, 10, 1, 1),
       (4, 3, 5, 1),
       (5, 4, 1, 1),
       (6, 2, 1, 2),
       (7, 2, 1, 4),
       (8, 2, 1, 5),
       (9, 2, 1, 6),
       (10, 1, 1, 7),
       (11, 2, 1, 7),
       (12, 1, 1, 8),
       (13, 2, 1, 8);

insert into `t_cart_item`(`id`, `book`, `buyCount`, `user`)
values (1, 1, 1, 2),
       (2, 5, 1, 1),
       (3, 1, 2, 1),
       (4, 2, 13, 1),
       (5, 3, 2, 1),
       (6, 4, 1, 1),
       (7, 6, 1, 1),
       (8, 7, 1, 1),
       (9, 8, 1, 1),
       (10, 9, 1, 1),
       (11, 10, 1, 1),
       (12, 11, 4, 1);
