-- MySQL dump 10.13  Distrib 5.6.31, for Linux (x86_64)
--
-- Host: localhost    Database: zswh
-- ------------------------------------------------------
-- Server version	5.6.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement` (
  `ad_id` int(11) NOT NULL AUTO_INCREMENT,
  `ad_name` varchar(100) DEFAULT NULL COMMENT '广告名称',
  `ad_location` int(11) DEFAULT NULL COMMENT '位置',
  `ad_type` char(1) DEFAULT NULL COMMENT '1:轮撥图, 2:栏位 , 3:单图',
  `ad_status` char(1) DEFAULT NULL COMMENT '0:delete,1:publish,2:block',
  `ad_start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `ad_end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`ad_id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
INSERT INTO `advertisement` VALUES (76,'101',101,'1','1','2016-06-16 16:00:00','2016-08-25 07:26:46');
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertisement_image`
--

DROP TABLE IF EXISTS `advertisement_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement_image` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT,
  `ad_id` int(11) DEFAULT NULL COMMENT '广告主键',
  `image_url` varchar(200) DEFAULT NULL COMMENT '图片url',
  `image_text` varchar(200) DEFAULT NULL COMMENT '图片文字',
  `image_index` int(11) DEFAULT NULL COMMENT '图片索引',
  `information_link` varchar(200) DEFAULT NULL COMMENT '图片链接',
  `image_height` int(11) DEFAULT NULL COMMENT '高度',
  `image_width` int(11) DEFAULT NULL COMMENT '宽度',
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `advertisement_pic_size`
--

DROP TABLE IF EXISTS `advertisement_pic_size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement_pic_size` (
  `height` int(11) DEFAULT NULL COMMENT '像素高度',
  `width` int(11) DEFAULT NULL COMMENT '像素宽度',
  `index` int(11) DEFAULT NULL COMMENT '索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `advertisement_preset_location`
--

DROP TABLE IF EXISTS `advertisement_preset_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement_preset_location` (
  `location_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '广告位置主键',
  `location_name` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '位置名称',
  `status` int(11) DEFAULT NULL COMMENT '0:delete, 1:in use',
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `advertisement_properties`
--

DROP TABLE IF EXISTS `advertisement_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement_properties` (
  `property_id` int(11) NOT NULL AUTO_INCREMENT,
  `ad_id` int(11) DEFAULT NULL,
  `property_code` varchar(100) DEFAULT NULL,
  `property_value` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `advertising`
--

DROP TABLE IF EXISTS `advertising`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertising` (
  `advertising_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '广告位编号',
  `advertising_fodder_category_id` int(11) DEFAULT NULL COMMENT '广告素材类型编号',
  `advertising_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '广告位名称',
  `advertising_type` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '广告位类型',
  `advertising_status` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '广告位状态',
  `advertising_fodder_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`advertising_id`),
  KEY `FK_Relationship_33` (`advertising_fodder_category_id`),
  CONSTRAINT `FK_Relationship_33` FOREIGN KEY (`advertising_fodder_category_id`) REFERENCES `advertising_fodder_category` (`advertising_fodder_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='广告位';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `advertising_fodder`
--

DROP TABLE IF EXISTS `advertising_fodder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertising_fodder` (
  `advertising_fodder_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '广告素材编号',
  `advertising_fodder_category_id` int(11) DEFAULT NULL COMMENT '广告素材类型编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `advertising_fodder_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '广告素材名',
  `advertising_fodder_start_date` varchar(14) COLLATE utf8_bin DEFAULT NULL COMMENT '广告素材开始时间',
  `advertising_fodder_end_date` varchar(14) COLLATE utf8_bin DEFAULT NULL COMMENT '广告素材结束时间',
  `advertising_fodder_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '广告素材指向',
  PRIMARY KEY (`advertising_fodder_id`),
  KEY `FK_Relationship_31` (`advertising_fodder_category_id`),
  KEY `FK_Relationship_32` (`user_id`),
  CONSTRAINT `FK_Relationship_31` FOREIGN KEY (`advertising_fodder_category_id`) REFERENCES `advertising_fodder_category` (`advertising_fodder_category_id`),
  CONSTRAINT `FK_Relationship_32` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='广告素材';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `advertising_fodder_category`
--

DROP TABLE IF EXISTS `advertising_fodder_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertising_fodder_category` (
  `advertising_fodder_category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '广告素材类型编号',
  `platform_id` int(11) DEFAULT NULL COMMENT '平台编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `advertising_fodder_size` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '广告素材尺寸',
  PRIMARY KEY (`advertising_fodder_category_id`),
  KEY `FK_Relationship_29` (`user_id`),
  KEY `FK_Relationship_30` (`platform_id`),
  CONSTRAINT `FK_Relationship_29` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`),
  CONSTRAINT `FK_Relationship_30` FOREIGN KEY (`platform_id`) REFERENCES `platform` (`platform_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='广告素材类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `advertising_fodder_image`
--

DROP TABLE IF EXISTS `advertising_fodder_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertising_fodder_image` (
  `advertising_fodder_image_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '广告素材图片编号',
  `advertising_fodder_image` varchar(255) DEFAULT NULL COMMENT '广告素材图片',
  `advertising_fodder_id` int(11) DEFAULT NULL COMMENT '广告素材编号',
  PRIMARY KEY (`advertising_fodder_image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='广告素材图片';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club` (
  `club_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '俱乐部编号',
  `user_id` int(11) DEFAULT NULL COMMENT '俱乐部申请人编号',
  `id` int(11) DEFAULT NULL COMMENT '编号',
  `language_id` int(11) DEFAULT NULL COMMENT '语言编号',
  `club_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '俱乐部名称',
  `club_applicant_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '俱乐部名称',
  `create_time` varchar(6) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `create_date` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '创建日期',
  `club_status` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '俱乐部状态',
  `club_description` text COLLATE utf8_bin COMMENT '俱乐部描述',
  `club_create_date` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '俱乐部成立时间',
  `club_qq` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '俱乐部qq群',
  `club_name_short` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `is_delete` char(1) COLLATE utf8_bin DEFAULT '0',
  `club_pic` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `longitude` decimal(13,10) DEFAULT NULL,
  `latitude` decimal(13,10) DEFAULT NULL,
  `club_level` varchar(2) COLLATE utf8_bin DEFAULT '1',
  `club_rank` varchar(2) COLLATE utf8_bin DEFAULT '1',
  PRIMARY KEY (`club_id`),
  KEY `FK_Relationship_18` (`id`),
  KEY `FK_Relationship_5` (`language_id`),
  KEY `FK_user_id` (`user_id`),
  CONSTRAINT `FK_Relationship_18` FOREIGN KEY (`id`) REFERENCES `world_city` (`id`),
  CONSTRAINT `FK_Relationship_5` FOREIGN KEY (`language_id`) REFERENCES `language` (`language_id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='俱乐部表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `club_applicant_log`
--

DROP TABLE IF EXISTS `club_applicant_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club_applicant_log` (
  `club_applicant_log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '俱乐部申请日志编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `club_id` int(11) DEFAULT NULL COMMENT '俱乐部编号',
  `from_club_status` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '原俱乐部状态',
  `to_club_status` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '新俱乐部状态',
  PRIMARY KEY (`club_applicant_log_id`),
  KEY `FK_Relationship_6` (`user_id`),
  KEY `FK_Relationship_7` (`club_id`),
  CONSTRAINT `FK_Relationship_6` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`),
  CONSTRAINT `FK_Relationship_7` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='俱乐部申请日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `club_competition_score`
--

DROP TABLE IF EXISTS `club_competition_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club_competition_score` (
  `club_id` int(11) NOT NULL COMMENT '俱乐部ID',
  `competition_id` int(11) NOT NULL COMMENT '赛事ID',
  `rank` int(11) DEFAULT NULL COMMENT '成绩排名',
  PRIMARY KEY (`club_id`,`competition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='俱乐部, 赛事的成绩排名';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `club_sports_camp`
--

DROP TABLE IF EXISTS `club_sports_camp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club_sports_camp` (
  `sports_camp_id` int(11) NOT NULL COMMENT '运动派编号',
  `club_id` int(11) NOT NULL COMMENT '俱乐部编号',
  PRIMARY KEY (`sports_camp_id`,`club_id`),
  KEY `FK_club_sports_camp2` (`club_id`),
  CONSTRAINT `FK_club_sports_camp` FOREIGN KEY (`sports_camp_id`) REFERENCES `sports_camp` (`sports_camp_id`),
  CONSTRAINT `FK_club_sports_camp2` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='俱乐部运动派关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `competition`
--

DROP TABLE IF EXISTS `competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition` (
  `competition_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '赛事编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `language_id` int(11) DEFAULT NULL COMMENT '语言编号',
  `platform_id` int(11) DEFAULT NULL COMMENT '平台编号',
  `competition_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '赛事名称',
  `competition_level` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '赛事级别',
  `competition_description` text COLLATE utf8_bin COMMENT '赛事描述',
  `competition_status` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '赛事状态',
  `create_time` varchar(6) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `create_date` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '创建日期',
  `competition_publicity_pictures` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '赛事宣传图片',
  `competition_primary_id` int(11) DEFAULT NULL COMMENT '赛事主编号',
  `registration_start_date` varchar(14) COLLATE utf8_bin DEFAULT NULL COMMENT '报名开始时间',
  `registration_end_date` varchar(14) COLLATE utf8_bin DEFAULT NULL COMMENT '报名结束时间',
  `competition_start_date` varchar(14) COLLATE utf8_bin DEFAULT NULL COMMENT '比赛开始时间',
  `competition_end_date` varchar(14) COLLATE utf8_bin DEFAULT NULL COMMENT '比赛结束时间',
  `id` int(11) DEFAULT NULL,
  `is_delete` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否删除',
  `compertition_live_status` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '赛事进行状态',
  `club_score_publish` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`competition_id`),
  KEY `FK_Relationship_11` (`user_id`),
  KEY `FK_Relationship_12` (`platform_id`),
  KEY `FK_Relationship_13` (`language_id`),
  CONSTRAINT `FK_Relationship_11` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`),
  CONSTRAINT `FK_Relationship_12` FOREIGN KEY (`platform_id`) REFERENCES `platform` (`platform_id`),
  CONSTRAINT `FK_Relationship_13` FOREIGN KEY (`language_id`) REFERENCES `language` (`language_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='赛事';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `competition_application`
--

DROP TABLE IF EXISTS `competition_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition_application` (
  `competition_application_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '赛事报名编号',
  `controls_id` int(11) DEFAULT NULL COMMENT '控件编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `competition_id` int(11) DEFAULT NULL COMMENT '赛事编号',
  `language_id` int(11) DEFAULT NULL COMMENT '语言编号',
  `create_time` varchar(6) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `create_date` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '创建日期',
  `controls_placeholder` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '控件占位符',
  `title_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '标题名称',
  `controls_data` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '控件数据',
  `controls_order` int(11) DEFAULT NULL COMMENT '控件顺序',
  PRIMARY KEY (`competition_application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=369 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='赛事报名';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `competition_application_data`
--

DROP TABLE IF EXISTS `competition_application_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition_application_data` (
  `competition_application_data_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '赛事报名数据',
  `controls_id` int(11) DEFAULT NULL COMMENT '控件编号',
  `competition_application_id` int(11) DEFAULT NULL COMMENT '赛事报名编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `competition_id` int(11) DEFAULT NULL COMMENT '赛事编号',
  `create_time` varchar(6) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `create_date` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '创建日期',
  `title_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '标题名称',
  `controls_order` int(11) DEFAULT NULL COMMENT '控件顺序',
  `user_value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户数据',
  `is_delete` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标志, 1:删除, 0:未删',
  PRIMARY KEY (`competition_application_data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1449 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='赛事报名数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `competition_club`
--

DROP TABLE IF EXISTS `competition_club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition_club` (
  `club_id` int(11) NOT NULL COMMENT '俱乐部编号',
  `competition_id` int(11) NOT NULL COMMENT '赛事编号',
  PRIMARY KEY (`club_id`,`competition_id`),
  KEY `FK_competition_club2` (`competition_id`),
  CONSTRAINT `FK_competition_club` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`),
  CONSTRAINT `FK_competition_club2` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`competition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='赛事俱乐部关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `controls`
--

DROP TABLE IF EXISTS `controls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `controls` (
  `controls_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '控件编号',
  `controls_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '控件名称',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`controls_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='控件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `device_info`
--

DROP TABLE IF EXISTS `device_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(50) DEFAULT NULL COMMENT '品牌',
  `model` varchar(50) DEFAULT NULL COMMENT '型号',
  `cpu` varchar(50) DEFAULT NULL COMMENT 'cpu info',
  `memory` double DEFAULT NULL COMMENT '內存大小(GB)',
  `os` varchar(50) DEFAULT NULL COMMENT '操作系统,  ios x.x  /  android x.x ',
  `ui_version` varchar(50) DEFAULT NULL COMMENT 'android , miui x.x / xxxuix.x',
  `serial_number` varchar(100) DEFAULT NULL,
  `carrier` varchar(50) DEFAULT NULL COMMENT '运营商',
  `wifi_mac_address` varchar(100) DEFAULT NULL COMMENT 'wifi MAC 地址',
  `screen_size` double DEFAULT NULL COMMENT '屏幕大小(英吋)',
  `storage_size` double DEFAULT NULL COMMENT '硬盘容量(GB)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `device_user`
--

DROP TABLE IF EXISTS `device_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_user` (
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `device_id` int(11) DEFAULT NULL COMMENT '设备ID , device_info -> id ',
  `save_tiime` timestamp NULL DEFAULT NULL COMMENT '保存时間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dictionary`
--

DROP TABLE IF EXISTS `dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dictionary` (
  `code` varchar(20) COLLATE utf8_bin NOT NULL,
  `value` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `p_code` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `language` varchar(7) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `follower`
--

DROP TABLE IF EXISTS `follower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `follower` (
  `user_id` int(11) NOT NULL,
  `follower_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`follower_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `information`
--

DROP TABLE IF EXISTS `information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `information` (
  `information_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资讯编号',
  `competition_id` int(11) DEFAULT NULL COMMENT '赛事编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `platform_id` int(11) DEFAULT NULL COMMENT '平台编号',
  `information_type` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '资讯类型',
  `information_title` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '资讯标题',
  `information_abstract` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '资讯摘要',
  `information_content` text COLLATE utf8_bin COMMENT '资讯内容',
  `information_status` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '资讯状态',
  `create_date` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '创建日期',
  `create_time` varchar(6) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `is_delete` varchar(1) COLLATE utf8_bin DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`information_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='资讯表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `information_comments`
--

DROP TABLE IF EXISTS `information_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `information_comments` (
  `information_comments_id` int(11) NOT NULL AUTO_INCREMENT,
  `information_id` int(11) DEFAULT NULL COMMENT '资讯编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `information_comments_content` text COLLATE utf8_bin,
  `is_delete` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`information_comments_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='资讯评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `information_image`
--

DROP TABLE IF EXISTS `information_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `information_image` (
  `information_image_id` int(11) NOT NULL AUTO_INCREMENT,
  `information_id` int(11) DEFAULT NULL COMMENT '资讯编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `information_image_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`information_image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='资讯图片';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `language_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '语言编号',
  `language_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '语言名称',
  `language_abbreviation` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '语言简称',
  PRIMARY KEY (`language_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='语言种类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_coupon`
--

DROP TABLE IF EXISTS `mall_coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_coupon` (
  `coupon_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '优惠券id',
  `coupon_name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '优惠券名称',
  `coupon_describe` text COLLATE utf8_bin COMMENT '优惠券描述',
  `use_scope` int(11) DEFAULT NULL COMMENT '0:all user use , 1:destribute, 2:club',
  `coupon_type` int(11) DEFAULT NULL COMMENT '1:order ; 2:goods',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `coupon_status` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '1:useable , 0:unusable',
  `discount_type` int(11) unsigned DEFAULT NULL COMMENT '1:直接调用公式 , 2:数量大于 min_amount 时调用公式 , 3: 数量在 min_amount 和 max_amount 之間时调用公式 \n',
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_coupon_properties`
--

DROP TABLE IF EXISTS `mall_coupon_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_coupon_properties` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `coupon_id` int(11) DEFAULT NULL COMMENT '优惠券id',
  `property_code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '优惠券属性编码',
  `property_value` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '优惠券属性值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_discount`
--

DROP TABLE IF EXISTS `mall_discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_discount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discount_type` int(11) DEFAULT NULL COMMENT ' 折扣类型, 1: 立减 XXX 元,  2:滿 param XXXX , 3:XX 折',
  `param` decimal(20,2) DEFAULT NULL COMMENT '参数',
  `method` varchar(30) DEFAULT NULL COMMENT '方程式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT=' 商品折扣表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_goods`
--

DROP TABLE IF EXISTS `mall_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_code` varchar(30) DEFAULT NULL COMMENT '商品编号',
  `category` int(11) DEFAULT NULL COMMENT '商品分类',
  `name` varchar(30) DEFAULT NULL COMMENT '商品名',
  `goods_describe` text COMMENT '商品描述',
  `status` char(1) DEFAULT NULL COMMENT '狀态 , 1:上架, 2:下架, 3:删除',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时間',
  `create_user` int(11) DEFAULT NULL COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='商品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_goods_bak`
--

DROP TABLE IF EXISTS `mall_goods_bak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_goods_bak` (
  `id` int(11) NOT NULL DEFAULT '0',
  `goods_code` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品编号',
  `category` int(11) DEFAULT NULL COMMENT '商品分类',
  `name` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品名',
  `goods_describe` text CHARACTER SET utf8 COMMENT '商品描述',
  `status` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '狀态 , 1:上架, 2:下架, 3:删除',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时間',
  `create_user` int(11) DEFAULT NULL COMMENT '创建者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_goods_category`
--

DROP TABLE IF EXISTS `mall_goods_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_goods_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '上級分类ID',
  `category_name` varchar(30) DEFAULT NULL COMMENT '分类名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='商品分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_goods_picture`
--

DROP TABLE IF EXISTS `mall_goods_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_goods_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `picture_url` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `show_index` int(11) DEFAULT NULL COMMENT '排序',
  `status` char(1) DEFAULT NULL COMMENT '狀态; 1:启用, 0:禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='商品图片';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_goods_price`
--

DROP TABLE IF EXISTS `mall_goods_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_goods_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `properties_id` int(11) DEFAULT NULL COMMENT '商品属性ID',
  `price` decimal(20,2) DEFAULT NULL COMMENT '价格',
  `discount_id` int(11) DEFAULT NULL COMMENT '折扣',
  `current_price` decimal(20,2) DEFAULT NULL COMMENT '现价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='商品价格';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_goods_properties`
--

DROP TABLE IF EXISTS `mall_goods_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_goods_properties` (
  `properties_id` int(11) NOT NULL COMMENT '商品属性ID',
  `goods_id` int(11) NOT NULL COMMENT '商品ID',
  `property_code` varchar(30) DEFAULT NULL COMMENT '属性名',
  `property_value` varchar(30) DEFAULT NULL COMMENT '属性值',
  `price_effect` int(11) DEFAULT NULL COMMENT '是否影响价格 ; 1:影响, 0:不影响'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品属性表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_order`
--

DROP TABLE IF EXISTS `mall_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(30) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时間',
  `status` char(1) DEFAULT NULL COMMENT '订单狀态;  1: 已下单, 2:已付款, 3:已取消 , 跟据app数据',
  `delivery` int(11) DEFAULT NULL COMMENT '物流ID, 票务无物流,置空',
  `price` decimal(20,2) DEFAULT NULL COMMENT '订单总价',
  `discount_id` int(11) DEFAULT NULL COMMENT '折扣ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=740 DEFAULT CHARSET=utf8 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_order_coupon_use`
--

DROP TABLE IF EXISTS `mall_order_coupon_use`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_order_coupon_use` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `coupon_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_order_goods`
--

DROP TABLE IF EXISTS `mall_order_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_order_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL COMMENT '订单ID',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `properties_id` int(11) DEFAULT NULL COMMENT '商品属性ID',
  `total_counts` int(11) DEFAULT NULL COMMENT '订单中本商品数量',
  `effect_counts` int(11) DEFAULT NULL COMMENT '有效数量',
  `single_price` decimal(20,2) DEFAULT NULL COMMENT '单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=715 DEFAULT CHARSET=utf8 COMMENT='订单-商品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_payment`
--

DROP TABLE IF EXISTS `mall_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_payment` (
  `order_id` int(11) DEFAULT NULL,
  `way` char(1) DEFAULT NULL,
  `status` char(1) DEFAULT NULL COMMENT 'dictionary ,  pay_status , 跟据支付平台数据, 1:下单 ,2:已付款 ,3:付款失败 ,4:已退款',
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `trace_code` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_payment_histroy`
--

DROP TABLE IF EXISTS `mall_payment_histroy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_payment_histroy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_code` varchar(50) NOT NULL,
  `return_code` int(11) DEFAULT NULL COMMENT '1:client success , 2:client fail , 4: server success , 8 :server fail ',
  `return_time` timestamp NULL DEFAULT NULL,
  `data_source` int(11) DEFAULT NULL COMMENT '1:client , 2:server',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8 COMMENT='支付狀态历史表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_return_history`
--

DROP TABLE IF EXISTS `mall_return_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_return_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `order_id` int(11) DEFAULT NULL COMMENT '订单ID',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `goods_count` int(11) DEFAULT NULL COMMENT '商品数量',
  `status` char(1) DEFAULT NULL COMMENT '退货狀态;  1:退款中 ,2:已退款, 3:退款失败',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时間',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时間',
  `pay_device` varchar(10) DEFAULT NULL COMMENT 'iOS  or  Android',
  `refund_no` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8 COMMENT='退货表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_return_preset_reason`
--

DROP TABLE IF EXISTS `mall_return_preset_reason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_return_preset_reason` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `preset_reason_content` varchar(300) DEFAULT NULL COMMENT '退货內容',
  `is_delete` char(1) DEFAULT '0' COMMENT '1:已删除, 0:未删',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_return_reason`
--

DROP TABLE IF EXISTS `mall_return_reason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_return_reason` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL COMMENT '订单ID',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `properties_id` int(11) DEFAULT NULL COMMENT '商品属性ID',
  `preset_reason_id` int(11) DEFAULT NULL COMMENT '预设原因ID',
  `return_reason` varchar(500) DEFAULT NULL COMMENT '退货原因,用户填写',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `menu_info`
--

DROP TABLE IF EXISTS `menu_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_info` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `menu_name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单地址',
  `menu_parent_id` int(11) DEFAULT NULL COMMENT '菜单父节点',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `platform_id` int(11) DEFAULT NULL COMMENT '平台 1:pc 2:app移动',
  `news_type` char(1) DEFAULT NULL COMMENT '0,图文 1,图片集 2,视频',
  `news_title` varchar(300) DEFAULT NULL COMMENT '标题',
  `news_abstract` varchar(500) DEFAULT NULL COMMENT '摘要',
  `news_status` char(1) DEFAULT NULL COMMENT '0:delete,1:publish,2:临时关闭',
  `news_content` text COMMENT '內容, 富文本',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '资讯创建时间',
  `create_user` int(11) DEFAULT NULL COMMENT 'create user id ',
  `news_subtitle` varchar(255) DEFAULT NULL,
  `weights_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=219 DEFAULT CHARSET=utf8 COMMENT='最新资讯';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `news_category`
--

DROP TABLE IF EXISTS `news_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(50) DEFAULT NULL COMMENT '频道名称',
  `category_describe` varchar(300) DEFAULT NULL COMMENT '频道描述',
  `parent_category_id` int(11) DEFAULT NULL COMMENT '父亲频道id',
  `category_image` varchar(200) DEFAULT NULL COMMENT '频道图片',
  `is_delete` int(11) DEFAULT NULL COMMENT '是否删除1：是2：否',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资讯分类, 频道';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `news_category_relation`
--

DROP TABLE IF EXISTS `news_category_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_category_relation` (
  `news_id` int(11) DEFAULT NULL COMMENT '资讯id',
  `category_id` int(11) DEFAULT NULL COMMENT '频道id',
  `is_delete` int(11) DEFAULT NULL COMMENT '是否删除 1：是 2：否'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `news_comment`
--

DROP TABLE IF EXISTS `news_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `news_id` int(11) DEFAULT NULL COMMENT '资讯id',
  `comment_content` text COMMENT '评论内容',
  `comment_status` char(1) DEFAULT NULL COMMENT '0:delete,1:public,2:block',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` int(11) DEFAULT NULL COMMENT '作者',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `news_multimedia`
--

DROP TABLE IF EXISTS `news_multimedia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_multimedia` (
  `media_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '媒体id',
  `news_id` int(11) DEFAULT NULL COMMENT '资讯id',
  `media_url` varchar(300) DEFAULT NULL COMMENT 'image_url or video_url',
  `media_information` text COMMENT '对每个文件的介绍, 图集中的每个图片',
  `media_index` int(11) DEFAULT NULL COMMENT '排序',
  `is_delete` int(11) DEFAULT '0' COMMENT '0:not delete , 1: delete',
  PRIMARY KEY (`media_id`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=utf8 COMMENT='资讯 , 图片, 视频';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `news_range`
--

DROP TABLE IF EXISTS `news_range`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_range` (
  `range_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资讯范围id',
  `news_id` int(11) DEFAULT NULL COMMENT '资讯id',
  `range_type` int(11) DEFAULT NULL COMMENT '0:public,1:club,2:competition',
  `circle_id` int(11) DEFAULT NULL COMMENT 'null,club_id, competition_id',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除 1：是2：否',
  PRIMARY KEY (`range_id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8 COMMENT='资讯传播范围';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `news_watch_history`
--

DROP TABLE IF EXISTS `news_watch_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_watch_history` (
  `news_id` int(11) DEFAULT NULL COMMENT '资讯id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `noti_from` int(11) DEFAULT NULL,
  `noti_to` int(11) DEFAULT NULL,
  `noti_category` char(1) DEFAULT NULL,
  `notification_body` text,
  `create_time` timestamp NULL DEFAULT NULL,
  `already_read` char(1) DEFAULT NULL,
  `is_delete` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1328 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification_user_state`
--

DROP TABLE IF EXISTS `notification_user_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification_user_state` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `category_code` char(1) NOT NULL COMMENT '消息分类ID',
  `noti_count` int(11) DEFAULT NULL COMMENT '消息数量',
  PRIMARY KEY (`user_id`,`category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification_user_state_bak`
--

DROP TABLE IF EXISTS `notification_user_state_bak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification_user_state_bak` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `category_code` char(1) CHARACTER SET utf8 NOT NULL COMMENT '消息分类ID',
  `noti_count` int(11) DEFAULT NULL COMMENT '消息数量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `picture_upload`
--

DROP TABLE IF EXISTS `picture_upload`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `picture_upload` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `origin_name` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `new_name` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `platform`
--

DROP TABLE IF EXISTS `platform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `platform` (
  `platform_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '平台编号',
  `platform_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '平台名称',
  PRIMARY KEY (`platform_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='平台表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_info`
--

DROP TABLE IF EXISTS `role_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_info` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `is_delete` char(1) COLLATE utf8_bin DEFAULT '0',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles_menus`
--

DROP TABLE IF EXISTS `roles_menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_menus` (
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `menu_id` int(11) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK_roles_menus2` (`menu_id`),
  CONSTRAINT `FK_roles_menus` FOREIGN KEY (`role_id`) REFERENCES `role_info` (`role_id`),
  CONSTRAINT `FK_roles_menus2` FOREIGN KEY (`menu_id`) REFERENCES `menu_info` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='角色菜单关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `session_attribute`
--

DROP TABLE IF EXISTS `session_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_attribute` (
  `id` int(11) DEFAULT NULL COMMENT 'session_time -> table_id',
  `key_name` varchar(20) DEFAULT NULL COMMENT '键名',
  `value_content` varchar(300) DEFAULT NULL COMMENT '数值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `session_log`
--

DROP TABLE IF EXISTS `session_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_log` (
  `id` int(11) DEFAULT NULL COMMENT 'session_time -> id',
  `function_name` varchar(100) DEFAULT NULL COMMENT '用户访問的功能名',
  `visit_time` timestamp NULL DEFAULT NULL COMMENT '访問时間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `session_time`
--

DROP TABLE IF EXISTS `session_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(300) NOT NULL COMMENT 'session_id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时間',
  `last_use_time` timestamp NULL DEFAULT NULL COMMENT '上次使用时間',
  `due_time` timestamp NULL DEFAULT NULL COMMENT '到期时間',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1495 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shipping_address`
--

DROP TABLE IF EXISTS `shipping_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping_address` (
  `shipping_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '配送地址ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID,  user_info > user_id',
  `city_code` varchar(20) DEFAULT NULL COMMENT '地点标记 ,  world_city > remark ',
  `detail_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `receiver_name` varchar(50) DEFAULT NULL COMMENT '收货人',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '收货人电话',
  `is_default` char(1) DEFAULT NULL COMMENT '是否是默认地址,  0: 否, 1: 是',
  `is_delete` char(1) DEFAULT '0' COMMENT '删除标记, 0: 未删除, 1: 己删除',
  PRIMARY KEY (`shipping_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='配送地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sports_camp`
--

DROP TABLE IF EXISTS `sports_camp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sports_camp` (
  `sports_camp_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '运动派编号',
  `language_id` int(11) DEFAULT NULL COMMENT '语言编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `sports_camp_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '运动派名称',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `is_delete` char(1) COLLATE utf8_bin DEFAULT '0',
  PRIMARY KEY (`sports_camp_id`),
  KEY `FK_Relationship_10` (`language_id`),
  KEY `FK_Relationship_9` (`user_id`),
  CONSTRAINT `FK_Relationship_10` FOREIGN KEY (`language_id`) REFERENCES `language` (`language_id`),
  CONSTRAINT `FK_Relationship_9` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='运动派表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `traffic`
--

DROP TABLE IF EXISTS `traffic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `traffic` (
  `order_id` varchar(50) NOT NULL,
  `level_id` varchar(10) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `traffic_status` varchar(10) DEFAULT NULL,
  `traffic_code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `upgrade_version`
--

DROP TABLE IF EXISTS `upgrade_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upgrade_version` (
  `iOS_current_version` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `android_current_version` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `iOS` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `Android` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `iOS_open` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `android_open` varchar(10) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_competition`
--

DROP TABLE IF EXISTS `user_competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_competition` (
  `competition_id` int(11) NOT NULL DEFAULT '0' COMMENT '赛事编号',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户编号',
  `user_competition_status` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '用户赛事状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `apply_or_refuse_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`,`competition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='用户赛事关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_competition_refuse_reason`
--

DROP TABLE IF EXISTS `user_competition_refuse_reason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_competition_refuse_reason` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `competition_id` int(11) DEFAULT NULL,
  `refuse_reason` varchar(200) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_detail`
--

DROP TABLE IF EXISTS `user_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_detail` (
  `club_id` int(11) DEFAULT NULL COMMENT '俱乐部编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `jump_club_number` int(11) DEFAULT NULL COMMENT '选择俱乐部跳过次数',
  `join_club_status` varchar(2) COLLATE utf8_bin DEFAULT '' COMMENT '加入俱乐部状态',
  `user_level` varchar(3) COLLATE utf8_bin DEFAULT '99',
  `create_time` timestamp NULL DEFAULT NULL,
  `change_status_time` timestamp NULL DEFAULT NULL,
  `club_refuse_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='用户详细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_detail_bak`
--

DROP TABLE IF EXISTS `user_detail_bak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_detail_bak` (
  `club_id` int(11) DEFAULT NULL COMMENT '俱乐部编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `jump_club_number` int(11) DEFAULT NULL COMMENT '选择俱乐部跳过次数',
  `join_club_status` varchar(2) COLLATE utf8_bin DEFAULT '' COMMENT '加入俱乐部状态',
  `user_level` varchar(3) COLLATE utf8_bin DEFAULT '99',
  `create_time` timestamp NULL DEFAULT NULL,
  `change_status_time` timestamp NULL DEFAULT NULL,
  `club_refuse_id` int(11) DEFAULT NULL,
  `create_time_bak` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='用户详细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_detail_refuse_club_reason`
--

DROP TABLE IF EXISTS `user_detail_refuse_club_reason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_detail_refuse_club_reason` (
  `club_reason_id` int(11) NOT NULL AUTO_INCREMENT,
  `reason_content` text,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`club_reason_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `platform_id` int(11) DEFAULT NULL COMMENT '平台编号',
  `user_real_name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '用户真实姓名',
  `user_login_name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '用户登录名',
  `user_password` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '登录密码',
  `phone` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '手机',
  `mail_address` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `user_status` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '用户状态',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `create_time` varchar(6) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `create_date` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '创建日期',
  `is_delete` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否删除',
  `nickname` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `gender` char(1) COLLATE utf8_bin DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `address` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `profile_picture` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `create_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `salt` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_Relationship_1` (`platform_id`),
  CONSTRAINT `FK_Relationship_1` FOREIGN KEY (`platform_id`) REFERENCES `platform` (`platform_id`)
) ENGINE=InnoDB AUTO_INCREMENT=804 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_online`
--

DROP TABLE IF EXISTS `user_online`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_online` (
  `online_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '在线编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `session_id` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'session_id',
  `online_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`online_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='用户在线';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_sports_camp`
--

DROP TABLE IF EXISTS `user_sports_camp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_sports_camp` (
  `sports_camp_id` int(11) NOT NULL COMMENT '运动派编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  PRIMARY KEY (`sports_camp_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='用户与运动派关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_users_roles2` (`role_id`),
  CONSTRAINT `FK_users_roles` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`),
  CONSTRAINT `FK_users_roles2` FOREIGN KEY (`role_id`) REFERENCES `role_info` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='用户角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `world_city`
--

DROP TABLE IF EXISTS `world_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `world_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父编号\r\n            ',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `name_en` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '英文名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3690 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='世界城市表';
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-14 11:28:36
