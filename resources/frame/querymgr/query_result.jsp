<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Content Header (Page header) -->
<style type="text/css">
h1, h3 {
	font-family: 微软雅黑 !important;
}

h3 span {
	color: #dd4b39 !important;
}

.highlight{
	font-size: 16px;color: #DB131B;font-weight: bold;
}
.back{
	float:right;
	margin-right:30px;
}
.back>a:before{
	padding: 0 5px;
	color: #ccc;
	content: "/\00a0";
}
.no-data{
	margin-top: 5px; 
	height: 225px; 
	background: url("../../frame/image/zw.png") no-repeat 0 0; 
	background-color: #f5f9fc; 
	background-position: 50% 50px; 
}
</style>
<div id="canvas" style="height:874px;">
	<c:set var="count" value="1" />
	<!-- Main content -->
	<section class="">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header">
						<h3 class="box-title">
							<c:if test="${account ne '' }">
						查询的QQ账号关键词：<span>${account }</span>
							</c:if>
							<c:if test="${group ne '' }">
					  	 查询的QQ群关键词：<span>${group }</span>
							</c:if>
							<c:if test="${msg ne '' }">
					 	查询的聊天内容关键词：<span>${msg }</span>
							</c:if>
						</h3>
						<div class="back"><i class="fa fa-dashboard"></i>
							<a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/servlet/toSearch.do');">返回查询首页</a>
						</div>

					</div>
					<div class="box-body">
						<div class="nav-tabs-custom">
						
						
							<!-- Tab头部 -->
							<ul class="nav nav-tabs">
								<c:if test="${groupCount > 0 }">
									<li id="groupTab" class="active"><a href="" id="tab1" data-toggle="tab">群账号结果</a></li>

								</c:if>
								<c:if test="${msgCount > 0 }">
									<li id="msgTab"><a href="" id="tab2" data-toggle="tab">聊天记录结果</a></li>

								</c:if>

							</ul>
							<div class="tab-content">
								<c:if test="${totalCount eq 0 }">

									<div class="no-data"></div>
								</c:if>
							</div>
							<!-- /.tab-content -->
						</div>
						<!-- nav-tabs-custom -->

					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</section>
	<!-- /.content -->
</div>
<!-- page script -->
<input type="hidden" id="group" value="${group}"/>
<input type="hidden" id="account" value="${account}"/>
<input type="hidden" id="msg" value="${msg}"/>

<script>
	$(function() {
		var curr = $(".nav-tabs li:first");
		$(curr).addClass('active');
		var group =$("#group").val();
		var account =$("#account").val();
		var msg =$("#msg").val();
		
		if($("#groupTab").hasClass("active")){
			
			$('div[class="tab-content"]').load("${pageContext.request.contextPath}/servlet/toQueryGroupList.do?group="+group+"&account="+account+"&msg="+encodeURIComponent(msg));
			
		}else if($("#msgTab").hasClass("active")){
		    $('div[class="tab-content"]').load("${pageContext.request.contextPath}/servlet/toQueryMsgList.do?group="+group+"&account="+account+"&msg="+encodeURIComponent(msg));
			
		}
		$("#tab1").click(function(){
			$('div[class="tab-content"]').load("${pageContext.request.contextPath}/servlet/toQueryGroupList.do?group="+group+"&account="+account+"&msg="+encodeURIComponent(msg));
			
		});

	
		$("#tab2").click(function(){
			$('div[class="tab-content"]').load("${pageContext.request.contextPath}/servlet/toQueryMsgList.do?group="+group+"&account="+account+"&msg="+encodeURIComponent(msg));
			
		});
	});

	

	function pageLoad(url) {
		$('div[class="content-wrapper"]').load(url);
	}
</script>