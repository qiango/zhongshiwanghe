<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html><head>
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
	.news-content img{height: 80%; width: 80%;}
	.box-item{ margin-top: 8px; margin-bottom: 20px; }
	.mb-5{ margin-bottom: 5px; }
</style>
<link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="http://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
</head>
<body style="margin: 0; padding: 0;">
	<article class="news-container">
		<h3 class="infor-title">${information.news_title}</h3>
        
        <aside class="news-time"><time>${information.create_time}</time></aside>
					
					
		<c:forEach items="${information.image_list }" var="info_image" varStatus="count">
                <div class="box-item">
                   <img class="mb-5"  src="<%=basePath %>${info_image.media_url }" alt="加载失败" width="100%">
	               <div>${info_image.media_information }</div>
                </div>
        </c:forEach>
					
    <!-- 
	         <img class="mb-5" src="http://127.0.0.1:8080/springmvc_mybatis_demo/upload/2.jpg" alt="加载失败" width="100%">
	         <div>sdsadasdasdasdsa</div>
	    </div>
	    <div class="box-item">
     -->
	</article>
</body>
</html>