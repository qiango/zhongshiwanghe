
-- 本脚本已于 20160722 投产


-- 字典新增数据


INSERT INTO `zswh`.`dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('2', '筹备中', 'club', 'zh');
INSERT INTO `zswh`.`dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('1081', '俱乐部名不能为空', 'return_info', 'zh');
INSERT INTO `zswh`.`dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('1082', '城市编号不能为空', 'return_info', 'zh');
INSERT INTO `zswh`.`dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('club_min_member', '4', 'data_alias', 'zh');
INSERT INTO `zswh`.`dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('check_join_club_f', '尊敬的用户，很抱歉，您申请加入', 'data_alias', 'zh');
INSERT INTO `zswh`.`dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('join_club_f', '未通过。', 'data_alias', 'zh');
INSERT INTO `zswh`.`dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('join_club_reason', '原因为:', 'data_alias', 'zh');
INSERT INTO `zswh`.`dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('check_join_club_t', '尊敬的用户，您的俱乐部申请已通过，您现在是', 'data_alias', 'zh');
INSERT INTO `zswh`.`dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('join_club_t', '的一员了。', 'data_alias', 'zh');
INSERT INTO `zswh`.`dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('1083', '俱乐部名已存在，请重新输入', 'return_info', 'zh');
INSERT INTO `zswh`.`dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('club_effective_days', '7', 'data_alias', 'zh');
INSERT INTO `zswh`.`dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('break_club_message ', '尊敬的用户，很抱歉，您已退出俱乐部，原因：俱乐部筹备失败，已自动解散。', 'data_alias', 'zh');
INSERT INTO `zswh`.`dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('out_club_message', '，该用户已经退出俱乐部', 'data_alias', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES (X'666F6C6C6F77', X'E585B3E6B3A8E4BA86E682A8E38082', X'646174615F616C696173', X'7A68');






CREATE TABLE `app_regid` (
	  `user_id` int(11) NOT NULL,
	  `regid` varchar(255) NOT NULL DEFAULT '',
	  `status` varchar(2) NOT NULL DEFAULT '1' COMMENT '0禁用；1启用',
	  `app_type` varchar(2) DEFAULT NULL COMMENT '1表示IOS;2表示Android',
	  PRIMARY KEY (`user_id`,`regid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






-- club表新增字段

ALTER TABLE club ADD  column  club_level varchar(2) DEFAULT '1';

ALTER TABLE club ADD  column  club_rank varchar(2) DEFAULT '1';

ALTER TABLE club ADD  column  longitude decimal(13,10) DEFAULT NULL;

ALTER TABLE club ADD  column  latitude decimal(13,10) DEFAULT NULL;

ALTER TABLE club ADD  column  club_create_time timestamp DEFAULT CURRENT_TIMESTAMP;




 ALTER TABLE club DROP FOREIGN KEY FK_Relationship_18 ;

 update club  set id=3328  where  club_id=8 ;
 update club  set id=772  where  club_id=16 ;
 update club  set id=751  where  club_id=17 ;
 update club  set id=6921  where  club_id=18 ;
 update club  set id=3350  where  club_id=19 ;
 update club  set id=5669  where  club_id=20 ;
 update club  set id=740  where  club_id=21 ;
 update club  set id=8854  where  club_id=22 ;
 update club  set id=3453  where  club_id=23 ;
 update club  set id=6964  where  club_id=24 ;
 update club  set id=623  where  club_id=25 ;
 update club  set id=518  where  club_id=26 ;
 update club  set id=450  where  club_id=27 ;
 update club  set id=362  where  club_id=29 ;
 update club  set id=728  where  club_id=30 ;
 update club  set id=265  where  club_id=31 ;
 update club  set id=801  where  club_id=32 ;
 update club  set id=2408  where  club_id=33 ;
 update club  set id=6700  where  club_id=34 ;
 update club  set id=811  where  club_id=35 ;
 update club  set id=8760  where  club_id=36 ;
 update club  set id=10015  where  club_id=37 ;
 update club  set id=18  where  club_id=38 ;
 update club  set id=1  where  club_id=39 ;
 update club  set id=1  where  club_id=40 ;
 update club  set id=572  where  club_id=41 ;
 update club  set id=6760  where  club_id=42 ;
 update club  set id=424  where  club_id=43 ;
 update club  set id=652  where  club_id=44 ;
 update club  set id=276  where  club_id=45 ;
 update club  set id=122  where  club_id=46 ;
 update club  set id=228  where  club_id=47 ;
 update club  set id=385  where  club_id=48 ;
 update club  set id=772  where  club_id=49 ;
 update club  set id=1078  where  club_id=50 ;



