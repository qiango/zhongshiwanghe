

-- 本脚本已于 20160708 投产
-- move old information data to news 


 insert into news (
   platform_id
  ,news_type
  ,news_title
  ,news_abstract
  ,news_status
  ,news_content
  ,create_time
  ,create_user
 )

 select 
   platform_id
  ,information_type
  ,information_title
  ,information_abstract
  ,'1' 
  ,information_content
  ,STR_TO_DATE(concat(create_date,create_time),'%Y%m%d%H%i%s')
  ,user_id
 from information 
 where is_delete = '0' 
 ;

 insert into news_multimedia (
   news_id
  ,media_url
  ,media_index
  )  
      select 
    c.news_id
   ,substr(a.information_image_url,8)
   ,0
  from information_image a 
  left join information b
  on a.information_id  = b.information_id 
  left join news c 
  on b.information_title = c.news_title
 ; 


INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`)
VALUES
	(X'6E6F74695F6A6F696E5F636C7562', X'E794B3E8AFB7E58AA0E585A5E4BFB1E4B990E983A8EFBC8CE8AFB7E5B0BDE5BFABE5A484E79086E38082', X'646174615F616C696173', X'7A68');


INSERT INTO `dictionary` (`code`, `value`, `p_code`, `language`)
VALUES
	(X'31303830', X'E682A8E5B7B2E58F96E6B688EFBC8CE6ACA2E8BF8EE4B88BE6ACA1E5868DE69DA5', X'72657475726E5F696E666F', X'7A68');



drop table user_detail_bak ;

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



update news 
set news_subtitle = news_title ;



 insert into news_range (
   news_id
  ,range_type
  ,circle_id
  )
 select 
    c.news_id
   ,0
   ,0
  from information_image a 
  left join information b
  on a.information_id  = b.information_id 
  left join news c 
  on b.information_title = c.news_title
  where c.news_id is not null ;


