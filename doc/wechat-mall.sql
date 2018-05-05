/*
Navicat MySQL Data Transfer

Source Server         : lyf
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : wechat-mall

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-05-05 21:43:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contacts` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT '',
  `province` varchar(255) DEFAULT NULL,
  `province_city` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `detail_address` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '',
  `vip_price` float(10,0) DEFAULT NULL,
  `price` float(10,0) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT '',
  `stock` float(10,0) DEFAULT NULL,
  `main_image` varchar(255) DEFAULT NULL,
  `carousel_image` varchar(255) DEFAULT '',
  `detail_image` varchar(255) DEFAULT '',
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `typeId` (`type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT '',
  `type_describe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of goodstype
-- ----------------------------

-- ----------------------------
-- Table structure for goods_order
-- ----------------------------
DROP TABLE IF EXISTS `goods_order`;
CREATE TABLE `goods_order` (
  `id` int(11) NOT NULL,
  `goods_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `goods_number` int(11) DEFAULT NULL,
  `goods_price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of goods_order
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT '',
  `status` varchar(255) DEFAULT '',
  `user_id` int(11) NOT NULL,
  `address_id` int(11) NOT NULL,
  `total_amount` float(255,0) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT '',
  `order_time` datetime DEFAULT NULL,
  `type` int(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `addressId` (`address_id`),
  KEY `userId` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share` (
  `id` int(11) NOT NULL,
  `share_user_id` int(11) NOT NULL,
  `goods_id` int(11) NOT NULL,
  `amounts` float(255,0) DEFAULT NULL,
  `share_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `shareUserId` (`share_user_id`),
  KEY `goodsId` (`goods_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of share
-- ----------------------------

-- ----------------------------
-- Table structure for share_user
-- ----------------------------
DROP TABLE IF EXISTS `share_user`;
CREATE TABLE `share_user` (
  `id` int(11) NOT NULL,
  `share_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `amounts` float(255,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of share_user
-- ----------------------------

-- ----------------------------
-- Table structure for shoppingcar
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcar`;
CREATE TABLE `shoppingcar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `goods_id` int(11) NOT NULL,
  `goods_number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`),
  KEY `goodsId` (`goods_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of shoppingcar
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) DEFAULT '',
  `password` varchar(255) DEFAULT '',
  `name` varchar(255) DEFAULT '',
  `regist_time` datetime DEFAULT NULL,
  `recent_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
