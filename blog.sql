/*
Navicat MySQL Data Transfer

Source Server         : MySql55
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-06-29 19:43:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` mediumtext NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `foreignkey_blog_user` (`user_id`),
  CONSTRAINT `foreignkey_blog_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', '11123', '11112。223', '1');
INSERT INTO `blog` VALUES ('2', '2221', '2222。112', '1');
INSERT INTO `blog` VALUES ('3', '333', '3333。44', '1');
INSERT INTO `blog` VALUES ('4', '444', '4444', '1');
INSERT INTO `blog` VALUES ('5', '555', '5555', '1');
INSERT INTO `blog` VALUES ('6', '666', '6666', '1');
INSERT INTO `blog` VALUES ('7', '777', '7777', '1');
INSERT INTO `blog` VALUES ('8', '888', '8888', '1');
INSERT INTO `blog` VALUES ('10', '123', '1234', '1');
INSERT INTO `blog` VALUES ('13', '321', '4321', '2');
INSERT INTO `blog` VALUES ('14', '我的第一篇博客', '胜多负少尴尬啥价格司法机关卡根据案件公共空间撒个娇肯德基噶就看见。i是法国窘境爱国。', '1');
INSERT INTO `blog` VALUES ('16', '我的第二篇博客', '写点啥呢', '3');
INSERT INTO `blog` VALUES ('17', '第三篇', '', '3');
INSERT INTO `blog` VALUES ('18', '第四篇', '发发发地方fsdfsdfs', '3');
INSERT INTO `blog` VALUES ('19', '第五篇', '阿凡达的方法的反对的手法首发sd 色地方', '3');
INSERT INTO `blog` VALUES ('20', '第六篇', '宋大哥是法国十多个人地方还是短发', '3');
INSERT INTO `blog` VALUES ('21', '第七篇', '<p>阿法狗撒</p><p>纠结啥</p><p>说得更加昂贵结果</p>', '3');
INSERT INTO `blog` VALUES ('22', '路由器OSPF动态路由配置', '<p><strong>实验目的</strong></p><p>l&nbsp; 掌握OSPF协议的配置方法：</p><p>l&nbsp; 掌握查看通过动态路由协议OSPF学习产生的路由；</p><p>l&nbsp; 熟悉广域网线缆的链接方式；</p><p><strong>实验背景</strong></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 假设校园网通过一台三层交换机连到校园网出口路由器上，路由器再和校园外的另一台路由器连接。现要做适当配置，实现校园网内部主机与校园网外部主机之间的相互通信。为了简化网管的管理维护工作，学校决定采用OSPF协议实现互通。</p><p><strong>技术原理</strong></p><p>l&nbsp; OSPF开放式最短路径优先协议，是目前网路中应用最广泛的路由协议之一。属于内部网管路由协议，能够适应各种规模的网络环境，是典型的链路状态协议。OSPF路由协议通过向全网扩散本设备的链路状态信息，使网络中每台设备最终同步一个具有全网链路状态的数据库，然后路由器采用SPF算法，以自己为根，计算到达其他网络的最短路径，最终形成全网路由信息。</p><p><strong>实验步骤</strong></p><p>l&nbsp; 新建packet tracer拓扑图</p><p>l&nbsp; （1）在本实验中的三层交换机上划分VLAN10和VLAN20，其中VLAN10用于连接校园网主机，VLAN20用于连接R1。</p><p>l&nbsp; （2）路由器之间通过V35电缆通过串口连接，DCE端连接在R1上，配置其时钟频率64000。</p><p>l&nbsp; （3）主机和交换机通过直连线，主机与路由器通过交叉线连接。</p><p>l&nbsp; （4）在S3560上配置OSPF路由协议。</p><p>l&nbsp; （5）在路由器R1、R2上配置OSPF路由协议。</p><p>l&nbsp; （6）将PC1、PC2主机默认网关设置为与直连网路设备接口IP地址。</p><p>l&nbsp; （7）验证PC1、PC2主机之间可以互相同信；</p><p><strong>实验设备</strong></p><p>PC 2台；Switch_3560 1台；Router-PT 2台；直连线；交叉线；DCE串口线</p><p><br></p><p>PC1</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; IP:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 192.168.1.2</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Submask:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 255.255.255.0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Gateway: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 192.168.1.1</p><p>PC2</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; IP:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 192.168.2.2</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Submask:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 255.255.255.0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Gateway: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 192.168.2.1</p><p><br></p><p>S3560</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; en</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; conft</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; hostname S3569</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vlan 10</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; exit</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vlan 20</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; interface fa0/10</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; switchportaccess vlan 10</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; exit</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; intfa 0/20</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; switchportaccess valn 20</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; exit</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; interfacevaln 10</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ipaddress 192.168.1.1 255.255.255.0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; noshutdown</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; exit</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; interfacevlan 20</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ipaddress 192.168.3.1 255.255.255.0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; noshutdown</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; end</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; showip route</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; conft</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; routerospf 1</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; network192.168.1.0 0.0.0.255 area 0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; network192.168.3.0 0.0.0.255 area 0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; end</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; showip route</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p>R1</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; en</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; conft</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; hostnameR1</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; interfacefa 0/0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; noshutdown</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ipaddress 192.168.3.2 255.255.255.0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; exit</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; interfaceserial 2/0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; noshutdown</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; clockrate 64000</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ipaddress 192.168.4.1 255.255.255.0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; end</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; showip route</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; conft</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; routerospf 1</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; network192.168.3.0 0.0.0.255 area 0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; network192.168.4.0 0.0.0.255 area 0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; end</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; showip route</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p>R2</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; en</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; conft</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; hostnameR2</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; interfacefa 0/0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; noshutdown</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ipaddress 192.168.2.1 255.255.255.0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; exit</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; interfaceserial 2/0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; noshutdown</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ipaddress 192.168.4.2 255.255.255.0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; end</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; showip route</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; conft</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; routerospf 1</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; network192.168.2.0 0.0.0.255 area 0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; network192.168.4.0 0.0.0.255 area 0</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; end</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; showip route</p><p><br data-cke-eol=\"1\"></p>', '3');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  `blog_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `foreignkey_comment_user` (`user_id`),
  KEY `foreignkey_comment_blog` (`blog_id`),
  CONSTRAINT `foreignkey_comment_blog` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreignkey_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '123', '1', '1');
INSERT INTO `comment` VALUES ('2', '321', '1', '1');
INSERT INTO `comment` VALUES ('3', '147', '1', '2');
INSERT INTO `comment` VALUES ('4', '258', '2', '1');
INSERT INTO `comment` VALUES ('5', '222', '1', '2');
INSERT INTO `comment` VALUES ('6', '112', '1', '1');
INSERT INTO `comment` VALUES ('7', '221', '1', '2');
INSERT INTO `comment` VALUES ('8', '333', '1', '1');
INSERT INTO `comment` VALUES ('9', '444', '1', '1');
INSERT INTO `comment` VALUES ('10', '感到十分赶得上', '1', '1');
INSERT INTO `comment` VALUES ('11', '飞洒发发士大夫', '1', '14');
INSERT INTO `comment` VALUES ('12', '人噶往往给人', '3', '14');
INSERT INTO `comment` VALUES ('13', '完全如何发挥RH', '3', '14');
INSERT INTO `comment` VALUES ('14', '第三个人有', '3', '14');
INSERT INTO `comment` VALUES ('15', '的风光过后', '3', '14');
INSERT INTO `comment` VALUES ('16', 'h\'s\'d\'f\'hEHF', '3', '14');
INSERT INTO `comment` VALUES ('17', '粉色公司', '1', '14');
INSERT INTO `comment` VALUES ('18', 'adD', '3', '21');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'test1', 'test1');
INSERT INTO `user` VALUES ('2', 'test2', 'test2');
INSERT INTO `user` VALUES ('3', 'dd', '301520');
