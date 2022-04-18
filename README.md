# BookManager
## 技术栈
- 后端技术：Spring + SpringMVC + MyBatis（SSM）
- 前端技术：LayUI
- 数据连接池：Druid
- 数据库：MySQL
- 项目管理：Maven
- 版本控制：Git
- Web容器：tomcat
- 反代服务器：nginx
- 云产品：腾讯云COS

## 项目介绍
- 基于SSM+Layuimini的教材管理系统

## 主要功能
- 本系统主要包括三种角色
- 管理员
    - 教材信息管理
    - 教材采购功能
    - 教师信息管理
    - 学生信息管理
- 教师
    - 教材申请功能
- 学生
    - 教材领取功能

## 项目预览
- [教材管理系统](https://book.iruochen.cn)

## 注意
有些表字段需要更改
```sql
-- 修改列名
-- book_apply
alter table book_apply change tea_id tid int;
alter table book_apply change book_id bid int;

-- book_receive
alter table book_receive change stu_id sid int;
alter table book_receive change book_id bid int;

-- 修改列名
-- book_pruchase
alter table book_purchase change admin_id aid int;
alter table book_purchase change book_id bid int;
alter table book_purchase add price double after count; 


-- book_apply表添加字段
alter table book_apply add status int after count; 
alter table book_apply change status status int default 0;
```