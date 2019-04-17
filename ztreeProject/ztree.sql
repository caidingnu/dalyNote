/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : ztree

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 17/04/2019 23:37:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `open` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `isParent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, '云南省菜单', 'true', 'true', 'http://search.chongbuluo.com/');
INSERT INTO `menu` VALUES (2, 0, '贵州省菜单', NULL, NULL, 'http://search.chongbuluo.com/');
INSERT INTO `menu` VALUES (3, 0, '四川省菜单', NULL, NULL, 'http://search.chongbuluo.com/');
INSERT INTO `menu` VALUES (4, 0, '浙江省菜单', NULL, NULL, 'http://search.chongbuluo.com/');
INSERT INTO `menu` VALUES (5, 1, '昆明', NULL, NULL, 'http://www.baidu.com/');
INSERT INTO `menu` VALUES (6, 1, '曲靖', NULL, NULL, 'http://search.chongbuluo.com/');
INSERT INTO `menu` VALUES (7, 1, '昭通', NULL, NULL, 'http://search.chongbuluo.com/');
INSERT INTO `menu` VALUES (8, 2, '贵阳', NULL, NULL, 'http://search.chongbuluo.com/');
INSERT INTO `menu` VALUES (9, 2, '遵义', NULL, NULL, 'http://search.chongbuluo.com/');
INSERT INTO `menu` VALUES (10, 2, '安顺', NULL, NULL, 'http://search.chongbuluo.com/');
INSERT INTO `menu` VALUES (11, 3, '成都', NULL, NULL, 'http://search.chongbuluo.com/');
INSERT INTO `menu` VALUES (12, 3, '绵阳', NULL, NULL, 'http://search.chongbuluo.com/');
INSERT INTO `menu` VALUES (13, 3, '雅安市', NULL, NULL, 'http://search.chongbuluo.com/');
INSERT INTO `menu` VALUES (14, 4, '杭州', NULL, NULL, 'http://search.chongbuluo.com/');
INSERT INTO `menu` VALUES (15, 4, '金华', NULL, NULL, 'http://search.chongbuluo.com/');
INSERT INTO `menu` VALUES (16, 4, '宁波', NULL, NULL, 'http://search.chongbuluo.com/');
INSERT INTO `menu` VALUES (25, 2, '11111', NULL, NULL, NULL);
INSERT INTO `menu` VALUES (29, NULL, '沙发沙发上', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
