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

 Date: 11/02/2019 14:49:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for step
-- ----------------------------
DROP TABLE IF EXISTS `step`;
CREATE TABLE `step`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` int(10) UNSIGNED NOT NULL COMMENT '菜谱id',
  `sort` int(10) UNSIGNED DEFAULT 0 COMMENT '排序',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '步骤图片',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '步骤描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '步骤表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
