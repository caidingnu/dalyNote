/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : caidingnu

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 12/06/2019 00:21:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `menun_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `update_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('031a5ae5611d49db9d8e88f36fde1643', '会计学院', '6', '2', '2019-04-11 20:48:51', '2019-04-11 20:48:51');
INSERT INTO `menu` VALUES ('25ee4b1fc8d6457ab05894cb1f366143', '关于我们', '20', '0', '2019-04-11 20:48:51', '2019-04-11 20:48:51');
INSERT INTO `menu` VALUES ('314f0f33a88b4a12ae857f4f0ff69266', '大数据学院', '7', '2', '2019-04-11 20:48:51', '2019-04-11 20:48:51');
INSERT INTO `menu` VALUES ('35f5561eacbb4fc8a164ce17730e90ba', '菜单管理', '0', '-1', '2019-04-11 20:48:51', '2019-04-11 20:48:50');
INSERT INTO `menu` VALUES ('5f60c8b0533c4a95ab5db826c02aa0fb', '英语学院', '8', '2', '2019-04-11 20:48:51', '2019-04-11 20:48:51');
INSERT INTO `menu` VALUES ('783e393c0d0a49b2a41e018fdbef1ca4', '000', '97973', '3', NULL, NULL);
INSERT INTO `menu` VALUES ('7c1cb301e94b4b16846a34192d96e6f3', '烟草学院', '9', '2', '2019-04-11 20:48:51', '2019-04-11 20:48:51\r\n2019-04-11 20:48:51\r\n2019-04-11 20:48:51\r\n2019-04-11 20:48:51');
INSERT INTO `menu` VALUES ('9bbc893d3f42487d866de7e4868a815a', '经管学院', '10', '2', '2019-04-11 20:48:51', '2019-04-11 20:48:51\r\n2019-04-11 20:48:51\r\n2019-04-11 20:48:51\r\n2019-04-11 20:48:51');
INSERT INTO `menu` VALUES ('bf770c99f91e44f7872b321b05c3b290', '学生管理', '3', '0', '2019-04-11 20:48:51', '2019-04-11 20:48:51');
INSERT INTO `menu` VALUES ('d8af5a6984f8463dbc6e20f342830744', '学院管理', '29', '0', '2019-04-11 20:48:51', '2019-04-11 20:48:51');
INSERT INTO `menu` VALUES ('rrrrrr', 'mybatis', '224', '8', '2018-9', '2019-9');
INSERT INTO `menu` VALUES ('rrrrrr3', 'mybatis', '224', '8', '2018-9', '2019-9');
INSERT INTO `menu` VALUES ('rrrrrrr', 'mybatis', '224', '8', '2018-9', '2019-9');
INSERT INTO `menu` VALUES ('wwww', '蔡定努', '33', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
