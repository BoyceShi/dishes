/*
 Navicat Premium Data Transfer

 Source Server         : 我的阿里云数据库
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 47.105.61.77:3306
 Source Schema         : dishes

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 11/02/2019 14:48:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu_detail
-- ----------------------------
DROP TABLE IF EXISTS `menu_detail`;
CREATE TABLE `menu_detail`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` int(10) UNSIGNED NOT NULL COMMENT '菜谱id',
  `ingredient_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '食材名',
  `unit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '食材单位',
  `num` int(10) UNSIGNED NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜谱明细表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
