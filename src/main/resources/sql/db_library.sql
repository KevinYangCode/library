/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : db_library

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 10/01/2020 14:01:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book`  (
  `book_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图书ID',
  `name` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '书名',
  `author` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `publish` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `isbn` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标准书号',
  `introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '简介',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `pub_date` date NULL DEFAULT NULL COMMENT '出版时间',
  `class_id` int(11) NOT NULL COMMENT '分类号',
  `pic` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '封面图片',
  `location` int(11) NOT NULL COMMENT '位置',
  `status` int(2) NOT NULL COMMENT '图书状态(on在架/null借出)',
  `owner` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '拥有者(0/1)',
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '图书书目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES (2, '书21', '作者2', '第一版', '9787115417305', '测试2', 100.00, '2016-05-06', 2, '/images/spring4.png', 2, 1, '4c1514d0f5b3441fb44531f60ca9a99a');
INSERT INTO `t_book` VALUES (3, '书3', '作者3', '第一版', '9787115417305', '测试3', 100.00, '2016-05-10', 3, '/images/spring4.png', 3, 1, '4c1514d0f5b3441fb44531f60ca9a99a');
INSERT INTO `t_book` VALUES (5, '书5', '作者5', '第一版', '9787115417305', '测试5', 100.00, '2016-05-18', 2, '/images/spring4.png', 2, 0, '5773991f4af74d84b0aa03db50ba4b4d');
INSERT INTO `t_book` VALUES (24, '1', '1', '1', '1', '11', 1.00, '2020-01-21', 2, '/images/spring4.png', 1, 0, '5773991f4af74d84b0aa03db50ba4b4d');
INSERT INTO `t_book` VALUES (25, '书1', 'ss', '1', 'a', '', 1.00, '2020-01-09', 7, '/images/spring4.png', 2, 0, '8027505d11044c86a4a471e9c8c2e410');
INSERT INTO `t_book` VALUES (26, 'aa', 'aa', 'aa', 'aaa', '', 11.00, '2020-01-01', 3, '/images/spring4.png', 2, 1, '0a55f84521df4ce99a1ac9b6218c7d15');
INSERT INTO `t_book` VALUES (29, '11', '1', '11', '1', '', 11.00, '2020-01-14', 2, '/images/spring4.png', 2, 1, '57c98c8043304fab98314989c60a6f03');
INSERT INTO `t_book` VALUES (36, '啊啊', '11', '1', '11', '', 1.00, '2020-01-07', 5, '/images/spring4.png', 2, 1, '19ef853e4b3e4ae49de26a343f87849e');

-- ----------------------------
-- Table structure for t_bookshelf
-- ----------------------------
DROP TABLE IF EXISTS `t_bookshelf`;
CREATE TABLE `t_bookshelf`  (
  `bs_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '书架ID',
  `name` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '书架名称',
  PRIMARY KEY (`bs_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '书架表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_bookshelf
-- ----------------------------
INSERT INTO `t_bookshelf` VALUES (1, '第一架1层');
INSERT INTO `t_bookshelf` VALUES (2, '第一架2层');
INSERT INTO `t_bookshelf` VALUES (3, '第一架3层');
INSERT INTO `t_bookshelf` VALUES (4, '第一架4层');
INSERT INTO `t_bookshelf` VALUES (5, '第一架5层');
INSERT INTO `t_bookshelf` VALUES (6, '第一架6层');
INSERT INTO `t_bookshelf` VALUES (7, '第二架1层');
INSERT INTO `t_bookshelf` VALUES (8, '第二架2层');
INSERT INTO `t_bookshelf` VALUES (9, '\r\n第二架3层');
INSERT INTO `t_bookshelf` VALUES (10, '第二架4层');
INSERT INTO `t_bookshelf` VALUES (11, '第二架5层');
INSERT INTO `t_bookshelf` VALUES (12, '第二架6层');

-- ----------------------------
-- Table structure for t_classify
-- ----------------------------
DROP TABLE IF EXISTS `t_classify`;
CREATE TABLE `t_classify`  (
  `class_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图书类别号',
  `class_name` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图书类别名',
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '图书分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_classify
-- ----------------------------
INSERT INTO `t_classify` VALUES (1, 'JAVA');
INSERT INTO `t_classify` VALUES (2, 'SQL');
INSERT INTO `t_classify` VALUES (3, 'JAVAEE');
INSERT INTO `t_classify` VALUES (4, '网络');
INSERT INTO `t_classify` VALUES (5, '软件工程');
INSERT INTO `t_classify` VALUES (6, '软考');
INSERT INTO `t_classify` VALUES (10, '算法');
INSERT INTO `t_classify` VALUES (11, '其它');
INSERT INTO `t_classify` VALUES (15, 'aa');

-- ----------------------------
-- Table structure for t_intention
-- ----------------------------
DROP TABLE IF EXISTS `t_intention`;
CREATE TABLE `t_intention`  (
  `inte_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新书采购意向ID',
  `name` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图书名称',
  `submitter` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '提交者',
  `isbn` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标准书号',
  `pic` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '图书图片',
  `reasons` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '原因',
  PRIMARY KEY (`inte_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '新书采购意向表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_intention
-- ----------------------------
INSERT INTO `t_intention` VALUES (1, 'SpringBoot1', '4c1514d0f5b3441fb44531f60ca9a99a', '31922121', NULL, NULL);
INSERT INTO `t_intention` VALUES (2, 'SpringBoot2', '4c1514d0f5b3441fb44531f60ca9a99a', '31922121', NULL, NULL);
INSERT INTO `t_intention` VALUES (3, 'SpringBoot3', '4c1514d0f5b3441fb44531f60ca9a99a', '31922121', NULL, NULL);

-- ----------------------------
-- Table structure for t_lend
-- ----------------------------
DROP TABLE IF EXISTS `t_lend`;
CREATE TABLE `t_lend`  (
  `lend_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '借阅记录ID',
  `book_id` int(11) NOT NULL COMMENT '图书ID',
  `reader_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '读者ID',
  `lend_date` date NOT NULL COMMENT '借出日期',
  `back_date` date NULL DEFAULT NULL COMMENT '归还日期',
  PRIMARY KEY (`lend_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '借阅信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_lend
-- ----------------------------
INSERT INTO `t_lend` VALUES (1, 1, '4c1514d0f5b3441fb44531f60ca9a99a', '2020-01-03', NULL);
INSERT INTO `t_lend` VALUES (2, 1, '4c1514d0f5b3441fb44531f60ca9a99a', '2020-01-03', NULL);
INSERT INTO `t_lend` VALUES (3, 1, '4c1514d0f5b3441fb44531f60ca9a99a', '2020-01-03', NULL);

-- ----------------------------
-- Table structure for t_menus
-- ----------------------------
DROP TABLE IF EXISTS `t_menus`;
CREATE TABLE `t_menus`  (
  `menus_id` int(5) NOT NULL AUTO_INCREMENT,
  `title` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单标题',
  `href` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转路径',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `target` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '转发方式(_blank/_self)',
  `parentId` int(11) NULL DEFAULT NULL COMMENT '父节点',
  PRIMARY KEY (`menus_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userUUID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '成员ID',
  `stuID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '成员学号/老师可用手机号',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
  `role` int(2) NULL DEFAULT NULL COMMENT '1为超管,2为普管，3没有权限',
  PRIMARY KEY (`userUUID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户登录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('084560fb042b40d9b28c9e41b213d3c3', '20170404430208', '123456', 1);
INSERT INTO `t_user` VALUES ('0a55f84521df4ce99a1ac9b6218c7d15', '20170407430132', '123456', 3);
INSERT INTO `t_user` VALUES ('19ef853e4b3e4ae49de26a343f87849e', '20170406430134', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);
INSERT INTO `t_user` VALUES ('2c9fdf91aef54eab84ee1b376c376c72', '20170404430533', 'ca0d5d5fc5c1175e0927f4fd60db114a', 3);
INSERT INTO `t_user` VALUES ('36e431a305f04014b8fa3097e9aec42a', '20170404430511', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);
INSERT INTO `t_user` VALUES ('39012074521a419f9214dfb43241c5de', '20170406430121', 'fc1709d0a95a6be30bc5926fdb7f22f4', 2);
INSERT INTO `t_user` VALUES ('3c8d8f1e675b42b1ac1088639a53e480', '20170408430119', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);
INSERT INTO `t_user` VALUES ('4a8d3a2d183e44a3b9bffdc26f7837b8', '20170408430115', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);
INSERT INTO `t_user` VALUES ('4c1514d0f5b3441fb44531f60ca9a99a', '20170404430140', '123456', 2);
INSERT INTO `t_user` VALUES ('5773991f4af74d84b0aa03db50ba4b4d', '20170404430106', '123456', 1);
INSERT INTO `t_user` VALUES ('57c98c8043304fab98314989c60a6f03', '20160406430232', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);
INSERT INTO `t_user` VALUES ('75192452592946699586e049c682fb2e', '20160404430143', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);
INSERT INTO `t_user` VALUES ('8027505d11044c86a4a471e9c8c2e410', '20160404430204', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);
INSERT INTO `t_user` VALUES ('93762a6a6dd04743a0eae2287695b6c8', '20180404430152', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);
INSERT INTO `t_user` VALUES ('96611473753141dfb7d173854aa98a38', '20180407430212', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);
INSERT INTO `t_user` VALUES ('9a7ecec29d7d41abb2494e34380f4f8d', '20180404430146', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);
INSERT INTO `t_user` VALUES ('a97b4d67447f4580ad367fecabecd31e', '20180407430116', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);
INSERT INTO `t_user` VALUES ('bc1c3bcb622d4bd19f06059cab76a965', '20180407430111', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);
INSERT INTO `t_user` VALUES ('bdc0710cfc30436c9a6d56fefc60a584', '20180404430249', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);
INSERT INTO `t_user` VALUES ('c5b80b9e6d8f498897093133bd6db635', '20180407430140', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);
INSERT INTO `t_user` VALUES ('c701f7843a564b6587abbae17508f6ec', '20180407430226', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);
INSERT INTO `t_user` VALUES ('d481721220d4478b8ffb9945e65683a6', '20180409430129', 'fc1709d0a95a6be30bc5926fdb7f22f4', 3);

SET FOREIGN_KEY_CHECKS = 1;
