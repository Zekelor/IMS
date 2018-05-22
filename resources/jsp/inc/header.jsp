<%@ page language="java" pageEncoding="UTF-8"%>
<header class="main-header">
	<!-- Logo -->
	<a href="" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"></span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><b>信息管理系统</b></span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span> <span
			class="icon-bar"></span> <span class="icon-bar"></span> <span
			class="icon-bar"></span>
		</a>

		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<!-- Messages: style can be found in dropdown.less-->
				
				<!-- User Account: style can be found in dropdown.less -->
				<li class="dropdown user user-menu">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
					<img src="${pageContext.request.contextPath}/dist/img/avatar5.png" class="user-image" alt="User Image"> <span class="hidden-xs">${sessionScope.username }</span>
				</a>
					<ul class="dropdown-menu">
						<!-- User image -->
						<li class="user-header">
						<img src="${pageContext.request.contextPath}/dist/img/avatar5.png" class="img-circle" alt="User Image">

							<p>
								当前用户  - ${sessionScope.username }
								  
							</p></li>
						<!-- Menu Body -->
						<!-- <li class="user-body">
							<div class="row">
								<div class="col-xs-4 text-center">
									<a href="#">江苏省</a>
								</div>
								<div class="col-xs-4 text-center">
									<a href="#">南京市</a>
								</div>
								<div class="col-xs-4 text-center">
									<a href="#">省厅</a>
								</div>
							</div> /.row
						</li> -->
						<!-- Menu Footer-->
						<li class="user-footer">
							<!-- <div class="pull-left">
								<a href="#" class="btn btn-default btn-flat">资料</a>
							</div> -->
							<div class="pull-right">
								<%-- <a href="javascript:;" onclick="location.href = '${pageContext.request.contextPath}/user/logout.do';" class="btn btn-default btn-flat">退出</a> --%>
								<a href="javascript:;" onclick="location.href = '${pageContext.request.contextPath}/servlet/logout.do?loginUser=${sessionScope.username }';" class="btn btn-default btn-flat">退出</a>
							</div>
						</li>
					</ul></li>
				<!-- Control Sidebar Toggle Button -->
				<li><a href="#" data-toggle="control-sidebar"><i
						class="fa fa-gears"></i></a></li>
			</ul>
		</div>
	</nav>
</header>