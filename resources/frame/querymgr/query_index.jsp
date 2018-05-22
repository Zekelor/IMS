<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Content Header (Page header) -->
<style type="text/css">
h1, h3 {
	font-family: 微软雅黑 !important;
}

#canvas{
	-webkit-animation: 1.5s;
	opacity: 1;
	background-image:url(../../frame/image/medic.jpg);
	background-repeat:no-repeat;
}
.search-logo{
	width:75px;
	height:75px;
	float:left;
	position:relative;
	left:60px;
	background-color: transparent;
	background-image: url(../../frame/querymgr/image/logo.png);
}
tp{
	position: relative;
	top:8px;
}
</style>
<div id="canvas" style="height:874px;">
<section class="content-header"></section>
<c:set var="count" value="1" />
<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<!-- <div class="box" >
				<div class="box-body" id="canvas"> ./此项带有窗体BOX效果 -->
			<div class="" >
				<div class="">

					<div class="register-box">
						<div class="register-logo">
							<div class="search-logo"></div><div><b class="tp">全文检索</b></div>
						</div>

						<div class="register-box-body">
							<p class="login-box-msg">支持对聊天内容、QQ号、QQ群检索</p>

							<form id="searchForm" action="${pageContext.request.contextPath}/servlet/queryResult" method="post">
								<div class="form-group has-feedback">
									<input type="text" id="account" name="account" class="form-control" placeholder="QQ账号">
									<span class="fa fa-qq form-control-feedback"></span>
								</div>
								<div class="form-group has-feedback">
									<input type="text" id="group" name="group" class="form-control" placeholder="QQ群">
									<span class="fa fa-wechat form-control-feedback"></span>
								</div>
								<div class="form-group has-feedback">
									<input type="text" id="msg" name="msg" class="form-control" placeholder="聊天内容">
									<span class="fa fa-comment form-control-feedback"></span>
								</div>

								<div class="row">
									<div class="col-xs-12">
										<!-- <button type="button" class="btn btn-block btn-primary btn-lg">Primary</button> -->
										 <button type="button" class="btn btn-primary btn-block " onclick="query();">Go!</button>
										 
									</div>
									<!-- /.col -->
								</div>
							</form>

							<!-- <div class="social-auth-links text-center">
								<p>- OR -</p>
									<a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign up using Facebook</a> 
									<a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign up using Google+</a>
							</div> -->
						</div>
						<!-- /.form-box -->
					</div>
					<!-- /.register-box -->
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

<!-- 提示Modal -->
<div class="modal fade" id="tipModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="tipModalLabel">提示</h4>
			</div>
			
			<div class="modal-body">
				查询内容不能为空
			</div>
			<div class="modal-footer">
				<!-- <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button> -->
				<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
			</div>
		
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		
	});
	
	//点击进行查询
	function query(){
		var account =$("#account").val();
		var group =$("#group").val();
		var msg =$("#msg").val();
		if(account==""&&group==""&&msg==""){
			$("#tipModal").modal();
		}else{
			var url ="${pageContext.request.contextPath}/servlet/queryResult?account="+account+"&group="+group+"&msg="+encodeURIComponent(msg);
			pageLoad(url);
		}
	
	}

	function pageLoad(url) {
		Pace.restart();
		$('div[class="content-wrapper"]').load(url);
	}
</script>
