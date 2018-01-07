-- 数据库初始化脚本

-- 创建数据库
-- CREATE DATABASE chat;
-- 使用数据库
use chat;

-- 创建表
DROP TABLE IF EXISTS user;
CREATE TABLE user(
  user_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户id',
  username VARCHAR(40) NOT NULL COMMENT '用户名（邮箱）',
  password VARCHAR(40) NOT NULL COMMENT '密码',
  nickname VARCHAR(20) NOT NULL COMMENT '昵称' DEFAULT '游客318',
  other VARCHAR(120) COMMENT '其他',
  PRIMARY KEY (user_id)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='用户基本信息';

DROP TABLE IF EXISTS friend_relation;
CREATE TABLE friend_relation(
  user_id BIGINT NOT NULL COMMENT '用户id',
  friend_id BIGINT NOT NULL COMMENT '好友id'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='好友关系表';

DROP TABLE IF EXISTS group_relation;
CREATE TABLE group_relation(
  user_id BIGINT NOT NULL COMMENT '用户id',
  group_id BIGINT NOT NULL COMMENT '群组id'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群组关系表';

-- 初始化数据
INSERT INTO
  user(username, password, nickname, other)
VALUES
  ('1', '1', '老大', '这人很懒，啥也没写'),
  ('2', '2', '老二', '平平淡淡才是真'),
  ('3', '3', '老三', '无');

INSERT INTO
  friend_relation(user_id, friend_id)
VALUES
  (1000, 1001),
  (1001, 1000),
  (1002, 1000),
  (1002, 1001),
  (1000, 1002),
  (1001, 1002);

-- V 1.1
-- 原始

-- V 1.2
-- 给用户昵称添加默认值 游客318

