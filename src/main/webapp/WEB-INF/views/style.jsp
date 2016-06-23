<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>中视网合后台管理系统</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/style/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/style/awesome/css/font-awesome.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/main_sys.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/style/chosen/chosen.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/style/bootstrap-datepicker/bootstrap-datepicker.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/style/upload/fileinput.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/style/jquery-ui/jquery-ui.min.css"/>
<script type="text/javascript" defer async src="<%=basePath %>/style/requirejs/require.min.js" data-main="<%=basePath %>/js/main"></script>