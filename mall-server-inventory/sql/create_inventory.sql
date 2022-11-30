/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50162
 Source Host           : localhost:3306
 Source Schema         : db_sixstar_springcloud_inventory

 Target Server Type    : MySQL
 Target Server Version : 50162
 File Encoding         : 65001

 Date: 27/11/2022 16:36:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

create database db_inventory;

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory`  (
  `inventory_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库存ID',
  `shop_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `shop_count` int(8) NOT NULL COMMENT '库存数量'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
