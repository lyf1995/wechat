/*
Navicat MySQL Data Transfer

Source Server         : lyf
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : wechat-mall

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-05-07 23:18:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contacts` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `province` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `province_city` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `detail_address` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_address
-- ----------------------------

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `vip_price` float(10,0) DEFAULT NULL,
  `retail_price` float(10,0) DEFAULT NULL,
  `subtitle` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `stock` float(10,0) DEFAULT NULL,
  `main_image` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `carousel_image` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `detail_image` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `type_id` int(11) NOT NULL,
  `is_delete` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `typeId` (`type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES ('1', '智利泰瑞贵族珍藏佳美娜干红葡萄酒750mL', '120', '130', '非凡深邃的红宝石颜色，红色浆果、烟草、巧克力和纯净的果香演绎珍藏赤霞珠的盛世繁华、热情而高雅。', '1000', null, '', '', '4', '0');

-- ----------------------------
-- Table structure for t_goods_order
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_order`;
CREATE TABLE `t_goods_order` (
  `id` int(11) NOT NULL,
  `goods_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `goods_number` int(11) DEFAULT NULL,
  `goods_price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_goods_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_type`;
CREATE TABLE `t_goods_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `type_describe` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `is_delete` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_goods_type
-- ----------------------------
INSERT INTO `t_goods_type` VALUES ('1', '食品生鲜', '食品生鲜分类', '0');
INSERT INTO `t_goods_type` VALUES ('2', '果蔬休闲', '果蔬休闲分类', '0');
INSERT INTO `t_goods_type` VALUES ('3', '精粮副食', '精粮副食分类', '0');
INSERT INTO `t_goods_type` VALUES ('4', '酒水茶饮', '酒水茶饮分类', '0');
INSERT INTO `t_goods_type` VALUES ('5', 'test1', 'test1分类', '0');
INSERT INTO `t_goods_type` VALUES ('6', 'test2', 'test2分类', '1');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `status` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `user_id` int(11) NOT NULL,
  `address_id` int(11) NOT NULL,
  `total_amount` float(255,0) DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `order_time` datetime DEFAULT NULL,
  `type` int(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `addressId` (`address_id`),
  KEY `userId` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_share
-- ----------------------------
DROP TABLE IF EXISTS `t_share`;
CREATE TABLE `t_share` (
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
-- Records of t_share
-- ----------------------------

-- ----------------------------
-- Table structure for t_share_user
-- ----------------------------
DROP TABLE IF EXISTS `t_share_user`;
CREATE TABLE `t_share_user` (
  `id` int(11) NOT NULL,
  `share_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `amounts` float(255,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_share_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_shoppingcar
-- ----------------------------
DROP TABLE IF EXISTS `t_shoppingcar`;
CREATE TABLE `t_shoppingcar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `goods_id` int(11) NOT NULL,
  `goods_number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`),
  KEY `goodsId` (`goods_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_shoppingcar
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `password` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `name` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `regist_time` datetime DEFAULT NULL,
  `recent_login_time` datetime DEFAULT NULL,
  `is_delete` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `type` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', '系统管理员', '2018-05-06 00:00:00', '2018-05-07 21:42:58', '0', '0');
INSERT INTO `t_user` VALUES ('2', '17826804112', '123', '张三', '2018-05-06 21:32:15', null, '0', '1');
INSERT INTO `t_user` VALUES ('3', '12312312312', '123', '李四', '2018-05-06 21:16:14', null, '0', '1');
INSERT INTO `t_user` VALUES ('4', '17826804000', '123', '王五', '2018-05-06 21:18:06', null, '0', '1');
INSERT INTO `t_user` VALUES ('5', '17474847811', '123', '赵六', '2018-05-06 21:46:25', null, '0', '1');
INSERT INTO `t_user` VALUES ('6', '12345678901', '123', '陈七', '2018-05-06 23:04:58', null, '0', '1');
