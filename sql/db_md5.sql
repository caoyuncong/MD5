DROP DATABASE IF EXISTS db_md5;
CREATE DATABASE db_md5;

DROP TABLE IF EXISTS db_md5.md5;
CREATE TABLE db_md5.md5 (
  id                INT AUTO_INCREMENT PRIMARY KEY
  COMMENT 'PK',
  plainPassword     VARCHAR(255) NOT NULL
  COMMENT '密码明文',
  encryptedPassword VARCHAR(255) NOT NULL
  COMMENT '密码密文'
)
  COMMENT 'md5表';

SELECT *
FROM db_md5.md5;

SELECT count(*)
FROM db_csdn.user_new;

SELECT *
FROM db_csdn.user_new;