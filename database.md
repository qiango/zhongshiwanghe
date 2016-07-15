
### 首页广告
advertisement

字段|类型|默认值|中文名
---|---|---|--
ad_id| int(11)| NOT NULL AUTO_INCREMENT|自增主键
ad_name| varchar(100)| NULL| 广告名称
ad_location| int(11) |NULL| 位置
ad_type| char(1)| NULL| 广告类型 1:轮撥图 2:栏位  3:单图
ad_status| char(1)| NULL|狀态 0:删除1: 发布 2:撤消
ad_start_time| timestamp| NULL |开始时间
ad_end_time| timestamp |NULL| 结束时间


### 广告图片
advertisement_image

字段|类型|默认值|中文名
---|---|---|--
image_id| int(11) | NOT NULL AUTO_INCREMENT|自增主键
ad_id| int(11) | NULL | 广告主键
image_url| varchar(200) | NULL | 图片url
image_text| varchar(200) | NULL | 图片文字
image_index| int(11) | NULL | 图片索引
information_link| varchar(200) | NULL | 图片链接
image_height| int(11) | NULL | 高度
image_width| int(11) | NULL | 宽度


### 广告图片尺寸大小
advertisement_pic_size

字段|类型|默认值|中文名
---|---|---|--
height| int(11) | NULL | 像素高度
width| int(11) | NULL | 像素宽度
index| int(11) | NULL | 索引

### 广告位置
advertisement_preset_location

字段|类型|默认值|中文名
---|---|---|--
location_id| int(11) | NOT NULL AUTO_INCREMENT | 广告位置主键
location_name| varchar(11) COLLATE utf8_bin | NULL | 位置名称
status| int(11) | NULL | 位置狀态  0:未使用 1:使用中


### 广告属性
advertisement_properties

字段|类型|默认值|中文名
---|---|---|--
property_id| int(11) | NOT NULL AUTO_INCREMENT | 属性主键
ad_id| int(11) | NULL| 广告ID
property_code| varchar(100) | NULL| 属性編号
property_value| varchar(200) | NULL| 属性值



DROP TABLE IF EXISTS |advertising|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |advertising| (
  |advertising_id| int(11) NOT NULL AUTO_INCREMENT | 广告位编号
  |advertising_fodder_category_id| int(11) | NULL | 广告素材类型编号
  |advertising_name| varchar(255) COLLATE utf8_bin | NULL | 广告位名称
  |advertising_type| varchar(2) COLLATE utf8_bin | NULL | 广告位类型
  |advertising_status| varchar(2) COLLATE utf8_bin | NULL | 广告位状态
  |advertising_fodder_id| int(11) | NULL
  PRIMARY KEY (|advertising_id|)
  KEY |FK_Relationship_33| (|advertising_fodder_category_id|)
  CONSTRAINT |FK_Relationship_33| FOREIGN KEY (|advertising_fodder_category_id|) REFERENCES |advertising_fodder_category| (|advertising_fodder_category_id|)
) ENGINE=InnoDB AUTO_INCREMENT=2 | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT |=广告位;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS |advertising_fodder|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |advertising_fodder| (
  |advertising_fodder_id| int(11) NOT NULL AUTO_INCREMENT | 广告素材编号
  |advertising_fodder_category_id| int(11) | NULL | 广告素材类型编号
  |user_id| int(11) | NULL | 用户编号
  |advertising_fodder_name| varchar(255) COLLATE utf8_bin | NULL | 广告素材名
  |advertising_fodder_start_date| varchar(14) COLLATE utf8_bin | NULL | 广告素材开始时间
  |advertising_fodder_end_date| varchar(14) COLLATE utf8_bin | NULL | 广告素材结束时间
  |advertising_fodder_url| varchar(255) COLLATE utf8_bin | NULL | 广告素材指向
  PRIMARY KEY (|advertising_fodder_id|)
  KEY |FK_Relationship_31| (|advertising_fodder_category_id|)
  KEY |FK_Relationship_32| (|user_id|)
  CONSTRAINT |FK_Relationship_31| FOREIGN KEY (|advertising_fodder_category_id|) REFERENCES |advertising_fodder_category| (|advertising_fodder_category_id|)
  CONSTRAINT |FK_Relationship_32| FOREIGN KEY (|user_id|) REFERENCES |user_info| (|user_id|)
) ENGINE=InnoDB AUTO_INCREMENT=2 | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT |=广告素材;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS |advertising_fodder_category|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |advertising_fodder_category| (
  |advertising_fodder_category_id| int(11) NOT NULL AUTO_INCREMENT | 广告素材类型编号
  |platform_id| int(11) | NULL | 平台编号
  |user_id| int(11) | NULL | 用户编号
  |advertising_fodder_size| varchar(255) COLLATE utf8_bin | NULL | 广告素材尺寸
  PRIMARY KEY (|advertising_fodder_category_id|)
  KEY |FK_Relationship_29| (|user_id|)
  KEY |FK_Relationship_30| (|platform_id|)
  CONSTRAINT |FK_Relationship_29| FOREIGN KEY (|user_id|) REFERENCES |user_info| (|user_id|)
  CONSTRAINT |FK_Relationship_30| FOREIGN KEY (|platform_id|) REFERENCES |platform| (|platform_id|)
) ENGINE=InnoDB | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT |=广告素材类型;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS |advertising_fodder_image|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |advertising_fodder_image| (
  |advertising_fodder_image_id| int(11) NOT NULL AUTO_INCREMENT | 广告素材图片编号
  |advertising_fodder_image| varchar(255) | NULL | 广告素材图片
  |advertising_fodder_id| int(11) | NULL | 广告素材编号
  PRIMARY KEY (|advertising_fodder_image_id|)
) ENGINE=InnoDB AUTO_INCREMENT=2 | CHARSET=utf8 ROW_FORMAT=DYNAMIC |=广告素材图片;
/*!40101 SET character_set_client = @saved_cs_client */;



### 俱乐部
club

字段|类型|默认值|中文名
---|---|---|--
club_id| int(11)| NOT NULL AUTO_INCREMENT | 俱乐部编号
user_id| int(11) | NULL | 俱乐部申请人编号
id| int(11) | NULL | 编号
language_id| int(11) | NULL | 语言编号
club_name| varchar(255) COLLATE utf8_bin | NULL | 俱乐部名称
club_applicant_name| varchar(255) COLLATE utf8_bin | NULL | 俱乐部名称
create_time| varchar(6) COLLATE utf8_bin | NULL | 创建时间
create_date| varchar(10) COLLATE utf8_bin | NULL | 创建日期
club_status| varchar(2) COLLATE utf8_bin | NULL | 俱乐部状态
club_description| text COLLATE utf8_bin | 俱乐部描述
club_create_date| varchar(8) COLLATE utf8_bin | NULL | 俱乐部成立时间
club_qq| varchar(50) COLLATE utf8_bin | NULL | 俱乐部qq群
club_name_short| varchar(100) COLLATE utf8_bin | NULL | 俱乐部间称
is_delete| char(1) COLLATE utf8_bin | 0 | 是否删除 , 0: 未删除, 1:已删除
club_pic| varchar(255) COLLATE utf8_bin | NULL | 俱乐部头像
longitude| decimal(1310) | NULL | 俱乐部所在地经度
latitude| decimal(1310) | NULL | 俱乐部所在地纬度
club_level| varchar(2) COLLATE utf8_bin | 1 | 俱乐部級別
club_rank| varchar(2) COLLATE utf8_bin | 1 | 俱乐部等級, (官方 / 认证)


DROP TABLE IF EXISTS |club_applicant_log|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |club_applicant_log| (
  |club_applicant_log_id| int(11) NOT NULL AUTO_INCREMENT | 俱乐部申请日志编号
  |user_id| int(11) | NULL | 用户编号
  |club_id| int(11) | NULL | 俱乐部编号
  |from_club_status| varchar(2) COLLATE utf8_bin | NULL | 原俱乐部状态
  |to_club_status| varchar(2) COLLATE utf8_bin | NULL | 新俱乐部状态
  PRIMARY KEY (|club_applicant_log_id|)
  KEY |FK_Relationship_6| (|user_id|)
  KEY |FK_Relationship_7| (|club_id|)
  CONSTRAINT |FK_Relationship_6| FOREIGN KEY (|user_id|) REFERENCES |user_info| (|user_id|)
  CONSTRAINT |FK_Relationship_7| FOREIGN KEY (|club_id|) REFERENCES |club| (|club_id|)
) ENGINE=InnoDB | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT |=俱乐部申请日志;
/*!40101 SET character_set_client = @saved_cs_client */;


### 俱乐部的赛事成绩
club_competition_score

字段|类型|默认值|中文名
---|---|---|--
club_id| int(11) | NOT NULL | 俱乐部ID
competition_id| int(11) | NOT NULL | 赛事ID
rank| int(11) | NULL | 成绩排名


### 俱乐部的运动派
club_sports_camp

字段|类型|默认值|中文名
---|---|---|--
sports_camp_id| int(11) | NOT NULL | 运动派编号
club_id| int(11) | NOT NULL | 俱乐部编号


### 赛事
competition

字段|类型|默认值|中文名
---|---|---|--
competition_id| int(11)| NOT NULL AUTO_INCREMENT | 赛事编号
user_id| int(11) | NULL | 用户编号
language_id| int(11) | NULL | 语言编号
platform_id| int(11) | NULL | 平台编号
competition_name| varchar(255) COLLATE utf8_bin | NULL | 赛事名称
competition_level| varchar(2) COLLATE utf8_bin | NULL | 赛事级别
competition_description| text | NULL | 赛事描述
competition_status| varchar(2) COLLATE utf8_bin | NULL | 赛事状态
create_time| varchar(6) COLLATE utf8_bin | NULL | 创建时间
create_date| varchar(8) COLLATE utf8_bin | NULL | 创建日期
competition_publicity_pictures| varchar(255) COLLATE utf8_bin | NULL | 赛事宣传图片
competition_primary_id| int(11) | NULL | 赛事主编号
registration_start_date| varchar(14) COLLATE utf8_bin | NULL | 报名开始时间
registration_end_date| varchar(14) COLLATE utf8_bin | NULL | 报名结束时间
competition_start_date| varchar(14) COLLATE utf8_bin | NULL | 比赛开始时间
competition_end_date| varchar(14) COLLATE utf8_bin | NULL | 比赛结束时间
id| int(11) | NULL | 城市ID
is_delete| varchar(1) COLLATE utf8_bin | NULL | 是否删除 0:未删除 , 1:已删除
compertition_live_status| varchar(2) COLLATE utf8_bin | NULL | 赛事进行状态
club_score_publish| char(1) COLLATE utf8_bin | NULL | 赛事成绩是否发布 , 1:发布 , 0/NULL:未发布


### 赛事报名表单
competition_application

字段|类型|默认值|中文名
---|---|---|--
competition_application_id| int(11) | NOT NULL AUTO_INCREMENT | 赛事报名编号
controls_id| int(11) | NULL | 控件编号
user_id| int(11) | NULL | 用户编号
competition_id| int(11) | NULL | 赛事编号
language_id| int(11) | NULL | 语言编号
create_time| varchar(6) COLLATE utf8_bin | NULL | 创建时间
create_date| varchar(8) COLLATE utf8_bin | NULL | 创建日期
controls_placeholder| varchar(255) COLLATE utf8_bin | NULL | 控件占位符
title_name| varchar(255) COLLATE utf8_bin | NULL | 标题名称
controls_data| varchar(255) COLLATE utf8_bin | NULL | 控件数据
controls_order| int(11) | NULL | 控件顺序




### 赛事报名表数据
competition_application_data

字段|类型|默认值|中文名
---|---|---|--
competition_application_data_id| int(11)| NOT NULL AUTO_INCREMENT | 赛事报名数据
controls_id| int(11) | NULL | 控件编号
competition_application_id| int(11) | NULL | 赛事报名编号
user_id| int(11) | NULL | 用户编号
competition_id| int(11) | NULL | 赛事编号
create_time| varchar(6) COLLATE utf8_bin | NULL | 创建时间
create_date| varchar(8) COLLATE utf8_bin | NULL | 创建日期
title_name| varchar(255) COLLATE utf8_bin | NULL | 标题名称
controls_order| int(11) | NULL | 控件顺序
user_value| varchar(255) COLLATE utf8_bin | NULL | 用户数据
is_delete| char(1) COLLATE utf8_bin | 0 | 删除标志 1:删除 0:未删


### 赛事与俱乐部关系
competition_club

字段|类型|默认值|中文名
---|---|---|--
club_id| int(11) | NOT NULL | 俱乐部编号
competition_id| int(11) NOT NULL | 赛事编号



### 报名表单中的控件
controls

字段|类型|默认值|中文名
---|---|---|--
controls_id| int(11)| NOT NULL AUTO_INCREMENT | 控件编号
controls_name| varchar(50) COLLATE utf8_bin | NULL | 控件名称
remark| varchar(500) COLLATE utf8_bin | NULL | 备注


### 设备信息
device_info

字段|类型|默认值|中文名
---|---|---|--
id| int(11)| NOT NULL AUTO_INCREMENT|主键
brand| varchar(50) | NULL | 品牌
model| varchar(50) | NULL | 型号
cpu| varchar(50) | NULL | cpu info
memory| double | NULL | 內存大小(GB)
os| varchar(50) | NULL | 操作系统  ios x.x  /  android x.x
ui_version| varchar(50) | NULL | android  miui x.x / xxxuix.x
serial_number| varchar(100) | NULL
carrier| varchar(50) | NULL | 运营商
wifi_mac_address| varchar(100) | NULL | wifi MAC 地址
screen_size| double | NULL | 屏幕大小(英吋)
storage_size| double | NULL | 硬盘容量(GB)



### 设备用户关系
device_user

字段|类型|默认值|中文名
---|---|---|--
user_id| int(11) | NULL | 用户ID
device_id| int(11) | NULL | 设备ID  device_info -> id
save_tiime| timestamp NULL | NULL | 保存时間



### 字典表
dictionary

字段|类型|默认值|中文名
---|---|---|--
code| varchar(20) COLLATE utf8_bin | NOT NULL|编号
value| varchar(100) COLLATE utf8_bin | NULL|值
p_code| varchar(20) COLLATE utf8_bin | NULL|父级编号
language| varchar(7) COLLATE utf8_bin | NULL| 语言


### 用户粉丝
follower

字段|类型|默认值|中文名
---|---|---|--
user_id| int(11) |NOT NULL|用户ID
follower_id| int(11) | NOT NULL|用户粉丝的ID


### 优惠券
mall_coupon

字段|类型|默认值|中文名
---|---|---|--
coupon_id| int(11) |unsigned NOT NULL AUTO_INCREMENT | 优惠券id
coupon_name| varchar(200) COLLATE utf8_bin | NULL | 优惠券名称
coupon_describe| text COLLATE utf8_bin | 优惠券描述
use_scope| int(11) | NULL | 使用范围 0:所有用户可用 1:分配給用户可用 2:俱乐部成员可用
coupon_type| int(11) | NULL |优惠类型  1:对订单的优惠 ; 2:对商品的优惠
start_time| timestamp NULL | NULL | 开始时间
end_time| timestamp NULL | NULL | 结束时间
coupon_status| char(1) COLLATE utf8_bin | NULL |狀态 1:可用 0:不可用
discount_type| int(11) unsigned | NULL | 1:直接调用公式  2:数量大于 min_amount 时调用公式  3: 数量在 min_amount 和 max_amount 之間时调用公式 \n


### 优惠券
mall_coupon_properties

字段|类型|默认值|中文名
---|---|---|--
id| int(11) | NOT NULL AUTO_INCREMENT | ID
coupon_id| int(11) | NULL | 优惠券id
property_code| varchar(20) COLLATE utf8_bin | NULL | 优惠券属性编码
property_value| varchar(30) COLLATE utf8_bin | NULL | 优惠券属性值


### 打折
mall_discount

字段|类型|默认值|中文名
---|---|---|--
id| int(11) | NOT NULL AUTO_INCREMENT
discount_type| int(11) | NULL |  折扣类型 1: 立减 XXX 元  2:滿 param XXXX  3:XX 折
param| decimal(202) | NULL | 参数
method| varchar(30) | NULL | 方程式


### 商品
mall_goods

字段|类型|默认值|中文名
---|---|---|--
id| int(11)| NOT NULL AUTO_INCREMENT
goods_code| varchar(30) | NULL | 商品编号
category| int(11) | NULL | 商品分类
name| varchar(30) | NULL | 商品名
goods_describe| text | 商品描述
status| char(1) | NULL | 狀态  1:上架 2:下架 3:删除
create_time| timestamp NULL | NULL | 创建时間
create_user| int(11) | NULL | 创建者



### 商品
mall_goods_category

字段|类型|默认值|中文名
---|---|---|--
id| int(11) | NOT NULL AUTO_INCREMENT | 分类ID
parent_id| int(11) | NULL | 上級分类ID
category_name| varchar(30) | NULL | 分类名字



### 商品图片
mall_goods_picture

字段|类型|默认值|中文名
---|---|---|--
id| int(11)| NOT NULL AUTO_INCREMENT
goods_id| int(11) | NULL | 商品ID
picture_url| varchar(255) | NULL | 图片路径
show_index| int(11) | NULL | 排序
status| char(1) | NULL | 狀态; 1:启用 0:禁用


### 商品价格
mall_goods_price

字段|类型|默认值|中文名
---|---|---|--
id| int(11) |NOT NULL AUTO_INCREMENT
goods_id| int(11) | NULL | 商品ID
properties_id| int(11) | NULL | 商品属性ID
price| decimal(202) | NULL | 价格
discount_id| int(11) | NULL | 折扣
current_price| decimal(202) | NULL | 现价


### 商品属性
mall_goods_properties

字段|类型|默认值|中文名
---|---|---|--
properties_id| int(11) NOT NULL | 商品属性ID
goods_id| int(11) NOT NULL | 商品ID
property_code| varchar(30) | NULL | 属性名
property_value| varchar(30) | NULL | 属性值
price_effect| int(11) | NULL | 是否影响价格 ; 1:影响 0:不影响


### 订单
mall_order

字段|类型|默认值|中文名
---|---|---|--
id| int(11) | NOT NULL AUTO_INCREMENT | 主键
code| varchar(30) | NULL | 订单号
user_id| int(11) | NULL | 用户ID
create_time| timestamp NULL | NULL | 创建时間
status| char(1) | NULL | 订单狀态;  1: 已下单 2:已付款 3:已取消  跟据app数据
delivery| int(11) | NULL | 物流ID 票务无物流置空
price| decimal(202) | NULL | 订单总价
discount_id| int(11) | NULL | 折扣ID


### 订单中使用的优惠券
mall_order_coupon_use

字段|类型|默认值|中文名
---|---|---|--
id| int(11) | NOT NULL AUTO_INCREMENT | ID
order_id| int(11) | NULL | 订单ID
coupon_id| int(11) | NULL | 优惠券ID


### 订单中的商品
mall_order_goods

字段|类型|默认值|中文名
---|---|---|--
id| int(11)| NOT NULL AUTO_INCREMENT
order_id| int(11) | NULL | 订单ID
goods_id| int(11) | NULL | 商品ID
properties_id| int(11) | NULL | 商品属性ID
total_counts| int(11) | NULL | 订单中本商品数量
effect_counts| int(11) | NULL | 有效数量
single_price| decimal(202) | NULL | 单价


### 订单支付方式
mall_payment

字段|类型|默认值|中文名
---|---|---|--
order_id| int(11) | NULL|订单ID
way| char(1) | NULL|支付方式 1: 支付宝 , 2: 微信
status| char(1) | NULL | 跟据支付平台数据 1:下单 2:已付款 3:付款失败 4:已退款
start_time| timestamp NULL | NULL|开始时间
end_time| timestamp NULL | NULL|结束时间
trace_code| varchar(50) | NULL|支付平台编号


### 订单支付方式
mall_payment_histroy

字段|类型|默认值|中文名
---|---|---|--
id| int(11)| NOT NULL AUTO_INCREMENT
order_code| varchar(50)| NOT NULL |订单编号
return_code| int(11) | NULL | 返回值 1: 客户端成功  2: 客户端失败  4: 服务端成功  8: 服务端失败
return_time| timestamp | NULL | NULL |  时间
data_source| int(11) | NULL | 数据來源  1: 客户端 2: 服务端


### 订单支付方式
mall_return_history

字段|类型|默认值|中文名
---|---|---|--
id| int(11) | NOT NULL AUTO_INCREMENT
user_id| int(11) | NULL | 用户ID
order_id| int(11) | NULL | 订单ID
goods_id| int(11) | NULL | 商品ID
goods_count| int(11) | NULL | 商品数量
status| char(1) | NULL | 退货狀态;  1:退款中 2:已退款 3:退款失败
start_time| timestamp NULL | NULL | 开始时間
end_time| timestamp NULL | NULL | 结束时間
pay_device| varchar(10) | NULL | iOS  or  Android
refund_no| varchar(30) | NULL | 退款编号



### 退货预设原因
mall_return_preset_reason

字段|类型|默认值|中文名
---|---|---|--
id| int(11)| NOT NULL AUTO_INCREMENT
preset_reason_content| varchar(300) | NULL | 退货原因
is_delete| char(1) | 0 | 1:已删除 0:未删


### 退货原因
mall_return_reason

字段|类型|默认值|中文名
---|---|---|--
id| int(11)| NOT NULL AUTO_INCREMENT
order_id| int(11) | NULL | 订单ID
goods_id| int(11) | NULL | 商品ID
properties_id| int(11) | NULL | 商品属性ID
preset_reason_id| int(11) | NULL | 预设原因ID
return_reason| varchar(500) | NULL | 退货原因用户填写


### 退货原因
menu_info

字段|类型|默认值|中文名
---|---|---|--
menu_id| int(11)| NOT NULL AUTO_INCREMENT | 菜单编号
menu_name| varchar(200) COLLATE utf8_bin | NULL | 菜单名称
menu_url| varchar(500) COLLATE utf8_bin | NULL | 菜单地址
menu_parent_id| int(11) | NULL | 菜单父节点



### 资讯
news

字段|类型|默认值|中文名
---|---|---|--
news_id| int(11)| NOT NULL AUTO_INCREMENT
platform_id| int(11) | NULL | 平台 1:pc 2:app移动
news_type| char(1) | NULL | 0图文 1图片集 2视频
news_title| varchar(300) | NULL | 标题
news_abstract| varchar(500) | NULL | 摘要
news_status| char(1) | NULL |狀态 0:删除 1: 发布 2:临时关闭
news_content| text | 內容 富文本
create_time| timestamp NULL | CURRENT_TIMESTAMP | 资讯创建时间
create_user| int(11) | NULL | 创建者ID
news_subtitle| varchar(255) | NULL | 副标题
weights_order| int(11) | NULL | 权重


### 资讯分类
news_category

字段|类型|默认值|中文名
---|---|---|--
category_id| int(11) | NOT NULL
category_name| varchar(50) | NULL | 频道名称
category_describe| varchar(300) | NULL | 频道描述
parent_category_id| int(11) | NULL | 父亲频道id
category_image| varchar(200) | NULL | 频道图片
is_delete| int(11) | NULL | 是否删除1：是2：否


### 资讯分类记录
news_category_relation

字段|类型|默认值|中文名
---|---|---|--
news_id| int(11) | NULL | 资讯id
category_id| int(11) | NULL | 频道id
is_delete| int(11) | NULL | 是否删除 1：是 2：否


### 资讯评论
news_comment

字段|类型|默认值|中文名
---|---|---|--
comment_id| int(11) NOT NULL AUTO_INCREMENT | 评论id
news_id| int(11) | NULL | 资讯id
comment_content| text | 评论内容
comment_status| char(1) | NULL |狀态 0:删除 1: 发布 2:临时关闭
create_time| timestamp NULL | NULL | 创建时间
create_user| int(11) | NULL | 作者


### 资讯图片或视频
news_multimedia

字段|类型|默认值|中文名
---|---|---|--
media_id| int(11) | NOT NULL AUTO_INCREMENT | 媒体id
news_id| int(11) | NULL | 资讯id
media_url| varchar(300) | NULL | image_url
media_information| text | 对每个文件的介绍 图集中的每个图片 , 视频 fileid
media_index| int(11) | NULL | 排序
is_delete| int(11) | 0 |狀态 0: 未删除  1: 删除


### 资讯传播范围
news_range

字段|类型|默认值|中文名
---|---|---|--
range_id| int(11)| NOT NULL AUTO_INCREMENT | 资讯范围id
news_id| int(11) | NULL | 资讯id
range_type| int(11) | NULL |范围类型 0: 公共 1:俱乐部 2:赛事
circle_id| int(11) | NULL | null,club_id,competition_id
is_delete| int(11) | 0 | 是否删除 1：是2：否


### 资讯观看次数
news_watch_history

字段|类型|默认值|中文名
---|---|---|--
news_id| int(11) | NULL | 资讯id
user_id| int(11) | NULL | 用户id
create_time| timestamp NULL | CURRENT_TIMESTAMP | 创建时间


### 消息
notification

字段|类型|默认值|中文名
---|---|---|--
id| int(11) |NOT NULL AUTO_INCREMENT|ID
noti_from| int(11) | NULL| 发送者ID
noti_to| int(11) | NULL| 接收者 ID
noti_category| char(1) | NULL| 消息分类
notification_body| text| NULL| 消息內容
create_time| timestamp NULL | NULL| 创建时间
already_read| char(1) | NULL| 是否已读  1:已读,  null:未读
is_delete| char(1) | NULL| 是否删除  1:已删 , 0:未删


### 用户消息数量
notification_user_state

字段|类型|默认值|中文名
---|---|---|--
user_id| int(11)| NOT NULL | 用户ID
category_code| char(1) NOT NULL | 消息分类ID
noti_count| int(11) | NULL | 消息数量


### 上传图片
picture_upload

字段|类型|默认值|中文名
---|---|---|--
id| int(11) unsigned NOT NULL AUTO_INCREMENT
origin_name| varchar(40) COLLATE utf8_bin | NULL
new_name| varchar(40) COLLATE utf8_bin | NULL
create_time| timestamp NULL | NULL




DROP TABLE IF EXISTS |platform|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |platform| (
  |platform_id| int(11) NOT NULL AUTO_INCREMENT | 平台编号
  |platform_name| varchar(50) COLLATE utf8_bin | NULL | 平台名称
  PRIMARY KEY (|platform_id|)
) ENGINE=InnoDB AUTO_INCREMENT=3 | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT |=平台表;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |role_info|
--

DROP TABLE IF EXISTS |role_info|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |role_info| (
  |role_id| int(11) NOT NULL AUTO_INCREMENT | 角色编号
  |role_name| varchar(100) COLLATE utf8_bin | NULL | 角色名称
  |remark| varchar(500) COLLATE utf8_bin | NULL | 备注
  |is_delete| char(1) COLLATE utf8_bin | 0
  PRIMARY KEY (|role_id|)
) ENGINE=InnoDB AUTO_INCREMENT=11 | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT |=角色表;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |roles_menus|
--

DROP TABLE IF EXISTS |roles_menus|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |roles_menus| (
  |role_id| int(11) NOT NULL | 角色编号
  |menu_id| int(11) NOT NULL | 菜单编号
  PRIMARY KEY (|role_id||menu_id|)
  KEY |FK_roles_menus2| (|menu_id|)
  CONSTRAINT |FK_roles_menus| FOREIGN KEY (|role_id|) REFERENCES |role_info| (|role_id|)
  CONSTRAINT |FK_roles_menus2| FOREIGN KEY (|menu_id|) REFERENCES |menu_info| (|menu_id|)
) ENGINE=InnoDB | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT |=角色菜单关系表;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |session_attribute|
--

DROP TABLE IF EXISTS |session_attribute|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |session_attribute| (
  |id| int(11) | NULL | session_time -> table_id
  |key_name| varchar(20) | NULL | 键名
  |value_content| varchar(300) | NULL | 数值
) ENGINE=InnoDB | CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |session_log|
--

DROP TABLE IF EXISTS |session_log|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |session_log| (
  |id| int(11) | NULL | session_time -> id
  |function_name| varchar(100) | NULL | 用户访問的功能名
  |visit_time| timestamp NULL | NULL | 访問时間
) ENGINE=InnoDB | CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |session_time|
--

DROP TABLE IF EXISTS |session_time|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |session_time| (
  |id| int(11) NOT NULL AUTO_INCREMENT
  |session_id| varchar(300) NOT NULL | session_id
  |create_time| timestamp NULL | NULL | 创建时間
  |last_use_time| timestamp NULL | NULL | 上次使用时間
  |due_time| timestamp NULL | NULL | 到期时間
  PRIMARY KEY (|id|)
) ENGINE=InnoDB AUTO_INCREMENT=1495 | CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |shipping_address|
--

DROP TABLE IF EXISTS |shipping_address|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |shipping_address| (
  |shipping_id| int(11) NOT NULL AUTO_INCREMENT | 配送地址ID
  |user_id| int(11) | NULL | 用户ID  user_info > user_id
  |city_code| varchar(20) | NULL | 地点标记   world_city > remark
  |detail_address| varchar(200) | NULL | 详细地址
  |receiver_name| varchar(50) | NULL | 收货人
  |receiver_phone| varchar(20) | NULL | 收货人电话
  |is_default| char(1) | NULL | 是否是默认地址  0: 否 1: 是
  |is_delete| char(1) | 0 | 删除标记 0: 未删除 1: 己删除
  PRIMARY KEY (|shipping_id|)
) ENGINE=InnoDB AUTO_INCREMENT=94 | CHARSET=utf8 ROW_FORMAT=DYNAMIC |=配送地址;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |sports_camp|
--

DROP TABLE IF EXISTS |sports_camp|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |sports_camp| (
  |sports_camp_id| int(11) NOT NULL AUTO_INCREMENT | 运动派编号
  |language_id| int(11) | NULL | 语言编号
  |user_id| int(11) | NULL | 用户编号
  |sports_camp_name| varchar(255) COLLATE utf8_bin | NULL | 运动派名称
  |remark| varchar(500) COLLATE utf8_bin | NULL | 备注
  |is_delete| char(1) COLLATE utf8_bin | 0
  PRIMARY KEY (|sports_camp_id|)
  KEY |FK_Relationship_10| (|language_id|)
  KEY |FK_Relationship_9| (|user_id|)
  CONSTRAINT |FK_Relationship_10| FOREIGN KEY (|language_id|) REFERENCES |language| (|language_id|)
  CONSTRAINT |FK_Relationship_9| FOREIGN KEY (|user_id|) REFERENCES |user_info| (|user_id|)
) ENGINE=InnoDB AUTO_INCREMENT=12 | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT |=运动派表;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |traffic|
--

DROP TABLE IF EXISTS |traffic|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |traffic| (
  |order_id| varchar(50) NOT NULL
  |level_id| varchar(10) | NULL
  |phone| varchar(50) | NULL
  |create_time| datetime | NULL
  |traffic_status| varchar(10) | NULL
  |traffic_code| varchar(10) | NULL
  PRIMARY KEY (|order_id|)
) ENGINE=InnoDB | CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |upgrade_version|
--

DROP TABLE IF EXISTS |upgrade_version|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |upgrade_version| (
  |iOS_current_version| varchar(30) COLLATE utf8_bin | NULL
  |android_current_version| varchar(30) COLLATE utf8_bin | NULL
  |iOS| varchar(10) COLLATE utf8_bin | NULL
  |Android| varchar(10) COLLATE utf8_bin | NULL
  |iOS_open| varchar(10) COLLATE utf8_bin | NULL
  |android_open| varchar(10) COLLATE utf8_bin | NULL
) ENGINE=InnoDB | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |user_competition|
--

DROP TABLE IF EXISTS |user_competition|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |user_competition| (
  |competition_id| int(11) NOT NULL | 0 | 赛事编号
  |user_id| int(11) NOT NULL | 0 | 用户编号
  |user_competition_status| varchar(2) COLLATE utf8_bin | NULL | 用户赛事状态
  |create_time| timestamp NOT NULL | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  |apply_or_refuse_time| timestamp NULL | NULL
  PRIMARY KEY (|user_id||competition_id|)
) ENGINE=InnoDB | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT |=用户赛事关系;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |user_competition_refuse_reason|
--

DROP TABLE IF EXISTS |user_competition_refuse_reason|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |user_competition_refuse_reason| (
  |id| int(11) NOT NULL AUTO_INCREMENT
  |user_id| int(11) | NULL
  |competition_id| int(11) | NULL
  |refuse_reason| varchar(200) | NULL
  |create_time| timestamp NULL | CURRENT_TIMESTAMP
  PRIMARY KEY (|id|)
) ENGINE=InnoDB AUTO_INCREMENT=27 | CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |user_detail|
--

DROP TABLE IF EXISTS |user_detail|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |user_detail| (
  |club_id| int(11) | NULL | 俱乐部编号
  |user_id| int(11) NOT NULL | 用户编号
  |jump_club_number| int(11) | NULL | 选择俱乐部跳过次数
  |join_club_status| varchar(2) COLLATE utf8_bin |  | 加入俱乐部状态
  |user_level| varchar(3) COLLATE utf8_bin | 99
  |create_time| timestamp NULL | NULL
  |change_status_time| timestamp NULL | NULL
  |club_refuse_id| int(11) | NULL
  PRIMARY KEY (|user_id|)
) ENGINE=InnoDB | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT |=用户详细表;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |user_detail_bak|
--

DROP TABLE IF EXISTS |user_detail_bak|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |user_detail_bak| (
  |club_id| int(11) | NULL | 俱乐部编号
  |user_id| int(11) NOT NULL | 用户编号
  |jump_club_number| int(11) | NULL | 选择俱乐部跳过次数
  |join_club_status| varchar(2) COLLATE utf8_bin |  | 加入俱乐部状态
  |user_level| varchar(3) COLLATE utf8_bin | 99
  |create_time| timestamp NULL | NULL
  |change_status_time| timestamp NULL | NULL
  |club_refuse_id| int(11) | NULL
  |create_time_bak| timestamp NOT NULL | 0000-00-00 00:00:00
) ENGINE=InnoDB | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC |=用户详细表;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |user_detail_refuse_club_reason|
--

DROP TABLE IF EXISTS |user_detail_refuse_club_reason|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |user_detail_refuse_club_reason| (
  |club_reason_id| int(11) NOT NULL AUTO_INCREMENT
  |reason_content| text
  |create_time| timestamp NULL | CURRENT_TIMESTAMP
  PRIMARY KEY (|club_reason_id|)
) ENGINE=InnoDB | CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |user_info|
--

DROP TABLE IF EXISTS |user_info|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |user_info| (
  |user_id| int(11) NOT NULL AUTO_INCREMENT | 用户编号
  |platform_id| int(11) | NULL | 平台编号
  |user_real_name| varchar(200) COLLATE utf8_bin | NULL | 用户真实姓名
  |user_login_name| varchar(200) COLLATE utf8_bin | NULL | 用户登录名
  |user_password| varchar(1024) COLLATE utf8_bin | NULL | 登录密码
  |phone| varchar(50) COLLATE utf8_bin | NULL | 手机
  |mail_address| varchar(200) COLLATE utf8_bin | NULL | 邮箱
  |user_status| varchar(2) COLLATE utf8_bin | NULL | 用户状态
  |remark| varchar(500) COLLATE utf8_bin | NULL | 备注
  |create_time| varchar(6) COLLATE utf8_bin | NULL | 创建时间
  |create_date| varchar(8) COLLATE utf8_bin | NULL | 创建日期
  |is_delete| varchar(1) COLLATE utf8_bin | NULL | 是否删除
  |nickname| varchar(200) COLLATE utf8_bin | NULL
  |gender| char(1) COLLATE utf8_bin | NULL
  |birthdate| date | NULL
  |address| varchar(500) COLLATE utf8_bin | NULL
  |profile_picture| varchar(300) COLLATE utf8_bin | NULL | 头像
  |create_timestamp| timestamp NULL | CURRENT_TIMESTAMP
  |salt| varchar(20) COLLATE utf8_bin | NULL
  PRIMARY KEY (|user_id|)
  KEY |FK_Relationship_1| (|platform_id|)
  CONSTRAINT |FK_Relationship_1| FOREIGN KEY (|platform_id|) REFERENCES |platform| (|platform_id|)
) ENGINE=InnoDB AUTO_INCREMENT=804 | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT |=用户表;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |user_online|
--

DROP TABLE IF EXISTS |user_online|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |user_online| (
  |online_id| int(11) NOT NULL AUTO_INCREMENT | 在线编号
  |user_id| int(11) | NULL | 用户编号
  |session_id| varchar(100) COLLATE utf8_bin | NULL | session_id
  |online_time| timestamp NOT NULL | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  PRIMARY KEY (|online_id|)
) ENGINE=InnoDB | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT |=用户在线;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |user_sports_camp|
--

DROP TABLE IF EXISTS |user_sports_camp|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |user_sports_camp| (
  |sports_camp_id| int(11) NOT NULL | 运动派编号
  |user_id| int(11) NOT NULL | 用户编号
  PRIMARY KEY (|sports_camp_id||user_id|)
) ENGINE=InnoDB | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT |=用户与运动派关系表;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |users_roles|
--

DROP TABLE IF EXISTS |users_roles|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |users_roles| (
  |user_id| int(11) NOT NULL | 用户编号
  |role_id| int(11) NOT NULL | 角色编号
  PRIMARY KEY (|user_id||role_id|)
  KEY |FK_users_roles2| (|role_id|)
  CONSTRAINT |FK_users_roles| FOREIGN KEY (|user_id|) REFERENCES |user_info| (|user_id|)
  CONSTRAINT |FK_users_roles2| FOREIGN KEY (|role_id|) REFERENCES |role_info| (|role_id|)
) ENGINE=InnoDB | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT |=用户角色关系表;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table |world_city|
--

DROP TABLE IF EXISTS |world_city|;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE |world_city| (
  |id| int(11) NOT NULL AUTO_INCREMENT | 编号
  |name| varchar(255) COLLATE utf8_bin | NULL | 名称
  |parent_id| int(11) | NULL | 父编号\r\n
  |remark| varchar(500) COLLATE utf8_bin | NULL | 备注
  |name_en| varchar(255) COLLATE utf8_bin |  | 英文名
  PRIMARY KEY (|id|)
) ENGINE=InnoDB AUTO_INCREMENT=3690 | CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT |=世界城市表;
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
