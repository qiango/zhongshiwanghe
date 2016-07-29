
-- vote
CREATE TABLE `vote` (
  `vote_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '投票ID',
  `vote_name` varchar(100) DEFAULT NULL COMMENT '投票名',
  `image` varchar(50) DEFAULT NULL COMMENT '图片',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时間',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时間',
  `vote_status` int(11) DEFAULT NULL COMMENT '0:未开始,1:进行中,2:已结束',
  `vote_index` int(11) DEFAULT NULL,
  PRIMARY KEY (`vote_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `vote_item` (
  `item_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `vote_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `describe` varchar(200) DEFAULT NULL,
  `index` int(11) DEFAULT NULL,
  `votes` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `vote_limit` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `vote_id` int(11) DEFAULT NULL,
  `limit_type` varchar(5) DEFAULT NULL COMMENT 'H,D,W,M',
  `limit_vote` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `vote_range` (
  `vote_id` int(11) DEFAULT NULL COMMENT 'vote_id',
  `range_type` int(11) DEFAULT NULL COMMENT '0:所有人, 1:赛事参赛者, 2:俱乐部成员, 3:个人用户',
  `circle_id` int(11) DEFAULT NULL COMMENT '0,competition_id,club_id,user_id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `vote_result` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `vote_id` int(11) DEFAULT NULL,
  `create_time` int(11) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- event
CREATE TABLE `event` (
  `event_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `event_name` varchar(100) DEFAULT NULL,
  `club_id` int(11) DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL COMMENT '活动开始时間',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '活动结束时間',
  `register_start_time` timestamp NULL DEFAULT NULL COMMENT '开始报名时間',
  `register_end_time` timestamp NULL DEFAULT NULL COMMENT '结束报名时間',
  `organizer_id` int(11) DEFAULT NULL,
  `event_address` varchar(200) DEFAULT NULL COMMENT '活动地址',
  `min_people` int(11) DEFAULT '0' COMMENT '0:unlimit',
  `max_people` int(11) DEFAULT '0' COMMENT '0:unlimit',
  `fee` decimal(10,3) DEFAULT '0.000' COMMENT '0:unlimit',
  `image` varchar(100) DEFAULT NULL,
  `event_status` int(11) DEFAULT NULL COMMENT '0:审核中, 1:审核通过,2:审核失败, 3:终止',
  `view_guests` int(11) DEFAULT NULL COMMENT '发起活动 - 谁可以看「参加的人」;  0:public,1:part, 2:private',
  `form_item` varchar(200) DEFAULT NULL COMMENT 'item 以 逗号分隔',
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `event_form_item` (
  `item_code` varchar(100) DEFAULT NULL COMMENT '报名信息编号',
  `item_name` varchar(100) DEFAULT NULL COMMENT '报名信息',
  `is_default` int(11) DEFAULT '0' COMMENT '1:default, 0:not default ',
  `language` varchar(5) DEFAULT 'zh' COMMENT '语言',
  `index` int(11) DEFAULT NULL COMMENT '排序'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `event_reason` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `event_id` int(11) DEFAULT NULL,
  `reason_type` varchar(100) DEFAULT NULL COMMENT 'REVIEW_FAIL , TERMINAL',
  `reason` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `event_registration` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `event_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `my_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_profile` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `item_code` varchar(100) DEFAULT NULL,
  `item_value` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `event_form_item` (`item_code`, `item_name`, `is_default`, `language`, `index`)
VALUES
	('name', '姓名', 1, 'zh', 1),
	('nickname', '昵称', 1, 'zh', 2),
	('mobile', '手机', 1, 'zh', 3),
	('email', '邮箱', 0, 'zh', 4),
	('qq', 'QQ', 0, 'zh', 5),
	('wechat', '微信', 0, 'zh', 6),
	('company', '公司', 0, 'zh', 7),
	('address', '地址', 0, 'zh', 8),
	('position', '职位', 0, 'zh', 9);

