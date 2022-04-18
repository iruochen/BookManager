/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/3/26 14:29:58                           */
/*==============================================================*/


drop table if exists admin;

drop table if exists book;

drop table if exists book_apply;

drop table if exists book_purchase;

drop table if exists book_receive;

drop table if exists department;

drop table if exists student;

drop table if exists teacher;

drop table if exists user;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   id                   int not null auto_increment,
   user_id              int not null,
   admin_id             varchar(9) not null,
   admin_name           varchar(20) not null,
   sex                  varchar(5) not null,
   primary key (id),
   unique key AK_Key_2 (admin_id)
);

alter table admin comment '教材管理员表';

/*==============================================================*/
/* Table: book                                                  */
/*==============================================================*/
create table book
(
   id                   int not null auto_increment,
   book_id              varchar(25) not null,
   book_name            varchar(50) not null,
   book_author          varchar(20),
   book_press           varchar(25),
   book_price           float not null,
   book_num             int not null default 0,
   book_img_url         varchar(255),
   primary key (id),
   unique key AK_Key_2 (book_id)
);

alter table book comment '教材表';

/*==============================================================*/
/* Table: book_apply                                            */
/*==============================================================*/
create table book_apply
(
   id                   int not null auto_increment,
   book_id              int not null,
   tea_id               int not null,
   time                 date not null,
   count                int not null,
   primary key (id)
);

alter table book_apply comment '教材申请表';

/*==============================================================*/
/* Table: book_purchase                                         */
/*==============================================================*/
create table book_purchase
(
   id                   int not null auto_increment,
   book_id              int not null,
   admin_id             int not null,
   time                 date not null,
   count                int not null,
   primary key (id)
);

alter table book_purchase comment '教材采购表';

/*==============================================================*/
/* Table: book_receive                                          */
/*==============================================================*/
create table book_receive
(
   id                   int not null auto_increment,
   book_id              int not null,
   stu_id               int not null,
   time                 date not null,
   count                int not null,
   primary key (id)
);

alter table book_receive comment '教材领取表';

/*==============================================================*/
/* Table: department                                            */
/*==============================================================*/
create table department
(
   id                   int not null auto_increment,
   dept_name            varchar(25) not null,
   primary key (id),
   unique key AK_Key_2 (dept_name)
);

alter table department comment '院系表';

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   id                   int not null auto_increment,
   user_id              int not null,
   stu_id               varchar(9) not null,
   stu_name             varchar(20) not null,
   stu_sex              varchar(5) not null,
   dept_id              int not null,
   major                varchar(25) not null,
   class                varchar(7) not null,
   primary key (id),
   unique key AK_Key_2 (stu_id)
);

alter table student comment '学生表';

/*==============================================================*/
/* Table: teacher                                               */
/*==============================================================*/
create table teacher
(
   id                   int not null auto_increment,
   user_id              int not null,
   tea_id               varchar(9) not null,
   tea_name             varchar(20) not null,
   tea_sex              varchar(5) not null,
   dept_id              int not null,
   primary key (id),
   unique key AK_Key_2 (tea_id)
);

alter table teacher comment '教师表';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null auto_increment,
   username             varchar(25) not null,
   password             varchar(25) not null,
   role                 int not null,
   primary key (id),
   unique key AK_Key_2 (username)
);

alter table user comment '用户表';

alter table admin add constraint FK_user_admin foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table book_apply add constraint FK_apply_tea foreign key (tea_id)
      references teacher (id) on delete restrict on update restrict;

alter table book_apply add constraint FK_appply_book foreign key (book_id)
      references book (id) on delete restrict on update restrict;

alter table book_purchase add constraint FK_admin_purchase foreign key (admin_id)
      references admin (id) on delete restrict on update restrict;

alter table book_purchase add constraint FK_purchase_book foreign key (book_id)
      references book (id) on delete restrict on update restrict;

alter table book_receive add constraint FK_receive_book foreign key (book_id)
      references book (id) on delete restrict on update restrict;

alter table book_receive add constraint FK_receive_stu foreign key (stu_id)
      references student (id) on delete restrict on update restrict;

alter table student add constraint FK_stu_dept foreign key (dept_id)
      references department (id) on delete restrict on update restrict;

alter table student add constraint FK_user_stu foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table teacher add constraint FK_tea_dept foreign key (dept_id)
      references department (id) on delete restrict on update restrict;

alter table teacher add constraint FK_user_tea foreign key (user_id)
      references user (id) on delete restrict on update restrict;

