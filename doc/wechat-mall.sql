/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : wechat-mall

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-05-09 18:23:23
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
  `city` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `detail_address` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `is_default` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `is_delete` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_address
-- ----------------------------
INSERT INTO `t_address` VALUES ('1', '鲁钺锋', '17826804660', '浙江省', '杭州市', '西湖区', '西和公寓10幢', '1', '2', '0');
INSERT INTO `t_address` VALUES ('2', '鲁钺锋', '17826804660', '天津市', '天津市', '河东区', '哈哈', '0', '2', '0');
INSERT INTO `t_address` VALUES ('3', '张三', '17826804000', '北京市', '北京市', '东城区', '123123', '1', '3', '0');
INSERT INTO `t_address` VALUES ('4', '1111', '1111111111', '北京市', '北京市', '东城区', '11111', '0', '2', '1');

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
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES ('1', '智利泰瑞贵族珍藏佳美娜干红葡萄酒750mL', '120', '130', '非凡深邃的红宝石颜色，红色浆果、烟草、巧克力和纯净的果香演绎珍藏赤霞珠的盛世繁华、热情而高雅。', '100', null, '', '', '4', '0');
INSERT INTO `t_goods` VALUES ('2', '乌拉圭原装进口牛腱子 (1.6-1.8KG/块)', '117', '139', '臻选南美潘帕斯草原天然草饲牛，原装进口牛腱肉口感劲道，肉香层次丰富，让人回味无穷。适合炖、卤、烤，是您餐宴的加分之选。', '100', null, '', '', '1', '0');
INSERT INTO `t_goods` VALUES ('3', '冰岛Clearice大西洋鳕鱼(去皮4段) 560G/袋', '110', '130', '冰岛拥有全球最理想、稳定的纯净水源。这里出产的大西洋真鳕鱼，含高蛋白质、低脂肪，肉质白细鲜嫩，清口不腻。', '100', null, '', '', '1', '0');
INSERT INTO `t_goods` VALUES ('4', '阿根廷安格斯谷饲西冷牛排 300g/袋', '89', '99', '原汁原味，肉质嫩中带腴', '100', null, '', '', '1', '0');
INSERT INTO `t_goods` VALUES ('5', '新西兰 冷冻鲽鱼（盾吻鲽） 1kg/袋', '69', '79', '扁平少刺，肉嫩鲜美', '100', null, '', '', '1', '0');
INSERT INTO `t_goods` VALUES ('6', '马达加斯加Aqualma黑虎虾 800g/盒 30-35尾/kg', '198', '218', '马达加斯加-红树林生态保护区出产的黑虎虾享有“虾中香奈儿”的美誉。', '100', null, '', '', '1', '0');
INSERT INTO `t_goods` VALUES ('7', '浙江象山红美人柑橘--T15 （ 15颗/盒）', '198', '208', '15颗/盒，净重4.5斤，象山名果 赠礼佳品', '100', null, '', '', '2', '0');
INSERT INTO `t_goods` VALUES ('8', '永沁精选 花生牛轧糖150g/袋（分享装）', '15', '18', '奶香浓郁 嚼劲十足', '100', null, '', '', '2', '0');
INSERT INTO `t_goods` VALUES ('9', '永沁精选 蔓越莓巴旦木牛轧糖 454g/袋', '75', '85', '奶香浓郁 嚼劲十足', '100', null, '', '', '2', '0');
INSERT INTO `t_goods` VALUES ('10', '永沁精选 火星橙 8kg/箱 果径85mm', '155', '158', '香味浓郁，汁水充盈，素有“脐橙进房香满堂', '100', null, '', '', '2', '0');
INSERT INTO `t_goods` VALUES ('11', '丹东红颜草莓礼盒装 36颗 单果30-40g 1200g', '145', '148', '新鲜免洗草莓，个大香甜味美，水分充足可口', '100', null, '', '', '2', '0');
INSERT INTO `t_goods` VALUES ('12', '永沁精选 燕麦胚芽米 500g/盒 2盒', '65', '69', '精致燕麦胚芽米', '100', null, '', '', '3', '0');
INSERT INTO `t_goods` VALUES ('13', '禾然 素食佐餐酱210g', '28', '30', '禾然 素食佐餐酱210g', '100', null, '', '', '3', '0');
INSERT INTO `t_goods` VALUES ('14', '永沁精选 燕麦胚芽米 500g/盒 2盒', '65', '69', '永沁精选 燕麦胚芽米', '100', null, '', '', '3', '0');
INSERT INTO `t_goods` VALUES ('15', '禾然糙米醋 160ml', '13', '15', '禾然糙米醋', '100', null, '', '', '3', '0');
INSERT INTO `t_goods` VALUES ('16', '饭爷 三味浓素面酱180g/瓶', '16', '18', '饭爷 三味浓素面酱', '100', null, '', '', '3', '0');
INSERT INTO `t_goods` VALUES ('17', '智利泰瑞贵族珍藏佳美娜干红葡萄酒750mL', '120', '150', '非凡深邃的红宝石颜色，红色浆果、烟草、巧克力和纯净的果香演绎珍藏赤霞珠的盛世繁华、热情而高雅。', '100', null, '', '', '4', '0');
INSERT INTO `t_goods` VALUES ('18', '智利泰瑞贵族特级珍藏佳美娜干红葡萄酒750ml/瓶', '200', '268', '新鲜的红色浆果的芳香混合烟草和烤面包的味道,在不国橡木桶中陈酿酒韵。厚重的酒体结构和完整饱满的风格使单宁在樱桃、巧克力和烧烤的诱人组合里尽显绝代芳华。', '100', null, '', '', '4', '0');
INSERT INTO `t_goods` VALUES ('19', '智利泰瑞贵族窖藏CA2号干红葡萄酒750ml', '360', '480', '泰瑞贵族酒庄旨在通过CA系列葡萄酒，在智利各种风土条件下，探索佳美娜品种的不同风格和特点。', '100', null, '', '', '4', '0');
INSERT INTO `t_goods` VALUES ('20', '智利泰瑞贵族酒园精选梅乐干红葡萄酒750ml/瓶', '100', '120', '泰瑞贵族酒庄旨在通过CA系列葡萄酒，在智利各种风土条件下，探索佳美娜品种的不同风格和特点。', '100', null, '', '', '4', '0');
INSERT INTO `t_goods` VALUES ('21', '禾然龙眼蜂蜜500ml', '158', '160', '禾然龙眼蜂蜜。', '100', null, '', '', '4', '0');
INSERT INTO `t_goods` VALUES ('22', '测试商品1', '100', '110', '这是导入测试商品', '100', null, '', '', '4', '1');
INSERT INTO `t_goods` VALUES ('23', '测试商品2', '117', '139', '这是导入测试商品', '100', null, '', '', '4', '1');

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
INSERT INTO `t_goods_type` VALUES ('5', 'test1', 'test1分类', '1');
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
  `is_delete` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`),
  KEY `goodsId` (`goods_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_shoppingcar
-- ----------------------------
INSERT INTO `t_shoppingcar` VALUES ('1', '2', '1', '1', '0');
INSERT INTO `t_shoppingcar` VALUES ('2', '2', '13', '2', '0');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `password` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `name` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `money` float(255,0) DEFAULT NULL,
  `regist_time` datetime DEFAULT NULL,
  `recent_login_time` datetime DEFAULT NULL,
  `is_delete` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `type` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', '系统管理员', null, '2018-05-06 00:00:00', '2018-05-09 10:57:36', '0', '0');
INSERT INTO `t_user` VALUES ('2', '12312312312', '123', '张三', '1000', '2018-05-06 21:32:15', '2018-05-09 16:18:07', '0', '1');
INSERT INTO `t_user` VALUES ('3', '12312312313', '123', '李四', '1000', '2018-05-06 21:16:14', '2018-05-09 16:17:09', '0', '1');
INSERT INTO `t_user` VALUES ('4', '17826804000', '123', '王五', '1000', '2018-05-06 21:18:06', null, '0', '1');
INSERT INTO `t_user` VALUES ('5', '17474847811', '123', '赵六', '1000', '2018-05-06 21:46:25', null, '0', '1');
INSERT INTO `t_user` VALUES ('6', '12345678901', '123', '陈七', '1000', '2018-05-06 23:04:58', '2018-05-08 22:57:13', '0', '1');
INSERT INTO `t_user` VALUES ('7', '12312312311', '123', '12312312311', '1000', '2018-05-08 22:56:32', '2018-05-08 22:58:47', '0', '1');
INSERT INTO `t_user` VALUES ('8', '12312312322', '123', '', '1000', '2018-05-09 10:59:09', null, '0', '1');
