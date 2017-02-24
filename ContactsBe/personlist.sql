/*
Navicat MySQL Data Transfer

Source Server         : water
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-02-22 11:02:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for personlist
-- ----------------------------
DROP TABLE IF EXISTS `personlist`;
CREATE TABLE `personlist` (
  `birthday` varchar(255) NOT NULL,
  `set` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `detailAddress` varchar(255) NOT NULL,
  `phoneNumber` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `zip` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personlist
-- ----------------------------
INSERT INTO `personlist` VALUES ('1999-01-01', '家', 'Water', '广东', '汕头', '广东省广州市天河区华南农业大学', '天河区华南农业大学', '15113110298', '13602803974@163.com', '515162');
INSERT INTO `personlist` VALUES ('1998-02-01', '公司', 'Zico', '广东', '广州', '广东省广州市天河区华南农业大学', '天河区华南农业大学', '13333330298', '13333330298@163.com', '510642');