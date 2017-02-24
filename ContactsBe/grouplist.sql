/*
Navicat MySQL Data Transfer

Source Server         : water
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-02-24 23:41:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for grouplist
-- ----------------------------
DROP TABLE IF EXISTS `grouplist`;
CREATE TABLE `grouplist` (
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grouplist
-- ----------------------------
INSERT INTO `grouplist` VALUES ('家');
INSERT INTO `grouplist` VALUES ('公司');
