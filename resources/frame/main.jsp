<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>信息管理系统</title>
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
<!-- DataTables -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/dataTables.checkboxes.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/extensions/Select/css/select.dataTables.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/skins/_all-skins.min.css">

<!-- Select2 -->
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2/select2.min.css"> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/dist/css/select2.min.css">
<!-- CheckBox -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/extensions/checkboxes/css/dataTables.checkboxes.css">
<!-- Pace -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/pace/pace.min.css">


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
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<jsp:include page="${pageContext.request.contextPath}/jsp/inc/header.jsp" />
		<jsp:include page="${pageContext.request.contextPath}/jsp/inc/leftSideBar.jsp" />
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			
		</div>
		<jsp:include page="${pageContext.request.contextPath}/jsp/inc/footer.jsp" />

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Create the tabs -->
			<!-- <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
				<li><a href="#control-sidebar-home-tab" data-toggle="tab"><i
						class="fa fa-home"></i></a></li>
				<li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i
						class="fa fa-gears"></i></a></li>
			</ul> -->
			<!-- Tab panes -->
			<div class="tab-content">
				<!-- Home tab content -->
				<div class="tab-pane" id="control-sidebar-home-tab">
					<h3 class="control-sidebar-heading">最近活动</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-birthday-cake bg-red"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">傻狍子生日</h4>

									<p>4月23日</p>
								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-user bg-yellow"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">艾瑞克更新了他的资料</h4>

									<p>新手机号码：138xxxxxxxx</p>
								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-envelope-o bg-light-blue"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">诺亚被加入邮件列表</h4>

									<p>nora@njsecnet.com</p>
								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-file-code-o bg-green"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">系统维护任务执行</h4>

									<p>花费 5 秒</p>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

					<h3 class="control-sidebar-heading">待办事务进度</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									整理硬盘碎片 <span
										class="label label-danger pull-right">20%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-danger"
										style="width: 20%"></div>
								</div>
						</a></li>
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									创建新的工作列表 <span class="label label-success pull-right">40%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-success"
										style="width: 40%"></div>
								</div>
						</a></li>
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									接待广东省厅战友 <span
										class="label label-warning pull-right">0%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-warning"
										style="width: 0%"></div>
								</div>
						</a></li>
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									开通银行信用卡 <span class="label label-primary pull-right">100%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-primary"
										style="width: 100%"></div>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

				</div>
				<!-- /.tab-pane -->
				<!-- Stats tab content -->
				<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab
					Content</div>
				<!-- /.tab-pane -->
				<!-- Settings tab content -->
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">常规设置</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading"> 报告面板使用情况 <input type="checkbox" class="pull-right" checked>
							</label>

							<p></p>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> 允许邮件转发 <input type="checkbox" class="pull-right" checked>
							</label>

							<p></p>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> 在帖子中显示作者 <input type="checkbox" class="pull-right" checked>
							</label>

							<p>允许用户在帖子中显示他们的姓名</p>
						</div>
						<!-- /.form-group -->

						<h3 class="control-sidebar-heading">图标设置</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading"> 显示我为在线状态 <input type="checkbox" class="pull-right" checked>
							</label>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> 关闭通知 <input type="checkbox" class="pull-right">
							</label>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> 关闭历史图标 <a href="javascript:void(0)" class="text-red pull-right"><i
									class="fa fa-trash-o"></i></a>
							</label>
						</div>
						<!-- /.form-group -->
					</form>
				</div>
				<!-- /.tab-pane -->
			</div>
		</aside>
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

	<!-- jQuery 2.2.3 -->
	<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	<!-- Select2 -->
	<script src="${pageContext.request.contextPath}/plugins/dist/js/select2.min.js"></script>
	<!-- DataTables -->
	<script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.checkboxes.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/datatables/extensions/Select/js/dataTables.select.min.js"></script>
	
	<!-- PACE -->
	<script src="${pageContext.request.contextPath}/plugins/pace/pace.min.js"></script>
	<!-- SlimScroll -->
	<script src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="${pageContext.request.contextPath}/dist/js/app.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="${pageContext.request.contextPath}/dist/js/demo.js"></script>
</body>
</html>
