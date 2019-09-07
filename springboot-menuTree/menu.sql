/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : spring

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 07/09/2019 22:51:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '金浔', 0);
INSERT INTO `menu` VALUES (2, '业务部', 1);
INSERT INTO `menu` VALUES (3, '技术部', 1);
INSERT INTO `menu` VALUES (4, '法务部', 1);
INSERT INTO `menu` VALUES (5, '铜事业部', 2);
INSERT INTO `menu` VALUES (6, '锌事业部', 2);
INSERT INTO `menu` VALUES (7, '铅事业部', 2);
INSERT INTO `menu` VALUES (8, '铜销售', 5);
INSERT INTO `menu` VALUES (9, '铜销售1部', 8);

SET FOREIGN_KEY_CHECKS = 1;
