

CREATE TABLE `rest_user_info` (
  `user_id` int(11) DEFAULT NULL,
  `user_login_name` varchar(100) DEFAULT NULL,
  `rest_user_name` varchar(100) DEFAULT NULL,
  `rest_user_password` varchar(100) DEFAULT NULL,
  `is_delete` varchar(2) DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE event ADD  column  group_id varchar(100) DEFAULT '0';
