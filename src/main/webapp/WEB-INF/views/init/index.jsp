<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../style.jsp"%>
<!--[if lte IE 8]>
	<style>
		.navbar-nav>li{ float: left !important; }
		.navbar-nav{ float: left; }
		.navbar-nav.tab-menu{ margin-top: 12px; } 
		.login-list{ float: right; }
		.navbar-static-top .navbar-collapse { padding-right: 0; padding-left: 0; }
		.navbar-collapse { width: auto; border-top: 0; display: block; }
		.navbar-header { float: left; margin: 8px 0 0 0 !important; }
		.navbar-header .collapsed{ display: none; }
	</style>
<![endif]-->
</head>
<body>

	<!-- 加载过渡 -->
	<div class="loading-modal load"></div>

	<!--头部-->
	<div class="site-header container-fluid hide" id="wj-header">
		<div class="navbar-header stretch site-title-respond float-respond">
	      	<button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#cn-navbar">
	      		<i class=" fa fa-list fa-lg color-white"></i>
	      	</button>
			<div class="site-logo navbar-brand">
				<a href="#" class="color-white">中视网合后台管理系统</a><!-- <img src="images/logo.png"> -->
			</div>
    	</div>
		<div id="cn-navbar" class="navbar-collapse collapse">
		<ul class="nav navbar-nav tab-menu">
			<li class="text-hovered">
				<a href="#main-menu" data-toggle="tab"
				class="tab-item tab-active text-hovered stretch">系统管理</a>
			</li>
			<li class="text-hovered">
				<a href="#setting" data-toggle="tab"
				class="tab-item text-hovered">运营系统管理</a>
			</li>
		</ul>
		<ul class="nav navbar-nav navbar-right login-list">
			<li class="text-hovered">
				<span class="user-pic"><img src="images/login-pic.jpg"></span>
				<span class="user-name"></span>
				<i class="arrow-down fa fa-angle-down"></i>
			</li>
			<li class="login-info">
				<div class="item text-hovered" id="set-account">
					<a href="javascript:void(0);">账号设置</a>
				</div>
				<div class="item text-hovered" id="un-login" data-path="/logout.htmls">
					<a href="javascript:void(0);">退出</a>
				</div>
			</li>
		</ul>
		</div>
      
	</div>

	<!--主体-->
	<div class="site-content container-fluid stretch hide">
		<!--左菜单-->
		<div class="tab-content left-menu pull-left has-feedback">
			<ul class="tab-pane active height-stretch scroll-auto" id="main-menu">

              <c:forEach items="${menu_tree}" var="menu">
				<li class="cell-list">
					<div class="text-hovered" data-toggle="collapse"
						data-target="#all-order_${menu.parent_id }" >
						<i class="fa fa-file-text-o"></i><a href="javascript:void(0);">${menu.parent_name }</a>
					</div>
					
					<ul class="retract collapse" id="all-order_${menu.parent_id }">
					<c:forEach items="${menu.menu_name_list }" var="menuname">
                        <li class="child-item text-hovered"><a href="#${menuname.menu_url }" class="" title="">${menuname.menu_name } </a></li>
					</c:forEach>
                    </ul>
				</li>
			  </c:forEach>  

		    </ul>
		</div>

		<!--右内容-->
		<div class="right-content" >
			<div class="real-content scroll-auto col-xs-12 stretch" id="wj-main-content">
				<div class="display-table col-xs-12 padding-collapse">
					<div class="welcome-content">
<!-- 						<div class="welcome-box"> -->
<!-- 							<h2 class="user-name">xxx</h2> -->
<!-- 							<div>欢迎进入中视网合后台管理系统</div> -->
<!-- 							<div>您现在可以进行相关操作了</div> -->
<!-- 						</div> -->
						<div class="panel-plg bg-white width-66p col-xs-offset-2 my-30 has-feedback">
							<div class="ribbon ribbon-default"></div>
							<table class="word-doc table table-hover">
								<caption class="bg-tspt"><h3 class="bg-tspt">俱乐部概览</h3></caption>
								<thead>
									<tr>
										<th>次序</th>
										<th>俱乐部名称</th>
										<th>俱乐部人数</th>
									</tr>
								</thead>
								<tbody>
								
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--底部-->
	<div class="site-footer container-fluid stretch hide" id="wj-footer">
		<!-- <div class="col-xs-2 site-logo"><a href="#"><img src="images/login-logo.png"></a></div> -->
		<div class="site-copyRight">Copyright 2015,All rights reserved</div>
	</div>
</body>
</html>