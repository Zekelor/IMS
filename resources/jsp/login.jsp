<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>请登录</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/AdminLTE.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
<style type="text/css">
* {
	font-family: 微软雅黑;
}
#canvas{
	height:0;
	-webkit-animation: 1.5s;
	opacity: 1;
	background-image:url(../frame/image/1.jpg);

}
.white{
	color:#fff!important;
}

</style>
</head>
<body id="canvas" class="hold-transition login-page" >

<div style="width:100%;height:100%; ">
		<div class="login-box" >
		<div class="login-logo">
			<a class="white"><b>信息管理系统</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body" >
			<p class="login-box-msg">请登录</p>
		
			<form id="frmLogin" action="${pageContext.request.contextPath}/servlet/login.do" method="post">
				<div class="form-group has-feedback">
					<input type="text" name="userLoginToken.userName" id="userName" class="form-control" placeholder="用户名">
					<span class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" name="userLoginToken.userPassword" id="userPassword" class="form-control" placeholder="密　码">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<!--
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox" disabled="disabled"> 记住我
							</label>
						</div>
					</div>
					-->
					<!-- /.col -->
					<div class="col-xs-4 col-xs-offset-8">
						<button type="button" class="btn btn-primary btn-block btn-flat" onclick="login();">登录</button>
					</div>
					<!-- /.col -->
				</div>
			</form>
		
		<form id="pageForm" action="${pageContext.request.contextPath}/jsp/example/data.jsp" method="post">
		</form>

			<!-- <a href="#">忘记密码</a> -->

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->


<!-- /end wrapper -->
</div>

	
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">提示</h4>
				</div>
				<div class="modal-body">添加用户成功</div>
			</div>
		</div>
	</div>

	<!-- jQuery 2.2.3 -->
	<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
	<script>
		$(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});
			
			if (${sessionScope.USER_LOGIN_TOKEN ne null}) {
				location.href = '${pageContext.request.contextPath}/jsp/example/data.jsp';
			}
		});
		
		
		function login() {
			/* $.ajax({
				url: $('#frmLogin').attr('action'), 
				type: $('#frmLogin').attr('method'), 
				dataType: 'text', 
				data: {
					userName: $('#userName').val(), 
					userPassword: $('#userPassword').val()
				}, 
				success: function (data) {
					console.log("data"+data);
				
					if (data=="1") {
						//location.href = '${pageContext.request.contextPath}/jsp/example/data.jsp';
						 var ac=$("#pageForm").attr("action");
							console.log(ac)
						$("#pageForm").submit();
					}
					else {
						$('div[class="modal-body"]').html(data);
						$('#myModal').modal();
					}
				}, 
				error: function (data) {
					alert('服务器错误');
				}
			}); */
			$("#frmLogin").submit();
			
		}
		
		
		
		/* function login() {
			$.ajax({
				url: $('#frmLogin').attr('action'), 
				type: $('#frmLogin').attr('method'), 
				dataType: 'json', 
				data: {
					userName: $('#userName').val(), 
					userPassword: $('#userPassword').val()
				}, 
				success: function (data) {
				
					if (data.success) {
						location.href = '${pageContext.request.contextPath}/jsp/example/data.jsp';
						console.log(data)
					}
					else {
						$('div[class="modal-body"]').html(data.message);
						$('#myModal').modal();
					}
				}, 
				error: function (data) {
					alert('服务器错误');
				}
			});
		} */
	</script>
</body>
</html>
