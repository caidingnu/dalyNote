/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : test1

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 30/06/2019 16:04:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for com_emp
-- ----------------------------
DROP TABLE IF EXISTS `com_emp`;
CREATE TABLE `com_emp`  (
  `emp_id` int(11) NOT NULL,
  `company_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of com_emp
-- ----------------------------
INSERT INTO `com_emp` VALUES (1, 2);
INSERT INTO `com_emp` VALUES (2, 4);
INSERT INTO `com_emp` VALUES (3, 1);
INSERT INTO `com_emp` VALUES (4, 3);

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `company_id` int(11) NOT NULL,
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`company_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES (1, '能投');
INSERT INTO `company` VALUES (2, '建投');
INSERT INTO `company` VALUES (3, '交投');
INSERT INTO `company` VALUES (4, '城投');

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`emp_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES (1, '张飞', '223', '男');
INSERT INTO `emp` VALUES (2, '关羽', '44', '3');
INSERT INTO `emp` VALUES (3, '卧龙', '40', '0');
INSERT INTO `emp` VALUES (4, '曹操', '42', '2');
INSERT INTO `emp` VALUES (5, '蔡定努', '26', '男');
INSERT INTO `emp` VALUES (8, '是是是', '33', '男');
INSERT INTO `emp` VALUES (9, '是是是1', '33', '男');
INSERT INTO `emp` VALUES (10, '是是是2', '33', '男');
INSERT INTO `emp` VALUES (11, '是是是3', '33', '男');
INSERT INTO `emp` VALUES (12, '是是是4', '33', '男');

SET FOREIGN_KEY_CHECKS = 1;
