/*
 Navicat Premium Data Transfer

 Source Server         : mycat
 Source Server Type    : MySQL
 Source Server Version : 50629
 Source Host           : localhost:8066
 Source Schema         : bookstore

 Target Server Type    : MySQL
 Target Server Version : 50629
 File Encoding         : 65001

 Date: 06/01/2020 12:41:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `adminId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `adminPass` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `adminTel` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`adminId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for adminandconsumer
-- ----------------------------
DROP TABLE IF EXISTS `adminandconsumer`;
CREATE TABLE `adminandconsumer`  (
  `csuTel` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `adminId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `update` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`csuTel`, `adminId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for adminandshop
-- ----------------------------
DROP TABLE IF EXISTS `adminandshop`;
CREATE TABLE `adminandshop`  (
  `adminId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `shopId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `update` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`adminId`, `shopId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `bookId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `bookName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `bookISBN` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `bookInfor` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `bookPrice` float(10, 0) NULL DEFAULT NULL,
  `bookShopId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `bookStock` int(10) NULL DEFAULT NULL,
  `bookImage` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `bookdeleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`bookId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('20200102112622', '平凡的世界：全三册', '9787530216781', '这是一部现实主义小说，也是小说化的家族史。作家高度浓缩了中国西北农村的历史变迁过程，作品达到了思想性与艺术性的高度统一，特别是主人公面对困境艰苦奋斗的精神，对今天的大学生朋友仍有启迪。这是一部全景式地表现中国当代城乡社会生活的长篇小说，本书共三部。作者在近十年问广阔背景上，通过复杂的矛盾纠葛，刻划了社会各阶层众多普通人的形象。劳动与爱情，挫折与追求，痛苦与欢乐，日常生活与巨大社会冲突，纷繁地交织在一起，深刻地展示了普通人在大时代历史进程中所走过的艰难曲折的道路。', 82, '1000', 3, 'cover\\e47f4090-afb7-42e5-8f1c-4dc75a035f1a.jpg', 0);
INSERT INTO `book` VALUES ('20200102112839', '习近平新时代中国特色社会主义思想学习纲要', '9787514709223', '《纲要》以“八个明确”和“十四个坚持”为核心内容，分十九章全面、系统、深入阐释了习近平新时代中国特色社会主义思想的核心要义、精神实质、丰富内涵、实践要求，是广大党员、干部、群众深入学习领会习近平新时代中国特色社会主义思想的重要辅助读物。', 21, '1000', 3, 'cover\\7ba891f2-1e48-45f3-b09f-cdfdc681a710.jpg', 0);
INSERT INTO `book` VALUES ('20200102113050', '美国陷阱 任正非桌上的“神秘的书” ', '9787521702415', '2013年4月14日，美国纽约肯尼迪国际机场，法国阿尔斯通集团锅炉部全球负责人弗雷德里克?皮耶鲁齐，刚下飞机就被美国联邦调查局探员逮捕。', 65, '1000', 0, 'cover\\10b3f13c-5949-4d8b-8158-1be50385dc4c.jpg', 0);
INSERT INTO `book` VALUES ('20200104152104', '国家人文历史（2019年11月下第22期）', '9772095518197', '国家人文历史（2019年11月下第22期）', 16, '1000', 0, 'cover\\0005ddb6-6468-4d1e-96ae-df9d5e9b14fa.jpg', 0);
INSERT INTO `book` VALUES ('20200104152435', '王阳明全集心学知行合一', '9787545137811', '他在近代学术界中，极具伟大，军事上、政治上，具有很大的勋业。', 79, '1002', 0, 'cover\\c9728fd0-1850-42c1-9cd8-423abcc58534.jpg', 0);
INSERT INTO `book` VALUES ('20200104152533', '中国近代史', '9787511358226', '《中国近代史》以中国的近代化为线索，认为“中国人能否近代化将关系国家兴亡”，主要阐述了面对“数千年未有之大变局”，近代人所做的自强努力及其失败的原因，进而提出“近代化国防不但需要近代的交通、教育、经济，并又需要近代化的政治和国民，半新半旧是不中用的。”', 26, '1002', 0, 'cover\\a79f7980-2e7e-49a4-a0ab-f42a8914d88e.jpg', 0);
INSERT INTO `book` VALUES ('20200104121054', 'C++ Primer Plus(第6版)(中文版) ', '9787115279460', 'C++是在C语言基础上开发的一种集面向对象编程、通用编程和传统的过程化编程于一体的编程语言，是C语言的超集。《C++ Primer Plus（第6版）（中文版）》是根据2003年的ISO/ANSI C++标准编写的。通过大量短小精悍的程序详细而全面地阐述了C++的基本概念和技术。', 66, '1001', 0, 'cover\\97dee3ef-8ed6-4143-8c4c-6b867ac6687e.jpg', 0);
INSERT INTO `book` VALUES ('20200104121717', 'Python编程 从入门到实践', '9787115428028', '本书是一本针对所有层次的Python读者而作的Python入门书。', 60, '1001', 1, 'cover\\6aafddf0-6971-4eea-bb7a-3167c249c23c.jpg', 0);
INSERT INTO `book` VALUES ('20200104121849', '计算机网络：自顶向下方法(原书第7版)', '9787111599715', '本书是经典的计算机网络教材之一，采用了作者的自顶向下方法来讲授计算机网络的原理及其协议，自16年前第1版出版以来已经被数百所大学和学院选作教材，被译为14种语言。', 70, '1001', 0, 'cover\\ef52ecf8-2bbe-4359-9671-89b4e3749152.jpg', 0);
INSERT INTO `book` VALUES ('20200104122155', '零基础学Python（全彩版）', '9787569222258', '《零基础学 Python》是针对零基础编程学习者研发的 Python 入门教程。从初学者角度出发，通过通俗易懂的语言、流行有趣的实例，详细地介绍了使用 IDLE 及 Python 框架进行程序管理的知识和技术。', 73, '1001', 0, 'cover\\0928d1df-4282-49b9-9ef2-f56a227af537.jpg', 0);
INSERT INTO `book` VALUES ('20200104131029', '编程珠玑（第2版 修订版）', '9787115357618', '编程珠玑（第2版·修订版）》是计算机科学方面的经典名著。书的内容围绕程序设计人员面对的一系列实际问题展开。作者JonBentley以其独有的洞察力和创造力，引导读者理解这些问题并学会解决方法，而这些正是程序员实际编程生涯中至关重要的。', 29, '1001', 12, 'cover\\07a24689-c680-4a44-9d06-78ae33c48043.jpg', 0);
INSERT INTO `book` VALUES ('20200104131151', '算法导论（原书第3版）', '9787111407010', '《算法导论（原书第3版）/计算机科学丛书》将严谨性和全面性融为一体，深入讨论各类算法，并着力使这些算法的设计和分析能为各个层次的读者接受。', 94, '1001', 8, 'cover\\c38f676d-30a0-40ee-9cf6-4e99bc3bb089.jpg', 0);
INSERT INTO `book` VALUES ('20200104152628', '哈佛中国史 精装（礼盒套装共6册）', '9787508665450', '《哈佛中国史》六卷本丛书，由国际知名汉学家卜正民教授领衔主编，集结罗威廉、陆威仪和迪特?库恩三位汉学家，萃集半个世纪以来西方中国史研究成果，以全球史视野、多学科知识、倾十年之功写就，是继《剑桥中国史》之后又一套反映西方中国史研究成果和水准的多卷本中国通史。', 252, '1002', 3, 'cover\\4d945d58-88a5-4b1f-9c8d-498085f88b50.jpg', 0);
INSERT INTO `book` VALUES ('20200104152731', '黄仁宇作品系列：万历十五年', '9787108009821', '明万历十五年，即公元1587年，在中国历史上原本是极其普通的年份。作者以该年前后的史事件及生活在那个时代的人物为中心，抽丝剥茧，梳理了中国传统社会管理层面存在的种种问题，并在此基础上探索现代中国应当涉取的经验和教训。作者黄仁宇以其“大历史”观而闻名于世，《黄仁宇作品系列：万历十五年》中这一观念初露头角，“叙事不妨细致，但是结论却要看远不顾近”。', 16, '1002', 0, 'cover\\3698e31c-b48e-4ed6-9bf3-a58ad8f09534.jpg', 0);
INSERT INTO `book` VALUES ('20200104151632', 'Java从入门到精通（第5版）', '9787302517597', '《Java从入门到精通（第5版）》从初学者角度出发，通过通俗易懂的语言、丰富多彩的实例，详细介绍了使用Java语言进行程序开发需要掌握的知识。', 55, '1001', 0, 'cover\\a51ba225-1d8f-4bca-9b6a-626823b5e7b9.jpg', 0);
INSERT INTO `book` VALUES ('20200104151754', '零基础学C++（全彩版）', '9787569226607', '《零基础学C++》从初学者角度出发，通过通俗易懂的语言、流行有趣的实例，详细地介绍了使用C++ 语言进行程序开发需要掌握的知识和技术。', 73, '1001', 0, 'cover\\84a9fc3c-b642-48fe-8dcd-cf4d2583b7d4.jpg', 0);

-- ----------------------------
-- Table structure for bookpurchase
-- ----------------------------
DROP TABLE IF EXISTS `bookpurchase`;
CREATE TABLE `bookpurchase`  (
  `orderNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `bookId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `bookName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `csuTel` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `shopId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `paymentType` int(11) NOT NULL DEFAULT 0,
  `num` int(11) NOT NULL,
  `totalMoney` float NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0,
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`orderNumber`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bookpurchase
-- ----------------------------
INSERT INTO `bookpurchase` VALUES ('20200105213400', '20200102112622', '平凡的世界：全三册', '18801366009', '1000', 1, 1, 82, 5, '200105231649');
INSERT INTO `bookpurchase` VALUES ('20200106110058', '20200102113050', '美国陷阱 任正非桌上的“神秘的书” ', '18801366009', '1000', 1, 1, 65, 5, '200106110105');
INSERT INTO `bookpurchase` VALUES ('20200106102711', '20200104122155', '零基础学Python（全彩版）', '18801366009', '1001', 1, 1, 73, 5, '200106102722');
INSERT INTO `bookpurchase` VALUES ('20200106110655', '20200104131151', '算法导论（原书第3版）', '18801366009', '1001', 1, 1, 94, 5, '200106110701');
INSERT INTO `bookpurchase` VALUES ('20200106114157', '20200104131151', '算法导论（原书第3版）', '18801366009', '1001', 1, 1, 94, 5, '200106114203');
INSERT INTO `bookpurchase` VALUES ('20200106114425', '20200104131151', '算法导论（原书第3版）', '18801366009', '1001', 1, 1, 94, 5, '200106114431');
INSERT INTO `bookpurchase` VALUES ('20200105232300', '20200104152435', '王阳明全集心学知行合一', '18801366009', '1002', 1, 1, 79, 5, '200105232306');
INSERT INTO `bookpurchase` VALUES ('20200105233231', '20200104152435', '王阳明全集心学知行合一', '18801366009', '1002', 1, 1, 79, 5, '200105233428');
INSERT INTO `bookpurchase` VALUES ('20200106104148', '20200104152533', '中国近代史', '18801366009', '1002', 1, 1, 26, 5, '200106104154');

-- ----------------------------
-- Table structure for consumer
-- ----------------------------
DROP TABLE IF EXISTS `consumer`;
CREATE TABLE `consumer`  (
  `csuTel` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `csuPass` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `csuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `csuAdd` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `csuEmail` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `csuBalance` float(10, 0) NULL DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`csuTel`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of consumer
-- ----------------------------
INSERT INTO `consumer` VALUES ('13474711554', '123456', '刘森', '北京理工大学', 'csyuhao@126.com', 0, '20200104172312');
INSERT INTO `consumer` VALUES ('18801366009', '123456', '于灏', '北京理工大学', 'csyuhao@126.com', 123, '20190652235621');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `shopId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `shopPass` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `shopName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `shopAdd` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `shopTel` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`shopId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1001', '123456', '我的小书屋', '北京理工大学', '18801366009', '20190523092631');
INSERT INTO `shop` VALUES ('1000', '123456', '书舍', '北京理工大学', '13474711554', '20190102122112');
INSERT INTO `shop` VALUES ('1002', '123456', '雅舍', '北京理工大学', '18412896547', '20190628162454');

SET FOREIGN_KEY_CHECKS = 1;
