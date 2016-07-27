
本脚本已于 2016 年 7 月 5 日 投产
-------------

CREATE TABLE `picture_upload` (
	  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	  `origin_name` varchar(40) COLLATE utf8_bin DEFAULT NULL,
	  `new_name` varchar(40) COLLATE utf8_bin DEFAULT NULL,
	  `create_time` timestamp NULL DEFAULT NULL,
	  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


ALTER TABLE club ADD  column  club_pic varchar(255) DEFAULT NULL;
ALTER TABLE news ADD  column  news_subtitle varchar(255) DEFAULT NULL;
ALTER TABLE news ADD  column  weights_order int(11) DEFAULT NULL;



CREATE TABLE `user_detail_bak` (
	  `club_id` int(11) DEFAULT NULL COMMENT '俱乐部编号',
	  `user_id` int(11) NOT NULL COMMENT '用户编号',
	  `jump_club_number` int(11) DEFAULT NULL COMMENT '选择俱乐部跳过次数',
	  `join_club_status` varchar(2) COLLATE utf8_bin DEFAULT '' COMMENT '加入俱乐部状态',
	  `user_level` varchar(3) COLLATE utf8_bin DEFAULT '99',
	  `create_time` timestamp NULL DEFAULT NULL,
	  `change_status_time` timestamp NULL DEFAULT NULL,
	  `club_refuse_id` int(11) DEFAULT NULL,
	  `create_time_bak` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
	  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='用户详细表';


INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`)
VALUES
  (X'31303737', X'E8B59BE4BA8BE8BF9BE8A18CE4B8ADE4B88DE883BDE98080E587BAE4BFB1E4B990E983A8', X'72657475726E5F696E666F', X'7A68'),
  (X'31303738', X'E682A8E697A0E69D83E99990E69BB4E694B9', X'72657475726E5F696E666F', X'7A68'),
  (X'31303739', X'E4BFB1E4B990E983A8E7AEA1E79086E59198E4B88DE883BDE98080E587BA', X'72657475726E5F696E666F', X'7A68');


