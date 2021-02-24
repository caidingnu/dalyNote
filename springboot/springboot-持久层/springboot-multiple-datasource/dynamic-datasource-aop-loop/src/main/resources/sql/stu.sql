/*
 Navicat Premium Data Transfer

 Source Server         : 127
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : 127.0.0.1:3306
 Source Schema         : stu

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 10/07/2020 13:24:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `account` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `reg_date` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `last_login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后一次登录',
  `is_allow` int(1) NULL DEFAULT NULL COMMENT '是否启用，1 启用，0 停用',
  `roles` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'AA', '123', NULL, NULL, NULL, 1, NULL);
INSERT INTO `admin` VALUES (2, 'BB', '456', '1', NULL, NULL, 1, NULL);
INSERT INTO `admin` VALUES (7, 'bb', '1', '12', NULL, NULL, 1, NULL);
INSERT INTO `admin` VALUES (9, 'dd', '1', '123', NULL, NULL, 0, NULL);

-- ----------------------------
-- Table structure for lab
-- ----------------------------
DROP TABLE IF EXISTS `lab`;
CREATE TABLE `lab`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `code` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `class_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级名称',
  `class_id` int(11) NULL DEFAULT NULL COMMENT '班级id',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` int(11) NULL DEFAULT NULL COMMENT '性别：1 男，0 女',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lab
-- ----------------------------
INSERT INTO `lab` VALUES (1, '张三', '2017', '17外包1班', NULL, '1234', 1, '无');
INSERT INTO `lab` VALUES (2, '李四', '2017', '17外包2班', NULL, '1234', 1, '无');
INSERT INTO `lab` VALUES (3, '王五', '2018', '17外包3班', NULL, '123', 2, '无');
INSERT INTO `lab` VALUES (4, '赵六', '2019', '17外包4班', NULL, '123', 2, '无');
INSERT INTO `lab` VALUES (25, '张一', '2017', '17外包1班', NULL, NULL, NULL, '线下跟班授课');
INSERT INTO `lab` VALUES (26, '张二', '2017', '17外包2班', NULL, NULL, NULL, '线下跟班授课');
INSERT INTO `lab` VALUES (27, '张三', '2017', '17外包3班', NULL, NULL, NULL, '线下跟班授课');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(8) NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_allow` int(1) NULL DEFAULT NULL,
  `menus` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', 1, '252,253,255,251,254');

-- ----------------------------
-- Table structure for role_auth
-- ----------------------------
DROP TABLE IF EXISTS `role_auth`;
CREATE TABLE `role_auth`  (
  `id` int(8) NOT NULL,
  `role_id` int(8) NULL DEFAULT NULL,
  `menu_id` int(8) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `code` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `class_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级名称',
  `class_id` int(11) NULL DEFAULT NULL COMMENT '班级id',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` int(11) NULL DEFAULT NULL COMMENT '性别：1 男，0 女',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (104, '张一', '2017', '17外包1班', NULL, NULL, NULL, '线下跟班授课');
INSERT INTO `student` VALUES (105, '张二', '2017', '17外包2班', NULL, NULL, NULL, '线下跟班授课');
INSERT INTO `student` VALUES (106, '张三', '2017', '17外包3班', NULL, NULL, NULL, '线下跟班授课');
INSERT INTO `student` VALUES (109, '---', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (110, '---', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int(11) NOT NULL DEFAULT 0 COMMENT '父ID',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '菜单图标',
  `href` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '链接',
  `target` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '_self' COMMENT '链接打开方式',
  `sort` int(11) NULL DEFAULT 0 COMMENT '菜单排序',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '状态(0:禁用,1:启用)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `create_at` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `delete_at` timestamp(0) NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `title`(`title`) USING BTREE,
  INDEX `href`(`href`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 256 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES (250, 0, '用户管理', '', '', '_self', 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (251, 250, '管理员管理', '', '/admin/toList', '_self', 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (252, 250, '学生管理', '', '/student/toList', '_self', 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (253, 0, '系统设置', '', '', '_self', 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (254, 253, '菜单管理', '', '/systemMenu/toList', '_self', 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (255, 253, '角色管理', '', '/role/toList', '_self', 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_menu` VALUES (256, 250, '实验室管理', '', '/lab/toList', '_self', 3, 1, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
