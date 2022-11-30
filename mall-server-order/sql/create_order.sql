/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50162
 Source Host           : localhost:3306
 Source Schema         : db_sixstar_springcloud_order

 Target Server Type    : MySQL
 Target Server Version : 50162
 File Encoding         : 65001

 Date: 27/11/2022 16:37:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

create database db_order;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单ID',
  `order_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单名称',
  `order_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单金额',
  `inventory_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库存ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
