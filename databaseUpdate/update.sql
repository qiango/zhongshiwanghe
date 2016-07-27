

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
