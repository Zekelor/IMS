<%@ page language="java" pageEncoding="UTF-8"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>用户管理 <small>添加用户</small></h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
		<li><a href="#">用户管理</a></li>
		<li class="active">添加用户</li>
	</ol>
</section>
<section class="content">
	<div class="row">
		<form id="frmAddUser" action="${pageContext.request.contextPath}/user/addUser.do" method="post">
			<div class="col-md-6">
				<!-- Form Element sizes -->
				<div class="box box-success">
					<div class="box-header with-border">
						<h3 class="box-title">用户账户信息</h3>
					</div>
					<div class="box-body">
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-user"></span></span>
							<input type="text" class="form-control" placeholder="用户名" id="userName" name="userName" autocomplete="off">
						</div>
						<br />
						<div class="input-group">
							<span class="input-group-addon"><span
								class="fa fa-unlock-alt"></span></span> <input type="password"
								class="form-control" placeholder="密码" id="password" name="password" autocomplete="off">
						</div>
						<br />
						<div class="input-group">
							<span class="input-group-addon"><span
								class="fa fa-user-circle-o"></span></span> <input type="password"
								class="form-control" placeholder="确认密码" id="confirmPassword" name="confirmPassword" autocomplete="off">
						</div>
						<br />
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-users"></span></span>
							<input type="text" class="form-control" placeholder="QQ群号" id="qqGroupNo" name="qqGroupNo" autocomplete="off">
						</div>
						<br />
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-phone"></span></span>
							<input type="text" class="form-control" placeholder="电话号码" id="mobileNumber" name="mobileNumber" autocomplete="off">
						</div>
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<button type="button" class="btn btn-primary" onclick="addUser();">创建</button>
					</div>
				</div>
				<!-- /.box -->
			</div>
			<!-- /.col (left) -->
		</form>
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->

<script>
	function addUser() {
		var action = $('#frmAddUser').attr('action');
		var type = $('#frmAddUser').attr('method');
		var userName = $('#userName').val();
		var password = $('#password').val();
		var confirmPassword = $('#confirmPassword').val();
		var qqGroupNo = $('#qqGroupNo').val();
		var mobileNumber = $('#mobileNumber').val();
		
		$.ajax({
			url: action, 
			dataType: 'json', 
			type: type, 
			data: {
				userName: userName, 
				password: password, 
				confirmPassword: confirmPassword, 
				qqGroupNo: qqGroupNo, 
				mobileNumber: mobileNumber
			}, 
			success: function (data) {
				if (data.success) {
					$('#myModal').modal();
				}
				else {
					alert(data.message);
				}
			}, 
			error: function (data) {
				alert('服务器错误');
			}
		});
	}
</script>


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