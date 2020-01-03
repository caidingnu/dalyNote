/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 129.28.171.44:3306
 Source Schema         : caidingnu

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 04/01/2020 02:24:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `valid` int(2) NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`news_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (8, '习近平主持召开中央财经委员会第六次会议 发表重要讲话', 1, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:53:36');
INSERT INTO `news` VALUES (9, '设小组审视偏颇教材 香港立法会对“毒教材”出手', 1, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:53:36');
INSERT INTO `news` VALUES (10, '美国国防部：总统特朗普下令暗杀伊朗少将', 1, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:53:36');
INSERT INTO `news` VALUES (11, '1月3日外交部例行记者会(全文)', 1, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:53:36');
INSERT INTO `news` VALUES (12, '武汉卫健委最新通报：共发现44例病毒性肺炎患者', 1, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:53:36');
INSERT INTO `news` VALUES (13, '被美国“羞辱”后 德国上下彻底“怒了”', 0, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:51:11');
INSERT INTO `news` VALUES (14, '被美国“羞辱”后 德国上下彻底“怒了”', 0, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:51:11');
INSERT INTO `news` VALUES (15, '被美国“羞辱”后 德国上下彻底“怒了”', 0, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:51:11');
INSERT INTO `news` VALUES (16, '被美国“羞辱”后 德国上下彻底“怒了”', 0, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:52:17');
INSERT INTO `news` VALUES (17, '被美国“羞辱”后 德国上下彻底“怒了”', 0, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:52:17');
INSERT INTO `news` VALUES (18, '被美国“羞辱”后 德国上下彻底“怒了”', 0, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:52:17');
INSERT INTO `news` VALUES (19, '被美国“羞辱”后 德国上下彻底“怒了”', 0, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:52:17');
INSERT INTO `news` VALUES (20, '被美国“羞辱”后 德国上下彻底“怒了”', 0, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:52:17');
INSERT INTO `news` VALUES (21, '被美国“羞辱”后 德国上下彻底“怒了”', 0, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:52:17');
INSERT INTO `news` VALUES (22, '被美国“羞辱”后 德国上下彻底“怒了”', 0, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:52:17');
INSERT INTO `news` VALUES (23, '被美国“羞辱”后 德国上下彻底“怒了”', 0, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:52:17');
INSERT INTO `news` VALUES (24, '被美国“羞辱”后 德国上下彻底“怒了”', 0, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:52:18');
INSERT INTO `news` VALUES (25, '被美国“羞辱”后 德国上下彻底“怒了”', 0, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:52:18');
INSERT INTO `news` VALUES (26, '被美国“羞辱”后 德国上下彻底“怒了”', 0, NULL, '原标题：习近平主持召开中央财经委员会第六次会议强调 抓好黄河流域生态保护和高质量发展 大力推动成渝地区双城经济圈建设中央财经委员会第六次会议', '2020-01-04 01:52:18');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 2, '管理员');
INSERT INTO `role` VALUES (2, 3, '角色2');
INSERT INTO `role` VALUES (3, 4, '角色3');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(200) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 210 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '刘备', '123', '就被', 22);
INSERT INTO `user` VALUES (2, '关羽', '77', '云长', 55);
INSERT INTO `user` VALUES (3, '张飞', '55', '12', 88);

SET FOREIGN_KEY_CHECKS = 1;
