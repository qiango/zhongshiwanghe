INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('1089', '活动编号不能为空', 'return_info', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('1090', '用户编码不能为空', 'return_info', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('1091', '活动群号不能为空', 'return_info', 'zh');
INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`) VALUES ('1092', '报名人数已满', 'return_info', 'zh');
UPDATE dictionary set value ='报名人数已满' where code = 'full' and p_code = 'event_button';

ALTER TABLE event ADD  column  group_id varchar(100) DEFAULT '0';

DROP TABLE IF EXISTS `rest_user_info`;
CREATE TABLE `rest_user_info` (
  `user_id` int(11) DEFAULT NULL,
  `user_login_name` varchar(100) DEFAULT NULL,
  `rest_user_name` varchar(100) DEFAULT NULL,
  `rest_user_password` varchar(100) DEFAULT NULL,
  `is_delete` varchar(2) DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `event_group`;
CREATE TABLE `event_group` (
  `user_id` int(11) DEFAULT NULL,
  `event_id` int(11) DEFAULT NULL,
  `group_id` varchar(100) DEFAULT NULL,
  `group_level` varchar(2) DEFAULT '99' COMMENT '0群管理员，99普通群成员',
  `status` varchar(2) DEFAULT '1' COMMENT '1已加入'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;