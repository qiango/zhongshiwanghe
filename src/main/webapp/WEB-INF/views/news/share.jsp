<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<title>资讯详情</title>
	<link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link href="http://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
	<style>
		html,body{ height: 100%; }
		body{ background-color: #f8f8f8; font-family: "microsoft yahei"; font-weight: 400; font-size: 13px; }
		.f-300{ font-weight: 300; }
		.font-15{ font-size: 15px; }
		.font-20{ font-size: 20px; }
		.b-fff{ background-color: #fff; }
		.p-9{ padding: 11px 13px; }
		.m-0{ margin: 0 !important; }
		.mt-0{ margin-top: 0 !important; }
		.mt-1{ margin-top: 1px !important; }
		.mb-2{ margin-bottom: 2px !important; }
		.mt-3{ margin-top: 3px !important; }
		.mb-8{ margin-bottom: 8px !important; }
		.mb-22{ margin-bottom: 22px !important; }
		.mr-6{ margin-right: 6px !important; }
		.ml-6{ margin-left: 6px !important; }
		.mt-8{ margin-top: 8px; }
		.mx-15{ margin: 0 15px; }
		.ov-h{ overflow: hidden; }
		.d-block{ display: block; }		
		.b-s-b1{ box-shadow: 0 1px 1px 0 #eee; }
		.b-s-t1{ box-shadow: 0 -1px 1px 0 #eee; }
		.subscribe-btn{ background-color: #c93415; color: #fff; padding: 6px 8px; font-size: 14px; }
		.intro-title{ position: relative; padding-left: 15px; }
		.intro-title:before{ content: "";position: absolute; background-color: #c93415; top: 0; left: 0; height: 100%; width: 5px; }
		/*.vedio-item:nth-child(odd) { margin-left: 1%; }*/
		.vedio-item:nth-child(even) { margin-right: 3%; }
		.vedio-item{ width: 48.5%; overflow: hidden; font-weight: 300; }
		.color-000{ color: #000; }
		.more-content .fa-angle-right:after { content: "\f1db"; position: absolute; top: 1px; right: -2px; font-size: 13px; }
		.more-content { padding-right: 16px; position: relative; }
		.content-more{ background: #fff; margin-top: 1px; }
		.intro{ word-break: break-all; overflow: hidden; max-height: 3em; }
		.height-auto{ height: auto !important; max-height: 9999px; }
		.text-ellipsis{ overflow: hidden; text-overflow: ellipsis; white-space: nowrap; }
		/*.intro-panel{ max-height: 118px; }*/
	</style>
</head>
<body>
<!-- 
	<iframe src="http://open.iqiyi.com/developer/player_js/coopPlayerIndex.html?vid=0c2340bbe6cce1bde14e94e50cd754ac&tvId=386549000&accessToken=2.f22860a2479ad60d8da7697274de9346&appKey=3955c3425820435e86d0f4cdfe56f5e7&appId=1368" frameborder="0" width="100%" height="33.333%" allowfullscreen="true"></iframe>
 -->
	<iframe src="${video.video_url.mp4_HD }" frameborder="0" width="100%" height="33.333%" allowfullscreen="true"></iframe>
	<div class="b-fff p-9 mb-2">
		<!-- <div class="pull-right subscribe-btn mt-3"><i class="fa fa-plus mr-6"></i>订阅</div> -->
		<div class="media mt-0">
			<div class="media-left">
				<img src="<%=basePath %>${video.channel_image }" alt="" height="44" width="44" class="img-circle">
			</div>
			<div class="media-body">
				<h4 class="media-heading">${video.channel_name}</h4>
				<span class="f-300">${video.channel_describe }</span>
			</div>
		</div>
	</div>
	<div class="b-fff p-9 mb-22 b-s-b1 intro-panel">
		<div class="media mt-0">
			<div class="media-body">
				<h4 class="media-heading mb-8">${information.news_title}<i class="fa fa-angle-down pull-right font-20 js-angle-down"></i></h4>
				<span class="f-300">${information.create_time}</span>&nbsp;&nbsp;<span class="f-300">${video.watch_count }次播放</span>
				<div class="mt-8 intro">简介：${information.news_abstract }</div>
			</div>
		</div>
	</div>
	<section class="b-fff p-9 b-s-b1 clearfix">
		<h4 class="intro-title mt-0">推荐</h4>
		<c:forEach items="${recommend.news}" var="video" varStatus="status">
		  <div class="vedio-item pull-left mb-8">
		  	<a href="<%=basePath %>/v5/news/detail.htmls?news_id=${video.news_id }" class="color-000">
		  		<img src="<%=basePath %>${video.preview_image }" alt="" width="100%" height="96">
		  		<h5 class="m-0 font-15 mt-3 text-ellipsis">${video.news_title }</h5>
		  		<div class="mt-3 text-ellipsis">${video.news_abstract }</div>
		  	</a>
		  </div>
		</c:forEach>
		<!-- 
		<div class="vedio-item pull-left mb-8">
			<a href="" class="color-000">
				<img src="" alt="" width="100%" height="96">
				<h5 class="m-0 font-15 mt-3 text-ellipsis">标题</h5>
				<div class="mt-3 text-ellipsis">dasdasdas</div>
			</a>
		</div>
		<div class="vedio-item pull-left mb-8">
			<a href="" class="color-000">
				<img src="" alt="" width="100%" height="96">
				<h5 class="m-0 font-15 mt-3 text-ellipsis">标题</h5>
				<div class="mt-3 text-ellipsis">dasdasdas</div>
			</a>
		</div>
		<div class="vedio-item pull-left mb-8">
			<a href="" class="color-000">
				<img src="" alt="" width="100%" height="96">
				<h5 class="m-0 font-15 mt-3 text-ellipsis">标题</h5>
				<div class="mt-3 text-ellipsis">dasdasdas</div>
			</a>
		</div>
		 -->
	</section>
	<div class="col-xs-12 p-9 mb-22 content-more text-center b-s-b1">
		<a href="#" class="more-content color-000">查看更多<i class="fa fa-angle-right ml-6 has-feedback"></i></a>
	</div>
	<a href="https://itunes.apple.com/cn/app/cheng-ju-bei/id1094134268?mt=8" class="btn btn-default mx-15 font-15 mb-22 d-block ov-h js-download">查看更多资讯，请下载城俱杯APP</a>
	<script src="http://cdn.bootcss.com/jquery/2.2.4/jquery.js"></script>
	<script>
	if(location.hash == '#download'){
		$('.js-download').addClass('hide');
	}
	$('.js-angle-down').click(function(){
		$('.intro').toggleClass('height-auto');
	});
	</script>
</body>
</html>