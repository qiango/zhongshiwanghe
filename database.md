
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
location_name| varchar(11)  | NULL | 位置名称
status| int(11) | NULL | 位置狀态  0:未使用 1:使用中


### 广告属性
advertisement_properties

字段|类型|默认值|中文名
---|---|---|--
property_id| int(11) | NOT NULL AUTO_INCREMENT | 属性主键
ad_id| int(11) | NULL| 广告ID
property_code| varchar(100) | NULL| 属性編号
property_value| varchar(200) | NULL| 属性值




### 俱乐部
club

字段|类型|默认值|中文名
---|---|---|--
club_id| int(11)| NOT NULL AUTO_INCREMENT | 俱乐部编号
user_id| int(11) | NULL | 俱乐部申请人编号
id| int(11) | NULL | 编号
language_id| int(11) | NULL | 语言编号
club_name| varchar(255)  | NULL | 俱乐部名称
club_applicant_name| varchar(255)  | NULL | 俱乐部名称
create_time| varchar(6)  | NULL | 创建时间
create_date| varchar(10)  | NULL | 创建日期
club_status| varchar(2)  | NULL | 俱乐部状态
club_description| text  | NULL |俱乐部描述
club_create_date| varchar(8)  | NULL | 俱乐部成立时间
club_qq| varchar(50)  | NULL | 俱乐部qq群
club_name_short| varchar(100)  | NULL | 俱乐部间称
is_delete| char(1)  | 0 | 是否删除 , 0: 未删除, 1:已删除
club_pic| varchar(255)  | NULL | 俱乐部头像
longitude| decimal(13,10) | NULL | 俱乐部所在地经度
latitude| decimal(13,10) | NULL | 俱乐部所在地纬度
club_level| varchar(2)  | 1 | 俱乐部級別
club_rank| varchar(2)  | 1 | 俱乐部等級, (官方 / 认证)



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
competition_name| varchar(255)  | NULL | 赛事名称
competition_level| varchar(2)  | NULL | 赛事级别
competition_description| text | NULL | 赛事描述
competition_status| varchar(2)  | NULL | 赛事状态
create_time| varchar(6)  | NULL | 创建时间
create_date| varchar(8)  | NULL | 创建日期
competition_publicity_pictures| varchar(255)  | NULL | 赛事宣传图片
competition_primary_id| int(11) | NULL | 赛事主编号
registration_start_date| varchar(14)  | NULL | 报名开始时间
registration_end_date| varchar(14)  | NULL | 报名结束时间
competition_start_date| varchar(14)  | NULL | 比赛开始时间
competition_end_date| varchar(14)  | NULL | 比赛结束时间
id| int(11) | NULL | 城市ID
is_delete| varchar(1)  | NULL | 是否删除 0:未删除 , 1:已删除
compertition_live_status| varchar(2)  | NULL | 赛事进行状态
club_score_publish| char(1)  | NULL | 赛事成绩是否发布 , 1:发布 , 0/NULL:未发布


### 赛事报名表单
competition_application

字段|类型|默认值|中文名
---|---|---|--
competition_application_id| int(11) | NOT NULL AUTO_INCREMENT | 赛事报名编号
controls_id| int(11) | NULL | 控件编号
user_id| int(11) | NULL | 用户编号
competition_id| int(11) | NULL | 赛事编号
language_id| int(11) | NULL | 语言编号
create_time| varchar(6)  | NULL | 创建时间
create_date| varchar(8)  | NULL | 创建日期
controls_placeholder| varchar(255)  | NULL | 控件占位符
title_name| varchar(255)  | NULL | 标题名称
controls_data| varchar(255)  | NULL | 控件数据
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
create_time| varchar(6)  | NULL | 创建时间
create_date| varchar(8)  | NULL | 创建日期
title_name| varchar(255)  | NULL | 标题名称
controls_order| int(11) | NULL | 控件顺序
user_value| varchar(255)  | NULL | 用户数据
is_delete| char(1)  | 0 | 删除标志 1:删除 0:未删


### 赛事与俱乐部关系
competition_club

字段|类型|默认值|中文名
---|---|---|--
club_id| int(11) | NOT NULL | 俱乐部编号
competition_id| int(11)| NOT NULL | 赛事编号



### 报名表单中的控件
controls

字段|类型|默认值|中文名
---|---|---|--
controls_id| int(11)| NOT NULL AUTO_INCREMENT | 控件编号
controls_name| varchar(50)  | NULL | 控件名称
remark| varchar(500)  | NULL | 备注


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
serial_number| varchar(100) | NULL |  序列号
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
code| varchar(20)  | NOT NULL|编号
value| varchar(100)  | NULL|值
p_code| varchar(20)  | NULL|父级编号
language| varchar(7)  | NULL| 语言


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
coupon_name| varchar(200)  | NULL | 优惠券名称
coupon_describe| text |NULL | 优惠券描述
use_scope| int(11) | NULL | 使用范围 0:所有用户可用 1:分配給用户可用 2:俱乐部成员可用
coupon_type| int(11) | NULL |优惠类型  1:对订单的优惠 ; 2:对商品的优惠
start_time| timestamp NULL | NULL | 开始时间
end_time| timestamp NULL | NULL | 结束时间
coupon_status| char(1)  | NULL |狀态 1:可用 0:不可用
discount_type| int(11) unsigned | NULL | 1:直接调用公式  2:数量大于 min_amount 时调用公式  3: 数量在 min_amount 和 max_amount 之間时调用公式 \n


### 优惠券
mall_coupon_properties

字段|类型|默认值|中文名
---|---|---|--
id| int(11) | NOT NULL AUTO_INCREMENT | ID
coupon_id| int(11) | NULL | 优惠券id
property_code| varchar(20)  | NULL | 优惠券属性编码
property_value| varchar(30)  | NULL | 优惠券属性值


### 打折
mall_discount

字段|类型|默认值|中文名
---|---|---|--
id| int(11) | NOT NULL AUTO_INCREMENT
discount_type| int(11) | NULL |  折扣类型 1: 立减 XXX 元  2:滿 param XXXX  3:XX 折
param| decimal(20,2) | NULL | 参数
method| varchar(30) | NULL | 方程式


### 商品
mall_goods

字段|类型|默认值|中文名
---|---|---|--
id| int(11)| NOT NULL AUTO_INCREMENT
goods_code| varchar(30) | NULL | 商品编号
category| int(11) | NULL | 商品分类
name| varchar(30) | NULL | 商品名
goods_describe| text|NULL | 商品描述
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
price| decimal(20,2) | NULL | 价格
discount_id| int(11) | NULL | 折扣
current_price| decimal(20,2) | NULL | 现价


### 商品属性
mall_goods_properties

字段|类型|默认值|中文名
---|---|---|--
properties_id| int(11)| NOT NULL | 商品属性ID
goods_id| int(11) |NOT NULL | 商品ID
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
price| decimal(20,2) | NULL | 订单总价
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
single_price| decimal(20,2) | NULL | 单价


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
return_time| timestamp | NULL |  时间
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
menu_name| varchar(200)  | NULL | 菜单名称
menu_url| varchar(500)  | NULL | 菜单地址
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
news_content| text |NULL| 內容 富文本
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
comment_content| text | NULL | 评论内容
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
media_information| text|NULL | 对每个文件的介绍 图集中的每个图片 , 视频 fileid
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
id| int(11) | NOT NULL AUTO_INCREMENT | ID
origin_name| varchar(40)  | NULL | 原图片名
new_name| varchar(40)  | NULL | 新图片名
create_time| timestamp NULL | NULL |创建时间


### 角色
role_info

字段|类型|默认值|中文名
---|---|---|--
role_id| int(11)| NOT NULL AUTO_INCREMENT | 角色编号
role_name| varchar(100)  | NULL | 角色名称
remark| varchar(500)  | NULL | 备注
is_delete| char(1)  | 0 |  是否删除  1:已删 , 0:未删


### 角色菜单关系
roles_menus

字段|类型|默认值|中文名
---|---|---|--
role_id| int(11)| NOT NULL | 角色编号
menu_id| int(11)| NOT NULL | 菜单编号


### session中綁定数据
session_attribute

字段|类型|默认值|中文名
---|---|---|--
id| int(11) | NULL | session_time -> table_id
key_name| varchar(20) | NULL | 键名
value_content| varchar(300) | NULL | 数值


### session日志
session_log

字段|类型|默认值|中文名
---|---|---|--
id| int(11) | NULL | session_time -> id
function_name| varchar(100) | NULL | 用户访问的功能名
visit_time| timestamp NULL | NULL | 访问时间


### session时间
session_time

字段|类型|默认值|中文名
---|---|---|--
id| int(11) NOT NULL AUTO_INCREMENT
session_id| varchar(300) NOT NULL | session_id
create_time| timestamp NULL | NULL | 创建时间
last_use_time| timestamp NULL | NULL | 上次使用时间
due_time| timestamp NULL | NULL | 到期时间


### 配送地址
shipping_address

字段|类型|默认值|中文名
---|---|---|--
shipping_id| int(11) NOT NULL AUTO_INCREMENT | 配送地址ID
user_id| int(11) | NULL | 用户ID  user_info > user_id
city_code| varchar(20) | NULL | 地点标记   world_city > remark
detail_address| varchar(200) | NULL | 详细地址
receiver_name| varchar(50) | NULL | 收货人
receiver_phone| varchar(20) | NULL | 收货人电话
is_default| char(1) | NULL | 是否是默认地址  0: 否 1: 是
is_delete| char(1) | 0 | 删除标记 0: 未删除 1: 己删除


### 运动派
sports_camp

字段|类型|默认值|中文名
---|---|---|--
sports_camp_id| int(11) NOT NULL AUTO_INCREMENT | 运动派编号
language_id| int(11) | NULL | 语言编号
user_id| int(11) | NULL | 用户编号
sports_camp_name| varchar(255)  | NULL | 运动派名称
remark| varchar(500)  | NULL | 备注
is_delete| char(1)  | 0


### 版本信息
upgrade_version

字段|类型|默认值|中文名
---|---|---|--
iOS_current_version| varchar(30)  | NULL| iOS 版本
android_current_version| varchar(30)  | NULL| Android 版本
iOS| varchar(10)  | NULL| iOS 是否更新 , F:更新 ,  N:不更新
Android| varchar(10)  | NULL| Android 是否更新 , F:更新 ,  N:不更新
iOS_open| varchar(10)  | NULL| iOS 启用更新  yes:启用,  no:不启用
android_open| varchar(10)  | NULL| Android 启用更新  yes:启用,  no:不启用


### 用户参加的赛事
user_competition

字段|类型|默认值|中文名
---|---|---|--
competition_id| int(11) | 0 | 赛事编号
user_id| int(11) | 0 | 用户编号
user_competition_status| varchar(2)  | NULL | 用户赛事状态
create_time| timestamp | CURRENT_TIMESTAMP |  创建时间
apply_or_refuse_time| timestamp | NULL | 同意或拒绝时间



### 用户参加赛事被拒绝原因
user_competition_refuse_reason

字段|类型|默认值|中文名
---|---|---|--
id| int(11) | NOT NULL AUTO_INCREMENT | 主键
user_id| int(11) | NULL| 用户ID
competition_id| int(11) | NULL| 赛事ID
refuse_reason| varchar(200) | NULL| 原因
create_time| timestamp NULL | CURRENT_TIMESTAMP| 创建时间



### 用户参加俱乐部狀态
user_detail

字段|类型|默认值|中文名
---|---|---|--
club_id| int(11) | NULL | 俱乐部编号
user_id| int(11)| NOT NULL | 用户编号
jump_club_number| int(11) | NULL | 选择俱乐部跳过次数
join_club_status| varchar(2)  | NULL | 加入俱乐部状态
user_level| varchar(3)  | 99 | 用户级別
create_time| timestamp NULL | NULL | 创建时间
change_status_time| timestamp NULL | NULL | 狀态改变时间
club_refuse_id| int(11) | NULL | 拒绝原因ID


### 用户被拒绝加入俱乐部原因
user_detail_refuse_club_reason

字段|类型|默认值|中文名
---|---|---|--
club_reason_id| int(11) | NOT NULL AUTO_INCREMENT | 主键
reason_content| text | NULL  | 原因
create_time| timestamp NULL | CURRENT_TIMESTAMP | 创建时间


### 用户
user_info

字段|类型|默认值|中文名
---|---|---|--
user_id| int(11) NOT NULL AUTO_INCREMENT | 用户编号
platform_id| int(11) | NULL | 平台编号
user_real_name| varchar(200)  | NULL | 用户真实姓名
user_login_name| varchar(200)  | NULL | 用户登录名
user_password| varchar(1024)  | NULL | 登录密码
phone| varchar(50)  | NULL | 手机
mail_address| varchar(200)  | NULL | 邮箱
user_status| varchar(2)  | NULL | 用户状态
remark| varchar(500)  | NULL | 备注
create_time| varchar(6)  | NULL | 创建时间
create_date| varchar(8)  | NULL | 创建日期
is_delete| varchar(1)  | NULL | 是否删除
nickname| varchar(200)  | NULL | 昵称
gender| char(1)  | NULL | 性別
birthdate| date | NULL | 生日
address| varchar(500)  | NULL | 地址
profile_picture| varchar(300)  | NULL | 头像
create_timestamp| timestamp NULL | CURRENT_TIMESTAMP | 创建时间
salt| varchar(20)  | NULL | 盐



### 用户加入的运动派
user_sports_camp

字段|类型|默认值|中文名
---|---|---|--
sports_camp_id| int(11) |NOT NULL | 运动派编号
user_id| int(11)| NOT NULL | 用户编号


### 用户角色
users_roles

字段|类型|默认值|中文名
---|---|---|--
user_id| int(11)  | NOT NULL | 用户编号
role_id| int(11)  | NOT NULL | 角色编号


### 城市
world_city

字段|类型|默认值|中文名
---|---|---|--
id| int(11)| NOT NULL AUTO_INCREMENT | 编号
name| varchar(255)  | NULL | 名称
parent_id| int(11) | NULL | 父编号
remark| varchar(500)  | NULL | 备注
name_en| varchar(255)  |NULL  | 英文名



