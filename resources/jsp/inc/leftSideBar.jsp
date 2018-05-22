<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/dist/img/avatar5.png" class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>${sessionScope.username }</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>
		<!-- search form -->
		<form action="#" method="get" class="sidebar-form">
			<div class="input-group">
				<input type="text" name="q" class="form-control" placeholder="搜索...">
				<span class="input-group-btn">
					<button type="submit" name="search" id="search-btn"
						class="btn btn-flat">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form>
		<!-- /.search form -->
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">导航菜单</li>
			<!-- QQ群管理 -->
			<li class="treeview">
				<a href="#"> <i class="fa fa-qq"></i><span>QQ群管理</span> <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>
				<!-- 二级菜单 -->
				<ul class="treeview-menu">
					<li>
						<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/servlet/toGroupList.do');"><i class="fa fa-circle-o"></i>QQ群列表</a>
					</li>
					
					<%-- <li>
						<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/user/userList.do');"><i class="fa fa-circle-o"></i>用户列表</a>
					</li> --%>
				
				</ul>
			</li>
			
			<!-- 全文检索 -->
			<li class="treeview">
				<a href="#"> <i class="fa fa-search"></i><span>全文检索</span> <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>
				<!-- 二级菜单 -->
				<ul class="treeview-menu">
					<li>
						<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/servlet/toSearch.do');"><i class="fa fa-circle-o"></i>全文检索</a>
					</li>
				</ul>
			</li>
			
			<!-- 报告管理 -->
			<li class="treeview">
				<a href="#"> <i class="fa fa-file-text"></i><span>报告管理</span> <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>
				<!-- 二级菜单 -->
				<ul class="treeview-menu">
					<li>
						<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/report/toReport.do');"><i class="fa fa-circle-o"></i>报告管理</a>
					</li>
					<%-- <li>
						<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/report/toAdd.do');"><i class="fa fa-circle-o"></i>添加报告</a>
					</li> --%>
				</ul>
			</li>
			
			<!-- 用户反馈 -->
			<li class="treeview">
				<a href="#"> <i class="fa fa-paper-plane"></i><span>用户反馈</span> <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>
				<!-- 二级菜单 -->
				<ul class="treeview-menu">
					<li>
						<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/feedback/toFeedBack.do');"><i class="fa fa-circle-o"></i>用户反馈</a>
					</li>
					<%-- <li>
						<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/report/toAdd.do');"><i class="fa fa-circle-o"></i>添加报告</a>
					</li> --%>
				</ul>
			</li>
			
			<!-- 授权管理 -->
			<c:if test="${sessionScope.role eq '1' }">
			<li class="treeview">
				<a href="#"> <i class="glyphicon glyphicon-eye-close"></i><span>授权管理</span> <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>
				<!-- 二级菜单 -->
				<ul class="treeview-menu">
					<li>
						<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/monitor/toMonitor.do');"><i class="fa fa-circle-o"></i>监控账号管理</a>
					</li>
				</ul>
			</li>
			</c:if>
			
			<!-- 用户管理 -->
			<%-- <li class="treeview">
				<a href="#"> <i class="fa fa-user-circle"></i> <span>用户管理</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span> </a>
				<!-- 二级菜单 -->
				<ul class="treeview-menu">
					<c:if test="${sessionScope.role eq '1' }">
						<li>
							<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/servlet/toUserList.do');"><i class="fa fa-circle-o"></i>用户列表</a>
						</li>
					
						<li>
							<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/servlet/toAddUser.do');"> <i class="fa fa-circle-o"></i>新增用户 </a>
						</li>
					
					</c:if>
					<c:if test="${sessionScope.role ne '1' }">
						<li>
							<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/servlet/toEdit.do');"> <i class="fa fa-circle-o"></i>编辑用户 </a>
						</li>
					
					</c:if>
				</ul>
			</li> --%>
			
			<!-- 系统管理 -->
			<li class="treeview">
				<a href="#"> <i class="fa fa-gears"></i><span>系统管理</span> <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>
				<!-- 二级菜单 -->
				<ul class="treeview-menu">
					<!-- 用户管理 -->
					<li class="treeview">
						<a href="#"> <i class="fa fa-user-circle"></i> <span>用户管理</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span> </a>
						<ul class="treeview-menu">
							<c:if test="${sessionScope.role eq '1' }">
								<li>
									<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/servlet/toUserList.do');"><i class="fa fa-circle-o"></i>用户列表</a>
								</li>

								<li>
									<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/servlet/toAddUser.do');"><i class="fa fa-circle-o"></i>新增用户</a>
								</li>

							</c:if>
							<c:if test="${sessionScope.role ne '1' }">
								<li>
									<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/servlet/toEdit.do');"><i class="fa fa-circle-o"></i>编辑用户</a>
								</li>
							</c:if>
						</ul>
					</li>
					<c:if test="${sessionScope.role eq '1' }">
					<li class="treeview">
						<a href="#"> <i class="fa fa-users"></i> <span>部门管理</span> <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a> 
						<ul class="treeview-menu">
							<li>
								<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/department/toDepart.do');"><i class="fa fa-circle-o"></i>部门列表</a>
							</li>
							
							<%-- <li>
								<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/department/toAdd.do');"><i class="fa fa-circle-o"></i>添加部门</a>
							</li> --%>
						</ul>
					</li>
					</c:if>
				</ul>
			</li>
			
			
			<!-- 日志管理 -->
			<c:if test="${sessionScope.role eq '1' }">
			<li class="treeview">
				<a href="#"> <i class="fa fa-file-text-o"></i><span>日志管理</span> <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>
				<!-- 二级菜单 -->
				<ul class="treeview-menu">
					<li>
						<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/servlet/toLogList.do');"><i class="fa fa-circle-o"></i>登录日志列表</a>
					</li>
				</ul>
			</li>
			</c:if>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>
<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>

<script>
	$(document).ready(function() {
		$('div[class="content-wrapper"]').load("${pageContext.request.contextPath}/servlet/toGroupList.do");
	
	});

	function pageLoad(url) {
		$('div[class="content-wrapper"]').load(url);
	}
</script>