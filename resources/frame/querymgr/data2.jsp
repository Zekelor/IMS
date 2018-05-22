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
		<%-- <jsp:include page="${pageContext.request.contextPath}/jsp/inc/header.jsp" /> --%>
		<jsp:include page="${pageContext.request.contextPath}/jsp/inc/leftSideBar.jsp" />
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper" style="margin-left:0;">
			
		</div>
		<%-- <jsp:include page="${pageContext.request.contextPath}/jsp/inc/footer.jsp" /> --%>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			
			
			
		</aside>
	
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
	<%
		String qmcTag = new String(request.getParameter("qmcTag").getBytes("ISO8859-1"),"UTF-8");
		String qmcGroupName =new String(request.getParameter("qmcGroupName").getBytes("ISO8859-1"),"UTF-8");
	%>
	<input type="text" id="qmcGroup" value="${param.qmcGroup}"/>
	<input type="text" id="qmcGroupName" value="<%= qmcGroupName %>"/>
	<input type="text" id="qmcTag" value="<%= qmcTag %>"/>
	<input type="text" id="qmcQQ" value="${param.qmcQQ}"/>
	<input type="text" id="isDetail" value="${param.isDetail }"/>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$(".main-sidebar").hide();
		var qmcGroup =$("#qmcGroup").val();
		var qmcGroupName =$("#qmcGroupName").val();
		var qmcTag =$("#qmcTag").val();
		var qmcQQ =$("#qmcQQ").val();
		
		var url="${pageContext.request.contextPath}/servlet/toDetail.do?isDetail=1&qmcGroup="+qmcGroup+"&qmcQQ="+qmcQQ+"&qmcGroupName="+encodeURIComponent(qmcGroupName)+"&qmcTag="+encodeURIComponent(qmcTag);
		$('div[class="content-wrapper"]').load(url);
	
	});
	
	
	</script>
</body>
</html>
