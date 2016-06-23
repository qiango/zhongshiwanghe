WEB-INF<%@ page language="java" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=no">
<title>登录页</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/style/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/style/awesome/css/font-awesome.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/main_sys.css"/>
<script type="text/javascript" src="<%=basePath %>/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/style/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/style/validate/messages_cn.js"></script>
</head>
<body class="login-body">
	<!-- 加载过渡 -->
	<div class="loading-modal load"></div>



	<!--主体-->
	<div class="site-login container-fluid stretch">
		<div class="login-container panel panel-default container-fluid">
			<div class="panel-heading padding-collapse border-none">
				<div class="login-title panel-title">中视网合</div>
			</div>
			<form action="" class="form-horizontal" target="_self" id="login-form"  method="post">
				<div class="panel-body">
					<div class="ipt-container col-xs-12 stretch login-tips" id="error">请输入用户信息</div>
					<div class="ipt-container col-xs-12 stretch form-group">
						<input type="text" class="ipt-text pull-right form-control" name="user_login_name" id="user_login_name" required placeholder="用户名">
						<label id="user_login_name-error" class="error" for="user_login_name"></label>
					</div>
					<div class="ipt-container col-xs-12 stretch form-group">
						<input type="password" class="ipt-text pull-right form-control" name="user_password" id="user_password" rangelength="6,22" required placeholder="密码">
						<label id="user_password-error" class="error" for="user_password"></label>
					</div>
				</div>
				<div class="submit col-xs-12">
					<button class="sub-btn btn btn-primary btn-theme btn-block" type="submit">登录</button>
				</div>
			</form>
		</div>				
	</div>



	<script type="text/javascript">
		$(function(){			
			$(".loading-modal").removeClass('load');

			/*表单验证*/
		 	$("#login-form").validate({
		 		submitHandler : function(){
		 			$('.sub-btn').prop("disabled",true).text('登录中...');
	 				$.ajax({
	 		             type: "POST",
	 		             url: "<%=basePath %>/login.htmls",
	 		             data: $('#login-form').serialize(),
	 		             headers:{
							Accept:'*/*'
						 },
	 		             success: function(data){
	 		            	$('.sub-btn').prop("disabled",false).text('登录');
	 		            	if (0 == data.code) {
	 		            		window.location.href = "<%=basePath %>/redirect.htmls?url=" + data.data.url;  
	 		            	} else {
	 		            		$("#error").html("<span style='color:red'>"+data.message+"</span>");
	 		            	}
	 		             },
	 		             error: function(xhr,status,error){
	 		            	$('.sub-btn').prop("disabled",false).text('登录');
							$("#error").html("<span style='color:red'>网络有误，请稍后再试。</span>");
	 		             }
	
	 		        });
		 			return false;//阻止表单提交
		 		}
			}); 

		});
	</script>
</body>
</html>