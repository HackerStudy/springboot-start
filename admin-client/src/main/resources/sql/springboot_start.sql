/*
Navicat MySQL Data Transfer

Source Server         : LocalMysql
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : adminclient

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-04-16 15:48:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `authorization` varchar(255) DEFAULT NULL COMMENT '功能权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'superadmin', '全部权限');
INSERT INTO `role` VALUES ('3', 'admin', '只能对用户进行操作');
