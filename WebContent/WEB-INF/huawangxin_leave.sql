/*==============================================================*/
/* Database name:  huawangxin_leave                             */
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/11/17 15:57:34                          */
/*==============================================================*/


drop database if exists huawangxin_leave;

/*==============================================================*/
/* Database: huawangxin_leave                                   */
/*==============================================================*/
create database huawangxin_leave;

use huawangxin_leave;

/*==============================================================*/
/* Table: basic_leave                                           */
/*==============================================================*/
create table basic_leave
(
   id                   varchar(32) not null,
   name                 varchar(32)  not null comment '用户名',
   department           varchar(32) not null comment '接收的部门',
   leave_start          varchar(255) comment '****年*月*日*时',
   leave_over           varchar(255) comment '****年*月*日*时',
   leave_time           varchar(255) comment '精确到0.5天',
   type                 varchar(255) comment '(事假，病假，公假，其他)',
   description          varchar(255) comment '可选',
   state                varchar(2) comment '状态',
   sort                 varchar(3) comment '排序,000-999',
   create_time          bigint(20) comment '创建时间',
   primary key (id)
);

/*==============================================================*/
/* Table: basic_monthly                                         */
/*==============================================================*/
create table basic_monthly
(
   id                   varchar(32) not null,
   name                 varchar(32)  not null comment '用户名',
   department           varchar(32) not null comment '接收的部门',
   url                  varchar(255) not null comment '月报地址',
   month                varchar(20) comment '所属月份',
   state                varchar(2) comment '状态',
   sort                 varchar(3) comment '排序,000-999',
   create_time          bigint(20) comment '创建时间',
   primary key (id)
);

/*==============================================================*/
/* Table: basic_offduty                                         */
/*==============================================================*/
create table basic_offduty
(
   id                   varchar(32) not null,
   name                 varchar(32)  not null comment '用户名',
   department           varchar(32) not null comment '接收的部门',
   ovetime_start        varchar(255) comment '****年*月*日*时',
   ovetime_over         varchar(255) comment '****年*月*日*时',
   overtime             varchar(255) comment '精确到0.5天',
   reason               varchar(255) comment '调休事由',
   state                varchar(2) comment '状态',
   sort                 varchar(3) comment '排序,000-999',
   create_time          bigint(20) comment '创建时间',
   primary key (id)
);

/*==============================================================*/
/* Table: basic_ovetime                                         */
/*==============================================================*/
create table basic_ovetime
(
   id                   varchar(32) not null,
   name                 varchar(32)  not null comment '用户名',
   department           varchar(32) not null comment '接收的部门',
   ovetime_start        varchar(255) comment '****年*月*日*时',
   ovetime_over         varchar(255) comment '****年*月*日*时',
   overtime             varchar(255) comment '精确到0.5天',
   reason               varchar(255) comment '加班事由',
   place                varchar(32) comment '加班地点',
   state                varchar(2) comment '状态',
   sort                 varchar(3) comment '排序,000-999',
   create_time          bigint(20) comment '创建时间',
   primary key (id)
);

/*==============================================================*/
/* Table: basic_user                                            */
/*==============================================================*/
create table basic_user
(
   id                   varchar(32) not null,
   name                 varchar(32)  not null comment '用户名',
   password             varchar(32) not null comment '密码',
   department           varchar(32) not null comment '所属部门(多个部门用,隔开)',
   position             varchar(32) not null comment '职位(00,01,02)',
   tel                  varchar(20) comment '电话',
   remain_time          varchar(255) comment '默认0',
   state                varchar(2) comment '状态',
   sort                 varchar(3) comment '排序,000-999',
   create_time          bigint(20) comment '创建日期',
   primary key (id)
);

INSERT INTO basic_user VALUES('01','张经理','111111','研发一组,研发二组','部门经理','110','0','0','000',201511170001);
INSERT INTO basic_user VALUES('02','赵经理','111111','研发三组','部门经理','111','0','0','000',201511170002);
INSERT INTO basic_user VALUES('0101','张组长','111111','研发一组','组长','112','0','0','000',201511170003);
INSERT INTO basic_user VALUES('0102','王组长','111111','研发二组','组长','113','0','0','000',201511170004);
INSERT INTO basic_user VALUES('0201','孙组长','111111','研发三组','组长','114','0','0','000',201511170005);
INSERT INTO basic_user VALUES('010101','张三','111111','研发一组','组员','115','0','0','000',201511170006);
INSERT INTO basic_user VALUES('010102','王五','111111','研发一组','组员','116','0','0','000',201511170007);
INSERT INTO basic_user VALUES('010201','李四','111111','研发二组','组员','117','0','0','000',201511170008);
INSERT INTO basic_user VALUES('020101','赵六','111111','研发三组','组员','118','0','0','000',201511170009);