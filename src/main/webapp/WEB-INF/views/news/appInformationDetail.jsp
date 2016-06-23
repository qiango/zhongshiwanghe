<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=no">
<title>资讯详情</title>
<style>
	.news-container{ margin: 25px 20px 15px; display:block; }
	.news-summary{ display:block; margin:8px 0; font-size: 14px; }
	.infor-title{ text-align: left; font-size: 20px; }
	.news-time{ text-align: left; font-size: 14px; color: #999; }
	.news-content{ font-size: 14px; text-align: left; margin-top: 20px; }
	.news-img{ text-align: left; }
	.comment{ font-size: 14px; text-indent: 2em; text-align: left; }
	.box{ padding-bottom: 60% !important; margin: 10px 0 0; }
	p span img{height: 80%; width: 80%;}
	span img{height: 80%; width: 80%;}
	p img{height: 80%; width: 80%;}
	.news-content img{height: 80%; width: 80%;}
	.mx-15{ margin: 0 15px; }
	.ov-h{ overflow: hidden; }
	.d-block{ display: block !important; }
	.mb-22{ margin-bottom: 22px !important; }
	.font-15{ font-size: 15px; }
</style>
</head>
<body style="margin: 0; padding: 0;">
	<script>
		!window.UE&&(document.write('<link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">'),document.write('<link href="http://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">'),document.write('<script src="http://cdn.bootcss.com/jquery/2.2.4/jquery.js"><\/script>'));
	</script>
	<article class="news-container">
		<h3 class="infor-title">${information.news_title}</h3>
		
		<aside class="news-time"><time>${information.create_time}</time></aside>

		<!-- 一张的时候输出 -->
		<c:if test="${information.image_list.size() == 1  }">
			<c:forEach items="${information.image_list }" var="info_image" varStatus="count">
				<div class="embed-responsive box">
	                 <img class="news-img embed-responsive-item"  src="<%=basePath %>/${info_image.media_url }" alt="加载失败">
	            </div>
	        </c:forEach>
        </c:if>
		
			<c:if test="${information.image_list.size() != 1  }">

		<div id="carousel-ad" class="carousel slide" data-ride="carousel" data-interval="3000"><!-- 多张的时候输出 -->
		   <ol class="carousel-indicators">
		   <c:forEach items="${information.image_list }" var="info_image" varStatus="status">
		      <c:choose>
                 <c:when test="${status.count=='1'}">
		      <li data-target="#carousel-ad" data-slide-to="${status.count}" class="active"></li>
                 </c:when>    
                 <c:otherwise>
		      <li data-target="#carousel-ad" data-slide-to="${status.count}" ></li>
                 </c:otherwise>
              </c:choose>
		   </c:forEach>
		   </ol>
           <div class="carousel-inner">
            <c:forEach items="${information.image_list }" var="info_image" varStatus="status">
            <c:choose>
               <c:when test="${status.count=='1'}">
               <div class="item active box embed-responsive">
                  <img class="news-img embed-responsive-item" src="<%=basePath %>${info_image.media_url }" alt="加载失败">
               </div>
               </c:when>    
               <c:otherwise>
               <div class="item box embed-responsive">
                 <img class="news-img embed-responsive-item" src="<%=basePath %>${info_image.media_url }" alt="加载失败">
               </div>
               </c:otherwise>
            </c:choose>
            </c:forEach>
          </div>
		</div>
        </c:if>
	
	
		

		<div class="news-content">
			${information.news_content}
		</div>
		
<%-- 		<footer class="comment">备注${information.remark }</footer>  --%>
	</article>
<!-- 	<a href="https://itunes.apple.com/cn/app/cheng-ju-bei/id1094134268?mt=8" class="btn btn-default mx-15 font-15 mb-22 d-block ov-h js-download">查看更多资讯，请下载城俱杯APP</a> -->
	<script>
		if(location.hash == '#download'){
			$('.js-download').addClass('hide');
		}
		!window.UE&&document.write('<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"><\/script>');
		var touchFunc = (function(){
		  	var startX = 0,
		  		startY = 0,
		  		  endX = 0,
		  		  endY = 0; 
		    return function(e){
		      	if(e.type === 'touchstart'){
		      		startX = e.changedTouches[0].pageX;
		      	 	startY = e.changedTouches[0].pageY;
		      	} else if (e.type === 'touchend'){
		      		endX = e.changedTouches[0].pageX;
				    endY = e.changedTouches[0].pageY;
				    e.preventDefault();
				    if(endX-startX > 0){ 
				    	if(!$('li.active').prev().length){
				    		$("#carousel-ad li").eq($('#carousel-ad li').length-1).click();
				    	} else {
				    		$('li.active').prev().click();
				    	}
				    } else if(endX-startX < 0){
				    	if(!$('li.active').next().length){
				    		$('#carousel-ad li').eq(0).click();
				    	} else {
				    		$('li.active').next().click();
				    	}
				    }
		      	}
		    };
	  	})();
		!window.UE&&($('#carousel-ad')[0].addEventListener('touchstart', touchFunc, false),
	  	$('#carousel-ad')[0].addEventListener('touchend', touchFunc, false));
	</script>
</body>
</html>