
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

ALTER TABLE club ADD  column  reminder_mark varchar(2) DEFAULT '0';



INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('event_button', 'button_content', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('finish', '已结束', 'event_button', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('club_id_null', '俱乐部编号为空', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('start_time_null', '活动开始时间为空', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('end_time_null', '活动结束时间为空', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('register_start_null', '活动报名开始时间为空', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('register_end_null', '活动报名结束时间为空', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('time_error', '输入时間有误', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('event_address_null', '活动地址为空', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('form_item_null', '报名信息为空', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('organizer_join_null', '组织者参加活动为空', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('event_name_null', '活动名为空', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('mi_push_header', 'http://zswh.hzbuvi.com', 'data_alias', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('view_guests_null', '参加的人可見为空', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('organizer_id_null', '组织者为空', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('abort', '中止', 'event_button', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('registered', '已报名', 'event_button', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('stop_register', '报名截止', 'event_button', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('full', '报名人数已滿', 'event_button', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('i_wanna_join', '我要参加', 'event_button', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('prepare', '预热中', 'event_button', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('under_review', '审核中', 'event_button', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('fail_review', '审核未通过', 'event_button', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('no_authority_review', '您无权限审核本活动', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('review_fail', '审核失败', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('not_own_club', '您不属于该俱乐部', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('register_fail', '报名失败', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('vote', '投票', 'root', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('vote_fail', '投票失败', 'vote', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('no_authority', '您无权限投票', 'vote', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('limit', 'true', 'vote', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('week_limit', '5', 'vote', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('day_limit', '2', 'vote', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('today_up_to_limit', '今日投票已达上限', 'vote', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('week_up_to_limit', '本周投票已达上限', 'vote', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('unregister_fail', '活动取消报名失败', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('review_event_true', '尊敬的用户，您申请的活动\"', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('event_true_message', '\",已审核通过了。', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('review_event_fail', '尊敬的用户，很抱歉，您申请的活动\"', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('event_fail_message', '\",未通过审核，原因：', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('picHead', '/pic.htmls?p=', 'data_alias', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('event_enum', 'event_enum', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('UNDER_REVIEW', '审核未通过', 'event_enum', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('NORMAL', '正常', 'event_enum', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('OVER', '中止', 'event_enum', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('UNDER_REVIEW', '审核中', 'event_enum', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('CLUB_MANAGER', '俱乐部管理员', 'event_enum', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('EVENT_ORGANIZER', '活动组织者', 'event_enum', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('EVENT_MEMBER', '活动参加者', 'event_enum', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('NOT_JOIN_CLUB', '未加入俱乐部', 'event_enum', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('CLUB_MEMBER', '俱乐部成员', 'event_enum', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('abort_event', '删除活动', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('abort_fail', '活动中止失败', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('1086', '活动中不能退出', 'return_info', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('unregister_success', '取消报名成功', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('abort_success', '活动中止成功', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('event_review_success', '审核操作成功', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('image_null', '封面图片不能为空', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('join_event', '尊敬的用户，', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('join_event_message', '已加入该活动：', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('unregister_event', '尊敬的用户，', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('unregister_event_m', '已退出该活动：', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('abort_event', '尊敬的用户，很抱歉，您参加的活动：', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('abort_event_message', ',已中止。原因：', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('create_event', '尊敬的用户，', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('create_event_message', '发起了新活动，请及时审核：', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('event_ongoing', '进行中', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('event_end', '已结束', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('event_start', '开始', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('delete_member', '对不起，您不再是', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('delete_member_m', '的一员了。原因：', 'event', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('1087', '您已不属于该俱乐部了', 'return_info', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('1088', '中止理由不能为空', 'return_info', 'zh');



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
DROP TABLE IF EXISTS `event`;
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
  `event_detail` text,
  `event_notice` text,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8;

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





DROP TABLE IF EXISTS `event_user_profile`;
CREATE TABLE `event_user_profile` (
  `event_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `item_code` varchar(100) NOT NULL,
  `item_value` varchar(300) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `is_delete` int(11) DEFAULT '0',
  PRIMARY KEY (`event_id`,`user_id`,`item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile` (
  `user_id` int(11) NOT NULL,
  `item_code` varchar(100) NOT NULL,
  `item_value` varchar(300) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `is_delete` int(11) DEFAULT '0',
  PRIMARY KEY (`user_id`,`item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `event_image`;
CREATE TABLE `event_image` (
  `id` int(11) NOT NULL,
  `image` varchar(100) DEFAULT NULL,
  `index` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `push_record`;
CREATE TABLE `push_record` (
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '当前日期',
  `type` varchar(2) DEFAULT NULL COMMENT '消息类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into user_profile (user_id,item_code,item_value)
select
 user_id
,'name' as item_code
 ,user_real_name as item_value
 from user_info
where user_real_name is not null;

insert into user_profile (user_id,item_code,item_value)
select
 user_id
,'mobile'
 ,phone
from user_info
where phone is not null;

insert into user_profile (user_id,item_code,item_value)
select
 user_id
,'nickname'
 ,nickname
from user_info
where nickname is not null;

insert into user_profile (user_id,item_code,item_value)
select
 user_id
,'email'
 ,mail_address
from user_info
where mail_address is not null ;
